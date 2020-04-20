<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@include file="/common/taglib.jsp" %>
<%@include file="/common/common-css.jsp" %>
<%@include file="/common/common-js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${pageContext.servletContext.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.servletContext.contextPath }/css/select.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.servletContext.contextPath }/css/bootstrap.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.link-button{
		display:block;
		float:left;
		backgroud:#ccc;
		width:60px;
		text-align:center;
		height:26px;
		line-height:26px;
		border:solid #ccc 1px;
		margin:auto 5px;
		padding:0;
		border-radius: 6px;
	
	}
	
	.date_po{
		height:24px;
		border:solid #ccc 1px;
	}
	
	.date_po span{
		position:relative;
		float:right;
		margin:auto 10px;
	}
	.scbtn{
		display:block;
		font-size:12px;
		width:40px;
		height:20px;
		text-align:center;
		line-height:20px;
		color:white;
	}
	.scbtn:hover{
		color:white;
	}
	
	table.list {
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #666666;
		border-collapse: collapse;
		width:100%;
	}
	table.list th {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #dedede;
	}
	table.list td {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #666666;
		background-color: #ffffff;
	}
	
	#popup{
		border:2px solid #ccc;
		width:400px;
		height:200px;
    	border-radius: 15px;
    	padding:2px;
    	background-color:#FAFCFB;
    	position:fixed;
    	top:20%;
    	left:35%;
    	display:none;
	}
</style>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.idTabs.min.js"></script> 
<script type="text/javascript">

function add(){
	window.location.href="${pageContext.request.contextPath}/background/activity/addUI.html";
}

function scaninfo(id){
	window.location.href="${pageContext.servletContext.contextPath }/background/activity/readinfo.html?id="+id;
}

</script>
</head>

<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">政策管理</a></li>
     <li><a href="#">政策列表</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="toolbar1">
    	<ul class="toolbar">
        <li class="click"><span><a href="javascript:add();"><img src="${pageContext.servletContext.contextPath }/images/t01.png" /></a></span><a href="javascript:add();">发布政策</a></li>       
    	</ul>
    </div>
    <div class="itab_nav">
  	<ul> 
    <li><a href="#tab1" class="selected">政策列表</a></li>
  	</ul>
    </div>
    <form id="fenye" action="${pageContext.servletContext.contextPath }/background/activity/readsearch.html">
    <div class="date_po" style="font-size:14px; color:#888; padding:4px 0 4px 0; border-bottom:1px solid #f5f5f5; ">
			<span><a class="scbtn" href="javascript:void(0)" onclick="searchondate()">查询</a></span>		
			<span>结束日期:<input id="endtime" style="border: 1px solid #000;height: 29px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="date" name="endtime" value="${endtime }" /></span>
			<span>开始日期:<input id="starttime" style="border: 1px solid #000;height: 29px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="date" name="starttime" value="${starttime}" /></span>
	</div>
    
    
    <div class="line">
  	<div id="tab2" class="tabson">
    <div class="toolbar1"></div>
		    <table class="list">
		    	<thead>
		    	<tr>
		        <th width="10%">标题</th>
		         <th width="8%">阅读人数</th>
		        <th width="5%">阅读次数</th>
		        <th width="5%">阅读占比</th>
		        <th width="10%">开始时间</th>
		        <th width="10%">结束时间</th>
		        <th width="5%">操作</th>
		        </tr>
		        </thead>
		        <tbody>
		        <c:forEach var="key" items="${listPolicy}">
		        <tr>
		        <td><a href="${pageContext.servletContext.contextPath }/background/activity/readinfo.html?id=${key.id}">${key.title}</a></td>
		        
		         <td style="text-align:right;">
		        	${key.readcounts}
		        </td>
		        <td style="text-align:right;">${key.counts}</td>
		         <td style="text-align:right;">
		         	${key.readcounts}/${users }
		         </td>
		        <td style="text-align:center;">
		        	 <c:out value="${fn:substring(key.start_time, 0, 19)}" /> 
		        </td>
		        <td style="text-align:center;">
		        	 <c:out value="${fn:substring(key.end_time, 0, 19)}" /> 
		        </td>
		        <td>
		        	<a href="javascript:void(0);" class="link-button" onclick="scaninfo('${key.id }')">查看详情</a>
				</td>
		        </tr> 
		        </c:forEach>
		        </tbody>
		    </table>
    
		    <div class="statistics pull-left">
					  共<b style="color:#337ab7"> ${page.totalCount} </b>条记录，当前显示<b style="color:#337ab7"> ${page.pageNo}/${page.totalPages } </b>页
			</div>
			<div class="pagination pull-right">
					<ul id="page_demo"></ul>
			</div>
  
    </div> 
    </div> 
      </form>
	</div> 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
      
      
      function ww3(date){  
          var y = date.getFullYear();  
          var m = date.getMonth()+1;  
          var d = date.getDate();  
          var str = y+'/'+(m<10?('0'+m):m)+'/'+(d<10?('0'+d):d);  
          return str;  
      }  
      function w3(s){  
          if (!s) return new Date();  
          var y = s.substring(0,4);  
          var m =s.substring(5,7);  
          var d = s.substring(8,10);  
          if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
              return new Date(y,m-1,d);  
          } else {  
              return new Date();  
          }  
      }     
      function getValue(){
    	  var date = new Date();  
    	  var y = date.getFullYear();  
          var m = date.getMonth()+1;  
          var d = date.getDate();  
          var str = y+'/'+(m<10?('0'+m):m)+'/'+(d<10?('0'+d):d);  
          return str; 
      }
      function searchondate(){       	     		
          	$('#fenye').submit();
      }
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    </div>
</body>

<div id="popup" >
	<div style="width:100%;height:35px;background-color:#B8CBD1;border-top-left-radius: 15px;border-top-right-radius: 15px; line-height: 35px; font-size: 18; color: blueviolet;">&nbsp;&nbsp;Are You Sure?</div>
	<div style=" border-bottom: 25px solid orange;
    border-left: 20px solid transparent;
    border-right: 20px solid transparent;
    width: 0px;
    height: 0px;
    font-size: 20px;
    font-weight: 900;
    padding-right: 5px;
    margin-top: 20;
    margin-left: 25px;
    float:left;">!</div>
    <div style="float:left;    margin-top: 20px;
    margin-left: 15px;
    font-size: 18px">开始时间不能大于结束时间！</div>
	<div style="    width: 80px;
    border: 1px solid green;
    border-radius: 5px;
    height: 25px;
    line-height: 25px;
    margin-top: 100px;
    text-align: center;
    margin-left: 160px;
    font-weight: 900;
    cursor: pointer;
    background-color:#BCE184" onclick="javascript:$('#popup').hide();">OK</div>
</div>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.twbsPagination.min.js"></script>
<script type="text/javascript">
$("#page_demo").twbsPagination({
	totalPages:"${page.totalPages}",//总页数
  	visiblePages:5,//导航页个数
	first:'首页',
 	last:'末页',
 	prev:'上一页',
	next:'下一页',
	/* href:"?pageNo={{number}}" */
	href:"?pageNo={{number}}&starttime=${starttime}&endtime="+$("#endtime").val()
});
</script>
</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>

</script>