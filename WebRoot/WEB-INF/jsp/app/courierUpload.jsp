<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
		<link href="${pageContext.servletContext.contextPath }/css/bootstrap.css" rel="stylesheet" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/AjaxUpload.js"></script>
		<script src="http://www.codefans.net/ajaxjs/jquery-1.6.2.min.js"></script>  
		<style type="text/css">
		.tpsc{ background: #fff; height: 150px; width:100%; margin: 10px 0;}
		.tpsc img{width:100%; height: 100%;}
		.btn-warning,.btn-info{ width:200px; height: 35px}
		.file{ width:200px; height: 35px; border:1px #000 solid;margin-top:-35px; position: relative; opacity:0;}
		</style>
	</head>
	<body class="bjs-gary">
	<!-- header -->
		<header id="weixin_header">
			<script>
				API.settingHeard("拍照上传");//修改“个人设置”这个参数为你本页面的功能名
			</script>
		</header>
	  
			<form id="imgForm" action="${pageContext.request.contextPath }/app/coordinate/uploadImg.html" method="post" enctype="multipart/form-data" >
			   
			   
			    <div style=" padding: 15px;">
				    <div>
					    <button class="btn btn-warning" type="button">
					    	<span class="glyphicon glyphicon-arrow-up"></span>
					    	点击上传订单照片
					    </button>
					    <input type="file" id="orderImg" name="orderImg" class="file" accept="image/*"/>
				    </div>
				    <div class="tpsc" id="result">
				    	
				    </div>
				    <div>
					    <button class="btn btn-info" type="button">
					    	<span class="glyphicon glyphicon-arrow-up"></span>
					    	点击上传门头照片
					    </button>
					    <input type="file" id="storeImg" name="storeImg" class="file" accept="image/*"/>
				    </div>
				    <div class="tpsc"  id="result1">
				    	
				    </div>
				    <div id="shangchuan"  style="color:red;text-align: center;"></div>
				    <div>
					   <input  type="hidden" value="${logistics}" name="logistics"/>
					   <input  type="hidden" value="${map.consignee}" name="signPeople"/>
					   <input  type="hidden" value="${map.phone}" name="signPhone"/>
					   <input  type="hidden" value="${map.recipientTime}" name="recipientTime"/>
					    <input type="button" class="btn btn-success btn-block"  value="提 交"  id="tijiao">
				    </div>
			    </div>
			</form>
		
	<!-- 个人中心	底部导航 -->
	<%@include file="/common/leftBottom.jsp" %>
	</body>
</html>

<script type="text/javascript">
$("#tijiao").click(function(){
	/* $("#imgForm").submit(); */
	/*修改后：两张图片都不允许为空*/
	var img1 = document.getElementById("result").getElementsByTagName("img").length;
	var img2 = document.getElementById("result1").getElementsByTagName("img").length;
	if(img1 == 0){
		$("#shangchuan").html("请选择订单照片");
		return ;
	} 
	if(img2 == 0){
		$("#shangchuan").html("请选择门店照片");
		return ;
	}else{
		var formData = new FormData($( "#imgForm" )[0],$( "#imgForm" )[1]); 
		console.log(formData);
		$.ajax({  
	         url: '${pageContext.request.contextPath }/app/coordinate/uploadImg.html' ,  
	         type: 'POST',  
	         data: formData,  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function (returndata) {  
	             if(returndata.state=="ok"){
	            	 $("#shangchuan").html("上传成功,3秒之后跳转");
		        	 setTimeout("location.href='${pageContext.request.contextPath}/app/coordinate/tocourierList.html'",3000);
	             }
	         },  
	         error: function (returndata) {  
	        	 $("#shangchuan").html("订单照片或门头照片未上传");
	         }  
	    }); 
	}
	 
	    /*修改时间：2017年11月22日 14:33*/
	    /*修改原因：new FormData的参数需要一个HTMLElement类型的数据:$("#imgForm")[0]只传递了一个参数，另一个参数无法传递到后台*/
		/* var formData = new FormData($( "#imgForm" )[0]); 
	    $.ajax({  
	         url: '${pageContext.request.contextPath }/app/coordinate/uploadImg.html' ,  
	         type: 'POST',  
	         data: formData,  
	         async: false,  
	         cache: false,  
	         contentType: false,  
	         processData: false,  
	         success: function (returndata) {  
	             if(returndata.state=="ok"){
	            	 $("#shangchuan").html("上传成功,3秒之后跳转");
		        	 setTimeout("location.href='${pageContext.request.contextPath}/app/coordinate/tocourierList.html'",3000);
	             }
	         },  
	         error: function (returndata) {  
	        	 $("#shangchuan").html("订单照片或门头照片未上传");
	         }  
	    });  */
	
	
})
</script>


  <!--   引入jQuery  -->  
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/LocalResizeIMG.js"></script>  
      
    <!--   mobileBUGFix.js 兼容修复移动设备       -->  
    <script src="${pageContext.request.contextPath}/js/mobileBUGFix.mini.js" type="text/javascript"></script>  
    <script type="text/javascript">  
    $(function(){
  	  API.checkLogin();
    })
    
    $("#orderImg").change(function(){  
    	  var objUrl = getObjectURL(this.files[0]) ;  
    	  if (objUrl) {  
    	    $("#result").html("<img src="+objUrl+">") ;  
    	  }  
    	}) ;  
    $("#storeImg").change(function(){  
    	  var objUrl1 = getObjectURL(this.files[0]) ;  
    	  if (objUrl1) {  
    	    $("#result1").html("<img src="+objUrl1+">") ;  
    	  }  
    	}) ;  
    	function getObjectURL(file) {  
    	  var url = null ;   
    	  if (window.createObjectURL!=undefined) {  
    	    url = window.createObjectURL(file) ;  
    	  } else if (window.URL!=undefined) { 
    	    url = window.URL.createObjectURL(file) ;  
    	  } else if (window.webkitURL!=undefined) { 
    	    url = window.webkitURL.createObjectURL(file) ;  
    	  }  
    	  return url ;  
    	}  
    /* function readAsDataURL(){  
    	$("#result").empty();  
        for(i ==0; i< file.length; i ++) {
            var reader    = new FileReader();    
            reader.readAsDataURL(file[i]);  
            names=file[i].name;
            console.log(file[i].name)
            reader.onload=function(e){  
                //多图预览
                $("result").html(this.result);
            }

        }
        
    }       function imgYanzheng(){
        	if(file==""){
        		alert("请上传图片");
        		return false;
        	}else{
        		
                	 return true;
               
        	}
        	
        	
        	
        } 
      */
    
 
    </script>  
