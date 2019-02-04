package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class ItemData extends BaseData {

	private static final long serialVersionUID = -2272141102926071336L;

	private long itemCategoryId;
	private String itemNo;
	private double rent;
	private boolean isActive;
	private int status;
	private RoomData roomData;
	
	public long getItemCategoryId() {
		return itemCategoryId;
	}
	public void setItemCategoryId(long itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
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
	public RoomData getRoomData() {
		return roomData;
	}
	public void setRoomData(RoomData roomData) {
		this.roomData = roomData;
	}

}
