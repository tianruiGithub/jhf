package com.jhs.controller.data;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jhs.common.model.DataDemand;
import com.jhs.interceptor.MenuInterceptor;
import com.jhs.interceptor.SessionInterceptor;
import com.jhs.po.Search;
import com.jhs.service.data.DataDemandService;
import com.jhs.validator.data.DataDemandValidator;

/**
 * 需求Controller
 * @author liuyang
 *
 */
@Before({ SessionInterceptor.class, MenuInterceptor.class })
public class DataDemandController extends Controller {

	static DataDemandService service = DataDemandService.me;

	public void index() {
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
		Page<DataDemand> page=service.paginate(search);
		Ret ret = new Ret();
		ret.set("rows",page.getList());
		ret.set("total",page.getTotalRow());
		renderJson(ret);
	}
	
	/**
	 * 更新需求
	 */
	@Before({POST.class,DataDemandValidator.class})
	public void modify(){
		DataDemand dd = new DataDemand();
		dd.setDemandId(Long.parseLong(getPara("demand_id")));
		dd.setDemandNo(getPara("demand_no"));
		dd.setDemandName(getPara("demand_name"));
		dd.setDemandSecondName(getPara("demand_second_name")); 
		renderJson(service.update(dd));
	}
	
	/**
	 * 添加需求
	 */
	@Before({POST.class,DataDemandValidator.class})
	public void add(){
		DataDemand dd = new DataDemand();
		dd.setDemandNo(getPara("demand_no"));
		dd.setDemandName(getPara("demand_name"));
		dd.setDemandSecondName(getPara("demand_second_name"));
		renderJson(service.create(dd));
	}
	
	/**
	 * 删除需求
	 */
	public void remove(){
		renderJson(service.delete(Long.parseLong(getPara("id"))));
	}
}
