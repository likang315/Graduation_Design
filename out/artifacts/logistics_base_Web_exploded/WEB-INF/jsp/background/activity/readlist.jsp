<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%@include file="/common/common-css.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${pageContext.servletContext.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.servletContext.contextPath }/css/select.css" rel="stylesheet" type="text/css" />
<style type="text/css">
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
	
	.download:hover{
		 cursor:pointer;
	}
	
	
</style>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.idTabs.min.js"></script>


  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
function modifyUser(id){
	window.location.href="${pageContext.request.contextPath}/foreground/news/editUI.html?accountId="+id;
}


function showrole(userId){
	 var url = "${pageContext.servletContext.contextPath }/background/newsnews/queryforuser.html?userId="+userId;
	 var h_sp1 = 400;
	 var w_sp1 = 800;
	//兼容IE，firefox,google.模态窗口居中问题
	 var iTop2 = (window.screen.availHeight - 20 - h_sp1) / 2;
	 var iLeft2 = (window.screen.availWidth - 10 - w_sp1) / 2;
	 var params = 'menubar:no;dialogHeight=' + h_sp1 + 'px;dialogWidth=' + w_sp1 + 'px;dialogLeft=' + iLeft2 + 'px;dialogTop=' + iTop2 + 'px;resizable=yes;scrollbars=0;resizeable=0;center=yes;location:no;status:no;scroll:no'
	// window.showModalDialog(url, window, params);
	window.open(url, window, params);
	 //location.href=url;
}
</script>
</head>

<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">政策管理</a></li>
     <li><a href="#">阅读明细</a></li>
    </ul>
    </div>
    <div class="formbody">
    
    <div id="usual1" class="usual"> 
    
    <div class="itab_nav">
  	<ul> 
    <li><a href="#tab1" class="selected">阅读明细</a></li>
  	</ul>
    </div>
    <div class="line">
  	<div id="tab2" class="tabson">
    <div class="toolbar1"></div>
    
    <h1 style="font-size:20px;">
    	${activity.title}
    </h1>
    
	<div style="font-size:14px; color:#888; padding:4px 0 4px 0; border-bottom:1px solid #f5f5f5; ">
		活动截止日期:${fn:substring(activity.end_time,0,19)}<span id="download" class="download" style="float:right;border:1px solid blue;border-radius:2px;"  >下载清单</span>
	</div>
<%-- 	<div style="font-size:16px; margin:18px 0; line-height: 20px;">${activity.content}</div> --%>
	
	<div>
	<form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/activity/readinfo.html" method="post">
   	<table class="list">
   		<thead>
   			<tr>
   				<th>活动标题</th>
   				<th>用户名称</th>
   				<th>开始时间</th>
   				<th>结束时间</th>
   				<th>是否阅读</th>
   				<th>阅读时间</th>
   			</tr>
   		</thead>
   		<tbody>
   			<c:forEach items="${pageView.records}" var="key">
   			<c:if test="${!empty  key.accountName}">
   			<tr>
   				<td>${activity.title}</td>
   				<td>${key.accountName}</td>
   				<td>${fn:substring(activity.start_time,0,19)}</td>
   				<td>${fn:substring(activity.end_time,0,19)}</td>
   				<td>
   					<c:if test="${flag == '1' }">
   						<label style="color:green;">已读</label>
   					</c:if>
   					<c:if test="${flag != '1' }">
   						<label style="color:red;">未读</label>
   					</c:if>
   				</td>
   				<td>${key.read_time}</td>
   			</tr>
   			</c:if>
   			</c:forEach>
   		</tbody>
   	</table>
   <div class="pagin">
    	<div class="message">共<i class="blue">${pageView.rowCount }</i>条记录，当前显示第&nbsp;<i class="blue">${pageView.pageNow }&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:pageSubmit(${pageView.pageNow-1 });"><span class="pagepre"></span></a></li>
      
        <c:forEach var="x" begin="${pageView.prePageStart }" end="${pageView.prePageEnd }">
        	<c:choose>
        		<c:when test="${x==pageView.pageNow }">
        			<li class="paginItem"><a href="javascript:;">${x }</a></li>
        		</c:when>
        		<c:otherwise>
        			<li class="paginItem"><a href="javascript:pageSubmit(${x });">${x }</a></li>
        		</c:otherwise>
        	</c:choose>
        	
        </c:forEach>
        <li class="paginItem"><a href="javascript:;">...</a></li>
         <c:forEach var="x" begin="${pageView.pnexPageStart }" end="${pageView.pnexPageEnd }">
        	<c:choose>
        		<c:when test="${x==pageView.pageNow }">
        			<li class="paginItem"><a href="javascript:;">${x }</a></li>
        		</c:when>
        		<c:otherwise>
        			<li class="paginItem"><a href="javascript:pageSubmit(${x });">${x }</a></li>
        		</c:otherwise>
        	</c:choose>
        	
        </c:forEach>
        
        <li class="paginItem"><a href="javascript:pageSubmit(${pageView.pageNow+1 });"><span class="pagenxt"></span></a></li>
        </ul>
        <input type="hidden" name="pageSize" value="${pageView.pageSize}"/>
        <input type="hidden" name="pageNow" value="${pageView.pageNow}" id="pageNow"/>
        
        <input type="hidden" name="endtime" value="${end}" id="submit_endtime"/>
    	<input type="hidden" name="starttime" value="${start}" id="submit_starttime"/>
    </div>
    </form>
   
	</div>
	
    </div> 
    </div> 
    
   
    
    <form id="dl" action="${pageContext.servletContext.contextPath }/background/activity/download.html" method="post">
    	<input type="hidden" value="${tail}" name="tail"/>
    	<input type="hidden" value="${activity.id}" name="id"/>
    	<input type="hidden" value="${qingdan}" name="fileName"/>
    </form>
    
	</div> 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
      
      $('#download').click(function(){
    	  $('#dl').submit();
      });
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    </div>
</body>
</html>
