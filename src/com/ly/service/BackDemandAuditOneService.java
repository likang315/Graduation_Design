package com.ly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface BackDemandAuditOneService {
	Integer getCount(String dataState, String startTime, String endTime);

	List<Map<String, Object>> getList(String dataState, String startTime, String endTime, int start, int size);

	boolean updateAuditData(String[] checkDatas, String examine, String examine_reason,String userName,String realName);

	void exportData(String startTime, String endTime, HttpServletResponse response);
}
