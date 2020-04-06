<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${pageContext.servletContext.contextPath }/css/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.servletContext.contextPath }/css/select.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.servletContext.contextPath }/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />


<style type="text/css">
table.list {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	width: 100%;
}

table.list th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: rgb(102, 201, 243);
}

table.list td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

.t_head {
	display: inline-block;
	float: left;
	height: 20px;
	line-height: 20px;
	margin: 0 8px;
}

.t_head1 {
	display: inline-block;
	height: 36px;
	line-height: 36px;
	font-size: 18px;
}

.download:hover {
	cursor: pointer;
}

#mysub, #download, #download1 {
	cursor: pointer;
	border: solid 1px #999;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	background-color: white;
	height: 30px;
	width: 80px;
}

#mytable {
	border: 1px solid #666666;
	width: 100%;
}

#searchdates {
	cursor: pointer;
	border: 1px solid #87CEFF;
	opacity: 1;
}

td {
	text-align: center;
}

#mytable td {
	text-align: left;
}
</style>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/js/jquery.idTabs.min.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/js/API.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/js/bootstrap.js"></script>

</head>

<body>


	<div id="bigblack"
		style="width: 100%; background: #000; height: 100%; position: fixed; top: 0px; left: 0px; opacity: 0.7; display: none"></div>
	<div id="smallblack"
		style="position: fixed; top: 100px; left: 30%; z-index: 5554; display: none"
		id="downModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">下载清单</h4>
				</div>
				<div class="modal-body">
					<form id="downForm" 
						action="${pageContext.request.contextPath}/background/newStatement/downList.html"
						method="post"
						class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">开始时间</label>
							<div class="col-sm-10">
								<input id="startTime2" name="startTime" class="form-control"
									style="width: 150px; border: 1px solid #000; height: 29px; border-radius: 5px; padding-left: 3px; margin-right: 3px;"
									type="date" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">结束时间</label>
							<div class="col-sm-10">
								<input id="endTime2" name="endTime" class="form-control"
									style="width: 150px; border: 1px solid #000; height: 29px; border-radius: 5px; padding-left: 3px; margin-right: 3px;"
									type="date" />
							</div>
						</div>
						<!-- 选项卡 -->
						<input type="hidden" name="bz" id="bz" value="1"/>
						<!-- 层级 -->
						<input type="hidden" name="type" value="${type}"/>
						<!-- 点击名称 -->
						<input type="hidden" name="clear" value="${area}"/>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="save">开始下载</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="quxiao">取消下载</button>
				</div>
			</div>
		</div>
	</div>


	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">报表管理</a></li>
			<li><a href="#">业务办理报表</a></li>
		</ul>
	</div>
	<div class="formbody">

		<div id="usual1" class="usual">

			<div class="itab_nav">
				<ul>
					<li><a href="#tab1" class="selected a">业务办理总表</a></li>
					<li><a href="#tab2" class="a">宽带办理报表</a></li>
					<li><a href="#tab3" class="a">营销活动报表</a></li>
					<li><a href="#tab4" class="a">套餐升舱报表</a></li>
				</ul>
			</div>

			<div align="right" style="border:1px solid #fff; ">
					<!-- 根据日期查询报表 -->
			    	<table border="1" cellpadding="0px" cellspacing="0px">
			    		<tr>
			    			<td>
			    				<form id="chaxun" action="${pageContext.request.contextPath }/background/newStatement/handle1.html" method="get">
						  			<div style="font-size: 16px;">
							  			请选择开始日期:
							  			<input style="border: 1px solid #000;height: 29px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="text" name="startTime" value="${startTime}" id="startTime"/>
										结束日期:
										<input style="border: 1px solid #000;height: 29px; border-radius:5px; padding-left: 3px; margin-right: 3px;" type="text" name="endTime" value="${endTime}" id="endTime"/>
										<input class="btn btn-info" id="find" type="button" value="查询" />&nbsp;
										<input type="hidden" name="type" value="${type}"/>
										<input type="hidden" name="area" value="${area}"/>
									</div>
					  			</form>
			    			</td>
			    			<td>
			    				<input class="btn btn-info" id="download2" type="button" value="下载清单" />
			    			</td>
			    		</tr>
			    	</table>
			    	</div>
				<div>
				
				<script>
									
					$("#download2").click(function(){
						$("#bigblack").css("display","block");
						$("#smallblack").css("display","block")
					});
		                
	                $("#quxiao").click(function(){
	    				$("#bigblack").css("display","none");
	    				$("#smallblack").css("display","none");
	    			});
	     			
	                $("#save").click(function(){
	     				$("#bigblack").css("display","none");
	     				$("#smallblack").css("display","none");
	     				var startTime = $("#startTime2").val();
	     				var endTime = $("#endTime2").val();
	     				 if(endTime >= startTime){
	     					$("#downForm").submit();
	     				}else{
	     					alert("结束时间必须大于或者等于开始时间")
	     				}  
		     				
		     		});
				</script>
			

				<div id="tab1" class="tabson">
					<div class="toolbar1"></div>
					<div align="right"></div>
					<div>
						<!-- 根据日期查询报表 -->
						<table class="list">
							<thead>
								<tr>
									<th colspan="8"><span class="t_head1"
										style="padding-left: 760px"> 业务办理总表 </span></th>
								</tr>
								<tr>
									<th colspan="8"><span class="t_head"
										style="padding-left: 700px; color: black;">统计时间:${startTime}至${endTime}</span>
									</th>
								</tr>
								<tr>
									<c:if test="${type == 'city'}">
										<th style="text-align: center">地市</th>
									</c:if>
									<c:if test="${type == 'area'}">
										<th style="text-align: center">片区</th>
									</c:if>
									<c:if test="${type == 'branch'}">
										<th style="text-align: center">支局</th>
									</c:if>
									<c:if test="${type == 'channelManager'}">
										<th style="text-align: center">渠道经理</th>
									</c:if>
									<c:if test="${type == 'channel'}">
										<th style="text-align: center">渠道</th>
									</c:if>
									<th style="text-align: center">周期累计</th>
									<th style="text-align: center">总累计</th>
								</tr>

							</thead>
							<tbody>
								<c:forEach items="${list}" var="v">
									<tr>
										<c:if test="${type == 'city'}">
											<td style="cursor: pointer; color: blue"><a href="${pageContext.request.contextPath}/background/newStatement/handle1.html?startTime=${startTime}&endTime=${endTime}&type=area">${v.n}</a></td>
										</c:if>
										<c:if test="${type == 'area' && v.n != '合计'}">
											<td style="cursor: pointer; color: blue"><a href="${pageContext.request.contextPath}/background/newStatement/handle1.html?startTime=${startTime}&endTime=${endTime}&type=branch&area=${v.n}">${v.n}</a></td>
										</c:if>
										<c:if test="${type == 'area' && v.n == '合计'}">
											<td style="cursor: pointer; color: blue"><a href="#">${v.n}</a></td>
										</c:if>
										<c:if test="${type == 'branch' && v.n != '合计'}">
											<td style="cursor: pointer; color: blue"><a href="${pageContext.request.contextPath}/background/newStatement/handle1.html?startTime=${startTime}&endTime=${endTime}&type=channelManager&area=${v.n}">${v.n}</a></td>
										</c:if>
										<c:if test="${type == 'branch' && v.n == '合计'}">
											<td style="cursor: pointer; color: blue"><a href="#">${v.n}</a></td>
										</c:if>
										<c:if test="${type == 'channelManager' && v.n != '合计'}">
											<td style="cursor: pointer; color: blue"><a href="${pageContext.request.contextPath}/background/newStatement/handle1.html?startTime=${startTime}&endTime=${endTime}&type=channel&area=${v.n}">${v.n}</a></td>
										</c:if>
										<c:if test="${type == 'channelManager' && v.n == '合计'}">
											<td style="cursor: pointer; color: blue"><a href="">${v.n}</a></td>
										</c:if>
										<c:if test="${type == 'channel'}">
											<td style="cursor: pointer; color: blue"><a href="#">${v.n}</a></td>
										</c:if>
										<td>${v.all1Con}</td>
										<td>${v.allCon}</td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
			</div>

				<div id="tab2" class="tabson">
					<div class="toolbar1"></div>
					<div align="right"></div>
					<div>
						<!-- 根据日期查询报表 -->
						<table class="list">
							<thead>
								<tr>
									<th colspan="8"><span class="t_head1"
										style="padding-left: 760px"> 宽带办理报表 </span></th>
								</tr>
								<tr>
									<th colspan="8"><span class="t_head"
										style="padding-left: 700px; color: black;">统计时间:${startTime}至${endTime}</span>
									</th>
								</tr>
								<tr>
									<th style="text-align: center">地市</th>
									<th style="text-align: center">周期累计</th>
									<th style="text-align: center">总累计</th>
								</tr>

							</thead>
							<tbody>
								<c:forEach items="${list}" var="v">
									<tr>
										<td style="cursor: pointer; color: blue"><a>${v.n}</a></td>
										<td>${v.kd1Con}</td>
										<td>${v.kdCon}</td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
				</div>
			</div>

				<div id="tab3" class="tabson">
					<div class="toolbar1"></div>
					<div align="right"></div>
					<div>
						<!-- 根据日期查询报表 -->
						<table class="list">
							<thead>
								<tr>
									<th colspan="8"><span class="t_head1"
										style="padding-left: 760px"> 营销活动报表 </span></th>
								</tr>
								<tr>
									<th colspan="8"><span class="t_head"
										style="padding-left: 700px; color: black;">统计时间:${startTime}至${endTime}</span>
									</th>
								</tr>
								<tr>
									<th style="text-align: center">地市</th>
									<th style="text-align: center">周期累计</th>
									<th style="text-align: center">总累计</th>
								</tr>

							</thead>
							<tbody>
								<c:forEach items="${list}" var="v">
									<tr>
										<td style="cursor: pointer; color: blue"><a>${v.n}</a></td>
										<td>${v.yx1Con}</td>
										<td>${v.yxCon}</td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
				</div>
			</div>


			<div id="tab4" class="tabson">
				<div class="toolbar1"></div>
				<div align="right"></div>
				<div>
					<table class="list">
						<thead>
							<tr>
								<th colspan="8"><span class="t_head1"
									style="padding-left: 760px"> 套餐升舱报表 </span></th>
							</tr>
							<tr>
								<th colspan="8"><span class="t_head"
									style="padding-left: 700px; color: black;">统计时间:${startTime}至${endTime}</span>
								</th>
							</tr>
							<tr>
								<th style="text-align: center">地市</th>
								<th style="text-align: center">周期累计</th>
								<th style="text-align: center">总累计</th>
							</tr>

						</thead>
						<tbody>
							<c:forEach items="${list}" var="v">
								<tr>
									<td style="cursor: pointer; color: blue"><a>${v.n}</a></td>
									<td>${v.sc1Con}</td>
									<td>${v.scCon}</td>

								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>


	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>

	<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
		</div>
	<script>
	
		$(function(){
			var date =moment().format("YYYY-MM-DD");    
			$("#startTime2").val(date);
			$("#endTime2").val(date);
			
			$("#endTime").datepicker({
				format: "yyyy-mm-dd",
				language: "zh-CN",
				autoclose: true
			});
			
			$("#startTime2").datepicker({
				format: "yyyy-mm-dd",
				language: "zh-CN",
				autoclose: true
			});
			
			$("#endTime2").datepicker({
				format: "yyyy-mm-dd",
				language: "zh-CN",
				autoclose: true
			});
		});
		
		$("#find").click(function() {
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			
			if(startTime == "" || endTime == "") { 
				alert("请输入日期");
				return;
			}
			
			if(Date.parse(startTime) > Date.parse(endTime)) {
				alert("请正确输入两个日期");
				reutrn;
			}
			
			$("#chaxun").submit();
		});
		
		$(".a").click(function() {
			var b = $(this).html();
			if(b == "业务办理总表") {
				$("#bz").val("1");
			} else if(b == "宽带办理报表") {
				$("#bz").val("2");
			} else if(b == "营销活动报表") {
				$("#bz").val("3");
			} else if(b == "套餐升舱报表") {
				$("#bz").val("4");
			}
		});
	</script>
</body>
</html>

