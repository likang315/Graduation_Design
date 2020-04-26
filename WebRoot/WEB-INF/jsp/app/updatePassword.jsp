<%@page import="com.sun.jersey.server.impl.container.servlet.Include" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@include file="/common/common-app-head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>个人设置-修改密码</title>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/cover.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath }/css/new.css" rel="stylesheet"/>
</head>
<body onload="beiLogin()" id="bodyLogin" class="bjs-blue">
<div class="container ">
    <div class="fpsw_page ">
        <header>
            <a href="javascript:history.go(-1);" class="pull-left go_back"><span
                    class="glyphicon glyphicon-menu-left"></span>返回</a>
            <h4 class="text-center">修改密码</h4>
        </header>
        <div class="p15">
            <img src="${pageContext.request.contextPath }/images/china_mobile.png" class="w200"/>
        </div>
        <section class="psw_box">
            <div>
                <div class="form-group">
                    <input type="password" id="old" class="form-control" placeholder="请输入旧密码"/>
                </div>
                <div class="form-group">
                    <input type="password" id="password1" class="form-control" placeholder="请输入新密码"/>
                </div>
                <div class="form-group">
                    <input type="password" id="password2" class="form-control" placeholder="请再次输入新密码"/>
                </div>
                <div class="form-group">
                    <button id="editpassword" class="btn  btn-block btn-lg btn_determine">修改</button>
                </div>
                <div style="color:white;">
                    <div onclick="javascript:history.go(-1);" style="width:25%;margin-left:70%">取消修改</div>
                </div>
            </div>
        </section>
    </div>
</div>
<div id="MessageMask"
     style="display:none;width:100%;height:100%;background-color:#ccc;position:fixed;top:0;left:0;opacity:0.4;z-index:100">
    <div id="messageImg"
         style="display:none;width:80px;height:80px;position:fixed;top:0px;left:0px;right:0px;bottom:0px;margin:auto;">
        <img src="${pageContext.request.contextPath }/images/ajax-loader.gif"/></div>
</div>
<div id="promptMessage"
     style="z-index:150;display:none;width:150px;height:100px;border:1px solid #ccc;position:fixed;top:40%;left:50%;margin-left:-75px;border-radius:5px;">
    <div style="height:30px;text-align:center;line-height:30px;font-size:20px;color:white;background-color:#7CC8E8;border-top-left-radius:5px;border-top-right-radius:5px;">
        提示信息
    </div>
    <div id="promptMessage1"
         style="color:rgb(204, 2, 204);text-align:center;width:100%;height:70px;background-color:white;border-bottom-left-radius:5px;border-bottom-right-radius:5px;overflow:scroll;word-wrap:break-word;word-break:break-all;"></div>
</div>
</body>
<script>
    function beiLogin() {
        var a = window.screen.height - 64;
        $("#bodyLogin").css("height", a);
    }

    $(window).resize(function () {
        var a = window.screen.height - 64;
        $("#bodyLogin").css("height", a);
    });


    var flag = false;
    $("#editpassword").click(function () {
        var old = $("#old").val();
        var password1 = $("#password1").val();
        var password2 = $("#password2").val();
        if (!old) {
            $("#old").css({'border-color': 'red'});
            return;
        }
        if (!password1 && !password2) {
            $("#password1").css({'border-color': 'red'});
            $("#password2").css({'border-color': 'red'});
            return;
        }
        if (password1 != password2) {
            $("#password1").css({'border-color': 'red'});
            $("#password2").css({'border-color': 'red'});
            return;
        }
        var user = API.getStore("userinfo");
        var info = JSON.parse(user);
        var username = info.accountName;
        var password = info.password;
        if (username && password) {
            var login = {
                username: username,
                password: old,
                password1: password1,
                password2: password2
            }
            API.ajax('/app/user/changepassword', login).success(function (data) {
                if (data.state == 'ok') {
                    API.setStore("userinfo", JSON.stringify(data.user));
                    alert("修改成功!");
                    location.href = '${pageContext.request.contextPath }/app/setup.html';
                } else {
                    alert('修改失败');
                }
            }).error(function () {
                alert("修改失败");
            }).exception(function () {
            });
        }
    });

    $(function () {
        $("#code").blur(function () {
            API.ajax("wx/user/testCode", {
                "tellPhone": $("#tellPhone").val(),
                "code": $("#code").val()
            }).success(function (data) {

            }).error(function (data) {
                tishiMessage(data.info)
            }).exception(function () {
            })
        })

        $("#promptMessage").click(function () {
            $("#MessageMask").hide();
            $("#promptMessage").hide();
        })
    })

    function getById(e) {
        return document.getElementById(e);
    }

    //隐藏提示信息
    function yincangInfo() {
        $("#MessageMask").hide();
        $("#promptMessage").hide();
    }

    //提示信息
    function tishiMessage(info) {
        getById("MessageMask").style.display = "block";
        getById("promptMessage").style.display = "block";
        getById("promptMessage1").innerHTML = info;
    }
</script>
</html>