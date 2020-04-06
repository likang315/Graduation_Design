package com.ly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 报表
 * @author 殷瑜泰 2017年3月30日下午2:43:41
 *
 */
public interface BackReportMapper{
	//获取订单发货状态
	List<Map<String,Object>> getOrder(Map m);
	
	//根据传入的时间段查询派送完成了的渠道编码信息
		List<Map<String,Object>> getChannelCode(Map m);
		//根据渠道编码查询出渠道的物资信息
		List<Map<String,Object>> getMaterials(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("channel_code") String channel_code);
		
		//将获取到的信息插入到临时表中
		Integer insertTemp(List<Map<String,Object>> ls);
		
		//从插入的临时表中获取到门店信息
		List<Map<String,Object>> getStoreInfo(@Param("createUserStr") String createUserStr);
		
		//查询出门店派送信息
		Map<String,Object> getSendNum(@Param("channel_code")String channel_code);
		//查询出门店当前时间段的物资和数量信息
		List<Map<String,Object>> getMaterialNum(@Param("channel_code")String channel_code, @Param("createUserStr") String createUserStr);
		
		//删除临时表数据
		Integer deleteTemp(@Param("createUserStr") String createUserStr);
	
}
