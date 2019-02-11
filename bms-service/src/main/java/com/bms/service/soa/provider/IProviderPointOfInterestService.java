package com.bms.service.soa.provider;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IProviderPointOfInterestDao;
import com.bms.service.data.provider.ProviderPointOfInterestData;

public interface IProviderPointOfInterestService {

	long create(ProviderPointOfInterestData providerPointOfInterestData, long loginUserId) throws BmsSqlException, BmsException;

	boolean delete(long providerPointOfInterestId) throws BmsSqlException, BmsException;
	
	ProviderPointOfInterestData getProviderPointOfInterestById(long providerPointOfInterestId) throws BmsSqlException, BmsException;
	
	List<ProviderPointOfInterestData> getAllProviderPointOfInterestsByProviderId(long providerId) throws BmsSqlException, BmsException;

	boolean isAvailable(long pointOfInterestId, long providerId) throws BmsSqlException, BmsException;
	
	public IProviderPointOfInterestDao getProviderPointOfInterestDao();

	public void setProviderPointOfInterestDao(IProviderPointOfInterestDao providerPointOfInterestDao);
	
}
