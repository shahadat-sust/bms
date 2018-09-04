package com.bms.service.soa.provider;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IPointOfInterestDao;
import com.bms.service.data.provider.PointOfInterestData;
import com.bms.service.soa.BaseService;

@Service("pointOfInterestService")
public class PointOfInterestService extends BaseService implements IPointOfInterestService {

	private IPointOfInterestDao pointOfInterestDao;
	
	@Override
	public long create(PointOfInterestData pointOfInterestData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			pointOfInterestData.setCreatedBy(loginUserId);
			pointOfInterestData.setCreatedOn(currDate);
			pointOfInterestData.setUpdatedBy(loginUserId);
			pointOfInterestData.setUpdatedOn(currDate);
			return pointOfInterestDao.create(pointOfInterestData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(PointOfInterestData pointOfInterestData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			pointOfInterestData.setUpdatedBy(loginUserId);
			pointOfInterestData.setUpdatedOn(currDate);
			return pointOfInterestDao.update(pointOfInterestData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public boolean delete(long pointOfInterestId) throws BmsException, BmsSqlException {
		return pointOfInterestDao.delete(pointOfInterestId);
	}

	@Override
	public PointOfInterestData getPointOfInterestById(long pointOfInterestId) throws BmsException, BmsSqlException {
		return pointOfInterestDao.getPointOfInterestById(pointOfInterestId);
	}
	
	@Override
	public List<PointOfInterestData> getPointOfInterestsByProviderTypeId(long providerTypeId) throws BmsException, BmsSqlException {
		return pointOfInterestDao.getPointOfInterestsByProviderTypeId(providerTypeId);
	}

	@Override
	public List<PointOfInterestData> getAllPointOfInterests() throws BmsException, BmsSqlException {
		return pointOfInterestDao.getAllPointOfInterests();
	}

	@Override
	public boolean isAvailable(long id, String name, long providerTypeId) throws BmsSqlException {
		return pointOfInterestDao.isAvailable(id, name, providerTypeId);
	}

	@Override
	public IPointOfInterestDao getPointOfInterestDao() {
		return pointOfInterestDao;
	}

	@Autowired
	@Qualifier("pointOfInterestDao")
	@Override
	public void setPointOfInterestDao(IPointOfInterestDao pointOfInterestDao) {
		this.pointOfInterestDao = pointOfInterestDao;
	}

}
