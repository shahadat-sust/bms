package com.bms.service.data.user;

import java.util.Date;

import com.bms.service.data.BaseData;

public class UserCardData extends BaseData {

	private static final long serialVersionUID = 6268160864712884508L;
	
	private long userId;
	private String cardNumber;
	private String holderName;
	private String cvvNumber;
	private Date expireDate;
	private boolean isActive;
	private int status;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getCvvNumber() {
		return cvvNumber;
	}
	public void setCvvNumber(String cvvNumber) {
		this.cvvNumber = cvvNumber;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
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
	
}
