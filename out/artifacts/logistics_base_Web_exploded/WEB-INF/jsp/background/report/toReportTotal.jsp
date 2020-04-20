<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/date.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>订单配送统计</title>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/API.js"></script>
</head>
<body>
	<div align="center" style="margin-top: 250px;">
	<img alt="预加载图片" height="100%" width="30%" src="${pageContext.request.contextPath }/images/yujiazai1.gif">
	</div>
	<script type="text/javascript">
		 API.checkLogin();
		var user = API.getUserInfo(); 
		location.href = "${pageContext.request.contextPath}/background/report/getReportTotal.html?userPhone="+ user;	
	</script>
</body>
</html>