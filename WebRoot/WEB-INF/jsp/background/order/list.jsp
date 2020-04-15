<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@include file="/common/common-css.jsp" %>
<%@include file="/common/common-js.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>物流订单管理-index</title>
    <link href="${pageContext.servletContext.contextPath }/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/API.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.2.1.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.twbsPagination.min.js"></script>
    <style type="text/css">
        .myTable img {
            width: 45%;
            height: 100px;
        }

        .myTable .A {
            width: 45%;
            height: 30px;
            text-align: center;
            line-height: 30px;
            float: left;
        }

        .table-striped tr td:first-child {
            text-align: right;
        }
    </style>
</head>

<body>


<div style="background:#000;position:fixed;top:0;left:0; width:100%; height:100%; opacity:0.2; z-index:9999;display:none; "
     id="modalmb"></div>
<div style="z-index:9999;position:fixed;top:100px; width:100%; display:none;" id="modalnr">
    <div class="modal-dialog" style=" margin:auto">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">

                </h4>
            </div>
            <div class="modal-body" id="hintContext">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="closeButton">关闭
                </button>
            </div>
        </div>
    </div>
</div>


<!-- 待完善信息列表-->
<div style="background:#000;position:fixed;top:0;left:0; width:100%; height:100%; opacity:0.2; z-index:9999;display:none; "
     id="modalmb2"></div>
<div style="z-index:9999;position:fixed;top:100px; width:100%; display:none;" id="modalnr2">
    <div class="modal-dialog" style=" margin:auto">
        <form id="sendForm" action="${pageContext.request.contextPath}/background/order/sendOrder.html" method="post">
            <input type="hidden" id="orderId" name="orderName"/>
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabel2">

                    </h4>
                </div>
                <div class="modal-body" id="hintContext2">
                    请选择快递公司：
                    <c:forEach items="${companyLs }" var="l">
                        <input type="checkbox" name="seleCompany" value="${l.id }"
                               onclick="getCourier()"/>${l.company_name }
                    </c:forEach>
                    <hr/>

                    请选择快递员：
                    <div id="divCourier">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" id="send">确认维护
                    </button>
                    <button type="button" class="btn btn-default" id="closeButton2">关闭窗口
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<!--订单号详情-->
<div style="background:#000;position:fixed;top:0;left:0; width:100%; height:100%; opacity:0.2; z-index:9999;display:none; "
     id="modalmb1"></div>
