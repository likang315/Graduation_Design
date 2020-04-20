<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!--HomePage center.html 框架布置格局-->
<style type="text/css">
    <!--
    body {
        margin-left: 0px;
        margin-top: 0px;
        margin-right: 0px;
        margin-bottom: 0px;
        overflow: hidden;
    }

    -->
</style>


<script>
    function switchSysBar() {
        var locate = location.href.replace('center.html', '');
        var ssrc = document.all("img1").src.replace(locate, '');
        if (ssrc == "images/main_18.gif") {
            document.all("img1").src = "images/main_18_1.gif";
            document.all("frmTitle").style.display = "none"
        } else {
            document.all("img1").src = "images/main_18.gif";
            document.all("frmTitle").style.display = ""
        }
    }
</script>


<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
    <tr>
        <td width="185" id=frmTitle noWrap name="fmTitle" align="center" valign="top">
            <iframe name="leftFrame" height="100%" width="185" src="${pageContext.request.contextPath}/left.html"
                    border="0" frameborder="0" scrolling="no">
            </iframe>
        </td>
        <td width="8" valign="middle" bgcolor="#edf6fa">
            <span class="navPoint" onclick=switchSysBar() style="cursor:hand;"><img
                    src="${pageContext.request.contextPath}/images/main_18.gif" name="img1" width=8 height=52
                    id=img1></span>
        </td>
        <td align="center" valign="top">
            <iframe name="rightFrame" height="100%" width="100%" border="0" frameborder="0"
                    src="${pageContext.request.contextPath}/right.html"></iframe>
        </td>
        <td width="4" align="center" valign="top"></td>
    </tr>
</table>


