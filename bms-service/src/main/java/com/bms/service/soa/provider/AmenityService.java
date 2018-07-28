package com.bms.service.soa.provider;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IAmenityDao;
import com.bms.service.data.provider.AmenityData;
import com.bms.service.soa.BaseService;

@Service("amenityService")
public class AmenityService extends BaseService implements IAmenityService {

	private IAmenityDao amenityDao;
	
	@Override
	public long create(AmenityData amenityData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			amenityData.setCreatedBy(loginUserId);
			amenityData.setCreatedOn(currDate);
			amenityData.setUpdatedBy(loginUserId);
			amenityData.setUpdatedOn(currDate);
			return amenityDao.create(amenityData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(AmenityData amenityData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			amenityData.setUpdatedBy(loginUserId);
			amenityData.setUpdatedOn(currDate);
			return amenityDao.update(amenityData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long amenityId) throws BmsException, BmsSqlException {
		return amenityDao.delete(amenityId);
	}

	@Override
	public AmenityData getAmenityById(long amenityId) throws BmsException, BmsSqlException {
		return amenityDao.getAmenityById(amenityId);
	}
	
	@Override
	public List<AmenityData> getAmenitiesByProviderTypeId(long providerTypeId) throws BmsException, BmsSqlException {
		return amenityDao.getAmenitiesByProviderTypeId(providerTypeId);
	}

	@Override
	public List<AmenityData> getAllAmenities() throws BmsException, BmsSqlException {
		return amenityDao.getAllAmenities();
	}

	@Override
	public IAmenityDao getAmenityDao() {
		return amenityDao;
	}

	@Autowired
	@Qualifier("amenityDao")
	@Override
	public void setAmenityDao(IAmenityDao amenityDao) {
		this.amenityDao = amenityDao;
	}

}
