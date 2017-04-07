<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="m-left">
  <div class="m-block">
    <img class="m-logo" src="${pageContext.request.contextPath}/static/images/logo.jpg" />
  </div>
  <div class="m-block">
    <div class="m-menu">
      <div class="m-menu-content ${menu_main}">
        <a href="${pageContext.request.contextPath}/main">&nbsp;<i
          class="glyphicon glyphicon-home"></i> <span>概 况</span>
        </a>
      </div>
      <div class="m-menu-content ${menu_data}">
        <a href="${pageContext.request.contextPath}/data/demand"> &nbsp;<i
          class="glyphicon glyphicon-th-list"></i> <span>管 理 </span>
        </a>
      </div>
      <div class="m-menu-content ${menu_activity}">
        <a href="${pageContext.request.contextPath}/manage/goods/list"> &nbsp;<i
          class="glyphicon glyphicon-tree-deciduous"></i> <span>活 动</span>
        </a>
      </div>
      <div class="m-menu-content ${menu_youzan}">
        <a href="${pageContext.request.contextPath}/youzan/goods/list"> &nbsp;<i
          class="glyphicon glyphicon-thumbs-up"></i> <span>有 赞</span>
        </a>
      </div>
      <div class="m-menu-content ${menu_graph}">
        <a href="${pageContext.request.contextPath}/graph/piao/list"> &nbsp;<i
          class="glyphicon glyphicon-signal"></i> <span>统 计</span>
        </a>
      </div>
      <div class="m-menu-content ${menu_system}">
        <a href="${pageContext.request.contextPath}/system/weixin/edit"> &nbsp;<i
          class="glyphicon glyphicon-wrench"></i> <span>系 统</span>
        </a>
      </div>
    </div>
  </div>
  <div class="m-menu-bottom" data-container="body" data-toggle="popover" title="${user_name}"
    data-placement="top" data-trigger="hover" data-content="${user_phone}">见好</div>
</div>
