$(function() {
	var $table = $("#table");
	window.operateEvents = {
		'click .remove' : function(e, value, row, index) {
			layer.confirm('您确定要删除吗？', {
				btn : [ '确定', '取消' ]
			// 按钮
			}, function() {
				var loading;
				$.ajax({
					url : "/jhf/data/demand/remove",
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
				url : "/jhf/data/demand/add",
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
		queryParamsType : "limit",// 查询参数组织方式
		pagination : true,
		sidePagination : "server",
		dataType : "json",
		pageList : [ 5, 10, 20, 30, 50 ],
		url : "/jhf/data/demand/list",
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
				url : "/jhf/data/demand/modify",
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