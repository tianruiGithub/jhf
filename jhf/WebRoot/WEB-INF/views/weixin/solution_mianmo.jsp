<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<title>面 膜</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">
<link rel="icon"
	href="${pageContext.request.contextPath}/static/images/favicon.icon"
	type="image/gif">

<link rel="stylesheet"
	href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">

<style type="text/css">
body {
	padding: 0;
	margin: 0;
	background-color: #f0f0f0;
}

.page {
	background-color: #f0f0f0;
}

.page .ui-content {
	padding: 0px;
}

.page .title {
	font-size: 13px;
	height: 30px;
	line-height: 30px;
	padding-left: 10px;
	font-weight: 100;
	border: 0;
	text-shadow: 0 0px 0 #fff;
	color: white;
}

.m-image {
	float: left;
	width: 100%;
}

.m-tab {
	float: left;
	width: 100%;
	height: 40px;
	color: #333;
	font-size: 13px;
	line-height: 40px;
	text-align: center;
	text-shadow: 0 0px 0 #fff;
	background-color: white;
}

.m-tab-active {
	color: #fe6b5b;
	border-bottom: 1px solid #fe6b5b;
}

.m-tab-p {
	float: left;
	width: 33.3%;
	height: 40px;
}

.m-content {
	float: left;
	width: 100%;
}

.m-content-p {
	float: left;
	width: 100%;
	display: none;
}

.m-box {
	float: left;
	width: 100%;
	min-height: 50px;
	margin-top: 10px;
	background-color: white;
}

.m-title {
	float: left;
	width: 100%;
	height: 30px;
	border-bottom: 1px solid #f0f0f0;
}

.m-title-bar {
	float: left;
	width: 8px;
	height: 16px;
	background-color: #fe6b5b;
	margin-left: 10px;
	margin-top: 7px;
}

.m-title-name {
	float: left;
	width: auto;
	height: 30px;
	line-height: 30px;
	margin-left: 5px;
	font-size: 14px;
	color: #fe6b5b;
	padding-top: 1px;
}

.m-content {
	float: left;
	width: 100%;
}

.m-word {
	float: left;
	margin: 0px;
	padding: 10px;
	color: #333;
	font-size: 13px;
	color: #333;
}
</style>
</head>

<body>
	<div class="page" data-role="page" id="pageone">
		<div data-role="main" class="ui-content">
			<img class="m-image" src="${pageContext.request.contextPath}/static/images/sol_mianmo_title.jpg" />
			<div class="m-tab">
				<div id="mTab1" class="m-tab-p m-tab-active"></div>
				<div id="mTab2" class="m-tab-p"></div>
				<div id="mTab3" class="m-tab-p"></div>
			</div>
			<div class="m-content">
				<div id="mTab1Content" class="m-content-p" style="display: block;">

				</div>
				<div id="mTab2Content" class="m-content-p"></div>
				<div id="mTab3Content" class="m-content-p"></div>
			</div>
		</div>
	</div>

	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	<script type="text/javascript"
		src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	<script type="text/javascript">
		wx.config({
			debug : false,
			appId : '${appId}',
			timestamp : '${timestamp}',
			nonceStr : '${nonceStr }',
			signature : '${signature}',
			jsApiList : [ 'chooseImage', 'uploadImage', 'hideOptionMenu', 'closeWindow' ]
		});
		var mianmo = ${res};
		$.each(mianmo, function(i, item) {
			$("#mTab" + (i + 1)).html(item.name);
			$("#mTab" + (i + 1) + "Content").append(
					'<div class="m-box">' + '<div class="m-title">' + '<div class="m-title-bar"></div>' + '<div class="m-title-name">原料</div>' + '</div>'
							+ '<div class="m-word">&nbsp;' + item.yuanliao + '</div>' + '</div>');
			var step = "";
			$.each(item.zuofa, function(j, item2) {
				step += "&nbsp;" + (j + 1) + "、" + item2.content + "<br/>";
			});
			$("#mTab" + (i + 1) + "Content").append(
					'<div class="m-box">' + '<div class="m-title">' + '<div class="m-title-bar"></div>' + '<div class="m-title-name">做法</div>' + '</div>' + '<div class="m-word">'
							+ step + '</div>' + '</div>');
			$("#mTab" + (i + 1) + "Content").append(
					'<div class="m-box">' + '<div class="m-title">' + '<div class="m-title-bar"></div>' + '<div class="m-title-name">用法</div>' + '</div>' + '<div class="m-word">'
							+ item.yongfa + '</div>' + '</div>');
			$("#mTab" + (i + 1) + "Content").append(
					'<div class="m-box" style="margin-bottom:10px;">' + '<div class="m-title">' + '<div class="m-title-bar"></div>' + '<div class="m-title-name">功效</div>'
							+ '</div>' + '<div class="m-word">&nbsp;' + item.gongxiao + '</div>' + '</div>');
		});
		$(".m-tab-p").click(function() {
			$(".m-tab-p").removeClass("m-tab-active");
			$("#" + this.id).addClass("m-tab-active");
			$(".m-content-p").css("display", "none");
			$("#" + this.id + "Content").css("display", "block");
		});
		wx.ready(function() {
			wx.hideOptionMenu();
		});
	</script>
</body>
</html>
