<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>需求上报审核-index</title>
    <%@include file="/common/common-css.jsp" %>
    <%@include file="/common/common-js.jsp" %>
    <%@include file="/common/date.jsp" %>
    <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath }/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath }/js/jquery.twbsPagination.min.js"></script>
    <style type="">
        .mydate {
            border: 1px solid #000;
            height: 29px;
            width: 75px;
            border-radius: 5px;
            padding-left: 3px;
            margin-right: 3px;
        }
    </style>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">物流管理</a></li>
        <li><a href="#">审核门店需求</a></li>
    </ul>
</div>
<div style="background:#000;position:fixed;top:0;left:0; width:100%; height:100%; opacity:0.2; z-index:9999;display:none; "
     id="modalmb1"></div>

<div style="z-index:9999;position:fixed;top:100px; width:100%; display:none;" id="modalnr1">
    <div class="modal-dialog" style=" margin:auto">
        <div class="modal-content">
            <div class="modal-header" style="text-align: center;">
                <h3 class="modal-title" id="myModalLabel1">
                    门店需求及审核详情
                </h3>
            </div>
            <div class="modal-body" id="hintContext1" style="height: 400px; overflow: auto;">
                <table class="table table-striped table-hover" style="border:1px #ccc solid;">
                    <tbody>
                    <tr>
                        <td width="40%;">门店名称：</td>
                        <td width="60%;" id="store_name"></td>
                    </tr>
                    <tr>
                        <td>渠道编码：</td>
                        <td id="channel_code"></td>
                    </tr>
                    <tr>
                        <td>上报人电话：</td>
                        <td id="store_shopowner_phone"></td>
                    </tr>
                    <tr>
                        <td>上报人姓名：</td>
                        <td id="store_shopowner_name"></td>
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
                        <td>上报时间：</td>
                        <td id="report_time"></td>
                    </tr>
                    <tr>
                        <td>扩展需求：</td>
                        <td id="expanding_demand"></td>
                    </tr>
                    <tr>
                        <td>审核状态：</td>
                        <td id="examine"></td>
                    </tr>
                    <tr>
                        <td>审核人(渠道经理)：</td>
                        <td id="examine_one_name"></td>
                    </tr>
                    <tr>
                        <td>审核人电话(渠道经理)：</td>
                        <td id="examine_one_phone"></td>
                    </tr>
                    <tr>
                        <td>审核人时间(渠道经理)：</td>
                        <td id="examine_one_time"></td>
                    </tr>
                    <tr>
                        <td>审核人(营销中心)：</td>
                        <td id="examine_two_name"></td>
                    </tr>
                    <tr>
                        <td>审核人电话(营销中心)：</td>
                        <td id="examine_two_phone"></td>
                    </tr>
                    <tr>
                        <td>审核时间(营销中心)：</td>
                        <td id="examine_time"></td>
                    </tr>
                    <tr>
                        <td>驳回原因：</td>
                        <td id="examine_reason"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="closeButton1">关闭
                </button>
            </div>
        </div>
    </div>
</div>

