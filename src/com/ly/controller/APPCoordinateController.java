package com.ly.controller;

import java.beans.Encoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sun.misc.BASE64Decoder;

import com.ly.entity.app.APPCourierStore;
import com.ly.entity.app.APPOperation;
import com.ly.entity.app.MailInformation;
import com.ly.entity.background.Account;
import com.ly.entity.background.Dic;
import com.ly.service.APPCoordinateService;
import com.ly.util.Common;
import com.ly.util.FTPLinuxUtils;
/**
 * 订单派送地图
 * @author lfy
 *
 */

@RequestMapping("/app/coordinate")
@Controller
public class APPCoordinateController {
	
	@Autowired
	APPCoordinateService appCoordinateService;
	
			/*	快递员地图显示暂时不用
			 * @RequestMapping()
				public String coordinate(){
					return Common.APP_PATH + "/coordinate";
				}*/
	
	/**
	 * 
	 * 跳转
	 * @throws UnsupportedEncodingException 
	 *//*
	

	@RequestMapping(value="tocourierList")
	public String tocourierList(String state,Model model,String accountName,String imgInfo) throws UnsupportedEncodingException{
		if(imgInfo != null && imgInfo != ""){
			model.addAttribute("imgInfo", new String(imgInfo.getBytes("ISO-8859-1"),"UTF-8"));	
		}
		
		return Common.APP_PATH + "/tocourierList";
	}
	*//**
	 * 查找
	 *//*
	@RequestMapping(value="CourierAllList")
	public  String CourierAllList(Model model,String accountName,String state,String searchMsg,String startTime,String endTime,Integer jiazai){
		if(StringUtils.isNotEmpty(state) && "0".equals(state)){
			state = null;
		}
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("state", state);
		m.put("accountName", accountName);
		m.put("searchMsg", searchMsg);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		m.put("jiazai", jiazai);
		List<Map<String, Object>> map=appCoordinateService.CourierAllTotal(m);
		
		model.addAttribute("state", state);
		model.addAttribute("searchMsg", searchMsg);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("map", map);
		model.addAttribute("size", map.size());
		
		return Common.APP_PATH+"/courierlist";
		
	}*/
	
	/**
	 * 
	 * 跳转订单列表
	 * @throws UnsupportedEncodingException 
	 */
	

	@RequestMapping(value="tocourierList")
	public String tocourierList(String state,Model model,String accountName,String imgInfo) throws UnsupportedEncodingException{
		if(imgInfo != null && imgInfo != ""){
			model.addAttribute("imgInfo", new String(imgInfo.getBytes("ISO-8859-1"),"UTF-8"));	
		}
		return Common.APP_PATH + "/courierlist";
	}
	/**
	 * 查找订单列表
	 */
	@RequestMapping(value="CourierAllList")
	@ResponseBody
	public  Object CourierAllList(Model model,String accountName,String state,String searchMsg,String startTime,String endTime,Integer jiazai){
		if(StringUtils.isNotEmpty(state) && "0".equals(state)){
			state = null;
		}
		Map<String, Object> pamer=new HashMap<String, Object>();
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("state", state);
		m.put("accountName", accountName);
		m.put("searchMsg", searchMsg);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		m.put("jiazai", jiazai);
		Integer count=appCoordinateService.getCourierlistCount(m);
		List<Map<String, Object>> map=appCoordinateService.CourierAllTotal(m);
		pamer.put("state", state);
		pamer.put("searchMsg", searchMsg);
		pamer.put("startTime", startTime);
		pamer.put("endTime", endTime);
		pamer.put("map", map);
		pamer.put("count", count);
		pamer.put("jiazai", jiazai);
		
		return pamer;
		
	}
	
	
	
	
	/**
	 * 查找未派送订单的坐标
	 * @return lfy
	 */
	
	
	@RequestMapping(value="/findIncomplete")
	@ResponseBody
	public Map<String, Object> findIncomplete(String courierPhone,Model model){
		List<Map<String, Object>> list =appCoordinateService.findIncomplete(courierPhone);
		Map<String, Object> map=new HashMap<String, Object>();
		if(list.size()>0){
			map.put("state", "ok");
			map.put("list", list);
		}else{
			map.put("state", "no");
		}
		
		return map;
	}
	
