package com.bms.service.soa.provider;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IPointOfInterestDao;
import com.bms.service.data.provider.PointOfInterestData;

public interface IPointOfInterestService {
	
	long create(PointOfInterestData pointOfInterestData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean update(PointOfInterestData pointOfInterestData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean delete(long pointOfInterestId) throws BmsException, BmsSqlException;
	
	PointOfInterestData getPointOfInterestById(long pointOfInterestId) throws BmsException, BmsSqlException;
	
	List<PointOfInterestData> getPointOfInterestsByProviderTypeId(long providerTypeId) throws BmsException, BmsSqlException;
	
	List<PointOfInterestData> getAllPointOfInterests() throws BmsException, BmsSqlException;
	
	boolean isAvailable(long id, String name, long providerTypeId) throws BmsException, BmsSqlException;
	
	IPointOfInterestDao getPointOfInterestDao();

	void setPointOfInterestDao(IPointOfInterestDao pointOfInterestDao);
}
