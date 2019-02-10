package com.bms.service.soa.provider;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IAmenityChargeDao;
import com.bms.service.data.provider.AmenityChargeData;

public interface IAmenityChargeService {

	long create(AmenityChargeData amenityChargeData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean update(AmenityChargeData amenityChargeData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean delete(long amenityChargeId) throws BmsSqlException, BmsException;
	
	AmenityChargeData getAmenityChargeById(long amenityChargeId) throws BmsSqlException, BmsException;
	
	List<AmenityChargeData> getAllAmenityChargesByProviderId(long providerId) throws BmsSqlException, BmsException;

	boolean isAvailable(long id, String amenityId, long providerId) throws BmsSqlException, BmsException;
	
	IAmenityChargeDao getAmenityChargeDao();

	void setAmenityChargeDao(IAmenityChargeDao amenityChargeDao);
	
}
