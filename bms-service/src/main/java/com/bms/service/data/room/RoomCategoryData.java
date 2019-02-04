package com.bms.service.data.room;

import com.bms.service.data.BaseData;

public class RoomCategoryData extends BaseData {

	private static final long serialVersionUID = 1711033027734734546L;
	
	private long itemCategoryId;
	private int size;
	
	public long getItemCategoryId() {
		return itemCategoryId;
	}
	public void setItemCategoryId(long itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
