package com.jhs.validator.data;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
/**
 * 需求验证
 * @author 刘阳
 *
 */
public class DataDemandValidator  extends Validator{

	@Override
	protected void validate(Controller c) {
		if(StrKit.isBlank(c.getPara("demand_no"))){
			addError("status","fail");
			addError("msg","需求编号不能为空");
			return;
		}
		if(StrKit.isBlank(c.getPara("demand_name"))){
			addError("status","fail");
			addError("msg","需求名称不能为空");
			return;
		}
		if(StrKit.isBlank(c.getPara("damand_second_name"))){
			addError("status","fail");
			addError("msg","需求别名不能为空");
			return;
		}
	}

	@Override
	protected void handleError(Controller c) {
		c.renderJson();	
	}

}
