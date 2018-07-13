package com.bms.service.dao.user;

import java.util.List;
import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserDeviceData;

public interface IUserDeviceDao {

	long create(UserDeviceData userDeviceData) throws BmsSqlException;
	
	boolean update(UserDeviceData userDeviceData) throws BmsSqlException;
	
	boolean delete(long userDeviceId) throws BmsSqlException;
	
	UserDeviceData getUserDeviceById(long userDeviceId) throws BmsSqlException;
	
	UserDeviceData getUserDevice(long userId, String token, int platform) throws BmsSqlException;
	
	List<UserDeviceData> getAllUserDevices() throws BmsSqlException;
	
}
