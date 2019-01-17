package com.bms.service.soa.provider;

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
import com.bms.common.SetupConstants;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.IEmailAddressDao;
import com.bms.service.dao.IImageDao;
import com.bms.service.dao.IPhoneNumberDao;
import com.bms.service.dao.IPostalAddressDao;
import com.bms.service.dao.provider.IHotelDao;
import com.bms.service.dao.provider.IProviderDao;
import com.bms.service.data.EmailAddressData;
import com.bms.service.data.PhoneNumberData;
import com.bms.service.data.PostalAddressData;
import com.bms.service.data.provider.HotelData;
import com.bms.service.data.provider.ProviderData;
import com.bms.service.soa.BaseService;

@Service("hotelProviderService")
public class HotelProviderService extends BaseService implements IHotelProviderService {

	private IProviderDao providerDao;
	private IHotelDao hotelDao;
	private IEmailAddressDao emailAddressDao;
	private IPhoneNumberDao phoneNumberDao;
	private IPostalAddressDao postalAddressDao;
	private IImageDao imageDao;
	
	@Override
	public void createHotel(ProviderData providerData, long loginUserId) throws BmsException, BmsSqlException {
		TransactionStatus txStatus= null;
		try {
			txStatus = getTxManager().getTransaction(new DefaultTransactionDefinition());
			Date currDate = new Date(System.currentTimeMillis());
			
			providerData.setActive(true);
			providerData.setStatus(Constants.STATUS_EXIST);
			providerData.setCreatedBy(loginUserId);
			providerData.setCreatedOn(currDate);
			providerData.setUpdatedBy(loginUserId);
			providerData.setUpdatedOn(currDate);
			long providerId= providerDao.create(providerData);
			providerData.setId(providerId);
			
			HotelData hotelData = providerData.getHotelData();
			hotelData.setProviderId(providerId);
			hotelData.setCreatedBy(loginUserId);
			hotelData.setCreatedOn(currDate);
			hotelData.setUpdatedBy(loginUserId);
			hotelData.setUpdatedOn(currDate);
			long hotelId = hotelDao.create(hotelData);
			hotelData.setId(hotelId);
			
			EmailAddressData emailAddressData = providerData.getEmailAddressDatas().get(0);
			emailAddressData.setProviderId(providerId);
			emailAddressData.setVerified(true);
			emailAddressData.setPrimary(true);
			emailAddressData.setCreatedBy(loginUserId);
			emailAddressData.setCreatedOn(currDate);
			emailAddressData.setUpdatedBy(loginUserId);
			emailAddressData.setUpdatedOn(currDate);
			long emailAddressId = emailAddressDao.create(0, providerId, emailAddressData);
			emailAddressData.setId(emailAddressId);
			
			PhoneNumberData phoneNumberData = providerData.getPhoneNumberDatas().get(0);
			emailAddressData.setProviderId(providerId);
			phoneNumberData.setVerified(true);
			phoneNumberData.setPrimary(true);
			phoneNumberData.setStatus(Constants.STATUS_EXIST);
			phoneNumberData.setCreatedBy(loginUserId);
			phoneNumberData.setCreatedOn(currDate);
			phoneNumberData.setUpdatedBy(loginUserId);
			phoneNumberData.setUpdatedOn(currDate);
			long phoneNumberId = phoneNumberDao.create(0, providerId, phoneNumberData);
			phoneNumberData.setId(phoneNumberId);

			PostalAddressData postalAddressData = providerData.getPostalAddressDatas().get(0);
			emailAddressData.setProviderId(providerId);
			postalAddressData.setCreatedBy(loginUserId);
			postalAddressData.setCreatedOn(currDate);
			postalAddressData.setUpdatedBy(loginUserId);
			postalAddressData.setUpdatedOn(currDate);
			long postalAddressId = postalAddressDao.create(0, providerId, postalAddressData);
			postalAddressData.setId(postalAddressId);
			
			getTxManager().commit(txStatus);
		} catch (BmsSqlException e) {
			if (txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			providerData.setId(0);
			providerData.getHotelData().setId(0);
			providerData.getHotelData().setProviderId(0);
			providerData.getEmailAddressDatas().get(0).setId(0);
			providerData.getEmailAddressDatas().get(0).setProviderId(0);
			providerData.getPhoneNumberDatas().get(0).setId(0);
			providerData.getPhoneNumberDatas().get(0).setProviderId(0);
			providerData.getPostalAddressDatas().get(0).setId(0);
			providerData.getPostalAddressDatas().get(0).setProviderId(0);
			throw e;
		} catch (Exception e) {
			if (txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			providerData.setId(0);
			providerData.getHotelData().setId(0);
			providerData.getHotelData().setProviderId(0);
			providerData.getEmailAddressDatas().get(0).setId(0);
			providerData.getEmailAddressDatas().get(0).setProviderId(0);
			providerData.getPhoneNumberDatas().get(0).setId(0);
			providerData.getPhoneNumberDatas().get(0).setProviderId(0);
			providerData.getPostalAddressDatas().get(0).setId(0);
			providerData.getPostalAddressDatas().get(0).setProviderId(0);
			throw new BmsException(e);
		}
	}

	@Override
	public void updateHotel(ProviderData providerData, long loginUserId) throws BmsException, BmsSqlException {
		TransactionStatus txStatus = null;
		long existingHotelId = 0;
		long existingEmailAddressId = 0;
		long existingPhoneNumberId = 0;
		long existingPostalAddressId = 0;
		try {
			HotelData hotelData = providerData.getHotelData();
			existingHotelId = hotelData.getId();
			EmailAddressData emailAddressData = providerData.getEmailAddressDatas().get(0);
			existingEmailAddressId = emailAddressData.getId();
			PhoneNumberData phoneNumberData = providerData.getPhoneNumberDatas().get(0);
			existingPhoneNumberId = phoneNumberData.getId();
			PostalAddressData postalAddressData = providerData.getPostalAddressDatas().get(0);
			existingPostalAddressId = postalAddressData.getId();
			txStatus = getTxManager().getTransaction(new DefaultTransactionDefinition());
			Date currDate = new Date(System.currentTimeMillis());
			
			providerData.setUpdatedBy(loginUserId);
			providerData.setUpdatedOn(currDate);
			providerDao.update(providerData);
			
			if (existingHotelId == 0) {
				hotelData.setProviderId(providerData.getId());
				hotelData.setCreatedBy(loginUserId);
				hotelData.setCreatedOn(currDate);
				hotelData.setUpdatedBy(loginUserId);
				hotelData.setUpdatedOn(currDate);
				long hotelId = hotelDao.create(hotelData);
				hotelData.setId(hotelId);
			} else {
				hotelData.setUpdatedBy(loginUserId);
				hotelData.setUpdatedOn(currDate);
				hotelDao.update(hotelData);
			}
			
			if (existingEmailAddressId == 0) {
				emailAddressData = providerData.getEmailAddressDatas().get(0);
				emailAddressData.setProviderId(providerData.getId());
				emailAddressData.setVerified(true);
				emailAddressData.setPrimary(true);
				emailAddressData.setCreatedBy(loginUserId);
				emailAddressData.setCreatedOn(currDate);
				emailAddressData.setUpdatedBy(loginUserId);
				emailAddressData.setUpdatedOn(currDate);
				long emailAddressId = emailAddressDao.create(0, providerData.getId(), emailAddressData);
				emailAddressData.setId(emailAddressId);
			} else {
				emailAddressData.setUpdatedBy(loginUserId);
				emailAddressData.setUpdatedOn(currDate);
				emailAddressDao.update(emailAddressData);
			}
			
			if (existingPhoneNumberId == 0) {
				phoneNumberData = providerData.getPhoneNumberDatas().get(0);
				emailAddressData.setProviderId(providerData.getId());
				phoneNumberData.setVerified(true);
				phoneNumberData.setPrimary(true);
				phoneNumberData.setStatus(Constants.STATUS_EXIST);
				phoneNumberData.setCreatedBy(loginUserId);
				phoneNumberData.setCreatedOn(currDate);
				phoneNumberData.setUpdatedBy(loginUserId);
				phoneNumberData.setUpdatedOn(currDate);
				long phoneNumberId = phoneNumberDao.create(0, providerData.getId(), phoneNumberData);
				phoneNumberData.setId(phoneNumberId);
			} else {
				phoneNumberData.setUpdatedBy(loginUserId);
				phoneNumberData.setUpdatedOn(currDate);
				phoneNumberDao.update(phoneNumberData);
			}
			
			if (existingPostalAddressId == 0) {
				postalAddressData = providerData.getPostalAddressDatas().get(0);
				emailAddressData.setProviderId(providerData.getId());
				postalAddressData.setCreatedBy(loginUserId);
				postalAddressData.setCreatedOn(currDate);
				postalAddressData.setUpdatedBy(loginUserId);
				postalAddressData.setUpdatedOn(currDate);
				long postalAddressId = postalAddressDao.create(0, providerData.getId(), postalAddressData);
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
			if (existingHotelId == 0) {
				providerData.getHotelData().setId(0);
				providerData.getHotelData().setProviderId(0);
			}
			if (existingEmailAddressId == 0) {
				providerData.getEmailAddressDatas().get(0).setId(0);
				providerData.getEmailAddressDatas().get(0).setUserId(0);
			}
			if (existingPhoneNumberId == 0) {
				providerData.getPhoneNumberDatas().get(0).setId(0);
				providerData.getEmailAddressDatas().get(0).setUserId(0);
			}
			if (existingPostalAddressId == 0) {
				providerData.getPostalAddressDatas().get(0).setId(0);
				providerData.getEmailAddressDatas().get(0).setUserId(0);
			}
			throw e;
		} catch (Exception e) {
			if (txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			if (existingHotelId == 0) {
				providerData.getHotelData().setId(0);
				providerData.getHotelData().setProviderId(0);
			}
			if (existingEmailAddressId == 0) {
				providerData.getEmailAddressDatas().get(0).setId(0);
				providerData.getEmailAddressDatas().get(0).setUserId(0);
			}
			if (existingPhoneNumberId == 0) {
				providerData.getPhoneNumberDatas().get(0).setId(0);
				providerData.getEmailAddressDatas().get(0).setUserId(0);
			}
			if (existingPostalAddressId == 0) {
				providerData.getPostalAddressDatas().get(0).setId(0);
				providerData.getEmailAddressDatas().get(0).setUserId(0);
			}
			throw new BmsException(e);
		}
	}

	@Override
	public boolean deleteHotel(long providerId, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			ProviderData providerData = new ProviderData();
			providerData.setId(providerId);
			providerData.setUpdatedBy(loginUserId);
			providerData.setUpdatedOn(currDate);
			return providerDao.delete(providerData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<ProviderData> getAllHotelDatas() throws BmsException, BmsSqlException {	
		try {
			return providerDao.getProviderDatasByProviderTypeId(SetupConstants.PROVIDER_TYPE_HOTEL);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ProviderData getHotelDetailInfo(long providerId) throws BmsException, BmsSqlException {
		try {
			ProviderData providerData = providerDao.getProviderDataById(providerId);
			if (providerData != null) {
				HotelData hotelData = hotelDao.getHotelDataByProviderId(providerId);
				if (hotelData != null) {
					providerData.setHotelData(hotelData);
				} else {
					providerData.setHotelData(new HotelData());
				}
				
				List<EmailAddressData> emailAddressDatas = emailAddressDao.getAllEmailAddressesByProviderId(providerId);
				if (emailAddressDatas != null && emailAddressDatas.size() > 0) {
					providerData.setEmailAddressDatas(emailAddressDatas);
				} else {
					providerData.setEmailAddressDatas(new ArrayList<>());
				}
				
				List<PhoneNumberData> phoneNumberDatas = phoneNumberDao.getAllPhoneNumbersByProviderId(providerId);
				if(phoneNumberDatas != null && phoneNumberDatas.size() > 0) {
					providerData.setPhoneNumberDatas(phoneNumberDatas);
				} else {
					providerData.setPhoneNumberDatas(new ArrayList<>());
				}
				
				List<PostalAddressData> postalAddressDatas = postalAddressDao.getAllPostalAddressesByProviderId(providerId);
				if(postalAddressDatas != null && postalAddressDatas.size() > 0) {
					providerData.setPostalAddressDatas(postalAddressDatas);
				} else {
					providerData.setPostalAddressDatas(new ArrayList<>());
				}
			}
			return providerData;
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IProviderDao getProviderDao() {
		return providerDao;
	}

	@Autowired
	@Qualifier("providerDao")
	@Override
	public void setProviderDao(IProviderDao providerDao) {
		this.providerDao = providerDao;
	}

	@Override
	public IHotelDao getHotelDao() {
		return hotelDao;
	}

	@Autowired
	@Qualifier("hotelDao")
	@Override
	public void setHotelDao(IHotelDao hotelDao) {
		this.hotelDao = hotelDao;
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
