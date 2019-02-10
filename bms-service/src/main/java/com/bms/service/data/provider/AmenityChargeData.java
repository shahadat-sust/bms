package com.bms.service.data.provider;

import com.bms.service.data.BaseData;

public class AmenityChargeData extends BaseData {

	private static final long serialVersionUID = 122457197422769523L;

	private long providerId;
	private long amenityId;
	private String amenityName;
	private double charge;
	
	public long getProviderId() {
		return providerId;
	}
	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}
	public long getAmenityId() {
		return amenityId;
	}
	public void setAmenityId(long amenityId) {
		this.amenityId = amenityId;
	}
	public String getAmenityName() {
		return amenityName;
	}
	public void setAmenityName(String amenityName) {
		this.amenityName = amenityName;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}

}
