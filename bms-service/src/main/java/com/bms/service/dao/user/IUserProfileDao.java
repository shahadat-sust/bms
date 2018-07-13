package com.bms.service.dao.user;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.user.UserProfileData;

public interface IUserProfileDao {

	long create(UserProfileData userProfileData) throws BmsSqlException;
	
	boolean update(UserProfileData userProfileData) throws BmsSqlException;
	
	boolean delete(long userProfileId) throws BmsSqlException;
	
	UserProfileData getUserProfileById(long userProfileId) throws BmsSqlException;
	
	List<UserProfileData> getAllUserProfileDatas() throws BmsSqlException;

}
