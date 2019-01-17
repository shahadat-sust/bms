package com.bms.service.dao.provider;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderAdminData;

public interface IProviderAdminDao {

	long createProviderAdmin(ProviderAdminData providerAdminData) throws BmsSqlException;

	boolean deleteProviderAdmin(long providerId, long userId) throws BmsSqlException;
	
	boolean isProviderAssignedForAdmin(long providerId, long userId) throws BmsSqlException;
	
	List<ProviderAdminData> getAllProviderAdminDatasByUserId(long userId) throws BmsSqlException;
	
	long createDefaultProvider(ProviderAdminData providerAdminData) throws BmsSqlException;
	
	boolean updateDefaultProvider(ProviderAdminData providerAdminData) throws BmsSqlException;
	
	boolean isDefaultProviderAssignedForAdmin(long providerId, long userId) throws BmsSqlException;
	
	ProviderAdminData getDefaultProviderByUserId(long userId) throws BmsSqlException;
}
