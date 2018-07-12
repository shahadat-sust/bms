package com.bms.service.soa.auth;

import java.util.Date;

import com.bms.common.BmsException;
import com.bms.common.util.CryptoChiper;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.auth.IAuthenticationDao;
import com.bms.service.dao.user.IUserDeviceDao;
import com.bms.service.data.user.UserDeviceData;

public class AuthenticationService implements IAuthenticationService {

	private IAuthenticationDao authenticationDao;
	private IUserDeviceDao userDeviceDao;

	@Override
	public long getAuthorizedAdmin(String username, String password) throws BmsException, BmsSqlException {
		return authenticationDao.getAuthorizedAdmin(username, new CryptoChiper().encrypt(password));
	}

	@Override
	public void updateDeviceLastUsedTime(long userID, String deviceToken, int platform, String deviceName, String imeiNumber) throws BmsException, BmsSqlException {
		UserDeviceData deviceDTO = userDeviceDao.getDevice(userID, deviceToken, platform);
		if(deviceDTO != null) {
			deviceDTO.setName(deviceName);
			deviceDTO.setImeiNumber(imeiNumber);
			deviceDTO.setLastUsedTime(new Date(System.currentTimeMillis()));
			userDeviceDao.update(deviceDTO);
		} else {
			deviceDTO = new UserDeviceData();
			deviceDTO.setUserId(userID);
			deviceDTO.setName(deviceName);
			deviceDTO.setToken(deviceToken);
			deviceDTO.setPlatform(platform);
			deviceDTO.setImeiNumber(imeiNumber);
			deviceDTO.setFirstUsedTime(new Date(System.currentTimeMillis()));
			deviceDTO.setLastUsedTime(deviceDTO.getFirstUsedTime());
			userDeviceDao.create(deviceDTO);
		}
	}

	@Override
	public IAuthenticationDao getAuthenticationDao() {
		return authenticationDao;
	}

	@Override
	public void setAuthenticationDao(IAuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}
	
	@Override
	public IUserDeviceDao getDeviceDao() {
		return userDeviceDao;
	}
	
	@Override
	public void setDeviceDao(IUserDeviceDao userDeviceDao) {
		this.userDeviceDao = userDeviceDao;
	}
	
}
