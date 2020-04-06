package com.ly.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.app.AppWxMenu;
import com.ly.entity.app.AppWxMenuShare;
import com.ly.entity.background.Account;
import com.ly.entity.background.Group;
import com.ly.service.AppLoginService;
import com.ly.service.AppMenuService;
import com.ly.util.Common;
import com.ly.util.Md5Tool;

import net.sf.json.JSONObject;

@RequestMapping("/app/login")
@Controller
public class AppLoginController {

	@Autowired
	private AppLoginService appLoginService;
	
	@Autowired
	private AppMenuService appMenuService;
	
	//跳转到登陆页面
	@RequestMapping()
	public String toLoginView(){
		return Common.APP_PATH + "/login";
	}

	@RequestMapping("/checkLogin")
	public String applogin(HttpServletRequest request,Model model,Account account){
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		if("".equals(account.getAccountName()) || account.getAccountName() == null || "".equals(account.getPassword()) || account.getPassword() == null){
			result.put("info","请输入账号和密码");
			return Common.APP_PATH + "/login";
		}
	
		result = appLoginService.login(account);
		if(result.get("type").equals(1)){
			model.addAttribute("userInfo", JSONObject.fromObject(result.get("info")));
			model.addAttribute("managerPhone",result.get("managerPhone"));
			model.addAttribute("managerName",result.get("managerName"));
			Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("groupId", ((Account)result.get("info")).getGroupId());
			parameter.put("vendorId", 0);
			parameter.put("brandId", 0);
			AppWxMenuShare wxMenuS = appMenuService.getWxMenuByAllInfo(parameter);
			AppWxMenu appWxMenu = null;
			if( wxMenuS!= null){
				List<AppWxMenu> LWxMenu =  new ArrayList<AppWxMenu>();
				LWxMenu =  new ArrayList<AppWxMenu>();
				parameter.put("ids",wxMenuS.getIndex());
				LWxMenu = appMenuService.getWxMenuByAll(parameter);
				appWxMenu = LWxMenu.size()>0?LWxMenu.get(0):null;
			}
			
			if(appWxMenu == null)
				model.addAttribute("appIndexUrl","/app/grabSingle.html");
			else{
				model.addAttribute("appIndexUrl",appWxMenu.getAddress());
			}
			return Common.APP_PATH + "/setAccount";
		}
		model.addAttribute("result",result);
		model.addAttribute("userName",account.getAccountName());
		return Common.APP_PATH + "/login";
	}
	
	@RequestMapping("/toRegister")
	public String toRegister(Account account){
		return Common.APP_PATH + "/register";
	}
	
	@RequestMapping("/getSectionName")
	@ResponseBody
	public Object getSectionName(){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Group> list=appLoginService.getSectionName();
		map.put("list", list);
		return map;
	}
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public Object getUserInfo(String accountName){
		Map<String,Object> map=new HashMap<String,Object>();
		List<Account> list=appLoginService.getUserInfo(accountName);
		if(list.size()>0){
			map.put("state", "no");
		}else{
			map.put("state", "ok");
		}
		return map;
		
	}
	
	@RequestMapping("/register")
	public String register(Account account,Model model){
		System.out.println(account.getGroupId());
		//支局id
		/*int idZJ=appLoginService.getZhiJuId(account.getGroupId());
		//
		int idQD=appLoginService.getQuDaoId(idZJ);
		int manager_id=appLoginService.getManager_id(idQD);
		
		account.setManager_id(manager_id);*/
		account.setState("0");
		account.setCreateTime(new Date());
		account.setAuth_flag(1);
		account.setPassword(Md5Tool.getMd5("123456"));
		account.setUser_flag(1);
		int re=appLoginService.add(account);
		return Common.APP_PATH + "/login";
	}
	
	//得到归属快递公司
	@RequestMapping("/getBycompanyId")
	@ResponseBody
	public Object getBycompanyId(String companyId){
		Map<String, Object> parameter = appLoginService.getBycompanyId(companyId);
		return parameter;
	}

}
