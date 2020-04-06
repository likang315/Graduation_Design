package com.ly.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.entity.background.Account;
import com.ly.service.LoginService;
import com.ly.util.Common;
import com.ly.util.Md5Tool;

/**
 * 微信端登录
 * @author wf
 *
 */
@Controller
@RequestMapping("app/login/")
public class LoginController extends BaseController {
	
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("login")
	public String login(){
		return Common.WEB_PATH + "/login";
	}
	
	@RequestMapping("/userLogin")
	public String getLogin(Account account,Model model){
		if(account.getAccountName()==null || "".equals(account.getAccountName())){
			model.addAttribute("info", "用户名不能为空");
			return Common.WEB_PATH+"/login";
		}
		if(account.getPassword()==null || "".equals(account.getPassword())){
			model.addAttribute("info", "密码不能为空");
			model.addAttribute("userInfo", account);
			return Common.WEB_PATH+"/login";
		}
		account.setPassword(Md5Tool.getMd5(account.getPassword()));
		Map<String,Object> ru = loginService.UserLogin(account);
		if(ru.get("state").equals("no")){
			model.addAttribute("userInfo", account);
			model.addAttribute("info", ru.get("info"));
			return Common.WEB_PATH+"/login";
		}else{
			Account account1 = (Account)ru.get("userInfo");
			System.out.println(account1);
			model.addAttribute("accountName", account1.getAccountName());
			return "redirect:broadbandManagement.html";
		}
	}
	
	@RequestMapping("broadbandManagement")
	public String broadbandManagement(String accountName,Model model){
		model.addAttribute("accountName", accountName);
		return Common.WEB_PATH + "/broadbandManagement";
	}
	
}