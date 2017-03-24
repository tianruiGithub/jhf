/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.jfinal;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NotAction;
import com.jfinal.kit.HttpKit;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.api.ApiConfigKit;
import com.jfinal.weixin.sdk.kit.MsgEncryptKit;
import com.jfinal.weixin.sdk.msg.InMsgParser;
import com.jfinal.weixin.sdk.msg.in.*;
import com.jfinal.weixin.sdk.msg.in.event.*;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.OutMsg;
import com.jfinal.weixin.sdk.msg.out.OutTextMsg;

/**
 * 接收微信服务器消息，自动解析成 InMsg 并分发到相应的处理方法
 */
public abstract class MsgController extends Controller {
	
	private static final Log log =  Log.getLog(MsgController.class);
	private String inMsgXml = null;		// 本次请求 xml数据
	private InMsg inMsg = null;			// 本次请求 xml 解析后的 InMsg 对象
	
	public abstract ApiConfig getApiConfig();
	
	/**
	 * weixin 公众号服务器调用唯一入口，即在开发者中心输入的 URL 必须要指向此 action
	 */
	@Before(MsgInterceptor.class)
	public void index() {
		// 开发模式输出微信服务发送过来的  xml 消息
		if (ApiConfigKit.isDevMode()) {
			System.out.println("接收消息:");
			System.out.println(getInMsgXml());
		}
		
		// 解析消息并根据消息类型分发到相应的处理方法
		InMsg msg = getInMsg();
		if (msg instanceof InTextMsg)
			processInTextMsg((InTextMsg) msg);
		else if (msg instanceof InImageMsg)
			processInImageMsg((InImageMsg) msg);
		else if (msg instanceof InSpeechRecognitionResults)  //update by unas at 2016-1-29, 由于继承InVoiceMsg，需要在InVoiceMsg前判断类型
			processInSpeechRecognitionResults((InSpeechRecognitionResults) msg);
		else if (msg instanceof InVoiceMsg)
			processInVoiceMsg((InVoiceMsg) msg);
		else if (msg instanceof InVideoMsg)
			processInVideoMsg((InVideoMsg) msg);
		else if (msg instanceof InShortVideoMsg)   //支持小视频
			processInShortVideoMsg((InShortVideoMsg) msg);
		else if (msg instanceof InLocationMsg)
			processInLocationMsg((InLocationMsg) msg);
		else if (msg instanceof InLinkMsg)
			processInLinkMsg((InLinkMsg) msg);
		else if (msg instanceof InCustomEvent)
			processInCustomEvent((InCustomEvent) msg);
		else if (msg instanceof InFollowEvent)
			processInFollowEvent((InFollowEvent) msg);
		else if (msg instanceof InQrCodeEvent)
			processInQrCodeEvent((InQrCodeEvent) msg);
		else if (msg instanceof InLocationEvent)
			processInLocationEvent((InLocationEvent) msg);
		else if (msg instanceof InMassEvent)
			processInMassEvent((InMassEvent) msg);
		else if (msg instanceof InMenuEvent)
			processInMenuEvent((InMenuEvent) msg);
		else if (msg instanceof InTemplateMsgEvent)
			processInTemplateMsgEvent((InTemplateMsgEvent) msg);
		else if (msg instanceof InShakearoundUserShakeEvent)
			processInShakearoundUserShakeEvent((InShakearoundUserShakeEvent) msg);
		else if (msg instanceof InVerifySuccessEvent)
			processInVerifySuccessEvent((InVerifySuccessEvent) msg);
		else if (msg instanceof InVerifyFailEvent)
			processInVerifyFailEvent((InVerifyFailEvent) msg);
		else if (msg instanceof InPoiCheckNotifyEvent)
			processInPoiCheckNotifyEvent((InPoiCheckNotifyEvent) msg);
		else if (msg instanceof InWifiEvent)
			processInWifiEvent((InWifiEvent) msg);
		else if (msg instanceof InUserViewCardEvent)
			processInUserViewCardEvent((InUserViewCardEvent) msg);
		else if (msg instanceof InSubmitMemberCardEvent)
			processInSubmitMemberCardEvent((InSubmitMemberCardEvent) msg);
		else if (msg instanceof InUpdateMemberCardEvent)
			processInUpdateMemberCardEvent((InUpdateMemberCardEvent) msg);
		else if (msg instanceof InUserPayFromCardEvent)
			processInUserPayFromCardEvent((InUserPayFromCardEvent) msg);
		else if (msg instanceof InMerChantOrderEvent)
			processInMerChantOrderEvent((InMerChantOrderEvent) msg);
		else if (msg instanceof InNotDefinedEvent) {
			log.error("未能识别的事件类型。 消息 xml 内容为：\n" + getInMsgXml());
			processIsNotDefinedEvent((InNotDefinedEvent) msg);
		} else if (msg instanceof InNotDefinedMsg) {
			log.error("未能识别的消息类型。 消息 xml 内容为：\n" + getInMsgXml());
			processIsNotDefinedMsg((InNotDefinedMsg) msg);
		}
	}

