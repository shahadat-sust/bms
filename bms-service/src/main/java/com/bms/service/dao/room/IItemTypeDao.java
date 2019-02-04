package com.bms.service.dao.room;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.room.ItemTypeData;

public interface IItemTypeDao {

	long create(ItemTypeData itemTypeData) throws BmsSqlException;
	
	boolean update(ItemTypeData itemTypeData) throws BmsSqlException;
	
	boolean delete(ItemTypeData itemTypeData) throws BmsSqlException;
	
	ItemTypeData getItemTypeById(long itemTypeId) throws BmsSqlException;
	
	List<ItemTypeData> getAllItemTypesByProviderId(long providerId) throws BmsSqlException;
	
	boolean isAvailable(long id, String name, long providerId) throws BmsSqlException;
	
}
