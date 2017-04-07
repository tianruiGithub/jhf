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
					url : "/jhf/data/physique/remove",
					type : "post",
					data : {
						id : row.physique_id
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
								field : 'physique_id',
								values : [ row.physique_id ]
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
		$("#mPhysiqueName").val("");
		$("#mPhysiqueGuanlian").val("");
		$("#mPhysiqueIntroduce").val("");
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
				url : "/jhf/data/physique/add",
				type : "post",
				data : {
					demand_no:data.field.demand_no,
					physique_no : data.field.demand_no + data.field.physique_no,
					physique_name : data.field.physique_name,
					physique_guanlian : data.field.physique_guanlian,
					physique_introduce : data.field.physique_introduce
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

	function queryParams(params) {
		$(".fixed-table-toolbar").find("select").each(function() {
			params[$(this).attr("name")] = $(this).val();
		});
		return params;
	}

	$table.bootstrapTable({
		searchOnEnterKey : true,
		idField : "physique_id",
		queryParamsType : "limit",// 查询参数组织方式
		pagination : true,
		sidePagination : "server",
		dataType : "json",
		queryParams : queryParams,
		url : "/jhf/data/physique/list",
		columns : [ {
			field : "physique_no",
			title : '编号',
			align : 'center',
			width : '100px',
			sortable : true
		}, {
			field : "physique_name",
			title : '名称',
			align : 'center',
			editable : {
				type : 'text',
				validate : function(v) {
					if (!($.trim(v))) {
						return "体质名称不能为空";
					}
				}
			}
		}, {
			field : "demand_name",
			title : '所属需求',
			align : 'center'
		}, {
			field : "physique_guanlian",
			title : '影响因素',
			align : 'center',
			editable : {
				type : 'text'
			}
		}, {
			field : "physique_introduce",
			title : '介绍链接',
			align : 'center',
			editable : {
				type : 'text'
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
				url : "/jhf/data/physique/modify",
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
	initDemand();
	setTimeout(function() {
		$table.bootstrapTable('resetView');
	}, 200);

	function initDemand() {
		$
				.ajax({
					url : "/jhf/data/demand/getAll",
					type : "get",
					success : function(res) {
						if (res.status == "success") {
							var select = "<select id='mSelectDemand' name='type1' class='pull-right search' style='height:34px;border-radius:4px;margin-right:6px;border: 1px solid #ccc;'><option value=''>全部</option>";
							$.each(res.list, function(i, v) {
								select += "<option value='" + v.demand_no + "'>" + v.demand_name + "</option>";
							});
							select += "</select>";
							$(".fixed-table-toolbar").append(select);
							$("#mSelectDemand").on("change", function() {
								$table.bootstrapTable('refresh');
							});
						}
					}
				});
	}

});