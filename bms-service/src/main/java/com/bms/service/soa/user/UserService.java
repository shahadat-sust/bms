package com.bms.service.soa.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
import com.bms.service.data.EmailAddressData;
import com.bms.service.data.PhoneNumberData;
import com.bms.service.data.PostalAddressData;
import com.bms.service.data.user.UserCardData;
import com.bms.service.data.user.UserData;
import com.bms.service.data.user.UserDeviceData;
import com.bms.service.data.user.UserProfileData;
import com.bms.service.data.user.UserSocialAccountData;
import com.bms.service.soa.BaseService;

@Service("userService")
public class UserService extends BaseService implements IUserService {

	private IUserDao userDao;
	private IUserProfileDao userProfileDao;
	private IUserDeviceDao userDeviceDao;
	private IUserSocialAccountDao userSocialAccountDao;
	private IUserCardDao userCardDao;
	private IEmailAddressDao emailAddressDao;
	private IPhoneNumberDao phoneNumberDao;
	private IPostalAddressDao postalAddressDao;
	private IImageDao imageDao;
	
	@Override
	public List<UserData> getAllUserDatas() throws BmsException, BmsSqlException {
		return userDao.getAllUserDatas();
	}
	
	@Override
	public UserData getUserDetailInfo(long userId) throws BmsException, BmsSqlException {
		UserData userData = userDao.getUserDataById(userId);
		if(userData != null) {
			UserProfileData userProfileData = userProfileDao.getUserProfileByUserId(userId);
			if(userProfileData != null) {
				userData.setUserProfileData(userProfileData);
			} else {
				userData.setUserProfileData(new UserProfileData());
			}
			
			List<UserSocialAccountData> userSocialAccountDatas = userSocialAccountDao.getAllUserSocialAccountsByUserId(userId);
			if(userSocialAccountDatas != null && userSocialAccountDatas.size() > 0) {
				userData.setUserSocialAccountDatas(userSocialAccountDatas);
			} else {
				userData.setUserSocialAccountDatas(new ArrayList<>());
			}
			
			List<UserCardData> userCardDatas = userCardDao.getAllUserCardsByUserId(userId);
			if(userCardDatas != null && userCardDatas.size() > 0) {
				userData.setUserCardDatas(userCardDatas);
			} else {
				userData.setUserCardDatas(new ArrayList<>());
			}
			
			List<EmailAddressData> emailAddressDatas = emailAddressDao.getAllEmailAddressesByUserId(userId);
			if(emailAddressDatas != null && emailAddressDatas.size() > 0) {
				userData.setEmailAddressDatas(emailAddressDatas);
			} else {
				userData.setEmailAddressDatas(new ArrayList<>());
			}
			
			List<PhoneNumberData> phoneNumberDatas = phoneNumberDao.getAllPhoneNumbersByUserId(userId);
			if(phoneNumberDatas != null && phoneNumberDatas.size() > 0) {
				userData.setPhoneNumberDatas(phoneNumberDatas);
			} else {
				userData.setPhoneNumberDatas(new ArrayList<>());
			}
			
			List<PostalAddressData> postalAddressDatas = postalAddressDao.getAllPostalAddressesByUserId(userId);
			if(postalAddressDatas != null && postalAddressDatas.size() > 0) {
				userData.setPostalAddressDatas(postalAddressDatas);
			} else {
				userData.setPostalAddressDatas(new ArrayList<>());
			}
		}
		return userData;
	}
	
