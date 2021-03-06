package com.bms.service.soa.auth;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.auth.IAuthenticationDao;
import com.bms.service.dao.user.IUserDeviceDao;

public interface IAuthenticationService {

	public IAuthenticationDao getAuthenticationDao();
	
	public void setAuthenticationDao(IAuthenticationDao authenticationDao);
	
	public long getAuthorizedAdmin(String username, String password) throws BmsException, BmsSqlException;

}
