<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>管理员-index-派送订单详情</title>
    <%@include file="/common/common-app-head.jsp" %>
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
<header id="weixin_header">
    <script>
        API.settingHeard("订单详情");
    </script>
</header>
<div class="" style="margin-top: 20px;">
    <div class="models">
        <div class="bian"></div>
        <div class="please">订单详情:</div>
    </div>
    <table class="table table-bordered" style="margin-top: 10px;font-size: 12px;">
        <tr class="info">
            <td width="150px">订单编码：</td>
            <td>${orderDetail.id }</td>
        </tr>
        <c:if test="${not empty orderDetail.shipTime }">
            <tr class="warning">
                <td>发货人姓名：</td>
                <td>${orderDetail.shipperName }</td>
            </tr>
            <tr class="info">
                <td>发货人电话：</td>
                <td>${orderDetail.shipper }</td>
            </tr>
        </c:if>
        <tr class="warning">
            <td>发货时间：</td>
            <td>
                <c:choose>
                    <c:when test="${not empty orderDetail.shipTime }">
                        <fmt:formatDate value="${orderDetail.shipTime }" pattern="yyyy年MM月dd日  HH点mm分ss秒"/>
                    </c:when>
                    <c:otherwise>
                        未发货
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="info">
            <td>邮寄预计时长：</td>
            <td>${orderDetail.predictTime }&nbsp;&nbsp;小时</td>
        </tr>
        <tr class="warning">
            <td>收货门店名称：</td>
            <td>${orderDetail.store }</td>
        </tr>
        <tr class="info">
            <td>收货门店归属品牌：</td>
            <td>${orderDetail.brand }</td>
        </tr>
        <tr class="warning">
            <td>收货门店渠道编码：</td>
            <td>${orderDetail.channel_code }</td>
        </tr>
        <tr class="info">
            <td>收货门店地址：</td>
            <td>${orderDetail.address }</td>
        </tr>
        <tr class="warning">
            <td>收货人姓名：</td>
            <td>${orderDetail.consignee }</td>
        </tr>
        <tr class="info">
            <td>收货人电话：</td>
            <td>${orderDetail.phone }</td>
        </tr>
        <tr class="warning">
            <td>订单状态：</td>
            <td>
                <c:if test="${orderDetail.state < 3 }">
                    已发货
                </c:if>
                <c:if test="${orderDetail.state >= 3 && orderDetail.state < 6 }">
                    派送中
                </c:if>
                <c:if test="${orderDetail.state >= 6 }">
                    已送达
                </c:if>
            </td>
        </tr>
        <tr class="info">
            <td>实际邮寄时长：</td>
            <td>
                <c:choose>
                    <c:when test="${empty orderDetail.factTime }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        ${orderDetail.factTime }
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="warning">
            <td>签收人姓名：</td>
            <td>
                <c:choose>
                    <c:when test="${empty orderDetail.signPeople }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        ${orderDetail.signPeople }
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="info">
            <td>签收人电话：</td>
            <td>
                <c:choose>
                    <c:when test="${empty orderDetail.signPhone }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        ${orderDetail.signPhone }
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="warning">
            <td>签收时间：</td>
            <td>
                <c:choose>
                    <c:when test="${empty orderDetail.leadTime }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        ${orderDetail.leadTime }
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="info">
            <td>物资名称和数量：</td>
            <td>${orderDetail.materialNameAndNumber }</td>
        </tr>
        <tr class="warning">
            <td>快递员电话：</td>
            <td>
                <c:choose>
                    <c:when test="${empty orderDetail.courierPhone }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        ${orderDetail.courierPhone }
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="info">
            <td>快递员姓名：</td>
            <td>
                <c:choose>
                    <c:when test="${empty orderDetail.courierName }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        ${orderDetail.courierName }
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="warning">
            <td>快递员收件时间：</td>
            <td>
                <c:choose>
                    <c:when test="${empty orderDetail.recipientTime }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        <fmt:formatDate value="${orderDetail.recipientTime }" pattern="yyyy年MM月dd日  HH点mm分ss秒"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="info">
            <td>快递员送达时间：</td>
            <td>
                <c:choose>
                    <c:when test="${empty orderDetail.serviceTime }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        <fmt:formatDate value="${orderDetail.serviceTime }" pattern="yyyy年MM月dd日  HH点mm分ss秒"/>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</div>
<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/API.js"></script>
<script type="text/javascript">
    $(function () {
        API.checkLogin();
    })
</script>
</html>
