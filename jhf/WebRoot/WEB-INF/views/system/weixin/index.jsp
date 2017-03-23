<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../../include/_meta.jsp" />
<link href="${pageContext.request.contextPath}/static/plugins/layui/css/layui.css" rel="stylesheet">
<body>
  <jsp:include page="../../include/_left.jsp" />
  <div class="m-right">
    <jsp:include page="../../include/_top.jsp" />
    <nav class="breadcrumb m-breadcrumb">
      <i class="glyphicon glyphicon-home"></i>&nbsp;概 况
    </nav>
    <jsp:include page="../../include/_system_menu.jsp" />
    <div class="m-content">
      <div class="m-container" style="text-align: center;">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px; width: 96%;">
          <legend>微信公众号参数设置</legend>
        </fieldset>
        <form id="mUpdateForm" class="layui-form" action="update" style="width: 96%;">
          <div class="layui-form-item">
            <label class="layui-form-label" style="width: 130px;">微信应用ID:</label>
            <div class="layui-input-block" style="margin-left: 130px;">
              <input name="weixin.weixin_appid" value="${weixin.weixin_appid}" class="layui-input"
                type="text" lay-verify="required">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" style="width: 130px;">微信应用密钥:</label>
            <div class="layui-input-block" style="margin-left: 130px;">
              <input name="weixin.weixin_secret" value="${weixin.weixin_secret}" class="layui-input"
                type="text" lay-verify="required">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" style="width: 130px;">token:</label>
            <div class="layui-input-block" style="margin-left: 130px;">
              <input name="weixin.token" value="${weixin.token}" class="layui-input" type="text">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" style="width: 130px;">是否加密:</label>
            <div class="layui-input-block" style="margin-left: 130px;">
              <select id="mIsEncrypt" name="weixin.encrypt_message" data-ref="${weixin.encrypt_message}" lay-filter="isEncrypt">
                <option value="0" selected>否</option>
                <option value="1">是</option>
              </select>
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" style="width: 130px;">加密密钥:</label>
            <div class="layui-input-block" style="margin-left: 130px;">
              <input id="mEncoding" name="weixin.encoding_aeskey" value="${weixin.encoding_aeskey}"
                class="layui-input" type="text" lay-verify="isEncoding">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" style="width: 130px;">关注链接:</label>
            <div class="layui-input-block" style="margin-left: 130px;">
              <input name="weixin.subscribe_url" value="${weixin.subscribe_url}" class="layui-input"
                type="text">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" style="width: 130px;">微信商户ID:</label>
            <div class="layui-input-block" style="margin-left: 130px;">
              <input name="weixin.mch_id" value="${weixin.mch_id}" class="layui-input" type="text">
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label" style="width: 130px;">微信商户密钥:</label>
            <div class="layui-input-block" style="margin-left: 130px;">
              <input name="weixin.mch_key" value="${weixin.mch_key}" class="layui-input" type="text">
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block">
              <button class="layui-btn" lay-submit lay-filter="update">立即提交</button>
              <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
          </div>
        </form>
      </div>
      <jsp:include page="../../include/_bottom.jsp" />
    </div>
  </div>
  <script type="text/javascript"
    src="${pageContext.request.contextPath}/static/plugins/layui/layui.js"></script>
  <script type="text/javascript"
    src="${pageContext.request.contextPath}/static/script/system/weixin.js"></script>
</body>
</html>