<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>管理员-统计-门店配送统计</title>
    <meta name="description" content=""/>
    <meta name="keywords" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/cover.css" rel="stylesheet"/>
    <script language="javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/biaogeguding.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        body {
            font-size: 12px;
        }

        .maxDiv td {
            text-align: center;
            border: 1px solid #CCC;
        }

        .maxDiv {
            border-bottom: 1px solid #ccc;
        }

        .leftMaxDiv {
            width: 50%;
            float: left;
        }

        .leftTopDiv {
            width: 100%;
            font-weight: bold;
        }

        .leftTopTable {
            width: 100%;
            height: 60px;
            background: #8ca9cf;
            text-shadow: 1px 1px 1px #e8ebee;
        }

        .leftTopTable tr td:first-child {
            width: 40%;
            word-wrap: break-word;
        }

        .leftTopTable tr td:nth-child(2) {
            width: 60%;
        }

        .leftDiv {
            width: 100%;
            overflow-y: hidden;
        }

        .leftTable {
            width: 100%;
            table-layout: fixed;
        }

        .leftTable tr td:first-child {
            width: 40%;
            word-wrap: break-word;
        }

        .leftTable tr td:nth-child(2) {
            width: 60%;
        }

        .rightMaxDiv {
            width: 50%;
            float: left;
        }

        .rightTopDiv {
            width: 100%;
            overflow-x: hidden;
            font-weight: bold;
            background: #8ca9cf;
            text-shadow: 1px 1px 1px #e8ebee;
        }

        .rightTopTable {
            height: 60px;
            min-width: 600px;
        }


        .rightDiv {
            width: 100%;
            overflow: auto;
        }

        .rightTable {
            min-width: 600px;
        }

        .rightTopTable td {
        }

        .rightTable td {
        }
    </style>
</head>
<body class="bjs-gary">
<div>
    <div class="policy_page new_broad_page text-center">
        <header>
            <a href="javascript:history.go(-1);">
                <div class="pull-left juleft">
                    <span class="glyphicon glyphicon-menu-left"></span>返回
                </div>
            </a>
            <h4 class="pull-left">门店配送统计</h4>

        </header>
    </div>
</div>

<div style="">
    <div style="margin:10px">
        <div style="text-align:center;">
            <form id="myform" action="${pageContext.request.contextPath}/app/fillingSingle/getMarketingOrderTotal.html"
                  method="post">
                <table width="100%" border="0" class="mytable">
                    <tr>
                        <td><label>开始时间：</label></td>
                        <td><input type="date" class="mydate" id="startTime" name="startTime" value="${startTime}"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>结束时间：</label></td>
                        <td><input type="date" class="mydate" id="endTime" name="endTime" value="${endTime}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="button" id="timeSearch" class="btn btn-warning" style="width: 35%;">确认
                            </button>
                        </td>
                    </tr>
                </table>
                <div id="timeError" style="text-align: center; color: red; display: none;">开始时间不能超过结束时间</div>
            </form>
        </div>
    </div>

    <div class="maxDiv">
        <div class="leftMaxDiv">
            <div class="leftTopDiv">
                <table border="0" cellpadding="0" cellspacing="0" class="leftTopTable">
                    <tr>
                        <td>渠道编码</td>
                        <td>渠道名称</td>
                    </tr>
                </table>
            </div>
            <div class="leftDiv" id="leftDiv">
                <table border="0" cellpadding="0" cellspacing="0" class="leftTable">
                    <c:forEach items="${ls }" var="l">
                        <tr>
                            <td>${l.channel_code }</td>
                            <td>${l.store }</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <div class="rightMaxDiv">
            <div class="rightTopDiv" id="rightTopDiv">
                <table border="0" cellpadding="0" cellspacing="0" class="rightTopTable">
                    <tr>
                        <td rowspan="2" width="60">配货次数</td>
                        <td rowspan="2" width="60">签收次数</td>
                        <td colspan="${size}">物资信息</td>
                    </tr>
                    <tr id="material_names">
                        <c:forEach items="${ls.get(0).materialAndNumLs }" var="l">
                            <td>${l.material_name } </td>
                        </c:forEach>

                    </tr>
                </table>
            </div>
            <div class="rightDiv" id="rightDiv">
                <table border="0" cellpadding="0" cellspacing="0" class="rightTable">
                    <c:forEach items="${ls }" var="l">
                        <tr id="tdNameNum">
                            <td class="tdphcs">${l.OrderAll }</td>
                            <td class="tdphcs">${l.doneOrder }</td>
                            <c:forEach items="${l.materialAndNumLs }" var="s">
                                <c:if test="${s.materialNameNum == null }">
                                    <td>0</td>
                                </c:if>
                                <c:if test="${s.materialNameNum != null }">
                                    <td>${s.materialNameNum }</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
</html>
<script language="javascript" src="${pageContext.request.contextPath}/js/API.js"></script>

<script>
    $(function () {
        var height = window.screen.height - 300;
        $(".maxDiv").css('height', height + 60);
        $(".leftDiv").css('max-height', height);
        $(".rightDiv").css('max-height', height);
    })

    //开始时间和结束时间验证
    function myseach(startTime, endTime) {
        var time = startTime.replace(/\-/g, "");
        var time1 = endTime.replace(/\-/g, "");
        if (time <= time1) {
            return true;
        } else {
            return false;
        }
    }

    //时间查询
    $("#timeSearch").click(function () {
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        if (myseach(startTime, endTime)) {
            $("#myform").submit();
        } else {
            $("#timeError").fadeIn(500);
            $("#timeError").fadeOut(2000);
            return false;
        }
    });


</script>