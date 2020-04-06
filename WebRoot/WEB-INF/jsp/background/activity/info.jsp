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
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/app/ueditor/ueditor.parse.js"></script>


  
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
     <li><a href="#">政策明细</a></li>
    </ul>
    </div>
    <div class="formbody">
    
    <div id="usual1" class="usual"> 
    
    <div class="itab_nav">
  	<ul> 
    <li><a href="#tab1" class="selected">政策明细</a></li>
  	</ul>
    </div>
    <div class="line">
  	<div id="tab2" class="tabson">
    <div class="toolbar1"></div>
    
    <h1 style="font-size:20px;">
    	${info.title}
    </h1>
    
	<div style="font-size:14px; color:#888; padding:4px 0 4px 0; border-bottom:1px solid #f5f5f5; ">
		${fn:substring(info.end_time,0,19)}
	</div>
	<div style="font-size:16px; margin:18px 0; line-height: 20px;" name="content">${info.content}</div>
	
    </div> 
    </div> 
	</div> 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    </div>
</body>
<script type="text/javascript">

    uParse("#content",{rootPath:"${pageContext.request.contextPath}"});

</script>
</html>
