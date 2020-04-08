<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%-- <%@include file="/common/common-css.jsp" %>
<%@include file="/common/common-js.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>组织架构_index</title>
    <link href="${pageContext.servletContext.contextPath }/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/css/pagination.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.pagination.js"></script>


    <script type="text/javascript">

        function addGroup() {
            window.location.href = "${pageContext.request.contextPath}/background/group/addGroup.html";
        }

        function deleteGroup(flag) {
            var checks = document.getElementsByName("check");
            if (result(checks) != false) {
                alert(" 请 选 择 你 要 操 作 的 项 ！");
                return;
            }
            var values = morecheck(checks);
            //执行产出操作
            if (flag == 1) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/background/group/delete.html",
                    data: "id=" + values,
                    success: function (msg) {
                        if (msg == "ok") {
                            window.location.href = "${pageContext.request.contextPath}/background/group/list.html";
                        } else {
                            alert("删除失败");
                        }
                    }
                });
            } else {
                //执行修改操作
                window.location.href = "${pageContext.request.contextPath}/background/resources/editUI.html?resourcesId=" + values;
            }

        }

        function showRole(id) {
            var url = "${pageContext.servletContext.contextPath }/background/group/permissionRole.html?groupId=" + id;
            var h_sp1 = 400;
            var w_sp1 = 350;
            //兼容IE，firefox,google.模态窗口居中问题
            var iTop2 = (window.screen.availHeight - 20 - h_sp1) / 2;
            var iLeft2 = (window.screen.availWidth - 10 - w_sp1) / 2;
            var params = 'menubar:no;dialogHeight=' + h_sp1 + 'px;dialogWidth=' + w_sp1 + 'px;dialogLeft=' + iLeft2 + 'px;dialogTop=' + iTop2 + 'px;resizable=yes;scrollbars=0;resizeable=0;center=yes;location:no;status:no;scroll:no'
            window.open(url, window, params);
        }

        function modifyUser(id) {
            window.location.href = "${pageContext.request.contextPath}/background/group/editUI.html?groupId=" + id;
        }

        //点击删除时是否有勾选
        function result(checks) {
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

        function selectAllCheckBox() {
            var chose;
            if (document.getElementById("chose").checked) {
                chose = document.getElementById("chose").checked;
            }

            var checkboxArray = document.getElementsByName("check");
            if (checkboxArray != null) {
                for (var i = 0; i < checkboxArray.length; i++) {
                    checkboxArray[i].checked = chose;
                }
                ;
            }
            ;
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
        <li><a href="#">基础管理</a></li>
        <li><a href="#">组织架构</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="toolbar1">
            <ul class="toolbar">
                <a href="javascript:addGroup()">
                    <li class="click">
     <span>
     <img src="${pageContext.request.contextPath}/images/t01.png"/>
     </span>新增组织机构
                    </li>
                </a>
                <a href="javascript:deleteGroup(1)">
                    <li><span><img src="${pageContext.servletContext.contextPath}/images/t03.png"/></span>删除组织机构</li>
                </a>
            </ul>
        </div>
        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">组织机构列表</a></li>
            </ul>
        </div>
        <div class="line">
            <div id="tab2" class="tabson">
                <div class="toolbar1"></div>
                <form id="fenye" name="fenye"
                      action="${pageContext.servletContext.contextPath}/background/group/list.html" method="post">
                    <table class="tablelist">
                        <thead>
                        <tr>
                            <th width="3%">
                                <input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()"/>
                            </th>
                            <th width="6%">组织编号</th>
                            <th width="15%">组织名</th>
                            <th width="16%">父级名</th>
                            <th width="13%">描述</th>
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
                                <td>${key.name}</td>
                                <td>${key.parentId}</td>
                                <td>${key.description}</td>

                                <td>
                                    <a href="javascript:void(0);" onclick="showRole('${key.id}')" style="margin-left:20%; color: green">分配资源</a>
                                    &nbsp;&nbsp;&nbsp;
                                    <a href="javascript:void(0);" onclick="modifyUser('${key.id}')" style="margin-left:10%; color: red">修改信息</a>
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
                            <!--  <li class="paginItem"><a href="javascript:;">1</a></li>
                             <li class="paginItem current"><a href="javascript:;">2</a></li>
                             <li class="paginItem"><a href="javascript:;">3</a></li>
                             <li class="paginItem"><a href="javascript:;">4</a></li>
                             <li class="paginItem"><a href="javascript:;">5</a></li>
                             <li class="paginItem more"><a href="javascript:;">...</a></li>
                             <li class="paginItem"><a href="javascript:;">10</a></li> -->

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

                    </div>
                    <input type="hidden" name="pageSize" value="${pageView.pageSize}"/>
                    <input type="hidden" name="pageNow" value="${pageView.pageNow}" id="pageNow"/>
                    <div id="Pagination" class="scott"></div>
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