<div style="z-index:9999;position:fixed;top:100px; width:100%; display:none;" id="modalnr1">
    <div class="modal-dialog" style=" margin:auto">
        <div class="modal-content">
            <div class="modal-header" style="text-align: center;">
                <h3 class="modal-title" id="myModalLabel1">
                    订单详情
                </h3>
            </div>
            <div class="modal-body" id="hintContext1" style="height: 400px; overflow: auto;">
                <table class="table table-striped table-hover" style="border:1px #ccc solid;">
                    <tbody>
                    <tr>
                        <td width="40%;">订单编号：</td>
                        <td width="60%;" id="myNumber"></td>
                    </tr>
                    <tr>
                        <td>发货人姓名：</td>
                        <td id="shipperName"></td>
                    </tr>
                    <tr>
                        <td>发货人电话：</td>
                        <td id="shipper"></td>
                    </tr>
                    <tr>
                        <td>发货时间：</td>
                        <td id="shipTime"></td>
                    </tr>
                    <tr>
                        <td>邮寄预计时长：</td>
                        <td id="predictTime"></td>
                    </tr>
                    <tr>
                        <td>收货门店名称：</td>
                        <td id="store"></td>
                    </tr>
                    <tr>
                        <td>收货门店归属品牌：</td>
                        <td id="brand"></td>
                    </tr>
                    <tr>
                        <td>收货门店渠道编码：</td>
                        <td id="channel_code"></td>
                    </tr>
                    <tr>
                        <td>收货门店地址：</td>
                        <td id="address"></td>
                    </tr>
                    <tr>
                        <td>收货人姓名：</td>
                        <td id="consignee"></td>
                    </tr>
                    <tr>
                        <td>收货人电话：</td>
                        <td id="phone"></td>
                    </tr>
                    <tr>
                        <td>订单状态：</td>
                        <td id="state"></td>
                    </tr>
                    <tr>
                        <td>实际邮寄时长：</td>
                        <td id="factTime"></td>
                    </tr>
                    <tr>
                        <td>签收人姓名：</td>
                        <td id="signPeople"></td>
                    </tr>
                    <tr>
                        <td>签收人电话：</td>
                        <td id="signPhone"></td>
                    </tr>
                    <tr>
                        <td>签收时间：</td>
                        <td id="leadTime"></td>
                    </tr>
                    <tr>
                        <td>物资名称：</td>
                        <td id="materialContent"></td>
                    </tr>
                    <tr>
                        <td>物资数量：</td>
                        <td id="materialNumber"></td>
                    </tr>
                    <tr>
                        <td>快递员姓名：</td>
                        <td id="courierName"></td>
                    </tr>
                    <tr>
                        <td>快递员电话：</td>
                        <td id="courierPhone"></td>
                    </tr>
                    <tr>
                        <td>快递员收件时间：</td>
                        <td id="recipientTime"></td>
                    </tr>
                    <tr>
                        <td>快递员送达时间：</td>
                        <td id="serviceTime"></td>
                    </tr>
                    <tr>
                        <td>验证方式：</td>
                        <td id="myCheck"></td>
                    </tr>
                    </tbody>
                </table>
                <div style="width: 100%; display: none; margin-top: 10px;" class="myTable" id="myimages">
                    <div style="width: 100%; margin-top: 10px;">
                        <div class="A">订单图片</div>
                        <div class="A">门头图片</div>
                    </div>
                    <div style="width: 100%;">
                        <img src="" id="orderImg"/>
                        <img src="" id="storeImg"/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="closeButton1">关闭
                </button>
            </div>
        </div>
    </div>
</div>


