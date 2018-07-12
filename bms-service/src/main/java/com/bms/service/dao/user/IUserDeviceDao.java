package com.bms.service.dao.user;

import java.util.List;
import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserDeviceData;

public interface IUserDeviceDao {

	long create(UserDeviceData userDeviceData) throws BmsSqlException;
	
	boolean update(UserDeviceData userDeviceData) throws BmsSqlException;
	
	boolean delete(long deviceID) throws BmsSqlException;
	
	UserDeviceData getDeviceByID(long deviceId) throws BmsSqlException;
	
	UserDeviceData getDevice(long userId, String token, int platform) throws BmsSqlException;
	
	List<UserDeviceData> getAllDevices() throws BmsSqlException;
	
}
