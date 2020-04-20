<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
	<%@include file="/common/common-app-head.jsp" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/logistics.css">
	<style type="">
		
	</style>
</head>
<body class="bgd">
	<header id="weixin_header">
		<script>
			API.settingHeard("订单列表");//修改“个人设置”这个参数为你本页面的功能名
		</script>
	</header>
	<div class="btDiv">按状态和编号查询</div>
	<div class="order">  
   		<select id="orderState">
    		<option value="pleaseSelectVendor">请选择订单状态</option>
    		<option value="2">已发货</option>
    		<option value="3">派送中</option>
    		<option value="6">已送达</option>
        </select>
        &nbsp;
        <input type="text" id="orderNumber" class="oneInput" placeholder="请输入订单编号" >
	</div>
	<div class="btDiv">按日期查询</div>
	<div style="text-align: center;" class="order">
		<input type="date" class="mydate" id="startTime" name="startTime" value="${startTime}" placeholder="请选择开始时间" />
		&nbsp;
		<input type="date" class="mydate" id="endTime" name="endTime" value="${endTime}" placeholder="请选择结束时间" />
		&nbsp;
		<button type="button" id="timeSearch" class="btn btn-primary">确认</button>
	</div>
	<div id="timeError" style="text-align: center; color: red; display: none;">开始时间不能超过结束时间</div>
	<div id="myDiv1" ></div>
	<div id="myDDDiv"></div>
	<!-- 个人中心	底部导航 -->
	<%@include file="/common/leftBottom.jsp" %>
</body>
<script type="text/javascript">
	//初始化加载数据
	var user = API.getUserInfo();
	$(function(){
		API.checkLogin();
		/* $("#startTime").datepicker({
			format: "yyyy-mm-dd",
			language: "zh-CN",
			autoclose: true
		});
			
		$("#endTime").datepicker({
			format: "yyyy-mm-dd",
			language: "zh-CN",
			autoclose: true
		}); */
		var parameter = {"userPhone":user.accountName};
		appendTable(parameter);
	});
	
	//开始时间和结束时间验证
	function myseach(startTime, endTime){
		var time=startTime.replace(/\-/g, "");
		var time1=endTime.replace(/\-/g, "");
		if(time<=time1){
			return true;
		}else{
			return false;
		}
	}
	
	//订单详情
	function lookDetail(orderNumber){
		location.href="${pageContext.request.contextPath}/app/fillingSingle/getOrderDetail.html?orderNumber="+orderNumber;
	}
	
	//订单状态搜索
	$("#orderState").change(function(){
		var startTime = $('#startTime').val();
		var endTime = $("#endTime").val();
		if(myseach(startTime, endTime)){
			var parameter = {"userPhone":user.accountName, "searchMsg":$("#orderNumber").val(), "orderState":$("#orderState").val(), "startTime":startTime, "endTime":$("#endTime").val()};
			appendTable(parameter);
		} else {
			$("#timeError").fadeIn(500);//页面刷新后多长时间显示
			$("#timeError").fadeOut(2000);//显示后多长时间消失
			return false;
		}
	});
	
	//订单编号搜索
	$("#orderNumber").keyup(function(){
		var startTime = $('#startTime').val();
		var endTime = $("#endTime").val();
		if(myseach(startTime, endTime)){
			var parameter = {"userPhone":user.accountName, "searchMsg":$(this).val(), "orderState":$("#orderState").val(), "startTime":startTime, "endTime":endTime};
			appendTable(parameter);
		} else {
			$("#timeError").fadeIn(500);//页面刷新后多长时间显示
			$("#timeError").fadeOut(2000);//显示后多长时间消失
			return false;
		}
	});
	
	//时间查询订单
	$("#timeSearch").click(function(){
		var startTime = $('#startTime').val();
		var endTime = $("#endTime").val();
		if(myseach(startTime, endTime)){
			var parameter = {"userPhone":user.accountName, "searchMsg":$("#orderNumber").val(), "orderState":$("#orderState").val(), "startTime":startTime, "endTime":endTime};
			appendTable(parameter);
		} else {
			$("#timeError").fadeIn(500);//页面刷新后多长时间显示
			$("#timeError").fadeOut(2000);//显示后多长时间消失
			return false;
		}
	});
	
	//数据拼接公用方法
	 function appendTable(parameter){
		$.post("${pageContext.request.contextPath}/app/fillingSingle/getOrderList.html", parameter).done(function(data){
			$("#myDDDiv").html("");
			$("#myDiv1").html("");
			if(data.orders.length > 0){
				var myDiv1 = "";
				var loading = "";
				for(var i = 0; i < data.orders.length; i++){
					myDiv1 +="<div id='myDiv' class='myDivB' onclick = 'lookDetail(\""+data.orders[i].id+"\")'><table>"
								+"<tr>"
									+"<td>订单编号:</td>"
									+"<td colspan = '2'>"+data.orders[i].id+"</td>"
									+"<td></td>"
								+"</tr>"
								+"<tr>"
									+"<td>下单时间:</td>"
									+"<td colspan = '2'><label>"+data.orders[i].createTime+"</label></td>"
									+"<td></td>"
								+"</tr>"
								+"<tr>"
									+"<td>物资名称:</td>"
									+"<td><label>"+data.orders[i].materialContent.substring(0, 1)+"...</label></td>"
									+"<td>物资数量:</td>"
									+"<td><label>"+data.orders[i].materialNumber.substring(0,2)+"...</label></td>"
								+"</tr>"
								+"<tr>"
									+"<td>快递员姓名:</td>"
									+"<td><label>"+data.orders[i].courierName+"</label></td>"
									+"<td>快递员电话:</td>"
									+"<td><label>"+data.orders[i].courierPhone+"</label></td>"
								+"</tr>"
								+"<tr>"
									+"<td>订单状态:</td>"
									+"<td colspan = '3' style = 'text-align: left;'><label><font color='#0C9719'>"+judgeState(data.orders[i].state)+"</font></label></td>"
								+"</tr>"
							+"</table></div>";
				}
				$("#myDiv1").append(myDiv1);
				if(data.count > (3 * data.pageNo)){
					loading = "<button onclick = 'loadMore(\""+data.pageNo+"\")' class='djjzgd'>点击加载更多......</button>";
				}
				if(loading != ""){
					$("#myDDDiv").append(loading);
				} else {
					loading = "";
					$("#myDDDiv").append(loading);
				}
			} else {
				$("#myDiv1").html("暂无记录");
			}
		}).error(function(data){
			$("#myDiv1").html("暂无记录");
		});
	}
	
	function judgeState(state){
		if(state < 3){
			return "已发货";
		} else if(state >= 3 && state < 6){
			return "派送中";
		} else if(state >= 6){
			return "已送达";
		}
	}
	
	function loadMore(pageNo){
		pageNo++;
		var startTime = $('#startTime').val();
		var endTime = $("#endTime").val();
		if(myseach(startTime, endTime)){
			console.log(pageNo);
			var parameter = {"userPhone":user.accountName, "searchMsg":$("#orderNumber").val(), "orderState":$("#orderState").val(), "startTime":startTime, "endTime":endTime, "pageNo":pageNo};
			appendTable(parameter);
		} else {
			$("#timeError").fadeIn(500);//页面刷新后多长时间显示
			$("#timeError").fadeOut(2000);//显示后多长时间消失
			return false;
		}
	}
</script>
</html>
