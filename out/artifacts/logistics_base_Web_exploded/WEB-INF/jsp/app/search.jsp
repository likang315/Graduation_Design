<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
	</head>
	<body class="bjs-blue" onload="beiLogin()" id="bodyLogin">
	
		<header id="weixin_header">
			<script>
				API.settingHeard("查询");
			</script>
		</header>

		<div class="center-block">
			<section class="mian_img">
				<img src="${pageContext.request.contextPath}/images/cx_pic.png" class="img-responsive center-block" />
			</section>
		
			<section class="input_box">
				<img src="${pageContext.request.contextPath}/images/cx_pic_hmcx.png" class="img_tit" />
				<div class="form-group">
				    <input type="text" id="cphone" name="cphone" class="form-control" placeholder="手机号" />
				</div>
				<div class="form-group">
				    <button class="btn btn-block btn-lg btn_chaxun" id="searchCustomer">
				        <span class="glyphicon glyphicon-search"></span> &nbsp; 查询
				    </button>
				</div>
			</section>
		 </div>
		 <%@include file="/common/leftBottom.jsp" %>
	</body>
</html>

<script>
	$("#searchCustomer").click(function(){
		$("#search_result").html();
		var phone = $("#cphone").val();
		if(!phone){
			alert("请输入手机号");
			return;
		}
		
		API.ajax("/app/search/phone",{"phone":phone}).success(function(data){
			var str = "";
			if(data.type === 0){
				str = "<h3>"+data.info+"</h3>"+
						'<hr/>'+
						'<button class="btn btn-block btn-lg btn_chaxun" onclick="javascript:window.document.location.href=\'${pageContext.request.contextPath}/app/business/broadbandManagement.html\'">'+
							'<span class="glyphicon glyphicon-search"></span> &nbsp; 立即办理'+
						'</button>';
			}else{
				var title = "";
				var content = "";
				var state = 0;
				for(var i = 0;i<data.info.length;i++){
					if(data.info[i].state_type === 1){
						state = data.info[i].state_type;
						title += "|"+data.info[i].state_title+"|";
						content += "<p>"+data.info[i].state_explain+"</p>";
					}
				}
				var btn = "";
				if(state == 0){
					title = "<h3>客户号码正常</h3>";
					btn = '<button class="btn btn-block btn-lg btn_chaxun" onclick="javascript:window.document.location.href=\'${pageContext.request.contextPath}/app/business/broadbandManagement.html\'">'+
								'<span class="glyphicon glyphicon-search"></span> &nbsp; 立即办理'+
							'</button>';
				}else{
					btn = '<button class="btn btn-block btn-lg btn_chaxun" onclick="javascript:alert(\'这里是提交异常连接\')">'+
								'<span class="glyphicon glyphicon-search"></span> &nbsp; 提交异常'+
							'</button>';
				}
				str  = title + "<br/>" + content +
						'<hr/>'+btn;
						
			}
			$("#search_result").html(str);
		}).error(function(data){
			
		}).exception(function(){
			
		})
	})
	
</script>
