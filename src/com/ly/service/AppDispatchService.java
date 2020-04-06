package com.ly.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

/**
 * app端接口类
 * @author admin
 * @date 2018年4月20日 上午10:22:38
 * @described
 */
public interface AppDispatchService {

	/**
	 * 登录接口
	 * @author zhangzhi
	 * @date 2018年4月20日上午10:28:29
	 * @param
	 */
	Object login(@Param("accountName") String accountName,@Param("password") String password);

	/**
	 * 忘记密码
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:04:48
	 * @param
	 */
	Object forgetPassword(@Param("accountName")  String accountName, @Param("password1")  String password1,@Param("password2") String password2);

	/**
	 * 获取用户信息
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:34:42
	 * @param
	 */
	Object getUserInfor(@Param("accountName") String accountName);

	/**
	 * 获取验证码
	 * @author zhangzhi
	 * @date 2018年4月20日下午4:38:29
	 * @param
	 * @throws Exception 
	 */
	Object getCode(@Param("accountName")String accountName,HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * 验证验证码
	 * @author zhangzhi
	 * @date 2018年4月20日下午4:56:47
	 * @param
	 */
	Object testCode(@Param("tellPhone")String tellPhone,@Param("code") String code);

	/**
	 * 修改密码
	 * @author zhangzhi
	 * @date 2018年4月20日下午5:13:23
	 * @param
	 */
	Object updatePassword(@Param("accountName")String accountName,@Param("oldPassword") String oldPassword,@Param("password1") String password1,@Param("password2") String password2);

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
	Object getOrderList(String accountName, String channel_code, String role, String state, String searchMsg,
			String startTime, String endTime, String pageNo);

	/**
	 * 订单详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param id 订单编号
	 * @param channel_code 收货门店渠道编码
	 */
	Object storelistDetails(String id, String channel_code);

	/**
	 * 获取物资名称
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 */
	Object getMaterials();

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
	Object addmaterial(String channel_code, String materialContent, String materialNumber, String store_shopowner_phone,
			String store_shopowner_name, String store_name, String expanding_demand,HttpSession session);

	/**
	 * 处理物资拼接问题 
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param materialId 物资id
	 * @param materialName 物资名称
	 * @param num 数量
	 * @param operation 操作(新增或删除)
	 * @throws UnsupportedEncodingException 
	 * */
	Object appendOrRemove(String materialId, String role,String materialName, String num, String operation, HttpSession session) throws UnsupportedEncodingException;

	/**
	 * 验证有没有添加物资
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * */
	Object checkMaterial(HttpSession session,String role);

	/**
	 * 需求上报列表
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:16:10
	 * @param
	 */
	Object suppliesMaterialList(String channel_code, String pageNo, String state, String startTime, String endTime);

	/**
	 * 需求上报统计
	 * @author zhangzhi
	 * @date 2018年5月2日下午10:54:48
	 * @param
	 */
	Object toStoreTotal(String channel_code, String startTime, String endTime);

	
	/**
	 * 读取政策列表
	 * @author zhangzhi
	 * @date 2018年5月3日上午9:48:35
	 * @param
	 */
	Object getPolicyList(String groupId, String userId);

	/**
	 * 政策搜索
	 * @author zhangzhi
	 * @date 2018年5月3日上午11:30:55
	 * @param
	 */
	Object policySearch(String keyword, String groupId, String userId);

	/**
	 * 政策详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param groupId 
	 * @param userId 
	 */
	Object policyDetail(String id, String userId);

	/**
	 * 快递员历史订单统计
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param accountName 快递员账号
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	Object historyListCount(String accountName, String startTime, String endTime);

	/**
	 * 门店配送统计（营销中心）
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	Object storeListCount(String startTime, String endTime);

	/**
	 * 获取所有品牌
	 * @author zhangzhi
	 * @date 2018年5月3日下午3:58:52
	 * @param
	 */
	Object getAllBrand();

	/**
	 * 根据品牌id、门店类型获取所有门店
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param vendor_id 品牌id
	 * @param type 门店类型
	 */
	Object getAllStore(String vendor_id, String type,String mohuchaxun);

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
	Object appendOrRemoveForPaidan(String num, String materialId,String role , String materialName, String store, String operation,
			String predictTime, HttpSession session);


	/**
	 * 查询所有的快递公司
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 */
	Object getAllDeliveryCompany();

	/**
	 * 根据快递公司查询快递员信息
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param company_id 快递公司id
	 * @param select_infor 快递员公司或姓名
	 */
	Object getDeliveryByCompany(String company_id, String select_infor);

	/**
	 * 生成工单
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param deliveryPhones 快递员电话（以“，”隔开）
	 * @param accountName 生成该次订单任务的营销中心人员电话
	 */
	Object addOrder(String deliveryPhones, String accountName,HttpSession session);

	/**
	 * 订单派送
	 * 点击“查看派送地图”时，将订单装维改为4，即订单此时处于派送中
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 */
	Object updateStateWhenDelivery(String id);

	/**
	 * 点击“查看派送地图”时，根据快递员变化和订单编号修改快递员经纬度
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 * @param courier_longitude 快递员经度
	 * @param courier_latitude 快递员纬度
	 * @param courier_Phone 快递员电话
	 */
	Object updateCourierstore(String courier_longitude, String courier_latitude, String courier_Phone,
			String logistics);

	/**
	 * 点击“开始配送时”时，添加快递员经纬度，实时获取快递员经纬度信息
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param logistics 订单id
	 * @param courier_longitude 快递员经度
	 * @param courier_latitude 快递员纬度
	 * @param courier_Phone 快递员电话
	 */
	Object addCourierstore(String courier_longitude, String courier_latitude, String courier_Phone, String logistics);

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
	Object detailedRoute(String store_longitude, String store_latitude, String address,String logistics) throws UnsupportedEncodingException;

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
	Object updateOrder(String id,String signPeople,String signPhone,String recipientTime) throws UnsupportedEncodingException;

	/**
	 * 完成订单后，当订单中的门店位置获取失败时（坐标为0或为空），重新采集门店的经纬度坐标分别对订单表的门店信息表进行更新
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 * @param store_longitude 门店经度
	 * @param store_latitude 门店纬度
	 * @param channel_code 门店编码
	 */
	Object updateStoreLocation(String id, String store_longitude, String store_latitude, String channel_code);

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
	Object uploadImg(String orderImg, String storeImg, String logistics, String signPeople,
			String signPhone, String recipientTime);

	/**
	 * 需求上报详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param channel_code 门店编码
	 * @param id 需求上报id
	 * @param endTime 查询结束时间
	 */
	Object toStoreSuppliesDetails(String channel_code, String id);

	/**
	 * 上传图片到服务器并返回文件路径
	 * @author zhangzhi
	 * @date 2018年5月11日下午5:11:07
	 * @param
	 */
	Object uploadImageForSingle(MultipartFile file);

}
