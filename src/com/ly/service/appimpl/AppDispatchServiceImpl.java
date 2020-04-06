package com.ly.service.appimpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.ActivityInfo;
import com.ly.entity.ActivityRead;
import com.ly.entity.app.APPCourierStore;
import com.ly.entity.app.APPStoreSupplies;
import com.ly.entity.app.Smslog;
import com.ly.entity.app.Store;
import com.ly.entity.app.Vendor;
import com.ly.entity.background.Account;
import com.ly.entity.background.SmsSendlog;
import com.ly.mapper.AccountMapper;
import com.ly.mapper.AppDispatchMapper;
import com.ly.mapper.AppFillingSingleMapper;
import com.ly.service.AccountService;
import com.ly.service.ActivityService;
import com.ly.service.AppDispatchService;
import com.ly.service.SmslogService;
import com.ly.util.Common;
import com.ly.util.EncodingUtil;
import com.ly.util.FTPLinuxUtils;
import com.ly.util.Md5Tool;
import com.ly.util.PropertiesUtils;
import com.ly.util.SmsSend;

import sun.security.provider.MD5;

/**
 * app端接口类
 * @author admin
 * @date 2018年4月20日 上午10:22:50
 * @described
 */
@Service("appDispatchService")
@Transactional
public class AppDispatchServiceImpl implements AppDispatchService{

	@Autowired
	private AppDispatchMapper appDispatchMapper;
	
	@Autowired
	private SmslogService smslogService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private AppFillingSingleMapper appFillingSingleMapper;
	
	/**
	 * 登陆令牌生成
	 * 生成策略：用户名+时间戳,在使用MD5加密
	 * @author zhangzhi
	 * @date 2018年4月20日上午10:36:32
	 * @param
	 */
	public String productLoginToken(String accountName, String password) {
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String str = time + accountName + password ;
		str = Md5Tool.getMd5(str);
		return str;
	}

	/**
	 * 登陆接口
	 */
	@Override
	public Object login(String accountName, String password) {
		Map<String,Object> map = new HashMap<>();
		if(accountName == null || "".equals(accountName) || password == null || "".equals(password)){
			map.put("state", "false");
			map.put("message", "参数错误!");
		}else{
			password = Md5Tool.getMd5(password);
			//校验是否存在用户
			Map<String, Object> user = appDispatchMapper.checkLogin(accountName,password);
			if(user == null){
				map.put("state", "false");
				map.put("message", "账号或密码错误!");
			}else{
				//生成新的令牌，修改用户令牌
				String token = productLoginToken(accountName, password);
				appDispatchMapper.updateLoginToken(accountName,token);
				map.put("state", "true");
				map.put("message", "登录成功!");
				map.put("token", token);
				map.put("accountType", user.get("accountType"));
			}
		}
		return map;
	}

	/**
	 * 忘记密码
	 */
	@Override
	public Object forgetPassword(String accountName, String password1, String password2) {
		Map<String, Object> map = new HashMap<>();
		if(accountName == null || "".equals(accountName) || password1 == null || "".equals(password1)){
			map.put("state", "false");
			map.put("message", "参数错误");
			return map;
		}
		if(!password1.equals(password2)){
			map.put("state", "false");
			map.put("message", "密码不一致");
			return map;
		}
		//查询账号是否存在
		String userId = appDispatchMapper.findUserIsExist(accountName);
		if(userId == null || "".equals(userId)){
			map.put("state", "false");
			map.put("message", "用户不存在");
		}else{
			//重置密码
			appDispatchMapper.updatePassword(userId,Md5Tool.getMd5(password1));
			map.put("state", "true");
			map.put("message", "密码重置成功");
		}
		return map;
	}
	
	/**
	 * 获取用户信息
	 */
	@Override
	public Object getUserInfor(String accountName){
		Map<String, Object> map = new HashMap<>();
		if(accountName == null || "".equals(accountName)){
			map.put("state", "false");
			map.put("message","参数错误");
			return map;
		}
		Map<String, Object> user = appDispatchMapper.getUserInforByAccountname(accountName);
		if(user == null){
			map.put("state","false");
			map.put("message", "参数错误");
		}else{
			map.put("state", "true");
			map.put("user", user);
		}
		return map;
	}

	/**
	 * 获取验证码
	 * @throws Exception 
	 */
	@Override
	public Object getCode(String accountName,HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> result = new HashMap<String,Object>();
		String mobile = accountName;
		String ip="";
		if(request.getHeader("x-forwarded-for") == null) { 
			 ip = request.getRemoteAddr(); 
		}else{ 
		     ip = request.getHeader("x-forwarded-for");  
		}
		response.setHeader("Content-Type", "application/x-www-form-urlencoded;charset:UTF-8");
		response.setHeader("Accept", "text/javascript, text/html, application/xml, text/xml, */*");
		
		Random rand = new Random();
		String code = "";
		//生成六位数验证码
		for(int i = 0;i<6;i++){
			code += rand.nextInt(10);
		}
		
		SmsSend ss = new SmsSend();
		PropertiesUtils.findPropertiesKey("whtsufix");
		ss.sendSmsCustomer(mobile, "您的验证码是："+code+"。");
		
		SmsSendlog smsSendlog = new SmsSendlog();
 		smsSendlog.setPhone(mobile);
		smsSendlog.setSuccess("Y");
		smsSendlog.setContent("您的验证码是："+code);
		smsSendlog.setRemoteAddr(ip);
		//将验证码写入日志
		smslogService.addSmsSendLog(smsSendlog);
		
		Smslog smslog = new Smslog();
		smslog.setCode(code);
		smslog.setMobile(mobile);
		smslog.setDeviceId("866288029363359");
		smslog.setMsg("您的验证码是："+code);
		smslog.setCreatetime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		
		smslogService.addSmslog(smslog);
		
		result.put("state","true");
		result.put("code", code);
		result.put("message","验证码发送成功");
		System.out.println("===============发送成功");
		System.out.println(code);
	
		return result;
	}

	/**
	 * 验证验证码
	 */
	@Override
	public Object testCode(String tellPhone, String code) {
		Map<String, Object> map = new HashMap<String,Object>();
		//先验证是否正确，在验证是否超时
		if(accountService.testCode(tellPhone, code)){
			/*map.put("state","true");
			map.put("message","验证码正确");*/
			if(accountService.testCode2(tellPhone, code)){
				map.put("state","true");
				map.put("message","验证码正确");
			}else{
				map.put("state","false");
				map.put("message","验证码已过期！请重新获取");
			}
		} else {
			map.put("state","false");
			map.put("message","验证码错误");
		}
		System.err.println(map);
		return map;
	}

