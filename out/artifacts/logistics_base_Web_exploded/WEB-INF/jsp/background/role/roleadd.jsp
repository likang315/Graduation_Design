<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <%@include file="/common/common-css.jsp" %>
    <%@include file="/common/common-js.jsp" %>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet"
          type="text/css"/>

    <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script> --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>


    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/kindeditor/kindeditor.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-validation/jquery.validate.min.js"></script>


    <style type="text/css">
        .error {
            color: red;
            background: none;
            padding-top: 0;
            width: 345px;
            margin-top: 0px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#addForm").validate({
                rules: {
                    name: "required",
                    roleKey: "required",
                    groupId: "required"

                },
                messages: {
                    name: "请输入角色构名",
                    roleKey: "请输入角色key",
                    groupId: "请选择组织机构"
                }

            });
        });
    </script>
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">我的CRM</a></li>
        <li><a href="#">添加角色</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">角色添加</a></li>
            </ul>
        </div>
        <div class="line">
            <div id="tab2" class="tabson">
                <div class="toolbar1"></div>
                <div class="toolbar1"></div>
                <form id="addForm" action="${pageContext.servletContext.contextPath }/background/role/add.html"
                      method="post">
                    <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF" class="tablelist_left">
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>角色名称：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <input name="name" id="name" class="dfinput"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>角色KEY：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input name="roleKey" id="roleKey" class="dfinput"/>
                                    </div>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>组织机构：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input name="groupId" id="groupId" class="easyui-combotree dfinput"
                                               data-options="url:'${pageContext.servletContext.contextPath }/background/group/tree.html',method:'get'"
                                        />
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>是否禁用：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <input name="enable" type="radio" value="2"/>禁用
                                    <input name="enable" type="radio" value="1" checked/>启用
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>描述：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input name="description" class="dfinput"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td height="54" bgcolor="#FFFFFF">
                                <div align="center"></div>
                            </td>
                            <td height="54" bgcolor="#FFFFFF"><input type="submit" value="　保　存　" class="scbtn"/>
                                <input onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt"
                                       type="button" value="　返　回　" class="scbtn"/></td>
                        </tr>
                    </table>
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
