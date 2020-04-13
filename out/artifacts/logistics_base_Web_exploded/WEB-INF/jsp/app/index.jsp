<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
	</head>
	<body class="bjs-gary">
		<form id="loginForm" action="${pageContext.request.contextPath}/app/login.html" method="post">
			<input type="hidden" id="userName" name="accountName" />
			<input type="hidden" id="password" name="password"/>
		</form>
	</body>
</html>

<script>
	var user= API.getUserInfo();
	$("#userName").val(API.getStore("userName"));
	$("#password").val(API.getStore("password"));
	if(API.isNull(API.getStore("userName")) || API.isNull(API.getStore("password")))
		window.document.location.href=API.httpserver + "/app/login.html";
	else
		$("#loginForm").submit();
</script>
