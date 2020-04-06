<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<%@include file="/common/common-app-head.jsp" %>	
    <script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    
    <script type="text/javascript">
    	var  a = "${delstate}"
    	if(a != null && a != "") {
    		alert(a);	
    	}
    </script>
    
    
</head>
<style>

.scans{ background: #fff; height:40px;width:100%;margin:20px 0;}
.herenzhanghao{ float:left; width:40%; height:40px; line-height:40px; text-align:right;}
.wenxinnicheng{ float:left; width:60%;height:40px; line-height:40px;text-align:left; padding-left:10px;}
.chongxinbangding{ margin-top:30px; text-align:center;}
</style>

<body class="bjs-gary" >
	<div>
        <div class="policy_page new_broad_page text-center">
            <header>
		    	<a href="javascript:history.go(-1);"><div class="pull-left juleft">
		    		<span class="glyphicon glyphicon-menu-left"></span>返回</div></a>
		        <h4 class="pull-left">微信绑定</h4>
		    </header>          
        </div>               
	    <div class="scans">
	        <div class="code herenzhanghao">个人账号：</div><div class="code wenxinnicheng" id='real_name'></div>
	    </div>
        <div class="scans">
	        <div class="code herenzhanghao">微信昵称：</div> <div  class="code wenxinnicheng" id="onMenuShareTimeline"></div>
	    </div>
	    
		<div class="chongxinbangding" >
			<input type="button" value="重新绑定" id="updateBind" class="btn btn-success">
			<!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" value="解除绑定" id="deleteBind" class="btn btn-warning"> -->
		</div>
		
		
    </div>
    
    
    <%@include file="/common/leftBottom.jsp" %>
    
</body>

<script type="text/javascript">
	$(document).ready(function(){
		var user = API.getUserInfo();
		var accountName = user.accountName;
	    API.ajax("/app/weixin/find",{"userPhone":accountName}).success(function(data){
			$("#real_name").html(accountName);
			$("#onMenuShareTimeline").html(data.info.nickname);
		}).error(function(data){
			$("#real_name").html(accountName);
			alert(data.info);
		})
	})
	$("#updateBind").click(function(){
	//用户授权，微信自动生成code,由于授权操作安全等级较高，所以在发起授权请求时，微信会对授权链接做正则强匹配校验，如果链接的参数顺序不对，授权页面将无法正常访问
	//授权页面点击确认登录即可跳转到刚刚配置的回调页面，并获取了微信传回的code参数

	window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx18a4c6de2f98bf87&redirect_uri=http://casually-test.d1.natapp.cc/wn_channel_system/app/TopayServlet&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
	})

</script>

</html>
