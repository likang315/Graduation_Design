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
     <li><a href="#">物资维护</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab_nav">
  	<ul> 
    <li><a href="#tab1" id="sigStore" class="selected">物资维护</a></li>
  	</ul>
    </div>
    <div class="line" >
    <div id="tab2" class="tabson">
    <div class="toolbar1"></div>
 <div class="toolbar1"></div>
 <form id="addForm" action="${pageContext.servletContext.contextPath }/background/Material/add.html" method="post" enctype="multipart/form-data">
 <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF"  class="tablelist_left">
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>物资名称：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input name="material_name" id="material_name" class="dfinput" onblur="check()"/>
       <label id="tipMaterial_name"></label>
     </div></td>
     </tr>
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>物资编码：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input name="code" id="code" class="dfinput" value="${code }" onblur="check()"/>
       <label id="tipCode"></label>
     </div></td>
     </tr>
     
     <td height="54" bgcolor="#FFFFFF"><div align="center"></div></td>
     <td height="54" bgcolor="#FFFFFF"><input type="button" id="sub" value="　保　存　" />
       <input onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt" type="button" value="　返　回　" /></td>
   </tr>
 </table>
 </form>
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

<script>

function check(){
	var material_name = $("#material_name").val();
	var code = $("#code").val();
	if(material_name == null || material_name == "") {
		$("#tipMaterial_name").html("<font color='red'>物资名称不能为空</font>")
		return false;
	} else if(code == null || code == "") {
		$("#tipMaterial_name").empty();
		$("#tipCode").html("<font color='red'>物资编码不能为空</font>")
		return false;
	} else {
		$("#tipMaterial_name").empty();
		$("#tipCode").empty();
		return true;
	}
	
}
//提交
$("#sub").click(function(){
	if(check()){
		$("#addForm").submit();
	}
})


	

</script>



<script>
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
