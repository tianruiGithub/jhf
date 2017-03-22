package com.jhs.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 菜单拦截器,显示菜单选中状态
 * 
 * @author 天瑞
 *
 */
public class MenuInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		if(inv.getActionKey().contains("main")){
			controller.setAttr("menu_main", "m-menu-active");
		}else if(inv.getActionKey().contains("data")){
			controller.setAttr("menu_data", "m-menu-active");
			if(inv.getActionKey().contains("demand")) 
				controller.setAttr("menu_data_demand", "m-item-menu-active");
			else if(inv.getActionKey().contains("physique"))
				controller.setAttr("menu_data_physique", "m-item-menu-active");
			else if(inv.getActionKey().contains("xuewei"))
				controller.setAttr("menu_data_xuewei", "m-item-menu-active");
			else if(inv.getActionKey().contains("tiaoli"))
				controller.setAttr("menu_data_tiaoli", "m-item-menu-active");
			else if(inv.getActionKey().contains("image"))
				controller.setAttr("menu_data_image", "m-item-menu-active");
			else if(inv.getActionKey().contains("pifu_sol"))
				controller.setAttr("menu_data_pifuSol", "m-item-menu-active");
		}else if(inv.getActionKey().contains("activity")){
			controller.setAttr("menu_activity", "m-menu-active");
		}else if(inv.getActionKey().contains("youzan")){
			controller.setAttr("menu_youzan", "m-menu-active");
		}else if(inv.getActionKey().contains("graph")){
			controller.setAttr("menu_graph", "m-menu-active");
		}else if(inv.getActionKey().contains("system")){
			controller.setAttr("menu_system", "m-menu-active");
			if(inv.getActionKey().contains("weixin")) 
				controller.setAttr("menu_system_weixin", "m-item-menu-active");
			else if(inv.getActionKey().contains("oauth")) 
				controller.setAttr("menu_system_oauth", "m-item-menu-active");
		}
		inv.invoke();
	}
}
