<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%@include file="/common/common-app-head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>忘记密码</title>
	<link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath }/css/cover.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath }/css/new.css" rel="stylesheet" />
</head>
<body onload="beiLogin()" id="bodyLogin"  class="bjs-blue">

    <div class="container ">
        <div class="fpsw_page ">
            <header>
                <a href="javascript:history.go(-1);" class="pull-left go_back"><span class="glyphicon glyphicon-menu-left"></span>返回</a>
                <h4 class="text-center">忘记密码</h4>
            </header>
                 <div class="p15"> <img src="${pageContext.request.contextPath }/images/china_mobile.png" class="w200" />   </div> 

            <section class="psw_box">

                <div>
                    <div class="form-group">
                        <input type="text" id="tellPhone" class="form-control" placeholder="手机号" />
                    </div>
                    <div class="form-group">
                        <input type="text" id="code" class="form-control" placeholder="请输入验证码" />
                        <a class="input-group-addon" id="basic-addon2">获取验证码</a>
                    </div>
                    <div class="form-group">
                        <input type="password" id="password" class="form-control" placeholder="请输入新密码" />
                    </div>
                    <div class="form-group">
                        <input type="password" id="password1" class="form-control" placeholder="请再次输入新密码" />
                    </div>
                    <div class="form-group">
                        <button id="update" class="btn btn-block btn-lg btn_determine">
                            确定
                        </button>
                    </div>
                </div>
            </section>
        </div>
    </div>
<div id="MessageMask" style="display:none;width:100%;height:100%;background-color:#ccc;position:fixed;top:0;left:0;opacity:0.4;z-index:100">
		<div id="messageImg" style="display:none;width:80px;height:80px;position:fixed;top:0px;left:0px;right:0px;bottom:0px;margin:auto;"><img src="${pageContext.request.contextPath }/images/ajax-loader.gif" /></div>
	</div>
	<div id="promptMessage" style="z-index:150;display:none;width:150px;height:100px;border:1px solid #ccc;position:fixed;top:40%;left:50%;margin-left:-75px;border-radius:5px;">
		<div style="height:30px;text-align:center;line-height:30px;font-size:20px;color:white;background-color:#7CC8E8;border-top-left-radius:5px;border-top-right-radius:5px;">提示信息</div>
		<div id="promptMessage1" style="color:rgb(204, 2, 204);text-align:center;width:100%;height:70px;background-color:white;border-bottom-left-radius:5px;border-bottom-right-radius:5px;overflow:scroll;word-wrap:break-word;word-break:break-all;"></div>
	</div>
</body>
<script>
function beiLogin(){
	var a=window.screen.height-64;
   $("#bodyLogin").css("height",a);
}

$(window).resize(function(){
   var a=window.screen.height-64;
   $("#bodyLogin").css("height",a);
});

var times_index = 60;
function getIdentifyingCodeTime(){
	if(times_index > 0){
		times_index--;
		$("#basic-addon2").html(times_index+"秒后重新获取");
		$("#basic-addon2").css("color","black");
		setTimeout("getIdentifyingCodeTime()",1000);		
	}else{
		times_index = 60;
		$("#basic-addon2").css("color","#fa7729");
		$("#basic-addon2").html("获取验证码")
	}
}

$("#promptMessage").click(function(){
	$("#MessageMask").hide();
	$("#promptMessage").hide();
})
		
$("#basic-addon2").click(function(){
	if($("#tellPhone").val() === ''){
		$("#MessageMask").show();
		tishiMessage("手机号不能为空");
		return;
	}
	var tell = $("#tellPhone").val();
	var re = /^1\d{10}$/;
	if(re.test(tell)){
		API.ajax("/app/user/isTellPhone",{"accountName":tell}).success(function(result){
			getIdentifyingCodeTime();
			$("#MessageMask").show();
			API.ajax("/app/user/getCode",{"tellPhone":$("#tellPhone").val()}).success(function(data){
				tishiMessage(data.info)
				
			}).error(function(){
				
			}).exception(function(){
				
			})
		}).error(function(){
			$("#MessageMask").show();
			tishiMessage("手机号不存在")
			return;
		}).exception(function(){});
	}else{}
})
	
	
		
$("#update").click(function(){
	var shoujihao=$("#tellPhone").val();
	var reeee = /^1\d{10}$/;
	if(!reeee.test(shoujihao)){
		tishiMessage("请输入11位正确的手机号");
		return;
	}
	
	var yanzhengma=$("#code").val();
	var yzmzz = /^\d{6}$/;
	if(!yzmzz.test(yanzhengma)){
		tishiMessage("请输入6位验证码");
		return;
	}
	
	API.ajax("/app/user/testCode",{"tellPhone":$("#tellPhone").val(),"code":$("#code").val()}).success(function(data){
		var rss = /^\S{6,18}$/
			//判断密码是否相同
			if($("#password").val()== '' || !(rss.test($("#password").val()))){
				$(".password").css("border","1px solid red");
				tishiMessage("密码长度需在6-18位")
				return;
			}
			if($("#password").val() != $("#password1").val()){
				tishiMessage("两次密码输入不一样！")
			}else{
				API.ajax("/app/user/updatePassword",{"tellPhone":$("#tellPhone").val(),"password":$("#password1").val()}).success(function(data){
					tishiMessage(data.info);
					var t = setTimeout("window.location.href='${pageContext.request.contextPath}/app/login.html';window.clearTimeout(t);",1000);
				}).error(function(data){
					tishiMessage(data.info);
				}).exception(function(){})
			}
	}).error(function(data){
		tishiMessage(data.info)
		return ;
	}).exception(function(){})
})
	
function getById(e){
	return document.getElementById(e);
}
	
//隐藏提示信息
function yincangInfo(){
	$("#MessageMask").hide();
	$("#promptMessage").hide();
}
	
//提示信息
function tishiMessage(info){
	getById("MessageMask").style.display="block";
	getById("promptMessage").style.display="block";
	getById("promptMessage1").innerHTML = info;
}
</script>
</html>