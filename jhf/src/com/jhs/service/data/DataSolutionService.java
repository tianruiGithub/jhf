package com.jhs.service.data;

import com.jhs.common.model.DataSolution;

/**
 * 方案Service
 * @author liuyang
 *
 */
public class DataSolutionService {

	public static DataSolutionService me = new DataSolutionService();
	
	private static final DataSolution dao = new DataSolution().dao();
	
	/**
	 * 按编号查询方案
	 * @param solutionNo 编号
	 * @return
	 */
	public DataSolution queryByNo(String solutionNo){
		return dao.findFirst("select a.*,b.physique_name,c.demand_name,c.demand_second_name from data_solution a,data_physique b,data_demand c where a.physique_no = b.physique_no and b.demand_no = c.demand_no and a.solution_no = ?",solutionNo);
	}
	
	/**
	 * 按体质查询方案
	 * @param solutionNo 编号
	 * @return
	 */
	public DataSolution queryByPhysique(String physiqueNo){
		return dao.findFirst("select * from data_solution where physique_no = ? and solution_score_up = 0 and solution_score_down = 0",physiqueNo);
	}
	
	/**
	 * 按体质和分数查询方案
	 * @param physiqueNo 体质编号
	 * @param score 分数
	 * @return
	 */
	public DataSolution queryByPhysiqueAndScore(String physiqueNo,int score){
		Object[] paras = new Object[3];
		paras[0] = physiqueNo;
		paras[1] = score;
		paras[2] = score;
		return dao.findFirst("select * from data_solution where physique_no = ? and solution_score_up >= ? and solution_score_down <= ?",paras);	
	}
}
