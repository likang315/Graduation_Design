<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		width:55px;
		text-align:center;
		height:26px;
		line-height:26px;
		border:solid #ccc 1px;
		margin:auto 5px;
		padding:0;
		border-radius: 6px;
		text-indent:0px;
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
	
	
</style>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.idTabs.min.js"></script>


  
<script type="text/javascript">
$(document).ready(function(e) {
   
});

function add(){
	window.location.href="${pageContext.request.contextPath}/background/activity/addUI.html";
}

function deleteAc(id){
	window.location.href="${pageContext.request.contextPath}/background/activity/del.html?id="+id;
}

function  topAc(id){
	window.location.href="${pageContext.request.contextPath}/background/activity/top.html?id="+id;
}

function  cancelTopAc(id){
	window.location.href="${pageContext.request.contextPath}/background/activity/canceltop.html?id="+id;
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
        <form id="chaxun" name="chaxun" action="${pageContext.servletContext.contextPath }/background/activity/listByTime.html" method="post">
    
		    <div class="date_po" style="font-size:14px; color:#888; padding:4px 0 4px 0; border-bottom:1px solid #f5f5f5; ">
					<span><a class="scbtn" href="javascript:void(0)" onclick="searchondate()">查询</a></span>
					<span>结束日期:<input id="endtime" style="border: 1px solid #000;height: 29px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="date" name="endtime" value="${endtime }" /></span>
					<span>开始日期:<input id="starttime" style="border: 1px solid #000;height: 29px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="date" name="starttime" value="${starttime}" /></span>
			</div>
    	</form>
    
    <div class="line">
  	<div id="tab2" class="tabson">
    <div class="toolbar1"></div>
    <form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/activity/list.html" method="post">
    <table class="tablelist">
    	<thead>
    	<tr>
    	<!-- <th width="3%">
              <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()" />
        </th> -->
        <th width="10%">标题</th>
        <th width="8%">结束时间</th>
         <th width="5%">有效标示</th>
        <th width="5%">阅读次数</th>
         <th width="5%">政策/活动</th>
        <th width="5%">分类</th>
        <th width="10%">描述</th>
        <th width="13%">操作</th>
        </tr>
        </thead>  
        <tbody>
        <c:forEach var="key" items="${listActivity}">
        <tr>
         <%-- <td>
              <input type="checkbox" name="check" value="${key.id}" />
         </td> --%>
        <td><a href="${pageContext.servletContext.contextPath }/background/activity/info.html?id=${key.id}">${key.title}</a></td>
        <td>
        	 <c:out value="${fn:substring(key.end_time, 0, 19)}" /> 
        </td>
        
        <td>
        	<c:choose>
        		<c:when test="${key.is_handle == '1'}">
        			<label style="color:green;">正在进行</label>
        		</c:when>
        		<c:when test="${key.is_handle == '2'}">
        			<label style="color:red;">活动结束</label>
        		</c:when>
        	</c:choose>  
        </td>
        <td>${key.counts}</td>
        
        <td>
        	<c:choose>
        		<c:when test="${key.mold == 'A'}">
        			活动
        		</c:when>
        		<c:when test="${key.mold == 'B'}">
        			政策
        		</c:when>
        	</c:choose>
        </td>
        <td>
        	<c:choose>
        		<c:when test="${key.activity_type == 'A'}">
        			套餐
        		</c:when>
        		<c:when test="${key.activity_type == 'B'}">
        			充值
        		</c:when>
        		<c:when test="${key.activity_type == 'C'}">
        			流量
        		</c:when>
        		<c:when test="${key.activity_type == 'D'}">
        			购机
        		</c:when>
        		<c:when test="${key.activity_type == 'E'}">
        			宽带
        		</c:when>
        	</c:choose>
        </td>
        <td>
        	<c:if test="${fn:length(key.description) > 16 }">
        		${fn:substring(key.description,0,16)}...
        	</c:if>
        	<c:if test="${fn:length(key.description) <= 16 }">
        		 ${key.description}
        	</c:if>
       </td>
        <td>
        	<a href="javascript:void(0);" class="link-button" onclick="deleteAc('${key.id }')">删除</a>
        	<c:choose>
        		<c:when test="${key.priority == '2'}">
        		
        			<a href="javascript:void(0);" class="link-button" style="background-color:#35A2DD;color:white" onclick="cancelTopAc('${key.id }')">取消置顶</a>
        		</c:when>
        		<c:otherwise>
        			<a href="javascript:void(0);" class="link-button" onclick="topAc('${key.id }')">置顶</a>
        		</c:otherwise>
        	</c:choose>
        	
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
    </form>
    </div> 
    </div> 
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
    		/* var beginTime=$("#txtBeginTime").datebox("getValue");
          	var endTime=$("#txtEndTime").datebox("getValue");
          	$('#submit_endtime').val(endTime);
          	$('#submit_starttime').val(beginTime);
          	$('#fenye').attr('action', '${pageContext.servletContext.contextPath }/background/activity/readsearch.html'); */
          	//$('#fenye').action = '${pageContext.servletContext.contextPath }/background/activity/readsearch.html';
          	$('#chaxun').submit();
      }
      
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	</script>
    </div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
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
