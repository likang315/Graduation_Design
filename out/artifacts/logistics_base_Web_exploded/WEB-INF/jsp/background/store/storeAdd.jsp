<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>门店管理-门店信息</title>
    <%@include file="/common/common-css.jsp" %>
    <%@include file="/common/common-js.jsp" %>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet"
          type="text/css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>


    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
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
</head>
<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">门店管理</a></li>
        <li><a href="#">新增门店</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" id="sigStore" class="selected">新增门店信息</a></li>
                <!-- <li><a href="#tab1" id="importTag">门店批量维护</a></li> -->
            </ul>
        </div>
        <div class="line">
            <div id="tab2" class="tabson">
                <div class="toolbar1"></div>
                <div class="toolbar1"></div>
                <form id="addForm" action="${pageContext.servletContext.contextPath }/background/Store/add.html"
                      method="post" enctype="multipart/form-data">
                    <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF" class="tablelist_left">
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>门店名称：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <input name="store_name" id="store_name" class="dfinput" onblur="check()"/>
                                    <label id="tipStore_name"></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>门店地址：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input name="store_address" id="store_address" class="dfinput"/>
                                        <label id="tipStore_address"></label>
                                    </div>
                                </div>
                            </td>
                        </tr>
<%--                        <tr>--%>
<%--                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">--%>
<%--                                <div>门头照片：</div>--%>
<%--                            </td>--%>
<%--                            <td height="38" bgcolor="#FFFFFF" style="margin-left:20px;">--%>
<%--                                <div align="left">--%>
<%--                                    <div align="left">--%>
<%--                                        <input type="file" name="file" id="file" class="dfinput" onblur="check()"/>--%>
<%--                                        <label id="tipFile"></label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>店长姓名：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input name="store_shopowner_name" id="store_shopowner_name" class="dfinput"
                                               onblur="check()"/>
                                        <label id="tipStore_shopowner_name"></label>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>店长电话：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input name="store_shopowner_phone" id="store_shopowner_phone" class="dfinput"
                                               onblur="check()"/>
                                        <label id="tipStore_shopowner_phone"></label>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>渠道编码：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <div align="left">
                                        <input name="channel_code" id="channel_code" class="dfinput" onblur="check()"/>
                                        <label id="tipChannel_code"></label>
                                    </div>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>门店归属：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <select id="vendor_id" name="vendor_id" style="opacity:1">
                                        <c:forEach items="${vendorList }" var="v">
                                            <option value='${v.id }'>${v.vendor_name }</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>门店类型：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <select id="type" name="type" style="opacity:1">
                                        <option value='0'>直营</option>
                                        <option value='1'>现金（社会门店）</option>
                                    </select>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td height="54" bgcolor="#FFFFFF">
                                <div align="center"></div>
                            </td>
                            <td height="54" bgcolor="#FFFFFF"><input type="button" id="sub1" value="　保　存　"/>
                                <input onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt"
                                       type="button" value="　返　回　"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <form action="${pageContext.servletContext.contextPath}/background/Store/add.html" method="post"
              enctype="multipart/form-data" id="addForm2">
            <div id="divStoreId" style="display:none ;">
                <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF" class="tablelist_left">
                    <tr>
                        <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                            <div>选择文件：</div>
                        </td>
                        <td height="38" bgcolor="#FFFFFF">
                            <div align="left">
                                <div align="left">
                                    <input type="file" name="file" id="fileExcel" class="dfinput"
                                           onchange="checkFile()"/>
                                    <label id="tipFileExcel"></label>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td height="54" bgcolor="#FFFFFF">
                            <div align="center"></div>
                        </td>
                        <td height="54" bgcolor="#FFFFFF"><input type="button" id="sub2" value="确认维护"/>
                            <input onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt1"
                                   type="button" value="　返　回　"/>
                            <input type="button" value="下载模板"
                                   onclick="javascript:window.location.href=encodeURI(encodeURI('${pageContext.servletContext.contextPath }/background/report/downDomeFile.html?fileName=门店信息导入模板.xlsx'))"/>
                        </td>
                    </tr>
                </table>
            </div>
        </form>

    </div>
    <script type="text/javascript">
        $("#usual1 ul").idTabs();
    </script>

    <script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');

    </script>

</div>


