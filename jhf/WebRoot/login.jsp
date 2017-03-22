<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登 陆</title>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/static/plugins/bootstrap/css/bootstrap.min.css"
  rel="stylesheet">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<link href="${pageContext.request.contextPath}/static/css/common.css" rel="stylesheet">
</head>
<body>
  <div class="m-login-box">
    <div class="m-login-left">
      <div class="m-login-top">
        <img alt="" class="m-login-logo"
          src="${pageContext.request.contextPath}/static/images/logo.jpg">
        <div class="m-login-title">见好后台管理系统</div>
      </div>
      <img class="m-login-bottom" src="${pageContext.request.contextPath}/static/images/login.jpg">
    </div>
    <div class="m-login-right">
      <img alt="" style="float: left;"
        src="${pageContext.request.contextPath}/static/images/shadow.png">
      <div class="m-login-content">
        <div class="m-login-tag">账号登陆</div>
        <form id="mLoginForm" method="post" action="login" class="form form-horizontal">
          <div class="m-login-block">
            <div class="m-login-input">
              <i class="glyphicon glyphicon-user"></i> <input id="adminName" name="adminName"
                type="text" class="input-text" placeholder="请输入账号" />
            </div>
            <div class="m-login-input" style="margin-top: 30px;">
              <i class="glyphicon glyphicon-asterisk"></i> <input id="adminPwd" name="adminPwd"
                type="password" class="input-text" placeholder="请输入密码" />
            </div>
            <div style="float: left; width: 100%; margin-top: 30px;">
              <div class="m-login-input" style="width: 140px;">
                <i class="glyphicon glyphicon-cog"></i> <input name="captcha" placeholder="验证码"
                  class="input-text" type="text" style="width: 97px;" />
              </div>
              <img id="adminCaptcha" onclick="changeCaptcha()"
                style="cursor: pointer; float: right; margin-right: 10%; margin-top: 11px;"
                src="${pageContext.request.contextPath}/captcha">
            </div>
            <button id="mLoginButton" class="m-login-button" type="submit">登 陆</button>
          </div>
        </form>
      </div>
      <img alt="" style="float: left;"
        src="${pageContext.request.contextPath}/static/images/shadow.png">
    </div>
    <footer class="m-login-footer">
      <div class="container-fluid">
        <p style="text-align: center;">Copyright &copy;2017 吉林市碾子科技 All Rights Reserved.</p>
      </div>
    </footer>
  </div>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="${pageContext.request.contextPath}/static/js/jquery-3.1.1.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="${pageContext.request.contextPath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/static/js/jquery.form.js"></script>
  <script type="text/javascript"
    src="${pageContext.request.contextPath}/static/plugins/layer/2.4/layer.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/login.js"></script>
</body>
</html>