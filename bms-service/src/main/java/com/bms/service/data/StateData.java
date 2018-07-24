package com.bms.service.data;

public class StateData extends BaseData {

	private static final long serialVersionUID = -5501570908633019318L;
	
	private long countryId;
	private String name;
	
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

}
