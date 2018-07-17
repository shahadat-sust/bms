package com.bms.service.soa;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.IStateDao;
import com.bms.service.data.StateData;

public interface IStateService {
	
	long create(StateData stateData) throws BmsException, BmsSqlException;
	
	boolean update(StateData stateData) throws BmsException, BmsSqlException;
	
	boolean delete(long stateId) throws BmsException, BmsSqlException;
	
	StateData getStateById(long stateId) throws BmsException, BmsSqlException;
	
	List<StateData> getAllStates() throws BmsException, BmsSqlException;
	
	IStateDao getStateDao();

	void setStateDao(IStateDao stateDao);
}
