package com.bms.service.data.provider;

import com.bms.service.data.BaseData;

public class PointOfInterestData extends BaseData {

	private static final long serialVersionUID = 8074347039682854267L;
	
	private String name;
	private long providerTypeId;
	private String providerTypeName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getProviderTypeId() {
		return providerTypeId;
	}

	public void setProviderTypeId(long providerTypeId) {
		this.providerTypeId = providerTypeId;
	}

	public String getProviderTypeName() {
		return providerTypeName;
	}

	public void setProviderTypeName(String providerTypeName) {
		this.providerTypeName = providerTypeName;
	}

}
