package com.bms.service.data;

public class ImageData extends BaseData {

	private static final long serialVersionUID = -5017417102606469670L;
	
	private String url;
	private int type;
	private boolean isDefault;
	private int status;
	private long userId;
	private long providerId;
	private long itemCategoryId;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getProviderId() {
		return providerId;
	}
	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}
	public long getItemCategoryId() {
		return itemCategoryId;
	}
	public void setItemCategoryId(long itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}
	
}
