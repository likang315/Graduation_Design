<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/common/common-pc-cj.jsp" %>
	<link href="${pageContext.servletContext.contextPath }/css/style.css" rel="stylesheet" type="text/css" />
	<style>
		.vendor_btn{
			border:1px solid black;
			width:100px;
			height:35px;
			line-height:35px;
			text-align:center;
			border-radius:10px;
			cursor:pointer;
		}
		.vendor_info img{
			width:250px;
			cursor:pointer;
		}
		.vendor_info{
			float:left;
			border:1px solid lightblue;
			margin:5px;
		}
		#addVendor_box{
			display:none;
			width:60%;
			position:fixed;
			top:10%;
			left:20%;
			height:650px;
			border:1px solid black;
			border-radius:20px;
			z-index:999;
			background-color:#fff;
		}
		#addVendor_title{
		    text-align: center;
		    height: 50px;
		    line-height: 50px;
		    background-color: lightblue;
		    border-top-left-radius: 20px;
		    border-top-right-radius: 20px;
		    font-size: 20px;
		    font-weight: bold;
		}
		.vendor_txt{
			margin-top:15px;
			border: 1px solid;
		    height: 35px;
		    width: 200px;
		    font-size: 20px;
   			font-weight: 900;
		}
		.vendor_label{
			margin-top:15px;
			width: 40%;
		    text-align: center;
		    font-size: 16px;
		}
		table{
			width:100%;
			margin-top:25px;
		}
		#mask{
			display:none;
			width:100%;
			height:100%;
			position:fixed;
			top:0px;
			left:0px;
			background-color:#000;
			opacity: 0.5;
			z-index:990;
		}
		#del,#add{
			float:right;
			margin-right:10px;
			margin-top:15px;
		}
		[name=delVendor]{
		    width: 20px;
    		height: 20px;
		}
		.wxMenu_info_message{
		    float: right;
		    text-align: center;
		    width: 90%;
		    height: 25px;
		    line-height: 25px;
		    font-size: 18px;
		}
		.ico{
			float:left;
		}
	</style>
</head>

