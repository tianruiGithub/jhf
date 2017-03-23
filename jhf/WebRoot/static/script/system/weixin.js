$(function() {
	$("#mIsEncrypt option[value='" + $("#mIsEncrypt").attr("data-ref") + "']").attr("selected", "true");
	if ($("#mIsEncrypt").val() == "0") {
		$("#mEncoding").attr("disabled", "disabled");
	} else {
		$("#mEncoding").removeAttr("disabled");
	}
	layui.use('form', function() {
		var form = layui.form();
		form.on('select(isEncrypt)', function(data) {
			if (data.value == "0") {
				$("#mEncoding").attr("disabled", "disabled");
			} else {
				$("#mEncoding").removeAttr("disabled");
			}
		});
		form.verify({
			isEncoding : function(value) {
				if ($("#mIsEncrypt").val() == "1" && value == "") {
					return '请输入加密密钥';
				}
			}
		});
		// 监听提交
		form.on('submit(update)', function(data) {
			alert(1);
			 $.ajax({
                 type:"post",
                 url:"updatess",
                 data: $('#mUpdateForm').serialize(),//表单数据
                 success:function(d){
                     alert(d.res);
                 }
             });
//			var loading;
//			$.ajax({
//				url : "update",
//				type:"post",
//				data : data.field,
//				beforeSend : function() {
//					loading = layer.load(1, {
//						shade : [ 0.1, '#fff' ]
//					});
//					
//				},
//				success : function(res) {
//					alert(res.status);
//					layer.close(loading)
//					if (res.status == "success") {
//						layer.msg(data.msg, {
//							time : 3000,
//							icon : 6
//						});
//					} else {
//						layer.msg(res.msg, {
//							time : 3000,
//							icon : 5
//						});
//					}
//				}
//			});
			return false;
		});
	});
	 
});
