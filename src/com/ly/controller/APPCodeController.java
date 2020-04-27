package com.ly.controller;

import com.ly.entity.app.Smslog;
import com.ly.entity.background.SmsSendlog;
import com.ly.mapper.SmslogMapper;
import com.ly.service.APPCoordinateService;
import com.ly.service.SmslogService;
import com.ly.util.Common;
import com.ly.util.PropertiesUtils;
import com.ly.util.SmsSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author kangkang.li@qunar.com
 * @Date 2020-04-27 22:43
 */
@Controller
@RequestMapping("/app/code")
public class APPCodeController extends AppBaseController {
	
	@Autowired
	private SmslogService smslogService;
	@Autowired
	private APPCoordinateService  appCoordinateService;
	@Autowired
	private SmslogMapper smslogMapper;


	/**
	 * 获取6位验证码
	 */
	@RequestMapping(value="/getCode")
	@ResponseBody
	public Object getCode(String tellPhone,HttpServletRequest request){
		return getCode(tellPhone,6,request);
	}


	/**
	 * 获取3位验证码
	 *
	 * @param tellPhone
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getCode2")
	@ResponseBody
	public Object getCode2(String tellPhone,HttpServletRequest request){
		return getCode(tellPhone,3,request);
	}
	
	
	@RequestMapping(value="/testCode")
	@ResponseBody
	public Object testCode(String tellPhone,String code){
		if(appCoordinateService.testCode(tellPhone, code)){
			setResult(Common.OK);
			result.put("info","验证码正确！");
			System.out.println("验证码正确");
		}else{
			setResult(Common.NO);
			result.put("info","验证码错误！");
		}
		return result;
	}
	

	@RequestMapping(value="/testCode2")
	@ResponseBody
	public Object testCode2(String tellPhone,String code){
		if(appCoordinateService.testCode(tellPhone, code)){
			setResult(Common.OK);
			result.put("info","验证码正确！");
			System.out.println("验证码正确");
			if(appCoordinateService.testCode2(tellPhone, code)){
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
		ss.sendSmsCustomer(tellPhone, "验证码："+code+"【XUPT】");//发送验证码给对应的用户
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
		smslog.setCreatetime( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format( new Date()));
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

	

}

