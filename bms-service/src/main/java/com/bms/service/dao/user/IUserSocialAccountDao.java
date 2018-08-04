package com.bms.service.dao.user;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserSocialAccountData;

public interface IUserSocialAccountDao {
	
	long create(UserSocialAccountData userSocialAccountData) throws BmsSqlException;
	
	boolean update(UserSocialAccountData userSocialAccountData) throws BmsSqlException;
	
	boolean delete(long userSocialAccountId) throws BmsSqlException;
	
	UserSocialAccountData getUserSocialAccountById(long userSocialAccountId) throws BmsSqlException;
	
	UserSocialAccountData getUserSocialAccountByTypeAccountId(int type, String accountId) throws BmsSqlException;
	
	List<UserSocialAccountData> getAllUserSocialAccountsByUserId(long userId) throws BmsSqlException;
	
}
