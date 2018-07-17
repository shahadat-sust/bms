package com.bms.service.soa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.ICountryDao;
import com.bms.service.data.CountryData;

@Service("countryService")
public class CountryService extends BaseService implements ICountryService {

	private ICountryDao countryDao;
	
	@Override
	public long create(CountryData countryData) throws BmsException, BmsSqlException {
		return countryDao.create(countryData);
	}

	@Override
	public boolean update(CountryData countryData) throws BmsException, BmsSqlException {
		return countryDao.update(countryData);
	}

	@Override
	public boolean delete(long countryId) throws BmsException, BmsSqlException {
		return countryDao.delete(countryId);
	}

	@Override
	public CountryData getCountryById(long countryId) throws BmsException, BmsSqlException {
		return countryDao.getCountryById(countryId);
	}

	@Override
	public List<CountryData> getAllCountries() throws BmsException, BmsSqlException {
		return countryDao.getAllCountries();
	}

	@Override
	public ICountryDao getCountryDao() {
		return countryDao;
	}

	@Autowired
	@Qualifier("countryDao")
	@Override
	public void setCountryDao(ICountryDao countryDao) {
		this.countryDao = countryDao;
	}

}
