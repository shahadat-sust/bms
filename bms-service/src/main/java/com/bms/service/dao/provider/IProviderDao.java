package com.bms.service.dao.provider;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderData;

public interface IProviderDao {

	long create(ProviderData providerData) throws BmsSqlException;
	
	boolean update(ProviderData providerData) throws BmsSqlException;
	
	boolean delete(ProviderData providerData) throws BmsSqlException;
	
	ProviderData getProviderDataById(long providerId) throws BmsSqlException;
	
	List<ProviderData> getProviderDatasByProviderTypeId(long providerTypeId) throws BmsSqlException;
	
	List<ProviderData> getAllProviderDatas() throws BmsSqlException;
	
	List<ProviderData> getSearchHotel(String title, int starRating, long countryId, long cityId) throws BmsSqlException;
	
}