	/**
	 * 修改密码
	 */
	@Override
	public Object updatePassword(String accountName, String oldPassword, String password1, String password2) {
		Map<String, Object> map = new HashMap<>();
		if(accountName == null || "".equals(accountName) || oldPassword == null || "".equals(oldPassword) || password1 == null || "".equals(password1)
				|| password2 == null || "".equals(password2)){
			map.put("state","false");
			map.put("message", "参数错误");
			return map;
		}
		//1、先验证旧密码是否正确
		Map<String, Object> userMap = appDispatchMapper.getUserInforByAccountname(accountName);
		if(userMap == null){
			map.put("state","false");
			map.put("message", "参数错误");
			return map;
		}else{
			if(!Md5Tool.getMd5(oldPassword).equals(userMap.get("password").toString())){
				map.put("state","false");
				map.put("message", "原始密码错误");
				return map;
			}
		}
		//2、在验证新密码是否相同
		if(!password1.equals(password2)){
			map.put("state","false");
			map.put("message", "密码不一致");
			return map;
		}else{
			//3、通过用户id修改用户登录密码
			password1 = Md5Tool.getMd5(password1);
			appDispatchMapper.updatePassword(userMap.get("id").toString(), password1);
			map.put("state","true");
			map.put("message", "密码修改成功");
		}
		return map;
	}

	/**
	 * 订单列表
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:04:48
	 * @param
	 * String accountName 快递员账号或管理员账号（当角色是快递员时使用）（channel_code与accountName不能同时为空）
	 * String channel_code门店渠道编码(当角色为门店时使用)
	 * String role 登录人角色（1代表门店，2代表管理员，3代表快递员）（not null）
	 * String state 订单状态
	 * String searchMsg 订单编号
	 * String startTime 查询开始时间
	 * String endTime 查询结束时间
	 * String pageNo 分页页数 默认为1
	 */
	@Override
	public Object getOrderList(String accountName, String channel_code, String role, String state, String searchMsg,
			String startTime, String endTime, String pageNo) {
		Map<String,Object> parame = new HashMap<>();
		parame.put("accountName", accountName);
		parame.put("channel_code", channel_code);
		parame.put("role", role);
		parame.put("state", state);
		parame.put("searchMsg", searchMsg);
		parame.put("startTime", startTime);
		parame.put("endTime", endTime);
		parame.put("pageNo", pageNo);
		if (pageNo == null) {
			pageNo = "1";
		}
		int start = Integer.valueOf(pageNo);
		if(start <= 0) {
			start = 1;
		}
		start = (start - 1)*10;
		parame.put("start", start);
		Map<String, Object> resultMap = new HashMap<>();
		if((accountName == null || "".equals(accountName)) && (channel_code == null || "".equals(channel_code)) || state == null || "".equals(state)){
			resultMap.put("state", "false");
			resultMap.put("message", "参数错误");
			return resultMap;
		}
		
		//当角色为管理员或快递员时，accountName不能为空
		if(("3".equals(role) || "2".equals(role)) && (accountName == null || "".equals(accountName))){
			resultMap.put("state", "false");
			resultMap.put("message", "参数错误");
			return resultMap;
			//role 登录人角色（1代表门店，2代表管理员，3代表快递员）（not null）
		}else if("1".equals(role) && (channel_code == null || "".equals(channel_code))){
			resultMap.put("state", "false");
			resultMap.put("message", "参数错误");
			return resultMap;
		}
		//获取订单总数
		int count = appDispatchMapper.getOrderCount(parame);
		//获取订单列表
		List<Map<String,Object>> list = appDispatchMapper.getOrderList(parame);
		resultMap.put("accountName", accountName);
		resultMap.put("channel_code", channel_code);
		resultMap.put("role", role);
		resultMap.put("state", state);
		resultMap.put("searchMsg", searchMsg);
		resultMap.put("startTime", startTime);
		resultMap.put("endTime", endTime);
		resultMap.put("pageNo", pageNo);
		resultMap.put("count", count);
		resultMap.put("list", list);
		if(count == 0){
			resultMap.put("state","false");
			resultMap.put("message","暂无数据");
		}
		return resultMap;
	}

	/**
	 * 订单详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param id 订单编号
	 * @param channel_code 收货门店渠道编码
	 */
	@Override
	public Object storelistDetails(String id, String channel_code) {
		Map<String, Object> resultType = new HashMap<>();
		if(id == null || "".equals(id) || channel_code == null || "".equals(channel_code)){
			resultType.put("state", "false");
			resultType.put("message", "参数错误");
			return resultType;
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("channel_code", channel_code);
		Map<String, Object> map = appDispatchMapper.storelistDetails(m);

		if (map != null) {
			String[] materialContents = map.get("materialContent").toString().split(",");// 物资名称
			String[] materialNumbers = map.get("materialNumber").toString().split(",");// 物资数量
			for (int i = 0; i < materialContents.length; i++) {
				if (map.get("materialNameAndNumber") != null) {
					map.put("materialNameAndNumber",map.get("materialNameAndNumber").toString() + ","+ materialContents[i] + "("+ materialNumbers[i] + ")");
				} else {
					map.put("materialNameAndNumber", materialContents[i] + "("+ materialNumbers[i] + "件)");
				}
			}
			resultType.put("state", "true");
			resultType.put("map", map);
		}else{
			resultType.put("state", "false");
			resultType.put("message", "参数错误");
			return resultType;
		}
		return resultType;
	}

	/**
	 * 获取物资名称
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 */
	@Override
	public Object getMaterials() {
		Map<String, Object> resultType = new HashMap<>();
		List<Map<String, Object>> list = appDispatchMapper.getMaterials();
		if(list.size()>0){
			resultType.put("state","true");
			resultType.put("list",list);
		}else{
			resultType.put("state","false");
			resultType.put("message", "暂无物资信息");
		}
		return resultType;
	}

	/**
	 * 需求上报
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param channel_code 门店渠道编码
	 * @param materialContent物资名称（以“，”分割）
	 * @param materialNumber 物质数量（以“，”分割)
	 * @param store_shopowner_phone 上报人电话
	 * @param store_shopowner_name 上报人姓名
	 * @param store_name 上报门店
	 * @param expanding_demand 拓展物资(选填)
	 */
	@Override
	public Object addmaterial(String channel_code, String materialContent, String materialNumber,
			String store_shopowner_phone, String store_shopowner_name, String store_name, String expanding_demand,HttpSession session) {
		Map<String,Object> map = new HashMap<>();
		if(channel_code == null || "".equals(channel_code) || materialContent == null || "".equals(materialContent)||
				materialNumber == null || "".equals(materialNumber) || store_shopowner_phone == null || "".equals(store_shopowner_phone) ||
				store_shopowner_name == null || "".equals(store_shopowner_name) || store_name == null || "".equals(store_name)){
			map.put("state", "false");
			map.put("message","参数错误");
			return map;
		}
		if(EncodingUtil.isMessyCode(store_name)){
			store_name = EncodingUtil.strISO8859toUTF8(store_name);
		}
		if(EncodingUtil.isMessyCode(store_shopowner_name)){
			store_shopowner_name = EncodingUtil.strISO8859toUTF8(store_shopowner_name);
		}
		if(EncodingUtil.isMessyCode(materialContent)){
			materialContent = EncodingUtil.strISO8859toUTF8(materialContent);
		}
		APPStoreSupplies appStoreSupplies = new APPStoreSupplies();
		appStoreSupplies.setChannel_code(channel_code);
		appStoreSupplies.setExpanding_demand(expanding_demand);
		appStoreSupplies.setMaterialContent(materialContent);
		appStoreSupplies.setMaterialNumber(materialNumber);
		appStoreSupplies.setStore_shopowner_phone(store_shopowner_phone);
		appStoreSupplies.setStore_shopowner_name(store_shopowner_name);
		appStoreSupplies.setStore_name(store_name);
		
		if (addmaterial(appStoreSupplies, session)) {
			map.put("state", "true");
			map.put("message", "需求上报成功！");
		} else {
			map.put("state","false");
			map.put("message", "需求上报失败！");
		}
		map.put("channel_code",appStoreSupplies.getChannel_code());
		map.put("order_state", "1");
		return map;
	}
	
	
	public boolean addmaterial(APPStoreSupplies appStoreSupplies, HttpSession session) {
		boolean flag = true;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String mytime = dateFormat.format(new Date());
				appStoreSupplies.setReport_time(dateFormat.parse(mytime));
				List<Map<String, Object>> storeMaterials = (List<Map<String, Object>>) session.getAttribute("storeMaterial");
				if(storeMaterials != null){
					for (int i = 0; i < storeMaterials.size(); i++) {
						Map<String, Object> storeMaterial = storeMaterials.get(i);
						if(appStoreSupplies.getMaterialContent() != null && appStoreSupplies.getMaterialContent() != ""){
							if(appStoreSupplies.getMaterialContent().indexOf(storeMaterial.get("materialName").toString()) <= -1){//表示不存在
								appStoreSupplies.setMaterialContent(appStoreSupplies.getMaterialContent() + "," + storeMaterial.get("materialName").toString());
								appStoreSupplies.setMaterialNumber(appStoreSupplies.getMaterialNumber() + "," + storeMaterial.get("num").toString());
							}
						} else {
							appStoreSupplies.setMaterialContent(storeMaterial.get("materialName").toString());
							appStoreSupplies.setMaterialNumber(storeMaterial.get("num").toString());
						}
						
					}
					//需求上报数据添加
					System.err.println(appStoreSupplies.getStore_name());
					appDispatchMapper.addmaterial(appStoreSupplies);
					session.removeAttribute("storeMaterial");
				}
			} catch (ParseException e) {
				System.out.println("日期转换异常");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("添加需求上报信息失败");
				flag = false;
				e.printStackTrace();
			}
		return flag;
	}

