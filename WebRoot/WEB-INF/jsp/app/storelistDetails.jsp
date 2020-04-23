<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>门店-订单详情</title>>
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
            <td width="150px">订单号:</td>
            <td>${maps.id }</td>
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
            <td>${maps.predictTime}&nbsp;&nbsp;小时</td>
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


</div>


<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
</html>

<script type="text/javascript">
    $(function () {
        API.checkLogin();
    })

</script>
