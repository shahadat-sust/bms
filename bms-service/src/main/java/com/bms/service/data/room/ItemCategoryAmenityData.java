package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class ItemCategoryAmenityData extends BaseData {

	private static final long serialVersionUID = 122457197422769523L;

	private long itemCategoryId;
	private long amenityId;
	private String amenityName;
	private int quantity;

	public long getItemCategoryId() {
		return itemCategoryId;
	}
	public void setItemCategoryId(long itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
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
