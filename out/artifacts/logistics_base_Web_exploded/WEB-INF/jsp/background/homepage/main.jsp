<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Logistics Management System</title>
    <!--后台管理系统首页-->

</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath}/top.html" name="topFrame" noresize="noresize" id="topFrame"
           title="topFrame"/>

    <frameset cols="*" frameborder="no" border="0" framespacing="0">

        <frame src="${pageContext.request.contextPath}/center.html" name="centerFrame" id="centerFrame"
               title="centerFrame"/>

    </frameset>

</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html>
