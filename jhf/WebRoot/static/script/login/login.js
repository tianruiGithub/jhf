$(".m-login-box").css("margin-top", (window.innerHeight - 450) / 2);
$(function() {
	$('#mLoginForm').submit(function() {
		var loading;
		var ajax_option = {
			beforeSend : function() {
			 loading =layer.load(1, {
					shade : [ 0.1, '#fff' ]
				});
			},
			success : function(data) {
				layer.close(loading)
				if (data.status == "success") {
					window.location.href="main";
				} else {
					layer.msg(data.msg, {
						time : 3000,
						icon : 5
					});
					changeCaptcha();
				}
			}
		}
		$(this).ajaxSubmit(ajax_option);
		return false;
	});
});
function changeCaptcha() {
	var d = new Date();
	// 为了避免服务器或者浏览器缓存，添加了一个额外的参数
	document.getElementById("adminCaptcha").src = "captcha?t=" + d.toString(40);
}