	/**
	 * 在接收到微信服务器的 InMsg 消息后后响应 OutMsg 消息
	 * @param outMsg 输出对象
	 */
	public void render(OutMsg outMsg) {
		String outMsgXml = outMsg.toXml();
		// 开发模式向控制台输出即将发送的 OutMsg 消息的 xml 内容
		if (ApiConfigKit.isDevMode()) {
			System.out.println("发送消息:");
			System.out.println(outMsgXml);
			System.out.println("--------------------------------------------------------------------------------\n");
		}
		
		// 是否需要加密消息
		if (ApiConfigKit.getApiConfig().isEncryptMessage()) {
			outMsgXml = MsgEncryptKit.encrypt(outMsgXml, getPara("timestamp"), getPara("nonce"));
		}
		
		renderText(outMsgXml, "text/xml");
	}
	
	public void renderOutTextMsg(String content) {
		OutTextMsg outMsg= new OutTextMsg(getInMsg());
		outMsg.setContent(content);
		render(outMsg);
	}
	
	@Before(NotAction.class)
	public String getInMsgXml() {
		if (inMsgXml == null) {
			inMsgXml = HttpKit.readData(getRequest());
			
			// 是否需要解密消息
			if (ApiConfigKit.getApiConfig().isEncryptMessage()) {
				inMsgXml = MsgEncryptKit.decrypt(inMsgXml, getPara("timestamp"), getPara("nonce"), getPara("msg_signature"));
			}
		}
		if (StrKit.isBlank(inMsgXml)) {
			throw new RuntimeException("请不要在浏览器中请求该连接,调试请查看WIKI:http://git.oschina.net/jfinal/jfinal-weixin/wikis/JFinal-weixin-demo%E5%92%8C%E8%B0%83%E8%AF%95");
		}
		return inMsgXml;
	}
	
	@Before(NotAction.class)
	public InMsg getInMsg() {
		if (inMsg == null)
			inMsg = InMsgParser.parse(getInMsgXml()); 
		return inMsg;
	}
	
	// 处理接收到的文本消息
	protected abstract void processInTextMsg(InTextMsg inTextMsg);
	
	// 处理接收到的图片消息
	protected abstract void processInImageMsg(InImageMsg inImageMsg);
	
	// 处理接收到的语音消息
	protected abstract void processInVoiceMsg(InVoiceMsg inVoiceMsg);
	
	// 处理接收到的视频消息
	protected abstract void processInVideoMsg(InVideoMsg inVideoMsg);

	// 处理接收到的视频消息
	protected abstract void processInShortVideoMsg(InShortVideoMsg inShortVideoMsg);
	
	// 处理接收到的地址位置消息
	protected abstract void processInLocationMsg(InLocationMsg inLocationMsg);

	// 处理接收到的链接消息
	protected abstract void processInLinkMsg(InLinkMsg inLinkMsg);

    // 处理接收到的多客服管理事件
    protected abstract void processInCustomEvent(InCustomEvent inCustomEvent);

	// 处理接收到的关注/取消关注事件
	protected abstract void processInFollowEvent(InFollowEvent inFollowEvent);
	
	// 处理接收到的扫描带参数二维码事件
	protected abstract void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent);
	
	// 处理接收到的上报地理位置事件
	protected abstract void processInLocationEvent(InLocationEvent inLocationEvent);

    // 处理接收到的群发任务结束时通知事件
    protected abstract void processInMassEvent(InMassEvent inMassEvent);

	// 处理接收到的自定义菜单事件
	protected abstract void processInMenuEvent(InMenuEvent inMenuEvent);
	
	// 处理接收到的语音识别结果
	protected abstract void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults);
	
	// 处理接收到的模板消息是否送达成功通知事件
	protected abstract void processInTemplateMsgEvent(InTemplateMsgEvent inTemplateMsgEvent);

	// 处理微信摇一摇事件
	protected abstract void processInShakearoundUserShakeEvent(InShakearoundUserShakeEvent inShakearoundUserShakeEvent);

	// 资质认证成功 || 名称认证成功 || 年审通知 || 认证过期失效通知
	protected abstract void processInVerifySuccessEvent(InVerifySuccessEvent inVerifySuccessEvent);

	// 资质认证失败 || 名称认证失败
	protected abstract void processInVerifyFailEvent(InVerifyFailEvent inVerifyFailEvent);
	
	// 门店在审核事件消息
	protected abstract void processInPoiCheckNotifyEvent(InPoiCheckNotifyEvent inPoiCheckNotifyEvent);

	// WIFI连网后下发消息 by unas at 2016-1-29
	protected abstract void processInWifiEvent(InWifiEvent inWifiEvent);

	// 微信会员卡二维码扫描领取接口
	protected abstract void processInUserViewCardEvent(InUserViewCardEvent inUserViewCardEvent);
	// 微信会员卡激活接口
	protected abstract void processInSubmitMemberCardEvent(InSubmitMemberCardEvent inSubmitMemberCardEvent);
	// 微信会员卡积分变更
	protected abstract void processInUpdateMemberCardEvent(InUpdateMemberCardEvent inUpdateMemberCardEvent);
	// 微信会员卡快速买单
	protected abstract void processInUserPayFromCardEvent(InUserPayFromCardEvent inUserPayFromCardEvent);
	// 微信小店订单支付成功接口消息
	protected abstract void processInMerChantOrderEvent(InMerChantOrderEvent inMerChantOrderEvent);

	// 没有找到对应的事件消息
	protected abstract void processIsNotDefinedEvent(InNotDefinedEvent inNotDefinedEvent);
	// 没有找到对应的消息
	protected abstract void processIsNotDefinedMsg(InNotDefinedMsg inNotDefinedMsg);
}













