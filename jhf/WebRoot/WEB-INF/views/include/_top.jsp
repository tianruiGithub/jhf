<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="m-top">
	<div class="m-top-title">见好后台管理系统</div>
	<div class="m-top-login mLogin">${sessionScope.User}</div>
	<div class="m-top-logout mLogin">
		<a href="${pageContext.request.contextPath}/logout"
			onclick="{if(confirm('确定要退出吗?')){return true;}return false;}">退 出</a>
	</div>
</div>
 