package com.jhs.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Ret;
import com.jhs.common.model.SysAdmin;
import com.jhs.interceptor.MenuInterceptor;
import com.jhs.interceptor.SessionInterceptor;
import com.jhs.service.data.SysAdminService;
import com.jhs.util.CaptchaUtil;
import com.jhs.validator.login.LoginValidator;

/**
 * 首页controller
 * 
 * @author 天瑞
 *
 */
public class IndexController extends Controller {
	
	/**
	 * 后台主页
	 */
	@Before({ SessionInterceptor.class, MenuInterceptor.class })
	public void main() {
		render("index.jsp");
	}

	/**
	 * 生成图形验证码
	 */
	public void captcha() {
		render(new CaptchaUtil(60, 22, 4, true));
	}

	/**
	 * 管理员登陆
	 * 
	 * @param adminName
	 * @param adminPwd
	 * @param captcha
	 */
	@Before({LoginValidator.class,POST.class})
	public void login() {
		Ret ret = new Ret();
		boolean validate = CaptchaUtil.validate(this, getPara("captcha"));
		if (validate) {
			SysAdmin sa =  SysAdminService.me.login( getPara("adminName"), getPara("adminPwd"));
			if (sa != null) {
				getSession().setAttribute("User", sa.getAdminName());
				getSession().setAttribute("Rank", sa.getAdminRank());
				ret.set("status", "success");
				ret.set("msg", "登陆成功！");
			} else {
				ret.set("status", "fail");
				ret.set("msg", "账号或密码错误！");
			}
		} else {
			ret.set("status", "fail");
			ret.set("msg", "验证码错误！");
		}
		renderJson(ret);
	}

	/**
	 * 管理员退出
	 */
	public void logout() {
		getSession().removeAttribute("User");
		getSession().removeAttribute("Rank");
		redirect("/login.jsp");
	}
}
