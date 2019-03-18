package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class ItemCategoryData extends BaseData {

	private static final long serialVersionUID = 4950784950137073301L;
	
	private String name;
	private long itemTypeId;
	private String itemTypeName;
	private double rent;
	private int adultCapacity;
	private int childCapacity;
	private boolean isActive;
	private int status;
	private RoomCategoryData roomCategoryData;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public int getAdultCapacity() {
		return adultCapacity;
	}
	public void setAdultCapacity(int adultCapacity) {
		this.adultCapacity = adultCapacity;
	}
	public int getChildCapacity() {
		return childCapacity;
	}
	public void setChildCapacity(int childCapacity) {
		this.childCapacity = childCapacity;
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
	public RoomCategoryData getRoomCategoryData() {
		return roomCategoryData;
	}
	public void setRoomCategoryData(RoomCategoryData roomCategoryData) {
		this.roomCategoryData = roomCategoryData;
	}
	
	
}
