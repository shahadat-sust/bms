package com.bms.service.data.user;

import java.util.List;

import com.bms.service.data.BaseData;
import com.bms.service.data.EmailAddressData;
import com.bms.service.data.ImageData;
import com.bms.service.data.PhoneNumberData;
import com.bms.service.data.PostalAddressData;

public class UserData extends BaseData {

	private static final long serialVersionUID = -8827275120341700194L;
	
	private String username;
	private String password;
	private String repassword;
	private boolean isActive;
	private int status;
	
	private UserProfileData userProfileData;
	private List<EmailAddressData> emailAddressDatas;
	private List<PhoneNumberData> phoneNumberDatas;
	private List<PostalAddressData> postalAddressDatas;
	private List<UserCardData> userCardDatas;
	private List<UserDeviceData> userDeviceDatas;
	private List<UserSocialAccountData> userSocialAccountDatas;
	private List<ImageData> imageDatas;
	private UserGroupData userGroupData;
	private UserRoleData userRoleData;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
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

	public UserProfileData getUserProfileData() {
		return userProfileData;
	}
	public void setUserProfileData(UserProfileData userProfileData) {
		this.userProfileData = userProfileData;
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
	public List<UserCardData> getUserCardDatas() {
		return userCardDatas;
	}
	public void setUserCardDatas(List<UserCardData> userCardDatas) {
		this.userCardDatas = userCardDatas;
	}
	public List<UserDeviceData> getUserDeviceDatas() {
		return userDeviceDatas;
	}
	public void setUserDeviceDatas(List<UserDeviceData> userDeviceDatas) {
		this.userDeviceDatas = userDeviceDatas;
	}
	public List<UserSocialAccountData> getUserSocialAccountDatas() {
		return userSocialAccountDatas;
	}
	public void setUserSocialAccountDatas(List<UserSocialAccountData> userSocialAccountDatas) {
		this.userSocialAccountDatas = userSocialAccountDatas;
	}
	public List<ImageData> getImageDatas() {
		return imageDatas;
	}
	public void setImageDatas(List<ImageData> imageDatas) {
		this.imageDatas = imageDatas;
	}
	public UserGroupData getUserGroupData() {
		return userGroupData;
	}
	public void setUserGroupData(UserGroupData userGroupData) {
		this.userGroupData = userGroupData;
	}
	public UserRoleData getUserRoleData() {
		return userRoleData;
	}
	public void setUserRoleData(UserRoleData userRoleData) {
		this.userRoleData = userRoleData;
	}
	
}
