package com.ly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 报表开发相关
 * @author 殷瑜泰 2017年3月30日下午2:34:29
 *
 */
public interface BackReportService{
	//订单报表数据
	List<Map<String,Object>> getOrder(Map m);
	
	
	//根据时间筛选获取到报表所需要数据
	List getReportDatas(String startTime, String endTime);
	
	
	
}
