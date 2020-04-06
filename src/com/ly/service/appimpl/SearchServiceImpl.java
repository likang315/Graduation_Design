package com.ly.service.appimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.mapper.SearchMapper;
import com.ly.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchMapper searchMapper;
	
	public Map<String,Object> searchPhoneState(String phone){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Map<String,Object>> lm = searchMapper.searchPhoneState(phone);
		if(lm == null){
			result.put("state","ok");
			result.put("info","号码正常");
			result.put("type",0);
		}else{
			result.put("state","ok");
			result.put("info", lm);
			result.put("type",1);
		}
		return result;
	}
}
