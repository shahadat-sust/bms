package com.bms.service.data;

public class CityData extends BaseData {

	private long Id;
	private long stateId;
	private String name;
	private String remarks;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public long getStateId() {
		return stateId;
	}
	public void setStateId(long stateId) {
		this.stateId = stateId;
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
