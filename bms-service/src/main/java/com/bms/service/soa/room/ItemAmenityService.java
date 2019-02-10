package com.bms.service.soa.room;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemAmenityDao;
import com.bms.service.data.room.ItemAmenityData;
import com.bms.service.soa.BaseService;

public class ItemAmenityService extends BaseService implements IItemAmenityService {

	private IItemAmenityDao itemAmenityDao; 
	
	@Override
	public long create(ItemAmenityData itemAmenityData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemAmenityData.setCreatedBy(loginUserId);
			itemAmenityData.setCreatedOn(currDate);
			itemAmenityData.setUpdatedBy(loginUserId);
			itemAmenityData.setUpdatedOn(currDate);
			return itemAmenityDao.create(itemAmenityData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(ItemAmenityData itemAmenityData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemAmenityData.setUpdatedBy(loginUserId);
			itemAmenityData.setUpdatedOn(currDate);
			return itemAmenityDao.update(itemAmenityData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long itemAmenityId) throws BmsSqlException, BmsException {
		try {
			return itemAmenityDao.delete(itemAmenityId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ItemAmenityData getItemAmenityById(long itemAmenityId) throws BmsSqlException, BmsException {
		try {
			return itemAmenityDao.getItemAmenityById(itemAmenityId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<ItemAmenityData> getAllItemAmenityByItemId(long itemId) throws BmsSqlException, BmsException {
		try {
			return itemAmenityDao.getAllItemAmenityByItemId(itemId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, String amenityId, long itemId) throws BmsSqlException, BmsException {
		try {
			return itemAmenityDao.isAvailable(id, amenityId, itemId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IItemAmenityDao getItemAmenityDao() {
		return itemAmenityDao;
	}

	@Autowired
	@Qualifier("itemAmenityDao")
	@Override
	public void setItemAmenityDao(IItemAmenityDao itemAmenityDao) {
		this.itemAmenityDao = itemAmenityDao;
	}

}