<div class="formbody">
    <div id="usual1" class="usual">
        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">审核门店需求上报</a></li>
            </ul>
        </div>
        <div class="line">
            <div id="tab2" class="tabson">
                <div class="toolbar1"></div>
                <span id="myResult"
                      style="display: none; font-size: 18px; color: red; text-align: center;">${result}</span>
                <form id="myform" name="fenye"
                      action="${pageContext.servletContext.contextPath }/background/DemandAudit/getList.html"
                      method="post">
                    <div style="font-size: 12px; width: 100%; text-align: right; margin-bottom: 10px;">
                        选择审核状态:
                        <select id="audit" name="dataState"
                                style="border:1px solid #000;opacity:1;height: 30px;width: 70px; border-radius:5px">
                            <c:if test="${dataState == 'all' }">
                                <option value="all" selected="selected">全部</option>
                                <option value="0">待审核</option>
                                <option value="1">审核通过</option>
                                <option value="2">审核驳回</option>
                            </c:if>
                            <c:if test="${dataState == '0' }">
                                <option value="all">全部</option>
                                <option value="0" selected="selected">待审核</option>
                                <option value="1">审核通过</option>
                                <option value="2">审核驳回</option>
                            </c:if>
                            <c:if test="${dataState == '1' }">
                                <option value="all">全部</option>
                                <option value="0">待审核</option>
                                <option value="1" selected="selected">审核通过</option>
                                <option value="2">审核驳回</option>
                            </c:if>
                            <c:if test="${dataState == '2' }">
                                <option value="all">全部</option>
                                <option value="0">待审核</option>
                                <option value="1">审核通过</option>
                                <option value="2" selected="selected">审核驳回</option>
                            </c:if>
                        </select>
                        选择开始日期:
                        <input type="text" class="mydate" id="startTime" name="startTime" value="${startTime}"/>
                        选择结束日期:
                        <input type="text" class="mydate" id="endTime" name="endTime" value="${endTime}"/>
                        <button type="button" id="timeSearch" class="btn btn-primary">确认</button>
                        <c:if test="${dataState == '0' }">
                            <button type="button" id="auditBtn" class="btn btn-primary" data-toggle="modal"
                                    data-target="#myModal">审核
                            </button>
                        </c:if>
                        <c:if test="${dataState == '1' }">
                            <button type="button" id="exportData" class="btn btn-primary">导出清单</button>
                        </c:if>
                    </div>
                    <table class="tablelist">
                        <thead>
                        <tr>
                            <c:if test="${dataState == '0'}">
                                <th><input type="checkbox" id="allSelect"/></th>
                            </c:if>
                            <th>序号</th>
                            <th>门店名称</th>
                            <th>上报人电话</th>
                            <th>上报人姓名</th>
                            <th>物资名称</th>
                            <th>上报时间</th>
                            <th>审核状态</th>
                            <th>操作</th>

                        </tr>
                        </thead>
                        <tbody align="center">
                        <c:choose>
                            <c:when test="${not empty ls }">
                                <c:forEach items="${ls}" var="auditData" varStatus="start">
                                    <tr>
                                        <c:if test="${dataState == '0'}">
                                            <td>
                                                <input type="checkbox" id="check" name="checkS"
                                                       value="${auditData.id }"/>
                                            </td>
                                        </c:if>
                                        <td>${start.index+1 }</td>
                                        <td onclick='show(${auditData})'><a>${auditData.store_name }</a></td>
                                        <td>${auditData.store_shopowner_phone }</td>
                                        <td>${auditData.store_shopowner_name }</td>
                                        <td>${auditData.materialContent }</td>
                                        <td><fmt:formatDate value="${auditData.report_time }"
                                                            pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>
                                            <c:if test="${auditData.examine == 0 }">
                                                <span style="color:orange;">待审核</span>
                                            </c:if>
                                            <c:if test="${auditData.examine == 1 }">

                                                <span style="color: green;">通过</span>
                                            </c:if>
                                            <c:if test="${auditData.examine == 2 }">

                                                <span style="color: red;">驳回</span>
                                            </c:if>
                                        </td>
                                        <c:if test="${auditData.examine == 0 ||auditData.examine == 3 || auditData.examine == 4}">
                                            <td>
                                                <a href="javascript:void(0);" onclick="audit(${auditData.id })"
                                                   data-toggle="modal" data-target="#myModal">审核</a>
                                            </td>
                                        </c:if>
                                        <c:if test="${auditData.examine == 1 ||auditData.examine ==2 }">
                                            <td>
                                                暂无操作
                                            </td>
                                        </c:if>


                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="11">无记录</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <div class="pagination pull-right">
                        <ul id="page_demo"></ul>
                    </div>
                    <div class="statistics pull-left">
                        共<b style="color:#337ab7"> ${page.totalCount} </b>条记录，当前显示<b
                            style="color:#337ab7"> ${page.pageNo}/${page.totalPages } </b>页
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 点击审核按钮后弹出选择审核通过与否 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <form id="NotgrantForm"
                      action="${pageContext.request.contextPath }/background/DemandAudit/updateAuditData.html"
                      method="post">
                    <input type="hidden" id="checkData" name="checkDatas"/>
                    <table style="margin: auto;">
                        <tr style="height:50px;">
                            <td>请选择审核结果: &nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <select id="Notgrant" name="examine"
                                        style="border:1px solid #ccc;opacity:1;height: 35px;width: 150px; border-radius:3px">
                                    <option value="pleaseSelect">请选择</option>
                                    <option value="1">通过</option>
                                    <option value="2">驳回</option>
                                </select>
                            </td>
                        </tr>
                        <br>
                        <tr style="display: none;" id="myExamine_reasontd">
                            <td>请输入驳回原因:&nbsp;&nbsp;&nbsp;</td>
                            <td>
                                <textarea rows="5" placeholder="在这里输入驳回原因"
                                          style="border: 1px #ccc solid;width: 150px;resize:none; border-radius:3px"
                                          id="myExamine_reason" name="examine_reason"></textarea>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="mengban">
                    确认
                </button>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">


    // 点击审核时，弹出审核框
    function audit(e) {
        var select_value = [];
        select_value.push(e);
        $("#checkData").val(select_value);
        $("#myModal").show();
    }


    $(function () {
        var result = $("#myResult").html();
        if (result != null && result != "") {
            $("#myResult").fadeIn(1000);//页面刷新后多长时间显示
            $("#myResult").fadeOut(2000);//显示后多长时间消失
            return;
        }
    })

    // 复选框
    $("#allSelect").click(function () {
        var ff = document.getElementById('allSelect');
        var aaaaa = $("[name='checkS']");
        if (ff.checked == true) {
            for (var i = 0; i < aaaaa.length; i++) {
                if (!aaaaa[i].disabled) {
                    aaaaa[i].checked = true;
                }

            }
        } else {
            for (var i = 0; i < aaaaa.length; i++) {
                aaaaa[i].checked = false;
            }
        }
    });

    // 批量审核
    $("#auditBtn").click(function () {
        var select_value = [];
        $("input[name = 'checkS']:checked").each(function () {
            select_value.push($(this).val());
        });
        if (select_value.length > 0) {
            $("#checkData").val(select_value);
            $("#myModal").show();
        } else {
            alert("请选择需要审核的数据");
            return false;
        }
    });

    //审核状态如果是驳回显示文本域(写驳回原因)
    $("#Notgrant").change(function () {
        if ($(this).val() === "2") {
            $("#myExamine_reasontd").show();
        } else {
            $("#myExamine_reasontd").hide();
        }
    });

    // 审核上的确认按钮
    $("#mengban").click(function () {
        if ($("#Notgrant").val() != "pleaseSelect") {
            if ($("#Notgrant").val() === "2" && $("#myExamine_reason").val() === "") {
                alert("请输入驳回原因");
                return false;
            }
            $("#NotgrantForm").submit();
        } else {
            alert("请选择审核结果");
            return false;
        }
    });

    // 导出清单
    $("#exportData").click(function () {
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        location.href = "${pageContext.request.contextPath }/background/DemandAudit/exportData.html?startTime=" + startTime + "&endTime=" + endTime;
    });

    //日期插件
    $(function () {
        $("#startTime").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true
        });

        $("#endTime").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true
        });
    });

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

    //时间查询
    $("#timeSearch").click(function () {
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        if (startTime == "" || endTime == "") {
            $("#myform").submit();
        } else {
            if (myseach(startTime, endTime)) {
                $("#myform").submit();
            } else {
                alert("起始日期不能大于结束日期");
                return false;
            }
        }
    });

    //需求数据状态查询
    $("#audit").change(function () {
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        if (startTime == "" || endTime == "") {
            $("#myform").submit();
        } else {
            if (myseach(startTime, endTime)) {
                $("#myform").submit();
            } else {
                alert("起始日期不能大于结束日期");
                return false;
            }
        }
    });

    $("#closeButton1").click(function () {
        $("#modalmb1").hide();
        $("#modalnr1").hide();
    });

    // 展示详情
    function show(data) {
        console.log(data);
        $("#store_name").html(data.store_name);
        $("#channel_code").html(data.channel_code);
        $("#store_shopowner_phone").html(data.store_shopowner_phone);
        $("#store_shopowner_name").html(data.store_shopowner_name);
        $("#materialContent").html(data.materialContent);
        $("#materialNumber").html(data.materialNumber);
        $("#report_time").html(conver(data.report_time));
        $("#examine").html(myState(data.examine));
        if (data.examine_one_time != null && data.examine_one_time != "") {
            $("#examine_one_time").html(conver(data.examine_one_time));
        } else {
            $("#examine_one_time").html("暂无记录");
        }
        if (data.examine_time != null && data.examine_time != "") {
            $("#examine_time").html(conver(data.examine_time));
        } else {
            $("#examine_time").html("暂无记录");
        }
        if (data.examine_one_name != null && data.examine_one_name != "") {
            $("#examine_one_name").html(data.examine_one_name);
        } else {
            $("#examine_one_name").html("暂无记录");
        }
        if (data.examine_one_phone != null && data.examine_one_phone != "") {
            $("#examine_one_phone").html(data.examine_one_phone);
        } else {
            $("#examine_one_phone").html("暂无记录");
        }
        if (data.examine_two_name != null && data.examine_two_name != "") {
            $("#examine_two_name").html(data.examine_two_name);
        } else {
            $("#examine_two_name").html("暂无记录");
        }
        if (data.examine_two_phone != null && data.examine_two_phone != "") {
            $("#examine_two_phone").html(data.examine_two_phone);
        } else {
            $("#examine_two_phone").html("暂无记录");
        }
        if (data.expanding_demand != null && data.expanding_demand != "") {
            $("#expanding_demand").html(data.expanding_demand);
        } else {
            $("#expanding_demand").html("暂无记录");
        }
        if (data.examine_reason != null && data.examine_reason != "") {
            $("#examine_reason").html(data.examine_reason);
        } else {
            $("#examine_reason").html("暂无记录");
        }
        $("#modalmb1").show();
        $("#modalnr1").show();
    }

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

    //订单状态
    function myState(examine) {
        if (examine == 0) {
            return "待审核";
        } else if (examine == 1) {
            return "营销中心审核通过";
        } else if (examine == 2) {
            return "营销中心审核通过";
        } else if (examine == 3) {
            return "渠道经理审核通过";
        } else if (examine == 4) {
            return "渠道经理审核驳回";
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



    //分页控件
    $("#page_demo").twbsPagination({
        totalPages: "${page.totalPages}",//总页数
        visiblePages: 5,//导航页个数
        first: '首页',
        last: '末页',
        prev: '上一页',
        next: '下一页',
        href: "?pageNo={{number}}&startTime=${startTime}&endTime=${endTime}&dataState=${dataState}"
    });
</script>
</html>