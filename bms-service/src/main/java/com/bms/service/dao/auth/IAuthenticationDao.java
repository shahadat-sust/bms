package com.bms.service.dao.auth;

import com.bms.service.BmsSqlException;

public interface IAuthenticationDao {

	public long getAuthorizedAdmin(String username, String password) throws BmsSqlException;
	
}
