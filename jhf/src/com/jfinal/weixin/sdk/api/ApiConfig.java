/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.jfinal.weixin.sdk.api;

import java.io.Serializable;

import com.jfinal.aop.Duang;
import com.jhs.common.model.ConfWeixin;
import com.jhs.service.system.ConfWeixinService;

/**
 * 存放 Weixin 服务号需要用到的各个参数
 */
public class ApiConfig implements Serializable {

	private static final long serialVersionUID = 5243926308290263767L;
	
	public static final ConfWeixinService me = Duang.duang(ConfWeixinService.class);
	
	private String token = null;
	private String appId = null;
	private String appSecret = null;
	private String encodingAesKey = null;
	private boolean messageEncrypt = false; // 消息加密与否

	public ApiConfig() {
		ConfWeixin cw = me.query();
		// 配置微信 API 相关常量
		setToken(cw.getToken());
		setAppId(cw.getWeixinAppid());
		setAppSecret(cw.getWeixinSecret());
		/**
		 * 是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey
		 * 2：false采用明文模式，同时也支持混合模式
		 */
		if(cw.getEncryptMessage().equals("0")){
			setEncryptMessage(false);
			setEncodingAesKey("setting it in config");
		}
		else{
			setEncryptMessage(true);
			setEncodingAesKey(cw.getEncodingAeskey());
		}
	}

	public ApiConfig(String token) {
		setToken(token);
	}

	public ApiConfig(String token, String appId, String appSecret) {
		setToken(token);
		setAppId(appId);
		setAppSecret(appSecret);
	}

	public ApiConfig(String token, String appId, String appSecret, boolean messageEncrypt, String encodingAesKey) {
		setToken(token);
		setAppId(appId);
		setAppSecret(appSecret);
		setEncryptMessage(messageEncrypt);
		setEncodingAesKey(encodingAesKey);
	}

	public String getToken() {
		if (token == null)
			throw new IllegalStateException("token 未被赋值");
		return token;
	}

	public void setToken(String token) {
		if (token == null)
			throw new IllegalArgumentException("token 值不能为 null");
		this.token = token;
	}

	public String getAppId() {
		if (appId == null)
			throw new IllegalStateException("appId 未被赋值");
		return appId;
	}

	public void setAppId(String appId) {
		if (appId == null)
			throw new IllegalArgumentException("appId 值不能为 null");
		this.appId = appId;
	}

	public String getAppSecret() {
		if (appSecret == null)
			throw new IllegalStateException("appSecret 未被赋值");
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		if (appSecret == null)
			throw new IllegalArgumentException("appSecret 值不能为 null");
		this.appSecret = appSecret;
	}

	public String getEncodingAesKey() {
		if (encodingAesKey == null)
			throw new IllegalStateException("encodingAesKey 未被赋值");
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		if (encodingAesKey == null)
			throw new IllegalArgumentException("encodingAesKey 值不能为 null");
		this.encodingAesKey = encodingAesKey;
	}

	public boolean isEncryptMessage() {
		return messageEncrypt;
	}

	/**
	 * @param messageEncrypt
	 *            是否对消息进行加密，对应于微信平台的消息加解密方式： 1：true进行加密且必须配置 encodingAesKey
	 *            2：false采用明文模式，同时也支持混合模式
	 */
	public void setEncryptMessage(boolean messageEncrypt) {
		this.messageEncrypt = messageEncrypt;
	}
}
