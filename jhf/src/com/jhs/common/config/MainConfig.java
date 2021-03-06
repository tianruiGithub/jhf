package com.jhs.common.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;
import com.jhs.common.model._MappingKit;
import com.jhs.controller.IndexController;
import com.jhs.controller.data.DataDemandController;
import com.jhs.controller.data.DataPhysiqueController;
import com.jhs.controller.data.DataQuestionController;
import com.jhs.controller.data.RecordTestController;
import com.jhs.controller.system.ConfWeixinController;
import com.jhs.controller.weixin.WeixinController;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.render.ViewType;

public class MainConfig extends JFinalConfig {
	/**
	 * 配置JFinal常量
	 */
	@Override
	public void configConstant(Constants me) {
		//读取数据库配置文件
		PropKit.use("config.properties");
		//设置当前是否为开发模式
		me.setDevMode(PropKit.getBoolean("devMode"));
		//设置默认上传文件保存路径 getFile等使用
		me.setBaseUploadPath("upload/temp/");
		//设置上传最大限制尺寸
		//me.setMaxPostSize(1024*1024*10);
		//设置默认下载文件路径 renderFile使用
		me.setBaseDownloadPath("download");
		//设置默认视图类型
		me.setViewType(ViewType.JSP);
		//设置404渲染视图
		//me.setError404View("404.html");
	}
	/**
	 * 配置JFinal路由映射
	 */
	@Override
	public void configRoute(Routes me) {
		// 首页
		me.add("/", IndexController.class, "/WEB-INF/views/main");
		// 数据
		me.add("/data/demand",DataDemandController.class,"/WEB-INF/views/data/demand");
		me.add("/data/physique",DataPhysiqueController.class,"/WEB-INF/views/data/physique");
		me.add("/data/question",DataQuestionController.class,"/WEB-INF/views/data/question");
		me.add("/data/test",RecordTestController.class,"/WEB-INF/views/data/test");
		// 系统设置 
		me.add("/system/weixin",ConfWeixinController.class,"/WEB-INF/views/system/weixin");
		//微信
		me.add("/weixin",WeixinController.class,"/WEB-INF/views/weixin");
	}
	/**
	 * 配置JFinal插件
	 * 数据库连接池
	 * ORM
	 * 缓存等插件
	 * 自定义插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		//配置数据库连接池插件
		DruidPlugin dbPlugin=new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
		//orm映射 配置ActiveRecord插件
		ActiveRecordPlugin arp=new ActiveRecordPlugin(dbPlugin);
		arp.setShowSql(PropKit.getBoolean("devMode"));
		arp.setDialect(new MysqlDialect());
		/********在此添加数据库 表-Model 映射*********/
		_MappingKit.mapping(arp);
		//添加到插件列表中
		me.add(dbPlugin);
		me.add(arp);
		// 缓存插件
		me.add(new EhCachePlugin());
	}
	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {

	}
	/**
	 * 配置全局处理器
	 */
	@Override
	public void configHandler(Handlers me) {

	}
	/**
	 * JFinal启动后调用
	 */
	@Override
	public void afterJFinalStart() {
		
	}
	/**
	 * JFinal Stop之前调用 
	 */
	@Override
	public void beforeJFinalStop() {
		
	}
	
	/**
	 * 配置模板引擎 
	 */
	@Override
	public void configEngine(Engine me) {
		//这里只有选择JFinal TPL的时候才用
		//配置共享函数模板
		//me.addSharedFunction("/view/common/layout.html")
	}
	
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 3);
	}
	

}
