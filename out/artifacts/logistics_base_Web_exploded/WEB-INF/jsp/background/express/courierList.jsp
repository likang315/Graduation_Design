<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>快递员管理</title>
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
	var deleteInfo = '${deleteInfo}';
	var modifyInfo = '${modifyInfo}';
	if(info != null && info != ""){
		alert(info);
	}
	if(deleteInfo != null && deleteInfo != ""){
		alert(deleteInfo);
	}
	if(modifyInfo != null && modifyInfo != ""){
		alert(modifyInfo);
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
			<li><a href="#">快递员列表</a></li>
	    </ul>
    </div>
    <div class="formbody">
    	<div id="usual1" class="usual"> 
    		<div class="toolbar1">
   				<ul class="toolbar">
   					<a href="javascript:addCourier();">
	   					<li class="click">
	     					<span>
	     						<img src="${pageContext.request.contextPath}/images/t01.png" />
	     					</span>新增
	     				</li>
     				</a>
       				<a href="javascript:deleteAll();">
	       				<li class="click">
	       					<span>
	        					<img src="${pageContext.request.contextPath}/images/t03.png" />
	        				</span>删除
	        			</li>
       				</a>
      			</ul>
  			</div>
    		<div class="itab_nav">
  				<ul> 
    				<li><a href="#tab1" class="selected">快递员列表</a></li>
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
							        <th width="15%">快递员姓名</th>
									<th width="10%">快递员电话</th>
									<th width="10%">创建时间</th>
							        <th width="8%">快递员归属</th>
							        <th width="6%">快递门店地址</th>
							        <th width="12%">操作</th>
        						</tr>
       						</thead>
        					<tbody>
						        <c:forEach var="courier" items="${courierList}">
							        <tr>
								        <td><input type="checkbox" name="check" value="${courier.id}"/></td>
								        <td>${courier.real_name}</td>
								        <td>${courier.accountName}</td>
								        <td><fmt:formatDate value="${courier.createTime}" pattern="yyyy-MM-dd HH:mm" /> </td>
								        <td>${courier.company_name}</td>
								        <td>${courier.company_address}</td>
								        <td>
								           <a href="javascript:void(0);" onclick="permissio('${courier.id}')">修改</a>
								        </td> 
							        </tr>
						        </c:forEach>
        					</tbody>
    					</table>
	    				<div class="pagination pull-right">
			    			<ul id="page"></ul>
						</div>
						<c:if test="${page.totalPages != null}">
							<div class="statistics pull-left">
							  	 共<b style="color:#4dc8fc"> ${page.totalCount} </b>条记录，当前显示<b style="color:#4dc8fc"> ${page.pageNo}/${page.totalPages } </b>页
							</div>
						</c:if>
					</form>
   				</div> 
    		<div>
    	</div> 
	</div>
</body>

<script type="text/javascript">
		var info = '${info}';
		var deleteInfo = '${deleteInfo}';
		var modifyInfo = '${modifyInfo}';
		
		if(info != null && info != ""){
			$("#modalmb").show();
			$("#modalnr").show();
			$("#hintContext").html(info);
		}
		if(modifyInfo != null && modifyInfo != ""){
			$("#modalmb").show();
			$("#modalnr").show();
			$("#hintContext").html(modifyInfo);
		}
		if(deleteInfo != null && deleteInfo != ""){
			$("#modalmb").show();
			$("#modalnr").show();
			$("#hintContext").html(deleteInfo);
		}
		$("#closeFork").click(function() {
            $("#modalmb").hide();
			$("#modalnr").hide();
        });
		$("#closeButton").click(function() {
            $("#modalmb").hide();
			$("#modalnr").hide();
        });
	</script>



<script>
//快递员信息批量维护
function addCourier(){
	window.location.href="${pageContext.request.contextPath}/background/Express/addCourierUI.html";
}
//删除选中的快递员信息
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
	window.location.href="${pageContext.request.contextPath}/background/Express/deleteCourier.html?id="+id;
	}
}

//修改该条数据
function permissio(id){
	window.location.href = "${pageContext.servletContext.contextPath }/background/Express/permissioCourier.html?id="+id;
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
