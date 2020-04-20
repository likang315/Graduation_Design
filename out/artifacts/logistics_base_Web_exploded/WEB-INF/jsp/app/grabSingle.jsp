<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
		<style type="text/css">
			/* 本页面css样式 */
		</style>
	</head>
	<body class="bjs-gary">
	<!-- header -->
		<header id="weixin_header">
			<script>
				API.settingHeard("抢单");//修改“个人设置”这个参数为你本页面的功能名
			</script>
		</header>
		<div>  
		
		<!-- 内容 -->
		成功进入系统
		
		<a href="coordinate.html" >门店坐标</a>
		</div>
		
		
		
	<!-- 个人中心	底部导航 -->
	<%@include file="/common/leftBottom.jsp" %>
	</body>
</html>

<script>
$(function(){
	  API.checkLogin();
})
</script>
