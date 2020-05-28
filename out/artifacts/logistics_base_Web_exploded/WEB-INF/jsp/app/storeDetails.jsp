<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>快递员-订单列表查看详情页-获取定位中</title>>
    <%@include file="/common/common-app-head.jsp" %>
    <link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css"/>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=qmQNEi1qbFX628WfMt4imhdT87RbCRzK"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/CurveLine/1.5/src/CurveLine.min.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
    <style type="text/css">
        /* 本页面css样式 */
        .a {
            position: fixed;
            left: 40%;
            width: 25%;
            margin-top: 10px;
        }

        .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
            vertical-align: middle;
        }
    </style>
</head>
<body class="bjs-gary">
<!-- header -->
<header id="weixin_header">
    <script>
        API.settingHeard("订单详情");//修改“个人设置”这个参数为你本页面的功能名
    </script>
</header>
<div class="" style="margin-top: 20px;">
    <div class="models">
        <div class="bian"></div>
        <div class="please">订单详情:</div>
    </div>
    <table class="table table-bordered" style="margin-top: 10px;font-size: 14px;">
        <tr class="warning">
            <td width="150">订单号:</td>
            <td>${maps.id}</td>
        </tr>
        <tr class="info">
            <td>发货人姓名:</td>
            <td>${maps.shipperName }</td>
        </tr>
        <tr class="warning">
            <td>发货人电话:</td>
            <td>${maps.shipper }</td>
        </tr>
        <tr class="info">
            <td>发货时间：</td>
            <td>
                <c:choose>
                    <c:when test="${not empty maps.shipTime }">
                        <fmt:formatDate value="${maps.shipTime }" pattern="yyyy年MM月dd日  HH点mm分ss秒"/>
                    </c:when>
                    <c:otherwise>
                        未发货
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="warning">
            <td>邮寄预计时长：</td>
            <td>${maps.predictTime }&nbsp;&nbsp;小时</td>
        </tr>
        <tr class="info">
            <td>收货门店名称：</td>
            <td>${maps.store }</td>
        </tr>
        <tr class="warning">
            <td>收货门店归属品牌：</td>
            <td>${maps.brand }</td>
        </tr>
        <tr class="info">
            <td>收货门店渠道编码：</td>
            <td>${maps.channel_code }</td>
        </tr>
        <tr class="warning">
            <td>收货人姓名：</td>
            <td>${maps.consignee }</td>
        </tr>
        <tr class="info">
            <td>收货人电话：</td>
            <td>${maps.phone }</td>
        </tr>
        <tr class="warning">
            <td>实际邮寄时长：</td>
            <td>
                <c:choose>
                    <c:when test="${empty maps.factTime }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        ${maps.factTime }
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="info">
            <td>订单状态：</td>
            <td>
                <c:if test="${maps.state < 3 }">
                    已发货
                </c:if>
                <c:if test="${maps.state >= 3 && maps.state < 6 }">
                    派送中
                </c:if>
                <c:if test="${maps.state >= 6 }">
                    已送达
                </c:if>
            </td>
        </tr>

        <tr class="warning">
            <td>物资名称及数量:</td>
            <td>${maps.materialNameAndNumber }</td>
        </tr>
        <tr class="info">
            <td>快递员姓名:</td>
            <td>${maps.courierName }</td>
        </tr>
        <tr class="warning">
            <td>快递员电话:</td>
            <td>${maps.courierPhone }</td>
        </tr>
        <tr class="info">
            <td>快递员收件时间：</td>
            <td>
                <c:choose>
                    <c:when test="${empty maps.recipientTime }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        <fmt:formatDate value="${maps.recipientTime }" pattern="yyyy年MM月dd日  HH点mm分ss秒"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="warning">
            <td>快递员送达时间：</td>
            <td>
                <c:choose>
                    <c:when test="${empty maps.serviceTime }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        <fmt:formatDate value="${maps.serviceTime }" pattern="yyyy年MM月dd日  HH点mm分ss秒"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>

    <!--发货状态（默认0为待发货，2快递员接收订单已发货，3：派送中,4：已收货,5货物已送达，6已签收-->
    <c:if test='${maps.state!="6"}'>
        <div style="text-align: center; width: 100%;">
            <input type="button" id="peisong"
                   onclick="peisong('${maps.store_longitude}','${maps.store_latitude}','${maps.courierPhone}','${maps.id}','${maps.address }','${maps.channel_code }','${maps.phone}','${maps.consignee }')"
                   class="btn btn-success"
                   value="<c:if test='${maps.state=="3"}'>查看派送地图</c:if><c:if test='${maps.state=="0" or maps.state=="1"or maps.state=="2"}'>开始派送</c:if>"
                   id="peisong"/>
        </div>
    </c:if>

    <%-- <input  type="button" onclick="jujue()"
         class="btn btn-success" value="拒绝派送"  style="<c:if test='${maps.state=="4"}'>display:none</c:if>"/> --%>

    <%-- <div style=" position: fixed; width: 100%; height: 100%; top:0; left:0; background: #000; z-index: 89999;opacity:0.5; display:none " id="mengban">
   </div>
   <div style=" position: fixed; width: 80%; height: 260px; top:200px;left:10%; background: #fff; z-index: 89999;display:none " id="motaikuang">
   <h4>请填写拒绝原因:</h4>

          <div class="form-group">
           <input type="checkbox" value="订单冲突" name="reason"  />订单冲突
       </div>
       <div class="form-group">
           <input type="checkbox" value="质量问题" name="reason"  />质量问题
       </div>
       <div class="form-group">
           <input type="checkbox" value="个人原因" name="reason"  />个人原因
       </div>
       <div class="form-group">
           <input type="checkbox" value="时间问题" name="reason"   />时间问题
       </div>
     <div>
       <input type="button"  onclick="tijiao('${maps.phone}','${maps.id}')" class="btn a btn-success" value="提交" />
     </div>
   </div> --%>
