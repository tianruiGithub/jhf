package com.jhs.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDataXuewei<M extends BaseDataXuewei<M>> extends Model<M> implements IBean {

	public void setXueweiId(java.lang.Long xueweiId) {
		set("xuewei_id", xueweiId);
	}

	public java.lang.Long getXueweiId() {
		return get("xuewei_id");
	}

	public void setXueweiName(java.lang.String xueweiName) {
		set("xuewei_name", xueweiName);
	}

	public java.lang.String getXueweiName() {
		return get("xuewei_name");
	}

	public void setXueweiImage(java.lang.String xueweiImage) {
		set("xuewei_image", xueweiImage);
	}

	public java.lang.String getXueweiImage() {
		return get("xuewei_image");
	}

	public void setXueweiQufa(java.lang.String xueweiQufa) {
		set("xuewei_qufa", xueweiQufa);
	}

	public java.lang.String getXueweiQufa() {
		return get("xuewei_qufa");
	}

	public void setXueweiGongxiao(java.lang.String xueweiGongxiao) {
		set("xuewei_gongxiao", xueweiGongxiao);
	}

	public java.lang.String getXueweiGongxiao() {
		return get("xuewei_gongxiao");
	}

}