package com.bms.service.soa.provider;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IAmenityDao;
import com.bms.service.data.provider.AmenityData;

public interface IAmenityService {
	
	long create(AmenityData amenityData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean update(AmenityData amenityData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean delete(long amenityId) throws BmsException, BmsSqlException;
	
	AmenityData getAmenityById(long amenityId) throws BmsException, BmsSqlException;
	
	List<AmenityData> getAmenitiesByProviderTypeId(long providerTypeId) throws BmsException, BmsSqlException;
	
	List<AmenityData> getAllAmenities() throws BmsException, BmsSqlException;
	
	boolean isAvailable(long id, String name, long providerTypeId, int type) throws BmsException, BmsSqlException;
	
	IAmenityDao getAmenityDao();

	void setAmenityDao(IAmenityDao amenityDao);
}
