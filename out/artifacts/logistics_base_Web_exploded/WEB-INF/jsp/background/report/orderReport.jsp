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
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/echarts.js"></script>
</head>
<body>
	<div class="place">
    	<span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">首页</a></li>
		    <li><a href="#">物流管理</a></li>
			<li><a href="#">订单配送统计</a></li>
	    </ul>
    </div>
    <div class="formbody">
		<div id="usual1" class="usual">
			<div class="itab_nav">
				<ul> 
					<li><a href="#tab1" class="selected">订单配送统计</a></li>
				</ul>
			</div>
			<div class="line">
				<div style="font-size: 12px; width: 100%; text-align: right; margin-bottom: 10px;">
					<form  id = "myform" action="${pageContext.request.contextPath }/background/report/orderInit.html" method="post">
				    	请选择起始日期:
						<input id="startDate" style="border: 1px solid #000;height: 29px;width: 75px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="text" name="startDate" value="${startDate }" />
						请选择结束日期:
						<input id="endDate" style="border: 1px solid #000;height: 29px;width: 75px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="text" name="endDate" value="${endDate }" />
						<button type="button" id="grantBtn" class="btn btn-primary">确定</button>
				    </form>
			    </div>
			    <div id="seven" style="width:98%;height:400px;"></div>
			</div>
		</div>
    </div>
    
</body>
</html>
<script type="text/javascript">
$("#startDate").datepicker({
		format: "yyyy-mm-dd",
		language: "zh-CN",
		autoclose: true
	});
	
$("#endDate").datepicker({
		format: "yyyy-mm-dd",
		language: "zh-CN",
		autoclose: true
	});

    var seven = echarts.init(document.getElementById('seven'));

    // 指定图表的配置项和数据	
	var option = {
    title : {
        text: '订单配送统计',
        subtext: '',
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
            name: '统计',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:
            	<c:choose>
        			<c:when test='${not empty ls}'>
	            		${ls},
        			</c:when>
	            	<c:otherwise>
	            		[{value:"", name:"暂无记录"}],
	            	</c:otherwise>
            	</c:choose>
           
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
// 使用刚指定的配置项和数据显示图表。
seven.setOption(option);
window.onresize = seven.resize;

//时间确认查询
$("#grantBtn").click(function(){
	var startTime=$("#startDate").val();
	var endTime=$("#endDate").val();
	var time=startTime.replace(/\-/g, "");
	var time1=endTime.replace(/\-/g, "");
	if(time<=time1){
		$("#myform").submit();
	}else{
		alert("起始日期不能大于结束日期");
		return false;
	}
});
</script>
