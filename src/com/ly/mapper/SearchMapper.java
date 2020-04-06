package com.ly.mapper;

import java.util.List;
import java.util.Map;

public interface SearchMapper {
	
	List<Map<String,Object>> searchPhoneState(String phone);
}
