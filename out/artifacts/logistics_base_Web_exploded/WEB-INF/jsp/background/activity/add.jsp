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

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.min.js"></script>
 <style type="text/css">
.error{color:red ;background:none;padding-top:0;width:345px; margin-top:0px;}
 </style>
<script type="text/javascript">
$(document).ready(function(){
	$("#addForm").validate({
		rules:{
			title:"required",
			endtime:"required",
		    activity_type:"required",
		    groupId:"required",
		    file:"required"
		},
		messages:{
			title:"请输入标题",
			endtime:"请选择政策结束时间",
		    activity_type:"请选择政策类型",
		    groupId:"请选择组织机构",
		    file:"请选择政策文件，格式为：.doc,.docx"
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
    <li><a href="#">政策管理</a></li>
     <li><a href="#">新增政策</a></li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab_nav">
  	<ul> 
    <li><a href="#tab1" class="selected">新增政策</a></li>
   
  	</ul>
  		<div style="float:right;padding-right:20px;">
  			<input type="button" value="视频发布" id="videoPublishing" style="background-color: #337ab7;border-color: 
  			#2e6da4; width:120px; height: 35px;color: #fff; border-radius:5px; font-size: 14px;font-weight: bold;"/>
  			<input type="button" value="文本发布" id="textPublishing" style="background-color: #337ab7;border-color: 
  			#2e6da4; width:120px; height: 35px;color: #fff; border-radius:5px; font-size: 14px;font-weight: bold;display:none;"/>
  		</div>
    </div>
    <div class="line">
    <div id="tab2" class="tabson">
    <div class="toolbar1"></div>
 <div class="toolbar1"></div>
<form id="addForm" action="${pageContext.request.contextPath }/background/activity/add.html" method="post" enctype="multipart/form-data">   
 <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF"  class="tablelist_left">
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>政策标题：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input name="title" id="title" class="dfinput"/>
     </div></td>
     </tr>
     <!-- 222 -->
     
     
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>开始时间：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input id="starttime" name="starttime" class="easyui-datetimebox" data-options="formatter:ww3,parser:w3,required:true" value="getValue()" style="width:150px" />
     </div></td>
     </tr>
     
     
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>结束时间：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input id="endtime" name="endtime" class="easyui-datetimebox" data-options="formatter:ww3,parser:w3,required:true" value="2050/01/01" style="width:150px" />
     </div></td>
     </tr>
     
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>政策描述：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <input name="desc" id="desc" class="dfinput"/>
     </div></td>
     </tr>
     
     <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>活动/政策：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="mold">
       	<select style="width:10%" id="flag" name="mold" class="easyui-combobox">
				    <option  value="A"  checked>活动</option>
				    <option  value="B" >政策</option>
                    <option  value="C" >知识库</option>
				</select >
     </div></td>
     </tr>
     
      <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>政策分类：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       	<select style="width:10%" id="activity_type" name="activity_type" class="easyui-combobox">
				    <option  value="A" >套餐</option>
				    <option  value="B" >充值</option>
				    <option  value="C" >流量</option>
				    <option  value="D" >购机</option>
				    <option  value="E" >宽带</option>
				</select >
     </div></td>
     </tr>
     
   <tr>
     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>传递范围：</div></td>
     <td height="38" bgcolor="#FFFFFF"><div align="left">
       <div align="left">
         <input name="groupId" class="easyui-combotree dfinput" 
         data-options="url:'${pageContext.servletContext.contextPath }/background/group/tree.html',method:'get'"  
         />
       </div>
     </div></td>
     </tr>
     
     <tr id="policyDocument">
	     <td width="10%" height="38" align="center" bgcolor="#F0F5F7"><div>政策文件：</div></td>
	     <td height="38" bgcolor="#FFFFFF"><div align="left">
	       <div align="left">
	       	 <input id="file" type="file" name="file" />
	        </div>
	     </div></td>
     </tr>
     
      
   
 </table>

      </div>
      
      <div id="selfEditing" style="display:none">
     <label style=" font-size: 16px; font-weight: bold;">自编辑政策：</label>
     
     		<!-- 加载编辑器的容器 -->
				<script id="container" name="content" type="text/plain"></script>
				<!-- 配置文件 -->
				<script type="text/javascript" src="${pageContext.request.contextPath}/js/ueditor/ueditor.config.js"></script>
				<!-- 编辑器源码文件 -->
				<script type="text/javascript" src="${pageContext.request.contextPath}/js/ueditor/ueditor.all.js"></script>
				<!-- 实例化编辑器 -->
				 <script type="text/javascript">
				 var ue = UE.getEditor('container',{
					    autoHeight: true,
					    toolbars:[//设置菜单
						['anchor','|','undo','redo','bold','indent','italic','underline','strikethrough','subscript','fontborder','superscript','formatmatch','source','blockquote','pasteplain','preview','horizontal','removeformat','|','time','date','unlink','insertrow','insertcol','mergeright','mergedown','inserttable','background','|','autotypeset','customstyle','edittip','wordimage','imagecenter','fullscreen','insertorderedlist','insertunorderedlist','|','forecolor','justifyleft','justifyright','justifycenter','justifyjustify','searchreplace','emotion','link','edittable','|','edittd','insertimage','paragraph','fontsize','fontfamily','mergecells','insertvideo']
						],

					});
				 ue.ready(ue.loadServerConfig);
				//对编辑器的操作最好在编辑器ready之后再做
				 ue.ready(function() {
				     //设置编辑器的内容
				     ue.setContent(null);
				     //获取html内容，返回: <p>hello</p>
				     var html = ue.getContent();
				     //获取纯文本内容，返回: hello
				     var txt = ue.getContentTxt();
				 });
				 </script>
     
     </div>
      <div>
      	<input type="text"id="context1" name="context1"/>
     	<input type="button" value="　发　布　" id="sub" class="scbtn" />
        <input onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt" type="button" value="　返　回　"  class="scbtn"/></td>
   	</div>
   </form>
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
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	
    
    
    </div>



</body>
<script>
$("#videoPublishing").click(function(){
	$("#videoPublishing").hide();
	$("#textPublishing").show();
	$("#selfEditing").show();
	$("#policyDocument").hide();
});

$("#textPublishing").click(function(){
	$("#textPublishing").hide();
	$("#videoPublishing").show();
	$("#policyDocument").show();
	$("#selfEditing").hide();
});
</script>


<script type="text/javascript">
$("#sub").click(function(){
	var html = ue.getContent();
	$("#context1").val(html);
	$("#addForm").submit();
})
	
</script>


</html>

