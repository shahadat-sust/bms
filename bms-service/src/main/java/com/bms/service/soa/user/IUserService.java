package com.bms.service.soa.user;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.IEmailAddressDao;
import com.bms.service.dao.IImageDao;
import com.bms.service.dao.IPhoneNumberDao;
import com.bms.service.dao.IPostalAddressDao;
import com.bms.service.dao.user.IUserCardDao;
import com.bms.service.dao.user.IUserDao;
import com.bms.service.dao.user.IUserDeviceDao;
import com.bms.service.dao.user.IUserProfileDao;
import com.bms.service.dao.user.IUserSocialAccountDao;
import com.bms.service.data.user.UserData;

public interface IUserService {

	public void createAdminUser(UserData userData, long loginUserId) throws BmsException, BmsSqlException;
	
	public void updateAdminUser(UserData userData, long loginUserId) throws BmsException, BmsSqlException;
	
	public List<UserData> getAllUserDatas() throws BmsException, BmsSqlException;
	
	public UserData getUserDetailInfo(long userId) throws BmsException, BmsSqlException;
	
	public void updateUserDeviceLastUsedTime(long userID, String deviceToken, int platform, String deviceName, String imeiNumber, long loginUserId) throws BmsException, BmsSqlException;

	public boolean isUsernameAvailable(long userId, String username) throws BmsException, BmsSqlException;
	
	public boolean isPhoneNumberAvailableForUser(long userId, String code, String number) throws BmsException, BmsSqlException;
	
	public boolean isEmailAvailableForUser(long userId, String email) throws BmsException, BmsSqlException;
	
	public IUserDao getUserDao();

	public void setUserDao(IUserDao userDao);

	public IUserProfileDao getUserProfileDao();

	public void setUserProfileDao(IUserProfileDao userProfileDao);

	public IUserDeviceDao getUserDeviceDao();

	public void setUserDeviceDao(IUserDeviceDao userDeviceDao);

	public IUserSocialAccountDao getUserSocialAccountDao();

	public void setUserSocialAccountDao(IUserSocialAccountDao userSocialAccountDao);

	public IUserCardDao getUserCardDao();

	public void setUserCardDao(IUserCardDao userCardDao);

	public IEmailAddressDao getEmailAddressDao();

	public void setEmailAddressDao(IEmailAddressDao emailAddressDao);

	public IPhoneNumberDao getPhoneNumberDao();

	public void setPhoneNumberDao(IPhoneNumberDao phoneNumberDao);

	public IPostalAddressDao getPostalAddressDao();

	public void setPostalAddressDao(IPostalAddressDao postalAddressDao);

	public IImageDao getImageDao();

	public void setImageDao(IImageDao imageDao);

}
