<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%@include file="/common/common-css.jsp" %>
<%@include file="/common/common-js.jsp" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery.twbsPagination.min.js"></script>

<!-- <script type="text/javascript">
var info = '${info}';
var deleteinfo = '${deleteinfo}';
if(info != null && info != ""){
	alert(info);
}
if(deleteinfo != null && deleteinfo != ""){
	alert(deleteinfo);
}
</script> -->
</head>
<body>
<div style="background:#000;position:fixed;top:0;left:0; width:100%; height:100%; opacity:0.2; z-index:9999;display:none; " id="modalmb"></div>
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
<div class="place">
   	<span>位置：</span>
	<ul class="placeul">
		<li><a href="#">首页</a></li>
		<li><a href="#">物流管理</a></li>
		<li><a href="#">快递公司列表</a></li>
	</ul>
</div>
<div class="formbody">
	<div id="usual1" class="usual"> 
		<div class="toolbar1">
			<ul class="toolbar">
				<a href="javascript:addExpress();">
					<li class="click">
						<span>
							<img src="${pageContext.request.contextPath}/images/t01.png" />
						</span>新增
					</li>
				</a>
				<a href="javascript:deleteAll();">
      					<li>
      						<span>
       						<img src="${pageContext.request.contextPath}/images/t03.png" />
       					</span>删除
       				</li>
				</a>
			</ul>
		</div>
		<div class="itab_nav">
			<ul> 
				<li><a href="#tab1" class="selected">快递公司列表</a></li>
			</ul>
   		</div> 
		<div class="line">
			<div id="tab1" class="tabson">
				<div class="toolbar1"></div>
				<form id="fenye" name="fenye" action="${pageContext.servletContext.contextPath }/background/role/query.html" method="post">
					<table class="tablelist">
						<thead>
							<tr>
								<th width="2%">
									<input id="chose" type="checkbox" name="checkbox" onclick="selectAllCheckBox()" />
								</th>
								<th width="15%">快递公司</th>
								<th width="10%">公司地址</th>
								<th width="8%">创建时间</th>
								<th width="6%">创建人</th>
								<!-- <th width="12%">操作</th> -->
							</tr>
						</thead>
						<tbody>
							<c:forEach var="express" items="${companyList}">
								<tr>
									<td><input type="checkbox" name="check" value="${express.id}"/></td>
									<td>${express.company_name}</td>
									<td>${express.company_address}</td>
									<td><fmt:formatDate value="${express.create_time}" pattern="yyyy-MM-dd  HH:mm" /></td>
									<td>${express.create_phone}</td>
									<%-- <td>
									   <a href="javascript:void(0);" onclick="permissio('${express.id}')">修改</a>&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="modifyRole('${store.id}')">编辑</a>
									</td> --%> 
								</tr>
							</c:forEach>
						</tbody>
					</table>
			    </form>
			</div> 
		<div>
		<div class="pagination pull-right">
	    	<ul id="page"></ul>
		</div>
		<div class="statistics pull-left">
			共<b style="color:#4dc8fc"> ${page.totalCount} </b>条记录，当前显示<b style="color:#4dc8fc"> ${page.pageNo}/${page.totalPages } </b>页
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
var info = '${info}';
var deleteinfo = '${deleteinfo}';
if(info != null && info != ""){
	$("#modalmb").show();
	$("#modalnr").show();
	$("#hintContext").html(info);
}
if(deleteinfo != null && deleteinfo != ""){
	$("#modalmb").show();
	$("#modalnr").show();
	$("#hintContext").html(deleteinfo);
}
$("#closeFork").click(function() {
	$("#modalmb").hide();
	$("#modalnr").hide();
});
$("#closeButton").click(function() {
	$("#modalmb").hide();
	$("#modalnr").hide();
});
//快递公司信息批量维护
function addExpress(){
	window.location.href="${pageContext.request.contextPath}/background/Express/addUI.html";
}
//删除选中的门店信息
function deleteAll(){
	var a=confirm("确认删除当前选中的数据？");
	if(a){
	var id = "";
	var a = $("[name='check']");
	for(var i = 0; i<a.length; i++){
		if(a[i].checked == true){
			id = id + a[i].value + ",";
		}
	}
	window.location.href="${pageContext.request.contextPath}/background/Express/deleteExpress.html?id="+id;
	}
}
//分页控件
$("#page").twbsPagination({
	totalPages:"${page.totalPages}",//总页数
			visiblePages:5,//导航页个数
		first:'首页',
		last:'末页',
		prev:'上一页',
		next:'下一页',
		href:"?pageNow={{number}}"
});
</script>
</html>
