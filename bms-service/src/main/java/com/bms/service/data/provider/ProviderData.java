package com.bms.service.data.provider;

import java.util.List;

import com.bms.service.data.BaseData;
import com.bms.service.data.EmailAddressData;
import com.bms.service.data.ImageData;
import com.bms.service.data.PhoneNumberData;
import com.bms.service.data.PostalAddressData;

public class ProviderData extends BaseData {

	private static final long serialVersionUID = -6653235600835306969L;
	
	private String title;
	private long providerTypeId;
	private String specification;
	private boolean isActive;
	private int status;
	private String remarks;
	private HotelData hotelData;
	private List<EmailAddressData> emailAddressDatas;
	private List<PhoneNumberData> phoneNumberDatas;
	private List<PostalAddressData> postalAddressDatas;
	private List<ImageData> imageDatas;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getProviderTypeId() {
		return providerTypeId;
	}

	public void setProviderTypeId(long providerTypeId) {
		this.providerTypeId = providerTypeId;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public HotelData getHotelData() {
		return hotelData;
	}

	public void setHotelData(HotelData hotelData) {
		this.hotelData = hotelData;
	}

	public List<EmailAddressData> getEmailAddressDatas() {
		return emailAddressDatas;
	}

	public void setEmailAddressDatas(List<EmailAddressData> emailAddressDatas) {
		this.emailAddressDatas = emailAddressDatas;
	}

	public List<PhoneNumberData> getPhoneNumberDatas() {
		return phoneNumberDatas;
	}

	public void setPhoneNumberDatas(List<PhoneNumberData> phoneNumberDatas) {
		this.phoneNumberDatas = phoneNumberDatas;
	}

	public List<PostalAddressData> getPostalAddressDatas() {
		return postalAddressDatas;
	}

	public void setPostalAddressDatas(List<PostalAddressData> postalAddressDatas) {
		this.postalAddressDatas = postalAddressDatas;
	}

	public List<ImageData> getImageDatas() {
		return imageDatas;
	}

	public void setImageDatas(List<ImageData> imageDatas) {
		this.imageDatas = imageDatas;
	}

}
