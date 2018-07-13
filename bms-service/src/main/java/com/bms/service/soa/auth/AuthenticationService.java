package com.bms.service.soa.auth;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.common.util.CryptoChiper;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.auth.IAuthenticationDao;
import com.bms.service.dao.user.IUserDeviceDao;
import com.bms.service.data.user.UserDeviceData;

@Service("authenticationService")
public class AuthenticationService implements IAuthenticationService {

	private IAuthenticationDao authenticationDao;
	private IUserDeviceDao userDeviceDao;

	@Override
	public long getAuthorizedAdmin(String username, String password) throws BmsException, BmsSqlException {
		return authenticationDao.getAuthorizedAdmin(username, new CryptoChiper().encrypt(password));
	}

	@Override
	public void updateDeviceLastUsedTime(long userID, String deviceToken, int platform, String deviceName, String imeiNumber, long loginUserId) throws BmsException, BmsSqlException {
		Date currDate = new Date(System.currentTimeMillis());
		UserDeviceData userDeviceData = userDeviceDao.getDevice(userID, deviceToken, platform);
		if(userDeviceData != null) {
			userDeviceData.setName(deviceName);
			userDeviceData.setImeiNumber(imeiNumber);
			userDeviceData.setLastUsedTime(currDate);
			userDeviceData.setUpdatedBy(loginUserId);
			userDeviceData.setUpdatedOn(currDate);
			userDeviceDao.update(userDeviceData);
		} else {
			userDeviceData = new UserDeviceData();
			userDeviceData.setUserId(userID);
			userDeviceData.setName(deviceName);
			userDeviceData.setToken(deviceToken);
			userDeviceData.setPlatform(platform);
			userDeviceData.setImeiNumber(imeiNumber);
			userDeviceData.setFirstUsedTime(currDate);
			userDeviceData.setLastUsedTime(currDate);
			userDeviceData.setCreatedBy(loginUserId);
			userDeviceData.setCreatedOn(currDate);
			userDeviceData.setUpdatedBy(loginUserId);
			userDeviceData.setUpdatedOn(currDate);
			userDeviceDao.create(userDeviceData);
		}
	}

	@Override
	public IAuthenticationDao getAuthenticationDao() {
		return authenticationDao;
	}

	@Autowired
	@Qualifier("authenticationDao")
	@Override
	public void setAuthenticationDao(IAuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}
	
	@Override
	public IUserDeviceDao getDeviceDao() {
		return userDeviceDao;
	}
	
	@Autowired
	@Qualifier("userDeviceDao")
	@Override
	public void setDeviceDao(IUserDeviceDao userDeviceDao) {
		this.userDeviceDao = userDeviceDao;
	}
	
}
