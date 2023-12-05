<!doctype html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/view/common/template.jsp"/>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<body>
<div class="wrapper">
	<jsp:include page="/WEB-INF/view/common/header.jsp"/>
	<jsp:include page="/WEB-INF/view/common/menu.jsp"/>
	<div class="page-wrapper">
		<div class="page-content">

			<!--breadcrumb-->
			<div class="page-breadcrumb d-none d-sm-flex align-items-center mb-3">
				<div class="breadcrumb-title pe-3">Tables</div>
				<div class="ps-3">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0 p-0">
							<li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">养护任务列表</li>
						</ol>
					</nav>
				</div>
			</div>
			<!--end breadcrumb-->

			<div class="card">
				<div class="card-body">
					<div class="table-responsive">
						<table id="example2" class="table table-striped table-bordered">
							<thead>
							<tr>
								<th>养护任务编号</th>
								<th>任务名称</th>
								<th>任务描述</th>
								<th>养护时间</th>
								<th>养护地点</th>
								<th>养护人员</th>
								<th>养护对象</th>
								<th>创建人员</th>
								<th>创建时间</th>
								<th>更新时间</th>
								<th>任务状态</th>
								<th>操作</th>
							</tr>
							</thead>
							<tbody>
							<c:forEach items="${maintenancetasklist}" var="data">
							<tr>
								<td>${data.taskId}</td>
								<td>${data.taskName}</td>
								<td>${data.taskDescription}</td>
								<td>${data.maintenanceTime}</td>
								<td>${data.maintenanceSite}</td>
								<td>${data.uid}</td>
								<td>${data.plantId}</td>
								<td>${data.creator}</td>
								<td>${data.creationTime}</td>
								<td>${data.updateTime}</td>
								<td>
									<c:if test="${data.taskStatus==0}"><span class="badge rounded-pill text-primary bg-light-success p-2 text-uppercase px-3">待完成</span></c:if>
									<c:if test="${data.taskStatus==1}"><span class="badge rounded-pill text-primary bg-light-success p-2 text-uppercase px-3">已完成</span></c:if>
								</td>
								<td>
									<c:if test="${maintainer!=null}">
										<c:if test="${data.taskStatus==0}">
											<button type="button" class="btn btn-outline-primary"
													onclick="completeTask(${data.taskId})"
											>完成</button>
										</c:if>
										<c:if test="${data.taskStatus!=0}">
											<button type="button" class="btn btn-outline-primary"
													onclick="completeTask(${data.taskId})"
													disabled="disabled"
											>完成</button>
										</c:if>
									</c:if>
								</td>
							</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--end wrapper-->

<!--start overlay-->
<div class="overlay toggle-icon"></div>
<!--end overlay-->
<!--Start Back To Top Button-->
<a href="javaScript:;" class="back-to-top"><i class='bx bxs-up-arrow-alt'></i></a>
<!--End Back To Top Button-->

<!--plugins-->
<%--分页以及模糊查询登录不可删除--%>
<script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script>
<script src="assets/plugins/datatable/js/dataTables.bootstrap5.min.js"></script>

<script>
	$(document).ready(function() {
		$('#example').DataTable();
	} );
</script>
<script>
	$(document).ready(function() {
		var table = $('#example2').DataTable( {
			lengthChange: false,
			buttons: [ 'copy', 'excel', 'pdf', 'print']
		} );

		table.buttons().container()
				.appendTo( '#example2_wrapper .col-md-6:eq(0)' );
	} );
</script>

<script>

	function completeTask(taskId){
		if(confirm("确定完成?")){
			$.ajax({
				url:"completeMaintenanceTask",
				data:{"taskId": taskId},
				type:"post",
				dataType:"json",
				success:function (data) {
					if (data) {
						alert("完成成功");
						document.location.reload();//当前页面
					}else{
						alert("完成失败");
					}
				}
			});
		}
	}

</script>

</body>

</html>
