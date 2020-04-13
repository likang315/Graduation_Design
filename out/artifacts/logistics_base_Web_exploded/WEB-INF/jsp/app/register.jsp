<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
		
	    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" />
	    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
	    <link href="${pageContext.request.contextPath}/css/cover.css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
		<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
		<style type="text/css">
		.look{
			  font-size: 14px;
			  line-height: 1.42857143;
			  color: #555;
			  background-color: #fff;
			  background-image: none;
			  border: 1px solid #ccc;
			  border-radius: 4px;
			  display: block;
			  height: 34px;
			  padding: 6px 12px;
			  
		}
		</style>
	</head>
	<body class="bjs-gary">
	<!-- header -->
		<header id="weixin_header">
			<script>
				API.settingHeard("注册");
			</script>
		</header>
		<div>  
			
    <div class="container ">
        <div class="register_page ">
             <form id="addForm" action="${pageContext.servletContext.contextPath }/app/login/register.html" method="post">

                <div style="margin-top:30px;">
                    <div class="form-group zhuce userName">
                        <input type="text" class="look form-control" name="roleName" id="roleName" placeholder="请输入姓名"/>
                    </div>
                    <div class="form-group zhuce CarId">
                        <input type="text" class="look form-control" name="id_car" id="id_car" placeholder="请输入身份号码"/>
                    </div>
                    <div class="form-group zhuce tellPhone">
                        <input type="text" class="look form-control" name="accountName" id="accountName" onblur="onblus()"  placeholder="请输入电话"/>
                    </div>
                   
                    <div class="form-group zhuce shopType">
                        <select  id="area"  style="color:#ccc" class="form-control">
                            <option>请选择所属业务区</option>
                        </select>
                    </div>
                    <div id="informatiotgn" style="text-align:center"></div>
                    <input type="text" style="display:none;" name="area" id="area1" >
                    <input type="text" style="display:none;" name="groupId" id="groupId" >
                    <div class="form-group">
                        <input type="button" class="btn btn-block btn-lg" id="register" value="立即注册">
                    </div>
                </div>
				
            </form>	
        </div>
    </div>
		
		
		</div>
		
		
		
	<!-- 个人中心	底部导航 -->
	<%@include file="/common/leftBottom.jsp" %>
	</body>
</html>

<script>

function onblus(){
	var accountName=$("#accountName").val();
	$.post("${pageContext.request.contextPath}/app/login/getUserInfo.html",{"accountName":accountName},function(data1){
	    var data = JSON.parse(data1);
		if(data.state =="no"){
			$("#informatiotgn").html("您好，你所输入的电话号码已注册");
			$("#informatiotgn").css("color","red");
			$("#accountName").css("borderColor","red");
		}else{
			$("#informatiotgn").html("");
			$("#accountName").css("borderColor","#ccc");
		}
	})
} 

	$(document).ready(function(){
		
		//获取业务区
		$.post("${pageContext.request.contextPath}/app/login/getSectionName.html",function(data1){
			var data = JSON.parse(data1);
			var list=data.list;	
			for(var i = 0;i < list.length;i++){
				
				str = "<option value="+list[i].id+" >"+list[i].name+"</option>";				    				
				$("#area").append(str); 						  
			}
		})
		
		$("#register").click(function(){
			var area=$("#area :selected").text();
			var groupId=$("#area :selected").val();
			$("#groupId").val($("#area :selected").val());
			$("#area1").val($("#area :selected").text());
			if(check()){
				$("#addForm").submit();
			}
		})
	})
	
	function check(){
		
		var roleName=$("#roleName").val();
		if(!roleName){
			$("#roleName").css("borderColor","red");
			return false;
		}
		
	    var customer_id_card= /^\d{15}|\d{18}$/;
	    var id_car=$("#id_car").val();
		if(!customer_id_card.test(id_car)){
			$("#id_car").css("borderColor","red");
			return false;
		}
		
		var phone = /^1(3[4-9]|5[012789]|8[23478]|4[7]|7[8])\d{8}$/;
		var accountName=$("#accountName").val();
		if(!phone.test(accountName)){
			alert("请输入移动号码");
			$("#accountName").css("borderColor","red");
			return false;
		}
		
		var area=$("#area :selected").text();
		if(!area || area == "请选择所属业务区"){
			alert("请选择所属业务区");
			return false;
		}else{
			return true;
		}
	}
</script>
