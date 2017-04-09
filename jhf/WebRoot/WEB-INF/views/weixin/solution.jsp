<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<title>方 案</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">
<link rel="icon"
	href="${pageContext.request.contextPath}/static/images/favicon.icon">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/plugins/bootstrap/css/bootstrap.min.css">

<style type="text/css">
body {
	padding: 0;
	margin: 0;
	background-color: #f6f3e6;
}

.result {
	float: left;
	width: 100%;
	padding-top: 10px;
}

.icon {
	float: left;
	width: 70px;
	height: 70px;
	margin-top: 24px;
	margin-left: 20px;
}

.result-title {
	float: left;
	width: 100%;
	color: white;
	font-size: 18px;
	padding-top: 10px;
	font-weight: bold;
	text-shadow: 0 0px 0 #fff;
}

.result-content {
	positive: relative;
	top: 0;
	left: 0;
	width: 100%;
	height: 30px;
	line-height: 30px;
	color: white;
	font-size: 14px;
	text-shadow: 0 0px 0 #fff;
	width: 100%;
}

.result-type {
	float: left;
	width: 70px;
	text-align: center;
	margin-left: 20px;
	color: white;
	font-size: 14px;
	text-shadow: 0 0px 0 #fff;
	text-align: center;
	margin-left: 20px;
	color: white;
}

.result-image {
	width: 100%;
}

.sol-tip {
	flaot: left;
	width: 100%;
	height: 30px;
	width: 100%;
	border: 0px;
	cell-spacing: 0;
	margin: 0px;
	padding: 0px;
	margin-top: -4px;
	cell-padding: 0px;
	border-top: 1px solid #d0d0d0;
}

.sol-tip-box {
	margin-top: 0px;
	width: 170px;
	height: 30px;
	line-height: 30px;
	text-align: center;
	font-size: 15px;
	color: white;
	text-shadow: 0 0px 0 #fff;
	border-radius: 0px 0px 5px 5px;
}

.sol-tip2 {
	float: left;
	width: 100%;
	height: 30px;
	line-height: 30px;
	color: #333;
	text-align: center;
	font-size: 13px;
}

.sol-item-left {
	flaot: left;
	width: 97%;
	height: 48px;
	margin-left: 2%;
	margin-right: 1%;
	border-radius: 5px;
	background-color: white;
	margin-top: 5px;
}

.sol-color {
	background-color: #c4a17b;
}

.sol-item-right {
	flaot: right;
	width: 97%;
	height: 48px;
	margin-right: 2%;
	margin-left: 1%;
	border-radius: 5px;
	background-color: white;
	margin-top: 5px;
}

.sol-item-img {
	float: left;
	width: 40px;
	height: 40px;
	margin-left: 4px;
	margin-top: 4px;
}

.sol-item-title {
	float: left;
	font-size: 13px;
	color: #333;
	font-weight: 500;
	margin-left: 5px;
	margin-top: 8px;
}

.sol-item-content {
	float: left;
	font-size: 12px;
	color: #666;
	margin-left: 5px;
	margin-top: 1px;
}
</style>
</head>

