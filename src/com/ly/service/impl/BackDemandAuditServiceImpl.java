package com.ly.service.impl;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.entity.background.Account;
import com.ly.mapper.BackDemandAuditMapper;
import com.ly.service.BackDemandAuditService;
import com.ly.util.Common;

/**
 * 审核门店上报的需求 Service
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-17 10:05
 */
@Service
public class BackDemandAuditServiceImpl implements BackDemandAuditService {
	
	@Autowired
	private BackDemandAuditMapper backDemandAuditMapper;
	
	@Override
	public Integer getCount(String dataState, String startTime, String endTime) {
		Map<String, Object> parameter = new HashMap<>();
		if("all".equals(dataState)){
			dataState = null;
		}
		parameter.put("dataState", dataState);
		parameter.put("startTime", startTime);
		parameter.put("endTime", endTime);
		return backDemandAuditMapper.getCount(parameter);
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
		return backDemandAuditMapper.getList(parameter);
	}

	/**
	 * 审核结果入库且生成物流订单
	 *
	 * @param checkDatas
	 * @param examine
	 * @param examine_reason
	 * @param request
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateAuditData(String[] checkDatas, String examine, String examine_reason, HttpServletRequest request) {
		boolean flag = false;
		//获取当前登录的信息
		Account ac = (Account)request.getSession().getAttribute("userSession");
		//获取当前登录的用户账号
		String userName = ac.getAccountName();
		//获取当前登录的人的姓名
		String realName =  ac.getReal_name();
		//获取当前用户的groupId
		int groupId =  ac.getGroupId();
		//获取当前时间
		String dateNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

		Map<String, Object> parameter = new HashMap<String, Object>();
		// 门店需求列表ID
		parameter.put("checkDatas", checkDatas);
		parameter.put("examine_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		parameter.put("examine", examine);
		parameter.put("userName", userName);
		parameter.put("realName", realName);
		parameter.put("examine_reason", examine_reason);

		// 审核信息
		Integer result = backDemandAuditMapper.updateAuditData(parameter);
		if(result > 0 && ! "2".equals(examine)){
			// 查询出本次通过审核的需求信息
			List<Map<String,Object>> ls =  backDemandAuditMapper.getPassInfo(parameter);
			//循环补全订单信息
			for(int i = 0; i < ls.size(); i++){
				//验证当前订单是否有效
				if(ls.get(i).get("store_name") == null){
					ls.remove(i);
					continue;
				}
				//生成订单号
				ls.get(i).put("id", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + Common.getRandom(3, true, false, false, false, 1).get(0));
				//发货人姓名
				ls.get(i).put("shipperName", realName);
				//发货人电话
				ls.get(i).put("shipper", userName);
				//发货时间
				//ls.get(i).put("shipTime", dateNow);
				//发货人的组织id
				ls.get(i).put("groupId", groupId);
				//订单创建时间
				ls.get(i).put("createTime", dateNow);
			}
			// 将循环补充完善的订单信息插入到数据库
			if(ls.size() > 0){
				if (backDemandAuditMapper.addOrderInfo(ls) > 0) {
					flag = true;
				} else {
					flag = false;
				}
			} else {
				flag = false;
			}

		}

		return flag;
	}

	@Override
	public void exportData(String startTime, String endTime, HttpServletResponse response) {
		Map<String, Object> paramter = new HashMap<String, Object>();
		paramter.put("startTime", startTime);
		paramter.put("endTime", endTime);
		List<Map<String, Object>> auditSuccessDatas = backDemandAuditMapper.getByState(paramter);
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
