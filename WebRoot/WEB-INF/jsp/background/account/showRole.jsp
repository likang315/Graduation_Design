<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%@include file="/common/common-css.jsp" %>
<%@include file="/common/common-js.jsp" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js"></script>
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
function allocation(){
       $.ajax({
      		async:false, //请勿改成异步，下面有些程序依赖此请数据
      		type : "POST",
      		data:{userId:"${userId}",roleId:$('input[name="roleId"]:checked').val()},
      		url: "${pageContext.servletContext.contextPath }/background/role/addAccRole.html",
      		dataType:'json',
      		success: function(json){
      			if(json == "1000"){
      			//window.dialogArguments.location.reload();
		          alert("分配成功！！");
		          window.opener.location.reload();
		          window.close();
              	}else if(json == "1001"){
              		alert("分配失败！！");
                };
       		}
      	});
      }
</script>
</head>
<body>
    <div class="formbody">
    <div id="usual1" class="usual" style="width:80%"> 
    <div class="itab_nav" >
  	<ul> 
    <li><a href="#tab1" class="selected">角色列表</a></li>
  	</ul>
    </div> 
    <div class="line">
    <form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/role/query.html" method="post">
  	<div id="tab2" class="tabson">
        <table class="tablelist">
    	<thead>
    	<tr>
    	<th width="2%">
		选择
		</th>
        <th width="5%">角色名称</th>
        <th width="5%">角色KEY</th>
        <th width="6%">是否禁用</th>
        <th width="5%">描述</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="role" items="${pageView.records}">
	        <tr>
	        <td><input type="radio" name="roleId" value="${role.id}"/></td>
	        <td>${role.name}</td>
	        <td>${role.roleKey}</td>
	        <td>
	        	 <c:if test="${role.enable eq '2'}">
	            <font color="red">禁用</font>
	            </c:if>
	            <c:if test="${role.enable eq '1'}">
	            <font color="blue">正常</font>
	            </c:if>
	        </td>
	        <td>${role.description}</td>
	        </tr>
        </c:forEach>
        </tbody>
    </table>
    
    </div> 
    <div align="center">
    	<input type="button" value="　保　存　" class="scbtn" onclick="allocation();" />
	    <input onclick="javascript:window.location.href='javascript:this.close()'" id="backBt" type="button" value="　关　闭　"  class="scbtn"/>
    </div>
    </form>
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
