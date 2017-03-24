package com.jhs.service.data;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jhs.common.model.RecordUser;
/**
 * 微信用户Service
 * @author 天瑞
 *
 */
@Before(Tx.class)
public class RecordUserService {
	
	public static RecordUserService me = new RecordUserService();
	
	private static final RecordUser dao = new RecordUser().dao();
	
	/**
	 * 添加
	 * @param ru 用户对象
	 * @return
	 */
	public boolean save(RecordUser ru){
		return ru.save();
	}
	
	/**
	 * 更新
	 * @param ru 用户对象
	 * @return
	 */
	public boolean update(RecordUser ru){
		return ru.update();
	}
	
	/**
	 * 查询
	 * @param openId 用户ID
	 * @return
	 */
	public RecordUser queryById(String openId){
		return dao.findById(openId);
	}
}
