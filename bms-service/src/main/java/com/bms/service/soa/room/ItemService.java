package com.bms.service.soa.room;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemDao;
import com.bms.service.data.room.ItemData;
import com.bms.service.soa.BaseService;

@Service("itemService")
public class ItemService extends BaseService implements IItemService {

	private IItemDao itemDao;
	
	@Override
	public long create(ItemData itemData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemData.setStatus(Constants.STATUS_EXIST);
			itemData.setCreatedBy(loginUserId);
			itemData.setCreatedOn(currDate);
			itemData.setUpdatedBy(loginUserId);
			itemData.setUpdatedOn(currDate);
			itemData.getRoomData().setCreatedBy(loginUserId);
			itemData.getRoomData().setCreatedOn(currDate);
			itemData.getRoomData().setUpdatedBy(loginUserId);
			itemData.getRoomData().setUpdatedOn(currDate);
			return itemDao.create(itemData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(ItemData itemData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemData.setUpdatedBy(loginUserId);
			itemData.setUpdatedOn(currDate);
			itemData.getRoomData().setUpdatedBy(loginUserId);
			itemData.getRoomData().setUpdatedOn(currDate);
			return itemDao.update(itemData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long itemId, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			ItemData itemData = new ItemData();
			itemData.setId(itemId);
			itemData.setUpdatedBy(loginUserId);
			itemData.setUpdatedOn(currDate);
			return itemDao.delete(itemData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ItemData getItemById(long itemId) throws BmsSqlException, BmsException {
		try {
			return itemDao.getItemById(itemId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public List<ItemData> getAllItemsByItemCategoryId(long itemCategoryId) throws BmsSqlException, BmsException {
		try {
			return itemDao.getAllItemsByItemCategoryId(itemCategoryId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<ItemData> getAllItemsByProviderId(long providerId) throws BmsSqlException, BmsException {
		try {
			return itemDao.getAllItemsByProviderId(providerId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long itemId, String name, long providerId) throws BmsSqlException, BmsException {
		try {
			return itemDao.isAvailable(itemId, name, providerId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IItemDao getItemDao() {
		return itemDao;
	}

	@Autowired
	@Qualifier("itemDao")
	@Override
	public void setItemDao(IItemDao itemDao) {
		this.itemDao = itemDao;
	}

}
