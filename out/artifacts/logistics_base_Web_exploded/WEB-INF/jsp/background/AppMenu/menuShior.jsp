<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
		<link href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.min.js"></script>
	</head>

	<style>
	*{padding:0px; margin:0px;}
	.max{ border:px solid #000; height:auto; width:98%; margin:auto;}
	.colmd2{ border:px solid #000; height:auto; margin:15px 0px;padding:15px; }
	.colmd10{
		border:1px solid #000; 
		height:auto; 
		margin:15px 0px;
		height: 80%;
	    overflow: scroll;
		}
	#innsert_menu{
		position: fixed;
	    top: 5%;
	    left: 10%;
	    background-color: #fff;
	    border-radius: 15px;
	   	z-index:999;
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
	.menu_title{
		font-size:20px;
		font-weight: 900;
	}
	select{margin-top:20px;}
	.tupindiv{ margin-top:10px; padding-top:15px;}
	.duihao{ position:absolute; width:50%; height:40%; top:30%; left:26%;}
	@media only screen and (max-width: 992px) {
	    .pcanniu {
			display:none;
	    }
		.mengban{background:#000; width:95%; height:95%; top:15px; left:15px; opacity:0.7; position:absolute;}
	}
	@media only screen and (min-width: 992px) {
	    .shoujianniu {
			display:none;
	    }
		.mengban{background:#000; width:92%; height:95%; top:15px; left:15px; opacity:0.7; position:absolute;}
	}
	@media only screen and (max-width: 350px) {
		
		.mengban{background:#000; width:77%; height:82%; top:15px; left:15px; opacity:0.7; position:absolute;}
	}
	</style>

	<body>
	<div class="row max">
		<div class="col-md-12">
	    	<div class="col-md-2 colmd2">
	            <div class="col-md-12">
	               <input style="width:100%;height: 35px;" name="groupId" id="groupId" class="easyui-combotree dfinput"
				         data-options="url:'${pageContext.servletContext.contextPath }/background/group/tree.html',method:'get'"
				         value="请选择地市"/>
	            </div>
	            <%-- <div class="col-md-12">
	                <select class="form-control" id="vendorId">
	                	<option value="0">无厂商</option>
	                    <c:forEach items="${LVendor}" var="vendor">
		                    <option value="${vendor.id}">${vendor.name}</option>
	                    </c:forEach>
	                </select>
	            </div> --%>
	            <input type="hidden" value = "0" id="vendorId"/>
	            <div class="col-md-12">
	<!--                 <select class="form-control">
	                    <option>品牌</option>
	                </select> -->
	            </div>
	            <div style="text-align:center;">
	                <input type="button" class="btn btn-info pcanniu" style=" margin-top:200px; width:80%;" onclick="addMenu()" value="添加/修改左侧菜单">
	            </div>
	        </div>
	        <div class="col-md-10 colmd10" id="show_menu_img" style="height:90%">
	        
	            <div style="text-align:center;">
	                <input type="button" class="btn btn-info shoujianniu" style=" margin-top:50px; width:50%;" onclick="addMenu()" value="添加/修改左侧菜单">
	            </div>
	        </div>
	    </div>
	</div>	
	
	<!-- 蒙版开始 -->
	<div id="mask"></div>
	<!-- 蒙版结束 -->
	
	<!-- 显示所有菜单缩略图开始 -->
	<div class="col-md-10 colmd10" id="innsert_menu" style="display:none">
		<c:forEach items="${LWxMenu}" var="wxMenu" >
			<div class="col-xs-6 col-sm-6 col-md-2 tupindiv" id="index${wxMenu.id}" name="index_left_bottom_menu">
		         <a href="#" class="thumbnail">
					<div style="text-align:center" >${wxMenu.name}</div>
		             <img src="${wxMenu.img}">
		         </a>
		         <div id="${wxMenu.id}" style="display:none" name="mengban_show_hide">
			         <div class="mengban"></div>
			         <span class="duihao">
			         	<img src="${pageContext.servletContext.contextPath }/images/wxMenu/duihao.png" width="100%">
			         </span>
		         </div>
		         
		     </div>
		</c:forEach>
		
		<div style="text-align:center;">
	        <input type="button" class="btn btn-info" value="确定修改侧边菜单" id="add_wx_menu_ok" style="position: absolute;bottom: 20px;right: 100px;">
	        <input type="button" class="btn btn-info" value="确定修改主页" id="add_wx_indexmenu_ok" style="display:none;position: absolute;bottom: 20px;right: 100px;">
	        <input type="button" class="btn btn-info" value="确定修改底部导航" id="add_wx_bottommenu_ok" style="display:none;position: absolute;bottom: 20px;right: 100px;">
	        
	        <input type="button" class="btn btn-info" onclick="hideMask()" value="取消" style="position: absolute;bottom: 20px;right: 20px;">
	    </div>
	</div>
	
	<!-- 显示所有菜单缩略图结束 -->
	
	
	</body>
</html>
<script type="text/javascript">
var flag = true;
var menuId = 0;
var groupId = 0;
var click_new_bottom = 0;
	$(document).ready(function(e) {
        $(".tupindiv").click(function(e) {
			if($(this).children("div").css("display")=="none"){
            	$(this).children("div").css("display","block");
			}else{
				$(this).children("div").css("display","none");
			}
			
			if($(this).children("span").css("display")=="none"){
				$(this).children("span").css("display","block");
			}else{
				$(this).children("span").css("display","none");
			}
        });
    });

	$("#groupId").combotree({
    	onChange:function(data){
    		groupId = data;
    		var vendorId = $("#vendorId").val();
    		var brandId = 0;//$("#brandId").val();
        	getMenuAll(groupId,vendorId,brandId);
    	}    
    });
	
	$("#vendorId").change(function(){
		if(groupId == 0){
			alert("请选择地市");
			return;
		}
		var brandId = 0;//$("#brandId").val();
		getMenuAll(groupId,$("#vendorId").val(),brandId);
	})
	
	$("#add_wx_menu_ok").click(function(){
		var menu_ids_name = new Array();
		menu_ids_name = getByNames("mengban_show_hide");
		var ids = "";
		for (var i = 0; i < menu_ids_name.length; i++) {
			if(menu_ids_name[i].style.display == "block"){
				ids += menu_ids_name[i].getAttribute("id")+",";
			}	
		}
		var vendorId = $("#vendorId").val();
		var brandId = 0 ;//$("#vendorId").val();
		var data={
				"groupId":groupId,
				"vendorId":vendorId,
				"brandId":brandId,
				"left_menu":ids,
				"id":menuId
		}
		var path =	flag?"/app/wxmenu/updateWxMenuByAll":"/app/wxmenu/addWxMenuByAll";
		API.ajax(path,data).success(function(data){
			alert(data.info);
			getMenuAll(groupId,vendorId,brandId);
			hideMask();
		}).error(function(data){
			alert(data.info);
		})
	})
	
	
	$("#add_wx_indexmenu_ok").click(function(){
		update_index_bottom(0);
	})
	
	
	$("#add_wx_bottommenu_ok").click(function(){
		update_index_bottom(1);
	})
	
	
	function update_index_bottom(type_index_bottom){
		var menu_ids_name = new Array();
		menu_ids_name = getByNames("mengban_show_hide");
		var ids = "";
		var num = 0;
		for (var i = 0; i < menu_ids_name.length; i++) {
			if(menu_ids_name[i].style.display == "block"){
				num++;
				ids = menu_ids_name[i].getAttribute("id");
			}	
		}
		
		switch (num) {
		case 0:
			hideMask();
			break;
		case 1:
			var vendorId = $("#vendorId").val();
			var brandId = 0 ;//$("#vendorId").val();
			
			var path = "";
			var data;
			switch (type_index_bottom) {
			case 0:
				data={
					"groupId":groupId,
					"vendorId":vendorId,
					"brandId":brandId,
					"index":ids,
					"id":menuId
				}
				path = "/app/wxmenu/updateIndex";
				break;
			case 1:
				//取出现有的所有的底部导航id
				var bottom_menu = new Array();
				var strA = new Array();
				bottom_menu = getByNames("bottom_menu_new");
				for (var j = 0; j < bottom_menu.length; j++) {
					strA[j] = bottom_menu[j].getAttribute("id");
				}
				var bottom_flag = true;
				for(var n=0;n<strA.length;n++){
					if(strA[n] == click_new_bottom){
						strA[n] = ids;
						bottom_flag = false;						
					}
				}
				var t = ids;
				ids ="";
				for(var m=0;m<strA.length;m++){
					ids += strA[m]+",";
				}
				
				if(bottom_flag){
					ids += t + ",";
				}
				
				data={
					"groupId":groupId,
					"vendorId":vendorId,
					"brandId":brandId,
					"bottom_menu":ids,
					"id":menuId
				}
				path = "/app/wxmenu/updateBottonMenu";
				break;

			default:
				break;
			}
			API.ajax(path,data).success(function(data){
				alert(data.info);
				getMenuAll(groupId,vendorId,brandId);
				hideMask();
			}).error(function(data){
				alert(data.info);
			})
			break;
		default:
			alert("最多只能选择一个");
			break;
		}
		
	}
	
	//展示当前用户所有菜单
	function getMenuAll(groupId,vendorId,brandId){
		var data={
			"groupId":groupId,
			"vendorId":vendorId,
			"brandId":brandId
		};
		API.ajax("/app/wxmenu/getWxMenuByAll",data).success(function(data){
			flag = true;
			if(data.groupId != groupId) flag = false;
			menuId = data.menuId;
			$("[name='mengban_show_hide']").css("display","none");
			$("[name='index_left_bottom_menu']").css("display","block");
			var str = "";
			//主页
			str += '<div class="menu_title">主页：</div><div class="row">';
			if(data.index_menu.length==0){
				str += '<div onclick="addIndex(0)" class="col-xs-6 col-sm-6 col-md-2 tupindiv" ><img style="width:100%" src="'+API.httpserver+'/images/wxMenu/tianjia.jpg"></div>'
			}else{
				for(var i=0;i<data.index_menu.length;i++){
					str +='<div onclick="addIndex(\''+data.index_menu[i].id+'\')"  class="col-xs-6 col-sm-6 col-md-2 tupindiv">'+
					          '<img style="width:100%" src="'+data.index_menu[i].img+'">'+
						  '</div>';
				}
			}
			//底部导航
			str += '</div><div class="menu_title">底部导航：</div><div class="row">';
			var bottom_menu = 0;
			for(var i=0;i<data.bottom_menu.length;i++){
				str += '<div id="'+data.bottom_menu[i].id+'" name="bottom_menu_new" onclick="addBottomMenu(\''+data.bottom_menu[i].id+'\')" class="col-xs-6 col-sm-6 col-md-2 tupindiv">'+
				          '<img style="width:100%" src="'+data.bottom_menu[i].img+'">'+
					  '</div>';
				bottom_menu++;
			}
			if(bottom_menu<3){
				str += '<div onclick="addBottomMenu(0)" class="col-xs-6 col-sm-6 col-md-2 tupindiv" style="text-align:center"><img style="width:100%" src="'+API.httpserver+'/images/wxMenu/tianjia.jpg"></div>';
			}
			//左侧菜单
			str += '</div><div class="menu_title">左侧菜单：</div><div class="row">';
			for(var i=0;i<data.info.length;i++){
				str += '<div class="col-xs-6 col-sm-6 col-md-2 tupindiv">'+
				          '<img style="width:100%" src="'+data.info[i].img+'">'+
					  '</div>';
				$("#"+data.info[i].id).css("display","block");
			}
			
			$("#show_menu_img").html(str);
		}).error(function(data){
			$("#show_menu_img").html("");
			$("[name='mengban_show_hide']").css("display","none");
			flag = false;
			menuId = data.menuId;
			$("[name='mengban_show_hide']").css("display","none");
			$("[name='index_left_bottom_menu']").css("display","block");
			var str = "";
			//主页
			str += '<div class="menu_title">主页：</div><div class="row">';
			str += '<div onclick="addIndex(0)" class="col-xs-6 col-sm-6 col-md-2 tupindiv" ><img style="width:100%" src="'+API.httpserver+'/images/wxMenu/tianjia.jpg"></div>'
			//底部导航
			str += '</div><div class="menu_title">底部导航：</div><div class="row">';
			str += '<div onclick="addBottomMenu(0)" class="col-xs-6 col-sm-6 col-md-2 tupindiv" ><img style="width:100%" src="'+API.httpserver+'/images/wxMenu/tianjia.jpg"></div>';
			//左侧菜单
			str += '</div><div class="menu_title">左侧菜单：</div><div class="row">';
			str += '<div onclick="addMenu()" class="col-xs-6 col-sm-6 col-md-2 tupindiv" ><img style="width:100%" src="'+API.httpserver+'/images/wxMenu/tianjia.jpg"></div>';
			$("#show_menu_img").html(str);
		})
	}
	
	function addMenu(){
		if(groupId == 0){
			alert("请选择地市");
			return;
		}
		$("#mask").show();
		$("#innsert_menu").show();
		showBtnMenu(0);
	}
	
	function hideMask(){
		var vendorId = $("#vendorId").val();
		var brandId = 0 ;//$("#vendorId").val();
		getMenuAll(groupId,vendorId,brandId);
		$("#mask").hide();
		$("#innsert_menu").hide();
	}
	
	function getByNames(e){
		return window.document.getElementsByName(e);
	}
	
	function addIndex(index_id){
		//alert("设置主页：当前主页id====>"+index_id);
		showBtnMenu(1);
		hide_existing(index_id)
	}
	
	function addBottomMenu(bottom_id){
		//alert("设置底部导航：当前底部导航id====>"+bottom_id);
		click_new_bottom = bottom_id;
		showBtnMenu(2);
		hide_existing(bottom_id)
	}
	
	function hide_existing(existing_id){
		$("#mask").show();
		$("#innsert_menu").show();
		$("[name='mengban_show_hide']").css("display","none");
		$("#index"+existing_id).css("display","none");
	}
	
	function showBtnMenu(type_val){
		$("#add_wx_menu_ok").css("display","none");
		$("#add_wx_indexmenu_ok").css("display","none");
		$("#add_wx_bottommenu_ok").css("display","none");
		switch (type_val) {
		case 0:
			$("#add_wx_menu_ok").css("display","block");
			break;
		case 1:
			$("#add_wx_indexmenu_ok").css("display","block");
			break;
		case 2:
			$("#add_wx_bottommenu_ok").css("display","block");
			break;
		}
	}
</script>


































