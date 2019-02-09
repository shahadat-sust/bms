package com.bms.service.soa.room;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemCategoryDao;
import com.bms.service.data.room.ItemCategoryData;

public interface IItemCategoryService {

	long create(ItemCategoryData itemCategoryData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean update(ItemCategoryData itemCategoryData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean delete(long itemCategoryId, long loginUserId) throws BmsSqlException, BmsException;
	
	ItemCategoryData getItemCategoryById(long itemCategoryId) throws BmsSqlException, BmsException;
	
	List<ItemCategoryData> getAllItemCategoriesByProviderId(long providerId) throws BmsSqlException, BmsException;
	
	List<ItemCategoryData> getAllItemCategoriesByItemTypeId(long itemTypeId) throws BmsSqlException, BmsException;
	
	boolean isAvailable(long itemCategoryId, String name, long itemTypeId) throws BmsSqlException, BmsException;
	
	IItemCategoryDao getItemCategoryDao();

	void setItemCategoryDao(IItemCategoryDao itemCategoryDao);
	
}
