<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>管理员-派送-物资信息</title>
    <%@include file="/common/common-app-head.jsp" %>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/defaultTheme.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/myTheme.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/logistics.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.fixedheadertable.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/demo.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        .garden2 {
            color: #bf4c28;
            font-wei ${pageContext.request.contextPath} ght: bold;
            border-bottom: 2px solid #bf4c28;
        }

        .divGarden2 {
            color: #bf4c28;
            border: 2px solid #bf4c28;
        }
    </style>
</head>
<body class="bjs-gary" onload="inits()">
<!-- header -->
<div class="container">
    <header id="weixin_header">
        <script>
            API.settingHeard("物资信息");//修改“个人设置”这个参数为你本页面的功能名
        </script>
    </header>
    <div class="procedure">
        <div class="col-xs-4">
            <div class="divGarden1">1</div>
            <div class="garden1">门店信息</div>
        </div>
        <div class="col-xs-4">
            <div class="divGarden2">2</div>
            <div class="garden2">物资信息</div>
        </div>
        <div class="col-xs-4">
            <div class="divGarden3">3</div>
            <div class="garden3">快递员</div>
        </div>
    </div>
    <div class="tableDivTow">
        <div>
            <table class="fancyTable" id="myTable02" width="100%" cellpadding="0" cellspacing="0">
                <thead>
                <tr>
                    <th>物资名称</th>
                    <th>数量</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="mytbody">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="clear"></div>
    </div>

    <div class="labelInformation">物流信息</div>
    <div class="tableDivFillIn">
        <form id="form" action="${pageContext.servletContext.contextPath }/app/fillingSingle/toCourier.html"
              method="post">
            <input type="hidden" id="store" name="store" value="${store }"/>
            <table border="1" bordercolor="#ccc" width="100%">
                <tr>
                    <td width="30%">预计时长:</td>
                    <td>
                        <input type="number" min="0" id="predictTime" name="predictTime"
                               style="width:30%; height:27px; border-radius:3px; border:1px solid #CCC;padding-left: 5px;">
                        小时(选填)
                    </td>
                </tr>
                <tr>
                    <td>物资名称:</td>
                    <td>
                        <select id="material" name="material">
                            <c:forEach items="${material }" var="m">
                                <option value="${m.id }">${m.material_name }</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>物品数量:</td>
                    <td><input type="number" min="0" id="materialNumber" id="materialNumber"
                               style="width:30%; height:27px; border-radius:3px; border:1px solid #CCC; padding-left: 5px;"/>
                    </td>
                </tr>
            </table>
            <div align="center" style="font-size:12px;height:15px; color:red; text-shadow:5px 2px 6px #666;">
                <label id="mesg"></label>
            </div>
            <div class="addToDiv">
                <input type="button" id="ok" value="添加" class="btn btn-success btn-xs"
                       style="width:100px; margin-top:5px;">
                <input type="button" id="nextStep" value="下一步" class="btn btn-success btn-xs"
                       style="width:100px; margin-top:5px;">
            </div>
        </form>
    </div>
</div>
<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
<script>
    $(document).ready(function () {
        var divValue;
        var inputValue;
        $(".fal").click(function () {
            divValue = $(this).text();
            $(this).hide();
            $(this).next().show();
        })
        $(".adf").blur(function (a) {
            inputValue = $(this).val();
            if (divValue != inputValue) {
                divValue = inputValue;
                alert(divValue);
            }
            $(this).hide();
            $(this).prev().show();

        })
    })
    var div_h = window.screen.height - 360;
    var data = {"height": div_h}
    $("#myTable02").fixedHeaderTable(data);

    //验证
    function check() {
        var material = $("#material :selected").text();
        if (material == "") {
            $("#mesg").html("请选择物资");
            return false;
        }
        var materialNumber = $("#materialNumber").val();
        if (materialNumber == "") {
            $("#mesg").html("请填写数字");
            return false;
        } else {
            return true;
        }
    }

    // 当光标进入文本框,错误消息提示消失
    $("#materialNumber").focus(function () {
        $("#mesg").html("");
    });

    // 添加物资
    $("#ok").click(function () {
        $("#mytbody").html("");

        var num = Number($("#materialNumber").val());//物资数量
        var predictTime = Number($("#predictTime").val());//预计时长
        if (num <= 0) {
            $("#mesg").html("请输入合法的物资数量!");
            $("#mesg").fadeIn(500);
            $("#mesg").fadeOut(2000);
            return false;
        }
        if (predictTime < 0) {
            $("#mesg").html("请输入合法的预计送达时间!");
            $("#mesg").fadeIn(500);
            $("#mesg").fadeOut(2000);
            return false;
        }
        var store = $("#store").val();//门店信息
        var material = $("#material").val();//物资id
        var materialName = $("#material :selected").html();//物资名称
        var data = {
            "num": num,
            "material": material,
            "materialName": materialName,
            "store": store,
            "operation": "append",
            "predictTime": predictTime
        };
        if (check()) {
            $.post("${pageContext.request.contextPath}/app/fillingSingle/appendOrRemove.html", data).done(function (data) {
                var mytbody;
                for (var i = 0; i < data.length; i++) {
                    mytbody += "<tr>"
                        + "<td>" + data[i].materialName + "</td>"
                        + "<td>" + data[i].num + "</td>"
                        + "<td><a onclick='javascript:mydelete(" + data[i].material + ")'>删除</a></td>"
                        + "</tr>";
                }
                $("#mytbody").append(mytbody);
            });
        }
    })

    // 删除物资信息
    function mydelete(materialId) {
        $.post("${pageContext.request.contextPath}/app/fillingSingle/appendOrRemove.html", {
            "material": materialId,
            "operation": "remove"
        }).done(function (data) {
            $("#mytbody").html("");
            var mytbody;
            for (var i = 0; i < data.length; i++) {
                mytbody += "<tr>"
                    + "<td>" + data[i].materialName + "</td>"
                    + "<td>" + data[i].num + "</td>"
                    + "<td><a onclick='javascript:mydelete(" + data[i].material + ")'>删除</a></td>"
                    + "</tr>";
            }
            $("#mytbody").append(mytbody);
        });
    }

    // 当页面一刷新如果物资session有值拼接到页面上
    function inits() {
        API.checkLogin();
        $.post("${pageContext.request.contextPath}/app/fillingSingle/appendOrRemove.html", {"operation": "getList"}).done(function (data) {
            $("#mytbody").html("");
            if (data != null && data != "") {
                var mytbody;
                for (var i = 0; i < data.length; i++) {
                    mytbody += "<tr>"
                        + "<td>" + data[i].materialName + "</td>"
                        + "<td>" + data[i].num + "</td>"
                        + "<td><a onclick='javascript:mydelete(" + data[i].material + ")'>删除</a></td>"
                        + "</tr>";
                }
                $("#mytbody").append(mytbody);
            }
        });
    }

    // 下一步
    $("#nextStep").click(function () {
        $.post("${pageContext.request.contextPath}/app/fillingSingle/checkMaterial.html").done(function (data) {
            if (data.info <= 0) {
                $("#mesg").html("您还没有添加物资,请添加!!!");
                $("#mesg").fadeIn(500);
                $("#mesg").fadeOut(2000);
                return false;
            } else {
                $("#form").submit();
            }
        }).error(function (data) {
            $("#mesg").html("您还没有添加物资,请添加!!!");
            $("#mesg").fadeIn(500);
            $("#mesg").fadeOut(2000);
            return false;
        });
    });
</script>
</html>
