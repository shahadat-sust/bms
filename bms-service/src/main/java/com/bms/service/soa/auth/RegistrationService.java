package com.bms.service.soa.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bms.common.BmsException;
import com.bms.common.util.StringUtils;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.IEmailAddressDao;
import com.bms.service.dao.IImageDao;
import com.bms.service.dao.IPhoneNumberDao;
import com.bms.service.dao.IPostalAddressDao;
import com.bms.service.dao.user.IUserDao;
import com.bms.service.dao.user.IUserDeviceDao;
import com.bms.service.dao.user.IUserProfileDao;
import com.bms.service.dao.user.IUserSocialAccountDao;
import com.bms.service.data.EmailAddressData;
import com.bms.service.data.ImageData;
import com.bms.service.data.PhoneNumberData;
import com.bms.service.data.PostalAddressData;
import com.bms.service.data.user.UserData;
import com.bms.service.data.user.UserDeviceData;
import com.bms.service.data.user.UserProfileData;
import com.bms.service.data.user.UserSocialAccountData;
import com.bms.service.soa.BaseService;

@Service("registrationService")
public class RegistrationService extends BaseService implements IRegistrationService {

	private IUserDao userDao;
	private IUserProfileDao userProfileDao;
	private IEmailAddressDao emailAddressDao;
	private IPostalAddressDao postalAddressDao;
	private IPhoneNumberDao phoneNumberDao;
	private IUserSocialAccountDao userSocialAccountDao;
	private IUserDeviceDao userDeviceDao;
	private IImageDao imageDao;

	@Override
	public long registerUser(UserData userData, long loginUserId) throws BmsException, BmsSqlException {
		TransactionStatus txStatus = null;
		try {
			txStatus = getTxManager().getTransaction(new DefaultTransactionDefinition());
			Date currDate = new Date(System.currentTimeMillis());
			userData.setCreatedBy(loginUserId);
			userData.setCreatedOn(currDate);
			userData.setUpdatedBy(loginUserId);
			userData.setUpdatedOn(currDate);
			userData.setId(userDao.create(userData));
			
			if(loginUserId == 0) {
				loginUserId = userData.getId();
			}
			
			UserProfileData userProfileData = userData.getUserProfileData();
			userProfileData.setUserId(userData.getId());
			userProfileData.setCreatedBy(loginUserId);
			userProfileData.setCreatedOn(currDate);
			userProfileData.setUpdatedBy(loginUserId);
			userProfileData.setUpdatedOn(currDate);
			userProfileDao.create(userData.getUserProfileData());

			if(userData.getEmailAddressDatas().size() > 0) {
				for(EmailAddressData emailAddressData : userData.getEmailAddressDatas()) {
					emailAddressData.setCreatedBy(loginUserId);
					emailAddressData.setCreatedOn(currDate);
					emailAddressData.setUpdatedBy(loginUserId);
					emailAddressData.setUpdatedOn(currDate);
					emailAddressDao.create(userData.getId(), 0, emailAddressData);
				}
			}
			
			if(userData.getPostalAddressDatas().size() > 0) {
				for(PostalAddressData postalAddressData : userData.getPostalAddressDatas()) {
					postalAddressData.setCreatedBy(loginUserId);
					postalAddressData.setCreatedOn(currDate);
					postalAddressData.setUpdatedBy(loginUserId);
					postalAddressData.setUpdatedOn(currDate);
					postalAddressDao.create(userData.getId(), 0, postalAddressData);
				}
			}
			
			if(userData.getPhoneNumberDatas().size() > 0) {
				for(PhoneNumberData phoneNumberData : userData.getPhoneNumberDatas()) {
					phoneNumberData.setCreatedBy(loginUserId);
					phoneNumberData.setCreatedOn(currDate);
					phoneNumberData.setUpdatedBy(loginUserId);
					phoneNumberData.setUpdatedOn(currDate);
					phoneNumberDao.create(userData.getId(), 0, phoneNumberData);
				}
			}
			
			if(userData.getUserSocialAccountDatas().size() > 0) {
				for(UserSocialAccountData userSocialAccountData : userData.getUserSocialAccountDatas()) {
					userSocialAccountData.setUserId(userData.getId());
					userSocialAccountData.setCreatedBy(loginUserId);
					userSocialAccountData.setCreatedOn(currDate);
					userSocialAccountData.setUpdatedBy(loginUserId);
					userSocialAccountData.setUpdatedOn(currDate);
					userSocialAccountDao.create(userSocialAccountData);
				}
			}
			
			if(userData.getImageDatas().size() > 0) {
				for(ImageData imageData : userData.getImageDatas()) {
					imageData.setCreatedBy(loginUserId);
					imageData.setCreatedOn(currDate);
					imageData.setUpdatedBy(loginUserId);
					imageData.setUpdatedOn(currDate);
					imageDao.create(userData.getId(), 0, 0, imageData);
				}
			}
			
			if(userData.getUserDeviceDatas().size() > 0) {
				for(UserDeviceData userDeviceData : userData.getUserDeviceDatas()) {
					userDeviceData.setUserId(userData.getId());
					userDeviceData.setCreatedBy(loginUserId);
					userDeviceData.setCreatedOn(currDate);
					userDeviceData.setUpdatedBy(loginUserId);
					userDeviceData.setUpdatedOn(currDate);
					userDeviceDao.create(userDeviceData);
				}
			}
			
			/*UserGroupDTO userGroupDTO = new UserGroupDTO();
			userGroupDTO.setUserID(userData.getID());
			userGroupDTO.setGroupID(Constants.VALUE_GROUP_CLIENT);
			userGroupDao.create(userGroupDTO);*/
			
			getTxManager().commit(txStatus);
		} catch (BmsSqlException e) {
			if(txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			userData.setId(0);
			throw e;
		} catch (Exception e) {
			if(txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			userData.setId(0);
			throw new BmsException(e);
		}
		return userData.getId();
	}
	
	@Override
	public boolean isUserAlearyExists(String user, String email, int socialType, String socialID) throws BmsException, BmsSqlException {
		try {
			if(!StringUtils.isNullEmptyOrWhiteSpace(email)) {
				return this.emailAddressDao.getEmailAddressByEmail(email) != null;
			} else if(!StringUtils.isNullEmptyOrWhiteSpace(socialID)) {
				return this.userSocialAccountDao.getUserSocialAccountByTypeAccountId(socialType, socialID) != null;
			}
			return true;
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
	public void setUserProfileDao(IUserProfileDao userGroupDao) {
		this.userProfileDao = userGroupDao;
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
	public IUserDeviceDao getUserDeviceDao() {
		return userDeviceDao;
	}
	
	@Autowired
	@Qualifier("userDeviceDao")
	@Override
	public void setUserDeviceDao(IUserDeviceDao userDeviceDao) {
		this.userDeviceDao = userDeviceDao;
	}

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
