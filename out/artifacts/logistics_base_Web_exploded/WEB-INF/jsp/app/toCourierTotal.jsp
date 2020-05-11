<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>快递员-统计index</title>
    <%@include file="/common/common-app-head.jsp" %>
</head>
<body>
<div align="center" style="margin-top: 250px;">
    <img alt="预加载图片" height="100%" width="100%" src="${pageContext.request.contextPath }/images/yujiazai1.gif">
</div>

</body>
</html>

<script type="text/javascript">
    $(function () {
        API.checkLogin();
    })
    var user = API.getUserInfo();
    var account = user.accountName;
    location.href = "${pageContext.request.contextPath}/app/coordinate/toCourierTotal.html?accountName=" + account;
</script> 