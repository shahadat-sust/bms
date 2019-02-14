package com.bms.service.soa.room;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemCategoryAmenityDao;
import com.bms.service.data.room.ItemCategoryAmenityData;
import com.bms.service.soa.BaseService;

@Service("itemCategoryAmenityService")
public class ItemCategoryAmenityService extends BaseService implements IItemCategoryAmenityService {

	private IItemCategoryAmenityDao itemCategoryAmenityDao; 
	
	@Override
	public long create(ItemCategoryAmenityData itemCategoryAmenityData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemCategoryAmenityData.setCreatedBy(loginUserId);
			itemCategoryAmenityData.setCreatedOn(currDate);
			itemCategoryAmenityData.setUpdatedBy(loginUserId);
			itemCategoryAmenityData.setUpdatedOn(currDate);
			return itemCategoryAmenityDao.create(itemCategoryAmenityData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(ItemCategoryAmenityData itemCategoryAmenityData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemCategoryAmenityData.setUpdatedBy(loginUserId);
			itemCategoryAmenityData.setUpdatedOn(currDate);
			return itemCategoryAmenityDao.update(itemCategoryAmenityData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long itemCategoryAmenityId) throws BmsSqlException, BmsException {
		try {
			return itemCategoryAmenityDao.delete(itemCategoryAmenityId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ItemCategoryAmenityData getItemCategoryAmenityById(long itemCategoryAmenityId) throws BmsSqlException, BmsException {
		try {
			return itemCategoryAmenityDao.getItemCategoryAmenityById(itemCategoryAmenityId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public ItemCategoryAmenityData getItemCategoryAmenityByItemCategoryIdAndAmenityId(long itemCategoryId, long amenityId) throws BmsSqlException, BmsException {
		try {
			return itemCategoryAmenityDao.getItemCategoryAmenityByItemCategoryIdAndAmenityId(itemCategoryId, amenityId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<ItemCategoryAmenityData> getAllItemCategoryAmenitiesByItemCategoryId(long itemCategoryId) throws BmsSqlException, BmsException {
		try {
			return itemCategoryAmenityDao.getAllItemCategoryAmenitiesByItemCategoryId(itemCategoryId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, long amenityId, long itemCategoryId) throws BmsSqlException, BmsException {
		try {
			return itemCategoryAmenityDao.isAvailable(id, amenityId, itemCategoryId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IItemCategoryAmenityDao getItemCategoryAmenityDao() {
		return itemCategoryAmenityDao;
	}

	@Autowired
	@Qualifier("itemCategoryAmenityDao")
	@Override
	public void setItemCategoryAmenityDao(IItemCategoryAmenityDao itemCategoryAmenityDao) {
		this.itemCategoryAmenityDao = itemCategoryAmenityDao;
	}

}
