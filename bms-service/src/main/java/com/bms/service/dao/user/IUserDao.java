package com.bms.service.dao.user;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserData;

public interface IUserDao {

	long create(UserData userData) throws BmsSqlException;
	
	boolean update(UserData userData) throws BmsSqlException;
	
	boolean delete(long userId) throws BmsSqlException;
	
	UserData getUserByID(long userId) throws BmsSqlException;
	
	List<UserData> getAllUsers() throws BmsSqlException;

}
