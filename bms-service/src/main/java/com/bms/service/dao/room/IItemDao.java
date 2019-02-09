package com.bms.service.dao.room;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.room.ItemData;

public interface IItemDao {

	long create(ItemData itemData) throws BmsSqlException;
	
	boolean update(ItemData itemData) throws BmsSqlException;
	
	boolean delete(ItemData itemData) throws BmsSqlException;
	
	ItemData getItemById(long itemId) throws BmsSqlException;
	
	List<ItemData> getAllItemsByProviderId(long providerId) throws BmsSqlException;
	
	boolean isAvailable(long itemId, String itemNo, long providerId) throws BmsSqlException;
	
}
