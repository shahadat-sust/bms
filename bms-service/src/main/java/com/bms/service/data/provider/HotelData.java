package com.bms.service.data.provider;

import java.time.LocalTime;
import org.springframework.format.annotation.DateTimeFormat;
import com.bms.service.data.BaseData;

public class HotelData extends BaseData {

	private static final long serialVersionUID = -1606855471041072669L;
	
	private long providerId;
	private int starRating;
	private int numberOfFloor;
	private double latitude;
	private double longitude;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime checkInTime;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime checkOutTime;
	private int maxChildAge;
	private String website;
	private String remarks;
	
	public long getProviderId() {
		return providerId;
	}
	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}
	public int getStarRating() {
		return starRating;
	}
	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
	public int getNumberOfFloor() {
		return numberOfFloor;
	}
	public void setNumberOfFloor(int numberOfFloor) {
		this.numberOfFloor = numberOfFloor;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public LocalTime getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(LocalTime checkInTime) {
		this.checkInTime = checkInTime;
	}
	public LocalTime getCheckOutTime() {
		return checkOutTime;
	}
	public void setCheckOutTime(LocalTime checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public int getMaxChildAge() {
		return maxChildAge;
	}
	public void setMaxChildAge(int maxChildAge) {
		this.maxChildAge = maxChildAge;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
