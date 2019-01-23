package com.bms.service.soa.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.common.util.CryptoChiper;
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
import com.bms.service.data.provider.ProviderData;
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
	public void createAdminUser(UserData userData, long loginUserId) throws BmsException, BmsSqlException {
		TransactionStatus txStatus = null;
		
		try {
			txStatus = getTxManager().getTransaction(new DefaultTransactionDefinition());
			Date currDate = new Date(System.currentTimeMillis());
			
			userData.setPassword(new CryptoChiper().encrypt(userData.getPassword()));
			userData.setActive(true);
			userData.setStatus(Constants.STATUS_EXIST);
			userData.setCreatedBy(loginUserId);
			userData.setCreatedOn(currDate);
			userData.setUpdatedBy(loginUserId);
			userData.setUpdatedOn(currDate);
			long userId = userDao.create(userData);
			userData.setId(userId);
			
			UserProfileData userProfileData = userData.getUserProfileData();
			userProfileData.setUserId(userId);
			userProfileData.setCreatedBy(loginUserId);
			userProfileData.setCreatedOn(currDate);
			userProfileData.setUpdatedBy(loginUserId);
			userProfileData.setUpdatedOn(currDate);
			long userProfileId = userProfileDao.create(userProfileData);
			userProfileData.setId(userProfileId);

			EmailAddressData emailAddressData = userData.getEmailAddressDatas().get(0);
			emailAddressData.setUserId(userId);
			emailAddressData.setVerified(true);
			emailAddressData.setPrimary(true);
			emailAddressData.setStatus(Constants.STATUS_EXIST);
			emailAddressData.setCreatedBy(loginUserId);
			emailAddressData.setCreatedOn(currDate);
			emailAddressData.setUpdatedBy(loginUserId);
			emailAddressData.setUpdatedOn(currDate);
			long emailAddressId = emailAddressDao.create(userId, 0, emailAddressData);
			emailAddressData.setId(emailAddressId);
			
			PhoneNumberData phoneNumberData = userData.getPhoneNumberDatas().get(0);
			phoneNumberData.setUserId(userId);
			phoneNumberData.setVerified(true);
			phoneNumberData.setPrimary(true);
			phoneNumberData.setStatus(Constants.STATUS_EXIST);
			phoneNumberData.setCreatedBy(loginUserId);
			phoneNumberData.setCreatedOn(currDate);
			phoneNumberData.setUpdatedBy(loginUserId);
			phoneNumberData.setUpdatedOn(currDate);
			long phoneNumberId = phoneNumberDao.create(userId, 0, phoneNumberData);
			phoneNumberData.setId(phoneNumberId);

			PostalAddressData postalAddressData = userData.getPostalAddressDatas().get(0);
			postalAddressData.setUserId(userId);
			postalAddressData.setCreatedBy(loginUserId);
			postalAddressData.setCreatedOn(currDate);
			postalAddressData.setUpdatedBy(loginUserId);
			postalAddressData.setUpdatedOn(currDate);
			long postalAddressId = postalAddressDao.create(userId, 0, postalAddressData);
			postalAddressData.setId(postalAddressId);
			
			getTxManager().commit(txStatus);
		} catch (BmsSqlException e) {
			if(txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			userData.setId(0);	
			userData.getUserProfileData().setId(0);
			userData.getUserProfileData().setUserId(0);
			userData.getEmailAddressDatas().get(0).setId(0);
			userData.getEmailAddressDatas().get(0).setUserId(0);
			userData.getPhoneNumberDatas().get(0).setId(0);
			userData.getPhoneNumberDatas().get(0).setUserId(0);
			userData.getPostalAddressDatas().get(0).setId(0);
			userData.getPostalAddressDatas().get(0).setUserId(0);
			throw e;
		} catch (Exception e) {
			if(txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			userData.setId(0);	
			userData.getUserProfileData().setId(0);
			userData.getUserProfileData().setUserId(0);
			userData.getEmailAddressDatas().get(0).setId(0);
			userData.getEmailAddressDatas().get(0).setUserId(0);
			userData.getPhoneNumberDatas().get(0).setId(0);
			userData.getPhoneNumberDatas().get(0).setUserId(0);
			userData.getPostalAddressDatas().get(0).setId(0);
			userData.getPostalAddressDatas().get(0).setUserId(0);
			throw new BmsException(e);
		}
	}

	@Override
	public void updateAdminUser(UserData userData, long loginUserId) throws BmsException, BmsSqlException {
		TransactionStatus txStatus = null;
		long existingUserProfileId = 0;
		long existingEmailAddressId = 0;
		long existingPhoneNumberId = 0;
		long existingPostalAddressId = 0;
		
		try {
			UserProfileData userProfileData = userData.getUserProfileData();
			existingUserProfileId = userProfileData.getId();
			EmailAddressData emailAddressData = userData.getEmailAddressDatas().get(0);
			existingEmailAddressId = emailAddressData.getId();
			PhoneNumberData phoneNumberData = userData.getPhoneNumberDatas().get(0);
			existingPhoneNumberId = phoneNumberData.getId();
			PostalAddressData postalAddressData = userData.getPostalAddressDatas().get(0);
			existingPostalAddressId = postalAddressData.getId();
			
			txStatus = getTxManager().getTransaction(new DefaultTransactionDefinition());
			Date currDate = new Date(System.currentTimeMillis());

			if(existingUserProfileId == 0) { 
				userProfileData.setUserId(userData.getId());
				userProfileData.setCreatedBy(loginUserId);
				userProfileData.setCreatedOn(currDate);
				userProfileData.setUpdatedBy(loginUserId);
				userProfileData.setUpdatedOn(currDate);
				long userProfileId = userProfileDao.create(userProfileData);
				userProfileData.setId(userProfileId);
			} else {
				userProfileData.setUpdatedBy(loginUserId);
				userProfileData.setUpdatedOn(currDate);
				userProfileDao.update(userProfileData);
			}

			if(existingEmailAddressId == 0) { 
				emailAddressData.setUserId(userData.getId());
				emailAddressData.setVerified(true);
				emailAddressData.setPrimary(true);
				emailAddressData.setStatus(Constants.STATUS_EXIST);
				emailAddressData.setCreatedBy(loginUserId);
				emailAddressData.setCreatedOn(currDate);
				emailAddressData.setUpdatedBy(loginUserId);
				emailAddressData.setUpdatedOn(currDate);
				long emailAddressId = emailAddressDao.create(userData.getId(), 0, emailAddressData);
				emailAddressData.setId(emailAddressId);
			} else {
				EmailAddressData tempData = emailAddressDao.getEmailAddressById(emailAddressData.getId());
				emailAddressData.setVerified(tempData.isVerified());
				emailAddressData.setPrimary(tempData.isPrimary());
				emailAddressData.setStatus(tempData.getStatus());
				emailAddressData.setUpdatedBy(loginUserId);
				emailAddressData.setUpdatedOn(currDate);
				emailAddressDao.update(emailAddressData);
			}

			if(existingPhoneNumberId == 0) { 
				phoneNumberData.setUserId(userData.getId());
				phoneNumberData.setVerified(true);
				phoneNumberData.setPrimary(true);
				phoneNumberData.setStatus(Constants.STATUS_EXIST);
				phoneNumberData.setCreatedBy(loginUserId);
				phoneNumberData.setCreatedOn(currDate);
				phoneNumberData.setUpdatedBy(loginUserId);
				phoneNumberData.setUpdatedOn(currDate);
				long phoneNumberId = phoneNumberDao.create(userData.getId(), 0, phoneNumberData);
				emailAddressData.setId(phoneNumberId);
			} else {
				PhoneNumberData tempData = phoneNumberDao.getPhoneNumberById(phoneNumberData.getId());
				phoneNumberData.setVerified(tempData.isVerified());
				phoneNumberData.setPrimary(tempData.isPrimary());
				phoneNumberData.setStatus(tempData.getStatus());
				phoneNumberData.setUpdatedBy(loginUserId);
				phoneNumberData.setUpdatedOn(currDate);
				phoneNumberDao.update(phoneNumberData);
			}
			
			if(existingPostalAddressId == 0) { 
				postalAddressData.setUserId(userData.getId());
				postalAddressData.setCreatedBy(loginUserId);
				postalAddressData.setCreatedOn(currDate);
				postalAddressData.setUpdatedBy(loginUserId);
				postalAddressData.setUpdatedOn(currDate);
				long postalAddressId = postalAddressDao.create(userData.getId(), 0, postalAddressData);
				postalAddressData.setId(postalAddressId);
			} else {
				postalAddressData.setUpdatedBy(loginUserId);
				postalAddressData.setUpdatedOn(currDate);
				postalAddressDao.update(postalAddressData);
			}
			
			getTxManager().commit(txStatus);
		} catch (BmsSqlException e) {
			if (txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			if (existingUserProfileId == 0) {
				userData.getUserProfileData().setId(0);
				userData.getUserProfileData().setUserId(0);
			}
			if (existingEmailAddressId == 0) {
				userData.getEmailAddressDatas().get(0).setId(0);
				userData.getEmailAddressDatas().get(0).setUserId(0);
			}
			if (existingPhoneNumberId == 0) {
				userData.getPhoneNumberDatas().get(0).setId(0);
				userData.getEmailAddressDatas().get(0).setUserId(0);
			}
			if (existingPostalAddressId == 0) {
				userData.getPostalAddressDatas().get(0).setId(0);
				userData.getEmailAddressDatas().get(0).setUserId(0);
			}
			throw e;
		} catch (Exception e) {
			if(txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			if (existingUserProfileId == 0) {
				userData.getUserProfileData().setId(0);
				userData.getUserProfileData().setUserId(0);
			}
			if (existingEmailAddressId == 0) {
				userData.getEmailAddressDatas().get(0).setId(0);
				userData.getEmailAddressDatas().get(0).setUserId(0);
			}
			if (existingPhoneNumberId == 0) {
				userData.getPhoneNumberDatas().get(0).setId(0);
				userData.getEmailAddressDatas().get(0).setUserId(0);
			}
			if (existingPostalAddressId == 0) {
				userData.getPostalAddressDatas().get(0).setId(0);
				userData.getEmailAddressDatas().get(0).setUserId(0);
			}
			throw new BmsException(e);
		}
	}
	
	@Override
	public boolean deleteUser(long userId, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			UserData userData = new UserData();
			userData.setId(userId);
			userData.setUpdatedBy(loginUserId);
			userData.setUpdatedOn(currDate);
			return userDao.delete(userData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public List<UserData> getAllUserDatas() throws BmsException, BmsSqlException {
		try {
			return userDao.getAllUserDatas();
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public UserData getUserDetailInfo(long userId) throws BmsException, BmsSqlException {
		try {
			UserData userData = userDao.getUserDataById(userId);
			if(userData != null) {
				UserProfileData userProfileData = userProfileDao.getUserProfileDataByUserId(userId);
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
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public void updateUserDeviceLastUsedTime(long userID, String deviceToken, int platform, String deviceName, String imeiNumber, long loginUserId) throws BmsException, BmsSqlException {
		try {
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
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	public boolean isUsernameAvailable(long userId, String username) throws BmsException, BmsSqlException {
		try {
			return userDao.isUsernameAvailable(userId, username);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public boolean isPhoneNumberAvailableForUser(long userId, String code, String number) throws BmsException, BmsSqlException {
		try {
			return phoneNumberDao.isPhoneNumberAvailableForUser(userId, code, number);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isEmailAvailableForUser(long userId, String email) throws BmsException, BmsSqlException {
		try {
			return emailAddressDao.isEmailAvailableForUser(userId, email);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public List<UserData> getSearchUser(String name, String username, String email, String code, String number) throws BmsException, BmsSqlException {
		try {
			return userDao.getSearchUser(name, username, email, code, number);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
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
