package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class ItemTypeData extends BaseData {

	private static final long serialVersionUID = -2670102595480091893L;
	
	private String name;
	private long providerId;
	private boolean isActive;
	private int status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getProviderId() {
		return providerId;
	}
	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
