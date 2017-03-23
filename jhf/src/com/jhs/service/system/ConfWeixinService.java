package com.jhs.service.system;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;
import com.jhs.common.model.ConfWeixin;

/**
 * 微信配置Service
 * @author 天瑞
 *
 */
@Before(Tx.class)
public class ConfWeixinService {
	
	public static final ConfWeixin dao = new ConfWeixin().dao();
	
	/**
	 * 编辑
	 * @param id
	 * @return
	 */
	public ConfWeixin edit(){
		return dao.findFirst("select * from conf_weixin where id = 1");
	}
	
	/**
	 * 更新
	 * @param cf
	 * @return
	 */
	@Before({CacheInterceptor.class})
	@CacheName("conf_weixin")
	public boolean update(ConfWeixin cf){
		return cf.update();
	}
}
