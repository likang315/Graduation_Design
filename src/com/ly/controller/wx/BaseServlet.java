package com.ly.controller.wx;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.util.wx.CoreService;
import com.ly.util.wx.SignUtil;




public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf8");
		String token = (String) request.getSession().getAttribute("token");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// 微信加密签名  
		         String signature = request.getParameter("signature");  
		         // 时间戳  
		         String timestamp = request.getParameter("timestamp");  
		         // 随机数  
		         String nonce = request.getParameter("nonce");  
		         // 随机字符串  
		         String echostr = request.getParameter("echostr");  
		         
		         /*
		          * 
		          * 
		          * 
		          * 
		          *  timestamp:'', // 必填，生成签名的时间戳
	    			 nonceStr: '', // 必填，生成签名的随机串
	    			 signature: '',// 必填，签名，见附录1
		          */
		         request.getSession().setAttribute("timestamp", timestamp);
		         request.getSession().setAttribute("nonce", nonce);
		         request.getSession().setAttribute("signature", signature);
		         
		         System.out.println("timestamp:"+timestamp);
		         System.out.println("signature:"+signature);
		         System.out.println("nonce:"+nonce);
		         System.out.println("echostr:"+echostr);
		         
		         PrintWriter out = response.getWriter();  
		         // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
		         if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
		             out.print(echostr);  
		         }else{
		        	 String ret = "error:200000000";
			         out.println(ret);
		         }
		         out.close();
		         out = null;
		         
	} 

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
	     resp.setCharacterEncoding("UTF-8");
		// 调用核心业务类接收消息、处理消息  
        String respMessage = CoreService.processRequest(req);  
        //String respMessage = "hello";
       
        
        // 响应消息  
        PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
		}  
        out.print(respMessage);  
        out.close();  
	}
	
	/*
	*//**http://localhost:8080/exhomelife/base.html?timestamp=1444383904&nonce=339206167&signature=400f29f87b7d72a43faadcd20c33401140ea5f77&echostr=1890706976660783073
	 * 消息接收
	 * @param request
	 * @param response
	 *//*
	public static void acceptMessage(HttpServletRequest request,HttpServletResponse response){
		 String fStr="<?xml version='1.0' encoding='UTF-8'?>" +  
	                "<ROOT test='test123' cod='cod123'><Name><b>a</b></Name><Number>BBB</Number>" +  
	                "<Recording>http://10.15.57.174/wav/2008/10/29/WG37100/ext37102/10.15.57.71!1~R!10292008_064002!37102!67256479!Ext!NA!1179371583!R.wav</Recording>" +  
	                "<Orders>有</Orders></ROOT>";  
		Document doc;
		try {
			doc = DocumentHelper.parseText(fStr);
			Element ele = doc.getRootElement().element("Name").element("b");
			
			System.out.println(ele.getData());
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		acceptMessage(null,null);
	}*/
}
