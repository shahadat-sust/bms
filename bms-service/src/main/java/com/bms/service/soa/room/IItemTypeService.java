package com.bms.service.soa.room;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemTypeDao;
import com.bms.service.data.room.ItemTypeData;

public interface IItemTypeService {

	long create(ItemTypeData itemTypeData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean update(ItemTypeData itemTypeData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean delete(long itemTypeId, long loginUserId) throws BmsSqlException, BmsException;
	
	ItemTypeData getItemTypeById(long itemTypeId) throws BmsSqlException, BmsException;
	
	List<ItemTypeData> getAllItemTypesByProviderId(long providerId) throws BmsSqlException, BmsException;
	
	boolean isAvailable(long itemTypeId, String name, long providerId) throws BmsSqlException, BmsException;
	
	IItemTypeDao getItemTypeDao();

	void setItemTypeDao(IItemTypeDao itemTypeDao);
	
}
