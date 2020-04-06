package com.ly.service.impl;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.entity.SmsSend;
import com.ly.mapper.BackDemandAuditOneMapper;
import com.ly.service.BackDemandAuditOneService;

@Service
public class BackDemandAuditOneServiceImpl implements BackDemandAuditOneService {
	
	@Autowired
	private BackDemandAuditOneMapper backDemandAuditOneMapper;
	
	@Override
	public Integer getCount(String dataState, String startTime, String endTime) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		if("all".equals(dataState)){
			dataState = null;
		}
		parameter.put("dataState", dataState);
		parameter.put("startTime", startTime);
		parameter.put("endTime", endTime);
		return backDemandAuditOneMapper.getCount(parameter);
	}
	
	@Override
	public List<Map<String, Object>> getList(String dataState, String startTime, String endTime, int start, int size) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		if("all".equals(dataState)){
			dataState = null;
		}
		parameter.put("dataState", dataState);
		parameter.put("startTime", startTime);
		parameter.put("endTime", endTime);
		parameter.put("start", start);
		parameter.put("size", size);
		return backDemandAuditOneMapper.getList(parameter);
	}

	@Override
	public boolean updateAuditData(String[] checkDatas, String examine, String examine_reason,String userName,String realName) {
		boolean flag = false;
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("checkDatas", checkDatas);
		parameter.put("examine_one_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		parameter.put("examine", examine);
		parameter.put("examine_reason", examine_reason);
		parameter.put("userName", userName);
		parameter.put("realName", realName);
		Integer result = backDemandAuditOneMapper.updateAuditData(parameter);
		if(result > 0){
			flag = true;
		}
		
		/*//审核驳回给该门店发送短信回馈
		if("4".equals(examine)){
			List<Map<String, Object>> storeUserPhones = backDemandAuditOneMapper.getById(checkDatas);
			for (Map<String, Object> map : storeUserPhones) {
				SmsSend send = new SmsSend();
				String msg = "您需求上报的信息已被审核,请您尽快前往系统查看结果";
				send.sendSms(map.get("store_shopowner_phone").toString(), msg, null);
			}
		}*/
		return flag;
	}

	@Override
	public void exportData(String startTime, String endTime, HttpServletResponse response) {
		Map<String, Object> paramter = new HashMap<String, Object>();
		paramter.put("startTime", startTime);
		paramter.put("endTime", endTime);
		List<Map<String, Object>> auditSuccessDatas = backDemandAuditOneMapper.getByState(paramter);
		String sheetNames;
		if(StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
			sheetNames = startTime+"到"+endTime+"需求上报审核成功清单";
		} else {
			sheetNames = "需求上报审核成功清单";
		}
		String[] titles = {"门店名称","门店渠道编码","上报人电话","上报人姓名","物资名称","物资数量","上报时间","审核时间","扩展需求"};
		String[] keys = {"store_name","channel_code","store_shopowner_phone","store_shopowner_name","materialContent","materialNumber","report_time","examine_time","expanding_demand"};
		exportExcel(auditSuccessDatas, sheetNames, titles, sheetNames, response, keys);
		
	}
	
	//公用 单sheet.
	public void exportExcel(List<Map<String, Object>> list, String sheetNames, String[] titles, String fileName, 
			HttpServletResponse response, String[] keys) {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetNames);
		sheet.setDefaultColumnWidth(20);
		Row row = sheet.createRow(0);
		for (int q = 0; q < titles.length; q++) {
			row.createCell(q).setCellValue(titles[q]);
		}
		for (int w = 0; w < list.size(); w++) {
			Row contentRow = sheet.createRow(w + 1);
			Map<String, Object> map = list.get(w);
			for (int j = 0; j < keys.length; j++) {
				if (map.get(keys[j]) == null || ("").equals(map.get(keys[j]))) {
					map.put(keys[j], "x");
				}
				contentRow.createCell(j).setCellValue(map.get(keys[j]).toString());
			}
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		try {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			response.addHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".xls");
			OutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//给exce设置样式
	public HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		//设置居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		//创建字体 设置字体加粗
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints(fontSize);
		//这里自己出错了
		style.setFont(font);
		return style;
	}
}
