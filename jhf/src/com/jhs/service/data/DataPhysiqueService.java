package com.jhs.service.data;

import java.util.List;

import com.jhs.common.model.DataPhysique;

/**
 * 体质Service
 * @author 天瑞
 *
 */
public class DataPhysiqueService {
	
	public static DataPhysiqueService me = new DataPhysiqueService();
	
	private static final DataPhysique dao = new DataPhysique().dao();
	
	/**
	 * 根据需求编号查询体质
	 * @param demandNo 需求编号
	 * @return
	 */
	public List<DataPhysique> queryByDemand(String demandNo){
		return dao.find("select * from data_physique where demand_no = ?",demandNo);
	}
}
