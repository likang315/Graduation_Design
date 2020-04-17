<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
		<script src="${pageContext.request.contextPath}/js/custom.js"></script>
	</head>
<body class="bjs-gary" >

	<header id="weixin_header">
			<script>
				API.settingHeard("政策下达");
			</script>
	</header>

<div id='loading-mask' style="display: none"></div>
    <div class="container ">
        <div class="policy_page text-center cx_detail_page">
            <section class="policy_box">
                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="home">
                        <div class="search_box">
                            <input type="text" id="outer" placeholder="请输入需要查找的活动关键字">
                            <a href="javascript:;" id="s_search" class="glyphicon glyphicon-search btn_search"></a>
                        </div>

                        
                     <!--    <ul class="nav text-center" role="tablist" style="border-bottom:1px #ddd solid;padding:15px 0;  ">
	                		<li role="presentation" class="active"><a  id="homeali" style="width:90px;" role="tab" data-toggle="tab">政策下达</a></li>
		                    <li role="presentation" ><a id="profileali" style="width:90px;"  role="tab" data-toggle="tab"> 知识库 </a></li>
		                </ul> -->
		                
		                
		                
		                <!-- <div  id="homeid"> -->
                        <div class="zc_list" id="content">
							<script type="text/html" id=tmpl>  	
							{{each datas as value i}}	
                            <div class="media"  onclick="scan('{{value.id}}')">
                                <div class="media-left">
                                    <a href="javascript:;" onclick="scan('{{value.id}}')">
											{{if value.activity_type == 'A'}}
												<img src="../../images/index/qg.jpg" class="media-object" />
											{{/if}}
											{{if value.activity_type == 'B'}}
												<img src="../../images/index/tc3.jpg" class="media-object" />
											{{/if}}
											{{if value.activity_type == 'C'}}
												<img src="../../images/index/tc2.jpg" class="media-object" />
											{{/if}}
											{{if value.activity_type == 'D'}}
												<img src="../../images/index/tc4.jpg" class="media-object" />
											{{/if}}
											{{if value.activity_type == 'E'}}
												<img src="../../images/index/tc1.jpg" class="media-object" />
											{{/if}}
										{{if value.flag == '1'}}
											<span class="tips_wd">未读</span>
										{{/if}}
                                    </a>
                                </div>
                                <div class="media-body" >
                                    <h4 class="media-heading">{{value.title}}</h4>
                                    <p class="date">开始时间：{{value.start_time.substring(0,16)}}</p>
                                    <p class="info">{{value.description.substring(0,16)}}...</p>
                                </div>
                            </div>
							{{/each}}
                        </script>    
                        </div>
                        
                        <!-- Nav tabs -->
                
                    <div class="zc_list" id="content2">
                            <script type="text/html" id=tmpl2>  	
							{{each datas as value i}}	
                            <div class="media" onclick="scan('{{value.id}}')">
                                <div class="media-left" >
                                    <a href="javascript:;" onclick="scan('{{value.id}}')">
											{{if value.activity_type == 'A'}}
												<img src="../../images/index/qg.jpg" class="media-object" />
											{{/if}}
											{{if value.activity_type == 'B'}}
												<img src="../../images/index/tc3.jpg" class="media-object" />
											{{/if}}
											{{if value.activity_type == 'C'}}
												<img src="../../images/index/tc2.jpg" class="media-object" />
											{{/if}}
											{{if value.activity_type == 'D'}}
												<img src="../../images/index/tc4.jpg" class="media-object" />
											{{/if}}
											{{if value.activity_type == 'E'}}
												<img src="../../images/index/tc1.jpg" class="media-object" />
											{{/if}}
										{{if value.flag == '1'}}   
											<span class="tips_wd">未读</span>
										{{/if}}
                                    </a>
                                </div>
                                <div class="media-body" >
                                    <h4 class="media-heading">{{value.title}}</h4>
                                    <p class="date">开始时间：{{value.start_time.substring(0,16)}}</p>
                                    <p class="info">{{value.description.substring(0,16)}}...</p>
                                </div>
                            </div>
							{{/each}}
                        </script>    
                        </div>  
                        
                        </div>
                        
                <!--        知识库 -->
                        
                        <div id="profile"  style="display:none;">

                            <div class="zc_list" id="content21">
                                <script type="text/html" id=tmp2l>
                                    {{each datas as value i}}
                                    <div class="media"  onclick="scan('{{value.id}}')">
                                        <div class="media-left">
                                            <a href="javascript:;" onclick="scan('{{value.id}}')">
                                                {{if value.activity_type == 'A'}}
                                                <img src="../../images/index/qg.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.activity_type == 'B'}}
                                                <img src="../../images/index/tc3.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.activity_type == 'C'}}
                                                <img src="../../images/index/tc2.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.activity_type == 'D'}}
                                                <img src="../../images/index/tc4.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.activity_type == 'E'}}
                                                <img src="../../images/index/tc1.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.flag == '1'}}
                                                <span class="tips_wd">未读</span>
                                                {{/if}}
                                            </a>
                                        </div>
                                        <div class="media-body" >
                                            <h4 class="media-heading">{{value.title}}</h4>
                                            <p class="date">开始时间：{{value.start_time.substring(0,16)}}</p>
                                            <p class="info">{{value.description.substring(0,16)}}...</p>
                                        </div>
                                    </div>
                                    {{/each}}
                                </script>
                            </div>

                            <!-- Nav tabs -->

                            <div class="zc_list" id="content22">
                                <script type="text/html" id=tmp22>
                                    {{each datas as value i}}
                                    <div class="media" onclick="scan('{{value.id}}')">
                                        <div class="media-left" >
                                            <a href="javascript:;" onclick="scan('{{value.id}}')">
                                                {{if value.activity_type == 'A'}}
                                                <img src="../../images/index/qg.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.activity_type == 'B'}}
                                                <img src="../../images/index/tc3.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.activity_type == 'C'}}
                                                <img src="../../images/index/tc2.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.activity_type == 'D'}}
                                                <img src="../../images/index/tc4.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.activity_type == 'E'}}
                                                <img src="../../images/index/tc1.jpg" class="media-object" />
                                                {{/if}}
                                                {{if value.flag == '1'}}
                                                <span class="tips_wd">未读</span>
                                                {{/if}}
                                            </a>
                                        </div>
                                        <div class="media-body" >
                                            <h4 class="media-heading">{{value.title}}</h4>
                                            <p class="date">开始时间：{{value.start_time.substring(0,16)}}</p>
                                            <p class="info">{{value.description.substring(0,16)}}...</p>
                                        </div>
                                    </div>
                                    {{/each}}
                                </script>
                            </div>
                        </div>

             <!--    </div> -->
            </section>
           
		<!-- 个人中心 -->
        <%@include file="/common/leftBottom.jsp" %>
        </div>
    </div>

