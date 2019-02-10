package com.bms.service.soa.room;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemDao;
import com.bms.service.data.room.ItemData;

public interface IItemService {

	long create(ItemData itemData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean update(ItemData itemData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean delete(long itemId, long loginUserId) throws BmsSqlException, BmsException;
	
	ItemData getItemById(long itemId) throws BmsSqlException, BmsException;
	
	List<ItemData> getAllItemsByItemCategoryId(long itemCategoryId) throws BmsSqlException, BmsException;
	
	List<ItemData> getAllItemsByProviderId(long providerId) throws BmsSqlException, BmsException;
	
	boolean isAvailable(long itemId, String itemNo, long providerId) throws BmsSqlException, BmsException;

	IItemDao getItemDao();

	void setItemDao(IItemDao itemDao);
	
}
