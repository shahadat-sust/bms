package com.bms.service.soa.provider;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IProviderAdminDao;
import com.bms.service.dao.provider.IProviderDao;
import com.bms.service.data.provider.ProviderAdminData;

public interface IProviderAdminService {

	boolean setProviderForAdmin(long userId, long providerId, boolean isAssign, long loginUserId) throws BmsSqlException, BmsException;

	boolean setDefaultProviderForAdmin(long userId, long providerId, long loginUserId) throws BmsSqlException, BmsException;

	ProviderAdminData getDefaultProviderByUserId(long userId) throws BmsSqlException, BmsException;
	
	public List<ProviderAdminData> getAssignedProviders(long userId, long userGroupId, long userRoleId) throws BmsSqlException, BmsException;
	
	public List<ProviderAdminData> getAssignableProviders(long userId, long loginUserId, long loginUserGroupId, long loginUserRoleId) throws BmsSqlException, BmsException;

	public IProviderAdminDao getProviderAdminDao();

	public void setProviderAdminDao(IProviderAdminDao providerAdminDao);
	
	public IProviderDao getProviderDao();

	public void setProviderDao(IProviderDao providerDao);
	
}
