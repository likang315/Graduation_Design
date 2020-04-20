<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@include file="/common/common-app-head.jsp" %>
<title></title>

<script type="text/javascript">
var imgInfo = '${imgInfo}';
if(imgInfo != null && imgInfo != "") {
	alert(imgInfo);
}

</script>
</head>
<body>
<script type="text/javascript">
$(function(){
	  API.checkLogin();
})
	var user = API.getUserInfo();
	var account = user.accountName;
	location.href = "${pageContext.request.contextPath}/app/coordinate/CourierAllList.html?accountName="+account+"&&state=0&&jiazai=5";	
</script>
</body>
</html>