package com.bms.service.dao.room;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.room.ItemCategoryAmenityData;

public interface IItemCategoryAmenityDao {

	long create(ItemCategoryAmenityData itemCategoryAmenityData) throws BmsSqlException;
	
	boolean update(ItemCategoryAmenityData itemCategoryAmenityData) throws BmsSqlException;
	
	boolean delete(long itemCategoryAmenityId) throws BmsSqlException;
	
	ItemCategoryAmenityData getItemCategoryAmenityById(long itemCategoryAmenityId) throws BmsSqlException;
	
	List<ItemCategoryAmenityData> getAllItemCategoryAmenityByItemCategoryId(long itemCategoryId) throws BmsSqlException;

	boolean isAvailable(long id, String amenityId, long itemCategoryId) throws BmsSqlException;
	
}
