package com.bms.service.soa;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.IStateDao;
import com.bms.service.data.StateData;

@Service("stateService")
public class StateService extends BaseService implements IStateService {

	private IStateDao stateDao;
	
	@Override
	public long create(StateData stateData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			stateData.setCreatedBy(loginUserId);
			stateData.setCreatedOn(currDate);
			stateData.setUpdatedBy(loginUserId);
			stateData.setUpdatedOn(currDate);
			return stateDao.create(stateData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(StateData stateData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			stateData.setUpdatedBy(loginUserId);
			stateData.setUpdatedOn(currDate);
			return stateDao.update(stateData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long stateId) throws BmsException, BmsSqlException {
		try {
			return stateDao.delete(stateId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public StateData getStateById(long stateId) throws BmsException, BmsSqlException {
		try {
			return stateDao.getStateById(stateId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public List<StateData> getStatesByCountryId(long countryId) throws BmsException, BmsSqlException {
		try {
			return stateDao.getStatesByCountryId(countryId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<StateData> getAllStates() throws BmsException, BmsSqlException {
		try {
			return stateDao.getAllStates();
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, String name, long countryId) throws  BmsException, BmsSqlException {
		try {
			return stateDao.isAvailable(id, name, countryId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public IStateDao getStateDao() {
		return stateDao;
	}

	@Autowired
	@Qualifier("stateDao")
	@Override
	public void setStateDao(IStateDao stateDao) {
		this.stateDao = stateDao;
	}

}
