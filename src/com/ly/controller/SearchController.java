package com.ly.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.service.SearchService;
import com.ly.util.Common;

/**
 * 微信端搜索
 * @author wf
 *
 */
@RequestMapping("/app/search")
@Controller
public class SearchController {

	@Autowired
	private SearchService SearchService;
	
	@RequestMapping()
	public String search(){
		return Common.APP_PATH + "/search";
	}
	
	@RequestMapping("/phone")
	@ResponseBody
	public Object search(String phone){
		Map<String,Object> result = SearchService.searchPhoneState(phone);
		return result;
	}
}