<body>
	<table class="result"
		style="background-color:${solution_chanpin_color}">
		<tr>
			<td style="width: 100px;"><img class="icon"
				src="${pageContext.request.contextPath}/static/images/sol_title.png" /></td>
			<td>
				<div class="result-title">辩证分型结果:</div>
				<div class="result-content">
					<c:choose>
						<c:when test="${solution_demand_no == '07'}">
							依中医理论，您的肌肤为[${solution_physique_name}]肤质
						</c:when>
						<c:otherwise>
							您的${solution_demand_second_name}是由于[${solution_physique_name}]引起的
						</c:otherwise>
					</c:choose>
				</div>
			</td>
		</tr>
		<tr>
			<td style="width: 100px; height: 25px;" align="center" valign="top"><span
				class="result-type">${solution_demand_name}</span></td>
			<td></td>
		</tr>
	</table>
	<img onclick="detail()" class="result-image" alt=""
		src="${solution_chanpin_image}" />
	<div class="sol-tip2">为您制定的方案如下，请点击查看</div>
	<table style="width: 100%; margin-bottom: 10px;">
		<tr>
			<td style="width: 50%;">
				<div onclick="mianmo()" class="sol-item-left">
					<img class="sol-item-img" alt=""
						src="${pageContext.request.contextPath}/static/images/sol_mianmo.jpg" />
					<div class="sol-item-title">自制面膜</div>
					<div class="sol-item-content">简单易学,天然有效</div>
				</div>
			</td>
			<td>
				<div onclick="zuyu()" class="sol-item-right">
					<img class="sol-item-img" alt=""
						src="${pageContext.request.contextPath}/static/images/sol_zuyu.jpg" />
					<div class="sol-item-title">足 浴</div>
					<div class="sol-item-content">疏通经气,祛风散寒</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="width: 50%;">
				<div onclick="baguan()" class="sol-item-left">
					<img class="sol-item-img" alt=""
						src="${pageContext.request.contextPath}/static/images/sol_baguan.jpg" />
					<div class="sol-item-title">拔 罐</div>
					<div class="sol-item-content">调经活络,祛风散寒</div>
				</div>
			</td>
			<td>
				<div onclick="cha()" class="sol-item-right">
					<img class="sol-item-img" alt=""
						src="${pageContext.request.contextPath}/static/images/sol_cha.jpg" />
					<div class="sol-item-title">茶</div>
					<div class="sol-item-content">饮服方便,甘淡平和</div>
				</div>
			</td>
		</tr>
		<tr>
			<td style="width: 50%;">
				<div onclick="yishi()" class="sol-item-left">
					<img class="sol-item-img" alt=""
						src="${pageContext.request.contextPath}/static/images/sol_yishi.jpg" />
					<div class="sol-item-title">宜 食</div>
					<div class="sol-item-content">越吃越漂亮</div>
				</div>
			</td>
			<td>
				<div onclick="jishi()" class="sol-item-right">
					<img class="sol-item-img" alt=""
						src="${pageContext.request.contextPath}/static/images/sol_jishi.jpg" />
					<div class="sol-item-title">忌 食</div>
					<div class="sol-item-content">管住嘴很重要</div>
				</div>
			</td>
		</tr>
	</table>

	<script
		src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>

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

		$(document).ready(function() {
			$(".sol-item-title").width($(".sol-item-left").width() - 50);
			if (window.innerHeight > 600) {
				$(".sol-item-left").height(60);
				$(".sol-item-right").height(60);
				$(".sol-item-img").width(52);
				$(".sol-item-img").height(52);
				$(".sol-item-title").width($(".sol-item-left").width() - 70);
				$(".sol-item-content").width($(".sol-item-left").width() - 70);
				$(".sol-item-title").css("font-size", "14px");
				$(".sol-item-content").css("font-size", "13px");
				$(".sol-item-title").css("margin-top", "9px");
				$(".sol-item-content").css("margin-top", "4px");
			}
		});

		function detail() {
			if ('${solution_chanpin_link}' != null && '${solution_chanpin_link}' != "") {
				window.location.href = "${solution_chanpin_link}";
			} else {
				alert("敬请期待!");
			}

		}

		function mianmo() {
			window.location.href = "weixin/mianmo?no=${solution_physique_no}";
		}

		function zuyu() {
			window.location.href = "weixin/zuyu?no=${solution_no}";
		}

		function baguan() {
			window.location.href = "weixin/baguan?no=${solution_no}";
		}

		function cha() {
			window.location.href = "weixin/cha?no=${solution_no}";
		}

		function yishi() {
			window.location.href = "weixin/yishi?no=${solution_physique_no}";
		}

		function jishi() {
			window.location.href = "weixin/jishi?no=${solution_physique_no}";
		}

		function closeWindow() {
			wx.closeWindow();
		}
		 
	</script>
</body>
</html>
