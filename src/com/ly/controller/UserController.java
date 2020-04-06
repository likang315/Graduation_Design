package com.ly.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ly.entity.SmsSend;
import com.ly.entity.app.Smslog;
import com.ly.entity.background.Account;
import com.ly.entity.background.SmsSendlog;
import com.ly.mapper.SmslogMapper;
import com.ly.service.AccountService;
import com.ly.service.AppUserService;
import com.ly.service.SmslogService;
import com.ly.util.Common;
import com.ly.util.PropertiesUtils;

@Controller
@RequestMapping("/app/user/")
public class UserController extends AppBaseController {
	
	@Autowired
	public AppUserService appUserService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private SmslogService smslogService;
	
	@Autowired
	private SmslogMapper smslogMapper;
	
	//跳转到忘记密码页面
	@RequestMapping("forgetPassword")
	public String toForgetPasswordView(){
		return Common.APP_PATH + "/forgetPassword";
	}

	/**
	 * 判断手机号是否注册
	 */
	@RequestMapping("isTellPhone")
	@ResponseBody
	public Object isTellPhone(String accountName){
		if(appUserService.validatePhoneNum(accountName)){
			setResult(Common.OK);
			result.put("info","此手机号已经注册，请勿重复注册！");
		}else{
			setResult(Common.NO);
			result.put("info","此手机号尚未注册，可以注册！");
		}
		return result;
	}
	
	//获取6位验证码
	@RequestMapping("getCode")
	@ResponseBody
	public Object getCode(String tellPhone,HttpServletRequest request){
		return getCode(tellPhone,6,request);
	}
	
	public Object getCode(String tellPhone,int num,HttpServletRequest request){
		String ip="";
		 if(request.getHeader("x-forwarded-for") == null) { 
			 ip = request.getRemoteAddr(); 
		 }else{ 
		     ip = request.getHeader("x-forwarded-for");  
		 }
		Random rand = new Random();
		String code = "";
		//参生六位数验证码
		for(int i = 0;i<num;i++){
			code += rand.nextInt(10);
		}
		SmsSend ss = new SmsSend();
		PropertiesUtils.findPropertiesKey("whtsufix");
		ss.sendSmsCustomer(tellPhone, "验证码："+code+"【马卡鲁科技】", null);//发送验证码给对应的用户
		SmsSendlog smsSendlog = new SmsSendlog();
 		smsSendlog.setPhone(tellPhone);
		smsSendlog.setSuccess("Y");
		smsSendlog.setContent("您的验证码是："+code);
		smsSendlog.setRemoteAddr(ip);
		//将验证码写入日志
		smslogService.addSmsSendLog(smsSendlog);
		
		Smslog smslog = new Smslog();
		smslog.setCode(code);
		smslog.setMobile(tellPhone);
		smslog.setDeviceId("866288029363359");
		smslog.setMsg("您的验证码是："+code);
		smslog.setCreatetime( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
		try {
			smslogMapper.add(smslog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResult(Common.OK);
		result.put("info","验证码发送成功！");
		//result.put("code", code);
		System.out.println("============================================="+result.get("info"));
		System.out.println(code);
		return result;
	}
	
	@RequestMapping("testCode")
	@ResponseBody
	public Object testCode(String tellPhone,String code){
		if(accountService.testCode(tellPhone, code)){
			setResult(Common.OK);
			result.put("info","验证码正确！");
			System.out.println("验证码正确");
		}else{
			setResult(Common.NO);
			result.put("info","验证码错误！");
		}
		return result;
	}
	

	@RequestMapping("testCode2")
	@ResponseBody
	public Object testCode2(String tellPhone,String code){
		if(accountService.testCode(tellPhone, code)){
			setResult(Common.OK);
			result.put("info","验证码正确！");
			System.out.println("验证码正确");
			if(accountService.testCode2(tellPhone, code)){
				setResult(Common.OK);
				result.put("info","验证码正确！");
				System.out.println("验证码正确");
			}else{
				setResult(Common.NO);
				System.out.println("验证码已过期！请重新获取");
				result.put("info","验证码已过期！请重新获取");
			}
		} else {
			setResult(Common.NO);
			result.put("info","验证码错误！");
		}
		return result;
	}
	
	@RequestMapping("updatePassword")
	@ResponseBody
	public Object updatePassword(String tellPhone,String password){
		if(accountService.updatePassword(tellPhone, password)){
			setResult(Common.OK);
			result.put("info","修改成功");
		}else{
			setResult(Common.NO);
			result.put("info","账号不存在");
		}
		return result;
	}
	
	//跳转到修改密码页面
	@RequestMapping("/toUpdatePasswordView")
	public String toUpdatePasswordView(){
		return Common.APP_PATH + "/updatePassword";
	}
	
	//修改密码
	@RequestMapping("changepassword")
	@ResponseBody
	public Object changePassword(String username,String password,String password1,String password2){
		result.clear();
		if(!(password1.equals(password2))){
			setResult(Common.NO);
			return result;
		}
		Account user = this.appUserService.login(username,password);
		if(user == null){
			setResult(Common.NO);
		}else{
			Account user1 = this.appUserService.changePassword(username,password1);
			if(user1 == null){
				setResult(Common.NO);
			}else{
				setResult(Common.OK);
				result.put("user", user1);
			}
		}
		return result;
	}
}