	@Override
	public void updateUserDeviceLastUsedTime(long userID, String deviceToken, int platform, String deviceName, String imeiNumber, long loginUserId) throws BmsException, BmsSqlException {
		Date currDate = new Date(System.currentTimeMillis());
		UserDeviceData userDeviceData = userDeviceDao.getUserDevice(userID, deviceToken, platform);
		if(userDeviceData != null) {
			userDeviceData.setName(deviceName);
			userDeviceData.setImeiNumber(imeiNumber);
			userDeviceData.setLastUsedTime(currDate);
			userDeviceData.setUpdatedBy(loginUserId);
			userDeviceData.setUpdatedOn(currDate);
			userDeviceDao.update(userDeviceData);
		} else {
			userDeviceData = new UserDeviceData();
			userDeviceData.setUserId(userID);
			userDeviceData.setName(deviceName);
			userDeviceData.setToken(deviceToken);
			userDeviceData.setPlatform(platform);
			userDeviceData.setImeiNumber(imeiNumber);
			userDeviceData.setFirstUsedTime(currDate);
			userDeviceData.setLastUsedTime(currDate);
			userDeviceData.setCreatedBy(loginUserId);
			userDeviceData.setCreatedOn(currDate);
			userDeviceData.setUpdatedBy(loginUserId);
			userDeviceData.setUpdatedOn(currDate);
			userDeviceDao.create(userDeviceData);
		}
	}

	public boolean isUsernameAvailable(long userId, String username) throws BmsException, BmsSqlException {
		return userDao.isUsernameAvailable(userId, username);
	}
	
	@Override
	public boolean isPhoneNumberAvailableForUser(long userId, String code, String number) throws BmsException, BmsSqlException {
		return phoneNumberDao.isPhoneNumberAvailableForUser(userId, code, number);
	}

	@Override
	public boolean isEmailAvailableForUser(long userId, String username) throws BmsException, BmsSqlException {
		return emailAddressDao.isEmailAvailableForUser(userId, username);
	}
	
	@Override
	public IUserDao getUserDao() {
		return userDao;
	}

	@Autowired
	@Qualifier("userDao")
	@Override
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public IUserProfileDao getUserProfileDao() {
		return userProfileDao;
	}

	@Autowired
	@Qualifier("userProfileDao")
	@Override
	public void setUserProfileDao(IUserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
	}

	@Override
	public IUserDeviceDao getUserDeviceDao() {
		return userDeviceDao;
	}

	@Autowired
	@Qualifier("userDeviceDao")
	@Override
	public void setUserDeviceDao(IUserDeviceDao userDeviceDao) {
		this.userDeviceDao = userDeviceDao;
	}

	@Override
	public IUserSocialAccountDao getUserSocialAccountDao() {
		return userSocialAccountDao;
	}

	@Autowired
	@Qualifier("userSocialAccountDao")
	@Override
	public void setUserSocialAccountDao(IUserSocialAccountDao userSocialAccountDao) {
		this.userSocialAccountDao = userSocialAccountDao;
	}

	@Override
	public IUserCardDao getUserCardDao() {
		return userCardDao;
	}

	@Autowired
	@Qualifier("userCardDao")
	@Override
	public void setUserCardDao(IUserCardDao userCardDao) {
		this.userCardDao = userCardDao;
	}

	@Override
	public IEmailAddressDao getEmailAddressDao() {
		return emailAddressDao;
	}

	@Autowired
	@Qualifier("emailAddressDao")
	@Override
	public void setEmailAddressDao(IEmailAddressDao emailAddressDao) {
		this.emailAddressDao = emailAddressDao;
	}

	@Override
	public IPhoneNumberDao getPhoneNumberDao() {
		return phoneNumberDao;
	}

	@Autowired
	@Qualifier("phoneNumberDao")
	@Override
	public void setPhoneNumberDao(IPhoneNumberDao phoneNumberDao) {
		this.phoneNumberDao = phoneNumberDao;
	}

	@Override
	public IPostalAddressDao getPostalAddressDao() {
		return postalAddressDao;
	}

	@Autowired
	@Qualifier("postalAddressDao")
	@Override
	public void setPostalAddressDao(IPostalAddressDao postalAddressDao) {
		this.postalAddressDao = postalAddressDao;
	}

	@Override
	public IImageDao getImageDao() {
		return imageDao;
	}

	@Autowired
	@Qualifier("imageDao")
	@Override
	public void setImageDao(IImageDao imageDao) {
		this.imageDao = imageDao;
	}

}
