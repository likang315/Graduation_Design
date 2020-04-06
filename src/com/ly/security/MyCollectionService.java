package com.ly.security;

import java.util.List;
import java.util.Map;

public interface MyCollectionService {
	
	boolean addMyCollection(Map<String,Object> pamar);
	
	List<Map<String,Object>> selectAllCollertion(String accountName);
	
	boolean delInfo(String id);
}
