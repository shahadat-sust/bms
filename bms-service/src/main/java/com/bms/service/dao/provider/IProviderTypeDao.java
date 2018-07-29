package com.bms.service.dao.provider;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.provider.ProviderTypeData;

public interface IProviderTypeDao {
	
	boolean create(ProviderTypeData providerTypeData) throws BmsSqlException;
	
	boolean update(ProviderTypeData providerTypeData) throws BmsSqlException;
	
	boolean delete(long providerTypeId) throws BmsSqlException;
	
	ProviderTypeData getProviderTypeById(long providerTypeId) throws BmsSqlException;
	
	List<ProviderTypeData> getAllProviderTypes() throws BmsSqlException;
	
}
