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

<title>拔 罐</title>
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
	background-color: #fff;
}

.page {
	background-color: #fff;
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

.b-tip {
	float: left;
	width: 100%;
	height: 40px;
	text-align: center;
	line-height: 40px;
	color: #333;
	margin-top: 100px;
	font-size: 25px;
}

.b-value {
	float: left;
	width: 100%;
	height: 40px;
	text-align: center;
	line-height: 40px;
	color: #fe6b5b;
	font-size: 20px;
}
.b-image{
	float:left;
	width:100%;
}
.b-box {
	float: left;
	width: 100%;
	min-height: 50px;
	margin-top: 10px;
	background-color: white;
}

.b-title {
	float: left;
	width: 100%;
	height: 30px;
	border-bottom: 1px solid #f0f0f0;
}

.b-title-bar {
	float: left;
	width: 8px;
	height: 16px;
	background-color: #fe6b5b;
	margin-left: 10px;
	margin-top: 7px;
}

.b-title-name {
	float: left;
	width: auto;
	height: 30px;
	line-height: 30px;
	margin-left: 5px;
	font-size: 14px;
	color: #fe6b5b;
	padding-top:1px;
}

.b-content {
	float: left;
	width: 100%;
}

.b-word {
	float: left;
	margin: 0px;
	padding: 10px;
	color: #333;
	font-size: 13px;
	color: #333;
}
.circle {
	margin-left:50%;
	left:-75px;
    width: 150px;
    height: 150px;  
    position: absolute;
    border-radius: 50%;
    background: #fe6b5b;
}
.pie_left, .pie_right {
    width: 150px; 
    height: 150px;
    position: absolute;
    top: 0;left: 0;
}
.left, .right {
    display: block;
    width:150px; 
    height:150px;
    background:#d0d0d0;
    border-radius: 50%;
    position: absolute;
    top: 0;
    left: 0;
}
.pie_right, .right {
    clip:rect(0,auto,auto,75px);
}
.pie_left, .left {
    clip:rect(0,75px,auto,0);
}
.mask {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    left: 15px;
    top: 15px;
    background: #FFF;
    position: absolute;
    text-align: center;
    line-height: 120px;
    font-size: 30px;
}

</style>
</head>

<body>
	<div class="page" data-role="page" id="page0">
		<div data-role="main" class="ui-content">
			<div class="b-tip">拔罐器数量</div>
			<div id="bAmount" class="b-value"></div>
			<div class="b-tip" style="margin-top:50px;">预计耗时</div>
			<div id="bTime" class="b-value"></div>
			<table style="float:left;width:100%;">
				<tr>
					<td align="center">
						<button onclick="goPage(1)" style="font-size:20px;color:white;margin-top:30px;text-shadow: 0 0px 0 #fff;width:100px;height:100px;border:1px solid #fe6b5b;background-color:#fe6b5b;border-radius:50px;">开 始</button>
					</td>
				</tr>
			</table>
			
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
		
		var baguan = ${res};
		$("#bAmount").html(baguan.xueweiList.length+"个");
		$("#bTime").html(baguan.time+"分钟");
		var n;
		var state = false;
		var time = baguan.time * 60;
		var sum = time;
		var jishi;
		$.each(baguan.xueweiList, function(i, item) {
			$("body").append('<div class="page" data-role="page" id="page'+(i+1)+'"> '+
					'<div data-role="main" class="ui-content" style="background-color:#f0f0f0;">'+
					'	<img class="b-image" src="http://www.qingliao365.com/jhResc/xw/'+item.xuewei.img+'"/>'+
					'<div class="b-box">' + 
						'<div class="b-title">'+
							'<div class="b-title-bar"></div>'+
							'<div class="b-title-name">操作</div>' + 
						'</div>'
						+ '<div class="b-word">請將火罐扣在如圖所示穴位上</div>' +
					'</div>'+
					'<div class="b-box">' + 
					'<div class="b-title">'+
						'<div class="b-title-bar"></div>'+
						'<div class="b-title-name">取法</div>' + 
					'</div>'
					+ '<div class="b-word">'+item.xuewei.qufa+'</div>' +
				'</div>'+
				'<div class="b-box" style="margin-bottom:10px;">' + 
				'<div class="b-title">'+
					'<div class="b-title-bar"></div>'+
					'<div class="b-title-name">功效</div>' + 
				'</div>'
				+ '<div class="b-word">'+item.xuewei.gongxiao+'</div>' +
			'</div>'+
			'<button onclick="goPage('+(i+2)+')" style="margin-bottom:10px;font-size:14px;color:white;margin-top:10px;margin-left:10%;text-shadow: 0 0px 0 #fff;width:80%;height:40px;border:1px solid #fe6b5b;background-color:#fe6b5b;">下一步</button>'+
					'</div>'+
				'</div>');
			n = i+2;
		});
		$("body").append('<div class="page" data-role="page" id="page'+(n)+'"> '+
				'<div data-role="main" class="ui-content" >'+
				'<audio id="audioplayer1" loop="true" style="display:none;" controls="true"/> '+
		          '<img class="b-image" src="${pageContext.request.contextPath}/static/images/sol_baguan_title.jpg"/>  '+
		          '<table style="width:100%;border-top:10px solid #f0f0f0;background-color:white;height:250px;"> '+
		           ' <tr> '+
		              '<td align="center" valign="top" style="width:80px;"/>  '+
		              '<td align="center" valign="top" style="padding-top:50px;">'+
		               ' <center><div class="circle"> '+
		                '  <div class="pie_left"> '+
		                 '   <div class="left"/> '+
		                 ' </div>  '+
		                 ' <div class="pie_right"> '+
		                 '   <div class="right"/> '+
		                '  </div>  '+
		                 ' <div class="mask"> '+
		                  '  <span id="progressValue1"/> '+
		                 ' </div> '+
		               ' </div></center>'+
		             ' </td>  '+
		             ' <td align="center" valign="top" style="width:80px;"> '+
		               ' <img id="bControlImage" onclick="control()"  style="width:60px;margin-top:10px;"'+
		                '  src="${pageContext.request.contextPath}/static/images/sol_baguan_kaishi.png"/> '+
		             ' </td> '+
		            '</tr> '+
		         ' </table>  '+
		          '<table style="float:left;width:100%;height:70px;"> '+
		            '<tr style="height:20px;"> '+
		            '  <td align="center" style="width:50px;"/>  '+
		            '  <td id="progressTime" align="center" style="color:#fe6b5b; font-size:15px;"/>  '+
		            '  <td align="center" style="width:50px;"/> '+
		           ' </tr>  '+
		           ' <tr style="height:50px;"> '+
		             ' <td align="center" style="width:50px;"/>  '+
		             ' <td align="center" style="color:#fe6b5b;">拔罐时不宜走动</td>  '+
		             ' <td align="center" style="width:50px;"/> '+
		           ' </tr> '+
		          '</table> </div></div>');
		wx.ready(function() {
			wx.hideOptionMenu();
		});
		function goPage(val){
			$.mobile.changePage("#page"+val, {
				transition : "slide"
			});
			 wx.hideOptionMenu();
		}
		function control(){
			var rotationMultiplier = 3.6;
			var audio = document.getElementById("audioplayer1");
			audio.volume = 1;
			if (state == false) {
				state = true;
				audio.play();
				$("#bControlImage").attr("src", "${pageContext.request.contextPath}/static/images/sol_baguan_ting.png");
				jishi = setInterval(function() {
					time = time - 1;
					if (time >= 0) {
						var min = parseInt(time / 60);
						var sec = self.time % 60;

						var per = parseInt((time * 100) / sum);
						$("#progressTime").html(min + ":" + returnSec(sec));
						var per = returnFloat(100 - ((time * 100) / (sum * 1.0)));
						$("#progressValue1").html(parseInt(per) + "%");
						if (per < 50) {
							$('.pie_right').css('transform', 'rotate(' + (per * 3.6) + 'deg)');
						} else {
							$('.pie_right').css('transform', 'rotate(' + (0) + 'deg)');
							$('.right').css('background', '#fe6b5b');
							$('.pie_left').css('transform', 'rotate(' + ((per - 50) * 3.6) + 'deg)');
						}

					} else {
						clearInterval(jishi);
						$("#progressTime").html("00:00");
						$("#progressValue1").html("0%");
						audio.src = "http://www.qingliao365.com/jhResc/music/baguanjieshu.mp3";
						audio.play();
						var daojishis = setInterval(function() {
							self.daojishi = self.daojishi - 1;
							if (self.daojishi <= 0) {
								audio.pause();
								self.daojishi = 30;
								clearInterval(daojishis);
							}
						}, 1000);
						alert("本次治疗已完成,继续加油!");
						 

					}
				}, 1000)
			} else {
				state = false;
				audio.pause();
				$("#bControlImage").attr("src", "${pageContext.request.contextPath}/static/images/sol_baguan_kaishi.png");
				clearInterval(jishi);
			}

		}
		
		$(document).on("pageshow","#page"+n,function(){ 
			var audio = document.getElementById("audioplayer1");
			audio.src = "http://www.qingliao365.com/jhResc/music/baguankaishi.mp3";
			$("#progressTime").html("00:00");
			$("#progressValue1").html("0%");
			state = false;
			time = baguan.time * 60;
			$("#bControlImage").attr("src", "${pageContext.request.contextPath}/static/images/sol_baguan_kaishi.png");
			clearInterval(jishi);
			$('.pie_left').css('transform', 'rotate(' + (0) + 'deg)');
			$('.pie_right').css('transform', 'rotate(' + (0) + 'deg)');
			$('.right').css('background', '#d0d0d0');
			 wx.hideOptionMenu();
		});
		
		function returnFloat(value) {
			var value = Math.round(parseFloat(value) * 100) / 100;
			var xsd = value.toString().split(".");
			if (xsd.length == 1) {
				value = value.toString() + ".00";
				return value;
			}
			if (xsd.length > 1) {
				if (xsd[1].length < 2) {
					value = value.toString() + "0";
				}
				return value;
			}
		}
		function returnSec(value) {
			if (value < 10) {
				return "0" + value;
			}
			return value;
		}
	</script>
</body>
</html>
