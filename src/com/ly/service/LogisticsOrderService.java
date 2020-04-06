package com.ly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.app.APPCourierStore;
import com.ly.entity.app.MailInformation;

public interface LogisticsOrderService {
   
	// 查询列表
	public List<Map<String, Object>> findOrderlist(Map<String, Object> map);
	
	//查询快递员信息为空的列表
	public List<Map<String, Object>> findOrderOfNulllist(Map<String, Object> map);
	
	public int findOrdercount(Map<String, Object> m);
	
	public Integer findOrderOfNullNum();
	 
	// 根据电话搜索信息
	
     public List<Map<String, Object>> getSearchPhone(Map<String, Object> map);
     
     public Map<String, Object> getDisplayMap(Map<String, Object> map);
     
     public Map<String, Object> getDisplayMap1(Map<String, Object> map);
     
     
	
	public int getSearchCount(Map<String, Object> m);
	
	//批量删除
	public boolean deleteLogistics(Map<String, Object> map);

	//批量删除
	public boolean distribution(Map<String, Object> map);
	
    public Map<String, Object> findByLogistics(String id);

	
	//修改订单信息
	public String updateLogistics(Map m);
		
		
	//下载清单(配送成功）
	public List<Map<String, Object>> findlogisticslist();
				
				
	//订单维护
	public Map addOrder(MultipartFile file, HttpServletRequest request);
	
	//判断快递员信息是否存在
	public String checkcourierPhoneService(String phone);
	
	//获取快递公司的信息
	public List<Map<String,Object>> getCompany();
	
	//根据公司id查询出快递员信息
	List<Map<String,Object>> getCourierLs(String[] companyId);
	//派送订单
	Map<String,Object> sendOrder(String orderName, String[] seleCourier);
		
}
