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

<title>忌 食</title>
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
.j-title{
	float:left;
	width:100%;
	height:35px;
	background-color:#f0f0f0;
	font-size:13px;
	color:#333;
	padding-left:10px;
	line-height:35px;
}
.j-content{
	float:left;
	width:100%;
	min-height:60px;
}
</style>
</head>

<body>
	<div class="page" data-role="page" id="pageone">
		<div data-role="main" class="ui-content">
			<img id="jImage" style="float:left;width:100%;"/>
			<div class="j-title">
				高脂类食物
			</div>
			<div id="gaozhi" class="j-content">
			</div>
			<div class="j-title">
				腥发类食物
			</div>
			<div id="xingfa" class="j-content">
			</div>
			<div class="j-title">
				高糖类食物
			</div>
			<div id="gaotang" class="j-content">
			</div>
			<div class="j-title">
				辛辣类食物
			</div>
			<div id="xinla" class="j-content">
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
		$("#jImage").attr("src",  "http://www.qingliao365.com/jhResc/jieqishiwu/" + jq[i].substr(6));
		
		var jishi = ${res};
		$.each(jishi.gaozhi, function(i, item) {
			$("#gaozhi").append('<img style="float:left;width:18%;margin-top:10px;margin-left:1.6%;" src="http://www.qingliao365.com/jhResc/jishi/'+item.name+'"/>');
		});
		$.each(jishi.xingfa, function(i, item) {
			$("#xingfa").append('<img style="float:left;width:18%;margin-top:10px;margin-left:1.6%;" src="http://www.qingliao365.com/jhResc/jishi/'+item.name+'"/>');
		});
		$.each(jishi.gaotang, function(i, item) {
			$("#gaotang").append('<img style="float:left;width:18%;margin-top:10px;margin-left:1.6%;" src="http://www.qingliao365.com/jhResc/jishi/'+item.name+'"/>');
		});
		$.each(jishi.xinla, function(i, item) {
			$("#xinla").append('<img style="float:left;width:18%;margin-top:10px;margin-left:1.6%;" src="http://www.qingliao365.com/jhResc/jishi/'+item.name+'"/>');
		});
	</script>
</body>
</html>
