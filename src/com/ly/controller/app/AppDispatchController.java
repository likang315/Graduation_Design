package com.ly.controller.app;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ly.service.AppDispatchService;


/**
 * app端接口类
 * @author admin
 * @date 2018年4月20日 上午10:18:59
 * @described
 */
@Controller
@RequestMapping("/app/dispatch/")
public class AppDispatchController {

	@Autowired
	private AppDispatchService appDispatchService;
	
	/**
	 * 登录接口
	 * @author zhangzhi
	 * @date 2018年4月20日上午10:28:16
	 * @param
	 */
	@RequestMapping(value="login",method={RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=utf-8")
	@ResponseBody
	public Object login(String accountName,String password){
		return appDispatchService.login(accountName,password);
	}
	
	/**
	 * 忘记密码
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:04:48
	 * @param
	 */
	@RequestMapping(value="forgetPassword",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object forgetPassword(String accountName,String password1,String password2){
		return appDispatchService.forgetPassword(accountName,password1,password2);
	}
	
	/**
	 * 获取用户信息
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:04:48
	 * @param
	 */
	@RequestMapping(value="getUserInfor",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object getUserInfor(String accountName){
		return appDispatchService.getUserInfor(accountName);
	}
	

	/**
	 * 修改密码
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:04:48
	 * @param
	 */
	@RequestMapping(value="updatePassword",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object updatePassword(String accountName,String oldPassword,String password1,String password2){
		return appDispatchService.updatePassword(accountName,oldPassword,password1,password2);
	}
	
	/**
	 * 订单列表
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:04:48
	 * @param
	 * accountName 快递员账号或管理员账号（当角色是快递员时使用）（channel_code与accountName不能同时为空）
	 * channel_code门店渠道编码(当角色为门店时使用)
	 * role 登录人角色（1代表门店，2代表管理员，3代表快递员）（not null）
	 * state 订单状态
	 * searchMsg 订单编号
	 * startTime 查询开始时间
	 * endTime 查询结束时间
	 * pageNo 分页页数 默认为0
	 */
	@RequestMapping(value="getOrderList",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object getOrderList(String accountName,String channel_code,String role,String state,String searchMsg,String startTime,String endTime,String pageNo){
		return appDispatchService.getOrderList(accountName,channel_code,role,state,searchMsg,startTime,endTime,pageNo);
	}
	
	/**
	 * 订单详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param id 订单编号
	 * @param channel_code 收货门店渠道编码
	 */
	@RequestMapping(value="storelistDetails",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object storelistDetails(String id,String channel_code){
		return appDispatchService.storelistDetails(id,channel_code);
	}
	
	/**
	 * 获取物资名称
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 */
	@RequestMapping(value="getMaterials",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object getMaterials(){
		return appDispatchService.getMaterials();
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
	 * @param expanding_demand 拓展物资
	 */
	@RequestMapping(value="addmaterial",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object addmaterial(String channel_code,String materialContent,String materialNumber,String store_shopowner_phone,String store_shopowner_name,String store_name,String expanding_demand,HttpSession session){
		return appDispatchService.addmaterial(channel_code,materialContent,materialNumber,store_shopowner_phone,store_shopowner_name,store_name,expanding_demand,session);
	}
	
	/**
	 * 处理物资拼接问题 
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param materialId 物资id
	 * @param materialName 物资名称
	 * @param num 数量
	 * @param operation 操作(新增或删除)
	 * @param role 登录人角色（1门店，2快递员，3营销中心)
	 * @throws UnsupportedEncodingException 
	 * */
	@RequestMapping(value="appendOrRemove",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object appendOrRemove(String materialId, String materialName,String num,String store,String predictTime, String operation, String role,HttpSession session) throws UnsupportedEncodingException{
		if("1".equals(role)){
			return appDispatchService.appendOrRemove(materialId,role,materialName,num,operation,session);
		}else{
			return appDispatchService.appendOrRemoveForPaidan(num,materialId,role,materialName,store,operation,predictTime,session);
		}
		//num,materialId,materialName,store,operation,predictTime,session
	}
	
	/**
	 * 验证有没有添加物资
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param role 登录人角色（1代表门店，2代表管理员，3代表快递员）
	 * */
	@RequestMapping(value="checkMaterial",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object checkMaterial(HttpSession session,String role){
		return appDispatchService.checkMaterial(session,role);
	}
	
	/**
	 * 需求上报列表
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param channel_code 门店编码
	 * @param pageNo 分页页数
	 * @param operation 操作(新增或删除)
	 * @param state 审核状态
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	@RequestMapping(value="suppliesMaterialList",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object suppliesMaterialList(String channel_code,String pageNo,String state,String startTime,String endTime){
		return appDispatchService.suppliesMaterialList(channel_code,pageNo,state,startTime,endTime);
	}
	//toStoreTotal
	
	
	/**
	 * 需求上报统计
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param channel_code 门店编码
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	@RequestMapping(value="toStoreTotal",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object toStoreTotal(String channel_code,String startTime,String endTime){
		return appDispatchService.toStoreTotal(channel_code,startTime,endTime);
	}
	
	/**
	 * 需求上报详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param channel_code 门店编码
	 * @param id 需求上报id
	 * @param endTime 查询结束时间
	 */
	@RequestMapping(value="toStoreSuppliesDetails",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object toStoreSuppliesDetails(String channel_code,String id){
		return appDispatchService.toStoreSuppliesDetails(channel_code,id);
	}
	
	/**
	 * 政策列表
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param groupId 
	 * @param userId 
	 */
	@RequestMapping(value="getPolicyList",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object getPolicyList(String groupId,String userId){
		return appDispatchService.getPolicyList(groupId,userId);
	}
	
	/**
	 * 政策搜索
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param groupId 
	 * @param userId 
	 * @param keyword 关键字
	 */
	@RequestMapping(value="policySearch",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object policySearch(String keyword,String groupId,String userId){
		return appDispatchService.policySearch(keyword,groupId,userId);
	}
	
	/**
	 * 政策详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param groupId 
	 * @param userId 
	 */
	@RequestMapping(value="policyDetail",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object policyDetail(String id,String userId){
		return appDispatchService.policyDetail(id,userId);
	}
	
	/**
	 * 快递员历史订单统计
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param accountName 快递员账号
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	@RequestMapping(value="historyListCount",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object historyListCount(String accountName,String startTime,String endTime){
		return appDispatchService.historyListCount(accountName,startTime,endTime);
	}
	
	/**
	 * 门店配送统计（营销中心）
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	@RequestMapping(value="storeListCount",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object storeListCount(String startTime,String endTime){
		return appDispatchService.storeListCount(startTime,endTime);
	}
	
	/**
	 * 获取所有品牌
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 */
	@RequestMapping(value="getAllBrand",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object getAllBrand(){
		return appDispatchService.getAllBrand();
	}
	
	/**
	 * 根据品牌id、门店类型获取所有门店
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param vendor_id 品牌id
	 * @param type 门店类型
	 * @param mohuchaxun 模糊查询字段
	 */
	@RequestMapping(value="getAllStore",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object getAllStore(String vendor_id,String type,String mohuchaxun){
		return appDispatchService.getAllStore(vendor_id,type,mohuchaxun);
	}
	
	/**
	 * 处理物资拼接问题(物资派送时)
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param num 物资数量
	 * @param materialId 物资id
	 * @param materialName 物资名称
	 * @param store 门店信息
	 * @param predictTime 预计送达时长
	 * @param operation 具体操作（增加或删除）（append、remove）
	 */
	@RequestMapping(value="appendOrRemoveForPaisong",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object appendOrRemoveForPaidan(String num, String materialId,String role, String materialName, String store, String operation, String predictTime, HttpSession session){
		return appDispatchService.appendOrRemoveForPaidan(num,materialId,role,materialName,store,operation,predictTime,session);
	}
	
	/**
	 * 查询所有的快递公司
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 */
	@RequestMapping(value="getAllDeliveryCompany",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object getAllDeliveryCompany(){
		return appDispatchService.getAllDeliveryCompany();
	}
	
	/**
	 * 根据快递公司查询快递员信息
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param company_id 快递公司id
	 * @param select_infor 快递员公司或姓名
	 */
	@RequestMapping(value="getDeliveryByCompany",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object getDeliveryByCompany(String company_id,String select_infor){
		return appDispatchService.getDeliveryByCompany(company_id,select_infor);
	}
	
	/**
	 * 生成工单
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param deliveryPhones 快递员电话（以“，”隔开）
	 * @param accountName 生成该次订单任务的营销中心人员电话
	 */
	@RequestMapping(value="addOrder",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object addOrder(String deliveryPhones,String accountName,HttpSession session){
		return appDispatchService.addOrder(deliveryPhones,accountName,session);
	}
	
	/**
	 * 订单派送
	 * 点击“查看派送地图”或“开始派送”前，将订单装维改为4，即订单此时处于派送中
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 */
	@RequestMapping(value="updateStateWhenDelivery",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object updateStateWhenDelivery(String id){
		return appDispatchService.updateStateWhenDelivery(id);
	}
	
	/**
	 * 点击“查看派送地图”时，根据快递员电话和订单编号修改快递员经纬度
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 * @param courier_longitude 快递员经度
	 * @param courier_latitude 快递员纬度
	 * @param courier_Phone 快递员电话
	 */
	@RequestMapping(value="updateCourierstore",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object updateCourierstore(String courier_longitude,String courier_latitude,String courier_Phone,String logistics){
		return appDispatchService.updateCourierstore(courier_longitude,courier_latitude,courier_Phone,logistics);
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
	@RequestMapping(value="addCourierstore",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object addCourierstore(String courier_longitude,String courier_latitude,String courier_Phone,String logistics){
		return appDispatchService.addCourierstore(courier_longitude,courier_latitude,courier_Phone,logistics);
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
	@RequestMapping(value="detailedRoute",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object detailedRoute(String store_longitude,String store_latitude,String address,String logistics) throws UnsupportedEncodingException{
		return appDispatchService.detailedRoute(store_longitude,store_latitude,address,logistics);
	}
	

	/**
	 * 完成订单，修改订单状态
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
	@RequestMapping(value="updateOrder",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object updateOrder(String id,String signPeople,String signPhone,String recipientTime) throws UnsupportedEncodingException{
		return appDispatchService.updateOrder(id,signPeople,signPhone,recipientTime);
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
	@RequestMapping(value="updateStoreLocation",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object updateStoreLocation(String id,String store_longitude,String store_latitude,String channel_code) throws UnsupportedEncodingException{
		return appDispatchService.updateStoreLocation(id,store_longitude,store_latitude,channel_code);
	}

	/**
	 * 上传图片到服务器并返回文件路径
	 * @author zhangzhi
	 * @date 2018年5月11日下午5:11:07
	 * @param
	 */
	@RequestMapping("uploadImageForSingle")
	@ResponseBody
	public Object uploadImageForSingle(@RequestParam("file") MultipartFile file) throws UnsupportedEncodingException{
		return appDispatchService.uploadImageForSingle(file);
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
	@RequestMapping(value="uploadImg",method={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=utf-8")
	@ResponseBody
	public Object uploadImg(String orderImg, String storeImg,String logistics, HttpServletRequest request,
			HttpServletResponse response,String signPeople,String signPhone, String recipientTime) throws UnsupportedEncodingException{
		return appDispatchService.uploadImg(orderImg,storeImg,logistics,signPeople,signPhone,recipientTime);
	}

}
