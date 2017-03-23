<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="m-item">
	<div class="m-item-menu ${menu_system_weixin}" style="margin-top: 18px;">
		<a href="${pageContext.request.contextPath}/system/weixin/edit">微信设置</a>
	</div>
	<div class="m-item-menu ${menu_system_oauth}">
		<a href="${pageContext.request.contextPath}/system/oauth/list"><i
			class="Hui-iconfont Hui-iconfont-link"></i>&nbsp;授 权</a>
	</div>
</div>