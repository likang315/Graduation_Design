<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE >
<html>
<head>
    <title>管理员-派送index</title>
    <%@include file="/common/common-app-head.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/logistics.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.2.1.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        .garden1 {
            color: #bf4c28;
            font-weight: bold;
            border-bottom: 2px solid #bf4c28;
        }

        .divGarden1 {
            color: #bf4c28;
            border: 2px solid #bf4c28;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            API.checkLogin();
            // 获取品牌信息
            $.post("${pageContext.request.contextPath}/app/fillingSingle/getVendor.html", function (data1) {
                for (var i = 0; i < data1.vendor.length; i++) {
                    str = "<option value='" + data1.vendor[i].id + "'>" + data1.vendor[i].vendor_name + "</option>";
                    $("#vendor").append(str);
                }

            })

            // 无选择时，获取所有门店信息
            $.post("${pageContext.request.contextPath}/app/fillingSingle/getStoreAll.html", function (data1) {
                if (data1.store.length > 0) {
                    $("#info").html("");
                    for (var i = 0; i < data1.store.length; i++) {
                        str = "<div class='store' name='material' data='" + data1.store[i].channel_code + "' onclick='dianji(\"" + data1.store[i].store_name + "\",this)'>" + data1.store[i].store_name + "</div>";
                        $("#searchContentP").append(str);
                    }
                } else {
                    $("#info").html("暂无信息");
                }
            })

            // 门店品牌查询
            $("#vendor").change(function () {
                $("#searchContentP").html("");
                $("#info").html("");
                var vendor_id = $("#vendor :selected").val();//品牌
                var type = $("#type").val(); //门店类别(直营/现金)
                $.post("${pageContext.request.contextPath}/app/fillingSingle/getStoreById.html", {
                    "vendor_id": vendor_id,
                    "type": type
                }, function (data1) {
                    if (data1.store.length > 0) {
                        $("#info").html("");
                        for (var i = 0; i < data1.store.length; i++) {
                            str = "<div  class='store' name='material' data='" + data1.store[i].channel_code + "' onclick='dianji(\"" + data1.store[i].store_name + "\",this)'>" + data1.store[i].store_name + "</div>";
                            $("#searchContentP").append(str);
                        }
                    } else {
                        $("#info").html("暂无信息");
                    }
                })
            });

            // 门店类别查询
            $("#type").change(function () {
                $("#searchContentP").html("");
                $("#info").html("");
                var vendor_id = $("#vendor :selected").val();//品牌
                var type = $("#type").val();//门店类别(直营/现金)
                $.post("${pageContext.request.contextPath}/app/fillingSingle/getStoreById.html", {
                    "vendor_id": vendor_id,
                    "type": type
                }, function (data1) {
                    if (data1.store.length > 0) {
                        $("#info").html("");
                        for (var i = 0; i < data1.store.length; i++) {
                            /*修改时间：2017年11月22日10:54*/
                            /*修改原因：没有给data赋值，门店类别改变后无法将门店传递到下一个页面去*/
                            /*修改前：str="<div  class='store' name='material' onclick='dianji(\""+data1.store[i].store_name+"\",this)'>"+data1.store[i].store_name+"</div>";*/
                            str = "<div  class='store' name='material' data='" + data1.store[i].channel_code + "' onclick='dianji(\"" + data1.store[i].store_name + "\",this)'>" + data1.store[i].store_name + "</div>";
                            $("#searchContentP").append(str);
                        }
                    } else {
                        $("#info").html("暂无信息");
                    }
                })
            });

            // 关键字模糊查询
            $("#mohuchaxun").keyup(function () {
                $("#searchContentP").html("");
                var vendor = $("#vendor :selected").val();//品牌
                var mohuchaxun = $("#mohuchaxun").val();//关键字
                var type = $("#type").val();//门店类别(直营/现金)
                $.post("${pageContext.request.contextPath}/app/fillingSingle/getStoreByMohuchaxun.html", {
                    "vendor": vendor,
                    "mohuchaxun": mohuchaxun,
                    "type": type
                }, function (data1) {
                    /* var data=JSON.parse(data1); */
                    if (data1.mohu.length > 0) {
                        $("#info").html("");
                        for (var i = 0; i < data1.mohu.length; i++) {
                            str = "<div class='store' name='material' data='" + data1.mohu[i].channel_code + "' onclick='dianji(\"" + data1.mohu[i].store_name + "\",this)'>" + data1.mohu[i].store_name + "</div>";
                            $("#searchContentP").append(str);
                        }
                    } else {
                        $("#info").html("暂无信息");
                    }
                })
            })
        })

        // 选择门店
        function dianji(e, a) {
            if ($(a).css("color") == "rgb(51, 51, 51)") {
                $(a).css("background", "#bf4c28");
                $(a).css("color", "#fff");
            } else {
                $(a).css("background", "#fff");
                $(a).css("color", "#333");
            }

        }

        $(document).ready(function () {
            // 下一步
            $("#yes").click(function (e) {
                var name = "";//选中的门店
                var a = $(".store");
                for (var i = 0; i < a.length; i++) {
                    if (a[i].style.color == "rgb(255, 255, 255)") {
                        console.log(a[i].getAttribute('data'));
                        name += a[i].getAttribute('data') + ",";
                    }
                }
                if (name == "") {
                    $("#mesg").html("请选择需要派送的门店!!!");
                    $("#mesg").fadeIn(500);
                    $("#mesg").fadeOut(2000);
                    return false;
                } else {
                    $("#store").val(name);//选中的门店
                    $("#form").submit();
                }
            })
        })

    </script>
</head>
<body class="bgd">
<header id="weixin_header">
    <script>
        API.settingHeard("门店信息");//修改“个人设置”这个参数为你本页面的功能名
    </script>
</header>
<div>
    <div class="container">
        <div class="procedure">
            <div class="col-xs-4">
                <div class="divGarden1">1</div>
                <a>
                    <div class="garden1">门店信息</div>
                </a>
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

        <form id="form" action="${pageContext.servletContext.contextPath }/app/fillingSingle/toMaterialInformation.html"
              method="post">
            <div class="selectDiv">
                <select id="vendor" name="vendor">
                    <option value="pleaseSelectVendor">请选择品牌</option>
                </select>
                &nbsp;
                <select id="type" name="type">
                    <option value="pleaseSelectType" selected="selected">请选择类型</option>
                    <option value="0">直营</option>
                    <option value="1">现金</option>
                </select>
            </div>
            <div class="inputTextDiv">
                <input type="text" id="mohuchaxun" placeholder="请输入要搜索的关键字">
            </div>
            <div class="searchContentP" id="searchContentP"></div>
            <div id="info" align="center" style="color:red;"></div>
            <div align="center" style="font-size:12px;height:15px; color:red; text-shadow:5px 2px 6px #666;">
                <label id="mesg"></label>
            </div>
            <div id="aaa" align="center" style="color:red;"></div>
            <input type="text" id="store" name="store" style="display:none;"/>
            <div align="center" style="padding-top: 5px;">
                <input type="button" value="下一步" id="yes" class="btn btn-success btn-sm" style="width:80px;">
            </div>
        </form>
    </div>
</div>
<!-- 个人中心	底部导航 -->
<%@include file="/common/leftBottom.jsp" %>
</body>
</html>
<script>
    $(document).ready(function (e) {
        $(function () {
            var div_h = window.screen.height - 300;
            $("#searchContentP").css("height", div_h + "px");
        })
    });
</script>
