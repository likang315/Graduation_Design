package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.util.Common;

@RequestMapping("/app/index")
@Controller
public class AppIndexController {
	
	@RequestMapping()
	public String index(){
		return Common.APP_PATH + "/index";
	}

}
