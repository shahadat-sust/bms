package com.bms.service.soa.room;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.room.IItemTypeDao;
import com.bms.service.data.room.ItemTypeData;
import com.bms.service.soa.BaseService;

@Service("itemTypeService")
public class ItemTypeService extends BaseService implements IItemTypeService {

	private IItemTypeDao itemTypeDao;
	
	@Override
	public long create(ItemTypeData itemTypeData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemTypeData.setStatus(Constants.STATUS_EXIST);
			itemTypeData.setCreatedBy(loginUserId);
			itemTypeData.setCreatedOn(currDate);
			itemTypeData.setUpdatedBy(loginUserId);
			itemTypeData.setUpdatedOn(currDate);
			return itemTypeDao.create(itemTypeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(ItemTypeData itemTypeData, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			itemTypeData.setUpdatedBy(loginUserId);
			itemTypeData.setUpdatedOn(currDate);
			return itemTypeDao.update(itemTypeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long itemTypeId, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			ItemTypeData itemTypeData = new ItemTypeData();
			itemTypeData.setId(itemTypeId);
			itemTypeData.setUpdatedBy(loginUserId);
			itemTypeData.setUpdatedOn(currDate);
			return itemTypeDao.delete(itemTypeData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ItemTypeData getItemTypeById(long itemTypeId) throws BmsSqlException, BmsException {
		try {
			return itemTypeDao.getItemTypeById(itemTypeId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<ItemTypeData> getAllItemTypesByProviderId(long providerId) throws BmsSqlException, BmsException {
		try {
			return itemTypeDao.getAllItemTypesByProviderId(providerId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, String name, long providerId) throws BmsSqlException, BmsException {
		try {
			return itemTypeDao.isAvailable(id, name, providerId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IItemTypeDao getItemTypeDao() {
		return itemTypeDao;
	}

	@Autowired
	@Qualifier("itemTypeDao")
	@Override
	public void setItemTypeDao(IItemTypeDao itemTypeDao) {
		this.itemTypeDao = itemTypeDao;
	}

}
