package com.bms.service.data.permission;

import com.bms.service.data.BaseData;

public class RoleData extends BaseData {

	private static final long serialVersionUID = 882380817053877211L;
	
	private String name;
	private int priority;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

}
