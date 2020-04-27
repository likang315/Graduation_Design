<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>快递员-订单列表查看详情页-已接单-开始派送-获取路线详情中或已送达</title>
    <%@include file="/common/common-app-head.jsp" %>
    <link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css"/>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=qmQNEi1qbFX628WfMt4imhdT87RbCRzK"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
    <style type="text/css">
        html {
            height: 100%
        }

        body {
            height: 100%;
            margin: 0px;
            padding: 0px
        }

        #container {
            height: 100%
        }

        .a {
            position: fixed;
            right: 130px;
            bottom: 60px;
        }

        .b {
            position: fixed;
            right: 10px;
            bottom: 60px;
        }

        .d {
            position: fixed;
            right: 220px;
            bottom: 60px;
        }

        .maskyanzhengma {
            width: 130px;
            height: 35px;
            background: #000;
            opacity: 0;
            float: right;
            position: relative;
            margin-top: -35px;
            z-index: 12;
            display: none;
        }
    </style>
</head>
<body class="bjs-gary">
<!-- header -->
<header id="weixin_header">
    <!-- 			<script>
                    API.settingHeard("订单详细路线"); //修改“个人设置”这个参数为你本页面的功能名
                </script> -->
    <div class="pull-left"><a href="javascript:" onclick="history.go(-2);">
        <span class="glyphicon glyphicon-menu-left"></span>返回
    </a>
    </div>
    <h4 class="pull-left">订单详细路线</h4>
    <div class="pull-right">
        <img src="${pageContext.request.contextPath }/images/china_mobile.png"/>
    </div>
</header>
<div>
    <input type="hidden" value="${address }" id="address"/>
    <input type="hidden" value="${maplist.recipientTime }" id="recipientTime"/>
    <div id="container">

    </div>
    <div class="a">
        <input type="button" id="search" value="查看详情" class="btn btn-success"/>
    </div>
    <!-- <div class="d">
       <input type="button" id="caiji" value="采集门店经纬度" class="btn btn-success"/>
     </div>  -->

</div>

<input type="button" class="btn b btn-success" id="xianshi" value="确认货物送达"/>

<div style=" position: fixed; width: 100%; height: 100%; top:0; left:0; background: #000; z-index: 89999;opacity:0.5; display:none "
     id="mengban">
</div>


<!--确认送达时 -->
<div style=" position: fixed; width: 80%; height: 200px; top:150px;left:10%; background: #fff; z-index: 89999;display:none "
     id="motaikuang">
    <div style="padding:15px;">
        <h4>已到达目的地</h4>
        <h5 style=" margin: 15px 0; ">请输入发送收货人的验证码:</h5>

        <div class="form-group">
            <div class="input-group">
                <input type="text" class="form-control" id="code" name="code">
                <span class="input-group-btn">
						        	<input onclick="acquireCode()" type="button" class="btn btn-info"
                                           id="huoquyanzhengma" value="获取验证码"/>
                </span>
            </div>

            <div class="maskyanzhengma" id="maskyanzhengma"></div>


        </div>

        <div class="show-content"
             style=" height:20px; color: red;font-size:12px;text-align: center; margin-bottom: 5px"></div>
        <div style="text-align: center;">
            <!-- TODO(likang): -->
            <input type="button" class="btn btn-success" id="uploadimgs" value="上传信息"/>
            &nbsp;&nbsp;
            <input type="button" class="btn  btn-warning" id="qurenshouhuo" value="确认门店收货"/>
        </div>
    </div>
</div>


<div style=" position: fixed; width: 100%; height: 100%; top:0; left:0; background: #000; z-index: 89999;opacity:0.5; display:none "
     id="mengban1">
</div>

<!--派送详情-->
<div style=" position: fixed; width: 80%; height: 150px; top:150px;left:10%; background: #fff; z-index: 89999;display:none "
     id="motaikuang1">
    <div style="padding:15px;" id="juli"></div>
</div>


<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
</html>

