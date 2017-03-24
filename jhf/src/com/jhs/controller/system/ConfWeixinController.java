package com.jhs.controller.system;

import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.jhs.common.model.ConfWeixin;
import com.jhs.interceptor.MenuInterceptor;
import com.jhs.interceptor.SessionInterceptor;
import com.jhs.service.system.ConfWeixinService;
import com.jhs.validator.system.ConfWeixinValidator;

/**
 * 微信配置
 * @author 天瑞
 *
 */
@Before({ SessionInterceptor.class, MenuInterceptor.class })
public class ConfWeixinController extends Controller{

	public static final ConfWeixinService me = Duang.duang(ConfWeixinService.class);
	
	/**
	 * 编辑页面
	 */
	public void edit(){
		setAttr("weixin",me.edit());
		render("index.jsp");
	}
	
	/**
	 * 更新
	 * @param weixin
	 */
	@Before({ConfWeixinValidator.class,POST.class,EvictInterceptor.class})
	@CacheName("conf_weixin")
	public void updatess(){
		Ret ret = new Ret();
		ConfWeixin cf = getModel(ConfWeixin.class,"weixin");
		cf.setId(Long.parseLong("1"));
		if(me.update(cf)){
			ret.set("status","success");
			ret.set("msg","更新成功");
		}
		else{
			ret.set("status","fail");
			ret.set("msg","更新失败");
		}
		renderJson(ret);
	}
}
