package com.jhs.service.data;

import com.jfinal.aop.Before;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jhs.common.model.DataDemand;
import com.jhs.po.Search;
import com.jhs.util.SQLUtil;

/**
 * 需求Service
 * @author 刘阳
 *
 */
@Before(Tx.class)
public class DataDemandService {

	public static DataDemandService me = new DataDemandService();
	
	private static final DataDemand dao = new DataDemand().dao();
	
	/**
	 * 获取数据列表
	 * @param search 条件对象
	 * @return
	 */
	public Page<DataDemand> paginate(Search search){
		String[] keys = { "demand_no", "demand_name", "demand_second_name" };
		String sql = SQLUtil.makeSQL(search, "data_demand ", keys, null, null, null);
		Page<DataDemand> data = dao.paginate(SQLUtil.getPage(), SQLUtil.getRow(), "select * ",sql);		 
		return data;
	}
	
	/**
	 * 更新需求
	 * @param dd 需求对象
	 * @return
	 */
	public Ret update(DataDemand dd){
		Ret ret = new Ret();
		Object[] paras = new Object[4];
		paras[0] = dd.getDemandId();
		paras[1] = dd.getDemandNo();
		paras[2] = dd.getDemandName();
		paras[3] = dd.getDemandSecondName();
		if(Db.queryLong("select * from data_demand where demand_id = ? and (demand_no = ? or demand_name = ? or demand_second_name = ?)", paras) > 0){
			ret.set("status","fail");
			ret.set("msg","编号、名称或别名已存在！");
		}
		else{
			if(dd.update()){
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
	 * 创建需求
	 * @param dd 需求对象
	 * @return
	 */
	public Ret create(DataDemand dd){
		Ret ret = new Ret();
		Object[] paras = new Object[3];
		paras[0] = dd.getDemandNo();
		paras[1] = dd.getDemandName();
		paras[2] = dd.getDemandSecondName();
		if(Db.queryLong("select count(*) from data_demand where demand_no = ? or demand_name = ? or demand_second_name = ?", paras) > 0){
			ret.set("status","fail");
			ret.set("msg","编号、名称或别名已存在！");
		}
		else{
			if(dd.save()){
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
	 * 删除需求 
	 * @param id 需求ID
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
