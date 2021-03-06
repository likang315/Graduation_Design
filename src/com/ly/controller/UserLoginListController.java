package com.ly.controller;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.background.Resources;
import com.ly.entity.background.UserLogin;
import com.ly.pulgin.mybatis.plugin.PageView;
import com.ly.service.UserLoginService;
import com.ly.util.Common;


@Controller
@RequestMapping("/background/userLoginList/")
public class UserLoginListController extends BaseController{
	@Inject
	private UserLoginService userLoginService;
	
	/**
	 * @param model
	 * 存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String list(Model model, Resources menu, String pageNow) {
		return Common.BACKGROUND_PATH+"/userLoginList/list";
	}
	
	@ResponseBody
	@RequestMapping("queryList")
	public PageView query(UserLogin userLogin,String pageNow,String pagesize) {
		pageView = userLoginService.query(getPageView(pageNow,pagesize), userLogin);
		return pageView;
	}
	
}