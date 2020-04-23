package com.ly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.util.Common;

/**
 * app 个人设置
 *
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-23 10:45
 */
@RequestMapping("/app/setup")
@Controller
public class AppSetUpController {

	@RequestMapping()
	public String setUP(){
		return Common.APP_PATH + "/setUp";
	}
}
