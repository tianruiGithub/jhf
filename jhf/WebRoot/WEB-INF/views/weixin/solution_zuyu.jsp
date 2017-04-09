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

<title>足 浴</title>
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
	width: 100%;
	font-size: 18px;
	font-family: '黑体';
	background-color: #fe6b5b;
	text-align: center;
	line-height: 45px;
	color: white;
	border: 0px;
	height: 45px;
	text-shadow: 0 0px 0 white;;
	font-weight: 500;
	font-size: 18px;
}

.c-image {
	flaot: left;
	width: 100%;
}

.c-box {
	float: left;
	width: 100%;
	min-height: 100px;
	margin-top: 10px;
	background-color: white;
}

.c-title {
	float: left;
	width: 100%;
	height: 30px;
	border-bottom: 1px solid #f0f0f0;
}

.c-title-bar {
	float: left;
	width: 8px;
	height: 16px;
	background-color: #fe6b5b;
	margin-left: 10px;
	margin-top: 7px;
}

.c-title-name {
	float: left;
	width: auto;
	height: 30px;
	line-height: 30px;
	margin-left: 5px;
	font-size: 14px;
	color: #fe6b5b;
	padding-top: 1px;
}

.c-table {
	float: left;
	width: 100%;
	margin-bottom: 5px;
	cell-padding: 0px;
	cell-spacing: 0px;
	border-spacing: 0px;
}

.c-table thead tr th {
	color: white;
	font-size: 13px;
	height: 24px;
	background-color: #fe6b5b;
	text-shadow: 0 0px 0 white;;
	border: 0px;
}

.c-table tbody tr td {
	color: #333;
	font-size: 12px;
	height: 20px;
	border-bottom: 1px solid #f0f0f0;
	text-shadow: 0 0px 0 #333;;
}

.c-content {
	float: left;
	margin: 0px;
	padding: 10px;
	color: #333;
	font-size: 12px;
}
</style>
</head>

<body>
	<div class="page" data-role="page" id="pageone">
		<div data-role="main" class="ui-content">
			<img class="c-image" alt="" src="${pageContext.request.contextPath}/static/images/sol_zuyu_title.jpg">
			<div class="c-box" style="margin-top: 6px;">
				<div class="c-title">
					<div class="c-title-bar"></div>
					<div class="c-title-name">组方</div>
				</div>
				<table class="c-table">
					<thead>
						<tr>
							<th align="center"
								style="width: 50%; border-right: 1px solid #f0f0f0;">名 称</th>
							<th align="center">重 量</th>
						</tr>
					</thead>
					<tbody id="list">

					</tbody>
				</table>
			</div>
			<div class="c-box" style="margin-bottom: 10px;">
				<div class="c-title">
					<div class="c-title-bar"></div>
					<div class="c-title-name">用法</div>
				</div>
				<div class="c-content">&nbsp;上述组方煮沸20分钟，去渣取汤，加3000ml温开水，调至适温泡足，每日一次，每次20分钟，15日为一个周期。(如用足浴粉，则温开水冲开，足浴时长不变)。</div>
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

		wx.ready(function() {
			wx.hideOptionMenu();
		});

		var zuyu = ${res};
		$.each(zuyu.zfList, function(i, item) {
			$("#list").append(
					'<tr><td align="center" style="width:50%;border-right:1px solid #f0f0f0;">' + item.zf.name + '</td><td align="center">' + item.zf.weight + '</td></tr>');
		});
	</script>
</body>
</html>
