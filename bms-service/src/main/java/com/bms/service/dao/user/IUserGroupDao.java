package com.bms.service.dao.user;

import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserCardData;
import com.bms.service.data.user.UserGroupData;

public interface IUserGroupDao {

	long create(UserGroupData userGroupData) throws BmsSqlException;
	
	boolean update(UserGroupData userGroupData) throws BmsSqlException;
	
	boolean delete(long userId) throws BmsSqlException;
	
	UserGroupData getUserGroupByUserId(long userId) throws BmsSqlException;

}
