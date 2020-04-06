package com.ly.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.ly.entity.NowPage;
import com.ly.entity.app.MailInformation;
import com.ly.entity.background.Account;
import com.ly.service.APPStroreSuppliesService;
import com.ly.service.LogisticsOrderService;
import com.ly.util.Common;
import com.ly.util.WorkSheet;

/**
 * 
 * @Author lfy
 *订单列表查询
 */
@RequestMapping("/background/order/")
@Controller
public class LogisticsOrderController  {
	
	@Autowired
	private LogisticsOrderService logisticsOrderService;
	@Autowired
	private APPStroreSuppliesService appStroreSuppliesService;
	
	/**
	 * 查找物流订单列表
	 * @author lfy
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpSession session,HttpServletRequest request,Model model,String pageNo,String state, String info, String modifyInfo,String deleteInfo,String orderInfo){
		if(pageNo == null || "".equals(pageNo))
			pageNo = "0";
		Map<String, Object> m = new HashMap<String, Object>();
		if(state == null || "".equals(state))
			state ="0";	
		m.put("state", state);	
		Integer counts = logisticsOrderService.findOrdercount(m);
		//查询出快递员信息为空的订单的数量
		Integer countOfNull = logisticsOrderService.findOrderOfNullNum();
		//创建分页查询对象
		NowPage<Map<String, Object>> page1 = new NowPage<Map<String,Object>>(pageNo, countOfNull, 10);
		
		//查询出快递公司的信息
		List<Map<String,Object>> companyList = logisticsOrderService.getCompany();
		
		NowPage<Map<String, Object>> page = new NowPage<Map<String,Object>>(pageNo, counts, 10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state);	
		map.put("start", page.getStart());
		map.put("size", page.getSize());
		/*设置分页信息*/
		map.put("start1", page1.getStart());
		map.put("size1", page1.getSize());
		List<Map<String, Object>> data = logisticsOrderService.findOrderlist(map);
		
