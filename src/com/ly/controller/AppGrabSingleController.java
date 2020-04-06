package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.util.Common;

/**
 * 派单地图
 * @author wf
 *
 */
@RequestMapping("/app/grabSingle")
@Controller
public class AppGrabSingleController {
	
	@RequestMapping()
	public String grabSingle(){
		return Common.APP_PATH + "/grabSingle";
	}
}
