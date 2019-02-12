package com.bms.service.soa.provider;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IAmenityChargeDao;
import com.bms.service.data.provider.AmenityChargeData;
import com.bms.service.soa.BaseService;

@Service("amenityChargeService")
public class AmenityChargeService extends BaseService implements IAmenityChargeService {

	private IAmenityChargeDao amenityChargeDao; 
	
	@Override
	public long create(AmenityChargeData amenityChargeData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			amenityChargeData.setCreatedBy(loginUserId);
			amenityChargeData.setCreatedOn(currDate);
			amenityChargeData.setUpdatedBy(loginUserId);
			amenityChargeData.setUpdatedOn(currDate);
			return amenityChargeDao.create(amenityChargeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(AmenityChargeData amenityChargeData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			amenityChargeData.setUpdatedBy(loginUserId);
			amenityChargeData.setUpdatedOn(currDate);
			return amenityChargeDao.update(amenityChargeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long amenityChargeId) throws BmsSqlException, BmsException {
		try {
			return amenityChargeDao.delete(amenityChargeId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public AmenityChargeData getAmenityChargeById(long amenityChargeId) throws BmsSqlException, BmsException {
		try {
			return amenityChargeDao.getAmenityChargeById(amenityChargeId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<AmenityChargeData> getAllAmenityChargesByProviderId(long providerId) throws BmsSqlException, BmsException {
		try {
			return amenityChargeDao.getAllAmenityChargesByProviderId(providerId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, long amenityId, long providerId) throws BmsSqlException, BmsException {
		try {
			return amenityChargeDao.isAvailable(id, amenityId, providerId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IAmenityChargeDao getAmenityChargeDao() {
		return amenityChargeDao;
	}

	@Autowired
	@Qualifier("amenityChargeDao")
	@Override
	public void setAmenityChargeDao(IAmenityChargeDao amenityChargeDao) {
		this.amenityChargeDao = amenityChargeDao;
	}

}
