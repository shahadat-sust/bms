package com.bms.service.data;

public class StateData extends BaseData {

	private long Id;
	private long countryId;
	private String name;
	private String remarks;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long countryId) {
		this.countryId = countryId;
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
