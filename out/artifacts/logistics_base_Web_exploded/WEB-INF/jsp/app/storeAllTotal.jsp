<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE >
<html>
	<head>
		<title>门店-需求上报列表</title>
		<%@include file="/common/common-app-head.jsp" %>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/logistics.css">
		<script src="${pageContext.request.contextPath}/js/moment-with-locales.js"></script>
</head>
<style>

 .jiazai{height: 40px; 
    line-height: 40px; 
    text-align: center; 
     background: green;
    color: #fff;
    margin-top: 10px;
    margin-bottom: 5px;
    width: 95%;
    margin-left: 2.5%}
</style>
<body class="bjs-gary">
	<!-- header -->
		<header id="weixin_header">
			<script>
				API.settingHeard("需求上报列表");//修改“个人设置”这个参数为你本页面的功能名
			</script>
		</header>
		<div class="btDiv">按状态查询</div>
		 <div class="order" style=" text-align: left; padding-left: 10px;">
		<select  id="xuanze" style="width:60%;">
		  <option value ="0"  > 请选择上报审核状态</option>
		  <option value ="1" <c:if test="${state==1}">selected </c:if>>未审核</option>
		  <option value ="2"  <c:if test="${state==2}">selected </c:if>>审核通过</option>
		  <option  value ="3"  <c:if test="${state==3}">selected </c:if>>审核不通过</option>
		</select>
		</div> 
		<div class="btDiv">按日期查询</div>
		<div class="order">
			<input type="date" class="mydate" id="startTime" name="startTime" value="${startTime}" placeholder="请选择开始时间" />
			&nbsp;
			<input type="date" class="mydate" id="endTime" name="endTime" value="${endTime}" placeholder="请选择结束时间" />
			&nbsp;
			<button type="button" id="timeSearch" class="btn btn-primary">确认</button>
	   </div>
		<div id="timeError" style="text-align: center; color: red; display: none;">开始时间不能超过结束时间</div>
		<div id="tipInfo" style="text-align: center; color: red; display: none;"></div>
		<%-- <c:choose>
			<c:when test="${not empty map}">
		<c:forEach  items="${map}"  var="map" >
				  <div class="myDivB">
				           <table>
								<tr>
									<td><label>上报时间:</label></td>
									<td><fmt:formatDate value="${map.report_time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									</tr>
								<tr>	
									<td><label>上报物资名称:</label></td>
									<td>${map.materialContent}</td>
								</tr>
								<tr>
									<td><label>上报物资数量:</label></td>
									<td>${map.materialNumber}</td>
									<td><label>审核状态:</label></td>
									<td style="color: green;" ><c:if test="${map.examine==0}"> 未审核</c:if>
						   <c:if test="${map.examine==1}"> 审核通过</c:if><c:if test="${map.examine==2}"> 审核不通过</c:if></td>
								</tr>
							
						</table>
					 </div>		          
					</c:forEach>		
					
					</c:when>
			<c:otherwise>
				<div class="myDivB">
					暂无记录
				</div>
			</c:otherwise>
		</c:choose> --%>	
		<div id="myDiv1" ></div>
		
				<div class="jiazai" id="dianjigengduo"></div>
						
				
			<!-- 个人中心	底部导航 -->
			<%@include file="/common/leftBottom.jsp" %>
			</body>
		</html>
		<script type="text/javascript">
		 var user=API.getUserInfo();
		 var accountName=user.accountName;
		 var channel_code=user.code;
		 var jiazai=0;
		 var info = '${info}';
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
				var parameter = {"channel_code":user.code,"jiazai":jiazai};
				appendTable(parameter);
		});
		
		function myseach(startTime, endTime){
			var time=startTime.replace(/\-/g, "");
			var time1=endTime.replace(/\-/g, "");
			if(time<=time1){
				return true;
				}else{
					return false;
			}
		}
		window.onload =  function(){
			var span = document.getElementById("tipInfo");
			span.innerHTML = info;
			$("#tipInfo").fadeIn(500);//页面刷新后多长时间显示
			$("#tipInfo").fadeOut(2000);//显示后多长时间消失
			
		}; 

		
		$("#xuanze").change(function(){
				var state=$("#xuanze").val();
				var startTime=$('#startTime').val();
				var endTime=$("#endTime").val();
				if(xuanze!="0"){
					$("#myDiv1").html("");
				}
				jiazai=0;
				if(myseach(startTime, endTime)){
					var parameter = {"channel_code":channel_code, "state":state, "startTime":startTime, "endTime":endTime,"jiazai":jiazai};
					appendTable(parameter);
					}else {
						$("#timeError").fadeIn(500);//页面刷新后多长时间显示
						$("#timeError").fadeOut(2000);//显示后多长时间消失
						return false;
				}
		});
		
		//时间查询订单
		$("#timeSearch").click(function(){
				var startTime=$('#startTime').val();
				var state=$("#xuanze").val();
				var endTime=$("#endTime").val();
				$("#myDiv1").html("");
				var jiazai=0;
				$("#dianjigengduo").hide();
				if(myseach(startTime, endTime)){
					var parameter = {"channel_code":channel_code,  "state":state, "startTime":startTime, "endTime":endTime,"jiazai":jiazai};
					appendTable(parameter);
					}else {
						$("#timeError").fadeIn(500);//页面刷新后多长时间显示
						$("#timeError").fadeOut(2000);//显示后多长时间消失
						return false;
				}
		});
		$("#dianjigengduo").click(function(){
			var startTime=$('#startTime').val();
			var state=$("#xuanze").val();
			var endTime=$("#endTime").val();
			$("#dianjigengduo").html("正在加载...");
			jiazai+=5;
			var parameter = {"channel_code":channel_code,  "state":state, "startTime":startTime, "endTime":endTime,"jiazai":jiazai};
			 appendTable(parameter);
	})
		function  appendTable(parameter){
				$.post("${pageContext.request.contextPath}/app/storeSupplies/StoreAllTotal.html", parameter).done(function(data){
					$("#dianjigengduo").hide();
					if(data.map.length > 0){
						var myDiv1 = "";
						var loading = "";
						for(var i = 0; i < data.map.length; i++){
							var datet= new moment(data.map[i].report_time).format("YYYY-MM-DD HH:mm:ss");
							  if (data.count<=(data.jiazai+5)||data.map.length<5){
									 $("#dianjigengduo").hide();
									
								}else{
									$("#dianjigengduo").show();
								    $("#dianjigengduo").html("点击加载更多 ");
						    	}
							  myDiv1 +="<div  class='myDivB' style='text-align: center;' onclick = 'tiaozhuan(\""+data.map[i].id+"\",\""+data.map[i].channel_code+"\")'><table>"
										+"<tr>"
											+"<td style = 'text-align: right; padding-right:15px;'><label>上报时间:</label></td>"
											+"<td style = 'text-align: left;'>"+datet+"</td>"
										+"</tr>"
										+"<tr>"
											+"<td style = 'text-align: right; padding-right:15px;'><label>上报人电话:</label></td>"
											+"<td style = 'text-align: left;'>"+data.map[i].store_shopowner_phone+"</td>"
										+"</tr>"
										+"<tr>"
											+"<td style = 'text-align: right; padding-right:15px;'><label>上报物资名称:</label></td>"
											+"<td style = 'text-align: left;'><label>"+data.map[i].materialContent.substring(0, 1)+"...</label></td>"
										+"</tr>"
											+"<td style = 'text-align: right; padding-right:15px;'><label>上报物资数量:</label></td>"
											+"<td style = 'text-align: left;'><label>"+data.map[i].materialNumber.substring(0,2)+"...</label></td>"
										+"</tr>"
										+"<tr>"
											+"<td style = 'text-align: right; padding-right:15px;'><label>审核状态:</label></td>"
											+"<td colspan = '3' style = 'text-align: left;'><font color='#0C9719'>"+judgeState(data.map[i].examine)+"</font></td>"
										+"</tr>"
									+"</table></div>";
						}
						
						 
							$("#myDiv1").append(myDiv1 );
						
					} else {
						$("#myDiv1").html("暂无记录");
					}
				}).error(function(data){
					$("#myDiv1").html("暂无记录");
				});

			}
		function judgeState(examine){
			if(examine == 0){
				return "未审核";
			} else if(examine == 1){
				return "审核通过";
			} else if(examine == 2){
				return "审核驳回";
			} else if(examine == 3){
				return "审核通过";
			} else if(examine == 4){
				return "审核驳回";
			}
		}

		function tiaozhuan(id,channel_code){
			
			window.location.href="${pageContext.request.contextPath}/app/storeSupplies/storeAllDetails.html?channel_code="+channel_code+"&&courierPhone="+user.accountName+"&&id="+id;
		}
		</script>



