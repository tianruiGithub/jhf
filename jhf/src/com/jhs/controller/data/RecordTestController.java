package com.jhs.controller.data;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.POST;
import com.jfinal.kit.Ret;
import com.jhs.interceptor.MenuInterceptor;
import com.jhs.interceptor.SessionInterceptor;
import com.jhs.service.data.RecordTestService;
import com.jhs.util.TongueUtil;

/**
 * 辩证检测Controller
 * @author 天瑞
 *
 */
@Before({ SessionInterceptor.class, MenuInterceptor.class })
public class RecordTestController extends Controller{
	
	/**
	 * ajax舌像检测请求
	 * @param imageData 图像BASE64数据
	 */
	@Clear({ SessionInterceptor.class, MenuInterceptor.class })
	@Before(POST.class)
	public void tongue(){
		Ret ret = new Ret();
		Ret result = TongueUtil.check(getPara("imageData"));
		if (result.get("state").equals(1)) {
			ret.set("status","success");
			ret.set("msg","");
			ret.set("data",result.getStr("color"));
		} else if (result.get("state").equals(2)) {
			ret.set("status","fail");
			ret.set("msg","你拍的不是舌像或光线太差");
			ret.set("data","");
		} else if (result.get("state").equals(3)) {
			ret.set("status","fail");
			ret.set("msg","请将舌像放大并放入框内");
			ret.set("data","");
		} else {
			ret.set("status","fail");
			ret.set("msg","请将舌像放大并放入框内");
			ret.set("data","");
		}
		renderJson(ret);
	}
	
	/**
	 * ajax检测记录添加
	 * @param openId 微信用户openId
	 * @param demandNo 需求编号
	 * @param imageData 舌像数据
	 * @param feature 所属特性分类
	 * @param color 舌像检测结果
	 * @param scoreList 分数
	 */
	@Clear({ SessionInterceptor.class, MenuInterceptor.class })
	@Before(POST.class)
	public void add(){
		Ret ret = new Ret();
		if(RecordTestService.me.create(getPara("openId"), getPara("demandNo"), getPara("imageData"), getPara("feature"), getPara("color"), getPara("scoreList"))){
			ret.set("status","success");
			ret.set("msg","检测记录成功");
		}
		else{
			ret.set("status","fail");
			ret.set("msg","检测记录失败");
		}
		renderJson(ret);
	}
}
