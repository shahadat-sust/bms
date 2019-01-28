package com.bms.service.data.user;

import com.bms.service.data.BaseData;

public class UserRoleData extends BaseData {

	private static final long serialVersionUID = -9092831319377397305L;
	
	private long userId;
	private long roleId;
	private String roleName;
	private int priority;
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	
	
}