		//根据分页信息查询出数据
		List<Map<String, Object>> data1 = logisticsOrderService.findOrderOfNulllist(map);
		//page.setItems(data);
		model.addAttribute("te", JSON.toJSON(data));
		model.addAttribute("data1", data1);
		model.addAttribute("page1", page1);
		model.addAttribute("companyLs",companyList);
		model.addAttribute("dataSize", data1.size());
		model.addAttribute("page", page);
		model.addAttribute("state", state);
		try {
			if(info != null && info != null){
			model.addAttribute("info", new String (info.getBytes("ISO-8859-1"),"UTF-8"));
			}
			if(modifyInfo != null && modifyInfo != ""){
				model.addAttribute("modifyInfo", new String (modifyInfo.getBytes("ISO-8859-1"),"UTF-8"));	
			}
			if(deleteInfo != null && deleteInfo != ""){
				model.addAttribute("deleteInfo", new String (deleteInfo.getBytes("ISO-8859-1"),"UTF-8"));
			}
			if(orderInfo != null && orderInfo != ""){
				model.addAttribute("orderInfo", new String (orderInfo.getBytes("ISO-8859-1"),"UTF-8"));
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Common.BACKGROUND_PATH + "/order/list";
	}
	
	/**
		 * 根据客户电话及店员电话搜索返销信息
		 * @author lfy
		 */
	@RequestMapping("search")
	public String search( Model model,String phone,String pageNo,HttpServletRequest request,String state){		
		if(pageNo == null || "".equals(pageNo))
			pageNo = "0";
		if(state == null || "".equals(state))
			state ="0";	
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("phone", phone);
		Integer counts = logisticsOrderService.getSearchCount( m);
		NowPage<Map<String, Object>> page = new NowPage<Map<String,Object>>(pageNo, counts, 10);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("start", page.getStart());
		map.put("size", page.getSize());	
		List<Map<String, Object>> data = logisticsOrderService.getSearchPhone(map);
		/*page.setItems(data);*/
		model.addAttribute("te", JSON.toJSON(data));
		model.addAttribute("page", page);
		model.addAttribute("state", state);
		model.addAttribute("phone", phone);
		return Common.BACKGROUND_PATH+"/order/list";
		
	}
	/**
	 * 查看地图
	 * @author lfy
	 */
	@RequestMapping(value = "displayMap")
	public String displayMap( String id,String courier_Phone,Model model,HttpServletRequest request){		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("courier_Phone", courier_Phone);
		Map<String, Object> maplist = logisticsOrderService.getDisplayMap(m);
		Map<String, Object> maplist1 = logisticsOrderService.getDisplayMap1(m);
		model.addAttribute("maplist", maplist);
		model.addAttribute("maplist1", maplist1);
		return Common.BACKGROUND_PATH+"/order/displayMap";
		
	}
	       /**
	        * 
	        *Author lfy
	        * 批量删除
	        */
			@RequestMapping(value ="deleteLogistics")
			@ResponseBody
			public Object deleteLogistics(MailInformation mailInformation,String ids,HttpServletRequest request){	
				
				Map<String,Object> result = new HashMap<String,Object>();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("mailInformation", mailInformation);
				map.put("ids", ids);
				boolean flag = logisticsOrderService.deleteLogistics(map);
				if(flag)	
					result.put("state", "ok");
				else
					result.put("state", "no");
				
				return result;
				
			}
			/**
			 * 
			 *Author lfy
			 * 批量配送
			 */
			@RequestMapping(value ="distribution")
			@ResponseBody
			public Object distribution(MailInformation mailInformation,String ids,HttpServletRequest request){	
				
				Map<String,Object> result = new HashMap<String,Object>();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("mailInformation", mailInformation);
				map.put("ids", ids);
				map.put("shipTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				boolean flag = logisticsOrderService.distribution(map);
				if(flag)	
					result.put("state", "ok");
				else
					result.put("state", "no");
				
				return result;
				
			}
			/**
			 * 单查订单
			 * @author lfy
			 * */
			@RequestMapping(value="findByLogistics")
			public String findByLogistics(Model model,String id){
				Map<String,Object> logisticslist  = logisticsOrderService.findByLogistics(id);
				List<Map<String,Object>> ls = new ArrayList<Map<String,Object>>();
				String materialContents= logisticslist.get("materialContent").toString();
				String materialNumbers= logisticslist.get("materialNumber").toString();
				String[] materialC=materialContents.split(",");
				String[] materialN=materialNumbers.split(",");
				for (int i = 0; i < materialC.length; i++) {
					Map<String, Object> map = new HashMap<String,Object>();
					String a = materialC[i];
					//根据物资名称查询出物资的id
					Integer materialId =  appStroreSuppliesService.getMaterialsId(a);
					String b = materialN[i];
					 map.put("materialC", a);
					 map.put("materialN",b);
					 map.put("materialId", materialId);
					 ls.add(map);
				}
				List<Map<String, Object>> material = appStroreSuppliesService.getMaterials();//所在所有物资
				model.addAttribute("material", material);
				model.addAttribute("chooseMaterial", materialContents);
				model.addAttribute("chooseMaterialNUM", materialNumbers);
				model.addAttribute("ls", ls);
				model.addAttribute("logisticslist", logisticslist);
				return Common.BACKGROUND_PATH+"/order/update";
			}
			
			@RequestMapping("/appendOrRemove")
			@ResponseBody
			public Object appendOrRemove(String num, String material, String materialName,  String operation,  HttpSession session){
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
							mList.add(map);
						}
					} else {
						mList = new ArrayList<Map<String, Object>>();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("num", num);
						map.put("material", material);
						map.put("materialName", materialName);
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
			 * 
			 * @author 殷瑜泰 2017年4月13日上午9:29:33
			 * 修改订单信息
			 *
			 * @param m
			 * @return
			 */
			@RequestMapping(value="updateLogistics")
			public String updateLogistics(@RequestParam Map m, RedirectAttributes attributes){
				//修改订单信息,返回订单修改的状态
				attributes.addAttribute("modifyInfo", logisticsOrderService.updateLogistics(m));				
				return "redirect:list.html";
				
			}
			
			
			/**
			 * @author lfy
			 * 返销成功下载清单
			 */
			@RequestMapping("downLogistics")
			@ResponseBody
			public void downCounterReport(HttpServletResponse response,HttpSession session){
					Workbook workbook = new HSSFWorkbook();
					Account account = (Account) session.getAttribute("userSession");
					List<Map<String,Object>> logisticslist = new ArrayList<Map<String,Object>>();	
					 String groupId= String.valueOf(account.getGroupId());
					 String[] header1={"订单号","发货人","发货人电话","发货时间","物资名称","物资数量","快递员电话",
							 "快递员姓名"," 收货时间","收货人","收货人电话","收货人地址"}; 
					 String[] sheets1={"id","shipperName","shipper","shipTime","materialContent","materialNumber",
							 "courierPhone","courierName","leadTime","consignee","phone","address"};     
					          logisticslist = logisticsOrderService.findlogisticslist();
							WorkSheet.ExcelSheet(header1, sheets1, workbook,"订单配送成功清单", logisticslist);
						
				        try {
				        	response.setCharacterEncoding("UTF-8");
				            response.setContentType("application/vnd.ms-excel");
					        String fileName = "订单配送成功清单" +".xls";
					        fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
				            response.addHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
				            OutputStream outputStream = response.getOutputStream();
				            workbook.write(outputStream);
				            
				        } catch (IOException e) {
				            e.printStackTrace();
				        }
						}
			
	
		/**
		 * 跳转到维护订单的界面
		 * 
		 */
		@RequestMapping("addOrderUI")
		public String addOrderUI(){
			return Common.BACKGROUND_PATH+"/order/orderadd";
		}
		
		@RequestMapping("addOrder")
		public String addOrder(@RequestParam(value="file", required = false) MultipartFile file, HttpServletRequest request,RedirectAttributes attributes){
			//订单信息维护
			attributes.addAttribute("info", logisticsOrderService.addOrder(file,request).get("addInfo")); 
			return "redirect:list.html";
		}
		
		/**
		 * 核实验证该快递员是否存在
		 * @author 殷瑜泰 2017年4月13日上午10:24:25
		 *
		 * @param courierPhone
		 * @return
		 */
		@RequestMapping("checkcourierPhone")
		@ResponseBody
		public Object checkcourierPhone(String courierPhone){
			Map m = new HashMap<>();
			String str = logisticsOrderService.checkcourierPhoneService(courierPhone);
			if(str.equals("ok")){
				m.put("info", "ok");
			} else {
				m.put("info", str);
			}
			return m;
		}
		
		/**
		 * 
		 * @author 殷瑜泰 2017年4月20日下午2:50:59
		 *
		 * @return
		 */
		@RequestMapping("getCourier")
		@ResponseBody
		public Object getCourier(String[] companyId){
			//根据传入的快递公司的ID信息查询出归属快递员
			return logisticsOrderService.getCourierLs(companyId);
		}
		/**
		 * 派送订单
		 * @author 殷瑜泰 2017年4月20日下午5:18:29
		 *
		 * @return
		 */
		@RequestMapping("sendOrder")
		public String sendOrder(String orderName, String[] seleCourier,RedirectAttributes attributes ){
			//派送处理订单
			attributes.addAttribute("orderInfo",logisticsOrderService.sendOrder(orderName,seleCourier).get("orderInfo"));
			return "redirect:list.html";
			
		}
		
		
		
			
			
}
