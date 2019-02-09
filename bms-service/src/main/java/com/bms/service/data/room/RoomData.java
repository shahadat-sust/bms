package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class RoomData extends BaseData {

	private static final long serialVersionUID = 4038255198538699598L;

	private int floorNo;
	private int size;
	private String landLine;
	
	public int getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getLandLine() {
		return landLine;
	}
	public void setLandLine(String landLine) {
		this.landLine = landLine;
	}

}
