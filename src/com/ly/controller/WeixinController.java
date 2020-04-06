package com.ly.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.app.WeixinBinding;
import com.ly.entity.background.Account;
import com.ly.entity.background.UserLogin;
import com.ly.service.WeixinService;
import com.ly.util.Common;
import com.ly.util.Md5Tool;

@Controller
@RequestMapping("app/weixin/")
public class WeixinController {

	
	@Autowired
	private WeixinService weixinService;
	
	@RequestMapping("weChatBinding")
	public String weChatBinding(){
		return Common.APP_PATH + "/weChatBinding";
	}
	
	@RequestMapping("weChatBindingLoginAgain")
	public String login2(Model model,String isflag,String openid,String nickname){
		model.addAttribute("isflag", isflag);
		model.addAttribute("openid", openid);
		model.addAttribute("nickname", nickname);
		return Common.APP_PATH + "/weChatBindingLoginAgain";
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Object find(HttpServletRequest request,String userPhone,String uuid,String nickname,String openIds){
		WeixinBinding WeixinBindingInfo = weixinService.getWeixinInfoUserTell(userPhone);
		Map<String ,Object> result = new HashMap<String,Object>();
		
		if(WeixinBindingInfo != null ){
			/*try {
				WeixinBindingInfo.setNickname(URLDecoder.decode(WeixinBindingInfo.getNickname(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} */
			result.put("state","ok");
			result.put("info", WeixinBindingInfo);
		}else{
			result.put("state","no");
			result.put("info","您还没有绑定微信号，请尽快绑定。绑定后请前往办理记录中查看，并联系管理员。");
			System.err.println("您还没有绑定微信号，请尽快绑定。绑定后请前往办理记录中查看，并联系管理员。");
		}
		return result;
	}
	/**
	 * 重写登录 增加微信用户信息
	 * @param username
	 * @param password
	 * @param token
	 * @return
	 */
	@RequestMapping("loginAgain")
	@ResponseBody
	public Object loginModify(String username,String password,String token,String openid,String nickname,String flag,HttpServletRequest request){
		password=Md5Tool.getMd5(password);
		List<Account> list = weixinService.loginAgainInWeixinBinDing(username, password);
		
		int re=0;
		Map<String,Object> map =new HashMap<String,Object>();
		
		if(list.size() < 1){
			map.put("info", "您所输入的密码错误！");
			map.put("state", "no");
		}else if(list.size() > 0 && list.get(0).getAuth_flag() == 1){
			map.put("info", "您好，你的账号正在审核中，请耐心等待...");
			map.put("state", "no");
		}else if(list.size() > 0 && list.get(0).getAuth_flag() == 2){
			String ip = Common.toIpAddr(request);
			UserLogin userLogin = new UserLogin();
			userLogin.setUserId(list.get(0).getId());
			userLogin.setUserName(list.get(0).getAccountName());
			userLogin.setloginIP(ip);
			try {
				weixinService.delete(username);
				WeixinBinding weixinBinding=new WeixinBinding();
				weixinBinding.setOpenid(openid);
				weixinBinding.setUserPhone(list.get(0).getAccountName());
				weixinBinding.setUserName(list.get(0).getReal_name());
				weixinBinding.setNickname(filterEmoji(nickname));
				re=weixinService.add(weixinBinding);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(re>0){
			this.weixinService.updateLogin(username,token);
			map.put("state", "ok");
		}else{
			map.put("state", "no");
		}
		return map;		
	}
	
	public static String filterEmoji(String source) {  
		  if (source != null && source.length() > 0) {  
		   return source.replaceAll("[\ud800\udc00-\udbff\udfff\ud800-\udfff]", "");  
		  } else {  
		   return source;  
		  }  
	}
}
