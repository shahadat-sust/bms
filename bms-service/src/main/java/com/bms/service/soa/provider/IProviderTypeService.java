package com.bms.service.soa.provider;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IProviderTypeDao;
import com.bms.service.data.provider.ProviderTypeData;
public interface IProviderTypeService {
	
	boolean create(ProviderTypeData providerTypeData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean update(ProviderTypeData providerTypeData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean delete(long providerTypeId) throws BmsException, BmsSqlException;
	
	ProviderTypeData getProviderTypeById(long providerTypeId) throws BmsException, BmsSqlException;
	
	List<ProviderTypeData> getAllProviderTypes() throws BmsException, BmsSqlException;
	
	boolean isAvailable(long id, String name) throws BmsException, BmsSqlException;
	
	IProviderTypeDao getProviderTypeDao();

	void setProviderTypeDao(IProviderTypeDao providerTypeDao);
}
