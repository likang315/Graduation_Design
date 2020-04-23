<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>门店-报需-需求上报页面</title>
    <%@include file="/common/common-app-head.jsp" %>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/defaultTheme.css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath }/css/myTheme.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/logistics.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.fixedheadertable.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/demo.js"></script>

    <style type="text/css">
        .products {
            width: 50%;
            float: left;
            margin-top: 10px;
        }

        .product {
            border: 1px solid #fa7729;
            width: 96%;
            height: 35px;
            margin-left: 2%;
            line-height: 35px;
            text-align: center;
            border-radius: 5px;
            color: #fa7729;
        }

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
<body class="bjs-gary">
<div class="container">
    <header id="weixin_header">
        <script>
            API.settingHeard("需求上报");//修改“个人设置”这个参数为你本页面的功能名
        </script>
    </header>


    <!-- 蒙版 -->
    <div id="bigblack2"
         style="z-index:9999;width:100%;background:#000;height:100%;position: fixed;top:0px;left:0px;opacity:0.7;display: none;"></div>
    <div id="yesOrNo" style=":fixed; width:100%; top:150px; z-index: 9999; display: none;">
        <div style="width:80%; margin-left:10%; background:#FFF; padding:20px; border-radius:5px;">
            <div>确认上报吗?</div>
            <div style="text-align:right; margin-top:20px;">
                <a href="javascript:myConfirm();">确认</a>&nbsp;&nbsp;&nbsp;<a href="javascript:myClose();">关闭</a>
            </div>
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
                <tbody id="mytbody"></tbody>
            </table>
        </div>
        <div class="clear"></div>
    </div>
    <div class="labelInformation">现有物资</div>
    <div class="tableDivFillIn" style="height: auto;">
        <form id="form" action="${pageContext.servletContext.contextPath }/app/storeSupplies/addmaterial.html"
              method="post">
            <input type="hidden" id="channel_code" name="channel_code" value=""/><!-- 上报人渠道编码 -->
            <input type="hidden" id="store_name" name="store_name" value=""/><!-- 上报人渠道名称 -->
            <input type="hidden" id="store_shopowner_phone" name="store_shopowner_phone" value=""/><!-- 上报人电话 -->
            <input type="hidden" id="store_shopowner_name" name="store_shopowner_name" value=""/><!-- 上报人姓名 -->
            <input type="hidden" id="expanding_demand" name="expanding_demand" value=""/><!-- 扩展需求 -->
            <table border="1" bordercolor="#ccc" width="100%">
                <tr>
                    <td>物资名称:</td>
                    <td>
                        <select id="material" name="material">
                            <c:forEach items="${materials }" var="m">
                                <option value="${m.id }">${m.material_name }</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>物品数量:</td>
                    <td><input type="number" id="materialNumber"
                               style="width:30%; height:27px; border-radius:3px; border:1px solid #CCC;"/>个
                    </td>
                </tr>
            </table>
            <div align="center" style="font-size:12px;height:15px; color:red; text-shadow:5px 2px 6px #666;">
                <label id="mesg"></label>
            </div>
            <div class="addToDiv">
                <input type="button" id="addMaterial" value="添加" class="btn btn-success btn-xs"
                       style="width:100px; margin-top:5px;">
            </div>
        </form>
    </div>
    <div class="labelInformation">扩展物资(选填)</div>
    <div class="tableDivFillIn" style="height: auto;">
        <table border="1" bordercolor="#ccc" width="100%">
            <tr>
                <td>
                    <textarea id="demand" style="width: 100%; height: 100px; resize:none;"
                              placeholder="在这里阐述您的需求"></textarea>
                </td>
            </tr>
        </table>
        <div class="addToDiv">
            <input type="button" id="mysubmit" value="提交" class="btn btn-success btn-xs"
                   style="width:100px; margin-top:5px;">
        </div>
    </div>
</div>

<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
<script>
    var user = API.getUserInfo();
    $(document).ready(function () {
        API.checkLogin();
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
    var div_h = window.screen.height - 460;
    var data = {"height": div_h}
    $("#myTable02").fixedHeaderTable(data);

    $("#materialNumber").focus(function () {
        $("#mesg").html("");
        $("#materialNumber").css("border", "1px solid #ccc")
    });

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
            $("#materialNumber").css("border", "1px solid red")
            return false;
        } else {
            return true;
        }
    }

    //添加物资
    $("#addMaterial").click(function () {
        if (check()) {
            var material = $("#material").val();//物资id
            var materialName = $("#material :selected").html();//物资名称
            var num = $("#materialNumber").val();//物资数量
            var data = {"materialId": material, "materialName": materialName, "num": num, "operation": "append"};
            appendView(data);
        }
    });

    //删除物资信息
    function mydelete(materialId) {
        var data = {"materialId": materialId, "operation": "remove"};
        appendView(data);
    }

    //添加或者删除物资
    function appendView(data) {
        $.post("${pageContext.request.contextPath}/app/storeSupplies/appendOrRemove.html", data).done(function (data) {
            $("#mytbody").html("");
            var mytbody = "";
            for (var i = 0; i < data.length; i++) {
                mytbody += "<tr>"
                    + "<td>" + data[i].materialName + "</td>"
                    + "<td>" + data[i].num + "</td>"
                    + "<td><a onclick='javascript:mydelete(" + data[i].materialId + ")'>删除</a></td>"
                    + "</tr>";
            }
            $("#mytbody").append(mytbody);
        });
    }

    //提交数据
    $("#mysubmit").click(function () {
        $.post("${pageContext.request.contextPath}/app/storeSupplies/checkMaterial.html").done(function (data) {
            if (data.info <= 0) {
                $("#mesg").html("您还没有添加物资,请添加!!!");
                $("#mesg").fadeIn(500);
                $("#mesg").fadeOut(2000);
                return false;
            } else {
            //  $("#bigblack2").show();
                $("#yesOrNo").show();
            }
        }).error(function (data) {
            alert("您还没有添加物资,请添加!!!");
            return false;
        });
    });

    // 提交需求上报数据
    function myConfirm() {
        $("#channel_code").val(user.code);
        $("#store_shopowner_phone").val(user.accountName);
        $("#store_shopowner_name").val(user.real_name);
        $("#store_name").val(user.section_name);
        $("#expanding_demand").val($("#demand").val());
        $("#form").submit();
    }

    // 关闭
    function myClose() {
        $("#bigblack2").hide();
        $("#yesOrNo").hide();
    }
</script>
</html>
