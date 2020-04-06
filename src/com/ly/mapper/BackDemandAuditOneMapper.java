package com.ly.mapper;

import java.util.List;
import java.util.Map;

public interface BackDemandAuditOneMapper {
	Integer getCount(Map<String, Object> parameter);

	List<Map<String, Object>> getList(Map<String, Object> parameter);

	Integer updateAuditData(Map<String, Object> parameter);

	List<Map<String, Object>> getById(String[] checkDatas);

	List<Map<String, Object>> getByState(Map<String, Object> paramter);
}
