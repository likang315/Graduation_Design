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
     <li><a href="#">快递员信息维护</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab_nav">
  	<ul> 
    <li><a href="#tab1" id="sigStore" class="selected">快递员信息单个维护</a></li>
    <li><a href="#tab1" id="importTag" >快递员信息批量维护</a></li>
  	</ul>
    </div>
    <div class="line" >
    <div id="tab2" class="tabson">
    <div class="toolbar1"></div>
 <div class="toolbar1"></div>
 <form id="addForm" action="${pageContext.servletContext.contextPath }/background/Express/addCourier.html" method="post" enctype="multipart/form-data">
 <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF"  class="tablelist_left">
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>快递员姓名：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input name="real_name" id="real_name" class="dfinput" onblur="check()"/>
       <label id="checkName"></label>
     </div></td>
     </tr>
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>快递员电话：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
         <input name="accountName" id="accountName" class="dfinput" onblur="check()"/>
         <label id="checkAccount"></label>
       </div>
     </div></td>
     </tr>
     </tr>
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>归属快递公司：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
         <select id="company" name="company" style="opacity:1">
         <c:forEach items="${companyls }" var="v">
         	<option value='${v.id }'>${v.company_name }</option>
         </c:forEach>
         </select>
     </div></td>
     </tr>
   <tr>
     <td height="54" bgcolor="#FFFFFF"><div align="center"></div></td>
     <td height="54" bgcolor="#FFFFFF"><input type="button" id="siglesub"  value="　保　存　" />
       <input onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt" type="button" value="　返　回　" /></td>
   </tr>
 </table>
 </form>
      </div>
    </div>
    <form action="${pageContext.servletContext.contextPath }/background/Express/addCourier.html" method="post" enctype="multipart/form-data" id="addForm1">
    <div id="divStoreId" style="display:none ;">
   <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF"  class="tablelist_left">
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>选择文件：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
         <input type="file" name="file" id="file" class="dfinput" onchange="changeFile()"/>
         <label id="tip"></label>
       </div>
     </div></td>
     </tr>
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>选择归属厂商：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
         <select id="company" name="company" style="opacity:1">
         <c:forEach items="${companyls }" var="v">
         	<option value='${v.id }'>${v.company_name }</option>
         </c:forEach>
         </select>
       </div>
     </div></td>
     </tr>
     
     
     
    <tr>
     <td height="54" bgcolor="#FFFFFF"><div align="center"></div></td>
     <td height="54" bgcolor="#FFFFFF"><input type="button" id="sub1" value="确认维护" />
       <input onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt1" type="button" value="　返　回　" />
       	<input type="button" 
       ondblclick="javascript:window.location.href=encodeURI(encodeURI('${pageContext.servletContext.contextPath }/background/report/downDomeFile.html?fileName=快递员维护模板.xlsx'))"
       value="下载模板"/>
       </td>
       <%-- <td height="54" bgcolor="#FFFFFF"><input type="button" 
       ondblclick="javascript:window.location.href=encodeURI(encodeURI('${pageContext.servletContext.contextPath }/background/report/downDomeFile.html?fileName=快递员维护模板.xlsx'))"
       value="下载模板"/></td> --%>
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

//验证
function check(){
//快递员姓名
var real_name =  $("#real_name").val();
//快递员电话
var accountName = $("#accountName").val();
console.log(accountName);
if(real_name == null || real_name == ""){
	$("#checkAccount").empty();
	$("#checkName").html("<font color='red'>快递员姓名不能为空</font>");
	return false;
} else if(accountName == null || accountName == ""){
	$("#checkName").empty();
	$("#checkAccount").html("<font color='red'>快递员的电话不能为空</font>");
	return false;
    } else if(!(/^1[3|4|5|7|8][0-9]{9}$/.test(accountName))){
    	$("#checkName").empty();
    	$("#checkAccount").empty();
    	$("#checkAccount").html("<font color='red'>您输入的电话号码不符合规范！</font>");
    	return false;
    } else {
    	$("#checkName").empty();
    	$("#checkAccount").empty();
    	return true;
    }
}
//单个提交
$("#siglesub").click(function(){
	if(check()){
		$("#addForm").submit();
	}
});
//当文件文本框发生变化时
function changeFile(){	
	//获取文件的名字
	var fileName = $("#file").val();
	var yz = /^.*\.(?:xls|xlsx)$/;
	if(yz.test(fileName)){
		$("#tip").empty();
		return true;
	} else {
		$("#tip").html("<font color='red'>您选择的文件不符合规范!</font>")
		return false;
	}
}
$("#sub1").click(function(){
	if(changeFile()){
		$("#addForm1").submit();
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
