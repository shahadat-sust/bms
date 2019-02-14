package com.bms.service.soa.room;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemCategoryAmenityDao;
import com.bms.service.data.room.ItemCategoryAmenityData;

public interface IItemCategoryAmenityService {

	long create(ItemCategoryAmenityData itemCategoryAmenityData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean update(ItemCategoryAmenityData itemCategoryAmenityData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean delete(long itemCategoryAmenityId) throws BmsSqlException, BmsException;
	
	ItemCategoryAmenityData getItemCategoryAmenityById(long itemCategoryAmenityId) throws BmsSqlException, BmsException;
	
	ItemCategoryAmenityData getItemCategoryAmenityByItemCategoryIdAndAmenityId(long itemCategoryId, long amenityId) throws BmsSqlException, BmsException;
	
	List<ItemCategoryAmenityData> getAllItemCategoryAmenitiesByItemCategoryId(long itemCategoryId) throws BmsSqlException, BmsException;

	boolean isAvailable(long id, long amenityId, long itemCategoryId) throws BmsSqlException, BmsException;
	
	IItemCategoryAmenityDao getItemCategoryAmenityDao();

	void setItemCategoryAmenityDao(IItemCategoryAmenityDao itemCategoryAmenityDao);
	
}
