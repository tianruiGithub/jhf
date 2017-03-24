package com.jhs.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * session验证拦截器，检验用户登陆状态
 * 
 * @author 天瑞
 */
public class SessionInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		try {
			if (controller.getSessionAttr("User") != null)
				inv.invoke();
			else
				controller.redirect("/login.jsp");
		} catch (Exception e) {
			controller.redirect("/login.jsp");
		}
	}

}
