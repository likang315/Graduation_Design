<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@include file="/common/common-css.jsp" %>
<%@include file="/common/common-js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.servletContext.contextPath }/css/bootstrap.css" rel="stylesheet" />
		<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/API.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
		<script type="text/javascript"	src="http://api.map.baidu.com/api?v=2.0&ak=qmQNEi1qbFX628WfMt4imhdT87RbCRzK"></script>
       <script type="text/javascript"	src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
		<style type="text/css">  
		    html{height:100%}    
		    body{height:100%;margin:0px;padding:0px}    
		    #container{height:100%}    
		    .a { position:fixed; right:130px; bottom:60px;}
		    .b { position:fixed; right:10px; bottom:60px;}
		    .c { position:fixed; left:40%; width: 25%;margin-top: 10px; }
			</style>
	</head>
	<body>
<div class="place">
    	<span>位置：</span>
	    <ul class="placeul">
		    <li><a href="#">首页</a></li>
		    <li><a href="#">订单管理</a></li>
		    <li><a href="#">显示地图</a></li>
	    </ul>
    </div>
    <div class="itab_nav">
  	<ul> 
    <li><a href="#tab1" class="selected">显示地图</a></li>
  	</ul>
    </div>
    <div class="line">
    	<div id="tab2" class="tabson">
		<input id="store_longitude" type="hidden" value="${maplist1.store_longitude }"/>
		<input id="store_latitude" type="hidden" value="${maplist1.store_latitude }"/>
		<input id="courier_longitude" type="hidden" value="${maplist.courier_longitude}"/>
		<input id="courier_latitude" type="hidden" value="${maplist.courier_latitude }"/>
		<input id="address" type="hidden" value="${maplist1.address }"/>
				
					<div id="container">
					</div>
					
					<div class="a">
					<input  type="button" id="search" value="查看时间及路程" class="btn btn-success"/>
					    <input type="button" id="fanhui" value="返回" class="btn btn-success"/>
					  </div>
					  
					
			</div>
			</div>
			
	</body>
</html>

<script type="text/javascript">
            var user=API.getUserInfo();
            var store_longitude = $("#store_longitude").val();
            var store_latitude = $("#store_latitude").val();
            var courier_longitude = $("#courier_longitude").val();
            var courier_latitude = $("#courier_latitude").val();
            var address = $("#address").val();
             var lucheng;
          // 百度地图API功能
         	var map = new BMap.Map("container");
         	map.centerAndZoom(new BMap.Point(108.955, 34.282), 13);
         	var myP1 = new BMap.Point(courier_longitude,courier_latitude);    //起点
         	var myP2 = new BMap.Point(store_longitude,store_latitude);    //终点
         	var output = "快递员到终点的时间：";
         	var output1 = "快递员到终点的距离：";
        	var searchComplete = function (results){
        		if (driving.getStatus() != BMAP_STATUS_SUCCESS){
        			return ;
        		}
        			var plan = results.getPlan(0);
        			output += plan.getDuration(true) + "\n";  //获取时间
        			output1 += plan.getDistance(true) + "\n";  //获取距离
        	   
        	}
        	 if(store_longitude==""||store_latitude==""){
					var driving = new BMap.DrivingRoute(map, {renderOptions: {map: map, autoViewport: true},
					onSearchComplete: searchComplete,
					onPolylinesSet: function(){     
						$("#search").click(function (){
							alert(output+output1);
							
						})
						
				}});
					driving.search(myP1, address); 
				
				}else{
					var driving = new BMap.DrivingRoute(map, {renderOptions: {map: map},
						onSearchComplete: searchComplete,
						onPolylinesSet: function(){     
							$("#search").click(function (){
								alert(output+output1);
								
							})
							
					}});
					driving.search(myP1, myP2); 
				} 
        	 
        	 $("#fanhui").click(function (){
					window.location.href="${pageContext.request.contextPath}/background/order/list.html?state=0";
					
				})
         	
</script>
