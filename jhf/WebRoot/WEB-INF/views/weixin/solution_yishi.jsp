<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">

<title>宜 食</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
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

.y-image {
	float: left;
	width: 100%;
}

.y-title {
	float: left;
	width: 100%;
	height: 30px;
	background-color: white;
	border-bottom: 1px solid #d0d0d0;
}

.y-title-bar {
	float: left;
	width: 8px;
	height: 16px;
	background-color: #fe6b5b;
	margin-left: 10px;
	margin-top: 7px;
}

.y-title-name {
	float: left;
	width: auto;
	height: 30px;
	line-height: 30px;
	margin-left: 5px;
	font-size: 13px;
	color: #333;
	padding-top: 2px;
}

.y-panel {
	float: left;
	width: 100%;
}

.question {
	color: #3a3939;
	width: 100%;
	height: 40px;
}

.question-accordion {
	float: left;
	width: 100%;
	background-color: white;
}

.question-accordion,.question-accordion ul,.question-accordion li {
	margin: 0px 0 0 0;
	padding: 0;
	border: none;
	outline: none;
}

.question-accordion li>.sub-menu {
	display: none;
}

.question-accordion li:target>.sub-menu {
	display: block;
}

.question-accordion li {
	list-style: none;
	height: auto;
}

.sub-menu li:hover a {
	background: #efefef;
}

.question-accordion li>.sub-menu li {
	width: 100%;
	padding-left: 20px;
	height: 30px;
	line-height: 30px;
}

.question-accordion li>a {
	width: 100%;
	color: #3a3939;
}

.question-title-bar {
	float: left;
	width: 7px;
	height: 16px;
	margin-top: 12px;
	margin-right: 5px;
	margin-left: 20px;
	background-color: #fe6b5b;
}

.question-title-name {
	min-height: 40px;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	padding-left: 20px;
	display: block;
	color: #666;
	padding-right: 10px;
}

.question-accordion li>a:hover,.question-accordion li>a:active {
	text-decoration: none;
}

.question-accordion li>.question-line {
	float: left;
	border-bottom: 1px solid #e0e0e0;
	width: 100%;
	height: 1px;
}

.y-icon {
	float: left;
	width: 60px;
	height: 56px;
	margin-left: 10px;
	margin-top: 10px;
	margin-bottom: 10px;
}

.y-table {
	float: left;
	width: 100%;
	height: 60px;
}

.y-right {
	float: right;
	width: 21px;
	margin-right: 2px;
}

.y-item {
	float: left;
	font-size: 12px;
	text-decoration: none;
	width: 30%;
	margin-left: 2.5%;
	margin-top: 3px;
	border-radius: 3px;
	height: 24px;
	line-height: 24px;
	color: white;
	background-color: #fe6b5b;
	text-align: center;
	border: 1px solid #f0f0f0;
	text-align: center;
	background-color: #fe6b5b;
	text-shadow: 0 0px 0 #fff;
}

.d-image {
	flaot: left;
	width: 100%;
}

.d-box {
	float: left;
	width: 100%;
	min-height: 100px;
	margin-top: 10px;
	background-color: white;
}

.d-title {
	float: left;
	width: 100%;
	height: 30px;
	border-bottom: 1px solid #f0f0f0;
}

.d-title-bar {
	float: left;
	width: 8px;
	height: 16px;
	background-color: #fe6b5b;
	margin-left: 10px;
	margin-top: 7px;
}

.d-title-name {
	float: left;
	width: auto;
	height: 30px;
	line-height: 30px;
	margin-left: 5px;
	font-size: 14px;
	color: #fe6b5b;
	padding-top: 1px;
}

.d-content {
	float: left;
	padding: 10px;
	color: #333;
	font-size: 12px;
}
</style>
</head>

