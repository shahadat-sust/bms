package com.bms.service.dao.provider;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.AmenityData;

public interface IAmenityDao {
	
	long create(AmenityData amenityData) throws BmsSqlException;
	
	boolean update(AmenityData amenityData) throws BmsSqlException;
	
	boolean delete(long amenityId) throws BmsSqlException;
	
	AmenityData getAmenityById(long amenityId) throws BmsSqlException;
	
	List<AmenityData> getAmenitiesByProviderTypeId(long providerTypeId) throws BmsSqlException;
	
	List<AmenityData> getAllAmenities() throws BmsSqlException;
	
}
