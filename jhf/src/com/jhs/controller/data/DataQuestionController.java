package com.jhs.controller.data;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Ret;
import com.jhs.common.model.DataQuestion;
import com.jhs.interceptor.MenuInterceptor;
import com.jhs.interceptor.SessionInterceptor;
import com.jhs.service.data.DataQuestionService;

/**
 * 检测问题Controller
 * @author 天瑞
 *
 */
@Before({ SessionInterceptor.class, MenuInterceptor.class })
public class DataQuestionController extends Controller{

	/**
	 * ajax查询问题请求
	 * @param demandNo 需求编号 
	 */
	@Clear({ SessionInterceptor.class, MenuInterceptor.class })
	@Before(POST.class)
	public void get(){
		Ret ret = new Ret();
		List<DataQuestion> dqList = DataQuestionService.me.queryByDemand(getPara("demandNo"));
		if(dqList !=null){
			ret.set("status","success");
			ret.set("msg","查询到数据");
			ret.set("data",dqList);
		}
		else{
			ret.set("status","fail");
			ret.set("msg","未查询到数据");
		}
		renderJson(ret);
	}
	
}
