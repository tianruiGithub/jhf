package com.jhs.controller.weixin;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.kit.PropKit;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.api.ApiResult;
import com.jfinal.weixin.sdk.api.SnsAccessToken;
import com.jfinal.weixin.sdk.api.SnsAccessTokenApi;
import com.jfinal.weixin.sdk.api.SnsApi;
import com.jfinal.weixin.sdk.api.UserApi;
import com.jfinal.weixin.sdk.interceptor.JSSDKInterceptor;
import com.jfinal.weixin.sdk.jfinal.ApiController;
import com.jfinal.weixin.sdk.utils.WeiXinUtils;
import com.jhs.common.model.ConfWeixin;
import com.jhs.common.model.DataChanpin;
import com.jhs.common.model.DataSolution;
import com.jhs.common.model.RecordUser;
import com.jhs.service.data.DataChanpinService;
import com.jhs.service.data.DataSolutionService;
import com.jhs.service.data.RecordUserService;
import com.jhs.service.system.ConfWeixinService;
import com.jhs.util.DateUtil;

/**
 * 微信
 * 
 * @author 天瑞
 *
 */
public class WeixinController extends ApiController {

	@Override
	public ApiConfig getApiConfig() {
		ApiConfig ac = new ApiConfig();
		ConfWeixin cw = ConfWeixinService.me.queryById(1);
		// 配置微信 API 相关常量
		ac.setToken(cw.getToken());
		ac.setAppId(cw.getWeixinAppid());
		ac.setAppSecret(cw.getWeixinSecret());
		/**
		 * 是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey
		 * 2：false采用明文模式，同时也支持混合模式
		 */
		if (cw.getEncryptMessage().equals("0")) {
			ac.setEncryptMessage(false);
			ac.setEncodingAesKey("setting it in config");
		} else {
			ac.setEncryptMessage(true);
			ac.setEncodingAesKey(cw.getEncodingAeskey());
		}
		return ac;
	}

	/**
	 * 网页授权认证
	 */
	public void oauth() {
		int subscribe = 0;
		// 用户同意授权，获取code
		String code = getPara("code");
		String state = getPara("state");
		if (code != null) {
			String appId = ApiConfigKit.getApiConfig().getAppId();
			String secret = ApiConfigKit.getApiConfig().getAppSecret();
			// 通过code换取网页授权access_token
			SnsAccessToken snsAccessToken = SnsAccessTokenApi.getSnsAccessToken(appId, secret, code);
			String token = snsAccessToken.getAccessToken();
			String openId = snsAccessToken.getOpenid();
			// 拉取用户信息(需scope为 snsapi_userinfo)
			ApiResult apiResult = SnsApi.getUserInfo(token, openId);
			if (apiResult.isSucceed()) {
				// 获取用户信息判断是否关注
				ApiResult userInfo = UserApi.getUserInfo(openId);
				if (userInfo.isSucceed()) {
					String userStr = userInfo.toString();
					subscribe = JSON.parseObject(userStr).getIntValue("subscribe");
					// 保存或更行用户信息
					RecordUser ru = RecordUserService.me.queryById(openId);
					if (ru != null) {
						ru.setUserId(openId);
						ru.setUserNickname(
								WeiXinUtils.filterWeixinEmoji(JSON.parseObject(userStr).getString("nickname")));
						ru.setUserSex(JSON.parseObject(userStr).getString("sex"));
						ru.setUserCountry(JSON.parseObject(userStr).getString("country"));
						ru.setUserProvince(JSON.parseObject(userStr).getString("province"));
						ru.setUserCity(JSON.parseObject(userStr).getString("city"));
						ru.setUserHead(JSON.parseObject(userStr).getString("headimgurl"));
						ru.setUserSubscribeTime(
								DateUtil.formatTime(JSON.parseObject(userStr).getIntValue("subscribe_time")));
						RecordUserService.me.update(ru);
					} else {
						ru = new RecordUser();
						ru.setUserId(openId);
						ru.setUserNickname(
								WeiXinUtils.filterWeixinEmoji(JSON.parseObject(userStr).getString("nickname")));
						ru.setUserSex(JSON.parseObject(userStr).getString("sex"));
						ru.setUserCountry(JSON.parseObject(userStr).getString("country"));
						ru.setUserProvince(JSON.parseObject(userStr).getString("province"));
						ru.setUserCity(JSON.parseObject(userStr).getString("city"));
						ru.setUserHead(JSON.parseObject(userStr).getString("headimgurl"));
						ru.setUserSubscribeTime(
								DateUtil.formatTime(JSON.parseObject(userStr).getIntValue("subscribe_time")));
						RecordUserService.me.create(ru);
					}
				}
			}
			// 缓存信息
			setSessionAttr("OpenId", openId);
			// 未关注先关注
			if (subscribe == 0) {
				renderText("请先关注[见好]公众号！");
			} else {
				// 根据state 跳转到不同的页面
				switch (state) {
				case "pifu":
					redirect(PropKit.get("url") + "/weixin/pifu");
					break;
				case "richang":
					redirect(PropKit.get("url") + "/weixin/richang");
					break;
				case "qudou":
					redirect(PropKit.get("url") + "/weixin/qudou");
					break;
				default:
					renderText("授权链接无效！");
					break;
				}
			}
		} else {
			renderText("请先通过微信网页授权认证！");
		}
	}

