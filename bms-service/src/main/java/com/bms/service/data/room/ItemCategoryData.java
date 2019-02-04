package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class ItemCategoryData extends BaseData {

	private static final long serialVersionUID = 4950784950137073301L;
	
	private String name;
	private long itemTypeId;
	private double rent;
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
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
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
