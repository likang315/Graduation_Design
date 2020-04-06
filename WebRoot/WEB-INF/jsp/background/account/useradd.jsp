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

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.min.js"></script>
 <style type="text/css">
		.error{
			color:red ;
			background:none;
			padding-top:0;
			width:345px; 
			margin-top:0px;
		}
 </style>
<script type="text/javascript">
$(document).ready(function(){
	$.post("${pageContext.request.contextPath}/background/county/findAreaName.html",function(data1){ 
		var data = JSON.parse(data1)		
		if(data.state == "ok"){
   		 	var areas  = data.area;				   		 	
   		 	var str1 = "";
			for(var i = 0;i < areas.length;i++){
				str1  = "<option value="+areas[i].areaName+ " >"+areas[i].areaName+"</option>";				    				
			    $("#area").append(str1); 						  
			}
		}   		
	});
	$("#area").change(function () {  				
    	 var areaName=$("#area").val(); 
    	 if(areaName){
	    	$("#town").html("");
	    	$("#town").append("<option>请选择</option>");
	    	$.post("${pageContext.request.contextPath}/background/county/findTownName.html",{"areaName":areaName},function(data1){
	    		var data = JSON.parse(data1)
	    		if(data.state == "ok"){
	       		 	var towns  = data.town;
	       		 	var str1 = "";
	    			for(var i = 0;i < towns.length;i++){
	    				str1  = "<option value="+towns[i].townName+" >"+towns[i].townName+"</option>";
	    				$("#town").append(str1);
	    			}
	    		}   		
	    	})
    	}else{
    		$("#town").html("");
	    	$("#town").append("<option>请选择</option>");
    	} 
   });
	
	
	$("#addForm").validate({
		rules:{
			accountName:{
				required:true,
				remote:{
					url:"${pageContext.request.contextPath}/background/account/isExist.html",
					type:"post",
					dataType:"json",
					cache:false,
					data:{
						name:function(){
							return $('#accountName').val();
						}
					},
					dataFilter:function(data){
						return data;
					}
				}
			},
			password:"required",
			real_name:"required"
			
		},
		messages:{
			accountName:{
				required:"请输入用户名",
				remote:"用户名已使用"
			},
			password:"请输入密码",
			real_name:"请输入真实姓名"
			
		}
		
	});
});
</script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">账户管理</a></li>
     <li><a href="#">新增用户</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab_nav">
  	<ul> 
    <li><a href="#tab1" class="selected">新增用户</a></li>
  	</ul>
    </div>
    <div class="line">
    <div id="tab2" class="tabson">
    <div class="toolbar1"></div>
 <div class="toolbar1"></div>
 <form id="addForm" action="${pageContext.servletContext.contextPath }/background/account/add.html" method="post">
 <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF"  class="tablelist_left">
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>账号名：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input name="accountName" id="accountName" class="dfinput"/>
     </div></td>
     </tr>
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>账号姓名：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input name="real_name" id="real_name" class="dfinput"/>
     </div></td>
     </tr>
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>身份证号：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input name="id_car" id="id_car" class="dfinput"/>
     </div></td>
     </tr>
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>组织机构：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
         <input name="groupId" class="easyui-combotree dfinput"
         data-options="url:'${pageContext.servletContext.contextPath }/background/group/tree.html',method:'get'"/>
       </div>
     </div></td>
     </tr>
     <!-- <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>区县：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
       	 <div class="mindiv">	
			  <select id="area" name="area"  style="opacity:1;width:348px;border:1px solid #95b8e7;height:22px;border-radius:5px;padding-left:10px;margin-top:8px">
			       <option value="" >请选择</option>							
			  </select> 
		 </div>			
       	 <div style="color:#66CCFF">县级，镇级账号则选，市级账号则不写</div>     	 
        </div>       
     </div></td>
     </tr>
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>乡镇：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
       	 <div class="mindiv">  					  
			 <select id="town" name="town" style="opacity:1;width:348px;border:1px solid #95b8e7;height:22px;border-radius:5px;padding-left:10px;margin-top:8px">				 
			    <option value="" >请选择</option>
			 </select> 							   
		 </div>  
       	 <div  style="color:#66CCFF">镇级账号则选，市级、县级账号则不选</div>     	  
        </div>        
     </div></td>
     </tr> -->
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>密码：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
         <input name="password" id="password" type="password" class="dfinput"/>
       </div>
     </div></td>
     </tr>
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>是否禁用：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input name="state" type="radio" value="2" />禁用 
       <input name="state" type="radio" value="1" checked="checked"/>启用 
     </div></td>
     </tr>
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>描述：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
       	 <input name="description" class="dfinput"/>
        </div>
     </div></td>
     </tr>
   <tr>
     <td height="54" bgcolor="#FFFFFF"><div align="center"></div></td>
     <td height="54" bgcolor="#FFFFFF"><input type="submit" value="　保　存　" class="scbtn" />
       <input onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt" type="button" value="　返　回　"  class="scbtn"/></td>
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
</html>
