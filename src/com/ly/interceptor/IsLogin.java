package com.ly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ly.mapper.AppDispatchMapper;

public class IsLogin implements HandlerInterceptor{

	@Autowired
	private AppDispatchMapper appDispatchMapper;	
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String loginToken = request.getHeader("token");
		System.err.println(loginToken);
		String url = request.getRequestURI();
		//如果是登录页面\忘记密码\获取验证码和验证验证码时放行
		if(url.indexOf("login.html") >= 0 || url.indexOf("getCode.html") >= 0 || url.indexOf("forget.html") >= 0 || url.indexOf("testCode.html") >= 0) {
			return true;
		}
		//如果用户已登录放行
		if(loginToken != null && loginToken != "") {
			//查询用户是否登录
			int num = appDispatchMapper.findLoginToken(loginToken);
			if(num > 0) {
				//app端留痕
				return true;
			} else {
				response.getWriter().write("loginOut");
				return false;
			}
		}else{
			response.getWriter().write("loginOut");
			return false;
		}
		
	}
	
}
