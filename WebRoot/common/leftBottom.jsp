<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--个人中心-->
<div class="mengban"></div>
<div class="sidebar-left">
	<div class="yctop">
        <!--TODO(likang): 后期图片更换... SuNing-->
        <div class="yclogo"><img src="${pageContext.request.contextPath}/images/member_img.png" width="100%"/></div>
        <div class="xingming">
        	<div >账户：<label id="userPhone"></label></div>
            <div >类型：<label id="userType"></label></div>
            <div >所属：<label id="userCity"></label></div>
        </div>
        <div class="tuichudtb"><img src="${pageContext.request.contextPath}/images/accessdenied.png" width="100%"/></div>		
    </div>
    <div id="my_business_list" class="my_business_list">
    
	</div>
    <div class="ycdbdlogo"><img src="${pageContext.request.contextPath}/images/m_logo_bottom.png" width="70%"/></div>
    <div style="height: 80px;"></div>
</div>


<!--底部导航-->
<div style="height:55px;"></div>
<nav class="navbar-fixed-bottom">
    <div class="container">
        <div class="row wnfooter">
            <div class="col-xs-3 footer-query">
            	<a id="foot_index"></a><!-- 办理 -->
            </div>
            <div class="col-xs-3 footer-business">
            	<a id="foot_policy" href="${pageContext.request.contextPath}/app/policy/policy.html">政策</a>
				<span class="tips_message" id="footer_hint"></span>
            </div>
            <div class="col-xs-3 footer-surface">
            	<a id="foot_query"></a><!-- 查询 -->
            </div>
            <div class="col-xs-3 footer-detailed" >
                <a id="foot_setup">我的</a>
            </div>
        </div>
    </div>
</nav>
<script type="text/javascript">
$(document).ready(function(){
  	$(".footer-detailed").click(function(){
		$(".footer-detailed").css("background","url("+API.httpserver + "/images/footer_icon04_focus.png) no-repeat top center/25px");
    	$(".sidebar-left").animate({left:'0px'});
		$(".mengban").show();
	});
	
	$(".tuichudtb").click(function(){
		$(".footer-detailed").css("background","url("+API.httpserver + "/images/footer_icon04.png) no-repeat top center/25px");
    	$(".sidebar-left").animate({left:'-250px'});  
		$(".mengban").css("display","none")	
 	});
	
	$(".mengban").click(function(){
		$(".footer-detailed").css("background","url("+API.httpserver + "/images/footer_icon04.png) no-repeat top center/25px");
    	$(".sidebar-left").animate({left:'-250px'});
		$(".mengban").css("display","none")  
 	});
	
	
	//加载个人信息
	var user = API.getUserInfo();
	$('#userPhone').html(user.accountName);
	//$('#userCity').html(user.city);
	if(user.accountType=='1'){
        $('#userCity').html("门店中心");
        $('#userType').html('门店');
	}else if(user.accountType=='2'){
        $('#userCity').html("快递员中心");
        $('#userType').html('快递员');
	}else if(user.accountType=='3'){
        $('#userCity').html("集团中心");
        $('#userType').html('管理员');
	}
	

	
	//加载底部菜单
 	setBottomeMenu = function(){
		var data={
				"groupId":user.groupId,
				"vendorId":0,
				"brandId":0
		};
		API.ajax("/app/wxmenu/getWxMenuByAll",data).success(function(data){
			$('#foot_index').html('<a href="'+API.httpserver + data.bottom_menu[0].address+'">'+data.bottom_menu[0].phrase+'</a>'); //首页 办理
			$('#foot_policy').html('<a href="'+API.httpserver + data.bottom_menu[1].address+'">'+data.bottom_menu[1].phrase+'</a>'); //首页 办理
			$('#foot_query').html('<a href="'+API.httpserver + data.bottom_menu[2].address+'">'+data.bottom_menu[2].phrase+'</a>'); //首页 办理
		}).error(function(data){
			$('#foot_index').html('<a href="'+API.httpserver + '/app/business.html">办理</a>'); //首页 办理
			$('#foot_policy').html('<a href="'+API.httpserver + '/app/policy/policy.html">政策</a>'); //首页 办理
			$('#foot_query').html('<a href="'+API.httpserver + '/app/search.html">查询</a>'); //首页 办理
		}).exception(function(data){
			$('#foot_index').html('<a href="'+API.httpserver + '/app/business.html">办理</a>'); //首页 办理
			$('#foot_policy').html('<a href="'+API.httpserver + '/app/policy/policy.html">政策</a>'); //首页 办理
			$('#foot_query').html('<a href="'+API.httpserver + '/app/search.html">查询</a>'); //首页 办理
		})
	}

	setBottomeMenu();
	
	//加载左侧菜单(点击我的开始加载)
	$("#foot_setup").click(function(){
		getLeftMenu();
	})
	
	//验证用户添加底部菜单
	if(user.groupId === 434){//快递员
		$("#foot_index").html("订单");
		$("#foot_index").attr('href', '${pageContext.request.contextPath}/app/coordinate/tocourierList.html');
		$("#foot_query").html("统计");
		$("#foot_query").attr('href', '${pageContext.request.contextPath}/app/coordinate/goCourierTotal.html');
	}
	if(user.groupId === 435){//营销中心
		$("#foot_index").html("派送");
		$("#foot_index").attr('href', '${pageContext.request.contextPath}/app/fillingSingle/toStoreInformation.html');
		$("#foot_query").html("统计");
		$("#foot_query").attr('href', '${pageContext.request.contextPath}/app/fillingSingle/toMarketingOrderTotalView.html');
	}
	if(user.groupId === 436){//门店
		$("#foot_index").html("订单");
		$("#foot_index").attr('href', '${pageContext.request.contextPath}/app/storeSupplies/toStoreList.html');
		$("#foot_query").html("报需");
		$("#foot_query").attr('href', '${pageContext.request.contextPath}/app/storeSupplies/toReport.html');
	}
	
	getLeftMenu = function(){
		$('#my_business_list').html("菜单加载中，请稍后。。。");
		var lStr = '';
		var data={
				"groupId":user.groupId,
				"vendorId":0,
				"brandId":0
		};
		API.ajax("/app/wxmenu/getWxMenuByAll",data).success(function(data){
			var str = "";
			for(var i=0;i<data.info.length;i++){
				str += '<a href="'+API.httpserver+data.info[i].address+'"><div class="'+ data.info[i].ioc+' left">'+data.info[i].name+'</div></a>'
			}
			str += '<a href="javascript:log_out()"><div class="leftDaohang14 left">退出系统</div></a>';
			$('#my_business_list').html(str);
		}).error(function(data){
			lStr = 
				'<a href="setup.html"><div class="leftDaohang1 left">个人设置</div></a>'+
				'<a href="mobilesales/wxDetials.html"><div class="leftDaohang8 left">微信绑定</div></a>'+
				'<a href="javascript:log_out()"><div class="leftDaohang14 left">退出系统</div></a>';
			$('#my_business_list').html(lStr);
		}).exception(function(data){
			lStr = 
				'<a href="setup.html"><div class="leftDaohang1 left">个人设置</div></a>'+
				'<a href="mobilesales/wxDetials.html"><div class="leftDaohang8 left">微信绑定</div></a>'+
				'<a href="javascript:log_out()"><div class="leftDaohang14 left">退出系统</div></a>';
			$('#my_business_list').html(lStr);
		})
	}

	//重写菜单结束
});

function log_out(){
	API.clearAllStore();
	window.document.location.href=API.httpserver + "/app/login.html";
}

</script>
