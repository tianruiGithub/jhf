package com.jhs.service.data;

import com.jhs.common.model.DataChanpin;

/**
 * 产品Service
 * @author liuyang
 *
 */
public class DataChanpinService {

	public static DataChanpinService me = new DataChanpinService();
	
	private static final DataChanpin dao = new DataChanpin().dao();
	
	/**
	 * 按体质编号查询产品
	 * @param physiqueNo
	 * @return
	 */
	public DataChanpin queryByPhysique(String physiqueNo){
		return dao.findFirst("select * from data_chanpin where physique_no = ?",physiqueNo);
	}
}
