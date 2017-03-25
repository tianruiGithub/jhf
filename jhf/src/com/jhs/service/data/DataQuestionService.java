package com.jhs.service.data;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jhs.common.model.DataQuestion;

/**
 * 检测问题Service
 * @author 天瑞
 *
 */
@Before(Tx.class)
public class DataQuestionService {
	
	public static DataQuestionService me = new DataQuestionService();
	
	private static final DataQuestion dao = new DataQuestion().dao();
	
	/**
	 * 根据需求编号查询问题
	 * @param demandNo 需求编号
	 * @return
	 */
	public List<DataQuestion> queryByDemand(String demandNo){
		return dao.find("select a.*,b.demand_no from data_question a,data_demand b,data_physique c where a.physique_no = c.physique_no and c.demand_no = b.demand_no and b.demand_no = ?",demandNo);
	}
}
