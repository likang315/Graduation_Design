<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>门店-需求上报统计-index</title>
    <%@include file="/common/common-app-head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/logistics.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script>
</head>
<style>
    .container {
        padding: 0;
    }

    .saefas {
        width: 100%;
        height: 50px;
        background: #FFF;
        text-align: center;
        font-size: 20px;
    }

    b {
        font-size: 35px;
        color: #F00;
    }

    .casd {
        width: 100%;
        height: 250px;
        margin: 15px 0;
    }

</style>
<body class="bjs-gary">
<!-- header -->
<header id="weixin_header">
    <script>
        API.settingHeard("需求上报统计");//修改“个人设置”这个参数为你本页面的功能名
    </script>
</header>
<div>
    <div class="order" style=" margin-top: 15px;">

        <input type="date" class="mydate" id="startTime" name="startTime" value="${startTime}" placeholder="请选择开始时间"/>
        <input type="date" class="mydate" id="endTime" name="endTime" value="${endTime}" placeholder="请选择结束时间"/>
        &nbsp;
        <button type="button" id="timeSearch" class="btn btn-primary">确认</button>
    </div>
    <div id="timeError" style="text-align: center; color: red; display: none;">开始时间不能超过结束时间</div>
    <div class="casd" id="seven"></div>
    <!--  <input type="button" value="查看列表" id="chakan" class="btn btn-success" />  -->
</div>


<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
</html>
<script type="text/javascript">
    var user = API.getUserInfo();
    var accountName = user.accountName;
    var channel_code = user.code;
    $(function () {
        API.checkLogin();
    })

    // 开始时间和结束时间验证
    function myseach(startTime, endTime) {
        var time = startTime.replace(/\-/g, "");
        var time1 = endTime.replace(/\-/g, "");
        if (time <= time1) {
            return true;
        } else {
            return false;
        }
    }

    // 时间查询
    $("#timeSearch").click(function () {
        var startTime = $('#startTime').val();
        var endTime = $("#endTime").val();
        if (myseach(startTime, endTime)) {
            location.href = "${pageContext.request.contextPath}/app/storeSupplies/toStoreTotal.html?channel_code=" + channel_code + "&startTime=" + startTime + "&endTime=" + endTime;
        } else {
            $("#timeError").fadeIn(500);//页面刷新后多长时间显示
            $("#timeError").fadeOut(2000);//显示后多长时间消失
            return false;
        }
    });


    var seven = echarts.init(document.getElementById('seven'));
    // 指定图表的配置项和数据
    var option = {
        backgroundColor: '#ffffff',
        title: {
            text: '需求上报',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['未审核', '审核通过', '审核不通过']
        },
        series: [
            {
                name: '类别',
                type: 'pie',
                radius: '54%',
                center: ['50%', '65%'],
                data: [
                    <c:forEach items="${map}" var="c">
                    {value: '${c.num}', name: '${c.examines}'},
                    </c:forEach>

                    {value: '${shang}', name: '暂无需求'}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    seven.setOption(option);
    window.onresize = seven.resize;
</script>
