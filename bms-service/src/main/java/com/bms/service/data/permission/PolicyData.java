package com.bms.service.data.permission;

import com.bms.service.data.BaseData;

public class PolicyData extends BaseData {

	private static final long serialVersionUID = -3056457609965164943L;
	
	private long parentId;
	private String name;
	private String code;
	private int type;

	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
