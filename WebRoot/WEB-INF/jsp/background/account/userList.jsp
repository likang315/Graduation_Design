<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@include file="/common/common-css.jsp" %>
<%@include file="/common/common-js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link href="${pageContext.servletContext.contextPath }/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/css/select.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery-3.0.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.idTabs.min.js"></script>


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

        function modifyUser(id) {
            window.location.href = "${pageContext.request.contextPath}/background/account/editUI.html?accountId=" + id;
        }

        function addUser() {
            window.location.href = "${pageContext.request.contextPath}/background/account/addUI.html";
        }

        function assignRole(groupId, id) {
            var url = "${pageContext.servletContext.contextPath }/background/role/queryforuser.html?groupId=" + groupId + "&userId=" + id;
            var h_sp1 = 400;
            var w_sp1 = 800;
            //兼容IE，firefox,google.模态窗口居中问题
            var iTop2 = (window.screen.availHeight - 20 - h_sp1) / 2;
            var iLeft2 = (window.screen.availWidth - 10 - w_sp1) / 2;
            var params = 'menubar:no;dialogHeight=' + h_sp1 + 'px;dialogWidth=' + w_sp1 + 'px;dialogLeft=' + iLeft2 + 'px;dialogTop=' + iTop2 + 'px;resizable=yes;scrollbars=0;resizeable=0;center=yes;location:no;status:no;scroll:no'
            // window.showModalDialog(url, window, params);
            window.open(url, window, params);
            //location.href=url;
        }

        function pageSubmit(pageNow) {
            var pageCount = "${pageView.pageCount }";
            var pageCur = "${pageView.pageNow }";

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

        function modifyPassword(userId) {
            window.location.href = "${pageContext.request.contextPath}/background/account/modifyPassword.html?userId=" + userId;
        }
    </script>
</head>

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">营销中心账号管理</a></li>
        <li><a href="#">账号列表</a></li>
    </ul>
</div>
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
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="toolbar1">
            <ul class="toolbar">
                <a href="javascript:addUser()">
                    <li class="click">
		     <span>
		     <img src="${pageContext.request.contextPath}/images/t01.png"/>
		     </span>新增用户
                    </li>
                </a>
                <a href="javascript:deleteAll()">
                    <li><span><img src="${pageContext.servletContext.contextPath}/images/t03.png"/></span>删除用户</li>
                </a>
            </ul>
        </div>
        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">客户列表</a></li>
            </ul>
        </div>
        <div class="line">
            <div id="tab2" class="tabson">
                <div class="toolbar1"></div>
                <form id="fenye" name="fenye"
                      action="${pageContext.servletContext.contextPath }/background/account/list.html" method="post">
                    <table class="tablelist">
                        <thead>
                        <tr>
                            <th width="3%">
                                <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()"/>
                            </th>
                            <th width="6%">用户编号</th>
                            <th width="10%">用户名</th>
                            <th width="10%">组织机构</th>
                            <th width="10%">所属角色</th>
                            <th width="14%">账号状态</th>
                            <th width="13%">描述</th>
                            <th width="16%">注册时间</th>
                            <th width="15%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="key" items="${pageView.records}">
                            <tr>
                                <td>
                                    <input type="checkbox" name="check" value="${key.id}"/>
                                </td>
                                <td>${key.id}</td>
                                <td>${key.accountName}</td>
                                <td>${key.groupName}</td>
                                <td>${key.roleName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${key.state==1}" >
                                            <font color="green">正常</font>
                                        </c:when>
                                        <c:otherwise>
                                            <font color="red">禁用</font>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                        ${key.description}
                                </td>
                                <td>
                                    <fmt:formatDate value="${key.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <a href="javascript:void(0);" onclick="modifyUser('${key.id}')" style="margin-left:10%; color: green">修改信息</a>
                                    <a href="javascript:void(0);" onclick="assignRole('${key.groupId}',${key.id })" style="margin-left:10%; color: blue">分配角色</a>
                                    <a href="javascript:void(0);" onclick="modifyPassword(${key.id })" style="margin-left:15%; color: red">变更密码</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

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
<script type="text/javascript">
    var info = '${info}';

    if (info != null && info != "") {
        $("#modalmb").show();
        $("#modalnr").show();
        $("#hintContext").html(info);
    }
</script>