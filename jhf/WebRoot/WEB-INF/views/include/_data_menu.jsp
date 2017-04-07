<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="m-item">
	<div class="m-item-menu ${menu_data_demand}" style="margin-top: 18px;">
		<a href="${pageContext.request.contextPath}/data/demand"><i
			class="Hui-iconfont Hui-iconfont-tags"></i>&nbsp;需 求</a>
	</div>
	<div class="m-item-menu ${menu_data_physique}">
		<a href="${pageContext.request.contextPath}/data/physique/list"><i
			class="Hui-iconfont Hui-iconfont-practice"></i>&nbsp;体 质</a>
	</div>
	<div class="m-item-menu ${menu_data_xuewei}">
		<a href="${pageContext.request.contextPath}/data/xuewei/list"><i
			class="Hui-iconfont Hui-iconfont-lunzi"></i>&nbsp;穴 位</a>
	</div>
	<div class="m-item-menu ${menu_data_tiaoli}">
		<a href="${pageContext.request.contextPath}/data/tiaoli/list"><i
			class="Hui-iconfont Hui-iconfont-xuanzhuan"></i>&nbsp;调 理</a>
	</div>
	<div class="m-item-menu ${menu_data_image}">
		<a href="${pageContext.request.contextPath}/data/image/list"><i
			class="Hui-iconfont Hui-iconfont-datumoshi"></i>&nbsp;图 库</a>
	</div>
	<div class="m-item-menu ${menu_data_pifuSol}">
		<a href="${pageContext.request.contextPath}/data/pifu_sol/list"><i
			class="Hui-iconfont Hui-iconfont-xiangpicha"></i>&nbsp;肤质方案</a>
	</div>
</div>