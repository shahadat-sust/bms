package com.bms.service.soa.user;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.user.IUserDeviceDao;

public interface IUserDeviceService {

	public IUserDeviceDao getDeviceDao();
	
	public void setDeviceDao(IUserDeviceDao userDeviceDao);
	
	public void updateUserDeviceLastUsedTime(long userID, String deviceToken, int platform, String deviceName, String imeiNumber, long loginUserId) throws BmsException, BmsSqlException;
	
}
