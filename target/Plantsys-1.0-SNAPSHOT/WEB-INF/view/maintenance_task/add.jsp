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
<!--wrapper-->
<div class="wrapper">
	<jsp:include page="/WEB-INF/view/common/header.jsp"/>
	<jsp:include page="/WEB-INF/view/common/menu.jsp"/>
	<!--start page wrapper -->
	<div class="page-wrapper">
		<div class="page-content">

			<c:if test="${manager!=null}">
				<div class="card">
					<div class="card-body">
						<form enctype="multipart/form-data" method="post" action="imExcelForBook">
							<input type="file" name="file" />
							<input type="submit" value="数据导入" />
						</form>
					</div>
				</div>
			</c:if>

			<div class="card border-top border-0 border-4 border-danger">
				<div class="card-body p-5">
					<div class="card-title d-flex align-items-center">
						<div><i class="bx bxs-user me-1 font-22 text-danger"></i>
						</div>
						<h5 class="mb-0 text-danger">添加</h5>
					</div>
					<hr>
					<form class="row g-3" id="saveForm">
						<div class="col-md-6">
							<label for="taskName" class="form-label">任务名称</label>
							<div class="input-group"> <span class="input-group-text bg-transparent"><i class="bx bxs-user"></i></span>
								<input type="text" name="taskName" class="form-control border-start-0" id="taskName">
							</div>
						</div>
						<div class="col-md-6">
							<label for="taskDescription" class="form-label">任务描述</label>
							<div class="input-group"> <span class="input-group-text bg-transparent"><i class="bx bxs-user"></i></span>
								<input type="text" name="taskDescription" class="form-control border-start-0" id="taskDescription">
							</div>
						</div>
						<div class="col-md-6">
							<label for="maintenanceTime" class="form-label">养护时间</label>
							<div class="input-group"> <span class="input-group-text bg-transparent"><i class="bx bxs-user"></i></span>
								<input type="date" name="maintenanceTime" class="form-control border-start-0" id="maintenanceTime" >
							</div>
						</div>
						<div class="col-md-6">
							<label for="maintenanceSite" class="form-label">养护地点</label>
							<div class="input-group"> <span class="input-group-text bg-transparent"><i class="bx bxs-user"></i></span>
								<input type="text" name="maintenanceSite" class="form-control border-start-0" id="maintenanceSite" >
							</div>
						</div>
						<div class="col-md-6">
							<label for="uid" class="form-label">养护人员</label>
							<div class="input-group"> <span class="input-group-text bg-transparent"><i class="bx bxs-user"></i></span>
								<input type="text" name="uid" class="form-control border-start-0" id="uid" >
							</div>
						</div>
						<div class="col-md-6">
							<label for="plantId" class="form-label">养护对象</label>
							<div class="input-group"> <span class="input-group-text bg-transparent"><i class="bx bxs-user"></i></span>
								<input type="text" name="plantId" class="form-control border-start-0" id="plantId" >
							</div>
						</div>
						<div class="col-md-6">
							<label for="creator" class="form-label">创建人员</label>
							<div class="input-group"> <span class="input-group-text bg-transparent"><i class="bx bxs-user"></i></span>
								<input type="text" name="creator" class="form-control border-start-0" id="creator" >
							</div>
						</div>
						<div class="col-12">
							<button type="button" class="btn btn-danger px-5" id="save">添加</button>
						</div>
					</form>
				</div>
			</div>
			<!--breadcrumb-->

			<!--end row-->
		</div>
	</div>
	<!--end page wrapper -->
	<!--start overlay-->

</div>


<script>
	$("#save").click(function() {

		$.ajax({
			cache : true,
			type : "post",
			url : "addMaintenanceTask",
			data : $("#saveForm").serialize(),
			async : false,
			success : function(e) {
				if (e) {
					alert("添加成功");
					window.parent.location.href = "maintenanceTaskList.do";
				} else {
					alert("添加失败");
				}
			}
		})
	});
</script>


<script>
	layui.use([ 'form','jquery','layer','laydate','upload' ], function() {
		var form = layui.form,
				layer = layui.layer,
				laydate = layui.laydate,
				upload = layui.upload,
				$= layui.jquery;
		form.render(); // 这句一定要加
	});

</script>

</body>

</html>
