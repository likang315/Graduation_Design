package com.ly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;


/**
 * 报表开发相关
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-17 09:56
 */
public interface BackReportService{

	/**
	 * 根据时间筛选获取到报表所需要数据
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List getReportDatas(String startTime, String endTime);
	
	
	
}
