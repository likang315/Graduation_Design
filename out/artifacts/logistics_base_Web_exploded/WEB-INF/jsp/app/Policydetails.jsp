<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE >
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
		<script src="${pageContext.request.contextPath}/js/custom.js"></script>
	</head>
	<style>
	.tips01 img,.tips01 video{width:100%}
	</style>
<body  class="bjs-gary" >
	<header id="weixin_header">
			<script>
				API.settingHeard("马卡鲁");
			</script>
	</header>

<div id='loading-mask' style="display: none"></div>
    <div class="container ">

        <div class="zcxq_page ">
            <section class="main_zcxq">
                <ul class="tit_info">
                    <li id="titel_info"></li>
                     <li> </li>
                     <li>
                         <a class="ydrs"><i class="fa fa-eye"></i>阅读数<label id="counts">0</label></a>
                         <a class="time"><i class="fa fa-clock-o"></i><label id="end_time">2016-03-20 00:00</label></a>
                     </li>
                </ul>
                <div class="content_info">
                
                   <p class="tips01" id="content">
                   
                   </p>
                    <!-- <img src="images/zcxq_img.jpg" class="img-responsive center-block" />

                 <div class="tips02">
备注：该营销活动与飞享两元半价、飞享38半价、预存升档赠费营销活动互斥。E动38半价自客户参加营销活动后前6个月半价，E动38产品生效后，每月赠送19元话费，赠送6个月，营销包由受理渠道直接添加；
目标客户产品月费不得大于等于38元，在营销活动期间不能平迁或降档，若出现违规操作，不核酬。
                 </div> -->

                </div>
            </section>

<div id="policyId" style="display:none;">${id}</div>

    
           
        <!-- 个人中心 -->
        <div class="sidebar-left" style="left: -270px;">
            <div class="sidebar-scroll-left">
                <div class="navbar-header header head_personal">
                    <div class="sidebar-header-left">
                        <a href="Index.html" class="close-sidebar-left" id="zhehzao">
                            <img src="${pageContext.request.contextPath}/images/index/accessdenied.png" />
                        </a>
                    </div>
                    <section class="member_one">
                        <div class="media">
                            <div class="media-left">
                                <a href="javascript:;">
                                    <img src="${pageContext.request.contextPath}/images/index/member_img.png" class="media-object" />
                                </a>
                            </div>
                            <div class="media-body media-middle">
                                <h4 class="media-heading"><span>账户</span><label id="user_name"></label>      
                                    <br />
                                    <span>所属</span><label id="user_category">西安分公司</label>
                                </h4>
                            </div>
                        </div>
                    </section>

                </div>
                <section class="member_tow">
                    <ul class="presonal_center" id="my_business_list">
                    
                    </ul>
                    <div class="m_logo p15">
                        <img src="${pageContext.request.contextPath}/images/index/m_logo_bottom.png" class="img-responsive center-block" />
                    </div>
                </section>
            </div>
        </div>

</body>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/template.js"></script>
<script type="text/javascript"  src="${pageContext.request.contextPath}/js/json2.js"></script>
<script type="text/javascript">
//var pid = API.getParam('id');
var pid=$("#policyId").text();
	$(document).ready(function(){
		//hint();
		initData(pid);
	});
	
	function initData(id){
		var uinfo = API.getStore('userinfo');
		var postdata;
		if(uinfo){
			var userid = JSON.parse(uinfo);
			postdata = {id:id,userid:userid.id}
		}else{
			postdata = {id:id}
		}
		
		API.ajax('/app/policy/detail', postdata).success(function(data){
			$('#titel_info').html(data.activity.title)
			$('#content').html(data.activity.content);
			$('#end_time').html(data.activity.end_time.substring(0,10))
			$('#counts').html(data.activity.counts);
		});
	}
</script>

</html>
