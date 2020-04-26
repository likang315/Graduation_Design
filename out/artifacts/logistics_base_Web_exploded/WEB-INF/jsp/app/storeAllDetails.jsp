<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>门店-需求上报列表-需求详情</title>
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
        API.settingHeard("上报详情");//修改“个人设置”这个参数为你本页面的功能名
    </script>
</header>
<div class="" style="margin-top: 20px;">
    <div class="models">
        <div class="bian"></div>
        <div class="please">上报详情:</div>
    </div>
    <table class="table table-bordered" style="margin-top: 10px;font-size: 14px;">
        <tr class="info">
            <td>门店名称:</td>
            <td>${maps.store_name }</td>
        </tr>
        <tr class="warning">
            <td>门店渠道编码:</td>
            <td>${maps.channel_code }</td>
        </tr>
        <tr class="info">
            <td>上报时间：</td>
            <td>
                <c:choose>
                    <c:when test="${not empty maps.report_time }">
                        <fmt:formatDate value="${maps.report_time }" pattern="yyyy年MM月dd日  HH点mm分ss秒"/>
                    </c:when>
                    <c:otherwise>
                        未发货
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="warning">
            <td>上报人姓名：</td>
            <td>${maps.store_shopowner_name }</td>
        </tr>
        <tr class="info">
            <td>上报人电话：</td>
            <td>${maps.store_shopowner_phone }</td>
        </tr>
        <tr class="warning">
            <td>物资名称及数量：</td>
            <td>${maps.materialNameAndNumber }</td>
        </tr>
        <tr class="info">
            <td>扩展需求：</td>
            <td>
                <c:choose>
                    <c:when test="${empty maps.expanding_demand }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        ${maps.expanding_demand }
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr class="warning">
            <td>审核时间：</td>
            <td>
                <c:choose>
                    <c:when test="${not empty maps.examine_time }">
                        <fmt:formatDate value="${maps.examine_time }" pattern="yyyy年MM月dd日  HH点mm分ss秒"/>
                    </c:when>
                    <c:when test="${empty maps.examine_time }">
                        暂无记录
                    </c:when>
                </c:choose>
            </td>
        </tr>
        <tr class="info">
            <td>审核状态：</td>
            <td>
                <c:if test="${maps.examine ==0 }">
                    未审核
                </c:if>
                <c:if test="${maps.examine == 1  }">
                    审核通过
                </c:if>
                <c:if test="${maps.examine >= 2 }">
                    审核不通过
                </c:if>
            </td>
        </tr>
        <tr class="warning">
            <td>驳回原因：</td>
            <td>
                <c:choose>
                    <c:when test="${empty maps.examine_reason }">
                        暂无记录
                    </c:when>
                    <c:otherwise>
                        ${maps.examine_reason }
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
