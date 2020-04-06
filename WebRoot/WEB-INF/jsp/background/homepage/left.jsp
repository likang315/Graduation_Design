<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HomePage left.html</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>菜单导航</div>
    
    <dl class="leftmenu">
		<!-left 目录名-->
        <c:forEach items="${resourceslists}" var="treeobject" varStatus="temp">
        	<dd>
			    <div class="title">
			    	<span><img src="${pageContext.request.contextPath}/images/leftico01.png" /></span>${treeobject.name}
			    </div>
				<ul class="menuson">
			    	<c:forEach items="${treeobject.children}" var="child">
			    		<li ><cite></cite><a href="${pageContext.request.contextPath}/${child.resUrl}" target="rightFrame">${child.name}</a><i></i></li>
			    	</c:forEach>
				</ul>
			</dd>
        </c:forEach>
    </dl>
</body>
</html>
