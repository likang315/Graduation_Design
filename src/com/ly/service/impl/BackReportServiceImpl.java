package com.ly.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.mapper.BackReportMapper;
import com.ly.service.BackReportService;

/**
 * 报表开发相关
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-17 09:56
 */
@Transactional
@Service("backReportService")
public class BackReportServiceImpl implements BackReportService {
	@Autowired
	private BackReportMapper backReportMapper; 

	/**
	 * 获取报表数据
	 */
	@Override
	public List<Map<String, Object>> getReportDatas(String startTime, String endTime) {
		//如果时间为空，给时间默认赋值查询最近一周的信息
		if(startTime == null){
			endTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() + (1*24*60*60*1000) );
			startTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime() - (7*24*60*60*1000));
		} else {
			try {
				endTime = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyy-MM-dd").parse(endTime).getTime() + (1*24*60*60*1000) );
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		//用于存放最终需要插入临时表数据的list
		List<Map<String, Object>> ls3 = new ArrayList<Map<String, Object>>();
		//生成随机字符串，用来标识本次查询的唯一性
		String createUserStr = UUID.randomUUID().toString();
		//创建存放传参map
		Map<String,Object> m = new HashMap<String,Object>();
		//map中放入开始结束时间
		m.put("startTime", startTime);
		m.put("endTime", endTime);
		//根据时间筛选出渠道信息
		List<Map<String,Object>> ls = backReportMapper.getChannelCode(m);
		//循环遍历根据当前时间查询出来的渠道信息
		for(int i = 0; i < ls.size(); i++){
			String materialContents = new String();
			String materialNumbers = new String();
			//循环遍历当前时间段查询出来的渠道编码信息
			//根据渠道编码查询出渠道的物资信息
			List<Map<String,Object>> ls2 = backReportMapper.getMaterials(startTime,endTime,(String)ls.get(i).get("channel_code"));
			//循环遍历门店的信息物资/数量信息
			for(int j = 0; j < ls2.size(); j++){
				//循环遍历单次配送的物资信息
				String singleMaterialContent =  (String)ls2.get(j).get("materialContent");
				String singleMaterialNumber =  (String)ls2.get(j).get("materialNumber");
				
				if(",".equals(singleMaterialContent.substring(singleMaterialContent.length()-1, singleMaterialContent.length()))){
					materialContents += "," + singleMaterialContent.substring(0, singleMaterialContent.length() -1);
					materialNumbers += "," + singleMaterialNumber.substring(0, singleMaterialNumber.length() -1);
				} else {
					materialContents += "," + singleMaterialContent;
					materialNumbers += "," + singleMaterialNumber;
				}
				 
			}
				
			 String[] arrayMaterialContents = materialContents.split(",");
			 String[] arrayMaterialNumbers = materialNumbers.split(",");
			 for (int j = 0; j < arrayMaterialContents.length; j++) {
				if(StringUtils.isNotEmpty(arrayMaterialContents[j])){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("channel_code", ls.get(i).get("channel_code").toString());
					map.put("store", ls.get(i).get("store").toString());
					map.put("state", ls.get(i).get("state").toString());
					map.put("materialName", arrayMaterialContents[j]);
					map.put("materialNumber", arrayMaterialNumbers[j]);
					//查询人随机字符串，每次查询创建一个唯一的随机字符串，防止查询数据出现冲突
					map.put("createUserStr",createUserStr);
					ls3.add(map);
				}
			}
			}
			if(ls3.size() > 0){
				//将获取到的处理后ls3插入到临时表中
			    if(backReportMapper.insertTemp(ls3) > 0){
			    	//当插入成功后，根据查询随机字符串查询出本次查询的门店信息
			    	List<Map<String,Object>> reportStoreLs = backReportMapper.getStoreInfo(createUserStr);
			    	//遍历查询出来的报表信息
			    	for(int i = 0; i < reportStoreLs.size(); i++){
			    		//获取门店的渠道信息
			    		String channel_code = (String)reportStoreLs.get(i).get("channel_code");
			    		//根据渠道信息查询出来派送次数信息
			    		Map<String,Object> orderNum = backReportMapper.getSendNum(channel_code);
			    		reportStoreLs.get(i).put("doneOrder", orderNum.get("doneOrder")); 
			    		reportStoreLs.get(i).put("OrderAll", orderNum.get("OrderAll"));
			    		//根据渠道信息和查询随机字符串信息查询出物资以及数量信息
			    		List<Map<String,Object>> materialAndNumLs = backReportMapper.getMaterialNum(channel_code,createUserStr);
			    		reportStoreLs.get(i).put("materialAndNumLs",materialAndNumLs);
			    		
			    	}
			    	//清除临时表数据
			    	backReportMapper.deleteTemp(createUserStr);
			    	return reportStoreLs;
			    }
				
			}
			
		
		return null;
	}
}
