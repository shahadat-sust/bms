package com.bms.service.dao.room;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.room.ItemAmenityData;

public interface IItemAmenityDao {

	long create(ItemAmenityData itemAmenityData) throws BmsSqlException;
	
	boolean update(ItemAmenityData itemAmenityData) throws BmsSqlException;
	
	boolean delete(long itemAmenityId) throws BmsSqlException;
	
	ItemAmenityData getItemAmenityById(long itemAmenityId) throws BmsSqlException;
	
	ItemAmenityData getItemAmenityByItemIdAndAmenityId(long itemId, long amenityId) throws BmsSqlException;
	
	List<ItemAmenityData> getAllItemAmenitisByItemId(long itemId) throws BmsSqlException;

	boolean isAvailable(long id, long amenityId, long itemId) throws BmsSqlException;
	
}
