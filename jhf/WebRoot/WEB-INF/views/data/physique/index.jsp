<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../include/_meta.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/plugins/bootstrap-editable/bootstrap-editable.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.min.css">
<link
	href="${pageContext.request.contextPath}/static/plugins/layui/css/layui.css"
	rel="stylesheet">
<body>
	<jsp:include page="../../include/_left.jsp" />
	<div class="m-right">
		<jsp:include page="../../include/_top.jsp" />
		<nav class="breadcrumb m-breadcrumb">
			<i class="glyphicon glyphicon-th-list"></i>&nbsp;管 理 > 体 质
		</nav>
		<jsp:include page="../../include/_data_menu.jsp" />
		<div class="m-content">
			<div class="m-container">
				<div id="toolbar">
					<button id="mAddBtn" class="btn btn-success">
						<i class="glyphicon glyphicon-add"></i> 添 加
					</button>
				</div>
				<table id="table" data-toolbar="#toolbar" data-search="true"
					data-sort-name="physique_no" data-sort-order="asc"
					data-show-refresh="true" data-show-export="true"
					data-show-export="true" data-export-types="['excel']"
					data-page-list="[10, 25, 50, 100, ALL]"
					data-export-options='{"fileName": "体质信息"}' data-show-columns="true">
				</table>
			</div>
			<jsp:include page="../../include/_bottom.jsp" />
		</div>
	</div>
	<div class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<form id="mAddForm" class="layui-form">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">添加体质</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="mDemandNo">所属需求</label> <select class="form-control"
								name="demand_no" id="mDemandNo" lay-verify="required">
								<option value="">请选择所属需求</option>
								<c:forEach items="${demandList}" var="row" varStatus="status">
									<option value="${row.demand_no}">${row.demand_name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="mPhysiqueNo">体质编号</label> <select
								class="form-control" name="physique_no" id="mPhysiqueNo"
								lay-verify="required">
								<option value="">请选择编号</option>
								<option value="01">01</option>
								<option value="02">02</option>
								<option value="03">03</option>
								<option value="04">04</option>
								<option value="05">05</option>
								<option value="06">06</option>
								<option value="07">07</option>
								<option value="08">08</option>
								<option value="09">09</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
							</select>
						</div>
						<div class="form-group">
							<label for="mPhysiqueName">体质名称</label> <input type="text"
								id="mPhysiqueName" class="form-control" name="physique_name"
								lay-verify="required">
						</div>
						<div class="form-group">
							<label for="mPhysiqueGuanlian">体质影响因素</label> <input
								id="mPhysiqueGuanlian" type="text" name="physique_guanlian"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="mPhysiqueGuanlian">体质介绍链接</label> <input
								id="mPhysiqueIntroduce" type="text" name="physique_introduce"
								class="form-control">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关
							闭</button>
						<button type="button" class="btn btn-primary" lay-submit
							lay-filter="add">保 存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/plugins/bootstrap-editable/bootstrap-editable.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/extensions/editable/bootstrap-table-editable.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/plugins/bootstrap-table/extensions/export/bootstrap-table-export.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/plugins/tableExport/tableExport.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/plugins/layui/layui.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/script/data/physique.js"></script>
</body>
</html>