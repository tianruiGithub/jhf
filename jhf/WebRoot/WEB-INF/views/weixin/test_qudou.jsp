<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,user-scalable=no">
	<title>祛痘检测</title>
<link rel="icon"
	href="${pageContext.request.contextPath}/static/images/favicon.icon"
	type="image/gif">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/weixin.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/plugins/cropper/cropper.css">
</head>
<body onload="init('${openId}')">
	<div class="w-panel-group">
		<div id="page0" class="w-panel">
			<img id="kaishi_bg"
				style="position: absolute; top: 0; left: 0; width: 100%; z-index: 1;"
				src="${pageContext.request.contextPath}/static/images/check_main_bg.jpg" />
			<div id="kaishi_box"
				style="position: absolute; top: 0; left: 0; width: 100%; z-index: 66;">
				<div style="float: left; width: 100%; margin-top: 30px;">
					<center>
						<img id="kaishi_s" style="width: 100px;"
							src="${pageContext.request.contextPath}/static/images/check_main1.png" />
					</center>
				</div>
				<div style="float: left; width: 100%; margin-top: 30px;">
					<center>
						<img id="kaishi_w" style="width: 250px;"
							src="${pageContext.request.contextPath}/static/images/check_main2.png" />
					</center>
				</div>
				<div style="float: left; width: 100%; margin-top: 50px;">
					<img id="kaishi_left" style="float: left; width: 170px;"
						src="${pageContext.request.contextPath}/static/images/check_main3.png" />
					<img id="kaishi_right"
						style="float: right; width: 137px; margin-right: 20px; margin-top: 20px;"
						src="${pageContext.request.contextPath}/static/images/check_main4.png" />
				</div>
				<div id="kaishi_div"
					style="float: left; width: 100%; margin-top: 40px;">
					<center>
						<img id="kaishi" style="width: 110px;"
							src="${pageContext.request.contextPath}/static/images/check_main5.png" />
					</center>
				</div>
				<div id="kaishi_w2"
					style="float: left; width: 100%; margin-top: 20px; text-align: center; color: #999; font-size: 14px;">
					本测试系统受国家专利保护</div>
			</div>
		</div>
		<div id="page1" class="w-panel">
			<div class="w-panel-title">选择痘痘所在的区域</div>
			<div class="w-panel-content">
				<table class="step">
					<tr>
						<td align="center"><img class="step-img"
							src="${pageContext.request.contextPath}/static/images/check_step1.png" /></td>
					</tr>
					<tr>
						<td align="center" class="step-tip">检测第一步，认真选择才有效哟</td>
					</tr>
				</table>

				<table class="face" style="margin-top:50px;">
					<tr>
						<td align="center">
							<div class="face-box">
								<img class="face-img" src="${pageContext.request.contextPath}/static/images/check_face.png" />
								<div id="faceType1" class="face-type face-type1">
									<img id="faceType1Image" class="face-type-img"
										src="${pageContext.request.contextPath}/static/images/check_unselect.png" /> <span
										class="face-type-span">额头</span>
								</div>
								<div id="faceType2" class="face-type face-type2">
									<img id="faceType2Image" class="face-type-img"
										src="${pageContext.request.contextPath}/static/images/check_unselect.png" /> <span
										class="face-type-span">面颊</span>
								</div>
								<div id="faceType3" class="face-type face-type3">
									<img id="faceType3Image" class="face-type-img"
										src="${pageContext.request.contextPath}/static/images/check_unselect.png" /> <span
										class="face-type-span">鼻部</span>
								</div>
								<div id="faceType4" class="face-type face-type4">
									<img id="faceType4Image" class="face-type-img"
										src="${pageContext.request.contextPath}/static/images/check_unselect.png" /> <span
										class="face-type-span">口唇和下巴</span>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center" class="face-tip" style="height:50px;">哪个位置<span
							style="color:#fe6b5b;">最容易</span>起痘?
						</td>
					</tr>
					<tr>
						<td align="center" style="height:80px;"><button id="first"
								class="check-button">下一步</button></td>
					</tr>
				</table>
			</div>

		</div>
		<div id="page2" class="w-panel">
			<div class="w-panel-title">舌像拍照</div>
			<div class="w-panel-content">
				<table class="step">
					<tr>
						<td align="center"><img class="step-img"
							src="${pageContext.request.contextPath}/static/images/check_step2.png" /></td>
					</tr>
					<tr>
						<td align="center" class="step-tip">请选择光线充足的环境拍摄</td>
					</tr>
				</table>
				<table class="paizhao">
					<tr>
						<td align="center" class="paizhao-tip1">舌诊为中医重要诊断方法，请在良好的光照条件下拍摄舌头照片，拍照时请将舌头尽量伸出！</td>
					</tr>
					<tr>
						<td align="center" class="paizhao-tip2">（可以试着用舌头努力舔下巴）</td>
					</tr>
					<tr>
						<td align="center"><img class="paizhao-img"
							src="${pageContext.request.contextPath}/static/images/check_shetou.png" /></td>
					</tr>
					<tr>
						<td align="center">
							<button id="second-paizhao" class="check-button">光线良好开始拍照</button>
							<input style="display: none;" id="paizhao" type="file"
							accept="image/*" capture="camera" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<button id="second-skip" class="check-button"
								style="background-color: #00b1ff; border: 1px solid #00b1ff;">光线不好跳过拍照进行答题</button>
						</td>
					</tr>
					<tr>
						<td align="center" class="paizhao-tip3">系统仅截取照片的舌像部位，放心拍照！</td>
					</tr>
				</table>
			</div>

		</div>
		<div id="page3" class="w-panel">
			<div class="w-panel-title">舌像拍照</div>
			<div class="w-panel-content">
				<table class="step">
					<tr>
						<td align="center"><img class="step-img"
							src="${pageContext.request.contextPath}/static/images/check_step2.png" /></td>
					</tr>
					<tr>
						<td align="center" class="step-tip">请在光线充足的环境拍照</td>
					</tr>
				</table>
				<table class="paizhao">
					<tr>
						<td align="center" class="paizhao-tip1">放大并移动图片,将舌头移动到框内</td>
					</tr>
					<tr>
						<td align="center" class="paizhao-tip2">(请将舌尖边缘和绿色边框对齐)</td>
					</tr>
					<tr>
						<td align="center">
							<div
								style="margin-top: 2px; width: 300px; height: 300px; border: 0px solid #d0d0d0;">
								<img
									style="position: relative; top: 0px; left: 0px; width: 300px; z-index: 77;"
									src="${pageContext.request.contextPath}/static/images/check_shetou1.png" />
								<img
									style="position: relative; top: 0px; left: -43px; width: auto; z-index: 88;"
									src="${pageContext.request.contextPath}/static/images/check_shetou2.png" />
								<img
									style="position: relative; top: 0px; left: 146px; width: auto; z-index: 99;"
									src="${pageContext.request.contextPath}/static/images/check_shetou6.png" />
								<img
									style="position: relative; top: 120px; left: -157px; width: auto; z-index: 88;"
									src="${pageContext.request.contextPath}/static/images/check_shetou3.png" />
								<img
									style="position: relative; top: 0px; left: 155px; width: auto; z-index: 88;"
									src="${pageContext.request.contextPath}/static/images/check_shetou5.png" />
								<img
									style="position: relative; top: 31px; left: -51px; width: auto; z-index: 88;"
									src="${pageContext.request.contextPath}/static/images/check_shetou4.png" />
								<div id="tongueImageDiv"
									style="margin-top: -300px; width: 300px; height: 300px;"></div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="center">
							<div style="width: 300px; height: 100px;">
								<button id="third-back" class="check-button"
									style="float: left; margin-left: 0; width: 100px; height: 35px; line-height: 35px; padding: 0px;">
									取消</button>
								<button id="third-cropper"
									style="outline: 0px; float: right; margin-top: 10px; padding: 0px; line-height: 35px; width: 100px; height: 35px; background-color: #fe6b5b; border: 1px solid #fe6b5b; border-radius: 3px; color: white; padding-left: 5px; padding-right: 5px;">
									截取并提交</button>
								<button id="third-rotate"
									class="ui-btn ui-icon-refresh ui-btn-icon-left"
									style="outline: 0px; line-height: 35px; margin-top: 10px; padding: 0px; float: right; width: 40px; margin-right: 5px; height: 35px; background-color: #fe6b5b; border: 1px solid #fe6b5b; border-radius: 3px; color: white;">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>

						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="page4" class="w-panel">
			<div class="w-panel-title">辩证答题</div>
			<div class="w-panel-content">
				<table class="step">
					<tr>
						<td align="center"><img class="step-img"
							src="${pageContext.request.contextPath}/static/images/check_step3.png" /></td>
					</tr>
					<tr>
						<td align="center" class="step-tip">认真回答，方案才更精准</td>
					</tr>
				</table>
				<ul id="questioninfo" class="question-accordion"></ul>
				<button id="four" class="check-button" style="margin-bottom: 10px;">提
					交</button>
			</div>
		</div>
		<div id="page5" class="w-panel">
			<div class="w-panel-title">检测分析中</div>
			<div class="w-panel-content">
				<table class="step">
					<tr>
						<td align="center"><img class="step-img"
							src="${pageContext.request.contextPath}/static/images/check_step4.png" /></td>
					</tr>
					<tr>
						<td align="center" class="step-tip">别急，一会儿就好</td>
					</tr>
				</table>
				<table style="float: left; width: 100%;">
					<tr>
						<td align="center"><img
							style="width: 250px; margin-top: 60px;"
							src="${pageContext.request.contextPath}/static/images/check_deal.png" /></td>
					</tr>
				</table>
				<div class="jindu">
					<div class="jindus"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
<script
	src="${pageContext.request.contextPath}/static/script/weixin/qudou.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/plugins/cropper/cropper.js"></script>
<script type="text/javascript">
	wx.config({
		debug : false,
		appId : '${appId}',
		timestamp : '${timestamp}',
		nonceStr : '${nonceStr }',
		signature : '${signature}',
		jsApiList : [ 'chooseImage', 'uploadImage', 'hideOptionMenu',
				'closeWindow' ]
	});
	 
	wx.ready(function() {
		wx.hideOptionMenu();
	});

	function closeWindow() {
		wx.closeWindow();
	}
</script>
</html>