<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">物流管理</a></li>
        <li><a href="#">物流订单列表</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <input id="isSeach1" type="hidden" value="${state }"/>
        <div class="toolbar1" id="toolDiv">
            <div class="input-group pull-right" style="width:300px;">
                <input type="text" id="searchPhone" class="form-control" placeholder="请输入电话号码或订单号" style="width: 200px;"
                       value="${phone }"/>
                <span class="input-group-btn">
		                            <button type="button" class="btn btn-info" onclick="searchPhone()">
		                                <i class="glyphicon glyphicon-search"></i>
		                                搜索
		                            </button>
		                        </span>
            </div>
            <div class="input-group pull-right" style="width:300px;">
	                        <span class="input-group-btn">
	                            <button type="button" class="btn btn-info" onclick="addOrder()">
	                                <i class="glyphicon glyphicon-search"></i>
	                                订单信息上传
	                            </button>
	                        </span>
            </div>
            <ul class="input-group pull-right">
                <c:if test="${state== 3 }">
                    <input id="download1" style="background-color: #3EB0D5;color:white; width:90px;height: 35px;"
                           type="button" value="下载清单"/>
                </c:if>


                <li style="margin-right:10px;" id="liAll">
                    <select id="isSeach" style="color:#000;opacity:1;height:100%;width:100%;background-color:#F2F6F8">
                        <option value="0">全部</option>
                        <c:choose>
                            <c:when test="${state == 1 }">
                                <option value="1" selected>待发货</option>
                            </c:when>
                            <c:otherwise>
                                <option value="1">待发货</option>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${state == 2}">
                                <option value="2" selected>配送中</option>
                            </c:when>
                            <c:otherwise>
                                <option value="2">配送中</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${state == 3}">
                                <option value="3" selected>配送完成</option>
                            </c:when>
                            <c:otherwise>
                                <option value="3">配送完成</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${state == 4}">
                                <option value="4" selected>派单中</option>
                            </c:when>
                            <c:otherwise>
                                <option value="4">派单中</option>
                            </c:otherwise>
                        </c:choose>
                    </select>

                </li>

            </ul>
        </div>


        <div class="toolbar1" id="toolDiv1" style="display: none; ">
            <div class="input-group pull-right" style="width:400px; text-align: right;">
                <input type="button" id="peisong" class="btn btn-success" value="批量派送"/>
                <input type="button" id="shanchu" class="btn btn-warning" value="批量删除" --%>

            </div>
        </div>


        <div class="itab_nav">
            <ul>
                <li><a href="#" class="selected" id="orderList1">订单列表</a></li>
                <li><a href="#" id="orderList2">待完善信息列表</a></li>
            </ul>
        </div>

        <!--订单列表 start-->
        <div class="line" style="display:" id="listDiv2">
            <div id="tab2" class="tabson">
                <div class="toolbar1"></div>
                <form id="fenye" name="fenye"
                      action="${pageContext.servletContext.contextPath }/background/order/list.html"
                      method="post">
                    <table class="tablelist">
                        <thead>
                        <tr>
                            <c:if test="${ state == 1}">
                                <th width="3%"><input id="chose" type="checkbox" name="checkbox"
                                                      onclick="selectAllCheckBox()"
                                                      style="<c:if test='${state!=1 }'>display:none</c:if>"/></th>
                            </c:if>
                            <th>订单编号</th>
                            <th>发货人姓名</th>
                            <th>发货时间</th>
                            <th>快递员姓名</th>
                            <th>物资名称</th>
                            <th>收货人姓名</th>
                            <th>收货具体地址</th>
                            <th>收货时间</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <%-- <c:forEach var="key" items="${page.items}"> --%>
                        <c:forEach var="key" items="${te}">
                        <tr>
                            <c:if test="${ state == 1}">
                                <td><input type="checkbox" name="check" value="${key.id}"
                                           style="<c:if test='${state!=1 }'>display:none</c:if>"/></td>
                            </c:if>
                            <td onclick='show(${key})'><a>${key.id}</a></td>
                            <td>${key.shipperName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${empty key.shipTime }">
                                        暂无记录
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatDate value="${key.shipTime }" pattern="yyyy-MM-dd  HH:mm"/>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>${key.courierName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${key.materialContent.length() > 10}">
                                        ${key.materialContent.substring(0, 10)} ...
                                    </c:when>
                                    <c:otherwise>
                                        ${key.materialContent}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${key.consignee}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${key.address.length() > 10}">
                                        ${key.address.substring(0, 10)}...
                                    </c:when>
                                    <c:otherwise>
                                        ${key.address}
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${empty key.leadTime }">
                                        暂无记录
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatDate value="${key.leadTime }" pattern="yyyy-MM-dd  HH:mm"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td><c:choose>
                                <c:when test="${key.state==0}">
                                    <p style="color:red ">待发货
                                    </p>

                                </c:when>
                                <c:when test="${key.state==1}">
                                    <p style="color:#000 ">已发货
                                    </p>

                                </c:when>
                                <c:when test="${ key.state==4}">
                                    <p style="color:blue ">配送中
                                    </p>
                                </c:when>
                                <c:when test="${key.state==5 or key.state==6}">
                                    <p style="color:green; "> 配送完成</p>
                                </c:when>
                                <c:when test="${key.state==2 or key.state==3}">
                                    <p style="color:orange;  ">派单中</p>
                                </c:when>

                            </c:choose></td>
                            <td><c:choose>

                                <c:when test="${key.state==0 or key.state==1}">
                                    <input type="button" class=" btn-primary btn-xs" onclick="update('${key.id}')"
                                           value="修改"
                                           style="<c:if test='${state!=1 and state!=0}'>display:none</c:if>"/>
                                </c:when>
                                <c:when test="${key.state==4}">
                                    <input type="button" id="map" class="btn-success  btn-xs"
                                           onclick="display('${key.id}','${key.courierPhone}','${key.store_longitude}','${key.store_latitude}')"
                                           value="显示地图"
                                           style="<c:if test='${state!=2  and state!=0}'>display:none</c:if>"/>
                                </c:when>
                                <c:when test="${key.state==5 or key.state==6}">
                                    <p style="color:green; "> 暂无操作</p>
                                </c:when>
                                <c:when test="${key.state==2 or key.state==3}">
                                    <p style="color:orange;  ">暂无操作</p>
                                </c:when>
                                </c:choose></td>

                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="statistics pull-left">
                        共<b style="color:#337ab7"> ${page.totalCount} </b>条记录，当前显示<b
                            style="color:#337ab7"> ${page.pageNo}/${page.totalPages } </b>页
                    </div>
                    <div class="pagination pull-right">
                        <ul id="page_demo"></ul>
                    </div>
                </form>
            </div>
        </div>
        <!--订单列表 end-->


        <!-- 待完善信息列表-start -->
        <div class="line" style="display:none;" id="listDiv3">
            <div id="tab3" class="tabson">
                <div class="toolbar1"></div>
                <form id="fenye3" name="fenye"
                      action="${pageContext.servletContext.contextPath}/background/order/list.html"
                      method="post">
                    <table class="tablelist">
                        <thead>
                        <tr>
                            <th width="3%"><input id="chose" type="checkbox" name="checkbox"
                                                  onclick="selectAllCheckBox()"/></th>
                            <th>订单编号</th>
                            <th>发货人姓名</th>
                            <th>发货时间</th>
                            <th>快递员姓名</th>
                            <th>物资名称</th>
                            <th>收货人姓名</th>
                            <th>收货具体地址</th>
                            <th>收货时间</th>
                            <th>状态</th>
                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:choose>
                            <c:when test="${not empty data1}">
                                <c:forEach var="key" items="${data1}">
                                    <tr>
                                        <td><input type="checkbox" name="check" value="${key.id}"/></td>
                                        <td>${key.id}</td>
                                        <td>${key.shipperName}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${empty key.shipTime }">
                                                    暂无记录
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:formatDate value="${key.shipTime }"
                                                                    pattern="yyyy-MM-dd  HH:mm"/>
                                                </c:otherwise>
                                            </c:choose>

                                        </td>
                                        <td id="${key.id}">${key.courierName}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${key.materialContent.length() > 10}">
                                                    ${key.materialContent.substring(0, 10)} ...
                                                </c:when>
                                                <c:otherwise>
                                                    ${key.materialContent}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${key.consignee}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${key.address.length() > 10}">
                                                    ${key.address.substring(0, 10)}...
                                                </c:when>
                                                <c:otherwise>
                                                    ${key.address}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <td>
                                            <c:choose>
                                                <c:when test="${empty key.leadTime }">
                                                    暂无记录
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:formatDate value="${key.leadTime }"
                                                                    pattern="yyyy-MM-dd  HH:mm"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <td><p style="color:GREEN ">待发货</p></td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="10">暂无数据</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>

                    <div class="statistics pull-left">
                        共<b style="color:#337ab7"> ${page1.totalCount} </b>条记录，当前显示<b
                            style="color:#337ab7"> ${page1.pageNo}/${page1.totalPages } </b>页
                    </div>
                    <div class="pagination pull-right">
                        <ul id="page_demo1"></ul>
                    </div>
                </form>
            </div>
        </div>
        <!-- 待完善信息列表-end -->


    </div>
</div>
</body>
<script type="text/javascript">
    var info = '${info}';
    var modifyInfo = '${modifyInfo}';
    var deleteInfo = '${deleteInfo}';
    var orderInfo = '${orderInfo}';
    if (info != null && info != "") {
        $("#modalmb").show();
        $("#modalnr").show();
        $("#hintContext").html(info);
    }
    if (modifyInfo != null && modifyInfo != "") {
        $("#modalmb").show();
        $("#modalnr").show();
        $("#hintContext").html(modifyInfo);

    }

    if (deleteInfo != null && deleteInfo != "") {
        $("#modalmb").show();
        $("#modalnr").show();
        $("#hintContext").html(deleteInfo);
    }

    if (orderInfo != null && orderInfo != "") {
        //显示需要完善信息的订单列表
        $("#listDiv3").show();
        //隐藏订单列表
        $("#listDiv2").hide();
        //给待完善订单信息标签添加class
        $("#orderList2").attr({class: "selected"});
        //取消订单列表class属性
        $("#orderList1").removeAttr("class");
        //隐藏订单筛选
        $("#toolDiv1").show();
        $("#toolDiv").hide();

        $("#modalmb").show();
        $("#modalnr").show();
        $("#hintContext").html(orderInfo);
    }

    $("#closeFork").click(function () {
        $("#modalmb").hide();
        $("#modalnr").hide();
    });
    $("#closeButton").click(function () {
        $("#modalmb").hide();
        $("#modalnr").hide();
    });
    $("#closeButton1").click(function () {
        $("#modalmb1").hide();
        $("#modalnr1").hide();
    });
    $("#closeButton2").click(function () {
        //清除文本框信息
        $("input[name='seleCompany']:checked").each(function () {
            $(this).attr("checked", false);
        })
        $("#divCourier").html("");
        $("#modalmb2").hide();
        $("#modalnr2").hide();
    });

    //点击未完善的信息显示信息框
    $("#orderList2").click(function () {
        //显示需要完善信息的订单列表
        $("#listDiv3").show();
        //隐藏订单列表
        $("#listDiv2").hide();
        //给待完善订单信息标签添加class
        $("#orderList2").attr({class: "selected"});
        //取消订单列表class属性
        $("#orderList1").removeAttr("class");
        //隐藏订单筛选
        $("#toolDiv1").show();
        $("#toolDiv").hide();

    });

    //点击订单列表的信息显示信息框
    $("#orderList1").click(function () {
        //显示订单列表
        $("#listDiv2").show();
        //隐藏需要完善信息的订单列表
        $("#listDiv3").hide();
        //添加订单列表class属性
        $("#orderList1").attr({class: "selected"});
        //删除待完善订单信息标签class
        $("#orderList2").removeAttr("class");
        //显示订单筛选
        $("#toolDiv").show();
        $("#toolDiv1").hide();

    });

    //
    $("#send").click(function () {
        var a = $("input[name='seleCourier']:checked").val();
        if (a != null && a != "") {
            $("#sendForm").submit();
        } else {
            $("#divCourier").html("<font color='red'>请选择快递员</font>")
        }
    });


    function getCourier() {
        $("#divCourier").empty();
        var select_value = new Array();
        $("input[name='seleCompany']:checked").each(function () {
            select_value.push($(this).val());
        })
        $.ajax({
            url: "getCourier.html",
            type: "POST",
            data: {"companyId": select_value},
            traditional: true,
            success: function (data) {
                //循环赋值
                for (var i = 0; i < data.length; i++) {
                    //拼接快递员信息
                    $("#divCourier").append("<input type='checkbox' name='seleCourier' value='" + data[i].accountName + "'/>" + data[i].real_name)

                }
            }
        });
    };

    // 添加订单信息
    function addOrder() {
        location.href = "${pageContext.request.contextPath}/background/order/addOrderUI.html"
    }

    function searchPhone1() {
        var searchphone = $("#searchPhone1").val();
        if (searchphone == "") {
            alert("请输入用户号码及订单号");
        } else {
            window.location.href = "${pageContext.request.contextPath}/background/order/search.html?phone=" + searchphone;
        }
    }

    function searchPhone() {
        var searchphone = $("#searchPhone").val();
        if (searchphone == "") {
            alert("请输入用户号码及订单号");
        } else {
            window.location.href = "${pageContext.request.contextPath}/background/order/search.html?phone=" + searchphone;
        }
    }

    // 根据配送状态select
    $("#isSeach").change(function () {
        var state = $("#isSeach").val();
        window.location.href = "${pageContext.request.contextPath}/background/order/list.html?state=" + state;
    });

    //  配送完成,下载清单
    $("#download1").click(function () {
        window.location.href = 'downLogistics.html';
    });

    // 修改订单信息
    function update(id) {
        window.location.href = "${pageContext.request.contextPath}/background/order/findByLogistics.html?id=" + id;
    };

    // 显示地图传过去经纬度
    function display(id, courierPhone, store_longitude, store_latitude) {
        window.location.href = "${pageContext.request.contextPath}/background/order/displayMap.html?id=" + id
            + "&&courier_Phone=" + courierPhone + "&&store_longitude=" + store_longitude + "&&store_latitude=" + store_latitude;
    };

    // 批量删除
    $("#shanchu").click(function () {
        //判断至少写了一项
        var checkedNum = $("input[name='check']:checked").length;
        if (checkedNum == 0) {
            alert("请至少选择一项!");
            return false;
        }

        if (confirm("确定删除所选订单?")) {
            var checkedList = new Array();
            $("input[name='check']:checked").each(function () {
                checkedList.push($(this).val());
            });
        }

        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/background/order/deleteLogistics.html",
            data: "ids=" + checkedList.toString(),
            success: function (result) {
                if (result.state == "ok") {
                    alert("delete order successful!")
                    window.location.reload();
                } else {
                    alert("delete order failture...");
                }
            },

        });
    });


    // 批量配送
    $("#peisong").click(function () {
        //判断至少写了一项
        var checkedNum = $("input[name='check']:checked").length;
        if (checkedNum == 0) {
            alert("请至少选择一项!");
            return false;
        }

        // 如果快递员信息为空
        var ii = $("input[name='check']:checked").val();
        var courierName = $("#" + ii).text();
        if (courierName == null || courierName == "") {
            $("#modalmb2").show();
            $("#modalnr2").show();
            var checkedLs = new Array();
            $("input[name='check']:checked").each(function () {
                checkedLs.push($(this).val());
            });
            $("#orderId").val(checkedLs);
            return false;
        }

        if (confirm("确定配送此订单？")) {
            var checkedList = new Array();
            $("input[name='check']:checked").each(function () {
                checkedList.push($(this).val());
            });

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/background/order/distribution.html",
                data: "id=" + checkedList.toString(),
                success: function (result) {
                    if (result.state == "ok") {
                        alert("delivery order successful!");
                        window.location.reload();
                    } else {
                        alert("delivery order failture...");
                    }
                }
            });
        }

    });


    $("#page_demo").twbsPagination({
        totalPages: "${page.totalPages}", //总页数
        visiblePages: 5,//导航页个数
        first: '首页',
        last: '末页',
        prev: '上一页',
        next: '下一页',
        href: "?pageNo={{number}}&state=${state}"
    });

    $("#page_demo1").twbsPagination({
        totalPages: "${page1.totalPages}", //总页数
        visiblePages: 5,//导航页个数
        first: '首页',
        last: '末页',
        prev: '上一页',
        next: '下一页',
        href: "?pageNo={{number}}&state=${state}"
    });


    function show(data) {
        //var data = strToJson(mylist);
        console.log(data);
        $("#myNumber").html(data.id);
        $("#shipperName").html(data.shipperName);
        $("#shipper").html(data.shipper);
        if (data.shipTime != null && data.shipTime != "") {
            $("#shipTime").html(conver(data.shipTime));
        } else {
            $("#shipTime").html("暂无记录");
        }
        if (data.predictTime != null && data.predictTime != "") {
            $("#predictTime").html(data.predictTime + " 小时");
        } else {
            $("#predictTime").html("暂无记录");
        }

        $("#store").html(data.store);
        $("#brand").html(data.brand);
        $("#channel_code").html(data.channel_code);
        $("#address").html(data.address);
        $("#consignee").html(data.consignee);
        $("#phone").html(data.phone);
        $("#state").html(myState(data.state));
        $("#factTime").html(isORNotNull(data.factTime));
        $("#signPeople").html(isORNotNull(data.signPeople));
        $("#signPhone").html(isORNotNull(data.signPhone));
        if (data.leadTime != null && data.leadTime != "") {
            $("#leadTime").html(conver(data.leadTime));
        } else {
            $("#leadTime").html("暂无记录");
        }
        $("#materialContent").html(data.materialContent);
        $("#materialNumber").html(data.materialNumber);
        $("#courierName").html(data.courierName);
        $("#courierPhone").html(data.courierPhone);
        if (data.recipientTime != null && data.recipientTime != "") {
            $("#recipientTime").html(conver(data.recipientTime));
        } else {
            $("#recipientTime").html("暂无记录");
        }
        if (data.serviceTime != null && data.serviceTime != "") {
            $("#serviceTime").html(conver(data.serviceTime));
        } else {
            $("#serviceTime").html("暂无记录");
        }
        $("#myCheck").html(myCheck(myState(data.state), data.orderImg, data.storeImg));
        console.log($("#myCheck").html());
        if ($("#myCheck").html() === "图片验证") {
            $("#myimages").css('display', 'block');
            $("#orderImg").attr('src', data.orderImg);
            $("#storeImg").attr('src', data.storeImg);
        } else {
            $("#myimages").css('display', 'none');
        }
        $("#modalmb1").show();
        $("#modalnr1").show();
    }

    //日期转换
    function conver(e) {
        var date = new Date(e);
        var year = date.getFullYear()
        var month = date.getMonth() + 1;
        var day = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();

        month = (month < 10 ? "0" + month : month);
        day = (day < 10 ? "0" + day : day);
        return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
    }

    //将map字符串转化为JSON
    function strToJson(e) {
        /* var d = e.substring(1,e.length-2).split(",");
        var jstr = "{";
        for(var i = 0;i<d.length;i++){
            if(i==d.length-1)
                jstr += '"'+d[i].split("=")[0].replace(/(^\s*)/g, "")+'":"' + d[i].split("=")[1] + '"';
            else
                jstr += '"'+d[i].split("=")[0].replace(/(^\s*)/g, "")+'":"' + d[i].split("=")[1] + '",';
        }
        jstr += "}";
        console.log(jstr);
        return JSON.parse(jstr);*/
    }

    //订单状态
    function myState(state) {
        if (state < 3) {
            return "已发货";
        } else if (state >= 3 && state < 6) {
            return "派送中";
        } else if (state >= 6) {
            return "已送达";
        }
    }

    //格式化日期,
    function myFormatDate(myDate) {
        if (myDate != null && myDate != "") {
            return formatDate(new Date(myDate.replace(/-/g, "/")), "yyyy-MM-dd hh:mm:ss");
        } else {
            return "暂无记录";
        }
    }

    //格式化日期,
    function formatDate(date, format) {
        var paddNum = function (num) {
            num += "";
            return num.replace(/^(\d)$/, "0$1");
        }
        //指定格式字符
        var cfg = {
            yyyy: date.getFullYear() //年 : 4位
            , yy: date.getFullYear().toString().substring(2)//年 : 2位
            , M: date.getMonth() + 1  //月 : 如果1位的时候不补0
            , MM: paddNum(date.getMonth() + 1) //月 : 如果1位的时候补0
            , d: date.getDate()   //日 : 如果1位的时候不补0
            , dd: paddNum(date.getDate())//日 : 如果1位的时候补0
            , hh: paddNum(date.getHours())  //时
            , mm: paddNum(date.getMinutes()) //分
            , ss: paddNum(date.getSeconds()) //秒
        }
        format || (format = "yyyy-MM-dd hh:mm:ss");
        return format.replace(/([a-z])(\1)*/ig, function (m) {
            return cfg[m];
        });
    }

    //为空校验
    function isORNotNull(parameter) {
        if (parameter == null || parameter == "") {
            return "暂无记录";
        } else {
            return parameter;
        }
    };

    //货物送达验证方式
    function myCheck(state, orderImg, storeImg) {
        if (state != "已送达") {
            return "未送达";
        } else {
            if (orderImg != null && orderImg != "" && storeImg != null && storeImg != "") {
                return "图片验证";
            } else {
                return "短信验证";
            }
        }
    }
</script>
</html>	

