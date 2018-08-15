package com.bms.admin.model;

public class CountryCodeModel {

	private String name;
	private String code;
	private String shortCode;
	private String priority;
	
	public CountryCodeModel() {
		
	}
	
	public CountryCodeModel(String code, String shortCode, String name, String priority) {
		this();
		this.code = code;
		this.shortCode = shortCode;
		this.name = name;
		this.priority = priority;
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

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

}