<script type="text/javascript">

    $("#uploadimgs").click(function () {

        location.href = "${pageContext.request.contextPath}/app/coordinate/goCourierUpload.html?logistics=" + API.getParam('logistics') + "&&channel_code=" + API.getParam('channel_code') + "&&courierPhone=" + user.accountName;

    })

    $("#xianshi").hide();
    //$("#caiji").hide();
    var user = API.getUserInfo();
    var store_longitude = API.getParam('store_longitude');
    var store_latitude = API.getParam('store_latitude');
    var channel_code = API.getParam('channel_code');
    var phone = API.getParam('phone');
    var logistics = API.getParam('logistics');
    var signPeople = API.getParam('consignee');
    var address = $("#address").val();
    var recipientTime = $("#recipientTime").val();
    var courierPhone = user.accountName;
    var lucheng;
    var store_longitudeA;
    var store_latitudeA;

    $(function () {
        API.checkLogin();
        //页面一加载就定位
        jiazai();
    })

    function jiazai() {
        dingwei();
        setTimeout("jiazai()", 60000);
    }

    //西安坐标108.955, 34.282
    var map = new BMap.Map("container");
    //var point = new BMap.Point();
    map.centerAndZoom(new BMap.Point(108.955, 34.282), 13);
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
    var top_right_navigation = new BMap.NavigationControl({
        anchor: BMAP_ANCHOR_TOP_RIGHT,
        type: BMAP_NAVIGATION_CONTROL_SMALL
    }); //右上角，仅包含平移和缩放按钮
    map.addControl(top_left_control);
    map.addControl(top_right_navigation);

    // 定位方法
    function dingwei() {
        console.log("加载了...");
        var geolocation = new BMap.Geolocation();
        geolocation.getCurrentPosition(function (r) {
            store_longitudeA = r.point.lng;
            store_latitudeA = r.point.lat;
            if (this.getStatus() == BMAP_STATUS_SUCCESS) {
                var output = "";
                var output1 = "";
                var myP1 = new BMap.Point(r.point.lng, r.point.lat);    //起点
                var myP2 = new BMap.Point(store_longitude, store_latitude);    //终点

                // 当快递员的坐标与订单中门店坐标相同是显示
                var searchComplete = function (r) {
                    if (transit.getStatus() != BMAP_STATUS_SUCCESS) {
                        return;
                    }
                    var plan = r.getPlan(0);
                    output = plan.getDuration(true) + "\n";                //获取时间
                    output1 = plan.getDistance(true) + "\n";             //获取距离
                    lucheng = plan.getDistance(true);
                    var mi = lucheng.substr(lucheng.length - 1, lucheng.length - 1);
                    if (mi == "米") {
                        var reg = /\d+/g;
                        var ms = lucheng.match(reg);
                        if (ms < 100) {
                            $("#xianshi").show();
                            $("#mengban").show();
                            $("#motaikuang").show();

                        }
                    } else {
                        $("#xianshi").hide();
                        //$("#caiji").hide();
                    }
                }

                // 路线查看详情
                if (store_longitude == "" || store_latitude == "" || store_longitude == 0 || store_latitude == 0) {
                    var transit = new BMap.DrivingRoute(map, {
                        renderOptions: {map: map, autoViewport: true},
                        onSearchComplete: searchComplete,
                        onPolylinesSet: function () {
                            $("#search").click(function () {
                                $("#mengban1").show();
                                $("#motaikuang1").show();
                                $("#juli").html("<h4 style='color: green;'>距离终点的时间及路程</h4><div style='margin-top: 15px;'>距离终点的还需：" + output + "</div><div>距离终点还有：" + output1 + "</div>");

                            })

                        }
                    });
                    transit.search(myP1, address);
                } else {
                    var transit = new BMap.DrivingRoute(map, {
                        renderOptions: {map: map},
                        onSearchComplete: searchComplete,
                        onPolylinesSet: function () {
                            $("#search").click(function () {
                                $("#mengban1").show();
                                $("#motaikuang1").show();
                                $("#juli").html("<h4 style='color: green;'>距离终点的时间及路程</h4><div style='margin-top: 15px;'>距离终点的还需：" + output + "</div><div>距离终点还有：" + output1 + "</div>");
                            })

                        }
                    });
                    transit.search(myP1, myP2);
                }


            } else {

            }
        }, {enableHighAccuracy: true});
    }


    $("#xianshi").click(function () {
        $("#mengban").show();
        $("#motaikuang").show();

    })

    $("#mengban").click(function () {
        $("#mengban").hide();
        $("#motaikuang").hide();

    })
    $("#mengban1").click(function () {
        $("#mengban1").hide();
        $("#motaikuang1").hide();

    })


    function acquireCode() {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/app/code/getCode.html",
            data: {
                "tellPhone": phone
            },
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.state = "ok") {

                } else {
                    $(".show-content").html("验证码发送失败！");
                    //window.location.href="${pageContext.request.contextPath}/app/coordinate/goCourierUpload.html";
                }
            }
        });
        getIdentifyingCodeTime();
    }

    var times_index = 60;

    function getIdentifyingCodeTime() {
        if (times_index > 0) {
            times_index--;
            $("#huoquyanzhengma").val(times_index + "秒后重新获取");
            $("#huoquyanzhengma").css("color", "gray");
            $("#maskyanzhengma").show();
            setTimeout("getIdentifyingCodeTime()", 1000);
        } else {
            time_index = 60;
            $("#huoquyanzhengma").css("color", "#fff");
            $("#huoquyanzhengma").val("获取验证码")
            $("#maskyanzhengma").hide();
        }
    }

    $("#qurenshouhuo").click(function () {
        confirmreceipt();
    });

    function confirmreceipt() {
        var yangzhenmazz = /^\d{6}$/;
        var yanzhengma = $("#code").val();
        if (!yangzhenmazz.test(yanzhengma)) {
            $(".show-content").html("请输入6位正确的验证码");
            $("#code").css("border", "1px solid #F00");
            return false;
        }
        API.ajax("/app/code/testCode2", {"tellPhone": phone, "code": yanzhengma}).success(function (data) {
            API.ajax("/app/coordinate/updateOrder", {
                "id": logistics,
                "signPhone": phone,
                "signPeople": signPeople,
                "recipientTime": recipientTime
            }).success(function (datas) {
                if (datas.state == "ok") {
                    if (store_longitude == "" || store_latitude == "" || store_longitude == 0 || store_latitude == 0) {
                        var data = {
                            "id": logistics,
                            "store_longitude": store_longitudeA,
                            "store_latitude": store_latitudeA,
                            "channel_code": channel_code
                        };

                        API.ajax("/app/coordinate/addstore", data).success(function (datas) {
                            if (datas.state == "ok") {
                                $(".show-content").html("");
                                $(".show-content").html("货物已送达,3秒之后跳转！");
                                setTimeout("location.href='${pageContext.request.contextPath}/app/coordinate/tocourierList.html'", 3000);

                            }
                        }).error(function (datas) {
                        });
                    } else {
                        $(".show-content").html("");
                        $(".show-content").html("货物已送达,3秒之后跳转！");
                        setTimeout("location.href='${pageContext.request.contextPath}/app/coordinate/tocourierList.html'", 3000);
                        //108.953452,34.265728
                    }


                }
            }).error(function (datas) {
                $(".show-content").html("货物运输失败！");
                return false;
            });
        }).error(function (data) {
            $(".show-content").html("");
            $(".show-content").html("验证码验证失败！");
            return false;
        });
    }
</script>
