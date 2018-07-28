package com.bms.service.soa.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.IEmailAddressDao;
import com.bms.service.dao.IImageDao;
import com.bms.service.dao.IPhoneNumberDao;
import com.bms.service.dao.IPostalAddressDao;
import com.bms.service.dao.user.IUserDao;
import com.bms.service.dao.user.IUserDeviceDao;
import com.bms.service.dao.user.IUserProfileDao;
import com.bms.service.dao.user.IUserSocialAccountDao;
import com.bms.service.data.user.UserData;

public interface IRegistrationService {

	public long registerUser(UserData userData, long loginUserId) throws BmsException, BmsSqlException;
	
	public boolean isUserAlearyExists(String username, String email, int socialType, String socialID) throws BmsException, BmsSqlException;

	public PlatformTransactionManager getTxManager();

	public void setTxManager(PlatformTransactionManager transactionManager);
	
	public IUserDao getUserDao();
	
	public void setUserDao(IUserDao userDao);
	
	public IUserProfileDao getUserProfileDao();

	public void setUserProfileDao(IUserProfileDao userProfileDao);
	
	public IEmailAddressDao getEmailAddressDao();
	
	public void setEmailAddressDao(IEmailAddressDao emailAddressDao);
	
	public IPostalAddressDao getPostalAddressDao();

	public void setPostalAddressDao(IPostalAddressDao postalAddressDao);

	public IPhoneNumberDao getPhoneNumberDao();

	public void setPhoneNumberDao(IPhoneNumberDao phoneNumberDao);
	
	public IUserSocialAccountDao getUserSocialAccountDao();
	
	public void setUserSocialAccountDao(IUserSocialAccountDao userSocialAccountDao);
	
	public IUserDeviceDao getUserDeviceDao();
	
	public void setUserDeviceDao(IUserDeviceDao userDeviceDao);

	public IImageDao getImageDao();
	
	public void setImageDao(IImageDao imageDao);

}