	/**
	 * 
	 * @author lfy
	 * 根据id查找门店信息
	 */
	@RequestMapping(value="/storeDetails")
	public String storeInformation(String channel_code,String courierPhone,Model model,String id){
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("channel_code", channel_code);
		m.put("courierPhone", courierPhone);
		m.put("id", id);
		Map<String,	Object> map=appCoordinateService.findStore(m);
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
		
		model.addAttribute("maps", map);
		return Common.APP_PATH + "/storeDetails";
	}

	
	/**
	 * 
	 * @author lfy
	 * 返回页面门店的经纬度
	 */
	@RequestMapping(value="/detailedRoute")
	public String detailedRoute(Double store_longitude,Double store_latitude, Model model,String address,String dizhi,String logistics){
		
		Map<String,	Object> maplist  =appCoordinateService.findbyList(logistics);
		
		if(address != null && address != ""){
			try {
				address = new String(address.getBytes("ISO-8859-1"), "UTF-8");
				model.addAttribute("address", address);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		model.addAttribute("store_longitude", store_longitude);
		model.addAttribute("store_latitude", store_latitude);
		model.addAttribute("maplist", maplist);
		
		return Common.APP_PATH + "/detailedRoute";
	}
	
	/**
	 * @author lfy
	 *  更新派送订单的状态
	 */
	@ResponseBody
	@RequestMapping("updateOrder")
	public Map<String, Object> updateOrder (Model model, String id,String signPeople,String signPhone, Date leadTime,String isReplace,Date serviceTime,String recipientTime,String factTime ) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(signPeople != null && signPeople != ""){
			try {
				signPeople = new String(signPeople.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		leadTime=new Date();
		serviceTime=new Date();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		isReplace="0";
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("signPeople", signPeople);
		m.put("signPhone", signPhone);
		m.put("leadTime", leadTime);
		m.put("isReplace", isReplace);
		m.put("serviceTime", serviceTime);
		m.put("factTime", factTime);
		if(appCoordinateService.updateOrder(m)){
			map.put("state", "ok");
		} else {
			map.put("state", "no");
		}
		return map;
	}
	
	
	/**
	 * @author lfy
	 *  添加快递员经纬度
	 */
	@ResponseBody
	@RequestMapping("addCourierstore")
	public Map<String, Object> addCourierstore (Model model, APPCourierStore appCourierStore,String actualTime,Date recipientTime,String logistics) {
		appCourierStore.setActualTime(new Date());
		recipientTime=new Date();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", logistics);
		m.put("recipientTime", recipientTime);
		Map<String, Object> map = new HashMap<String, Object>();
		if(appCoordinateService.addCourierstore(appCourierStore)&&appCoordinateService.addCourierTime(m)){
			map.put("state", "ok");
		} else {
			map.put("state", "no");
		}
		return map;
	}

	
	/**
	 * @author lfy
	 *  修改快递员经纬度
	 */
	@ResponseBody
	@RequestMapping("updateCourierstore")
	public Map<String, Object> updateCourierstore ( String courier_longitude,Date actualTime,String courier_latitude,String courier_Phone,String logistics) {
		actualTime=	new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("courier_longitude",	courier_longitude);
		m.put("courier_latitude",	courier_latitude);
		m.put("actualTime",	actualTime);
		m.put("courier_Phone",	courier_Phone);
		m.put("logistics",	logistics);
		if(appCoordinateService.updateCourierstore(m)){
			map.put("state", "ok");
		} else {
			map.put("state", "no");
		}
		return map;
	}
	/**
	 * @author lfy
	 *  采集门店经纬度经纬度
	 */
	@ResponseBody
	@RequestMapping("addstore")
	public Map<String, Object> addstore (Model model, String id,String store_longitude,String store_latitude,String channel_code) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("store_longitude", store_longitude);
		m.put("store_latitude", store_latitude);
		m.put("channel_code", channel_code);
		if(appCoordinateService.addStoremail(m)&&appCoordinateService.addStoreinfo(m)){
			map.put("state", "ok");
		} else {
			map.put("state", "no");
		}
		return map;
	}
	/**
	 * @author lfy
	 *  添加快递员拒绝派单的原因
	 *//*
	@ResponseBody
	@RequestMapping("addRefuseReason")
	public Map<String, Object> addRefuseReason (Model model, APPOperation appOperation,String create_time) {
		appOperation.setCreate_time(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		if(appCoordinateService.addRefuseReason(appOperation)){
			map.put("state", "ok");
		} else {
			map.put("state", "no");
		}
		return map;
	}*/
	/**
	 * @author lfy
	 *  更新派送订单的状态
	 */
	@ResponseBody
	@RequestMapping("updateState")
	public Map<String, Object> updateState (Model model, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(appCoordinateService.updateState(id)){
			map.put("state", "ok");
		} else {
			map.put("state", "no");
		}
		return map;
	}
	@RequestMapping("goCourierTotal")
	public String goStoreTotal() {
		return Common.APP_PATH+"/toCourierTotal";
	}
	
	/**
	 * 跳转订单统计页面
	 */
	@RequestMapping(value="toCourierTotal")
	public  String toStoreTotal(Model model,String accountName, String startTime, String endTime){
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("accountName", accountName);
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		
	    List<Map<String, Object>> map=appCoordinateService.findtotal(m);
	    
			model.addAttribute("map", map);
			model.addAttribute("accountName", accountName);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
		
		return Common.APP_PATH+"/courierTotal";
		
	}
	
	/**
	 * 查找订单统计数据
	 */
	@RequestMapping(value="CourierAllTotal")
	public  String CourierAllTotal(Model model,String accountName,String state){
		
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("state", state);
		m.put("accountName", accountName);
		List<Map<String, Object>> map=appCoordinateService.CourierAllTotal(m);
		
		model.addAttribute("state", state);
		model.addAttribute("map", map);
		
		return Common.APP_PATH+"/courierAllTotal";
		
	}
	/**
	 * 跳转图片上传页面
	 * @return
	 */
	@RequestMapping("goCourierUpload")
	public String goCourierUpload(String logistics,String channel_code,String courierPhone, Model model) {
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("channel_code", channel_code);
		m.put("courierPhone", courierPhone);
		m.put("id", logistics);
		Map<String,	Object> map=appCoordinateService.findStore(m);
		model.addAttribute("map", map);
		model.addAttribute("logistics", logistics);
		return Common.APP_PATH+"/courierUpload";
	}
	
	/**
	 * 图片上传
	 * @return
	 */
	
	@RequestMapping("uploadImg")
	@ResponseBody
	public Object saveFile(@RequestParam("orderImg") MultipartFile orderImg, @RequestParam("storeImg") MultipartFile storeImg,
			String logistics, HttpServletRequest request,HttpServletResponse response,RedirectAttributes attributes, String signPeople,
			String signPhone, Date leadTime,String isReplace,Date serviceTime,String recipientTime,String factTime){
		leadTime=new Date();
		serviceTime=new Date();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		isReplace="0";
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("logistics", logistics);
		m.put("signPeople", signPeople);
		m.put("signPhone", signPhone);
		m.put("leadTime", leadTime);
		m.put("isReplace", isReplace);
		m.put("serviceTime", serviceTime);
		m.put("factTime", factTime);
		Map<String, Object>map=new HashMap<String, Object>();
		
		//将图片信息维护入订单中
		 if(appCoordinateService.addImgForOrder(orderImg,storeImg,m)){
			 //attributes.addAttribute("imgInfo","订单配送完成");
			 map.put("state","ok");
		 } else {
			 map.put("state","no");
			//attributes.addAttribute("imgInfo","订单状态修改异常");
		 }
		return map;
	}
	
}
