package com.jfinal.weixin.sdk.api;

import java.util.Map;

import com.jfinal.weixin.sdk.kit.PaymentKit;
import com.jfinal.weixin.sdk.utils.HttpUtils;

/**
 * 微信企业付款
 * @author 天瑞
 *
 */
public class CompanyPayUserApi {

	public CompanyPayUserApi(){
		
	}
	
	private static String transfersUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	
	/**
	 * 发送裂变红包
	 * @param params 请求参数
	 * @param certPath 证书文件目录
	 * @param partner 证书密码
	 * @return {String}
	 */
	public static String companyPayUser(Map<String, String> params, String certPath, String partner) {
		return HttpUtils.postSSL(transfersUrl, PaymentKit.toXml(params), certPath, partner);
	}
	
}
