package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class ItemData extends BaseData {

	private static final long serialVersionUID = -2272141102926071336L;

	private long itemCategoryId;
	private String itemNo;
	private double rent;
	private int capacity;
	private boolean isActive;
	private int status;
	private String itemCategoryName;
	private long itemTypeId;
	private String itemTypeName;
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
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
	public String getItemCategoryName() {
		return itemCategoryName;
	}
	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
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
	public RoomData getRoomData() {
		return roomData;
	}
	public void setRoomData(RoomData roomData) {
		this.roomData = roomData;
	}

}
