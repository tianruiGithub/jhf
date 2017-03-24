package com.jhs.service.system;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jhs.common.model.ConfWeixin;

/**
 * 微信配置Service
 * @author 天瑞
 *
 */
@Before(Tx.class)
public class ConfWeixinService {
	
	public static ConfWeixinService me = new ConfWeixinService();
	
	private static final ConfWeixin dao = new ConfWeixin().dao();
	
	/**
	 * 查询
	 * @param id 配置ID
	 * @return
	 */
	public ConfWeixin queryById(Object id){
		return dao.findFirstByCache("conf_weixin", null, "select * from conf_weixin where id = ?",id);
	}
	/**
	 * 编辑
	 * @param id 配置ID
	 * @return
	 */
	public ConfWeixin edit(Object id){
		return dao.findFirst("select * from conf_weixin where id = ?",id);
	}
	
	/**
	 * 更新
	 * @param cf 配置对象
	 * @return
	 */

	public boolean update(ConfWeixin cf){
		return cf.update();	
	}
}