<body>
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">首页</a></li>
		    <li><a href="#">综合管理</a></li>
		    <li><a href="#">微信端菜单管理</a></li>
	    </ul>
    </div>
    <!-- 按钮组件 开始-->
   	<div>
   		<div class="vendor_btn" id="del" >删除</div>
   		<div class="vendor_btn" id="add" >添加</div>
   	</div>
    <!-- 按钮组件 结束-->
    
    <!-- 显示厂商开始 -->
    <div style="clear:both">
    	<c:forEach items="${LWxMenu}" var="wxMenu">
    		<div class="vendor_info" >
    			<div onclick="updateVendor('${wxMenu.id }','${wxMenu.name }','${wxMenu.img }','${wxMenu.address }','${wxMenu.ioc }','${wxMenu.phrase }')"><img src="${wxMenu.img}" title="${wxMenu.name}"/></div>
    			<div><input type="checkbox" name="delVendor" value="${wxMenu.id}"/><span class="wxMenu_info_message">${wxMenu.name}</span></div>
    		</div>
    	</c:forEach>
    </div>
    <!-- 显示厂商结束 -->
    
    <!-- 新增厂商开始 -->
    <div id="addVendor_box">
    	<div id="addVendor_title">新增菜单</div>
    	<table>
    		<tr >
    			<td class="vendor_label">菜单名称：</td>
    			<td><input class="vendor_txt" type="text" id="name"/></td>
    			<td  class="vendor_label">页面缩略图：</td>
    		</tr>
    		<tr >
    			<td class="vendor_label">菜单地址：</td>
    			<td><input class="vendor_txt" type="text" id="address"/></td>
    			<td rowspan="2" style='    text-align: center;'>
    					<form id="uploadForm_img"  method="post" action=""	enctype="multipart/form-data" style="height: 350px;"  >
    						<img src="" style="width: 200px;height:300px;" id="vendor_ioc"/>
    						<input style="left:25%;position: relative; width: 200px; height: 300px;cursor:pointer;opacity: 0;top:-310px;" class="vendor_txt" type="file" id="imgFile" name="imgFile"/>
    					</form>
    			</td>
    		</tr>
    		<tr >
    			<td class="vendor_label">短语：</td>
    			<td><input class="vendor_txt" type="text" id="phrase"/></td>
    		</tr>
    		<tr>
    			<td  class="vendor_label">选择展示图标：</td>
    			<td >
    				<div style="overflow: auto; height: 100px;">
						<div class="leftDaohang1 left ico" onclick="ioc_click(this,'leftDaohang1')"></div>
						<div class="leftDaohang2 left ico" onclick="ioc_click(this,'leftDaohang2')"></div>
						<div class="leftDaohang3 left ico" onclick="ioc_click(this,'leftDaohang3')"></div>
						<div class="leftDaohang4 left ico" onclick="ioc_click(this,'leftDaohang4')"></div>
						<div class="leftDaohang5 left ico" onclick="ioc_click(this,'leftDaohang5')"></div>
						<div class="leftDaohang6 left ico" onclick="ioc_click(this,'leftDaohang6')"></div>
						<div class="leftDaohang7 left ico" onclick="ioc_click(this,'leftDaohang7')"></div>
						<div class="leftDaohang8 left ico" onclick="ioc_click(this,'leftDaohang8')"></div>
						<div class="leftDaohang9 left ico" onclick="ioc_click(this,'leftDaohang9')"></div>
						<div class="leftDaohang10 left ico" onclick="ioc_click(this,'leftDaohang10')"></div>
						<div class="leftDaohang11 left ico" onclick="ioc_click(this,'leftDaohang11')"></div>
						<div class="leftDaohang12 left ico" onclick="ioc_click(this,'leftDaohang12')"></div>
						<div class="leftDaohang13 left ico" onclick="ioc_click(this,'leftDaohang13')"></div>
						<div class="leftDaohang14 left ico" onclick="ioc_click(this,'leftDaohang14')"></div>
						<div class="leftDaohang15 left ico" onclick="ioc_click(this,'leftDaohang15')"></div>
						<div class="leftDaohang16 left ico" onclick="ioc_click(this,'leftDaohang16')"></div>
						<div class="leftDaohang17 left ico" onclick="ioc_click(this,'leftDaohang17')"></div>
						<div class="leftDaohang18 left ico" onclick="ioc_click(this,'leftDaohang18')"></div>
						<div class="leftDaohang19 left ico" onclick="ioc_click(this,'leftDaohang19')"></div>
						<div class="leftDaohang20 left ico" onclick="ioc_click(this,'leftDaohang20')"></div>
						<div class="leftDaohang21 left ico" onclick="ioc_click(this,'leftDaohang21')"></div>
						<div class="leftDaohang22 left ico" onclick="ioc_click(this,'leftDaohang22')"></div>
						<div class="leftDaohang23 left ico" onclick="ioc_click(this,'leftDaohang23')"></div>
						<div class="leftDaohang24 left ico" onclick="ioc_click(this,'leftDaohang24')"></div>
						<div class="leftDaohang25 left ico" onclick="ioc_click(this,'leftDaohang25')"></div>
						<div class="leftDaohang26 left ico" onclick="ioc_click(this,'leftDaohang26')"></div>
						<div class="leftDaohang27 left ico" onclick="ioc_click(this,'leftDaohang27')"></div>
						<div class="leftDaohang28 left ico" onclick="ioc_click(this,'leftDaohang28')"></div>
						<div class="leftDaohang29 left ico" onclick="ioc_click(this,'leftDaohang29')"></div>
						<div class="leftDaohang30 left ico" onclick="ioc_click(this,'leftDaohang30')"></div>
						<div class="leftDaohang31 left ico" onclick="ioc_click(this,'leftDaohang31')"></div>
						<div class="leftDaohang32 left ico" onclick="ioc_click(this,'leftDaohang32')"></div>
						<div class="leftDaohang33 left ico" onclick="ioc_click(this,'leftDaohang33')"></div>
						<div class="leftDaohang34 left ico" onclick="ioc_click(this,'leftDaohang34')"></div>
					</div>
    			</td>
    		</tr>
    	</table>
    	
    	<div class="vendor_btn" style="position: absolute;bottom:20px;left:8%;display:none" id="addVendor">添加</div>
    	<div class="vendor_btn" style="position: absolute;bottom:20px;left:8%;display:none" id="updateVendor">修改</div>
   		<div class="vendor_btn" style="position: absolute;bottom:20px;left:80%" id="cancel">取消</div>
    </div>
    <!-- 新增厂商结束 -->
    
    <!-- 蒙版开始 -->
    <div id="mask"></div>
    <!-- 蒙版结束 -->
