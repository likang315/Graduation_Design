<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
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
        function results(checks) {
            for (var i = 0; i < checks.length; i++) {
                if (checks[i].checked == true) {
                    return false;
                }
            }

        }

        //删除一个
        function morecheck(checks) {
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

        function addRole() {
            window.location.href = "${pageContext.request.contextPath}/background/role/addUI.html";
        }

        function modifyRole(roleId) {
            window.location.href = "${pageContext.request.contextPath}/background/role/editUI.html?roleId=" + roleId;
        }

        function permissio(id, groupId) {

            var url = "${pageContext.servletContext.contextPath }/background/resources/permissioRole.html?roleId=" + id + "&groupId=" + groupId;
            var h_sp1 = 400;
            var w_sp1 = 350;
            //兼容IE，firefox,google.模态窗口居中问题
            var iTop2 = (window.screen.availHeight - 20 - h_sp1) / 2;
            var iLeft2 = (window.screen.availWidth - 10 - w_sp1) / 2;
            var params = 'menubar:no;dialogHeight=' + h_sp1 + 'px;dialogWidth=' + w_sp1 + 'px;dialogLeft=' + iLeft2 + 'px;dialogTop=' + iTop2 + 'px;resizable=yes;scrollbars=0;resizeable=0;center=yes;location:no;status:no;scroll:no'
            window.open(url, window, params);
            //location.href=url;
        }

        function pageSubmit(pageNow) {
            var pageCount =${pageView.pageCount };
            var pageCur =${pageView.pageNow };

            $('#pageNow').val(pageNow);
            if (pageNow < 1)
                $('#pageNow').val(1)
            if (pageNow > pageCount)
                $('#pageNow').val(pageCount)


            if ($('#pageNow').val() == pageCur)
                return;
            $('#pageNow').val(pageNow);
            $('#fenye').submit();
        }

    </script>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">基础信息管理</a></li>
        <li><a href="#">角色列表</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="toolbar1">
            <ul class="toolbar">
                <a href="javascript:addRole();">
                    <li class="click">
     <span>
        <img src="${pageContext.request.contextPath}/images/t01.png"/>
     </span> 新增
                    </li>
                </a>

                <a href="javascript:deleteAll();">
                    <li>
     <span>
        <img src="${pageContext.request.contextPath}/images/t03.png"/>
     </span> 删除
                    </li>
                </a>

            </ul>
        </div>
        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">角色列表</a></li>
            </ul>
        </div>
        <div class="line">
            <form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath}/background/role/query.html"
                  method="post">
                <div id="tab2" class="tabson">
                    <table class="tablelist">
                        <thead>
                        <tr>
                            <th width="2%">
                                <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()"/>
                            </th>

                            <th width="15%">角色名称</th>
                            <th width="10%">组织机构</th>
                            <th width="8%">角色KEY</th>
                            <th width="6%">是否禁用</th>
                            <th width="9%">描述</th>
                            <th width="12%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="role" items="${pageView.records}">
                            <tr>
                                <td><input type="checkbox" name="check" value="${role.id}"/></td>
                                <td>${role.name}</td>
                                <td>${role.groupName}</td>
                                <td>${role.roleKey}</td>
                                <td>
                                    <c:if test="${role.enable eq '2'}">
                                        <font color="red">禁用</font>
                                    </c:if>
                                    <c:if test="${role.enable eq '1'}">
                                        <font color="blue">正常</font
                                    </c:if>
                                </td>
                                <td>${role.description}</td>
                                <td>
                                    <a href="javascript:void(0);" onclick="permissio('${role.id}','${role.groupId}')" style="margin-left:20%; color: green">分配权限</a>&nbsp;&nbsp;
                                    <a href="javascript:void(0);" onclick="modifyRole('${role.id}')" style="margin-left:10%; color: red">编辑</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="pagin">
                    <div class="message">共<i class="blue">${pageView.rowCount }</i>条记录，当前显示第&nbsp;<i
                            class="blue">${pageView.pageNow }&nbsp;</i>页
                    </div>
                    <ul class="paginList">
                        <li class="paginItem"><a href="javascript:pageSubmit(${pageView.pageNow-1 });"><span
                                class="pagepre"></span></a></li>

                        <c:forEach var="x" begin="${pageView.prePageStart }" end="${pageView.prePageEnd }">
                            <c:choose>
                                <c:when test="${x==pageView.pageNow }">
                                    <li class="paginItem"><a href="javascript:;">${x }</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="paginItem"><a href="javascript:pageSubmit(${x });">${x }</a></li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                        <li class="paginItem"><a href="javascript:;">...</a></li>
                        <c:forEach var="x" begin="${pageView.pnexPageStart }" end="${pageView.pnexPageEnd }">
                            <c:choose>
                                <c:when test="${x==pageView.pageNow }">
                                    <li class="paginItem"><a href="javascript:;">${x }</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="paginItem"><a href="javascript:pageSubmit(${x });">${x }</a></li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>

                        <li class="paginItem"><a href="javascript:pageSubmit(${pageView.pageNow+1 });"><span
                                class="pagenxt"></span></a></li>
                    </ul>
                    <input type="hidden" name="pageSize" value="${pageView.pageSize}"/>
                    <input type="hidden" name="pageNow" value="${pageView.pageNow}" id="pageNow"/>
                </div>
            </form>
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
