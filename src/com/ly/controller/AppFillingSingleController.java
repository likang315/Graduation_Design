package com.ly.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.NowPage;
import com.ly.entity.app.Store;
import com.ly.entity.app.Vendor;
import com.ly.service.AppFillingSingleService;
import com.ly.util.Common;

/**
 * 营销中心安排物资配送
 * @author wangleiqi
 * 	1:门店选择;
 * 	2:配送物资选择;
 * 	3:快递员选择;
 * 	4:信息发布查阅;
 */
@Controller
@RequestMapping("/app/fillingSingle")
public class AppFillingSingleController {
	
	@Autowired
	private AppFillingSingleService appFillingSingleService;
	
	/**
	 * 填单信息跳转
	 * @return
	 */
	@RequestMapping("/toStoreInformation")
	public String toStoreInformation(){
		return Common.APP_PATH+"/storeInformation";
		
	}
	
	/**
	 * 获取品牌信息
	 * @return
	 */
	@RequestMapping("/getVendor")
	@ResponseBody
	public Object getVendor(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Vendor> vendors = appFillingSingleService.getVendor();//加载品牌信息
		map.put("vendor", vendors);
		return map;
		
	}
	
	/**
	 * 获取所有门店信息
	 * @return
	 */
	@RequestMapping("/getStoreAll")
	@ResponseBody
	public Object getStoreAll(){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Store> stores = appFillingSingleService.getStoreAll();//加载门店信息
		map.put("store", stores);
		return map;
		
	}
	
	/**
	 * 根据品牌id和门店类型获取信息
	 * @param vendor_id 品牌id
	 * @param type 门店类型（直营，现金）
	 * @return
	 */
	@RequestMapping("/getStoreById")
	@ResponseBody
	public Object getStoreById(String vendor_id, String type){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Store> stores = appFillingSingleService.getStoreById(vendor_id, type);
		map.put("store", stores);
		return map;
		
	}
	
	/**
	 * 门店名称模糊查询
	 * @param vendor 品牌id
	 * @param type 门店类型（直营，现金）
	 * @param mohuchaxun 门店名称
	 * @return
	 */
	@RequestMapping("/getStoreByMohuchaxun")
	@ResponseBody
	public Object getStoreByMohuchaxun(String vendor, String mohuchaxun, String type){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Store> stores = appFillingSingleService.getStoreByMohuchaxun(vendor, mohuchaxun, type);
		map.put("mohu", stores);
		return map;
		
	}
	 
