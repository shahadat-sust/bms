package com.bms.service.data.provider;

import com.bms.service.data.BaseData;

public class ProviderPointOfInterestData extends BaseData {

	private static final long serialVersionUID = 8074347039682854267L;
	
	private long providerId;
	private long pointOfInterestId;
	private String pointOfInterestName;
	
	public long getProviderId() {
		return providerId;
	}
	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}
	public long getPointOfInterestId() {
		return pointOfInterestId;
	}
	public void setPointOfInterestId(long pointOfInterestId) {
		this.pointOfInterestId = pointOfInterestId;
	}
	public String getPointOfInterestName() {
		return pointOfInterestName;
	}
	public void setPointOfInterestName(String pointOfInterestName) {
		this.pointOfInterestName = pointOfInterestName;
	}
	
}
