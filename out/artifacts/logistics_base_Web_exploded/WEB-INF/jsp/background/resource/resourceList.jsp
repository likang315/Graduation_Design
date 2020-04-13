<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>资源列表管理-资源列表</title>
    <%@include file="/common/common-css.jsp" %>
    <%@include file="/common/common-js.jsp" %>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js"></script>
    <script type="text/javascript">
        $(document).ready(function (e) {
            $(".select1").uedSelect({
                width: 345
            });
            $(".select2").uedSelect({
                width: 167
            });
            $(".select3").uedSelect({
                width: 100
            });
        });

        //点击删除时是否有勾选
        function result(checks) {
            for (var i = 0; i < checks.length; i++) {
                if (checks[i].checked == true) {
                    return false;
                }
            }

        }

        //删除一个
        function moreCheck(checks) {
            var j = 0;
            var values;
            for (var i = 0; i < checks.length; i++) {
                if (checks[i].checked) {
                    values = checks[i].value;
                    j++;
                }
            }
            if (j > 1) {
                alert("请选择一条数据进行操作！");
                return false;
            } else {
                return values;
            }
        }

        // 既是删除有是修改
        function deleteResource(flag) {
            var checks = document.getElementsByName("check");
            if (result(checks) != false) {
                alert(" 请 选 择 你 要 操作 的 项 ！");
                return;
            }
            var values = moreCheck(checks);
            //执行产出操作
            if (flag == 1) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/background/resources/findResourceById.html",
                    data: "id=" + values,
                    success: function (msg) {
                        if (msg == "\"\"") {
                            window.location.href = "${pageContext.request.contextPath}/background/resources/delete.html?id=" + values;
                        } else {
                            alert("此条数据有关联数据，请不要删除");
                        }
                    }
                });
            } else {//执行修改操作
                window.location.href = "${pageContext.request.contextPath}/background/resources/editUI.html?resourcesId=" + values;
            }

        }

        // 新增资源
        function addResource() {
            window.location.href = "${pageContext.request.contextPath}/background/resources/addUI.html";
        }


    </script>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">基础管理</a></li>
        <li><a href="#">资源列表管理</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="toolbar1">
            <ul class="toolbar">
                <a href="javascript:addResource()">
                    <li class="click">
     <span>
     <img src="${pageContext.request.contextPath}/images/t01.png"/>
     </span>新增
                    </li>
                </a>
                <a href="javascript:deleteResource(2);">
                    <li class="click">
     <span>
     <img src="${pageContext.request.contextPath}/images/t02.png"/>
     </span>辑辑
                    </li>
                </a>
                <a href="javascript:deleteResource(1);">
                    <li><span>
        	<img src="${pageContext.request.contextPath}/images/t03.png"/>
        </span> 删除
                    </li>
                </a>

            </ul>
        </div>
        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">资源列表</a></li>
            </ul>
        </div>
        <div class="line">
            <div id="tab2" class="tabson">
                <table class="tablelist">
                    <thead>
                    <tr>
                        <th width="2%">
                            <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()"/>
                        </th>
                        <th width="25%">菜单名称</th>
                        <th width="8%">菜单类型</th>
                        <th width="6%">唯一KEY</th>
                        <th width="9%">URL地址</th>
                        <th width="12%">描述</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${resourceList}" var="treeo">
                        <tr>
                            <td><input type="checkbox" name="check" value="${treeo.id}"/></td>
                            <td>${treeo.name}</td>
                            <td>${treeo.type}</td>
                            <td>${treeo.resKey} </td>
                            <td>${treeo.resUrl} </td>
                            <td>${treeo.description}</td>
                        </tr>
                        <c:choose>
                            <c:when test="${!empty treeo.children}">
                                <c:forEach items="${treeo.children}" var="treech">
                                    <tr>
                                        <td><input type="checkbox" name="check" value="${treech.id}"/></td>
                                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${treech.name}</td>
                                        <td>${treech.type}</td>
                                        <td>${treech.resKey} </td>
                                        <td>${treech.resUrl} </td>
                                        <td>${treech.description}</td>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${!empty treech.children}">
                                            <c:forEach items="${treech.children}" var="treech1">
                                                <tr>
                                                    <td><input type="checkbox" name="check" value="${treech1.id}"/></td>
                                                    <td>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${treech1.name}</a></td>
                                                    <td>${treech1.type}</td>
                                                    <td>${treech1.resKey} </td>
                                                    <td>${treech1.resUrl}</td>
                                                    <td>${treech1.description}</td>
                                                </tr>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
    <script type="text/javascript">
        $("#usual1 ul").idTabs();
    </script>

    <script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
    </script>
</div>
</body>
</html>
