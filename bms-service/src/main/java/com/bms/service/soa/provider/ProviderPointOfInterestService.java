package com.bms.service.soa.provider;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IProviderPointOfInterestDao;
import com.bms.service.data.provider.ProviderPointOfInterestData;
import com.bms.service.soa.BaseService;

@Service("providerPointOfInterestService")
public class ProviderPointOfInterestService extends BaseService implements IProviderPointOfInterestService {

	private IProviderPointOfInterestDao providerPointOfInterestDao;
	
	@Override
	public long create(ProviderPointOfInterestData providerPointOfInterestData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			providerPointOfInterestData.setCreatedBy(loginUserId);
			providerPointOfInterestData.setCreatedOn(currDate);
			providerPointOfInterestData.setUpdatedBy(loginUserId);
			providerPointOfInterestData.setUpdatedOn(currDate);
			return providerPointOfInterestDao.create(providerPointOfInterestData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long providerPointOfInterestId) throws BmsSqlException, BmsException {
		try {
			return providerPointOfInterestDao.delete(providerPointOfInterestId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ProviderPointOfInterestData getProviderPointOfInterestById(long providerPointOfInterestId) throws BmsSqlException, BmsException {
		try {
			return providerPointOfInterestDao.getProviderPointOfInterestById(providerPointOfInterestId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<ProviderPointOfInterestData> getAllProviderPointOfInterestsByProviderId(long providerId) throws BmsSqlException, BmsException {
		try {
			return providerPointOfInterestDao.getAllProviderPointOfInterestsByProviderId(providerId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long pointOfInterestId, long providerId) throws BmsSqlException, BmsException {
		try {
			return providerPointOfInterestDao.isAvailable(pointOfInterestId, providerId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IProviderPointOfInterestDao getProviderPointOfInterestDao() {
		return providerPointOfInterestDao;
	}

	@Autowired
	@Qualifier("providerPointOfInterestDao")
	@Override
	public void setProviderPointOfInterestDao(IProviderPointOfInterestDao providerPointOfInterestDao) {
		this.providerPointOfInterestDao = providerPointOfInterestDao;
	}
	
}
