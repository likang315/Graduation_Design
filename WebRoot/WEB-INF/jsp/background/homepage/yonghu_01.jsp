<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
    <link href="css/select.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/jquery-3.1.1.js"></script>
    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>


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
    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>

        <li><a href="#">我的CRM</a></li>
        <li><a href="#">我的客户</a></li>
    </ul>


</div>

<div class="formbody">


    <div id="usual1" class="usual">
        <div class="toolbar1">

            <ul class="toolbar">
                <li class="click"><span><a href="wodekehu_02.html"><img src="images/t01.png"/></a></span><a
                        href="yonghu_03.html">新增用户</a></li>
                <li class="click"><span><img src="images/t01.png"/></span>分配角色</li>
                <li class="click"><span><img src="images/t02.png"/></span>辑辑</li>
                <li><span><img src="images/t03.png"/></span>删除</li>

            </ul>


        </div>


        <div class="itab_nav">
            <ul>
                <li><a href="#tab1" class="selected">客户列表</a></li>


            </ul>
        </div>

        <div class="line">


            <div class="formtext">


                <ul class="seachform">
                    <li><label>客户名称</label>
                        <div class="vocation">
                            <input name="input2" type="text" class="scinput"/>
                        </div>
                    </li>

                    <li><label>公司联系人</label>
                        <div class="vocation">
                            <input name="input3" type="text" class="scinput"/>
                        </div>
                    </li>
                    <li><label>&nbsp;</label><input name="" type="button" class="scbtn" value="查询"/></li>

                </ul>
            </div>


            <div id="tab2" class="tabson">


                <div class="toolbar1"></div>


                <table class="tablelist">
                    <thead>
                    <tr>
                        <th width="12%">用户名</th>
                        <th width="15%">所属角色</th>
                        <th width="16%">昵称</th>
                        <th width="14%">注册时间</th>
                        <th width="13%">最后一次登录时间</th>
                        <th width="14%">等级</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>王金平</td>
                        <td>王金平</td>
                        <td>2013-09-09 15:05</td>
                        <td>admin</td>
                        <td>江苏南京</td>
                        <td>服装批发</td>
                    </tr>

                    <tr>
                        <td>王金平</td>
                        <td>王金平</td>
                        <td>2013-09-09 15:05</td>
                        <td><a href="http://www.baidu.com" target="_blank">chinafaba</a></td>
                        <td>山东济南</td>
                        <td>服装批发</td>
                    </tr>

                    <tr>
                        <td>王金平</td>
                        <td>王金平</td>
                        <td>2013-09-09 15:05</td>
                        <td>user</td>
                        <td>江苏无锡</td>
                        <td>服装批发</td>
                    </tr>

                    <tr>
                        <td>王金平</td>
                        <td>王金平</td>
                        <td>2013-09-09 15:05</td>
                        <td>admin</td>
                        <td>北京市</td>
                        <td>服装批发</td>
                    </tr>

                    <tr>
                        <td>王金平</td>
                        <td>王金平</td>
                        <td>2013-09-09 15:05</td>
                        <td><a href="http://www.baidu.com" target="_blank">chinafaba</a></td>
                        <td>山东济南</td>
                        <td>服装批发</td>
                    </tr>

                    </tbody>
                </table>


                <div class="pagin">
                    <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
                    <ul class="paginList">
                        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
                        <li class="paginItem"><a href="javascript:;">1</a></li>
                        <li class="paginItem current"><a href="javascript:;">2</a></li>
                        <li class="paginItem"><a href="javascript:;">3</a></li>
                        <li class="paginItem"><a href="javascript:;">4</a></li>
                        <li class="paginItem"><a href="javascript:;">5</a></li>
                        <li class="paginItem more"><a href="javascript:;">...</a></li>
                        <li class="paginItem"><a href="javascript:;">10</a></li>
                        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
                    </ul>
                </div>

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
