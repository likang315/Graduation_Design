 package com.ly.service.appimpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ly.entity.SmsSend;
import com.ly.entity.app.MailInformation;
import com.ly.entity.app.Store;
import com.ly.entity.app.Vendor;
import com.ly.entity.background.Account;
import com.ly.mapper.AccountMapper;
import com.ly.mapper.AppFillingSingleMapper;
import com.ly.service.AppFillingSingleService;
import com.ly.util.Common;


@Service("appFillingSingleService")
public class AppFillingSingleServiceImpl implements AppFillingSingleService {

	@Autowired
	private AppFillingSingleMapper appFillingSingleMapper;
	
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public List<Vendor> getVendor() {
		return appFillingSingleMapper.getVendor();
	}

	@Override
	public List<Map<String, Object>> getMaterial() {
		return appFillingSingleMapper.getMaterial();
	}

	@Override
	public List<Store> getStoreById(String vendor_id, String type) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("vendor_id", vendor_id);
		m.put("type", type);
		return appFillingSingleMapper.getStoreById(m);
	}

	@Override
	public List<Store> getStoreByMohuchaxun(String vendor,String mohuchaxun, String type) {
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("vendor", vendor);
		map.put("mohuchaxun", mohuchaxun);
		map.put("type", type);
		return appFillingSingleMapper.getStoreByMohuchaxun(map);
	}

	@Override
	public List<Store> getStoreAll() {
		return appFillingSingleMapper.getStoreAll();
	}

	@Override
	public List<MailInformation> getMailInfor(String accountName) {
		return appFillingSingleMapper.getMailInfor(accountName);
	}

	@Override
	public List<Map<String, Object>> getCompany() {
		return appFillingSingleMapper.getCompany();
	}

	@Override
	public List<Map<String, Object>> getCourier(String expressIds, String condition) {
		expressIds = expressIds.substring(0, expressIds.length() - 1);
		String[] expressId = expressIds.split(",");
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("expressIds", expressId);
		m.put("phone", condition);
		List<Map<String, Object>> expressIdList = appFillingSingleMapper.getCourier(m);
		return expressIdList;
	}
	
	
	//给派送物资分配快递员
	@Override
	public Map<String, Object> putInMaterialInfo(String courierPhones, String accountPhone, HttpSession session) {
		Map<String, Object> resultState = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Account account = accountMapper.isExist(accountPhone);
		List<Map<String, Object>> materialAndStoreInformations = (List<Map<String, Object>>) session.getAttribute("myMaterial");//得到派送的物资和门店信息
		System.err.println(materialAndStoreInformations);
		if(StringUtils.isEmpty(courierPhones) || StringUtils.isEmpty(accountPhone) || materialAndStoreInformations.size() <= 0){
			resultState.put("error", "ok");
			return resultState;
		} else {
			/**
			 * 开始给快递员分配任务(单线)
			 * */
			//处理快递员电话(剔重)
			courierPhones = courierPhones.substring(0, courierPhones.length() - 1);
			String[] courierGroupByFront = courierPhones.split(",");
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
				resultState.put("info", "ok");
			} else {
				System.out.println("订单插入失败");
				resultState.put("info", "no");
			}
			session.removeAttribute("myMaterial");//释放session
			//给快递员发短息提醒
			SmsSend send = new SmsSend();
			for (int j = 0; j < result.size(); j++) {
				Map<String, Object> map = result.get(j);
				String[] size = map.get("number").toString().split(",");
				String msg = "您有  "+size.length+"条新订单,订单编码分别是:" + "\n" + "【"+map.get("number").toString()+"】";
				System.out.println(msg);
				boolean flag = send.sendSms(map.get("expressPhone").toString(), msg, null);
				if (flag) {
					System.out.println("验证码发送成功");
				} else {
					System.out.println("验证码发送失败");
				}
			}
			return resultState;
		}
	}
	
	@Override
	public Integer getByListCount(String userPhone, String searchMsg, String orderState, String startTime, String endTime) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userPhone", userPhone);
		m.put("searchMsg", searchMsg);
		if(StringUtils.isNotEmpty(orderState) && "pleaseSelectVendor".equals(orderState)){
			orderState = null;
		}
		m.put("orderState", orderState);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		return appFillingSingleMapper.getByListCount(m);
	}

	@Override
	public List<Map<String, Object>> findByList(String userPhone, String searchMsg, String orderState, String startTime, String endTime, Integer size) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userPhone", userPhone);
		m.put("searchMsg", searchMsg);
		if(StringUtils.isNotEmpty(orderState) && "pleaseSelectVendor".equals(orderState)){
			orderState = null;
		}
		m.put("orderState", orderState);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		m.put("size", size);
		return appFillingSingleMapper.findByList(m);
	}

	@Override
	public Map<String, Object> getOrderDetail(String orderNumber) {
		Map<String, Object> map = appFillingSingleMapper.getOrderDetail(orderNumber);
		if(map != null){
			String[] materialContents = map.get("materialContent").toString().split(",");//物资名称
			String[] materialNumbers = map.get("materialNumber").toString().split(",");//物资数量
			for (int i = 0; i < materialContents.length; i++) {
				if(map.get("materialNameAndNumber") != null){
					map.put("materialNameAndNumber", map.get("materialNameAndNumber").toString() + "," + materialContents[i] + "(" + materialNumbers[i] + ")");
				} else {
					map.put("materialNameAndNumber", materialContents[i]+"("+materialNumbers[i]+"件)");
				}
			}
			
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getMarketingOrderTotal(String userPhone, String startTime, String endTime) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userPhone", userPhone);
		parameter.put("startTime", startTime);
		parameter.put("endTime", endTime);
		return appFillingSingleMapper.getMarketingOrderTotal(parameter);
	}
	
	/**
	 * 获取报表数据
	 */
	@Override
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
			
		
		return null;
	}
	
	
	
	
	
	
}
