package com.bms.service.soa;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.ICityDao;
import com.bms.service.data.CityData;

public interface ICityService {
	
	long create(CityData cityData) throws BmsException, BmsSqlException;
	
	boolean update(CityData cityData) throws BmsException, BmsSqlException;
	
	boolean delete(long cityId) throws BmsException, BmsSqlException;
	
	CityData getCityById(long cityId) throws BmsException, BmsSqlException;
	
	List<CityData> getAllCities() throws BmsException, BmsSqlException;
	
	ICityDao getCityDao();

	void setCityDao(ICityDao cityDao);
}
