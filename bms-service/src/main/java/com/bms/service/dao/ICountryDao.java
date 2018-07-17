package com.bms.service.dao;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.CountryData;

public interface ICountryDao {
	
	long create(CountryData countryData) throws BmsSqlException;
	
	boolean update(CountryData countryData) throws BmsSqlException;
	
	boolean delete(long countryId) throws BmsSqlException;
	
	CountryData getCountryById(long countryId) throws BmsSqlException;
	
	List<CountryData> getAllCountries() throws BmsSqlException;
	
}
