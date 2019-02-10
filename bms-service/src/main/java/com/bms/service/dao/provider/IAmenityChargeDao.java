package com.bms.service.dao.provider;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.AmenityChargeData;

public interface IAmenityChargeDao {

	long create(AmenityChargeData amenityChargeData) throws BmsSqlException;
	
	boolean update(AmenityChargeData amenityChargeData) throws BmsSqlException;
	
	boolean delete(long amenityChargeId) throws BmsSqlException;
	
	AmenityChargeData getAmenityChargeById(long amenityChargeId) throws BmsSqlException;
	
	List<AmenityChargeData> getAllAmenityChargesByProviderId(long providerId) throws BmsSqlException;

	boolean isAvailable(long id, String amenityId, long providerId) throws BmsSqlException;
	
}
