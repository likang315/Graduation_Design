package com.ly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ly.entity.app.MailInformation;
import com.ly.entity.app.Store;
import com.ly.entity.app.Vendor;

/**
 * 管理员-mapper接口
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-01-04 09:23
 */
public interface AppFillingSingleMapper {

	List<Vendor> getVendor();

	List<Map<String, Object>> getMaterial();

	List<Store> getStoreById(Map<String, Object> m);

	List<Store> getStoreByMohuchaxun(Map<String ,Object> map);

	List<Store> getStoreAll();

	Integer putInMaterialInfo(List<Map<String, Object>> orders);

	List<MailInformation> getMailInfor(String accountName);

	List<Map<String, Object>> getCompany();

	List<Map<String, Object>> getCourier(Map<String, Object> m);

	List<Map<String, Object>> getCourierTaskCount(String[] courierGroupByBacks);

	Map<String, Object> getStoreByNumber(String storeCodes);

	List<Map<String, Object>> findByList(Map<String, Object> m);

	Map<String, Object> getOrderDetail(String orderNumber);

	List<Map<String, Object>> getMarketingOrderTotal(Map<String, Object> parameter);

	Integer getByListCount(Map<String, Object> m);

	/**
	 * 根据物资名称获取物资的id
	 */
	Integer getMaterialsId(@Param("material") String material);

	/**
	 * 根据传入的时间段查询派送完成了的渠道编码信息
	 *
	 * @param m
	 * @return
	 */
	List<Map<String,Object>> getChannelCode(Map m);

	/**
	 * 根据渠道编码查询出渠道的物资信息
	 *
	 * @param startTime
	 * @param endTime
	 * @param channel_code
	 * @return
	 */
	List<Map<String,Object>> getMaterials(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("channel_code") String channel_code);

	/**
	 * 将获取到的信息插入到临时表中
	 *
	 * @param ls
	 * @return
	 */
	Integer insertTemp(List<Map<String,Object>> ls);

	/**
	 * 从插入的临时表中获取到门店信息
	 *
	 * @param createUserStr
	 * @return
	 */
	List<Map<String,Object>> getStoreInfo(@Param("createUserStr") String createUserStr);

	/**
	 * 查询出门店派送信息
	 *
	 * @param channel_code
	 * @return
	 */
	Map<String,Object> getSendNum(@Param("channel_code")String channel_code);

	/**
	 * 查询出门店当前时间段的物资和数量信息
	 *
	 * @param channel_code
	 * @param createUserStr
	 * @return
	 */
	List<Map<String,Object>> getMaterialNum(@Param("channel_code")String channel_code, @Param("createUserStr") String createUserStr);

	/**
	 * 删除临时表数据
	 *
	 * @param createUserStr
	 * @return
	 */
	Integer deleteTemp(@Param("createUserStr") String createUserStr);
	
}
