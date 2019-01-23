package com.bms.service.soa.provider;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.IEmailAddressDao;
import com.bms.service.dao.IImageDao;
import com.bms.service.dao.IPhoneNumberDao;
import com.bms.service.dao.IPostalAddressDao;
import com.bms.service.dao.provider.IHotelDao;
import com.bms.service.dao.provider.IProviderDao;
import com.bms.service.data.provider.ProviderData;

public interface IHotelProviderService {

	public void createHotel(ProviderData providerData, long loginUserId) throws BmsException, BmsSqlException;
	
	public void updateHotel(ProviderData providerData, long loginUserId) throws BmsException, BmsSqlException;
	
	public boolean deleteHotel(long providerId, long loginUserId) throws BmsException, BmsSqlException;
	
	public List<ProviderData> getAllHotelDatas() throws BmsException, BmsSqlException;
	
	public ProviderData getHotelDetailInfo(long providerId) throws BmsException, BmsSqlException;
	
	public List<ProviderData> getSearchHotel(String title, int starRating, long countryId, long cityId) throws BmsException, BmsSqlException;
	
	public IProviderDao getProviderDao();

	public void setProviderDao(IProviderDao providerDao);

	public IHotelDao getHotelDao();

	public void setHotelDao(IHotelDao hotelDao);

	public IEmailAddressDao getEmailAddressDao();

	public void setEmailAddressDao(IEmailAddressDao emailAddressDao);

	public IPhoneNumberDao getPhoneNumberDao();

	public void setPhoneNumberDao(IPhoneNumberDao phoneNumberDao);

	public IPostalAddressDao getPostalAddressDao();

	public void setPostalAddressDao(IPostalAddressDao postalAddressDao);

	public IImageDao getImageDao();

	public void setImageDao(IImageDao imageDao);
	
}
