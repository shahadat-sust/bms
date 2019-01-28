package com.bms.service.soa.provider;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.ProviderAdminDao;
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.soa.BaseService;

@Service("providerAdminService")
public class ProviderAdminService extends BaseService implements IProviderAdminService {

	private ProviderAdminDao providerAdminDao;
	
	@Override
	public boolean setProviderForAdmin(long userId, long providerId, long loginUserId, boolean isAssign) throws BmsSqlException, BmsException {
		try {
			if (providerAdminDao.isProviderAssignedForAdmin(userId, providerId)) {
				if (isAssign) {
					return true;
				} else {
					return providerAdminDao.deleteProviderAdmin(userId, providerId);
				}
			} else {
				if (isAssign) {
					Date currDate = new Date(System.currentTimeMillis());
					ProviderAdminData providerAdminData = new ProviderAdminData();
					providerAdminData.setProviderId(providerId);
					providerAdminData.setUserId(userId);
					providerAdminData.setCreatedBy(loginUserId);
					providerAdminData.setCreatedOn(currDate);
					providerAdminData.setUpdatedBy(loginUserId);
					providerAdminData.setUpdatedOn(currDate);
					return providerAdminDao.createProviderAdmin(providerAdminData) > 0;
				} else {
					return true;
				}
			}
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<ProviderAdminData> getAllProviderAdminDatasByUserId(long userId) throws BmsSqlException, BmsException {
		try {
			return providerAdminDao.getAllProviderAdminDatasByUserId(userId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean setDefaultProviderForAdmin(long userId, long providerId, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			ProviderAdminData providerAdminData = new ProviderAdminData();
			providerAdminData.setProviderId(providerId);
			providerAdminData.setUserId(userId);
			providerAdminData.setCreatedBy(loginUserId);
			providerAdminData.setCreatedOn(currDate);
			providerAdminData.setUpdatedBy(loginUserId);
			providerAdminData.setUpdatedOn(currDate);
			
			if (providerId == 0) {
				return providerAdminDao.deleteDefaultProvider(userId);
			} else if (providerAdminDao.isDefaultProviderAssignedForAdmin(userId, providerId)) {
				return providerAdminDao.updateDefaultProvider(providerAdminData);
			} else {
				return providerAdminDao.createDefaultProvider(providerAdminData) > 0;
			}
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ProviderAdminData getDefaultProviderByUserId(long userId) throws BmsSqlException, BmsException {
		try {
			return providerAdminDao.getDefaultProviderByUserId(userId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ProviderAdminDao getProviderAdminDao() {
		return providerAdminDao;
	}

	@Autowired
	@Qualifier("providerAdminDao")
	@Override
	public void setProviderAdminDao(ProviderAdminDao providerAdminDao) {
		this.providerAdminDao = providerAdminDao;
	}

	

}
