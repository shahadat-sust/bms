package com.bms.service.soa.provider;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.ProviderAdminDao;
import com.bms.service.data.provider.ProviderAdminData;

public interface IProviderAdminService {

	boolean setProviderForAdmin(long userId, long providerId, long loginUserId, boolean isAssign) throws BmsSqlException, BmsException;

	List<ProviderAdminData> getAllProviderAdminDatasByUserId(long userId) throws BmsSqlException, BmsException;
	
	boolean setDefaultProviderForAdmin(long userId, long providerId, long loginUserId) throws BmsSqlException, BmsException;

	ProviderAdminData getDefaultProviderByUserId(long userId) throws BmsSqlException, BmsException;
	
	public ProviderAdminDao getProviderAdminDao();

	public void setProviderAdminDao(ProviderAdminDao providerAdminDao);
	
}
