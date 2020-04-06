<%@page import="com.sun.jersey.server.impl.container.servlet.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
	<head>
		<%@include file="/common/common-app-head.jsp" %>
		<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
		<script type="text/javascript"	src="http://api.map.baidu.com/api?v=2.0&ak=qmQNEi1qbFX628WfMt4imhdT87RbCRzK"></script>
       <script type="text/javascript"	src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
		<style type="text/css">  
		    html{height:100%}    
		    body{height:100%;margin:0px;padding:0px}    
		    #container{height:100%}    
			</style>
	</head>
	<body class="bjs-gary">
	<!-- header -->
		<header id="weixin_header">
			<script>
				API.settingHeard("订单坐标");//修改“个人设置”这个参数为你本页面的功能名
			</script>
		</header>
		<div>  
		<div id="container"></div>
		
		
		</div>
		
		
		
	<!-- 个人中心	底部导航 -->
	<%@include file="/common/leftBottom.jsp" %>
	</body>
</html>

<script type="text/javascript">
  $(function(){
	  API.checkLogin();
  })
     
            var user=API.getUserInfo();
             var courierPhone=user.accountName;
     //西安坐标108.955, 34.282
				var map = new BMap.Map("container");    
				//var point = new BMap.Point();    
				map.centerAndZoom(new BMap.Point(108.955, 34.282),13); 
				var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
				var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮
				map.addControl(top_left_control);        
				map.addControl(top_right_navigation); 
				 API.ajax("/app/coordinate/findIncomplete",{"courierPhone":courierPhone}).success(function(datas){
		            
		       if(datas.state="ok"){
		    	   var opts = {
							width : 250,     // 信息窗口宽度
							height: 80,     // 信息窗口高度
							title : "<strong>门店 ：</strong>"// 信息窗口标题
						   };
				for(var i=0;i<datas.list.length;i++){
					var point = new BMap.Point(datas.list[i].store_longitude,datas.list[i].store_latitude);
					map.centerAndZoom(point,13); 
					var marker = new BMap.Marker(point);  // 创建标注
					var content ='<div id="LoginBox">'
                        + '<div class="row" style="width: 200px;height: 20px;margin-top:-5px;">门店名字:'+datas.list[i].store+' </div>'
                        + '<div class="row" style="width: 250px;height: 20px;">门店地址: '+datas.list[i].address+'</div>'
                        + '<div class="row">'
                        + '<input onclick="tiaozhuan(\''+datas.list[i].channel_code+'\')" type="button" id="btn1" value="查看订单详情" style="width: 100px;height:25px;margin-top: 0px;margin-left: 150px;">'
                        + '</div></div>' ;
					map.addOverlay(marker);               // 将标注添加到地图中
					addClickHandler(content,marker);
					
				}
				function addClickHandler(content,marker){
					marker.addEventListener("click",function(e){
						openInfo(content,e)}
					);
				}
				
				function openInfo(content,e){
					var p = e.target;
					var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
					var infoWindow = new BMap.InfoWindow(content,opts);   // 创建信息窗口对象 
					map.openInfoWindow(infoWindow,point); //开启信息窗口
				}
		       }
		      
				
				 }).error(function(){
			           
			        }).exception(function(){
			        }); 
				 /* var myP1 = new BMap.Point(108.935630,34.292363);    //起点
				var myP2 = new BMap.Point(108.956534,34.245261);    //终点
				var driving2 = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});    //驾车实例
				driving2.search(myP1, myP2);    //显示一条公交线路 */

				 function tiaozhuan(channel_code){
					   window.location.href="coordinate/storeDetails.html?channel_code="+channel_code+"&&courierPhone="+courierPhone;
			       }
				
</script>
