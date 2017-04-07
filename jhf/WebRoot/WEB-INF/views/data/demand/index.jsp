<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<i class="glyphicon glyphicon-th-list"></i>&nbsp;管 理 > 需 求
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
					data-sort-name="demand_no" data-sort-order="asc"
					data-show-refresh="true" data-show-export="true"
					data-show-export="true" data-export-types="['excel']"
					data-export-options='{"fileName": "需求信息"}' data-show-columns="true">
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
						<h4 class="modal-title">添加需求</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="mDemandNo">需求编号</label> <select class="form-control"
								name="demand_no" id="mDemandNo" lay-verify="required">
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
							<label for="mDemandName">需求名称</label> <input type="text"
								id="mDemandName" class="form-control" name="demand_name"
								lay-verify="required">
						</div>
						<div class="form-group">
							<label for="mDemandSecondName">需求别名</label> <input
								id="mDemandSecondName" type="text" name="demand_second_name"
								class="form-control" lay-verify="required">
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
	<script>
		$(function() {
			var $table = $("#table");
			window.operateEvents = {
				'click .remove' : function(e, value, row, index) {
					layer.confirm('您确定要删除吗？', {
						btn : [ '确定', '取消' ]
					//按钮
					}, function() {
						var loading;
						$.ajax({
							url : "${pageContext.request.contextPath}/data/demand/remove",
							type : "post",
							data : {
								id : row.demand_id
							},
							beforeSend : function() {
								loading = layer.load(1, {
									shade : [ 0.1, '#fff' ]
								});
							},
							success : function(res) {
								layer.close(loading)
								if (res.status == "success") {
									layer.msg(res.msg, {
										time : 3000,
										icon : 6
									});
									$table.bootstrapTable('remove', {
										field : 'demand_id',
										values : [ row.demand_id ]
									});
								} else {
									layer.msg(res.msg, {
										time : 3000,
										icon : 5
									});
								}
							}
						});
					}, function() {

					});

				}
			};
			$("#mAddBtn").on("click", function() {
				$("#mDemandNo option[value='']").attr("selected", "true");
				$("#mDemandName").val("");
				$("#mDemandSecondName").val("");
				$('.modal').modal({
					backdrop : "static"
				})
			});
			layui.use('form', function() {
				var form = layui.form();
				// 监听提交
				form.on('submit(add)', function(data) {
					var loading;
					$.ajax({
						url : "${pageContext.request.contextPath}/data/demand/add",
						type : "post",
						data : {
							demand_no : data.field.demand_no,
							demand_name : data.field.demand_name,
							demand_second_name : data.field.demand_second_name
						},
						beforeSend : function() {
							loading = layer.load(1, {
								shade : [ 0.1, '#fff' ]
							});
						},
						success : function(res) {
							layer.close(loading)
							if (res.status == "success") {
								layer.msg(res.msg, {
									time : 3000,
									icon : 6
								});
								$('.modal').modal('hide');
								$table.bootstrapTable('refresh');
							} else {
								layer.msg(res.msg, {
									time : 3000,
									icon : 5
								});
							}
						}
					});
					return false;
				});
			});
			$table.bootstrapTable({
				searchOnEnterKey : true,
				idField : "demand_id",
				queryParamsType : "limit",//查询参数组织方式
				pagination : true,
				sidePagination : "server",
				dataType : "json",
				pageList : [ 5, 10, 20, 30, 50 ],
				url : "${pageContext.request.contextPath}/data/demand/list",
				columns : [ {
					field : "demand_no",
					title : '编号',
					align : 'center',
					width : '100px',
					sortable : true
				}, {
					field : "demand_name",
					title : '名称',
					align : 'center',
					sortable : true,
					editable : {
						type : 'text',
						validate : function(v) {
							if (!($.trim(v))) {
								return "需求名称不能为空";
							}
						}
					}
				}, {
					field : "demand_second_name",
					title : '别名',
					align : 'center',
					sortable : true,
					editable : {
						type : 'text',
						validate : function(v) {
							if (!($.trim(v))) {
								return "需求别名不能为空";
							}
						}
					}
				}, {
					field : "operate",
					title : '操作',
					align : 'center',
					width : '100px',
					events : operateEvents,
					formatter : function() {
						return [ '<button data-toggle="tooltip" title="删除" class="btn btn-danger btn-xs remove"><i class="glyphicon glyphicon-remove"></i><button>' ].join('');
					}
				} ],
				onEditableSave : function(field, row, oldValue, el) {
					$table.bootstrapTable('resetView');
					$.ajax({
						type : "post",
						url : "${pageContext.request.contextPath}/data/demand/modify",
						dataType : "json",
						data : row,
						success : function(res) {
							if (res.status == "success") {
								layer.msg(res.msg, {
									time : 3000,
									icon : 6
								});
							} else {
								layer.msg(res.msg, {
									time : 3000,
									icon : 5
								});
							}
						}
					});
				}
			});

			setTimeout(function() {
				$table.bootstrapTable('resetView');
			}, 200);

		});
	</script>
</body>
</html>