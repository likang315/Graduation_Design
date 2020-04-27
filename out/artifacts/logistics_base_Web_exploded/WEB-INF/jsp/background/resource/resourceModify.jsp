<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>资源列表管理-修改资源</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
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
            $("#editForm").validate({
                rules: {
                    name: "required",
                    parentId: "required",
                    resKey: "required",
                    resUrl: "required",
                    level: {
                        required: true,
                        number: true
                    }
                },
                messages: {
                    name: "请输入组织机构名",
                    parentId: "请选择父节点",
                    resKey: "请输入资源key",
                    resUrl: "清输入资源url",
                    level: {
                        required: "请输入优先级",
                        number: "请输入数字"
                    }
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
        <li><a href="#">资源列表管理</a></li>
        <li><a href="#">修改资源</a></li>
    </ul>


</div>

<div class="formbody">


    <div id="usual1" class="usual">

        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">资源列表</a></li>


            </ul>
        </div>

        <div class="line">
            <div id="tab2" class="tabson">


                <div class="toolbar1"></div>


                <div class="toolbar1"></div>
                <form id="editForm" action="${pageContext.servletContext.contextPath }/background/resources/edit.html"
                      method="post">
                    <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF" class="tablelist_left">
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>资源名称：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <input name="name" class="dfinput" id="name" value="${resources.name}"/>
                                    <input type="hidden" name="id" value="${resources.id}"/>
                                    <input type="hidden" name="parentId" value="${resources.parentId}"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>资源KEY：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input name="resKey" class="dfinput" id="resKey" value="${resources.resKey}"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>资源URL：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <input name="resUrl" class="dfinput" id="resUrl" value="${resources.resUrl}"/>
                                </div>
                            </td>


                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>资源类型：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input type="radio" value="0" name="type"
                                               <c:if test="${resources.type==0}">checked</c:if>/>目录　　
                                        <input type="radio" value="1" name="type"
                                               <c:if test="${resources.type==1}">checked</c:if>/>菜单　　
                                        <input type="radio" value="2" name="type"
                                               <c:if test="${resources.type==2}">checked</c:if>/>按扭
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>优先级：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input name="level" class="dfinput" id="level" value="${resources.level}"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>资源描述：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <input name="description" class="dfinput" value="${resources.description}"/>
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