<body>
	<div class="page" data-role="page" id="pageone">
		<div data-role="main" class="ui-content">
			<img id="yImage" class="y-image" />
			<div class="y-title">
				<div class="y-title-bar"></div>
				<div class="y-title-name">适合吃</div>
			</div>
			<ul id="questioninfo" class="question-accordion">
				<li class="question" id="q1"><a id="qt1" href="#q1">
						<table style="width:100%;height:80px;">
							<tr>
								<td style="width:70px;"><img class="y-icon"
									src="${pageContext.request.contextPath}/static/images/sol_yishi_shuiguo.png" /></td>
								<td><table class="y-table">
										<tr>
											<td style="color:#666;font-size:15px;font-bold:none;padding-left:5px;">水果</td>
										</tr>
										<tr>
											<td style="color:#999;font-size:13px;padding-left:5px;">丰富营养，帮助消化</td>
										</tr>
									</table></td>
								<td><img id="qt1right" class="y-right"
									src="${pageContext.request.contextPath}/static/images/sol_yishi_right.png" /></td>
							</tr>
						</table>


				</a>
					<ul id="shuiguo" class="sub-menu">
					</ul>
					<div class="question-line"></div></li>
				<li class="question" id="q2"><a id="qt2" href="#q2">
						<table style="width:100%;height:80px;">
							<tr>
								<td style="width:70px;"><img class="y-icon"
									src="${pageContext.request.contextPath}/static/images/sol_yishi_shucai.png" /></td>
								<td><table class="y-table">
										<tr>
											<td style="color:#666;font-size:15px;font-bold:none;padding-left:5px;">蔬菜</td>
										</tr>
										<tr>
											<td style="color:#999;font-size:13px;padding-left:5px;">提供人体必需的维生素和氨基酸</td>
										</tr>
									</table></td>
								<td><img id="qt2right" class="y-right"
									src="${pageContext.request.contextPath}/static/images/sol_yishi_right.png" /></td>
							</tr>
						</table>


				</a>
					<ul id="shucai" class="sub-menu">
					</ul>
					<div class="question-line"></div></li>
				<li class="question" id="q3"><a id="qt3" href="#q3">
						<table style="width:100%;height:80px;">
							<tr>
								<td style="width:70px;"><img class="y-icon"
									src="${pageContext.request.contextPath}/static/images/sol_yishi_rou.png" /></td>
								<td><table class="y-table">
										<tr>
											<td style="color:#666;font-size:15px;font-bold:none;padding-left:5px;">肉</td>
										</tr>
										<tr>
											<td style="color:#999;font-size:13px;padding-left:5px;">动物蛋白，为人体提供必需的氨基酸</td>
										</tr>
									</table></td>
								<td><img id="qt3right" class="y-right"
									src="${pageContext.request.contextPath}/static/images/sol_yishi_right.png" /></td>
							</tr>
						</table>


				</a>
					<ul id="rou" class="sub-menu">
					</ul>
					<div class="question-line"></div></li>
				<li class="question" id="q3"><a id="qt3" href="#q3">
						<table style="width:100%;height:80px;">
							<tr>
								<td style="width:70px;"><img class="y-icon"
									src="${pageContext.request.contextPath}/static/images/sol_yishi_gumi.png" /></td>
								<td><table class="y-table">
										<tr>
											<td style="color:#666;font-size:15px;font-bold:none;padding-left:5px;">谷米</td>
										</tr>
										<tr>
											<td style="color:#999;font-size:13px;padding-left:5px;">富含糖类和蛋白，提供能量</td>
										</tr>
									</table></td>
								<td><img id="qt4right" class="y-right"
									src="${pageContext.request.contextPath}/static/images/sol_yishi_right.png" /></td>
							</tr>
						</table>


				</a>
					<ul id="gumi" class="sub-menu">
					</ul>
					<div class="question-line"></div></li>
			</ul>
		</div>
	</div>
	<div class="page" data-role="page" id="pagetwo">
		<div data-role="main" class="ui-content">
			<img id="dImg" class="d-image" alt="">
			<div id="box1" class="d-box" style="display:none;margin-top:6px;">
				<div class="d-title">
					<div class="d-title-bar"></div>
					<div id="title1" class="d-title-name"></div>
				</div>
				<div id="content1" class="d-content"></div>
			</div>

			<div id="box2" class="d-box" style="display:none;">
				<div class="d-title">
					<div class="d-title-bar"></div>
					<div id="title2" class="d-title-name"></div>
				</div>
				<div id="content2" class="d-content"></div>
			</div>
			<div id="box3" class="d-box" style="margin-bottom:10px;display:none;">
				<div class="d-title">
					<div class="d-title-bar"></div>
					<div id="title3" class="d-title-name"></div>
				</div>
				<div id="content3" class="d-content"></div>
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
			jsApiList : ['hideOptionMenu', 'closeWindow' ]
		});
		
		wx.ready(function() {
			wx.hideOptionMenu();
		});
		
		var jq = new Array('0105小寒xiaohan.jpg', '0120大寒dahan.jpg', '0203立春lichun.jpg', '0218雨水yushui.jpg', '0305惊蜇jingzhe.jpg', '0320春分chunfen.jpg', '0404清明qingming.jpg', '0419谷雨guyu.jpg',
				'0505立夏lichun.jpg', '0520小满xiaoman.jpg', '0605芒种mangzhong.jpg', '0621夏至xiazhi.jpg', '0706小暑xiaoshu.jpg', '0722大暑dashu.jpg', '0807立秋liqiu.jpg', '0822处暑chushu.jpg', '0907白露bailu.jpg',
				'0922秋分qiufen.jpg', '1008寒露hanlu.jpg', '1023霜降shuangjiang.jpg', '1107立冬lidong.jpg', '1122小雪xiaoxue.jpg', '1206大雪daxue.jpg', '1221冬至dongzhi.jpg');
		var d = new Date();
		var s = '';
		if (d.getMonth() < 9)
			s += '0';
		s += '' + (d.getMonth() + 1);
		if (d.getDate() < 10)
			s += '0';
		s += '' + (d.getDate());
		var i = 0;
		while (i < jq.length && s >= jq[i])
			i++;
		i--;
		
		$(document).ready(function(){
			// 折叠面板效果js
			var accordion_head = $('.question-accordion > li > a'), accordion_body = $('.question-accordion  li > .sub-menu');
			// Open the first tab on load
			accordion_head
					.removeClass('active');
			
			// Click function
			accordion_head
					.on(
							'click',
							function(event) {
								// Disable header links
								event
										.preventDefault();
								// Show and hide the tabs on click
								if ($(this)
										.attr(
												'class') != 'active') {
									accordion_body
											.slideUp(100);
									$(this)
											.next()
											.stop(
													true,
													true)
											.slideToggle(
													100);
									accordion_head
											.removeClass('active');
									$(this)
											.addClass(
													'active');
									$(".y-right").attr("src","${pageContext.request.contextPath}/static/images/sol_yishi_right.png");
									$("#"+this.id+"right").attr("src","${pageContext.request.contextPath}/static/images/sol_yishi_down.png");
								}
							});
			
		});
		
		$("#yImage").attr("src",  "http://www.qingliao365.com/jhResc/jieqishiwu/" + jq[i].substr(6));
		
		var yishi = ${res};
		$.each(yishi.shuiguo, function(i, item) {
			$("#shuiguo").append('<span onclick="detail(\''+item.name+'\',\''+item.image+'\',\''+item.title1+'\',\''+item.content1+'\',\''+item.title2+'\',\''+item.content2+'\',\''+item.title3+'\',\''+item.content3+'\')" class="y-item">'+item.name+'</span>');
		});
		
		$.each(yishi.shucai, function(i, item) {
			$("#shucai").append('<span onclick="detail(\''+item.name+'\',\''+item.image+'\',\''+item.title1+'\',\''+item.content1+'\',\''+item.title2+'\',\''+item.content2+'\',\''+item.title3+'\',\''+item.content3+'\')" class="y-item">'+item.name+'</span>');
		});
		
		$.each(yishi.roulei, function(i, item) {
			$("#rou").append('<span onclick="detail(\''+item.name+'\',\''+item.image+'\',\''+item.title1+'\',\''+item.content1+'\',\''+item.title2+'\',\''+item.content2+'\',\''+item.title3+'\',\''+item.content3+'\')" class="y-item">'+item.name+'</span>');
		});
		
		$.each(yishi.gulei, function(i, item) {
			$("#gumi").append('<span onclick="detail(\''+item.name+'\',\''+item.image+'\',\''+item.title1+'\',\''+item.content1+'\',\''+item.title2+'\',\''+item.content2+'\',\''+item.title3+'\',\''+item.content3+'\')" class="y-item">'+item.name+'</span>');
		});
		
		$(document).on("pagebeforeshow","#pageone",function(event){
			 var $body = $('body');
		        document.title = "宜 食";
		        // hack在微信等webview中无法修改document.title的情况
		        var $iframe = $('<iframe src="/favicon.ico"></iframe>');
		        $iframe.on('load',function() {
		            setTimeout(function() {
		                $iframe.off('load').remove();
		            }, 0);
		        }).appendTo($body);
		        wx.hideOptionMenu();
		});
		
		function detail(name,image,title1,content1,title2,content2,title3,content3){
			$.mobile.changePage("#pagetwo", {
				transition : "slide"
			});
			 var $body = $('body');
		        document.title = name;
		        // hack在微信等webview中无法修改document.title的情况
		        var $iframe = $('<iframe src="/favicon.ico"></iframe>');
		        $iframe.on('load',function() {
		            setTimeout(function() {
		                $iframe.off('load').remove();
		            }, 0);
		        }).appendTo($body);
		        wx.hideOptionMenu();
			$("#dImg").attr("src","http://www.qingliao365.com/jhResc/remission/"+image);
			if(title1 != "" && title1 != null && title1 !="undefined"){
				$("#title1").html(title1);
				$("#content1").html(content1);
				$("#box1").css("display","block");
			}
			if(title2 != "" && title2 != null && title2 !="undefined"){
				$("#title2").html(title2);
				$("#content2").html(content2);
				$("#box2").css("display","block");
			}
			if(title3 != "" && title3 != null && title3 !="undefined"){
				$("#title3").html(title3);
				$("#content3").html(content3);
				$("#box3").css("display","block");
			}
		}
	</script>
</body>
</html>
