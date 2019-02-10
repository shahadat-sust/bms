package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class ItemAmenityData extends BaseData {

	private static final long serialVersionUID = 122457197422769523L;

	private long itemId;
	private long amenityId;
	private String amenityName;
	private int quantity;

	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
