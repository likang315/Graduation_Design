package com.ly.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.ly.entity.app.APPCourierStore;

public interface APPCoordinateMapper {
	
	public List<Map<String, Object>> findIncomplete(String courierPhone);
	
	public Map<String, Object> findbyList(String logistics);
	
	public Map<String,	Object> findStore(Map<String, Object> m);
	
	//验证验证码是否正确
		public List<com.ly.entity.app.Smslog> testCode(String tellPhone,String code);
		
		public List<Map<String,String>> testCode2(String tellPhone, String code, String time);
		
		
		public int updateOrder(Map<String, Object> m);
		
		public int updateState(String id);
		
		public int addCourierstore(APPCourierStore appCourierStore);
		
		  public int addCourierTime(Map<String, Object> m);
		
		public int updateCourierstore(Map<String, Object> m);
		
		public List<Map<String, Object>> findtotal(Map<String, Object> m);
		
		public List<Map<String, Object>> CourierAllTotal(Map<String, Object> m);
		
		public Integer getCourierlistCount(Map<String, Object> m);
		
		public int addStoremail(Map<String, Object> m);
		  
		  public int addStoreinfo(Map<String, Object> m);
		  
		  //
		  public Integer addImgForOrder(Map<String, Object> m);
}
