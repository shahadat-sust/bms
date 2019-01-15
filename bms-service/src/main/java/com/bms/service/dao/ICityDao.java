package com.bms.service.dao;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.CityData;

public interface ICityDao {
	
	long create(CityData cityData) throws BmsSqlException;
	
	boolean update(CityData cityData) throws BmsSqlException;
	
	boolean delete(long cityId) throws BmsSqlException;
	
	CityData getCityById(long cityId) throws BmsSqlException;
	
	List<CityData> getCitiesByCountryId(long countryId) throws BmsSqlException;
	
	List<CityData> getCitiesByStateId(long stateId) throws BmsSqlException;
	
	List<CityData> getAllCities() throws BmsSqlException;
	
}
