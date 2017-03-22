package com.jhs.validator.login;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
import com.jhs.util.CaptchaUtil;

/**
 * 登陆验证
 * @author 天瑞
 *
 */
public class LoginValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		//验证账号
		String adminName = c.getPara("adminName");
		if(StrKit.isBlank(adminName)){
			addError("status", "fail");
			addError("msg", "请输入账号！");
			return;
		}
		if(adminName.length() > 30){
			addError("status", "fail");
			addError("msg", "账号不能超过30个字符！");
			return;
		}
		//验证密码
		String adminPwd = c.getPara("adminPwd");
		if(StrKit.isBlank(adminPwd)){
			addError("status", "fail");
			addError("msg", "请输入密码！");
			return;
		}
		if(adminPwd.length() > 20){
			addError("status", "fail");
			addError("msg", "账号不能超过20个字符！");
			return;
		}
		//验证验证码
		String captcha = c.getPara("captcha");
		if(StrKit.isBlank(captcha)){
			addError("status", "fail");
			addError("msg", "请输入验证码！");
			return;
		}
	
	}

	@Override
	protected void handleError(Controller c) {
		c.renderJson();
	}

}