	/**
	 * 皮肤测试
	 */
	@Before(JSSDKInterceptor.class)
	public void pifu() {
		setAttr("openId", getSessionAttr("OpenId"));
		render("test_pifu.jsp");
	}

	/**
	 * 祛痘测试
	 */
	@Before(JSSDKInterceptor.class)
	public void qudou() {
		setAttr("openId", getSessionAttr("OpenId"));
		render("test_qudou.jsp");
	}

	/**
	 * 日常护理测试
	 */
	@Before(JSSDKInterceptor.class)
	public void richang() {
		setAttr("openId", getSessionAttr("OpenId"));
		render("test_richang.jsp");
	}

	/**
	 * 方案-主页
	 */
	@Before(JSSDKInterceptor.class)
	public void solution() {
		DataSolution ds = DataSolutionService.me.queryByNo(getPara("no"));
		if (ds != null) {
			setAttr("solution_no", ds.getSolutionNo());
			setAttr("solution_demand_name", ds.get("demand_name"));
			setAttr("solution_demand_second_name", ds.get("demand_second_name"));
			setAttr("solution_physique_no", ds.getPhysiqueNo());
			setAttr("solution_physique_name", ds.get("physique_name"));
			DataChanpin dc = DataChanpinService.me.queryByPhysique(ds.getPhysiqueNo());
			if (dc != null) {
				setAttr("solution_chanpin_link", dc.getChanpinLink());
				setAttr("solution_chanpin_image", dc.getChanpinImage());
				setAttr("solution_chanpin_color", dc.getChanpinColor());
			}
		}
		render("solution.jsp");
	}
	/**
	 * 方案-面膜
	 */
	@Before(JSSDKInterceptor.class)
	public void mianmo(){
		DataSolution ds = DataSolutionService.me.queryByPhysique(getPara("no"));
		if(ds != null)
			setAttr("res",ds.getSolutionMianmo());
		render("solution_mianmo.jsp");
	}
	/**
	 * 方案-足浴
	 */
	@Before(JSSDKInterceptor.class)
	public void zuyu(){
		DataSolution ds = DataSolutionService.me.queryByNo(getPara("no"));
		if(ds != null)
			setAttr("res",ds.getSolutionZuyu());
		render("solution_zuyu.jsp");
	}
	/**
	 * 方案-足浴
	 */
	@Before(JSSDKInterceptor.class)
	public void baguan(){
		DataSolution ds = DataSolutionService.me.queryByNo(getPara("no"));
		if(ds != null)
			setAttr("res",ds.getSolutionBaguan());
		render("solution_baguan.jsp");
	}
	/**
	 * 方案-茶饮
	 */
	@Before(JSSDKInterceptor.class)
	public void cha(){
		DataSolution ds = DataSolutionService.me.queryByNo(getPara("no"));
		if(ds != null)
			setAttr("res",ds.getSolutionChayin());
		render("solution_cha.jsp");
	}
	/**
	 * 方案-易食
	 */
	@Before(JSSDKInterceptor.class)
	public void yishi(){
		DataSolution ds = DataSolutionService.me.queryByPhysique(getPara("no"));
		if(ds != null)
			setAttr("res",ds.getSolutionYishi());
		render("solution_yishi.jsp");
	}
	/**
	 * 方案-易食
	 */
	@Before(JSSDKInterceptor.class)
	public void jishi(){
		DataSolution ds = DataSolutionService.me.queryByPhysique(getPara("no"));
		if(ds != null)
			setAttr("res",ds.getSolutionJishi());
		render("solution_jishi.jsp");
	}
}
