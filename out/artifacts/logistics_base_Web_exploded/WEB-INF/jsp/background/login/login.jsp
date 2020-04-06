<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--login.html-->
<title>欢迎登录后台管理系统</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script>

</head>

<body style="background-color:#1c77ac; background-image:url(${pageContext.request.contextPath}/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>


    <div class="logintop">
        <span>欢迎登录后台管理界面平台</span>
        <ul>
            <li><a href="#">西安邮电大学 李康 毕业设计</a></li>
        </ul>
    </div>
    
    <div class="loginbody">
        <span class="systemlogo"></span>
        <div class="loginbox">
            <form id="loginForm" name="loginForm" method="post"
                    action="${pageContext.servletContext.contextPath}/submitlogin.html">
                <ul>
                    <li><input type="text" class="loginuser"  id="username" placeholder="用户名" name="username" tabindex="1"/></li>
                    <li><input type="password" class="loginpwd" id="password" placeholder="密码" name="password" tabindex="2"/></li>
                    <li><input name="" type="button" class="loginbtn" value="登录"  onclick="checkUserForm();"  />
                        <label><input name="" type="checkbox" value="" checked="checked" />记住密码</label>
                    </li>
                </ul>
            </form>
        </div>
    </div>
    <div class="loginbm">版权所有  2020  <a href="https://github.com/likang315" target="_blank">kang.li</a>  </div>
	

<script type="text/javascript" src="${pageContext.request.contextPath}/js/fun.base.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
<script type="text/javascript">
if (top.location != self.location){
    top.location=self.location
}
if ("${error}" != "") {
	alert("${error}");
};
function checkUserForm() {
	var userName = $("#username").val();
	var userPassword = $("#password").val();
	if (userName == "" || userPassword == "") {
		alert("用户名或密码不能为空");
		return false;
	}
	var b="";
	// 利用ajax检查账户信息是否正确
	$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/loginCheck.html?data="
						+ new Date(),
				data : {
					username : userName,
					password : userPassword
				},
				dataType: "json",
				async : false,
				success : function(data) {
					if (data.error == "0") {
						b = false;
					} else {
						b = true;
						alert(data.error);
					}
				}
			});
	if (b) {
		return true;
	}
	document.loginForm.submit();
}
</script>

</body>
</html>