</body>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/template.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/json2.js"></script>
<script type="text/javascript">
	//API.checkLogin('policy.html');   
	var type = API.getParam('type');
	var user = API.getUserInfo();
	$(document).ready(function(){
 		//initcss();
 		
 		$('#s_search').click(function(){
 			search(user);
 		});
 		
 		var postdata = {};
 		if(type){
 			postdata = {activitytype:type,groupId:user.groupId,userid:user.id}
 			
 		}else{
 			postdata = {groupId:user.groupId,userid:user.id}
 		}
 		
 		
 		var postdataGov = {};
 		if(type){
 			postdataGov = {activitytype:type,groupId:user.groupId,userid:user.id}
 			
 		}else{
 			postdataGov = {groupId:user.groupId,userid:user.id}
 		}
 		
 		API.ajax("/app/policy/govlist",postdata).success(function(datas){
            initData(datas);
        }).error(function(){
            $("#content").html("<span style=\"color:red;text-align:center;width:100%;display:block;\">暂无政策</span>");
        }).exception(function(){
        });

        API.ajax("/app/policy/getKnow",postdata).success(function(datas){
        	
            initData1(datas);
        }).error(function(){
            $("#content21").html("<span style=\"color:red;text-align:center;width:100%;display:block;\">暂无政策</span>");
        }).exception(function(){
        });
 	});
 	
	
 	function initData(datas){
 		var html = template('tmpl', {datas : datas.activity});
 		var html2 = template('tmpl2', {datas : datas.activity1});
		$("#content").html(html);
		$("#content2").html(html2);
		
 	}
    function initData1(datas){
        var html = template('tmp2l', {datas : datas.activity});
        var html2 = template('tmp22', {datas : datas.activity1});
        $("#content21").html(html);
        $("#content22").html(html2);

    }
 	

 	
 	
 	function banli(id){//办理业务
 		location.href = "user_product2.html?id="+id;
 	}
 	function scan(id){//查看活动内容
 		location.href = "user_product.html?id="+id;
 	}
 	
 	/* function initcss(){
 		document.getElementById("outer").style.width = screen.width-44 +"px";
 	}
 	 */
 	function search(user){
 		var text = $('#outer').val();
 		if(text){
 			if(text.replace(/(^\s*)|(\s*$)/g,'').length  > 0){
 				API.ajax("/app/policy/search",{keyword:text,groupId:user.groupId,userid:user.id}).success(function(data){
 					
 					var htmlstr = '';
 					for(var i=0;i<data.activity.length;i++){
 						htmlstr += '<div class="media"><div class="media-left"><a href="javascript:;"'+"onclick=scan('"+data.activity[i].id+"')>"
 							
 						if(data.activity[i].activity_type == 'A'){
 							htmlstr +='<img src="../../images/index/qg.jpg" class="media-object"/>'
 						}
 						if(data.activity[i].activity_type == 'B'){
 							htmlstr +='<img src="../../images/index/tc3.jpg" class="media-object"/>'
 						}
 						if(data.activity[i].activity_type == 'C'){
 							htmlstr +='<img src="../../images/index/tc2.jpg" class="media-object"/>'
 						}   
 						if(data.activity[i].activity_type == 'D'){
 							htmlstr +='<img src="../../images/index/tc4.jpg" class="media-object"/>'
 						}
 						if(data.activity[i].activity_type == 'E'){
 							htmlstr +='<img src="../../images/index/tc1.jpg" class="media-object"/>'
 						}
 						if(data.activity[i].flag == '1'){
 							htmlstr +='<span class="tips_wd">未读</span>'
 						}
 						htmlstr += '</a></div>'
 						htmlstr += '<div class="media-body"><h4 class="media-heading">'+data.activity[i].title+'</h4>'
 						htmlstr += '<p class="date">开始时间:'+data.activity[i].start_time+'</p>';
 						htmlstr += '<p class="info">'+data.activity[i].description+'...</p></div></div>'
 					}
 					var lis = $("#title_nav > li");
 					var class0 = $(lis[0]).attr('class');
 					if(class0){
 						$("#content").html('');
 	 					$("#content").html(htmlstr);
 					}else{
 						$("#content").html('');
 	 					$("#content2").html(htmlstr);
 	 					$(lis[0]).removeClass('active')
 	 					$(lis[1]).attr('class','active');
 					}
 					
 		 		}).error(function(){
 		 			var lis = $("#title_nav > li");
 					var class0 = $(lis[0]).attr('class');
 					if(class0){
 						$("#content").html('');
 	 		 			$("#content").html("<span style=\"color:red;text-align:center;width:100%;display:block;\">没有搜到相关活动信息！</span>");
 	 		 			
 					}else{
 						$("#content2").html('');
 	 		 			$("#content2").html("<span style=\"color:red;text-align:center;width:100%;display:block;\">没有搜到相关活动信息！</span>");
 	 		 			$(lis[0]).removeClass('active')
 	 		 			$(lis[1]).attr('class','active');
 					}
 					
 		 			
 		 		}).exception(function(){
 		 			
 		 		});
 			}
 		}
 		
 	};
 	
 	function scan(id){//查看活动内容
 		location.href = "Policydetails.html?id="+id;
 	}
 	
 	$("#homeali").click(function(){
 		$("#profile").hide();
 		$("#homeid").show();
 	});
 	$("#profileali").click(function(){
 		$("#homeid").hide();
 		$("#profile").show();
 	});

</script>

</html>
