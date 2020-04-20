<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
	<head>
		<title>app-login—01</title>
		<%@include file="/common/common-app-head.jsp" %>
		<link href="${pageContext.request.contextPath}/css/drag.css" rel="stylesheet">
		<link  rel="stylesheet" href="${pageContext.request.contextPath}/css/cover.css" />
		<script type="text/javascript"  src="${pageContext.request.contextPath}/js/drag.js"></script>
		<style type="text/css">
			#hint1{
				color:red;
				text-align:center;
				display:block;
				
			}
			
			::-webkit-input-placeholder  {
			color:#ccc !important; 
			} 
		</style>
	</head>
	<body  class="bjs-blue">

	    <div class="container ">
	               <div class="p15"><img src="${pageContext.request.contextPath}/images/china_mobile.png" class="w200" style="width:200px;" />  </div>
	
	        <div class="login_page text-center">
				<div style="text-align:center;width:100%;height:80px;font-size:35px;color:white;font-family:幼圆"> 和·物达
				</div>
	            <div>
	            	<form id="loginForm" action="${pageContext.request.contextPath}/app/login/checkLogin.html" method="post">
		                <div class="form-group">
		                    <input type="name" class="form-control" id="exampleInputEmail1" name="accountName" value="${userName }" placeholder="请输入用户名"/>
		                </div>
		                <div class="form-group">
		                    <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="请输入密码"/>
		                </div>
		                <div class="form-group">
		                 <div id="drag"></div>
                		</div>
						<span id="hint1">${result.info }</span>
		               	<!-- <div class="form-group">
		                    <button id="login"  class="btn btn-block btn-lg btn_login">登录</button>
		                </div> -->
	                </form>
	               <div class="form-group">
	                    <p class="rember_psw"><a class="pull-right" href="${pageContext.request.contextPath }/app/user/forgetPassword.html">忘记密码</a></p>
	                </div>
	                
	            </div>
	        </div>
	
	    </div>
	
	</body>
</html>

<script>

$('#drag').drag({
	"slideEndFun":slideEndFun,
}); 

function slideEndFun(){
	API.setStore("userName",$("#exampleInputEmail1").val());
	API.setStore("password",$("#exampleInputPassword1").val());
	$("#loginForm").submit();
}
</script>
