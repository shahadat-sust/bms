package com.bms.service.data.user;

import java.util.Date;

import com.bms.common.util.StringUtils;
import com.bms.service.data.BaseData;

public class UserProfileData extends BaseData {

	private static final long serialVersionUID = 5352321731521068652L;
	
	private long userId;
	private String firstName;
	private String lastName;
	private int gender;
	private Date birthDay;
	private String securityNumber;
	private String passportNumber;
	private String drivingLicenceNumber;
	private String caption;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getSecurityNumber() {
		return securityNumber;
	}
	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getDrivingLicenceNumber() {
		return drivingLicenceNumber;
	}
	public void setDrivingLicenceNumber(String drivingLicenceNumber) {
		this.drivingLicenceNumber = drivingLicenceNumber;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getFullName() {
		return (!StringUtils.isNullEmptyOrWhiteSpace(firstName) ? firstName.trim() : "") 
				+ (!StringUtils.isNullEmptyOrWhiteSpace(lastName) ? " " + lastName.trim() : "");
	}
	public String getShortName() {
		String fullName = getFullName();
		return fullName.length() > 20 ? fullName.substring(0, 20) : fullName;
	}
}
