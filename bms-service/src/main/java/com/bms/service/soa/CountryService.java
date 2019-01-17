package com.bms.service.soa;

import java.util.Date;
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
	public long create(CountryData countryData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			countryData.setCreatedBy(loginUserId);
			countryData.setCreatedOn(currDate);
			countryData.setUpdatedBy(loginUserId);
			countryData.setUpdatedOn(currDate);
			return countryDao.create(countryData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(CountryData countryData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			countryData.setUpdatedBy(loginUserId);
			countryData.setUpdatedOn(currDate);
			return countryDao.update(countryData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long countryId) throws BmsException, BmsSqlException {
		try {
			return countryDao.delete(countryId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public CountryData getCountryById(long countryId) throws BmsException, BmsSqlException {
		try {
			return countryDao.getCountryById(countryId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<CountryData> getAllCountries() throws BmsException, BmsSqlException {
		try {
			return countryDao.getAllCountries();
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public boolean isAvailable(long id, String name) throws BmsException, BmsSqlException {
		try {
			return countryDao.isAvailable(id, name);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
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
