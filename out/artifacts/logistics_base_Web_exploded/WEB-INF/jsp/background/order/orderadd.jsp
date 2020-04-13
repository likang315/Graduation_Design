<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%@include file="/common/common-css.jsp" %>
<%@include file="/common/common-js.jsp" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.min.js"></script>

 <style type="text/css">
.error{color:red ;background:none;padding-top:0;width:345px; margin-top:0px;}
 </style>

</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">我的CRM</a></li>
     <li><a href="#">订单维护</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab_nav">
  	<ul> 
    <li><a href="#tab1" id="importTag" >订单批量维护</a></li>
  	</ul>
    </div>
    <form id="fileForm" action="${pageContext.servletContext.contextPath }/background/order/addOrder.html" method="post" enctype="multipart/form-data">
    <div id="divStoreId">
   <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF"  class="tablelist_left">
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>选择文件：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
         <input type="file" name="file" id="file" class="dfinput"/><div id="tip"></div>
       </div>
     </div></td>
     </tr>
    <tr>
     <td height="54" bgcolor="#FFFFFF"><div align="center"></div></td>
     <td height="54" bgcolor="#FFFFFF"><input type="button" class="btn-primary btn-sm" id="submit1" value="确认维护" />
       <input class="btn-primary  btn-sm" onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt1" type="button" class="btn" value="　返　回　" />
       <input type="button" value="下载模板" class="btn-primary  btn-sm"
       	onclick="javascript:window.location.href=encodeURI(encodeURI('${pageContext.servletContext.contextPath }/background/report/downDomeFile.html?fileName=订单维护模板.xlsx'))"/>
       </td>
      <%--  <td height="54" bgcolor="#FFFFFF"><input type="button" value="下载模板"
       	onclick="javascript:window.location.href=encodeURI(encodeURI('${pageContext.servletContext.contextPath }/background/report/downDomeFile.html?fileName=订单维护模板.xlsx'))"
       /></td> --%>
   </tr>
     </table>
    </div>
    </form>
    
	</div> 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

    </div>



</body>
<script>
	//当选择的文件发生变化时
	$("#file").change(function(){
		var name = $("#file").val();
		var yz = /^.*\.(?:xls|xlsx)$/;
		if(yz.test(name)){
			$("#tip").empty();
		} else {
			$("#tip").html("<font color='red'>您选择的文件不符合规范!</font>")
		}
	})
	
	$("#submit1").click(function(){
		var name = $("#file").val();
		var yz = /^.*\.(?:xls|xlsx)$/;
		if(yz.test(name)){
			//如果验证成功,进行提交表单的操作
			$("#tip").empty();
			$("#fileForm").submit();
		} else {
			$("#tip").html("<font color='red'>您选择的文件不符合规范!</font>")
		}
	})
	
	$("#sigStore").click(function(){
		$("#divStoreId").hide();
		$("#tab2").show();
		
	})	

	$("#importTag").click(function(){
		$("#divStoreId").show();
		$("#tab2").hide();
	})
	
</script>
</html>
