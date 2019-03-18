package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class RoomCategoryData extends BaseData {

	private static final long serialVersionUID = 1711033027734734546L;

	private int size;
	private int numberOfBed;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getNumberOfBed() {
		return numberOfBed;
	}
	public void setNumberOfBed(int numberOfBed) {
		this.numberOfBed = numberOfBed;
	}
	
	
	
}
