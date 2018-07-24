package com.bms.service.data;

public class CityData extends BaseData {

	private static final long serialVersionUID = -4499044319126060624L;
	
	private long stateId;
	private String name;
	
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
	
}
