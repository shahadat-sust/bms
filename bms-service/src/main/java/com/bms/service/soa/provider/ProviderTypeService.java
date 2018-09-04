package com.bms.service.soa.provider;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IProviderTypeDao;
import com.bms.service.data.provider.ProviderTypeData;
import com.bms.service.soa.BaseService;

@Service("providerTypeService")
public class ProviderTypeService extends BaseService implements IProviderTypeService {

	private IProviderTypeDao providerTypeDao;
	
	@Override
	public boolean create(ProviderTypeData providerTypeData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			providerTypeData.setCreatedBy(loginUserId);
			providerTypeData.setCreatedOn(currDate);
			providerTypeData.setUpdatedBy(loginUserId);
			providerTypeData.setUpdatedOn(currDate);
			return providerTypeDao.create(providerTypeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(ProviderTypeData providerTypeData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			providerTypeData.setUpdatedBy(loginUserId);
			providerTypeData.setUpdatedOn(currDate);
			return providerTypeDao.update(providerTypeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public boolean delete(long providerTypeId) throws BmsException, BmsSqlException {
		return providerTypeDao.delete(providerTypeId);
	}

	@Override
	public ProviderTypeData getProviderTypeById(long providerTypeId) throws BmsException, BmsSqlException {
		return providerTypeDao.getProviderTypeById(providerTypeId);
	}

	@Override
	public List<ProviderTypeData> getAllProviderTypes() throws BmsException, BmsSqlException {
		return providerTypeDao.getAllProviderTypes();
	}

	@Override
	public boolean isAvailable(long id, String name) throws BmsSqlException {
		return providerTypeDao.isAvailable(id, name);
	}

	@Override
	public IProviderTypeDao getProviderTypeDao() {
		return providerTypeDao;
	}

	@Autowired
	@Qualifier("providerTypeDao")
	@Override
	public void setProviderTypeDao(IProviderTypeDao providerTypeDao) {
		this.providerTypeDao = providerTypeDao;
	}

}
