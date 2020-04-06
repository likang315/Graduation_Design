package com.ly.mapper;

import java.util.List;
import java.util.Map;

public interface BackDemandAuditMapper {
	Integer getCount(Map<String, Object> parameter);

	List<Map<String, Object>> getList(Map<String, Object> parameter);

	Integer updateAuditData(Map<String, Object> parameter);

	List<Map<String, Object>> getById(String[] checkDatas);

	List<Map<String, Object>> getByState(Map<String, Object> paramter);
	//查询出本次通过审核的信息
	List<Map<String,Object>> getPassInfo(Map<String, Object> paramter);
	//插入订单表中
	Integer addOrderInfo(List<Map<String,Object>> ls);
}
