package com.bms.service.soa;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.ICountryDao;
import com.bms.service.data.CountryData;

public interface ICountryService {
	
	long create(CountryData countryData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean update(CountryData countryData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean delete(long countryId) throws BmsException, BmsSqlException;
	
	CountryData getCountryById(long countryId) throws BmsException, BmsSqlException;
	
	List<CountryData> getAllCountries() throws BmsException, BmsSqlException;
	
	boolean isAvailable(long id, String name) throws BmsSqlException;
	
	ICountryDao getCountryDao();

	void setCountryDao(ICountryDao countryDao);
}
