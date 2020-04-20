<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
	<%@include file="/common/common-app-head.jsp" %>
	<link rel="stylesheet"  href="${pageContext.servletContext.contextPath }/css/defaultTheme.css"/>
	<link rel="stylesheet"  href="${pageContext.servletContext.contextPath }/css/myTheme.css"/>
	<link rel="stylesheet"  href="${pageContext.servletContext.contextPath }/css/bootstrap.min.css"/>
	<link rel="stylesheet"  href="${pageContext.servletContext.contextPath }/css/logistics.css"/>
</head>
<body class="bgd">
	<div class="container">
		<header id="weixin_header">
			<script>
				API.settingHeard("报需选择");//修改“个人设置”这个参数为你本页面的功能名
			</script>
	    </header>
	    <table class="table table-bordered">
	    	<tr style="height: 120px; text-align: center;">
	    		<td class="tiaozhuan" style="padding-top: 60px;" data="tostoreSupplies">需求上报</td>
	    		<td class="tiaozhuan" style="padding-top: 60px;" data="toStoreAllTotal">上报列表</td>
	    	</tr>
	    	<tr style="height: 120px; text-align: center;">
	    		<td class="tiaozhuan" style="padding-top: 60px;" data="goStoreTotal">上报统计</td>
	    	</tr>
	    </table>
	</div>
	<%@include file="/common/leftBottom.jsp" %>
</body>
<script type="text/javascript">
	$(".tiaozhuan").click(function(){
		location.href="${pageContext.request.contextPath}/app/storeSupplies/"+$(this).attr("data")+".html";
	});
</script>
</html>
