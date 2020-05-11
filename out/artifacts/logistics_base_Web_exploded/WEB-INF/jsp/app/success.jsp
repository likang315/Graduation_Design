<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>管理员-派送-订单生成成功</title>
    <%@include file="/common/common-app-head.jsp" %>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/defaultTheme.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/myTheme.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/logistics.css"/>
</head>
<body class="bjs-gary">
<div class="container">
    <header id="weixin_header">
        <script>
            API.settingHeard("生成订单");//修改“个人设置”这个参数为你本页面的功能名
        </script>
    </header>

    <div style="width: 100%;  border:0px #000 solid; margin-top: 15px; background: #fff; padding: 0 100px;">
        <img src="${pageContext.servletContext.contextPath }/images/chengg.jpg" width="100%">
    </div>
    <div align="center" style="background: #fff; height: 80px; line-height: 80px;">
        <span>订单生成成功!</span>
    </div>
    <div align="center" style=" padding: 30px 20px; ">
        <input type="button" value="点击查看订单列表" id="clickLookList" class="btn btn-success btn-block">
    </div>
</div>
<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/API.js"></script>
<script type="text/javascript">
    $(function () {
        API.checkLogin();
    });
    $("#clickLookList").click(function () {
        location.href = "${pageContext.request.contextPath}/app/fillingSingle/orderList.html";
    });
</script>
</html>
