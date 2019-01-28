package com.bms.service.data.user;

import com.bms.service.data.BaseData;

public class UserGroupData extends BaseData {

	private static final long serialVersionUID = 2494852269398130100L;
	
	private long userId;
	private long groupId;
	private String groupName;
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getGroupId() {
		return groupId;
	}
	
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