</div>


<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
</html>

<script type="text/javascript">
    $(function () {
        API.checkLogin();
    })
    // TODO(likang):未进入gerCurrentPosition方法，获取courier定位
    var lng = "";
    var lat = "";
    var reason = $("input[name='reason']:checked").serialize();
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function (r) {
        lng = r.point.lng;
        lat = r.point.lat;

    }, {enableHighAccuracy: true});


    function peisong(store_longitude, store_latitude, courierPhone, id, address, channel_code, phone, consignee) {
        var peisong = $("#peisong").val()
        API.ajax("/app/coordinate/updateState", {"id": id}).success(function (datas) {
            if (datas.state == "ok") {
                if (peisong == "开始派送") {
                    API.ajax("/app/coordinate/addCourierstore", {
                        "courier_longitude": lng,
                        "courier_latitude": lat,
                        "courier_Phone": courierPhone,
                        "logistics": id
                    }).success(function (data) {
                        if (data.state = "ok") {
                            // 订单列表中的store经纬度
                            window.location.href = "${pageContext.request.contextPath}/app/coordinate/detailedRoute.html?store_longitude=" + store_longitude +
                                "&&store_latitude=" + store_latitude + "&&phone=" + phone + "&&logistics=" + id + "&&channel_code=" + channel_code + "&&address=" + encodeURIComponent(address) + "&&consignee=" + encodeURIComponent(consignee);
                        }
                    }).error(function (data) {
                    });
                } else if (peisong == "查看派送地图") {
                    API.ajax("/app/coordinate/updateCourierstore", {
                        "courier_longitude": lng,
                        "courier_latitude": lat,
                        "courier_Phone": courierPhone,
                        "logistics": id
                    }).success(function (data) {
                        if (data.state = "ok") {
                            window.location.href = "${pageContext.request.contextPath}/app/coordinate/detailedRoute.html?store_longitude=" + store_longitude +
                                "&&store_latitude=" + store_latitude + "&&phone=" + phone + "&&logistics=" + id + "&&channel_code=" + channel_code + "&&address=" + encodeURIComponent(address) + "&&consignee=" + encodeURIComponent(consignee);
                        }
                    }).error(function (data) {
                    });
                }


            }
        }).error(function (data) {
        });
    }


    // function jujue(store_longitude, store_latitude, courierPhone, id, logistics) {
    //     $("#mengban").show();
    //     $("#motaikuang").show();
    //
    // }
    //
    //     function tijiao(phone,id,logistics){
    //
    //     }
    //
    // $("#mengban").click(function () {
    //     $("#mengban").hide();
    //     $("#motaikuang").hide();
    // });
</script>
