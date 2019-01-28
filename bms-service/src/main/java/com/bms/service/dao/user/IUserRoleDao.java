package com.bms.service.dao.user;

import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserRoleData;

public interface IUserRoleDao {

	long create(UserRoleData userRoleData) throws BmsSqlException;
	
	boolean update(UserRoleData userRoleData) throws BmsSqlException;
	
	boolean delete(long userId) throws BmsSqlException;
	
	UserRoleData getUserRoleByUserId(long userId) throws BmsSqlException;
	
}
