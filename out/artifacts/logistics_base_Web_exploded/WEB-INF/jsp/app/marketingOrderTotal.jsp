<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
	<%@include file="/common/common-app-head.jsp" %>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/logistics.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script>

</head>
<style>
	.container{padding:0;}
	.saefas{
		width:100%;
		height:50px;
		background:#FFF;
		text-align:center;
		font-size:20px;
	}
	b{
		font-size:35px;
		color:#F00;
	}
	.casd{
		width:100%;
		height:250px;
	}

</style>
<body class="bjs-gary">
		<header id="weixin_header">
			<script>
				API.settingHeard("订单派送统计");//修改“个人设置”这个参数为你本页面的功能名
			</script>
		</header>
		
		<div class="order" style=" margin-top: 15px;">
			<input type="date" class="mydate" id="startTime" name="startTime" value="${startTime}" placeholder="请选择开始时间" />
			&nbsp;
			<input type="date" class="mydate" id="endTime" name="endTime" value="${endTime}" placeholder="请选择结束时间" />
			&nbsp;
			<button type="button" id="timeSearch" class="btn btn-primary">确认</button>
		</div>
		<div style="text-align: center; color: red; height: 20px; font-size: 12px;">
			<label id="timeError" style="display: none;">开始时间不能超过结束时间</label>
		</div>
		<div class="casd" id="seven"></div>
	<!-- 个人中心	底部导航 -->
	<%@include file="/common/leftBottom.jsp" %>
</body>
</html>
<script type="text/javascript">
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
	})
	
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
		var startTime = $('#startTime').val();
		var endTime = $("#endTime").val();
		if(myseach(startTime, endTime)){
			var userPhone = "${userPhone}";
			location.href="${pageContext.request.contextPath}/app/fillingSingle/getMarketingOrderTotal.html?userPhone="+userPhone+"&startTime="+startTime+"&endTime="+endTime;
		} else {
			$("#timeError").fadeIn(500);//页面刷新后多长时间显示
			$("#timeError").fadeOut(2000);//显示后多长时间消失
			return false;
		}
	});
	
    var seven = echarts.init(document.getElementById('seven'));
    // 指定图表的配置项和数据	
	var option = {
		backgroundColor: '#ffffff',		
	    title : {
	        text: '订单统计',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: ['已发货','派送中','已送达']
	    },
    series : [
        {
            name: '统计量',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                  <c:choose>
                  	<c:when test="${not empty totals}">
						<c:forEach items="${totals}" var="c">
							{value:'${c.con}', name:'${c.myState}'},
						</c:forEach>
                  	</c:when>
                  	<c:otherwise>
                  		{value:'0', name:'暂无记录'},
                  	</c:otherwise>
                  </c:choose>
                ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
    
//使用刚指定的配置项和数据显示图表。
seven.setOption(option);
window.onresize = seven.resize;

//跳转到列表页面
$("#chakan").click(function(){
	location.href="${pageContext.request.contextPath}/app/fillingSingle/orderList.html";
})

</script>
