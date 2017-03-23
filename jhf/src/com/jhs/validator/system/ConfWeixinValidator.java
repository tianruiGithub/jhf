package com.jhs.validator.system;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import com.jhs.common.model.ConfWeixin;

/**
 * 微信公众号配置验证
 * @author 天瑞
 *
 */
public class ConfWeixinValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		ConfWeixin cf = c.getModel(ConfWeixin.class,"weixin");
		if(StrKit.isBlank(cf.getWeixinAppid())){
			addError("status","fail");
			addError("msg","请输入微信应用ID");
			return;
		}
		if(StrKit.isBlank(cf.getWeixinSecret())){
			addError("status","fail");
			addError("msg","请输入微信应用密钥");
			return;
		}
		if(cf.getEncryptMessage().equals("1") && StrKit.isBlank(cf.getEncodingAeskey())){
			addError("status","fail");
			addError("msg","请输入加密密钥");
			return;
		}
	}

	@Override
	protected void handleError(Controller c) {
		c.renderJson();	
	}

}
