package com.bms.service.dao;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.StateData;

public interface IStateDao {
	
	long create(StateData stateData) throws BmsSqlException;
	
	boolean update(StateData stateData) throws BmsSqlException;
	
	boolean delete(long stateId) throws BmsSqlException;
	
	StateData getStateById(long stateId) throws BmsSqlException;
	
	List<StateData> getStatesByCountryId(long countryId) throws BmsSqlException;
	
	List<StateData> getAllStates() throws BmsSqlException;
	
	boolean isAvailable(long id, String name, long countryId) throws BmsSqlException;
	
}
