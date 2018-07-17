package com.bms.service.soa;

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
	public long create(StateData stateData) throws BmsException, BmsSqlException {
		return stateDao.create(stateData);
	}

	@Override
	public boolean update(StateData stateData) throws BmsException, BmsSqlException {
		return stateDao.update(stateData);
	}

	@Override
	public boolean delete(long stateId) throws BmsException, BmsSqlException {
		return stateDao.delete(stateId);
	}

	@Override
	public StateData getStateById(long stateId) throws BmsException, BmsSqlException {
		return stateDao.getStateById(stateId);
	}

	@Override
	public List<StateData> getAllStates() throws BmsException, BmsSqlException {
		return stateDao.getAllStates();
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
