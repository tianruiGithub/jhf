package com.jhs.service.data;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jhs.common.model.DataPhysique;
import com.jhs.po.Search;
import com.jhs.util.SQLUtil;

/**
 * 体质Service
 * @author 天瑞
 *
 */
@Before(Tx.class)
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
	
	/**
	 * 根据ID查询体质
	 * @param id 体质ID
	 * @return
	 */
	public DataPhysique queryById(Long id){
		return dao.findById(id);
	}
	
	/**
	 * 检索体质
	 * @param search 条件对象
	 * @return
	 */
	public Page<DataPhysique> paginate(Search search){
		String[] keys = { "physique_no", "physique_name"};
		String sql = SQLUtil.makeSQL(search, "data_physique a,data_demand b where a.demand_no = b.demand_no", keys, "a.demand_no", null, null);
		Page<DataPhysique> data = dao.paginate(SQLUtil.getPage(), SQLUtil.getRow(), "select a.*,b.demand_name ",sql);		 
		return data;
	}
	
	/**
	 * 更新体质
	 * @param dp 体质对象
	 * @return
	 */
	public Ret update(DataPhysique dp){
		Ret ret = new Ret();
		Object[] paras = new Object[2];
		paras[0] = dp.getPhysiqueId();
		paras[1] = dp.getPhysiqueName();
		if(Db.queryLong("select count(*) from data_physique where physique_id != ? and physique_name = ?", paras) > 0){
			ret.set("status","fail");
			ret.set("msg","名称已存在！");
		}
		else{
			if(dp.update()){
				ret.set("status","success");
				ret.set("msg","更新成功！");
			}
			else{
				ret.set("status","fail");
				ret.set("msg","更新失败！");
			}
		}
		return ret;
	}
	
	/**
	 * 创建体质
	 * @param dp 体质对象
	 * @return
	 */
	public Ret create(DataPhysique dp){
		Ret ret = new Ret();
		Object[] paras = new Object[2];
		paras[0] = dp.getPhysiqueNo();
		paras[1] = dp.getPhysiqueName();
		if(Db.queryLong("select count(*) from data_physique where physique_no = ? or physique_name = ?", paras) > 0){
			ret.set("status","fail");
			ret.set("msg","编号、名称或别名已存在！");
		}
		else{
			if(dp.save()){
				ret.set("status","success");
				ret.set("msg","创建成功！");
			}
			else{
				ret.set("status","fail");
				ret.set("msg","创建失败！");
			}
		}
		return ret;
	}
	
	/**
	 * 删除体质 
	 * @param id 体质ID
	 */
	public Ret delete(Long id){
		Ret ret = new Ret();
		try{
			if(dao.deleteById(id)){
				ret.set("status","success");
				ret.set("msg","删除成功！");
			}
			else{
				ret.set("status","fail");
				ret.set("msg","删除失败！");
			}
		}catch(Exception e){
			ret.set("status","fail");
			ret.set("msg","异常报错:"+e.toString());
		}
		return ret;
	}
}
