package com.bms.service.dao.room;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.room.ItemCategoryAmenityData;

public interface IItemCategoryAmenityDao {

	long create(ItemCategoryAmenityData itemCategoryAmenityData) throws BmsSqlException;
	
	boolean update(ItemCategoryAmenityData itemCategoryAmenityData) throws BmsSqlException;
	
	boolean delete(long itemCategoryAmenityId) throws BmsSqlException;
	
	ItemCategoryAmenityData getItemCategoryAmenityById(long itemCategoryAmenityId) throws BmsSqlException;
	
	ItemCategoryAmenityData getItemCategoryAmenityByItemCategoryIdAndAmenityId(long itemCategoryId, long amenityId) throws BmsSqlException;
	
	List<ItemCategoryAmenityData> getAllItemCategoryAmenitiesByItemCategoryId(long itemCategoryId) throws BmsSqlException;

	boolean isAvailable(long id, long amenityId, long itemCategoryId) throws BmsSqlException;
	
}
