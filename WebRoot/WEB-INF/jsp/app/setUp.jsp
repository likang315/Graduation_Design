<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
	</head>
	<body class="bjs-gary">
		<header id="weixin_header">
			<script>
				API.settingHeard("个人设置");
			</script>
		</header>
		<div class="myMaxDiv">
	    	<div class="col-xs-4 control_01">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</div>
	        <div class="col-xs-8" id="sec_username"></div>
	    </div>
		<div class="myMaxDiv">
	    	<div class="col-xs-4 control_01">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号:</div>
	        <div class="col-xs-8" id="sec_accountName"></div>
	    </div>
	    <div class="myMaxDiv">
	    	<div class="col-xs-4 control_04">账号类型:</div>
	        <div class="col-xs-8" id="sec_accountType"></div>
	    </div>
	    <div class="myMaxDiv">
	    	<div class="col-xs-4 control_06">身&nbsp;&nbsp;份&nbsp;&nbsp;证:</div>
	        <div class="col-xs-8" id="id_car"></div>
	    </div>
	    <div class="myMaxDiv" style="display: none;" id="myExpress">
	    	<div class="col-xs-4 control_04">快递公司:</div>
	        <div class="col-xs-8" id="sec_Express"></div>
	    </div>
	    <div class="myMaxDiv" style="display: none;" id="mycode">
	    	<div class="col-xs-4 control_04">渠道编码:</div>
	        <div class="col-xs-8" id="sec_code"></div>
	    </div>
	    <div class="myMaxDiv" style="display: none;" id="mysectionName">
	    	<div class="col-xs-4 control_04">渠道名称:</div>
	        <div class="col-xs-8" id="section_name"></div>
	    </div>
	   	<div class="myMaxDiv">
	    	<div class="col-xs-4 control_02">归属地市:</div>
	        <div class="col-xs-8" id="sec_dishi"></div>
	    </div>
		<div class="doubleBtn">
	    	<input type="button" value="修改密码" class="btn" id="ModifyPassword">
	        &nbsp;&nbsp;&nbsp;&nbsp;
	        <input type="button" value="退出登录" class="btn"  id="exit_login">
	    </div>

        <%@include file="/common/leftBottom.jsp" %>
	</body>
</html>

<script>
	$(function(){
		var user = API.getUserInfo();
		
		if(user.accountName == null)
			window.document.location.href=API.httpserver + "/app/login.html";
		$("#sec_username").html(user.real_name);
		$("#sec_accountName").html(user.accountName);
		if(user.accountType == 1){
			$("#sec_accountType").html("门店");
			$("#mycode").css('display','block');
			$("#sec_code").html(user.code);
			$("#mysectionName").css('display','block');
			$("#section_name").html(user.section_name);
		} else if(user.accountType == 2){
			$("#sec_accountType").html("快递员");
			$.post("${pageContext.request.contextPath}/app/login/getBycompanyId.html",{"companyId":user.company}).done(function(data){
				$("#sec_Express").html(data.company_name);
				$("#myExpress").css('display','block');
			}).error(function(data){});
			
		} else {
			$("#sec_accountType").html("管理员");
		}
		$("#id_car").html(user.id_car);
		$("#sec_dianyuanNum").html(user.dianyuanNum);
		$("#sec_dishi").html(user.city);
		$("#exit_login").click(function(){
			API.clearAllStore();
			window.document.location.href=API.httpserver + "/app/login.html";
		})
		
	})
	
	$("#ModifyPassword").click(function(){
		window.location.href="${pageContext.request.contextPath}/app/user/toUpdatePasswordView.html";
	})
</script>
