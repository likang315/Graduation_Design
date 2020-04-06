package com.ly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ly.entity.ActivityInfo;
import com.ly.entity.app.APPCourierStore;
import com.ly.entity.app.APPStoreSupplies;
import com.ly.entity.app.Store;
import com.ly.entity.app.Vendor;

/**
 * app端接口类
 * @author admin
 * @date 2018年4月20日 上午10:23:01
 * @described
 */
public interface AppDispatchMapper {

	/**
	 * 登陆接口校验用户是否存在
	 * @author zhangzhi
	 * @date 2018年4月20日上午10:44:44
	 * @param
	 */
	Map<String, Object> checkLogin(@Param("accountName") String accountName,@Param("password") String password);

	/**
	 * 用户登录验证通过之后根据用户登录账号修改token
	 * @author zhangzhi
	 * @date 2018年4月20日上午11:03:18
	 * @param
	 */
	void updateLoginToken(@Param("accountName") String accountName, @Param("token") String token);

	/**
	 * 查询账号是否存在
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:11:40
	 * @param
	 */
	String findUserIsExist(@Param("accountName")String accountName);

	/**
	 * 密码重置
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:21:05
	 * @param
	 */
	void updatePassword(@Param("userId")String userId, @Param("password")String password);

	/**
	 * 获取用户信息
	 * @author zhangzhi
	 * @date 2018年4月20日下午3:42:34
	 * @param
	 */
	Map<String, Object> getUserInforByAccountname(@Param("accountName")String accountName);

	/**
	 * 获取订单总数
	 * @author zhangzhi
	 * @date 2018年4月23日上午9:44:13
	 * @param
	 */
	int getOrderCount(Map<String, Object> parame);

	/**
	 * 获取订单列表
	 * @author zhangzhi
	 * @date 2018年4月23日上午9:44:29
	 * @param
	 */
	List<Map<String, Object>> getOrderList(Map<String, Object> parame);

	/**
	 * 订单详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:19:14
	 * @param id 订单编号
	 * @param channel_code 收货门店渠道编码
	 */
	Map<String, Object> storelistDetails(Map<String, Object> m);

	/**
	 * 获取物资名称
	 * @author zhangzhi
	 * @date 2018年5月2日下午2:45:49
	 * @param
	 */
	List<Map<String, Object>> getMaterials();

	/**
	 * 物资添加
	 * @author zhangzhi
	 * @date 2018年5月2日下午4:05:34
	 * @param
	 */
	void addmaterial(APPStoreSupplies appStoreSupplies);

	/**
	 * 获取需求上报总数据
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:26:28
	 * @param
	 */
	Integer suppliesMaterialCount(Map<String, Object> m);

	/**
	 * 分页获取需求列表数据
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:26:58
	 * @param
	 */
	List<Map<String, Object>> suppliesMaterialList(Map<String, Object> m);

	/**
	 * 需求上报 统计
	 * @author zhangzhi
	 * @date 2018年5月2日下午11:01:26
	 * @param
	 */
	List<Map<String, Object>> findtotal(Map<String, Object> m);

	/**
	 * 读取政策列表
	 * @author zhangzhi
	 * @date 2018年5月3日上午9:54:10
	 * @param
	 */
	List<ActivityInfo> getPolicyList(Map<String, Object> map);

	/**
	 * 快递员历史订单统计
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param accountName 快递员账号
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	List<Map<String, Object>> historyListCount(Map<String, Object> m);

	/**
	 * 门店配送统计（营销中心）
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param startTime 查询开始时间
	 * @param endTime 查询结束时间
	 */
	List<Map<String, Object>> storeListCount(@Param("startTime")String startTime, @Param("endTime")String endTime);

	/**
	 * 加载品牌信息
	 * @author zhangzhi
	 * @date 2018年5月3日下午4:01:41
	 * @param
	 */
	List<Map<String, Object>> getVendor();

	/**
	 * 根据品牌id、门店类型获取所有门店
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param vendor_id 品牌id
	 * @param type 门店类型
	 */
	List<Store> getAllStore(@Param("vendor_id")String vendor_id,@Param("type") String type,@Param("mohuchaxun")String mohuchaxun);

	/**
	 * 查询快递公司
	 * @author zhangzhi
	 * @date 2018年5月3日下午5:45:12
	 * @pMaparam
	 */
	List<Map<String, Object>> getAllDeliveryCompany();

	/**
	 * 根据快递公司、快递员姓名电话查找快递员信息
	 * @author zhangzhi
	 * @date 2018年5月3日下午6:06:39
	 * @param
	 */
	List<Map<String, Object>> getDeliveryByCompany(@Param("company_id")String ids,@Param("select_info") String select_info);

	/**
	 * 点击“查看派送地图”时，将订单装维改为4，即订单此时处于派送中
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 */
	int updateStateWhenDelivery(@Param("id")String id);

	/**
	 * 点击“查看派送地图”时，修改快递员经纬度
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param id 订单id
	 */
	int updateCourierstore(Map<String, Object> m);

	/**
	 * 添加快递员的位置坐标
	 * @author zhangzhi
	 * @date 2018年5月3日下午8:00:37
	 * @param
	 */
	int addCouriersLocation(APPCourierStore aPPCourierStore);

	/**
	 * 修改订单的邮寄预计时长
	 * @author zhangzhi
	 * @date 2018年5月3日下午8:01:06
	 * @param
	 */
	int updatePredictTime(Map<String, Object> m);

	/**
	 * 通过订单id查询订单信息
	 * @author zhangzhi
	 * @date 2018年5月3日下午8:21:03
	 * @param
	 */
	Map<String, Object> findOrderById(@Param("logistics")String logistics);

	/**
	 * 修改订单状态
	 * @author zhangzhi
	 * @date 2018年5月3日下午11:21:28
	 * @param
	 */
	int updateOrder(Map<String, Object> m);

	/**
	 * 根工单id修改工单表中的门店经纬度信息
	 * @author zhangzhi
	 * @date 2018年5月4日上午9:48:26
	 * @param
	 */
	int updateStoreLocationInMail(Map<String, Object> m);

	/**
	 * 根据门店编码修改门店信息表中的经纬度信息
	 * @author zhangzhi
	 * @date 2018年5月4日上午9:48:44
	 * @param
	 */
	int updateStoreLocationInStore(Map<String, Object> m);

	/**
	 * 维护图片信息
	 * @author zhangzhi
	 * @date 2018年5月4日下午2:26:40
	 * @param
	 */
	int addImgForOrder(Map<String, Object> m);

	/**
	 * 令牌验证登录
	 * @author zhangzhi
	 * @date 2018年5月4日下午5:15:54
	 * @param
	 */
	int findLoginToken(String loginToken);

	/**
	 * 需求上报详情
	 * @author zhangzhi
	 * @date 2018年5月2日下午5:15:52
	 * @param channel_code 门店编码
	 * @param id 需求上报id
	 * @param endTime 查询结束时间
	 */
	Map<String, Object> toStoreSuppliesDetails(Map<String, Object> m);

}
