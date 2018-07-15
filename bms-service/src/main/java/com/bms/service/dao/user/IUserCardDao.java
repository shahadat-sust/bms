package com.bms.service.dao.user;

import java.util.List;
import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserCardData;

public interface IUserCardDao {

	long create(UserCardData userCardData) throws BmsSqlException;
	
	boolean update(UserCardData userCardData) throws BmsSqlException;
	
	boolean delete(long userCardId) throws BmsSqlException;
	
	UserCardData getUserCardById(long userCardId) throws BmsSqlException;
	
	List<UserCardData> getAllUserCardsByUserId(long userId) throws BmsSqlException;
	
}
