<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<%@include file="/common/common-css.jsp" %>
<%@include file="/common/common-js.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>物流订单列表-修改订单信息</title>
    <link href="${pageContext.servletContext.contextPath }/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath }/css/select.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link href="${pageContext.servletContext.contextPath }/css/bootstrap.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/bootstrap.js"></script>
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

        .fancyTable td, .fancyTable th {
            border: 1px solid #ccc;
        }
    </style>
</head>

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">订单管理</a></li>
        <li><a href="#">修改订单</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">修改订单</a></li>
            </ul>
        </div>
        <div class="line">
            <div id="tab2" class="tabson">
                <div class="toolbar1"></div>
                <form id="addForm"
                      action="${pageContext.servletContext.contextPath }/background/order/updateLogistics.html"
                      method="post" enctype="multipart/form-data">
                    <input type="hidden" value="${logisticslist.id}" name="id"/>
                    <input type="hidden" id="materialId" name="materialContent"/>
                    <input type="hidden" id="materialNumId" name="materialNumber"/>
                    <table border="0" cellpadding="0" cellspacing="0" bgcolor="#66CCFF" class="tablelist_left">
                        <tr>
                            <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                                <div>订单号：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <input name="id" id="id" class="dfinput" value="${logisticslist.id}" readonly/>
                                    <label id="tipChannel_code"><font color="red">订单号不支持修改</font></label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" height="40" align="center" bgcolor="#F0F5F7">
                                <div>物资名称及数量：</div>
                            </td>
                            <td height="38" bgcolor="#FFFFFF">
                                <div align="left">
                                    <input type="button" id="updatewuzi" class="btn btn-success" value="点击修改物资"/>
                                </div>
            </div>
            </td>
            </tr>


            <tr>
                <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                    <div>收货人：</div>
                </td>
                <td height="38" bgcolor="#FFFFFF">
                    <div align="left">
                        <div align="left">
                            <input name="consignee" class=" dfinput"
                                   value="${logisticslist.consignee}"
                            />
                        </div>
                    </div>
                </td>
            </tr>

            <tr>
                <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                    <div>收货人电话：</div>
                </td>
                <td height="38" bgcolor="#FFFFFF">
                    <div align="left">
                        <input name="phone" id="phone" class="dfinput" value="${logisticslist.phone}"
                               onblur="checkForm()"/>
                        <label id="tipPhone"></label>
                    </div>
                </td>
            </tr>

            <tr>
                <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                    <div>快递员姓名：</div>
                </td>
                <td height="38" bgcolor="#FFFFFF">
                    <div align="left">
                        <input name="courierName" id="courierName" class="dfinput"
                               value="${logisticslist.courierName}"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                    <div>快递员电话：</div>
                </td>
                <td height="38" bgcolor="#FFFFFF">
                    <div align="left">
                        <input name="courierPhone" id="courierPhone" class="dfinput"
                               value="${logisticslist.courierPhone}" onblur="checkForm()"/>
                        <label id="tipCourierPhone"></label>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="10%" height="38" align="center" bgcolor="#F0F5F7">
                    <div>收货具体地址：</div>
                </td>
                <td height="38" bgcolor="#FFFFFF">
                    <div align="left">
                        <input name="address" id="address" class="dfinput" value="${logisticslist.address}" readonly/>
                        <label id="tipChannel_code"><font color="red">收货具体地址不支持修改</font></label>
                    </div>
                </td>
            </tr>


            <tr>
                <td height="54" bgcolor="#FFFFFF">
                    <div align="center"></div>
                </td>
                <td height="54" bgcolor="#FFFFFF"><input type="button" id="subForm" value="　修　改　"
                                                         class="btn btn-success"/>
                    <input onclick="javascript:window.location.href='javascript:history.go(-1)'" id="backBt"
                           type="button" value="　返　回　" class="btn btn-success"/></td>
            </tr>
            </table>
            </form>

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
                            <div class="procedure">
                                <div class="tableDivTow">
                                    <div>
                                        <table class="fancyTable" id="myTable02" width="100%" cellpadding="0"
                                               cellspacing="0">
                                            <thead>
                                            <tr>
                                                <th>物资名称</th>
                                                <th>数量</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="mytbody">
                                            <c:forEach items="${ls }" var="c">
                                                <tr id="${c.materialId }">
                                                    <td>${c.materialC} </td>
                                                    <td>${c.materialN}</td>
                                                    <td>
                                                        <a onclick="javascript:mydelete('${c.materialId}','${c.materialC}','${c.materialN}')">删除</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>


                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="clear"></div>
                                </div>

                                <div class="labelInformation">物流信息</div>
                                <div class="tableDivFillIn">
                                    <input type="text" id="store" name="store" style="display:none;" value="${store }"/>
                                    <table border="1" bordercolor="#ccc" width="100%">
                                        <tr>
                                            <td>物资名称:</td>
                                            <td>
                                                <select id="material" name="material" style="opacity: 1;">
                                                    <c:forEach items="${material}" var="m">
                                                        <option value="${m.id }">${m.material_name }</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>物品数量:</td>
                                            <td><input type="number" min="0" id="materialNumber"
                                                       style="width:30%; height:27px; border-radius:3px; border:1px solid #CCC; padding-left: 5px;"/>
                                            </td>
                                            <label id="tip"></label>
                                        </tr>
                                    </table>
                                    <div align="center"
                                         style="font-size:12px;height:15px; color:red; text-shadow:5px 2px 6px #666;">
                                        <label id="mesg"></label>
                                    </div>
                                    <div class="addToDiv">
                                        <input type="button" id="ok" value="添加" class="btn btn-success btn-xs"
                                               style="width:100px; margin-top:5px;"/>
                                    </div>

                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" id="closeButton">关闭
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>
<script type="text/javascript">

    var chooseMaterialStr = '${chooseMaterial}';
    var stringMaterialNumStr = '${chooseMaterialNUM}';
    var stringMaterial = chooseMaterialStr.split(',');
    var stringMaterialNum = stringMaterialNumStr.split(',');
    console.log(stringMaterial)

    //给form表单中隐藏域初始化
    $("#materialId").val(stringMaterial)
    $("#materialNumId").val(stringMaterialNum);


    $("#updatewuzi").click(function () {
        $("#modalmb").show();
        $("#modalnr").show();
    })


    $("#closeButton").click(function () {
        $("#materialId").val(stringMaterial);
        $("#materialNumId").val(stringMaterialNum);
        $("#modalmb").hide();
        $("#modalnr").hide();
    });
    $(document).ready(function () {
        var divValue;
        var inputValue;
        $(".fal").click(function () {
            divValue = $(this).text();
            $(this).hide();
            $(this).next().show();
        })
        $(".adf").blur(function (a) {
            inputValue = $(this).val();
            if (divValue != inputValue) {
                divValue = inputValue;
                alert(divValue);
            }
            $(this).hide();
            $(this).prev().show();

        })
    })

    //当光标进入文本框,错误消息提示消失
    $("#materialNumber").focus(function () {
        $("#mesg").html("");
    });

    //点击添加后操作
    $("#ok").click(function () {
        var num = Number($("#materialNumber").val());//物资数量
        if (num <= 0) {
            $("#mesg").html("请输入合法的物资数量!");
            $("#mesg").fadeIn(500);
            $("#mesg").fadeOut(2000);
            return false;
        }
        var material = $("#material").val();//物资id
        var materialName = $("#material :selected").html();//物资名称
        //查询当前数组中是否有当前物资信息，如果有返回true，没有返回false
        if (!checkExist(materialName)) {
            //将添加的新数据加到数组中
            mymaterial(materialName, num, '0');
            //将数据拼接到table
            var mytbody = "<tr id = '" + material + "'>"
                + "<td>" + materialName + "</td>"
                + "<td>" + num + "</td>"
                + "<td><a onclick='javascript:mydelete(\"" + material + "\",\"" + materialName + "\",\"" + num + "\")'>删除</a></td>"
                + "</tr>";
            $("#mytbody").append(mytbody);
        } else {
            $("#tip").html("<font color='red'>当前物资信息已存在！</font>")
            $("#tip").fadeIn(500);
            $("#tip").fadeOut(2000);
        }
    });


    //删除物资信息
    function mydelete(materialId, materialC, materialN) {
        //当物资的id不为空的时候
        if (materialId != null && materialId != "") {
            //从table上删除
            $("#" + materialId).remove();
            //从数组中删除
            mymaterial(materialC, materialN, '1');
        } else {
            $("#tip").html("<font color='red'>当前物资信息不在库，请维护后进行操作！</font>")
            $("#tip").fadeIn(500);
            $("#tip").fadeOut(2000);
        }

    }


    function mymaterial(materials, num, states) {
        if (states == "0") {
            //增加物资
            stringMaterial.push(materials);
            //增加对应物资数量
            stringMaterialNum.push(num);

        }
        if (states == "1") {
            //删除物资信息
            this.deleteArrayParam(materials)
        }
    }


    //根据传入的参数删除数组中
    function deleteArrayParam(e) {
        console.log(e);
        console.log(stringMaterial);
        console.log(stringMaterial.length);
        var stringMaterial1 = new Array();
        var stringMaterialNum1 = new Array();
        //循环遍历数组
        for (var i = 0; i < stringMaterial.length; i++) {
            if (stringMaterial[i] != null && stringMaterial[i] != "") {
                if (e != stringMaterial[i]) {
                    //删除后物资
                    stringMaterial1.push(stringMaterial[i]);
                    //删除后物资数量
                    stringMaterialNum1.push(stringMaterialNum[i]);
                }
            }

        }
        //重新给物资信息赋值
        stringMaterial = stringMaterial1;
        stringMaterialNum = stringMaterialNum1;
        $("#materialId").val(stringMaterial);
        $("#materialNumId").val(stringMaterialNum);
    }

    //查询当前传入的物资信息在数组中是否存在
    function checkExist(e) {
        //循环遍历数组
        for (var i = 0; i < stringMaterial.length; i++) {
            //当传入的数据存在的时候返回true
            if (e == stringMaterial[i]) {
                return true;
            }
        }
        return false;
    }

    // from 表单的验证
    function checkForm() {
        var flag = true;
        //电话号码正则
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/
        //获取填写的收货人电话号码
        var phone = $("#phone").val();
        var courierPhone = $("#courierPhone").val();
        if (!myreg.test(phone)) {
            $("#tipPhone").html("<font color='red'>收货人电话号码有误!</font>");
            $("#tipPhone").fadeIn(500);
            $("#tipPhone").fadeOut(2000);
            flag = false;
        }
        if (!myreg.test(courierPhone)) {
            $("#tipCourierPhone").html("<font color='red'>快递员电话号码有误!</font>");
            $("#tipCourierPhone").fadeIn(500);
            $("#tipCourierPhone").fadeOut(2000);
            flag = false;
        } else {
            //验证快递员信息是否在库
            $.ajax({
                url: "checkcourierPhone.html",
                type: "POST",
                dataType: "JSON",
                data: {"courierPhone": courierPhone},
                success: function (data) {
                    if (data.info != "ok") {
                        $("#tipCourierPhone").html("<font color='red'>" + data.info + "</font>");
                        $("#tipCourierPhone").fadeIn(500);
                        $("#tipCourierPhone").fadeOut(2000);
                        flag = false;
                    }
                }

            });
        }
        return flag;
    }

    $("#subForm").click(function () {
        if (checkForm()) {
            //console.log(checkForm());
            $("#addForm").submit();
        }
    })


</script>		
