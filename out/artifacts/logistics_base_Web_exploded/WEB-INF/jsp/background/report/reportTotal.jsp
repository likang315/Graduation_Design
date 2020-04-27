<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/date.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>订单配送统计</title>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/API.js"></script>
	<style>
	</style>
</head>
<body class="bjs-gary">
		<div class="place">
    	<span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">首页</a></li>
		    <li><a href="#">物流管理</a></li>
			<li><a href="#">订单配送统计</a></li>
	    </ul>
    </div>
		<div id="usual1" class="usual">
			<div class="itab_nav">
				<ul> 
					<li><a href="#tab1" class="selected">订单配送统计</a></li>
				</ul>
			</div>
			<div class="line">
				<div style="font-size: 12px; width: 100%; text-align: right; margin-bottom: 10px;">
					<form  id = "myform" action="${pageContext.request.contextPath }/background/report/getReportTotal.html" method="post">
				    	请选择起始日期:
						<input type="text" class="mydate" style="border: 1px solid #000;height: 29px;width: 75px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="text" id="startTime" name="startTime" value="${startTime }" />
						请选择结束日期:
						<input type="text" class="mydate" style="border: 1px solid #000;height: 29px;width: 75px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="text"  id="endTime"  name="endTime" value="${endTime }" />
						<button type="button" id="timeSearch" class="btn btn-primary">确定</button>
						<div id="timeError" style="text-align: center; color: red; display: none;">开始时间不能超过结束时间</div>
				    </form>
			    </div>
	<div class="maxDiv">
            <table  class="table table-striped table-hover" style="border:1px #ccc solid;">
                <tr>
                    <td>渠道编码</td>
                    <td>渠道名称</td>
                    <td  >配货次数</td>
                    <td >妥投次数</td>
                <c:forEach items="${ls.get(0).materialAndNumLs }" var="l">
                		<td>${l.material_name } </td>
                </c:forEach>
                  
                </tr>
                
            <c:forEach items="${ls }" var="l">
            <tr>
                    <td>${l.channel_code }</td>
                    <td>${l.store }</td>
                    	<td >${l.OrderAll }</td>
            			<td >${l.doneOrder }</td>
		                   <c:forEach items="${l.materialAndNumLs }" var="s">
		            		<c:if test="${s.materialNameNum == null }">
		            			<td>0</td>
		            		</c:if>
		            		<c:if test="${s.materialNameNum != null }">
		            			<td>${s.materialNameNum }</td>
		            		</c:if>
            		</c:forEach>
            		</tr>
			</c:forEach>
			
            </table>
		</div>
    </div>
    
   
</div>
</body>
</html>
<script>
	
//日期插件
$(function(){
	$("#startTime").datepicker({
		format: "yyyy-mm-dd",
		language: "zh-CN",
		autoclose: true
	});
		
	$("#endTime").datepicker({
		format: "yyyy-mm-dd",
		language: "zh-CN",
		autoclose: true
	});
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
	//时间查询
	$("#timeSearch").click(function(){
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if(myseach(startTime,endTime)){
			$("#myform").submit();
		} else {
			$("#timeError").fadeIn(500);
			$("#timeError").fadeOut(2000);
			return false;
		}
	});
	
	/* $("#myTable03").fixedHeaderTable({
		altClass: 'odd',
		footer: true,
		fixedColumns: 2,
	}); */
	
	
</script>