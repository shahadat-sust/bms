package com.bms.service.dao.permission;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.permission.GroupData;

public interface IGroupDao {
	
	long create(GroupData groupData) throws BmsSqlException;
	
	boolean update(GroupData groupData) throws BmsSqlException;
	
	boolean delete(long groupId) throws BmsSqlException;
	
	GroupData getGroupById(long groupId) throws BmsSqlException;
	
	List<GroupData> getAllGroups() throws BmsSqlException;
	
}
