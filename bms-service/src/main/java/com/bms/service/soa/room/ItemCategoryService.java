package com.bms.service.soa.room;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemCategoryDao;
import com.bms.service.data.room.ItemCategoryData;
import com.bms.service.soa.BaseService;

@Service("itemCategoryService")
public class ItemCategoryService extends BaseService implements IItemCategoryService {

	private IItemCategoryDao itemCategoryDao;
	
	@Override
	public long create(ItemCategoryData itemCategoryData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemCategoryData.setStatus(Constants.STATUS_EXIST);
			itemCategoryData.setCreatedBy(loginUserId);
			itemCategoryData.setCreatedOn(currDate);
			itemCategoryData.setUpdatedBy(loginUserId);
			itemCategoryData.setUpdatedOn(currDate);
			itemCategoryData.getRoomCategoryData().setCreatedBy(loginUserId);
			itemCategoryData.getRoomCategoryData().setCreatedOn(currDate);
			itemCategoryData.getRoomCategoryData().setUpdatedBy(loginUserId);
			itemCategoryData.getRoomCategoryData().setUpdatedOn(currDate);
			return itemCategoryDao.create(itemCategoryData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(ItemCategoryData itemCategoryData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemCategoryData.setUpdatedBy(loginUserId);
			itemCategoryData.setUpdatedOn(currDate);
			itemCategoryData.getRoomCategoryData().setUpdatedBy(loginUserId);
			itemCategoryData.getRoomCategoryData().setUpdatedOn(currDate);
			return itemCategoryDao.update(itemCategoryData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long itemCategoryId, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			ItemCategoryData itemCategoryData = new ItemCategoryData();
			itemCategoryData.setId(itemCategoryId);
			itemCategoryData.setUpdatedBy(loginUserId);
			itemCategoryData.setUpdatedOn(currDate);
			return itemCategoryDao.delete(itemCategoryData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ItemCategoryData getItemCategoryById(long itemCategoryId) throws BmsSqlException, BmsException {
		try {
			return itemCategoryDao.getItemCategoryById(itemCategoryId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<ItemCategoryData> getAllItemCategoriesByProviderId(long providerId) throws BmsSqlException, BmsException {
		try {
			return itemCategoryDao.getAllItemCategoriesByProviderId(providerId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long itemCategoryId, String name, long itemTypeId) throws BmsSqlException, BmsException {
		try {
			return itemCategoryDao.isAvailable(itemCategoryId, name, itemTypeId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IItemCategoryDao getItemCategoryDao() {
		return itemCategoryDao;
	}

	@Autowired
	@Qualifier("itemCategoryDao")
	@Override
	public void setItemCategoryDao(IItemCategoryDao itemCategoryDao) {
		this.itemCategoryDao = itemCategoryDao;
	}

}
