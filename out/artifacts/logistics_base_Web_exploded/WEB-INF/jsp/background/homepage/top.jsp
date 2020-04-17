<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--HomePage top.html-->
<title>首页顶部</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})
})	
</script>


</head>

<body style="background:url(${pageContext.request.contextPath}/images/topbg.gif) repeat-x;">

    <div class="topleft" style="padding-left:30px;padding-top: 25px">
    	<!--
    	 <a href="javascript:void(0)" target="_parent"><img src="${pageContext.request.contextPath}/images/loginlogo.png" title="系统首页"  style="margin-top: 20px;"/></a>
        -->
        <h1 style="font-size:150%;color:orange">Logistics Management System</h1>

    </div>
             
    <div class="topright">    
    <ul style="padding-top:35px;">
        <!--页面未找到，资源跳转问题...-->
    <li><a href="#"  onclick="homePageHelp()">帮助</a></li>
    <li><a href="${pageContext.request.contextPath}/login.html" target="_parent">退出</a></li>
    </ul>
     
    <div class="user" style="clear:none; margin-top:29px;">
        <span>${sessionScope.userSession.accountName}</span>
    </div>    
    
    </div>

    <script type="text/javascript">
        function homePageHelp() {
            alert("瞅啥，去看文档！！！");
        }
    </script>
</body>
</html>
