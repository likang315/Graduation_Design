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
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.fixedheadertable.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/demo.js"></script>
	<style>
	.garden3{color:#bf4c28; font-weight:bold; border-bottom:2px solid #bf4c28;}
	.divGarden3{color:#bf4c28;border:2px solid #bf4c28; }
	</style>	
</head>
	<body class="bgd">
	<!-- header -->
		<div class="container">
			<header id="weixin_header">
				<script>
					API.settingHeard("快递员");//修改“个人设置”这个参数为你本页面的功能名
				</script>
		    </header>
		    <div class="procedure">
		    	<div class="col-xs-4">
		    		<div class="divGarden1">1</div>
		            <div class="garden1">门店信息</div>
		    	</div>
		        <div class="col-xs-4">
		    		<div class="divGarden2">2</div>
		            <div class="garden2">物资信息</div>
		    	</div>
		        <div class="col-xs-4">
		    		<div class="divGarden3">3</div>
		            <div class="garden3">快递员</div>
		    	</div>
		    </div>
		    <div class="tableDivTow">
		        <div>
		            <table class="fancyTable" id="myTable02" width="100%" cellpadding="0" cellspacing="0">
		                <thead>
		                    <tr>
		                        <th>公司</th>
		                        <th>姓名</th>
		                        <th>电话</th>
		                        <th>操作</th>
		                    </tr>
		                </thead>
		                <tbody id="tbody1">
		                	<tr id="tetb">
		                        <td></td>
		                        <td></td>
		                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		                        <td></td>
		                    </tr>
		                </tbody>
		            </table>
		        </div>
		        <div class="clear"></div>
		    </div>
		    	
		  	<div class="courierSelectDiv">
		  	<form id="form" action="${pageContext.servletContext.contextPath }/app/fillingSingle/putInMaterialInfo.html" method="post">
		        <table border="0" width="100%">
		            <tr>
		            	<td width="90" align="right">快递公司:</td>
		                <td width="190">
		                    <div class="choiceDiv" id="express" >
                       
                			</div>
		                </td>
		            </tr>
		            <tr>
		            	<td  align="right">快递员:</td>
		                <td>
		                    <div class="choiceDiv"  id="courier">
                    
                			</div>
		                </td>
		            </tr>
		        </table>
		        <div align="center" style="font-size:12px;height:15px; color:red; text-shadow:5px 2px 6px #666;">
		        	<label id="mesg"></label>
		        </div>
		        <div class="addToDiv">
		        	<!-- <input type="button" value="添加" id="tjbutton"  class="btn btn-success btn-sm" style="width:80px;">
		            &nbsp;&nbsp;&nbsp;&nbsp; -->
		        	<input type="button" value="生成订单" id="next" class="btn btn-success btn-sm" style="width:80px;">
		        </div>
		   	</form>
		</div>
	</div>
    <div class="shadyDivs" id="shady" style="display:none;"></div>
    <div class="selectContent" id="company" style="display:none;">
    	<div class="top">请选择快递公司</div>
        <div class="over" id="overAA">
        	<c:forEach items="${expressCompanys }" var="e">
        		<div class="firm" data="${e.id }">${e.company_name }</div>
        	</c:forEach>
        </div>
        <div class="bottomQD">
            <input type="button" value="确定"  class="btn btn-primary btn-xs" style="width:80px;" id="companyDetermined">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="关闭"  class="btn btn-warning btn-xs" style="width:80px;" id="blogb">
        </div>
    </div>
    <div class="selectContent" style="display:none;" id="personnel">
        <div class="top">请选择快递员</div>
        <div class="susou"><input type="text" id="findPhone" placeholder="请输入要查询的姓名或者电话"></div>
        <div class="over">
            <table class="table table-condensed">
                <thead>
                    <tr class="info">
                        <th>所属公司</th>
                        <th>姓名</th>
                        <th>电话</th>
                    </tr>
                </thead>
                <tbody id="tbodytr"></tbody>
            </table>
        </div>
        <div class="bottomQD">
            <input type="button" value="确定"  class="btn btn-primary btn-xs" style="width:80px;" id="personnelDetermination">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="关闭"  class="btn btn-warning btn-xs" style="width:80px;" id="blohz">
        </div>
    </div>		
	<nav class="navbar-fixed-bottom">
	    <div class="container">
	        <div class="row wnfooter">
	            <div class="col-xs-3 footer-query">
	                <a>查询</a>
	            </div>
	            <div class="col-xs-3 footer-business">
	                <a href="">业务</a>
	            </div>
	            <div class="col-xs-3 footer-surface">
	                <a href="">报表</a>
	            </div>
	            <div class="col-xs-3 footer-detailed">
	                <a href="">明细</a>
	            </div>
	        </div>
	    </div>
	</nav>
	<!-- 个人中心	底部导航 -->
	<%@include file="/common/leftBottom.jsp" %>
	</body>
	
	<!-- 生成订单记录 -->
	<form action="${pageContext.request.contextPath}/app/fillingSingle/addMailInfor.html" method="post" id="myform">
		<input type="hidden" id="courierPhones" name="courierPhones" /><!-- 选择的快递员电话 -->
		<input type="hidden" id="marketingMobiles" name="accountPhone" /><!-- 生成该次订单任务的营销中心人员电话 -->
	</form>
	
	<!-- 蒙版 -->
	<div id="bigblack2" style="z-index:5554;width:100%;background:#000;height:100%;position: fixed;top:0px;left:0px;opacity:0.7;display: none;"></div>
	<!-- 确认框 -->
	<div id="yesOrNo" style="position:fixed; width:100%; top:150px; z-index: 999999; display: none;">
    	<div style="width:80%; margin-left:10%; background:#FFF; padding:20px; border-radius:5px;">
        	<div>确认本次派送任务吗?</div>
            <div style="text-align:right; margin-top:20px;"><a href="javascript:myConfirm();">确认</ a>&nbsp;&nbsp;&nbsp;<a href="javascript:myClose();">关闭</ a></div>
        </div>
    </div>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/API.js"></script>
<script>
$(document).ready(function(e) {
	API.checkLogin();
	
	//验证物资信息是否存在
	$.post("${pageContext.request.contextPath}/app/fillingSingle/checkMaterial.html").done(function(data){
		if(data.info <= 0){
			location.href="${pageContext.request.contextPath}/app/fillingSingle/toStoreInformation.html";
		}
	}).error(function(data){
		location.href="${pageContext.request.contextPath}/app/fillingSingle/toStoreInformation.html";
	});
	
	//传递宽值
	var div_h= window.screen.height-300;
	var data={"height":div_h}
	$("#myTable02").fixedHeaderTable(data);	
});	
	
	$("#express").click(function(){
		$("#express").html("");
		$("#shady").show();
		$("#company").show();
	})
	$("#courier").click(function(){
		$("#courier").html("");
		$("#shady").show();
		$("#personnel").css("display","block");
	})
	$("#blogb").click(function(){
		$("#shady").hide();
		$("#company").hide();
		$("#personnel").hide();
	})
	
	$("#blohz").click(function(){
		$("#shady").hide();
		$("#company").hide();
		$("#personnel").hide();
	})
	
	$("#shady").click(function(){
		$("#shady").hide();
		$("#company").hide();
		$("#personnel").hide();
	})
	
	
	var fall;//快递公司名称
	var fallId = "";//快递公司id
	//点击快递公司蒙版上的确认按钮
	$("#companyDetermined").click(function() {
		var fa=$(".firm");
		fall="";
		$("#tbodytr").html("");
		for(var i=0;i<fa.length;i++){
			if(fa[i].style.color=="rgb(255, 255, 255)"){
				fall+=fa[i].innerHTML + ",";
				fallId+=fa[i].getAttribute("data") + ",";
				$("#shady").hide();
				$("#company").hide();	
			}
		}
		var fas, newStr="";
		fas = fall.charAt(fall.length - 1);
		if(fas === ","){
			for(var i = 0; i < fall.length - 1; i++){
				newStr += fall[i];
			}
		}
		$("#express").html(newStr);
		$("#redx1").show();
		//根据快递公司id加载快递员
		//var falab = fall.split(",");
		if(fallId != null && fallId != ""){
			$.post("${pageContext.request.contextPath}/app/fillingSingle/getCourier.html", {"expressId":fallId}).done(function(data){
				var ant = "";
				if(data.courierList.length > 0){
					for(var i = 0; i < data.courierList.length; i++){
						 ant += "<tr  onClick='trtb(this)' class='tr'>"
								     +"<td>" + data.courierList[i].company_name + "</td>"
								     +"<td>" + data.courierList[i].real_name + "</td>"
								     +"<td>" + data.courierList[i].phone + "</td>"
								+"</tr>";
					}
				} else {
					ant = "<tr>"
		     			+"<td colspan='5'>该公司暂时没有快递员注册</td>"
			 		+"</tr>";
				}
				$("#tbodytr").append(ant);
			}).error(function(data){
				var ant = "<tr>"
				     			+"<td colspan='5'>暂无记录</td>"
					 		+"</tr>";
				$("#tbodytr").append(ant);
			});
		} else{
			return false;
		}
    });
	
	var name;
	var faaa;
	var hahaha;
	var phone;
	var telephone;
	var userId = "";//快递员id
	//点击快递员蒙版上的确认按钮
	$("#personnelDetermination").click(function() {
		phone="";
		name="";
		hahaha = "";	
		var fatr=$(".tr");	
		
		var qwes=$(".qwe");
		var telephone = new Array();
		for(var z = 0; z < qwes.length; z++){
			qwbesr = qwes[z].childNodes;
			telephone[z] = qwbesr[2].innerHTML;
		}
		for(var i = 0;i < fatr.length; i++){
			var flag = false;
			if(fatr[i].style.color =="rgb(255, 255, 255)"){
				faaa = fatr[i].childNodes;
				for(var jjj = 0; jjj < telephone.length; jjj++){
					if(faaa[2].innerHTML === telephone[jjj]){
						flag = true;
					}
				}
				if(!flag){
					hahaha += "<tr class='qwe'>" + fatr[i].innerHTML + "<td class='cla'><code onClick='tssc(this)'>删除</code></td>" + "</tr>";
				}
				name += faaa[1].innerHTML + ",";
				userId += faaa[2].innerHTML + ",";
				$("#shady").hide();
				$("#personnel").hide();	
			}
		}
		$("#courier").html(name);
		$("#redx2").show();
		//一点击快递员蒙版上的按钮直接拼接到列表
		telephone="";
		$("#tbody1").append(hahaha);
		
		if(hahaha!=""){
			$("#tetb").hide();
		}else{
			$("#tetb").show();
		}
		var clas=$(".cla").html();
		if(clas!=""){
			$("#tetb").hide();
		}else{
			$("#tetb").show();
		}
		hahaha="";
		$("#express").html("");
		$("#courier").html("");
		$("#redx1").hide();
		$("#redx2").hide();
    });
	
	//快递员蒙版上的搜索功能
	$("#findPhone").keyup(function(){
		$("#tbodytr").html("");
		var condition = $(this).val();
		$.post("${pageContext.request.contextPath}/app/fillingSingle/getCourier.html", {"expressId":fallId, "condition" : condition}).done(function(data){
			var ants = "";
			if(data.courierList.length > 0){
				for(var i = 0; i < data.courierList.length; i++){
					ants += "<tr  onClick='trtb(this)' class='tr'>"
					     +"<td>" + data.courierList[i].company_name + "</td>"
					     +"<td>" + data.courierList[i].real_name + "</td>"
					     +"<td>" + data.courierList[i].phone + "</td>"
					+"</tr>";
				}
			} else {
				ants = "<tr>"
	     			+"<td colspan='5'>没找到该快递员</td>"
		 		+"</tr>";
			}
			$("#tbodytr").append(ants);
		}).error(function(data){
			var ants = "<tr>"
			     			+"<td colspan='5'>暂无记录</td>"
				 		+"</tr>";
			$("#tbodytr").append(ants);
		});
	});
	
	//删除
	function tssc(b){
		var c=b.parentNode.parentNode;
        c.parentNode.removeChild(c);
	}

	function trtb(a){
		if($(a).css("color")=="rgb(51, 51, 51)"){
			$(a).css("background","#bf4c28");
			$(a).css("color","#fff");
		}else{
			$(a).css("background","#fff");
			$(a).css("color","#333");
		}
	}
	
	//选择快递公司
	$(".firm").click(function(){
		if($(this).css("color")=="rgb(51, 51, 51)"){
			$(this).css("background","#bf4c28");
			$(this).css("color","#fff");
		}else{
			$(this).css("background","#fff");
			$(this).css("color","#333");
		}
	})
	
	//生成订单
	$("#next").click(function(){
		var childNods = $("#tbody1").children();
		if(childNods.length > 1){
			$("#bigblack2").show();
			$("#yesOrNo").show();
		} else {
			$("#mesg").html("请选择快递公司和快递员");
			$("#mesg").fadeIn(500);
			$("#mesg").fadeOut(2000);
			return false;
		}
	});
	
	//确认派送
	function myConfirm(){
		var user = API.getUserInfo();
		//console.log("快递员电话:"+userId);
		$("#courierPhones").val(userId);//快递员电话
		//console.log("营销中心人员电话:"+user.accountName);
		$("#marketingMobiles").val(user.accountName);//生成该次订单任务的营销中心人员电话
		$("#myform").submit();
	}
	
	//关闭派送
	function myClose(){
		$("#bigblack2").hide();
		$("#yesOrNo").hide();
	}
</script>
