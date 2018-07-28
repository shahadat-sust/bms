package com.bms.service.soa;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.ICityDao;
import com.bms.service.data.CityData;

@Service("cityService")
public class CityService extends BaseService implements ICityService {

	private ICityDao cityDao;
	
	@Override
	public long create(CityData cityData, long loginUserId) throws BmsException, BmsSqlException {
		Date currDate = new Date(System.currentTimeMillis());
		cityData.setCreatedBy(loginUserId);
		cityData.setCreatedOn(currDate);
		cityData.setUpdatedBy(loginUserId);
		cityData.setUpdatedOn(currDate);
		return cityDao.create(cityData);
	}

	@Override
	public boolean update(CityData cityData, long loginUserId) throws BmsException, BmsSqlException {
		Date currDate = new Date(System.currentTimeMillis());
		cityData.setUpdatedBy(loginUserId);
		cityData.setUpdatedOn(currDate);
		return cityDao.update(cityData);
	}

	@Override
	public boolean delete(long cityId) throws BmsException, BmsSqlException {
		return cityDao.delete(cityId);
	}

	@Override
	public CityData getCityById(long cityId) throws BmsException, BmsSqlException {
		return cityDao.getCityById(cityId);
	}
	
	@Override
	public List<CityData> getCitiesByCountryId(long countryId) throws BmsException, BmsSqlException {
		return cityDao.getCitiesByCountryId(countryId);
	}

	@Override
	public List<CityData> getAllCities() throws BmsException, BmsSqlException {
		return cityDao.getAllCities();
	}

	@Override
	public ICityDao getCityDao() {
		return cityDao;
	}

	@Autowired
	@Qualifier("cityDao")
	@Override
	public void setCityDao(ICityDao cityDao) {
		this.cityDao = cityDao;
	}

}
