package com.bms.service.data.user;

import java.util.Date;

import com.bms.service.data.BaseData;

public class UserDeviceData extends BaseData {

	private static final long serialVersionUID = -7098875631591703491L;
	
	private long userId;
	private String name;
	private String token;
	private String imeiNumber;
	private int platform; 
	private Date firstUsedTime;
	private Date lastUsedTime;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getImeiNumber() {
		return imeiNumber;
	}
	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
	public Date getFirstUsedTime() {
		return firstUsedTime;
	}
	public void setFirstUsedTime(Date firstUsedTime) {
		this.firstUsedTime = firstUsedTime;
	}
	public Date getLastUsedTime() {
		return lastUsedTime;
	}
	public void setLastUsedTime(Date lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}
	
}