</body>

</html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script>
	var filePath = "";
	var menu_ioc = "item_01";
	$("#add").click(function(){
		$("#name").val("");
		$("#address").val("");
		$("#phrase").val("");
		$(".ioc").css("backgroundColor","#fff");
		showMask();
		$("#addVendor").show();
		$("#vendor_ioc").attr("src",API.httpserver+"/images/wxMenu/xuanze.png");
		$("#addVendor_title").html("新增菜单");
	});
	var id;
	$("#addVendor").click(function(){
		if($("#name").val() == ""){
			alert("请填写菜单名称");
			return;
		}
		if($("#address").val() == ""){
			alert("请填写菜单访问地址");
			return;
		}
		var data = {
				"name":$("#name").val(),
				"address":$("#address").val(),
				"img":filePath,
				"ioc":menu_ioc,
				"phrase":$("#phrase").val()
		};
		API.ajax("/app/wxmenu/addWxMenu",data).success(function(data){
			alert(data.info);
			hideMask();
			location.reload();
		}).error(function(data){
			alert(data.info);
		})
	})
	
	$("#updateVendor").click(function(){
		if($("#name").val() == ""){
			alert("请填写菜单名称名称");
			return;
		}
		if($("#address").val() == ""){
			alert("请填写菜单访问地址");
			return;
		}
		var data = {
				"name":$("#name").val(),
				"address":$("#address").val(),
				"id":id,
				"img":filePath,
				"ioc":menu_ioc,
				"phrase":$("#phrase").val()
		};
		API.ajax("/app/wxmenu/updateWxMenu",data).success(function(data){
			alert(data.info);
			hideMask();
			location.reload();
		}).error(function(data){
			alert(data.info);
		})
	})
	
	$("#cancel").click(function(){
		hideMask();
	})
	
	var vendorId = new Array();
	$("#del").click(function(){
		vendorId = getByNames("delVendor");
		var flag = false;
		var ids = "";
		for(var i=0;i<vendorId.length;i++){
			if(vendorId[i].checked){
				ids += vendorId[i].value + ",";
				flag = true;
			}
		}
		if(flag){
			API.ajax("/app/wxmenu/delWxMenu",{"ids":ids}).success(function(data){
				alert(data.info);
				location.reload()
			}).error(function(data){
				alert(data.info);
			})
		}else{
			alert("请选择要删除的信息")
		}
	})
	
	function hideMask(){
		$("#mask").hide();
		$("#addVendor_box").hide();
	}
	function showMask(){
		$("#mask").show();
		$("#addVendor_box").show();
	}
	
	function updateVendor(id,name,img,address,ioc,phrase){
		filePath = img;
		showMask();
		this.id = id;
		$("#name").val(name);
		$("#addVendor").hide();
		$("#updateVendor").show();
		$("#vendor_ioc").attr("src",img);
		$("#address").val(address);
		$("#phrase").val(phrase)
		$("#addVendor_title").html("菜单信息修改");
		$(".ioc").css("backgroundColor","#fff");
		$("." + ioc).css("backgroundColor","palegoldenrod");
	}
	function getByNames(e){
		return window.document.getElementsByName(e);
	}
	
	$("#imgFile").change(function(){
		var v = $("#imgFile").val();
		var ext = v.substring(v.lastIndexOf("."));
		if(ext != ".jpg" && ext != ".png" && ext != ".gif") {
			alert("请选择图片，支持的图片格式有：JPEG, PNG, GIF !");
			obj.val("");
			return ;
		}
		
		doUpload();
	})
	function doUpload() {  
	     $("#uploadForm_img").ajaxSubmit({
	    	 	type: "post",
	            url:  API.httpserver + '/app/wxmenu/uploadImg.html',
	            success: function (data) {
	            	console.log(JSON.parse(data).filePath); 
	            	$("#vendor_ioc").attr("src",JSON.parse(data).filePath);
	            	filePath = JSON.parse(data).filePath;
	            },
	            error: function (msg) {
	                alert("文件上传失败");    
	            }
	     })
	
	} 
	function ioc_click(e,v){
		$(".ico").css("backgroundColor","#fff");
		$(e).css("backgroundColor","palegoldenrod");
		menu_ioc = v;
	}
</script>
