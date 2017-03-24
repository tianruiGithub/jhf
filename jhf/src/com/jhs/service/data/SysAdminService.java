package com.jhs.service.data;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jhs.common.model.SysAdmin;
import com.jhs.util.MD5Util;

/**
 * 登陆Service
 * @author 天瑞
 *
 */
@Before(Tx.class)
public class SysAdminService {

	public static SysAdminService me = new SysAdminService();
	
	private static final SysAdmin dao = new SysAdmin().dao();
	
	/**
	 * 管理员登陆
	 * @param adminName 账号
	 * @param adminPwd 密码
	 * @return
	 */
	public SysAdmin login(String adminName,String adminPwd){
		Object[] paras = new Object[2];
		paras[0] = adminName;
		paras[1] = MD5Util.toMd5String(adminPwd);
		SysAdmin sa = dao.findFirst("select * from sys_admin where admin_name = ? and admin_pwd = ?",
				paras);
		return sa;
	}
}
