<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@include file="/common/common-js.jsp" %>
<%@include file="/common/common-css.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>门店管理-门店index</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath }/js/jquery.twbsPagination.min.js"></script>

</head>
<body>

<div style="background:#000;position:fixed;top:0;left:0; width:100%; height:100%; opacity:0.2; z-index:9999;display:none; "
     id="modalmb"></div>
<div style="z-index:9999;position:fixed;top:100px; width:100%; display:none;" id="modalnr">
    <div class="modal-dialog" style=" margin:auto">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" id="closeFork" style="margin-top: -13px;">
                    &times;
                </button>
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


<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">物流管理</a></li>
        <li><a href="#">门店管理</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="toolbar1">
            <ul class="toolbar">
                <a href="javascript:addStore();">
                    <li class="click">
     						<span>
								<img src="${pageContext.request.contextPath}/images/t01.png"/>
							</span>Add Store
                    </li>
                </a>
                <a href="javascript:deleteAll();">
                    <li class="click">
							<span>
								<img src="${pageContext.request.contextPath}/images/t03.png"/>
							</span>Delete Store
                    </li>
                </a>
            </ul>
        </div>
        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">门店列表</a></li>
            </ul>
        </div>
        <div class="line">
            <div id="tab1" class="tabson">
                <div class="toolbar1"></div>
                <form id="fenye"
                      name="fenye"
                      action="${pageContext.servletContext.contextPath}/background/role/query.html" method="post">
                    <table class="tablelist">
                        <thead>
                        <tr>
                            <th width="2%">
                                <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()"/>
                            </th>
                            <th width="15%">门店名称</th>
                            <th width="15%">门店地址</th>
                            <th width="8%">店长</th>
                            <th width="6%">渠道编码</th>
                            <th width="9%">归属厂商</th>
                            <th width="9%">创建时间</th>
                            <th width="6%">创建人</th>
                            <th width="8%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="store" items="${page.items}">
                            <tr>
                                <td><input type="checkbox" name="check" value="${store.id}"/></td>
                                <td>${store.store_name}</td>
                                <td>${store.store_address}</td>
                                <td>${store.store_shopowner_name}</td>
                                <td>${store.channel_code}</td>
                                <td>${store.vendor_name}</td>
                                <td><fmt:formatDate value='${store.create_time}' pattern="yyyy-MM-dd HH:mm"/></td>
                                <td>${store.create_phone}</td>
                                <td>
                                    <a href="javascript:void(0);" onclick="permission('${store.id}')" style="color: blue; margin-left: 35%">修改</a>&nbsp;&nbsp;
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination pull-right">
                        <ul id="page_search"></ul>
                    </div>
                    <c:if test="${page.totalPages != null}">
                        <div class="statistics pull-left">
                            共<b style="color:#4dc8fc"> ${page.totalCount} </b>条记录，当前显示<b
                                style="color:#4dc8fc"> ${page.pageNo}/${page.totalPages } </b>页
                        </div>
                    </c:if>
                </form>
            </div>
            <div>
            </div>
        </div>
</body>
<script type="text/javascript">
    var info = '${info}';
    var modifyState = '${modifyState}';
    var deleteinfo = '${deleteinfo}';

    if (info != null && info != "") {
        $("#modalmb").show();
        $("#modalnr").show();
        $("#hintContext").html(info);
    }
    if (modifyState != null && modifyState != "") {
        $("#modalmb").show();
        $("#modalnr").show();
        $("#hintContext").html(modifyState);
    }
    if (deleteinfo != null && deleteinfo != "") {
        $("#modalmb").show();
        $("#modalnr").show();
        $("#hintContext").html(deleteinfo);
    }
    $("#closeFork").click(function () {
        $("#modalmb").hide();
        $("#modalnr").hide();
    });
    $("#closeButton").click(function () {
        $("#modalmb").hide();
        $("#modalnr").hide();
    });
</script>
<script type="text/javascript">
    // 门店信息批量维护
    function addStore() {
        window.location.href = "${pageContext.request.contextPath}/background/Store/addUI.html";
    }

    // 删除选中的门店信息
    function deleteAll() {
        var a = confirm("确认删除当前选中的数据？");
        if (a) {
            var id = "";
            var a = $("[name='check']");
            for (var i = 0; i < a.length; i++) {
                if (a[i].checked == true) {
                    id = id + a[i].value + ",";
                }
            }
            window.location.href = "${pageContext.request.contextPath}/background/Store/deleteStore.html?id=" + id;
        }
    }

    // 修改该条数据
    function permission(id) {
        window.location.href = "${pageContext.servletContext.contextPath }/background/Store/permission.html?storeId=" + id;
    }

    //分页控件
    $("#page_search").twbsPagination({
        totalPages: "${page.totalPages}",//总页数
        visiblePages: 5,//导航页个数
        first: '首页',
        last: '末页',
        prev: '上一页',
        next: '下一页',
        href: "?pageNow={{number}}"
    });
</script>
</html>
