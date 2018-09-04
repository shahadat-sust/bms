package com.bms.service.dao.provider;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.PointOfInterestData;

public interface IPointOfInterestDao {
	
	long create(PointOfInterestData pointOfInterestData) throws BmsSqlException;
	
	boolean update(PointOfInterestData pointOfInterestData) throws BmsSqlException;
	
	boolean delete(long pointOfInterestId) throws BmsSqlException;
	
	PointOfInterestData getPointOfInterestById(long pointOfInterestId) throws BmsSqlException;
	
	List<PointOfInterestData> getPointOfInterestsByProviderTypeId(long providerTypeId) throws BmsSqlException;
	
	List<PointOfInterestData> getAllPointOfInterests() throws BmsSqlException;
	
	boolean isAvailable(long id, String name, long providerTypeId) throws BmsSqlException;
	
}
