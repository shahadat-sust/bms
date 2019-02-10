package com.bms.service.soa.room;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemAmenityDao;
import com.bms.service.data.room.ItemAmenityData;

public interface IItemAmenityService {

	long create(ItemAmenityData itemAmenityData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean update(ItemAmenityData itemAmenityData, long loginUserId) throws BmsSqlException, BmsException;
	
	boolean delete(long itemAmenityId) throws BmsSqlException, BmsException;
	
	ItemAmenityData getItemAmenityById(long itemAmenityId) throws BmsSqlException, BmsException;
	
	List<ItemAmenityData> getAllItemAmenityByItemId(long itemId) throws BmsSqlException, BmsException;

	boolean isAvailable(long id, String amenityId, long itemId) throws BmsSqlException, BmsException;

	public IItemAmenityDao getItemAmenityDao();

	public void setItemAmenityDao(IItemAmenityDao itemAmenityDao);
	
}
