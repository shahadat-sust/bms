package com.bms.service.data.permission;

import com.bms.service.data.BaseData;

public class GroupData extends BaseData {

	private long Id;
	private String name;
	private String remarks;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
