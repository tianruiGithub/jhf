package com.jhs.controller.data;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jhs.common.model.DataPhysique;
import com.jhs.interceptor.MenuInterceptor;
import com.jhs.interceptor.SessionInterceptor;
import com.jhs.po.Search;
import com.jhs.service.data.DataDemandService;
import com.jhs.service.data.DataPhysiqueService;
import com.jhs.validator.data.DataPhysiqueValidator;

/**
 * 体质Controller
 * @author 刘阳
 *
 */
@Before({ SessionInterceptor.class, MenuInterceptor.class })
public class DataPhysiqueController extends Controller{
	
	static DataPhysiqueService service = DataPhysiqueService.me;

	public void index() {
		setAttr("demandList",DataDemandService.me.queryAll());
		render("index.jsp");
	}
	
	/**
	 * 查询需求
	 */
	public void list() {
		int offset = getParaToInt("offset", 0);
		int limit = getParaToInt("limit", 10);
		Search search = new Search();
		search.setKeywords(getPara("search"));
		search.setPage((offset / limit) + 1);
		search.setRow(limit);
		search.setType1(getPara("type1"));
		search.setType2(getPara("type2"));
		search.setDate1(getPara("date1"));
		search.setDate2(getPara("date2"));
		search.setOrder(getPara("order"));
		search.setSort(getPara("sort"));
		Page<DataPhysique> page=service.paginate(search);
		Ret ret = new Ret();
		ret.set("rows",page.getList());
		ret.set("total",page.getTotalRow());
		renderJson(ret);
	}
	
	/**
	 * 更新需求
	 */
	@Before({POST.class,DataPhysiqueValidator.class})
	public void modify(){
		DataPhysique dp = service.queryById(Long.parseLong(getPara("physique_id")));
		dp.setPhysiqueName(getPara("physique_name"));
		dp.setPhysiqueGuanlian(getPara("physique_guanlian"));
		dp.setPhysiqueIntroduce(getPara("physique_introduce"));
		renderJson(service.update(dp));
	}
	
	/**
	 * 添加需求
	 */
	@Before({POST.class,DataPhysiqueValidator.class})
	public void add(){
		DataPhysique dp = new DataPhysique();
		dp.setPhysiqueNo(getPara("physique_no"));
		dp.setDemandNo(getPara("demand_no"));
		dp.setPhysiqueName(getPara("physique_name"));
		dp.setPhysiqueGuanlian(getPara("physique_guanlian"));
		dp.setPhysiqueIntroduce(getPara("physique_introduce"));
		renderJson(service.create(dp));
	}
	
	/**
	 * 删除需求
	 */
	public void remove(){
		renderJson(service.delete(Long.parseLong(getPara("id"))));
	}
}
