package com.bms.service.data.user;

import com.bms.service.data.BaseData;

public class UserSocialAccountData extends BaseData {

	private static final long serialVersionUID = -7494232905512164772L;
	
	private long userId;
	private int type;
	private String accountId;
	private boolean isVerified;
	private int status;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
