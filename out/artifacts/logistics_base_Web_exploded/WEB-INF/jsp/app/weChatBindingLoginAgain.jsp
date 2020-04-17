<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/cover.css" rel="stylesheet" />

    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
	<style type="text/css">
		#hint1{
			color:red;
			text-align:center;
			display:block;
			
		}
	</style>
<body >

   	<div style="width:100%; height:170px; background:#4cb12f; text-align:center; padding-top:30px;">
    	<div><img src="${pageContext.request.contextPath}/images/wenxin.png"></div>
        <div style="margin-top:20px; color:#FFF;" id="nickname">当前微信昵称:</div>
    </div>
    <br/>
    <div class="form-group">
      	 <input type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入渠道营销平台登录密码进行确认"/>
     </div>
     <span id="hint1"></span>
	<div>
		<input id="login" type="button" value="确认绑定" class="btn btn-block" style="background:#4cb12f; margin-top:40px; color:#FFF;">
 	</div>
 	<div id="name" style="display:none;">${nickname}</div>
 	<div id="openIds" style="display:none;">${openid}</div>
 	<div id="flag" style="display:none;">${isflag}</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/json2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/API.js"></script>
<script type="text/javascript">

	var openid="";
	var nickname="";
	var unionid="";
	var flag="";
	var user = API.getUserInfo();
	$(document).ready(function(){
		openid=$("#openIds").text();
		newnickname=$("#name").text();
		flag=$("#flag").text();
	    var n=self.location.href.lastIndexOf("=")
		if(n>0){
			var para=self.location.href.substr(n+1);//参数
		}
		nickname=decodeURI(para);
		API.setStore("openId",openid);
		API.setStore("nickname",nickname);
		API.setStore("unionid",unionid);
		
		$("#nickname").html("当前微信昵称:"+nickname);
		$('#login').click(function(){
			var username = user.accountName;
			var password = $("#exampleInputPassword1").val();
			if(!password){
				$("#exampleInputPassword1").css({ "border-color": "red"});
				return;
			}
			var imei = API.getStore("imei");			
			
		    var pathName =  window.document.location.pathname;
			var ProjectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		
			$.ajax({
				type:'POST',
				async:true,
				cache:false,
				url: ProjectName + "/app/weixin/loginAgain.html",
				data:{"username":username,"password":password,"token":imei,"openid":openid,"nickname":nickname,"unionid":unionid,"flag":flag},
				dataType:'json',
				success:function(data){
					if(data.state == "ok")
						window.location.href="${pageContext.request.contextPath}/app/weixin/weChatBinding.html";
					else{
						var info=data.info;
						$("#hint1").html(info);
						$("#hint1").css({"display":"block"});
					}
				},
				error:function(data){
					var info=data.info;
					$("#hint1").html("网络错误！");
					$("#hint1").css({"display":"block"});
				} 
			})
		});
	});
	
</script>
</html>
