package com.bms.service.soa.room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemAmenityDao;
import com.bms.service.dao.room.IItemCategoryAmenityDao;
import com.bms.service.dao.room.IItemDao;
import com.bms.service.data.room.ItemAmenityData;
import com.bms.service.data.room.ItemCategoryAmenityData;
import com.bms.service.data.room.ItemData;
import com.bms.service.soa.BaseService;

@Service("itemService")
public class ItemService extends BaseService implements IItemService {

	private IItemDao itemDao;
	private IItemAmenityDao itemAmenityDao; 
	private IItemCategoryAmenityDao itemCategoryAmenityDao; 
	
	@Override
	public long create(ItemData itemData, long loginUserId) throws BmsSqlException, BmsException {
		long itemId = 0;
		TransactionStatus txStatus= null;
		try {
			txStatus = getTxManager().getTransaction(new DefaultTransactionDefinition());
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
			itemId = itemDao.create(itemData);
			itemData.setId(itemId);
			
			if (itemId > 0) {
				List<ItemCategoryAmenityData> itemCategoryAmenityList = itemCategoryAmenityDao.getAllItemCategoryAmenitiesByItemCategoryId(itemData.getItemCategoryId());
				if (itemCategoryAmenityList != null && itemCategoryAmenityList.size() > 0) {
					for (ItemCategoryAmenityData itemCategoryAmenityData : itemCategoryAmenityList) {
						ItemAmenityData itemAmenityData = new ItemAmenityData();
						itemAmenityData.setItemId(itemId);
						itemAmenityData.setAmenityId(itemCategoryAmenityData.getAmenityId());
						itemAmenityData.setQuantity(itemCategoryAmenityData.getQuantity());
						itemAmenityData.setCreatedBy(loginUserId);
						itemAmenityData.setCreatedOn(currDate);
						itemAmenityData.setUpdatedBy(loginUserId);
						itemAmenityData.setUpdatedOn(currDate);
						itemAmenityDao.create(itemAmenityData);
					}
				}
			}
			
			getTxManager().commit(txStatus);
		} catch (BmsSqlException e) {
			if(txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			itemData.setId(0);
			throw e;
		} catch (Exception e) {
			if(txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			itemData.setId(0);
			throw new BmsException(e);
		}
		return itemId;
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
