package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.util.Common;

/**
 * 微信端个人设置
 * @author wf
 *
 */
@RequestMapping("/app/setup")
@Controller
public class AppSetUpController {

	@RequestMapping()
	public String setUP(){
		return Common.APP_PATH + "/setUp";
	}
}
