package com.jhs.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseDataImageType<M extends BaseDataImageType<M>> extends Model<M> implements IBean {

	public void setTypeId(java.lang.Long typeId) {
		set("type_id", typeId);
	}

	public java.lang.Long getTypeId() {
		return get("type_id");
	}

	public void setTypeName(java.lang.String typeName) {
		set("type_name", typeName);
	}

	public java.lang.String getTypeName() {
		return get("type_name");
	}

}