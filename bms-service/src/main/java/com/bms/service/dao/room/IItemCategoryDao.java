package com.bms.service.dao.room;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.room.ItemCategoryData;

public interface IItemCategoryDao {

	long create(ItemCategoryData itemCategoryData) throws BmsSqlException;
	
	boolean update(ItemCategoryData itemCategoryData) throws BmsSqlException;
	
	boolean delete(ItemCategoryData itemCategoryData) throws BmsSqlException;
	
	ItemCategoryData getItemCategoryById(long itemCategoryId) throws BmsSqlException;
	
	List<ItemCategoryData> getAllItemCategoriesByProviderId(long providerId) throws BmsSqlException;
	
	boolean isAvailable(long itemCategoryId, String name, long itemTypeId) throws BmsSqlException;
	
}
