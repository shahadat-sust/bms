package com.bms.service.data.provider;

import com.bms.service.data.BaseData;

public class AmenityData extends BaseData {

	private static final long serialVersionUID = 122457197422769523L;

	private String name;
	private int type;
	private long providerTypeId;
	private String providerTypeName;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
