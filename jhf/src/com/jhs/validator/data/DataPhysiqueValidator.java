package com.jhs.validator.data;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.validate.Validator;
/**
 * 体质验证
 * @author 刘阳
 *
 */
public class DataPhysiqueValidator  extends Validator{

	@Override
	protected void validate(Controller c) {
		if(StrKit.isBlank(c.getPara("physique_no"))){
			addError("status","fail");
			addError("msg","体质编号不能为空");
			return;
		}
		if(StrKit.isBlank(c.getPara("physique_name"))){
			addError("status","fail");
			addError("msg","体质名称不能为空");
			return;
		}
	}

	@Override
	protected void handleError(Controller c) {
		c.renderJson();	
	}

}
