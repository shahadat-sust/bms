package com.bms.service.soa;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.ICountryDao;
import com.bms.service.data.CountryData;

public interface ICountryService {
	
	long create(CountryData countryData) throws BmsException, BmsSqlException;
	
	boolean update(CountryData countryData) throws BmsException, BmsSqlException;
	
	boolean delete(long countryId) throws BmsException, BmsSqlException;
	
	CountryData getCountryById(long countryId) throws BmsException, BmsSqlException;
	
	List<CountryData> getAllCountries() throws BmsException, BmsSqlException;
	
	ICountryDao getCountryDao();

	void setCountryDao(ICountryDao countryDao);
}