	/**
	 * 物资跳转页面
	 * @return
	 */
	@RequestMapping("/toMaterialInformation")
	public String toMaterialInformation(String store, Model model, HttpSession session){
		if(StringUtils.isEmpty(store) || store.indexOf("null") != -1){//没选择门店
			return "redirect:toStoreInformation.html";
		} else {
			model.addAttribute("store", store);
			//加载物资名称
			List<Map<String, Object>> materials = appFillingSingleService.getMaterial();
			model.addAttribute("material", materials);
			session.removeAttribute("myMaterial");//防止购物车残留
			return Common.APP_PATH + "/materialInformation";
		}
	}
	
	
	/**
	 * 处理物资拼接问题
	 * @param num 物资数量
	 * @param material 物资id
	 * @param materialName 物资名称
	 * @param store 门店信息
	 * @param predictTime 预计送达时长
	 * */
	@RequestMapping("/appendOrRemove")
	@ResponseBody
	public Object appendOrRemove(String num, String material, String materialName, String store, String operation, String predictTime, HttpSession session){
		List<Map<String, Object>> mList; 
		mList = (List<Map<String, Object>>) session.getAttribute("myMaterial");//物资信息
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
		} else if("remove".equals(operation)){//删除操作
			for(int i = 0; i < mList.size(); i++){
				Map<String, Object> map = mList.get(i);
				if(material.equals(map.get("material").toString())){//根据物资id删除购物车中的物资
					mList.remove(i);
				}
			}
		}
		return mList;
	}
	
	//验证有没有添加物资
	@RequestMapping("/checkMaterial")
	@ResponseBody
	public Object checkMaterial(HttpSession session){
		Map<String, Object> m = new HashMap<String, Object>();
		List<Map<String, Object>> myMaterials = (List<Map<String, Object>>) session.getAttribute("myMaterial");//物资信息
		if(myMaterials != null){
			m.put("info", myMaterials.size());
		} else {
			m.put("info", 0);
		}
		return m; 
	}
	
	/**
	 * 快递员跳转页面
	 * @return
	 */
	@RequestMapping("/toCourier")
	public String toCourier(Model model){
		//得到快递公司
		List<Map<String, Object>> ExpressCompanys = appFillingSingleService.getCompany();
		model.addAttribute("expressCompanys", ExpressCompanys);
		return Common.APP_PATH + "/courier";
	}
	
	/**
	 * 获取快递员信息
	 * @return
	 */
	@RequestMapping("/getCourier")
	@ResponseBody
	public Object getCourier(String expressId, String condition){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Map<String, Object>> courierList = appFillingSingleService.getCourier(expressId, condition);
		map.put("courierList", courierList);
		return map;
	}
	
	
	/**
	 * 生成订单记录
	 * @param courierPhones 快递员电话
	 * @param chinaMobiles 生成该次订单任务的营销中心人员电话
	 * @param session 得到已经生成的物资信息和门店信息
	 * @return
	 */
	@RequestMapping("/addMailInfor")
	public String addMailInfor(String courierPhones, String accountPhone, HttpSession session){
		Map<String, Object> result = appFillingSingleService.putInMaterialInfo(courierPhones, accountPhone, session);
		System.out.println("==========================================");
		if (result.get("info") != null) {
			if("ok".equals(result.get("info").toString())){
				System.out.println("订单生成成功");
				return "redirect:toSuccessView.html";
			} else {
				System.out.println("订单生成失败");
				return null;
			}
		}
		if(result.get("error") != null){
			return "redirect:/app/login.html";
		}
		return null;
	}
	
	/**
	 * 跳转订单生成成功提示页面
	 * */
	@RequestMapping("/toSuccessView")
	public String toSuccessView(){
		return Common.APP_PATH + "/success";
	}
	
	/**
	 * 跳转到订单列表
	 * */
	@RequestMapping("/orderList")
	public String orderList(){
		return Common.APP_PATH + "/marketingOrderList";
	}
	
	/**
	 * 订单列表
	 * @param searchMsg 搜索条件
	 * @param orderState 订单状态
	 * @param pageNo 当前页
	 * @param userPhone 当前登录人的电话(营销中心)
	 * */
	@RequestMapping("/getOrderList")
	@ResponseBody
	public Object getOrderList(String userPhone, String searchMsg, String orderState, String startTime, String endTime, String pageNo){
		Map<String, Object> parameter = new HashMap<String, Object>();
		Integer count = appFillingSingleService.getByListCount(userPhone, searchMsg, orderState, startTime, endTime);
		NowPage<Map<String, Object>> page = new NowPage<>(pageNo, count, 3);
		List<Map<String, Object>> orders = appFillingSingleService.findByList(userPhone, searchMsg, orderState, startTime, endTime, (page.getPageNo() * page.getSize()));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for (int i = 0; i < orders.size(); i++) {
			Map<String, Object> m = orders.get(i);
			try {
				if(m.get("createTime") != null){
					m.put("createTime", dateFormat.format(dateFormat.parse(m.get("createTime").toString())));
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		parameter.put("orders", orders);
		parameter.put("count", count);
		parameter.put("pageNo", page.getPageNo());
		return parameter;
	}
	
	/**
	 * 订单详情
	 * */
	@RequestMapping("/getOrderDetail")
	public String getOrderDetail(String orderNumber, Model model){
		Map<String, Object> orderDetail = appFillingSingleService.getOrderDetail(orderNumber);
		model.addAttribute("orderDetail", orderDetail);
		return Common.APP_PATH + "/marketingOrderDetail";
	}
	
	
	/**
	 * 订单统计
	 * */
	@RequestMapping("/toMarketingOrderTotalView")
	public String toMarketingOrderTotalView(){
		return Common.APP_PATH + "/toMarketingOrderTotal";
	}
	
	
	/**
	 * @author 殷瑜泰 2017年4月15日下午1:52:08
	 * 营销中心统计报表
	 * @param userPhone
	 * @param startTime
	 * @param endTime
	 * @param model
	 * @return
	 */
	@RequestMapping("/getMarketingOrderTotal")
	public String getMarketingOrderTotal(String startTime, String endTime, Model model){
		
		//获取到报表所需要的数据
		 List<Map<String,Object>> ls =  appFillingSingleService.getReportDatas(startTime,endTime);
		 //当ls不为空的时候将数据传到页面
		 if(ls != null){
			List<Map<String,Object>> ls1 = (List<Map<String, Object>>) ls.get(0).get("materialAndNumLs");
			 model.addAttribute("ls", ls);
			 model.addAttribute("size", ls1.size());
		 } else {
			 model.addAttribute("ls", null);
		 }
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return Common.APP_PATH + "/reportMarket";
		
		
	}
}
