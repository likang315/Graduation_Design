<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>快递员-订单列表index</title>
    <%@include file="/common/common-app-head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/logistics.css">
</head>
<style>

    .jiazai {
        height: 40px;
        line-height: 40px;
        text-align: center;
        background: green;
        color: #fff;
        margin-top: 10px;
        margin-bottom: 5px;
        width: 95%;
        margin-left: 2.5%
    }
</style>
<body class="bjs-gary">
<!-- header -->
<header id="weixin_header">
    <script>
        API.settingHeard("订单列表");
    </script>
</header>
<div class="btDiv">按状态和编号查询</div>
<div class="order">
    <select id="xuanze" class="selectpicker">
        <option value="0"> 请选择订单状态</option>
        <option value="2" <c:if test="${state==2}">selected </c:if>>已接单</option>
        <option value="3" <c:if test="${state==3}">selected </c:if>>派送中</option>
        <option value="4" <c:if test="${state==4}">selected </c:if>>已派送</option>
    </select>
    &nbsp;
    <input type="text" id="orderNumber" class="oneInput" placeholder="请输入订单编号" value="${searchMsg}">
</div>
<div class="btDiv">按日期查询</div>
<div style="text-align: center;" class="order">
    <input type="date" class="mydate" id="startTime" name="startTime" value="${startTime}" placeholder="请选择开始时间"/>
    <input type="date" class="mydate" id="endTime" name="endTime" value="${endTime}" placeholder="请选择结束时间"/>
    <button type="button" id="timeSearch" class="btn btn-primary">确认</button>
</div>
<div id="timeError" style="text-align: center; color: red; display: none;">开始时间不能超过结束时间</div>

<p style="color: red; font-size: 12px; padding: 5px 10px;">备注： 点击订单查看派送详情</p>
<div id="myDiv1"></div>
<div class="jiazai" id="dianjigengduo"></div>

<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
</html>
<script type="text/javascript">
    $(function () {
        API.checkLogin();
        var parameter = {"accountName": user.accountName, "jiazai": jiazai};
        appendTable(parameter);

    });
    var user = API.getUserInfo();
    var accountName = user.accountName;
    var jiazai = 0;

    function myseach(startTime, endTime) {
        var time = startTime.replace(/\-/g, "");
        var time1 = endTime.replace(/\-/g, "");
        if (time <= time1) {
            return true;
        } else {
            return false;
        }
    }

    $("#xuanze").change(function () {
        var searchMsg = $("#orderNumber").val();
        var state = $("#xuanze").val();
        var startTime = $('#startTime').val();
        var endTime = $("#endTime").val();
        jiazai = 0;
        if (xuanze != "0") {
            $("#myDiv1").html("");
        }
        if (myseach(startTime, endTime)) {
            var parameter = {
                "accountName": accountName,
                "searchMsg": searchMsg,
                "state": state,
                "startTime": startTime,
                "endTime": endTime,
                "jiazai": jiazai
            };
            appendTable(parameter);
        } else {
            $("#timeError").fadeIn(500);//页面刷新后多长时间显示
            $("#timeError").fadeOut(2000);//显示后多长时间消失
            return false;
        }
    });


    //订单编号搜索
    $("#orderNumber").keyup(function () {
        var searchMsg = $(this).val();
        var startTime = $('#startTime').val();
        var state = $("#xuanze").val();
        var endTime = $("#endTime").val();
        $("#myDiv1").html("");
        if (myseach(startTime, endTime)) {
            var parameter = {
                "accountName": accountName,
                "searchMsg": searchMsg,
                "state": state,
                "startTime": startTime,
                "endTime": endTime,
                "jiazai": jiazai
            };
            appendTable(parameter);
        } else {
            $("#timeError").fadeIn(500);//页面刷新后多长时间显示
            $("#timeError").fadeOut(2000);//显示后多长时间消失
            return false;
        }

    });
    //时间查询订单
    $("#timeSearch").click(function () {
        var searchMsg = $("#orderNumber").val();
        var startTime = $('#startTime').val();
        var state = $("#xuanze").val();
        var endTime = $("#endTime").val();
        $("#dianjigengduo").hide();
        $("#myDiv1").html("");
        var jiazai = 0;
        if (myseach(startTime, endTime)) {
            var parameter = {
                "accountName": accountName,
                "searchMsg": searchMsg,
                "state": state,
                "startTime": startTime,
                "endTime": endTime,
                "jiazai": jiazai
            };
            appendTable(parameter);
        } else {
            $("#timeError").fadeIn(500);//页面刷新后多长时间显示
            $("#timeError").fadeOut(2000);//显示后多长时间消失
            return false;
        }
    });

    // 加载更多
    $("#dianjigengduo").click(function () {
        var searchMsg = $("#orderNumber").val();
        var startTime = $('#startTime').val();
        var state = $("#xuanze").val();
        var endTime = $("#endTime").val();
        $("#dianjigengduo").html("正在加载...");
        jiazai += 5;
        var parameter = {
            "accountName": accountName,
            "searchMsg": searchMsg,
            "state": state,
            "startTime": startTime,
            "endTime": endTime,
            "jiazai": jiazai
        };
        appendTable(parameter);
    })

    // 显示数据
    function appendTable(parameter) {
        $.post("${pageContext.request.contextPath}/app/coordinate/CourierAllList.html", parameter).done(function (data) {
            $("#dianjigengduo").hide();
            if (data.map.length > 0) {
                var myDiv1 = "";
                var loading = "";
                console.log(data.count, data.jiazai);
                if (data.count <= (data.jiazai + 5) || data.map.length < 5) {
                    $("#dianjigengduo").hide();

                } else {
                    $("#dianjigengduo").show();
                    $("#dianjigengduo").html("点击加载更多 ");
                }
                for (var i = 0; i < data.map.length; i++) {
                    myDiv1 += "<div id='myDiv' class='myDivB' onclick = 'tiaozhuan(\"" + data.map[i].id + "\",\"" + data.map[i].channel_code + "\")'><table>"
                        + "<tr>"
                        + "<td><label>订单编号:</label></td>"
                        + "<td colspan = '2'>" + data.map[i].id + "</td>"
                        + "<td></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>物资名称:</td>"
                        + "<td><label>" + data.map[i].materialContent.substring(0, 1) + "...</label></td>"
                        + "<td>物资数量:</td>"
                        + "<td><label>" + data.map[i].materialNumber.substring(0, 2) + "...</label></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td><label>收货人姓名:</label></td>"
                        + "<td>" + data.map[i].consignee + "</td>"
                        + "<td><label>收货人电话:</label></td>"
                        + "<td>" + data.map[i].phone + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td><label>订单状态:</label></td>"
                        + "<td colspan = '3' style = 'text-align: left;'><font color='#0C9719'>" + judgeState(data.map[i].state) + "</font></td>"
                        + "</tr>"
                        + "</table></div>";
                }


                $("#myDiv1").append(myDiv1);

            } else {
                $("#myDiv1").html("暂无记录");
            }
        }).error(function (data) {
            $("#myDiv1").html("暂无记录");
        });

    }

    // 查看详情
    function tiaozhuan(id, channel_code) {
        window.location.href = "${pageContext.request.contextPath}/app/coordinate/storeDetails.html?channel_code=" + channel_code + "&&courierPhone=" + user.accountName + "&&id=" + id;
    }

    function judgeState(state) {
        if (state == 0 || state == 1 || state == 2) {
            return "已接单";
        } else if (state == 3 || state == 4) {
            return "派送中";
        } else if (state == 5 || state == 6) {
            return "派送成功";
        }
    }

</script>