</body>
<script>

    // 检查门店信息规范
    function check() {
        //门店的名称
        var store_name = $("#store_name").val();
        //门店的地址
        var store_address = $("#store_address").val()
        //门店的经度
        var store_longitude = $("#store_longitude").val()
        //门店的纬度
        var store_latitude = $("#store_latitude").val()
        //门店的照片（门头）
        var file = $("#file").val();
        //门店店长电话
        var store_shopowner_phone = $("#store_shopowner_phone").val();
        //门店店长姓名
        var store_shopowner_name = $("#store_shopowner_name").val();
        //门店渠道编码
        var channel_code = $("#channel_code").val();

        if (store_name == null || store_name == "") {
            $("#tipStore_name").html("<font color='red'>门店名称不能为空</font>")
            return false;
        } else if (store_address == null || store_address == "") {
            $("#tipStore_name").empty();
            $("#tipStore_address").html("<font color='red'>门店地址不能为空</font>")
            return false;
        }/* else if(store_longitude == null || store_longitude == ""){
			$("#tipStore_name").empty();
			$("#tipStore_address").empty();
			$("#tipStore_longitude").html("<font color='red'>门店经度不能为空</font>")
			return false;
		}else if(store_latitude == null || store_latitude == ""){
			$("#tipStore_name").empty();
			$("#tipStore_address").empty();
			$("#tipStore_longitude").empty();
			$("#tipStore_latitude").html("<font color='red'>门店纬度不能为空</font>")
			return false;
		} */ else if (store_shopowner_phone == null || store_shopowner_phone == "") {
            $("#tipStore_name").empty();
            $("#tipStore_address").empty();
            $("#tipStore_longitude").empty();
            $("#tipStore_latitude").empty();
            $("#tipStore_shopowner_phone").empty();
            $("#tipStore_shopowner_phone").html("<font color='red'>店长电话不能为空</font>")
            return false;
        } else if (!(/^1[3|4|5|7|8][0-9]{9}$/.test(store_shopowner_phone))) {
            $("#tipStore_name").empty();
            $("#tipStore_address").empty();
            $("#tipStore_longitude").empty();
            $("#tipStore_latitude").empty();
            $("#tipStore_shopowner_phone").empty();
            $("#tipStore_shopowner_phone").html("<font color='red'>电话号码不符合规范</font>")
            return false;
        } else if (store_shopowner_name == null || store_shopowner_name == "") {
            console.log(11);
            $("#tipStore_name").empty();
            $("#tipStore_address").empty();
            $("#tipStore_longitude").empty();
            $("#tipStore_latitude").empty();
            $("#tipStore_shopowner_phone").empty();
            $("#tipStore_shopowner_name").html("<font color='red'>店长姓名不能为空</font>")
            return false;
        } else if (channel_code == null || channel_code == "") {
            $("#tipStore_name").empty();
            $("#tipStore_address").empty();
            $("#tipStore_longitude").empty();
            $("#tipStore_latitude").empty();
            $("#tipStore_shopowner_phone").empty();
            $("#tipStore_shopowner_name").empty();
            $("#tipChannel_code").html("<font color='red'>门店渠道编码不能为空</font>")
            return false;
        } else {
            $("#tipStore_name").empty();
            $("#tipStore_address").empty();
            $("#tipStore_longitude").empty();
            $("#tipStore_latitude").empty();
            $("#tipStore_shopowner_phone").empty();
            $("#tipStore_shopowner_name").empty();
            $("#tipChannel_code").empty();
            return true;
        }
    }

    $("#sub1").click(function () {
        if (check()) {
            $("#addForm").submit();
        }
    })

    $("#store_shopowner_phone").blur(function () {
        var store_shopowner_phone = $("#store_shopowner_phone").val();
        if (store_shopowner_phone != null && store_shopowner_phone != "") {
            checkHasVal(store_shopowner_phone);
        }
    })

    $("#store_address").blur(function () {
        var store_address = $("#store_address").val();
        $.post("${pageContext.request.contextPath}/background/Store/checkAddress.html", {"store_address": store_address}, function (result) {
            if (result.state != "ok") {
                $("#store_address").focus();
                $("#tipStore_address").empty();
                $("#tipStore_shopowner_phone").empty();
                $("#tipStore_address").html("<font color='red'>" + result.message + "</font>");
            } else {
                $("#tipStore_address").empty();
            }
        });
    })

    function checkFile() {
        var name = $("#fileExcel").val();
        var yz = /^.*\.(?:xls|xlsx)$/;
        if (yz.test(name)) {
            $("#tipFileExcel").empty();
            return true;
        } else {
            $("#tipFileExcel").html("<font color='red'>您选择的文件不符合规范!</font>")
            return false;
        }
    }

    $("#sub2").click(function () {
        if (checkFile()) {
            $("#addForm2").submit();
        }
    })

    function checkHasVal(e) {
        $.post("${pageContext.request.contextPath}/background/Store/findPhone.html", {"store_shopowner_phone": e}, function (result) {
            if (result.state != "ok") {
                $("#store_shopowner_phone").focus();
                $("#tipStore_shopowner_name").empty();
                $("#tipStore_shopowner_phone").html("<font color='red'>店长电话重复</font>");
            }
        });
    }

</script>


<script>
    $("#sigStore").click(function () {
        $("#divStoreId").hide();
        $("#tab2").show();
    })

    $("#importTag").click(function () {
        $("#divStoreId").show();
        $("#tab2").hide();
    })

</script>
</html>
