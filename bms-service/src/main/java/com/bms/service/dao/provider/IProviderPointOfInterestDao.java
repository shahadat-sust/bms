package com.bms.service.dao.provider;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderPointOfInterestData;

public interface IProviderPointOfInterestDao {

	long create(ProviderPointOfInterestData providerPointOfInterestData) throws BmsSqlException;

	boolean delete(long providerPointOfInterestId) throws BmsSqlException;
	
	ProviderPointOfInterestData getProviderPointOfInterestById(long providerPointOfInterestId) throws BmsSqlException;
	
	List<ProviderPointOfInterestData> getAllProviderPointOfInterestsByProviderId(long providerId) throws BmsSqlException;

	boolean isAvailable(long id, String pointOfInterestId, long providerId) throws BmsSqlException;
	
}