	/**
	 * 处理物资拼接问题 
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param materialId 物资id
	 * @param materialName 物资名称
	 * @param num 数量
	 * @param operation 操作(新增或删除)
	 * */
	@Override
	public Object appendOrRemove(String materialId,String role,String materialName, String num, String operation,
			HttpSession session) {
		Map<String, Object> resultmap = new HashMap<>();
		if(materialId == null || "".equals(materialId) || materialName == null || "".equals(materialName)||
				num == null || "".equals(num) || operation == null || "".equals(operation)){
			resultmap.put("state", "false");
			resultmap.put("message","参数错误");
			return resultmap;
		}
		if(!"1".equals(role)){
			resultmap.put("state", "false");
			resultmap.put("message", "参数错误!");
			return resultmap;
		}
		if(!"append".equals(operation) && !"remove".equals(operation)){
			resultmap.put("state", "false");
			resultmap.put("message","参数错误");
			return resultmap;
		}
		if(EncodingUtil.isMessyCode(materialName)){
			materialName = EncodingUtil.strISO8859toUTF8(materialName);
		}
		
		List<Map<String, Object>> mList = (List<Map<String, Object>>) session.getAttribute("storeMaterial");
		if ("append".equals(operation)) {// 拼接操作
			int a = 0;
			if (mList != null && mList.size() > 0) {
				for (int i = 0; i < mList.size(); i++) {
					Map<String, Object> m = mList.get(i);
					if (materialId.equals(m.get("materialId").toString())) {
						m.put("num", Integer.parseInt(m.get("num").toString())
								+ Integer.parseInt(num));
						a++;
					}
				}
				if (a == 0) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("num", num);
					map.put("materialId", materialId);
					map.put("materialName", materialName);
					mList.add(map);
				}
			} else {
				mList = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("num", num);
				map.put("materialId", materialId);
				map.put("materialName", materialName);
				mList.add(map);
			}
			session.setAttribute("storeMaterial", mList);
		} else if ("remove".equals(operation)) {// 删除操作
			for (int i = 0; i < mList.size(); i++) {
				Map<String, Object> map = mList.get(i);
				if (materialId.equals(map.get("materialId").toString())) {
					mList.remove(i);
				}
			}
		}
		resultmap.put("mList", mList);
		resultmap.put("state","true");
		return resultmap;
	}

	/**
	 * 验证有没有添加物资
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param role 登录人角色(1门店，2快递员，3营销中心)
	 **/
	@Override
	public Object checkMaterial(HttpSession session,String role) {
		Map<String, Object> m = new HashMap<String, Object>();
		if(role == null || "".equals(role)){
			m.put("state", "false");
			m.put("message", "参数错误!");
			return m;
		}
		if(!"1".equals(role) && !"3".equals(role)){
			m.put("state", "false");
			m.put("message", "参数错误!");
			return m;
		}
		List<Map<String, Object>> list = null;
		if("1".equals(role)){
			list = (List<Map<String, Object>>) session.getAttribute("storeMaterial");//门店需求上报时
		}else if("3".equals(role)){
			list = (List<Map<String, Object>>) session.getAttribute("myMaterial");//营销中心物资派送时
		}
		
		if (list != null && list.size()>0) {
			m.put("count", list.size());
			m.put("state","true");
		} else {
			m.put("count", 0);
			m.put("state", "false");
			m.put("message", "未添加物资");
		}
		return m;
	}

	/**
	 * 需求上报列表查询
	 */
	@Override
	public Object suppliesMaterialList(String channel_code, String pageNo, String order_state, String startTime,
			String endTime) {
		Map<String,Object> resultMap = new HashMap<>();
		if (order_state == null || "0".equals(order_state)) {
			order_state = null;
		}
		if (channel_code == null || "".equals(channel_code)) {
			resultMap .put("state", "false");
			resultMap .put("message", "参数错误");
			return resultMap;
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("state", order_state);
		m.put("channel_code", channel_code);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		if(pageNo == null || "".equals(pageNo)) {
			pageNo = "1";
		}
		int start = Integer.parseInt(pageNo);
		if(start <= 0) {
			start = 1;
		}
		start = (start - 1)*10;
		m.put("start", start);
		//查询需求上报列表的数据总数
		Integer count = appDispatchMapper.suppliesMaterialCount(m);
		//分页获取需求列表数据
		List<Map<String, Object>> map = appDispatchMapper.suppliesMaterialList(m);
		resultMap.put("order_state", order_state);
		resultMap.put("map", map);
		resultMap.put("startTime", startTime);
		resultMap.put("endTime", endTime);
		resultMap.put("pageNo", pageNo);
		resultMap.put("count", count);
		resultMap.put("state", "true");
		if (count == 0) {
			resultMap .put("state", "false");
			resultMap .put("message", "暂无上报信息");
		}
		return resultMap;
	}

	/**
	 * 需求上报统计
	 */
	@Override
	public Object toStoreTotal(String channel_code, String startTime, String endTime) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (channel_code == null || "".equals(channel_code)) {
			resultMap .put("state", "false");
			resultMap .put("message", "参数错误");
			return resultMap;
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("channel_code", channel_code);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		List<Map<String, Object>> map = appDispatchMapper.findtotal(m);
		resultMap.put("map", map);
		resultMap.put("state", "true");
		return resultMap;
	}

	/**
	 * 读取政策 列表
	 */
	@Override
	public Object getPolicyList(String groupId, String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (groupId == null || "".equals(groupId) || userId == null || "".equals(userId)) {
			resultMap .put("state", "false");
			resultMap .put("message", "参数错误!");
			return resultMap;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("groupId", groupId);
		map.put("userId", userId);
		List<ActivityInfo> activity = this.appDispatchMapper.getPolicyList(map);
		if(activity != null && activity.size() > 0){
			resultMap .put("state", "true");
			resultMap .put("activityList", activity);
		}else{
			resultMap .put("state", "false");
			resultMap .put("message", "暂无政策");
		}
		return resultMap;
	}

	/**
	 * 政策搜索
	 */
	@Override
	public Object policySearch(String keyword, String groupId, String userId) {
		
		Map<String, Object> resultMap = new HashMap<>();
		if(groupId == null || "".equals(groupId) || userId == null || "".equals(userId) || keyword == null || "".equals(keyword)){
			resultMap .put("state", "false");
			resultMap .put("message", "参数错误!");
			return resultMap;
		}
	
		if(EncodingUtil.isMessyCode(keyword)){
			keyword = EncodingUtil.strISO8859toUTF8(keyword);
		}
		
		String g=activityService.getGroups(groupId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("groupid", g);
		map.put("userid", userId);

		List<Map<String,Object>> activityList = this.activityService.search(map);
		if(activityList.size()!=0){
			for (int i = 0; i < activityList.size(); i++) {
				String start_time=new SimpleDateFormat("yyyy-MM-dd").format(activityList.get(i).get("start_time"));
				String end_time=new SimpleDateFormat("yyyy-MM-dd").format(activityList.get(i).get("end_time"));
				activityList.get(i).put("start_time",start_time);
				activityList.get(i).put("end_time", end_time);
			}
		}
		if(activityList!=null && activityList.size() > 0){
			resultMap.put("state", "true");
			resultMap.put("activityList", activityList);
		}else{
			resultMap.put("state", "false");
			resultMap .put("message", "暂无相关活动信息!");
		}
		return resultMap;
	}

	/**
	 * 政策详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param groupId 
	 * @param userId 
	 */
	@Override
	public Object policyDetail(String id, String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		if(userId == null || "".equals(userId) || id == null || "".equals(id)){
			resultMap .put("state", "false");
			resultMap .put("message", "参数错误!");
			return resultMap;
		}
		String userid = userId;
		ActivityInfo activity = this.activityService.getById(id);
		if(activity != null){
			/**
			 * 记录阅读
			 */
			if(userid != null){
				ActivityRead r = this.activityService.getReadByUserid(userid,id);
				if(r == null){
					ActivityRead read = new ActivityRead();
					read.setId(UUID.randomUUID().toString());
					read.setRead_time(Common.formatDate(new Date()));
					read.setRead_user(userid);
					read.setPolicy_id(id);
					this.activityService.updateRecord(read);
				}
			}
			resultMap.put("activity", activity);
			resultMap.put("state", "true");
		}else{
			resultMap.put("message", "暂无政策信息");
			resultMap.put("state", "false");
		}
		return resultMap;
	}

	/**
	 * 快递员历史订单统计
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param accountName 快递员账号
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	@Override
	public Object historyListCount(String accountName, String startTime, String endTime) {
		Map<String, Object> resultMap = new HashMap<>();
		if(accountName == null || "".equals(accountName)){
			resultMap .put("state", "false");
			resultMap .put("message", "参数错误!");
			return resultMap;
		}
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("accountName", accountName);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		//查询快递员历史订单统计
	    List<Map<String, Object>> map=appDispatchMapper.historyListCount(m);
	    
	    resultMap .put("map", map);
	    resultMap .put("accountName", accountName);
	    resultMap .put("startTime", startTime);
	    resultMap .put("endTime", endTime);
	    return resultMap;
	}

	/**
	 * 门店配送统计（营销中心）
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	@Override
	public Object storeListCount(String startTime, String endTime) {
		Map<String, Object> resultMap = new HashMap<>();
		
		//获取到报表所需要的数据
		List<Map<String,Object>> ls =  getReportDatas(startTime,endTime);
	    //当ls不为空的时候将数据传到页面
		//if(ls != null /*&& "getReportDatas".equals(ls)*/){
		if(ls != null){
			List<Map<String,Object>> ls1 = (List<Map<String, Object>>) ls.get(0).get("materialAndNumLs");
			resultMap.put("ls", ls);
			resultMap.put("size", ls1.size());
			resultMap.put("state", "true");
		} else {
			resultMap.put("state", "false");
			resultMap.put("message", "暂无数据");
		}
		resultMap.put("startTime", startTime);
		resultMap.put("endTime", endTime);
		return resultMap;
	}
	
	/**
	 * 获取报表数据
	 */
	public List<Map<String, Object>> getReportDatas(String startTime, String endTime) {
		//如果时间为空，给时间默认赋值查询最近一周的信息
		if(startTime == null){
			endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() + (1*24*60*60*1000) );
			startTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() - (7*24*60*60*1000));
		} else {
			try {
				endTime = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(endTime).getTime() + (1*24*60*60*1000) );
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//用于存放最终需要插入临时表数据的list
		List<Map<String, Object>> ls3 = new ArrayList<Map<String, Object>>();
		//生成随机字符串，用来标识本次查询的唯一性
		String createUserStr = UUID.randomUUID().toString();
		//创建存放传参map
		Map<String,Object> m = new HashMap<String,Object>();
		//map中放入开始结束时间
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		//根据时间筛选出渠道信息
		List<Map<String,Object>> ls = appFillingSingleMapper.getChannelCode(m);
		//循环遍历根据当前时间查询出来的渠道信息
		for(int i = 0; i < ls.size(); i++){
			String materialContents = new String();
			String materialNumbers = new String();
			//循环遍历当前时间段查询出来的渠道编码信息
			//根据渠道编码查询出渠道的物资信息
			List<Map<String,Object>> ls2 = appFillingSingleMapper.getMaterials(startTime,endTime,(String)ls.get(i).get("channel_code"));
			//循环遍历门店的信息物资/数量信息
			for(int j = 0; j < ls2.size(); j++){
				//循环遍历单次配送的物资信息
				String singleMaterialContent =  (String)ls2.get(j).get("materialContent");
				String singleMaterialNumber =  (String)ls2.get(j).get("materialNumber");
				
				if(",".equals(singleMaterialContent.substring(singleMaterialContent.length()-1, singleMaterialContent.length()))){
					materialContents += "," + singleMaterialContent.substring(0, singleMaterialContent.length() -1);
					materialNumbers += "," + singleMaterialNumber.substring(0, singleMaterialNumber.length() -1);
				} else {
					materialContents += "," + singleMaterialContent;
					materialNumbers += "," + singleMaterialNumber;
				}
				 
			}
				
			 String[] arrayMaterialContents = materialContents.split(",");
			 String[] arrayMaterialNumbers = materialNumbers.split(",");
			 for (int j = 0; j < arrayMaterialContents.length; j++) {
				if(StringUtils.isNotEmpty(arrayMaterialContents[j])){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("channel_code", ls.get(i).get("channel_code").toString());
					map.put("store", ls.get(i).get("store").toString());
					map.put("state", ls.get(i).get("state").toString());
					map.put("materialName", arrayMaterialContents[j]);
					map.put("materialNumber", arrayMaterialNumbers[j]);
					//查询人随机字符串，每次查询创建一个唯一的随机字符串，防止查询数据出现冲突
					map.put("createUserStr",createUserStr);
					ls3.add(map);
				}
			}
			}
			if(ls3.size() > 0){
				//将获取到的处理后ls3插入到临时表中
			    if(appFillingSingleMapper.insertTemp(ls3) > 0){
			    	//当插入成功后，根据查询随机字符串查询出本次查询的门店信息
			    	List<Map<String,Object>> reportStoreLs = appFillingSingleMapper.getStoreInfo(createUserStr);
			    	//遍历查询出来的报表信息
			    	for(int i = 0; i < reportStoreLs.size(); i++){
			    		//获取门店的渠道信息
			    		String channel_code = (String)reportStoreLs.get(i).get("channel_code");
			    		//根据渠道信息查询出来派送次数信息
			    		Map<String,Object> orderNum = appFillingSingleMapper.getSendNum(channel_code);
			    		reportStoreLs.get(i).put("doneOrder", orderNum.get("doneOrder")); 
			    		reportStoreLs.get(i).put("OrderAll", orderNum.get("OrderAll"));
			    		//根据渠道信息和查询随机字符串信息查询出物资以及数量信息
			    		List<Map<String,Object>> materialAndNumLs = appFillingSingleMapper.getMaterialNum(channel_code,createUserStr);
			    		reportStoreLs.get(i).put("materialAndNumLs",materialAndNumLs);
			    		
			    	}
			    	//清除临时表数据
			    	appFillingSingleMapper.deleteTemp(createUserStr);
			    	return reportStoreLs;
			    }
				
			}
		return ls3;
	}

	/**
	 * 获取所有品牌
	 * @author zhangzhi
	 * @date 2018年5月3日下午3:59:09
	 * @param
	 */
	@Override
	public Object getAllBrand() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> vendorsList = appDispatchMapper.getVendor();//加载品牌信息
		if(vendorsList != null && vendorsList.size()>0){
			map.put("vendorsList", vendorsList);
			map.put("state", "true");
		}else{
			map.put("state", "false");
			map.put("message", "暂无品牌信息!");
		}
		return map;
	}

	/**
	 * 根据品牌id、门店类型、模糊字段获取所有门店
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param vendor_id 品牌id
	 * @param type 门店类型
	 */
	@Override
	public Object getAllStore(String vendor_id, String type,String mohuchaxun) {
		if(mohuchaxun != null && !"".equals(mohuchaxun)){
			if(EncodingUtil.isMessyCode(mohuchaxun)){
				mohuchaxun = EncodingUtil.strISO8859toUTF8(mohuchaxun);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		List<Store> storeList = appDispatchMapper.getAllStore(vendor_id, type,mohuchaxun);
		if(storeList != null && storeList.size()>0){
			map.put("storeList", storeList);
			map.put("state", "true");
		}else{
			map.put("state", "false");
			map.put("message", "暂无门店信息!");
		}
		return map;
	}

	/**
	 * 处理物资拼接问题(物资派送时)
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param num 物资数量
	 * @param material 物资id
	 * @param materialName 物资名称
	 * @param store 门店信息
	 * @param predictTime 预计送达时长
	 * @param operation 具体操作（增加或删除）（append、remove）
	 */
	@Override
	public Object appendOrRemoveForPaidan(String num, String materialId,String role, String materialName, String store,
			String operation, String predictTime, HttpSession session) {
		String material = materialId;
		Map<String, Object> resultMap = new HashMap<>();
		if(material == null || "".equals(material) || materialName == null || "".equals(materialName)||
				num == null || "".equals(num) || operation == null || "".equals(operation) || store == null || "".equals(store)||
				predictTime == null || "".equals(predictTime)){
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		}
		if(!"3".equals(role)){
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		}
		if(!"append".equals(operation) && !"remove".equals(operation)){
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		}
		if(EncodingUtil.isMessyCode(materialName)){
			materialName = EncodingUtil.strISO8859toUTF8(materialName);
		}
		List<Map<String, Object>> mList = (List<Map<String, Object>>) session.getAttribute("myMaterial");//物资信息
		if("append".equals(operation)){//拼接操作
			int a = 0;//判断循环中的判断是否成立
			if(mList != null && mList.size() > 0){//有物资
				for (int i = 0; i < mList.size(); i++) {
					Map<String, Object> m = mList.get(i);
					if(material.equals(m.get("material").toString())){//如果该次添加的物资已经存在于购物车则只添加数量
						m.put("num", Integer.parseInt(m.get("num").toString()) + Integer.parseInt(num));
						a++;
					}
				}
				if(a == 0){//表示购物车中没有该物资，添加新的
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("num", num);
					map.put("material", material);
					map.put("materialName", materialName);
					map.put("store", store);
					if(Integer.valueOf(predictTime) == 0){//如果预计时长没填则默认2~3小时
						map.put("predictTime", "2~3");
					} else {
						map.put("predictTime", predictTime);
					}
					mList.add(map);
				}
			} else {
				mList = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("num", num);
				map.put("material", material);
				map.put("materialName", materialName);
				map.put("store", store);
				if(Integer.valueOf(predictTime) == 0){//如果预计时长没填则默认2~3小时
					map.put("predictTime", "2~3");
				} else {
					map.put("predictTime", predictTime);
				}
				mList.add(map);
			}
			session.setAttribute("myMaterial", mList);
			List<Map<String, Object>> list = (List<Map<String, Object>>) session.getAttribute("myMaterial");//物资信息
			System.err.println(list);
		} else if("remove".equals(operation)){//删除操作
			for(int i = 0; i < mList.size(); i++){
				Map<String, Object> map = mList.get(i);
				if(material.equals(map.get("material").toString())){//根据物资id删除购物车中的物资
					mList.remove(i);
				}
			}
		}
		resultMap.put("mList", mList);
		resultMap.put("state","true");
		return resultMap;
	}


	/**
	 * 查询所有的快递公司
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 */
	@Override
	public Object getAllDeliveryCompany() {
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = appDispatchMapper.getAllDeliveryCompany();
		if(list != null && list.size()>0){
			map.put("state", "true");
			map.put("list", list);
		}else{
			map.put("state", "false");
			map.put("message","暂无快递公司");
		}
		return map;
	}

	/**
	 * 根据快递公司查询快递员信息
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param company_id 快递公司id
	 * @param select_infor 快递员公司或姓名
	 */
	@Override
	public Object getDeliveryByCompany(String company_id,String select_info) {
		String ids = "";
		Map<String, Object> map = new HashMap<>();
		if(company_id == null || "".equals(company_id)){
			map.put("state", "false");
			map.put("message","参数错误");
			return map;
		}
		if(select_info != null || !"".equals(select_info)){
			if(EncodingUtil.isMessyCode(select_info)){
				select_info = EncodingUtil.strISO8859toUTF8(select_info);
			}
		}
		String[] company_ids = company_id.split(",");
		for (int i = 0; i < company_ids.length; i++) {
			if(ids == null || "".equals(ids)){
				ids = "'"+company_ids[i]+"'";
			}else{
				ids = ids +","+"'"+company_ids[i] + "'";
			}
		}
		List<Map<String, Object>> list = appDispatchMapper.getDeliveryByCompany(ids,select_info);
		if(list != null && list.size()>0){
			map.put("state", "true");
			map.put("list", list);
		}else{
			map.put("state", "false");
			map.put("message","暂无快递员信息");
		}
		return map;
	}

	/**
	 * 生成工单
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param deliveryPhones 快递员电话（以“，”隔开）
	 * @param accountName 生成该次订单任务的营销中心人员电话
	 */
	@Override
	public Object addOrder(String deliveryPhones, String accountName,HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		if(deliveryPhones == null || "".equals(deliveryPhones) || accountName == null || "".equals(accountName)){
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		}
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Account account = accountMapper.isExist(accountName);
		List<Map<String, Object>> materialAndStoreInformations = (List<Map<String, Object>>) session.getAttribute("myMaterial");//得到派送的物资和门店信息
		System.err.println(materialAndStoreInformations);
		if(materialAndStoreInformations == null || materialAndStoreInformations.size() <= 0){
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		} else {
			/**
			 * 开始给快递员分配任务(单线)
			 * */
			//处理快递员电话(剔重)
			deliveryPhones = deliveryPhones.substring(0, deliveryPhones.length() - 1);
			String[] courierGroupByFront = deliveryPhones.split(",");
			String courierGroupByBack = "";
			for (int i = 0; i < courierGroupByFront.length; i++) {
				System.out.println(courierGroupByFront[i]);
				if(courierGroupByBack.indexOf(courierGroupByFront[i]) <= -1){//表示不存在
					if(StringUtils.isEmpty(courierGroupByBack)){
						courierGroupByBack = courierGroupByFront[i];
					} else {
						courierGroupByBack = courierGroupByBack + "," +courierGroupByFront[i];
					}
				}
			}
			
			//处理订单数据
			List<Map<String, Object>> orders = new ArrayList<Map<String, Object>>();
			String[] courierGroupByBacks = courierGroupByBack.split(",");
			for (int i = 0; i < materialAndStoreInformations.size(); i++) {//任务量:materialAndStoreInformations.size()
				String[] stores = materialAndStoreInformations.get(i).get("store").toString().split(",");
				if(orders.size() > 0){
					for (int j = 0; j < stores.length; j++) {
						for (int k = 0; k < orders.size(); k++) {
							Map<String, Object> m = orders.get(k);
							if(stores[j].equals(m.get("store").toString())){
								m.put("num", m.get("num").toString() + "," + materialAndStoreInformations.get(i).get("num").toString());//物资数量
								m.put("materialName", m.get("materialName").toString() + "," + materialAndStoreInformations.get(i).get("materialName").toString());//物资名称
							}
						}
					}
				} else {
					for (int j = 0; j < stores.length; j++) {
						Map<String, Object> m = new HashMap<String, Object>();
						m.put("orderNumber", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + Common.getRandom(3, true, false, false, false, 1).get(0));//生成订单编号
						m.put("num", materialAndStoreInformations.get(i).get("num").toString());//物资数量
						m.put("materialName", materialAndStoreInformations.get(i).get("materialName").toString());//物资名称
						m.put("store", stores[j]);//门店编码
						m.put("predictTime", materialAndStoreInformations.get(i).get("predictTime").toString());//预计时长
						orders.add(m);
					}
				}
			}
			
			//生成订单
			for (int i = 0; i < orders.size(); i++) {
				List<Map<String, Object>> courierTaskCount = appFillingSingleMapper.getCourierTaskCount(courierGroupByBacks);//得到选中快递员们现有快递量
				if(courierTaskCount.size() < 1){
					System.out.println("没选择快递员");
				} else {
					Map<String, Object> m = orders.get(i);
					m.put("courierPhone", courierTaskCount.get(0).get("courierPhone").toString());//快递员电话
					m.put("courierName", courierTaskCount.get(0).get("real_name").toString());//快递员姓名
					System.err.println(m.get("store").toString());
					Map<String, Object> stores = appFillingSingleMapper.getStoreByNumber(m.get("store").toString());//查询门店信息
					m.put("shipperName", account.getReal_name());//发货人姓名
					m.put("shipperPhone", account.getAccountName());//发货人电话
					m.put("shipTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//发货时间
					m.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//订单生成时间
					m.put("shipperGroupId", account.getGroupId());//发货人组织id
					m.put("store_name", stores.get("store_name").toString());//门店名称
					m.put("store_address", stores.get("store_address").toString());//门店地址
					m.put("consigneeName", stores.get("store_shopowner_name").toString());//收货人
					m.put("consigneePhone", stores.get("store_shopowner_phone").toString()); //收货人电话
					m.put("state", "1");//订单状态(表示已生成订单)
					m.put("brand", stores.get("vendor_name").toString());//门店归属品牌
					m.put("store_longitude", stores.get("store_longitude").toString());//门店经度
					m.put("store_latitude", stores.get("store_latitude").toString());//门店纬度
					if(result.size() > 0){
						for (int j = 0; j < result.size(); j++) {
							if(result.get(j).get("expressPhone").toString().equals(m.get("courierPhone").toString())){
								if(result.get(j).get("number") != null){
									result.get(j).put("number", result.get(j).get("number").toString() + "," + m.get("orderNumber").toString());
								} else {
									result.get(j).put("number", m.get("orderNumber").toString());
								}
							} else {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("expressPhone", m.get("courierPhone").toString());
								map.put("number", m.get("orderNumber").toString());
								result.add(map);
							}
						}
					} else {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("expressPhone", m.get("courierPhone").toString());
						map.put("number", m.get("orderNumber").toString());
						result.add(map);
					}
				}
			}
			
			//插入记录
			Integer insertResult = appFillingSingleMapper.putInMaterialInfo(orders);//插入记录
			if(insertResult > 0){
				System.out.println("订单插入成功");
				resultMap.put("state", "true");
				resultMap.put("message","订单插入成功");
			} else {
				System.out.println("订单插入失败");
				resultMap.put("state", "false");
				resultMap.put("message","订单插入失败");
			}
			session.removeAttribute("myMaterial");//释放session
			//给快递员发短息提醒
			SmsSend send = new SmsSend();
			for (int j = 0; j < result.size(); j++) {
				Map<String, Object> map = result.get(j);
				String[] size = map.get("number").toString().split(",");
				String msg = "您有  "+size.length+"条新订单,订单编码分别是:" + "\n" + "【"+map.get("number").toString()+"】";
				System.out.println(msg);
				boolean flag = send.sendSmsCustomer(map.get("expressPhone").toString(), msg);
				if (flag) {
					System.out.println("验证码发送成功");
				} else {
					System.out.println("验证码发送失败");
				}
			}
		}
		return resultMap;
	}

	/**
	 * 订单派送
	 * 点击“查看派送地图”时，将订单装维改为4，即订单此时处于派送中
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 */
	@Override
	public Object updateStateWhenDelivery(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(id == null || "".equals(id)){
			map.put("state", "false");
			map.put("message","参数错误");
			return map;
		}
		if(appDispatchMapper.updateStateWhenDelivery(id) == 1){
			map.put("state", "true");
		} else {
			map.put("state", "false");
		}
		return map;
	}
	
	/**
	 * 点击“查看派送地图”时，根据快递员变化和订单编号修改快递员经纬度
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 * @param courier_longitude 快递员经度
	 * @param courier_latitude 快递员纬度
	 * @param courier_Phone 快递员电话
	 */
	@Override
	public Object updateCourierstore(String courier_longitude, String courier_latitude, String courier_Phone,
			String logistics) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(courier_longitude == null || "".equals(courier_longitude) || courier_latitude == null || "".equals(courier_latitude)
				|| courier_Phone == null || "".equals(courier_Phone) || logistics == null || "".equals(logistics)){
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("courier_longitude",	courier_longitude);
		m.put("courier_latitude",	courier_latitude);
		m.put("actualTime",	new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		m.put("courier_Phone",	courier_Phone);
		m.put("logistics",	logistics);
		if(appDispatchMapper.updateCourierstore(m)>0){
			resultMap.put("state", "true");
		} else {
			resultMap.put("state", "false");
		}
		return resultMap;
	}

	
	/**
	 * 点击“开始配送时”时，添加快递员经纬度，实时获取快递员经纬度信息
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param logistics 订单id
	 * @param courier_longitude 快递员经度
	 * @param courier_latitude 快递员纬度
	 * @param courier_Phone 快递员电话
	 */
	@Override
	public Object addCourierstore(String courier_longitude, String courier_latitude, String courier_Phone,String logistics) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(courier_longitude == null || "".equals(courier_longitude) || courier_latitude == null || "".equals(courier_latitude)||
				courier_Phone == null || "".equals(courier_Phone) || logistics == null || "".equals(logistics)){
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		}
		APPCourierStore aPPCourierStore = new APPCourierStore();
		aPPCourierStore.setActualTime(new Date());
		aPPCourierStore.setCourier_latitude(Double.parseDouble(courier_latitude));
		aPPCourierStore.setCourier_longitude(Double.parseDouble(courier_longitude));
		aPPCourierStore.setCourier_Phone(courier_Phone);
		aPPCourierStore.setLogistics(logistics);
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", logistics);
		m.put("recipientTime",new Date());
		//添加快递员坐标
		int num1 = appDispatchMapper.addCouriersLocation(aPPCourierStore);
		//修改订单的邮寄预计时长
		int num2 = appDispatchMapper.updatePredictTime(m);
		if(num1>0 && num2>0){
			resultMap.put("state", "true");
		} else {
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
		}
		return resultMap;
	}
	
	/**
	 * 返回页面门店的经纬度
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param store_longitude 门店经度
	 * @param store_latitude 门店纬度
	 * @param address 具体地址
	 * @param dizhi 订单id
	 * @param logistics 订单编码
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public Object detailedRoute(String store_longitude, String store_latitude, String address,String logistics) throws UnsupportedEncodingException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(store_longitude == null || "".equals(store_latitude) || store_longitude == null || "".equals(store_latitude)||
				logistics == null || "".equals(logistics)){
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		}
		//通过订单id查询订单信息
		Map<String,	Object> maplist  =appDispatchMapper.findOrderById(logistics);
		if(address != null && address != ""){
			address = new String(address.getBytes("ISO-8859-1"), "UTF-8");
			resultMap.put("address", address);
		}
		resultMap.put("store_longitude", store_longitude);
		resultMap.put("store_latitude", store_latitude);
		resultMap.put("maplist", maplist);
		resultMap.put("state", "true");
		return resultMap;
	}

	/**
	 * 完成订单，修改订单中状态
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 * @param signPeople 签收人
	 * @param signPhone 签收人电话
	 * @param isReplace 是否代收(0代表否，1代表是)
	 * @param serviceTime 快递员送达时间
	 * @param recipientTime 快递员收件时间
	 * @param factTime 实际邮寄时长
	 * @throws UnsupportedEncodingException 
	 */
	@Override
	public Object updateOrder(String id,String signPeople,String signPhone,String recipientTime) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		if(id == null || "".equals(String.valueOf(id)) || signPeople == null || "".equals(String.valueOf(signPeople))||
				signPhone == null || "".equals(signPhone)|| recipientTime == null || "".equals(recipientTime)){
			map.put("state", "false");
			map.put("message","参数错误");
			return map;
		}
		
		if(signPeople != null && signPeople != ""){
			signPeople = new String(signPeople.getBytes("ISO-8859-1"), "UTF-8");
		}
	    String factTime = "";
	    String re = recipientTime.split(" ")[1].replace("-", ":");
		recipientTime = recipientTime.split(" ")[0]+" "+re;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
			   String str=df.format(new Date());  
			   Date strnow=df.parse(str);   
			   Date date=df.parse(recipientTime);   
			   long l=strnow.getTime()-date.getTime();   
			   long day=l/(24*60*60*1000);   
			   long hour=(l/(60*60*1000)-day*24);   
			   long min=((l/(60*1000))-day*24*60-hour*60);   
			   long s=(l/1000-day*24*60*60-hour*60*60-min*60);   
			   if(day>0){
				   factTime=day+"天"+hour+"小时"+min+"分"+s+"秒";
			   }else if(day==0&&hour>0){
				   factTime=hour+"小时"+min+"分"+s+"秒"; 
			   }else if(hour==0&&min>0){
				   factTime=min+"分"+s+"秒"; 
			   }else if(min==0){
				   factTime=s+"秒"; 
			   }
			   System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
			   
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("signPeople", signPeople);
		m.put("signPhone", signPhone);
		m.put("leadTime", new Date());
		m.put("isReplace", "0");
		m.put("serviceTime", new Date());
		m.put("factTime", factTime);
		//修改订单状态
		int num = appDispatchMapper.updateOrder(m);
		if(num >0){
			map.put("state", "true");
		} else {
			map.put("state", "false");
		}
		return map;
	}

	/**
	 * 完成订单后，当订单中的门店位置获取失败时（坐标为0或为空），重新采集门店的经纬度坐标分别对订单表的门店信息表进行更新
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 * @param store_longitude 门店经度
	 * @param store_latitude 门店纬度
	 * @param channel_code 门店编码
	 */
	@Override
	public Object updateStoreLocation(String id, String store_longitude, String store_latitude, String channel_code) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(id == null || "".equals(String.valueOf(id)) || store_longitude == null || "".equals(String.valueOf(store_longitude))||
				"0".equals(String.valueOf(store_longitude))||store_latitude == null || "0".equals(String.valueOf(store_latitude))||
				"".equals(store_latitude)|| channel_code == null || "".equals(channel_code)){
			map.put("state", "false");
			map.put("message","参数错误");
			return map;
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("store_longitude", store_longitude);
		m.put("store_latitude", store_latitude);
		m.put("channel_code", channel_code);
		int num1 = appDispatchMapper.updateStoreLocationInMail(m);//根工单id修改工单表中的门店经纬度信息
		int num2 = appDispatchMapper.updateStoreLocationInStore(m);//根据门店编码修改门店信息表中的经纬度信息
		
		if(num1>0 && num2 >0){
			map.put("state", "true");
		} else {
			map.put("state", "false");
		}
		return map;
	}

	/**
	 * 拍照送达（订单照片和门店门头）
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param orderImg 订单图片
	 * @param storeImg 门店图片
	 * @param logistics 订单编码
	 * @param signPeople 签收人
	 * @param signPhone 签收人电话
	 * @param recipientTime 邮寄预计时长
	 */
	@Override
	public Object uploadImg(String orderImg, String storeImg, String logistics,
			String signPeople, String signPhone, String recipientTime) {
		Map<String, Object>map=new HashMap<String, Object>();
		if(orderImg == null || storeImg == null || signPeople == null || "".equals(logistics)|| logistics == null || "".equals(signPeople) || 
				signPhone == null || "".equals(signPhone) || recipientTime == null || "".equals(recipientTime)){
			map.put("state", "false");
			map.put("message","参数错误");
			return map;
		}
		String factTime = "";
		String re = recipientTime.split(" ")[1].replace("-", ":");
		recipientTime = recipientTime.split(" ")[0]+" "+re;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
			   String str=df.format(new Date());  
			   Date strnow=df.parse(str);   
			   Date date=df.parse(recipientTime);   
			   long l=strnow.getTime()-date.getTime();   
			   long day=l/(24*60*60*1000);   
			   long hour=(l/(60*60*1000)-day*24);   
			   long min=((l/(60*1000))-day*24*60-hour*60);   
			   long s=(l/1000-day*24*60*60-hour*60*60-min*60);   
			   if(day>0){
				   factTime=day+"天"+hour+"小时"+min+"分"+s+"秒";
			   }else if(day==0&&hour>0){
				   factTime=hour+"小时"+min+"分"+s+"秒"; 
			   }else if(hour==0&&min>0){
				   factTime=min+"分"+s+"秒"; 
			   }else if(min==0){
				   factTime=s+"秒"; 
			   }
			   System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("logistics", logistics);
		m.put("signPeople", signPeople);
		m.put("signPhone", signPhone);
		m.put("leadTime", new Date());
		m.put("isReplace", "0");
		m.put("serviceTime", new Date());
		m.put("factTime", factTime);
		m.put("orderImg", orderImg);
		m.put("storeImg", storeImg);
		
		boolean flag = appDispatchMapper.addImgForOrder(m)>0?true:false;//将门头照片和商品照片维护到京单中
		//将图片信息维护入订单中
		 if(flag){
			 map.put("state","true");
		 } else {
			 map.put("state","false");
		 }
		return map;
	}

	
	/**
	 * 获取文件后缀名
	 * @author zhangzhi
	 * @date 2018年5月4日下午2:24:40
	 * @param
	 */
	public String getSuffix(MultipartFile file){
		//获取文件的名称
		String fileName =  file.getOriginalFilename();
		String Suffix = "";
		if(!"".equals(fileName)){
			//获取文件的后缀	
			Suffix  = fileName.substring(fileName.lastIndexOf("."));
		}
		return Suffix;
		
	}

	/**
	 * 需求上报详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param channel_code 门店编码
	 * @param id 需求上报id
	 * @param endTime 查询结束时间
	 */
	@Override
	public Object toStoreSuppliesDetails(String channel_code, String id) {
		Map<String, Object> resultMap = new HashMap<>();
		if(channel_code == null || "".equals(channel_code) || id == null || "".equals(id)){
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("channel_code", channel_code);
		Map<String, Object> map = appDispatchMapper.toStoreSuppliesDetails(m);
		
		if (map != null) {
			String[] materialContents = map.get("materialContent").toString()
					.split(",");// 物资名称
			String[] materialNumbers = map.get("materialNumber").toString()
					.split(",");// 物资数量
			for (int i = 0; i < materialContents.length; i++) {
				if (map.get("materialNameAndNumber") != null) {
					map.put("materialNameAndNumber",
							map.get("materialNameAndNumber").toString() + ","
									+ materialContents[i] + "("
									+ materialNumbers[i] + ")");
				} else {
					map.put("materialNameAndNumber", materialContents[i] + "("
							+ materialNumbers[i] + "件)");
				}
			}
		}else{
			resultMap.put("state", "false");
			resultMap.put("message","参数错误");
			return resultMap;
		}
		resultMap.put("state", "true");
		resultMap.put("map", map);
		return resultMap;
	}

	/**
	 * 上传图片到服务器并返回文件路径
	 * @author zhangzhi
	 * @date 2018年5月11日下午5:11:07
	 * @param
	 */
	@Override
	public Object uploadImageForSingle(MultipartFile file) {
		
		Map<String, Object> map = new HashMap<>();
		if (file.isEmpty()) {
			 map.put("state","false");
			 map.put("message", "参数错误!");
		}
		
		//获取文件的后缀名
		String orderImgSuffix = this.getSuffix(file);
		//获取文件流
		InputStream imgInputStream = null;
		try {
			imgInputStream = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//生成文件的名字
		String imgName = UUID.randomUUID().toString() + orderImgSuffix;
		
		//将图片信息上传到fpt服务器
		FTPLinuxUtils ftpLinux = new FTPLinuxUtils();
		
		boolean orderImgState =  ftpLinux.uploadFile("112.74.88.25", 21, "mkl_ftp", "makalu_ftp", imgName, imgInputStream, "logistics/upload/");;
		String ftpPath ="http://112.74.88.25/logistics/upload/"+ imgName;
		
		//如果文件上传成功
		if(orderImgState){
			map.put("ftpPath", ftpPath);
			map.put("state", "true");
		}else{
			map.put("state","false");
			map.put("message", "文件上传失败");
		}
		return map;
	}
	
	public static void main(String[] args) throws ParseException {
		String factTime= "";
		String recipientTime = "2018-06-15 10-35-36";
		String re = recipientTime.split(" ")[1].replace("-", ":");
		recipientTime = recipientTime.split(" ")[0]+" "+re;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		   String str=df.format(new Date());  
		   Date strnow=df.parse(str);   
		   Date date=df.parse(recipientTime);   
		   long l=strnow.getTime()-date.getTime();   
		   long day=l/(24*60*60*1000);   
		   long hour=(l/(60*60*1000)-day*24);   
		   long min=((l/(60*1000))-day*24*60-hour*60);   
		   long s=(l/1000-day*24*60*60-hour*60*60-min*60);   
		   if(day>0){
			   factTime=day+"天"+hour+"小时"+min+"分"+s+"秒";
		   }else if(day==0&&hour>0){
			   factTime=hour+"小时"+min+"分"+s+"秒"; 
		   }else if(hour==0&&min>0){
			   factTime=min+"分"+s+"秒"; 
		   }else if(min==0){
			   factTime=s+"秒"; 
		   }
		   System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
	}
	
}
