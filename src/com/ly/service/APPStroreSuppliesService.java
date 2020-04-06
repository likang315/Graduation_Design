package com.ly.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ly.entity.app.APPStoreSupplies;



public interface APPStroreSuppliesService {
	
	public List<Map<String, Object>> findtotal(Map<String, Object> m);
	
	public List<Map<String, Object>> StoreAllTotal(Map<String, Object> m);
	
	public Integer getStoreAllTotalCount(Map<String, Object> m);
	
	public List<Map<String, Object>> findStoreList(Map<String, Object> m);
	
	public Integer getStorelistCount(Map<String, Object> m);
	
	public Map<String, Object> findStoreListDetails(Map<String, Object> m);
	
	public Map<String, Object> findStoreAllDetails(Map<String, Object> m);
   
	 public boolean addmaterial(APPStoreSupplies appStoreSupplies, HttpSession session);

	public List<Map<String, Object>> getMaterials();
	
	public Integer getMaterialsId(String material);
	
	
		
}
