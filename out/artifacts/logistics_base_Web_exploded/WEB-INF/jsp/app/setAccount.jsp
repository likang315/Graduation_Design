<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE >
<html>
	<head>
		<title>app-login—02</title>
		<%@include file="/common/common-app-head.jsp" %>
	</head>
	<body>
		
	</body>
</html>

<script>
	API.setStore("userinfo",'${userInfo}');
	API.setStore("managerName",'${managerName}');
	API.setStore("managerPhone",'${managerPhone}');
	var user = API.getUserInfo();
	accountName=user.accountName;
	window.location.href= API.httpserver + "${appIndexUrl}";
</script>
