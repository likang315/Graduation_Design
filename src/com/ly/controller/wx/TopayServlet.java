package com.ly.controller.wx;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ly.controller.wx.util.CommonUtil;

import net.sf.json.JSONObject;


@WebServlet(urlPatterns="/app/TopayServlet")
public class TopayServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String code = request.getParameter("code");
		
		String appid ="wx18a4c6de2f98bf87";
		String appsecret ="76808217f2eaf276f4eaf807c9dbbd50";
				
		String openId ="";
		//使用code换取access_token(网页授权接口调用凭证),换取网页授权access_token页面的构造方式：
		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
		//　错误时微信会返回JSON数据包如下（示例为Code无效错误）:{"errcode":40029,"errmsg":"invalid code"}
		JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
		if (null != jsonObject) {
			openId = jsonObject.getString("openid");
		}
		
		String frashtoken="https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+appid+"&grant_type=refresh_token&refresh_token="+jsonObject.getString("refresh_token");;
		JSONObject frashjson = CommonUtil.httpsRequest(frashtoken, "GET", null);
		String access_token="";
		if(frashjson!=null){
			access_token=frashjson.getString("access_token");
		}
		
		String userInfo="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openId+"&lang=zh_CN";
		JSONObject userJson = CommonUtil.httpsRequest(userInfo, "GET", null);
		String openIds="";
		String nickname="";
		if(userJson!=null){
			openIds=userJson.getString("openid");
			nickname=userJson.getString("nickname");
		}
		String path=request.getContextPath();
		String newnickname = URLEncoder.encode(nickname, "utf-8");
		System.err.println(path+"/app/weixin/weChatBindingLoginAgain.html?isflag=1&openid="+openIds+"&nickname="+newnickname);
		response.sendRedirect(path+"/app/weixin/weChatBindingLoginAgain.html?isflag=1&openid="+openIds+"&nickname="+newnickname);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	public String getIpAddr(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for"); 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
        return ip; 
    }

}
