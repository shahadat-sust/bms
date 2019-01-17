package com.bms.service.soa.permission;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.permission.IGroupDao;
import com.bms.service.data.permission.GroupData;

public interface IGroupService {

	boolean create(GroupData groupData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean update(GroupData groupData, long loginUserId) throws BmsException, BmsSqlException;
	
	boolean delete(long groupId) throws BmsException, BmsSqlException;
	
	GroupData getGroupById(long groupId) throws BmsException, BmsSqlException;
	
	List<GroupData> getAllGroups() throws BmsException, BmsSqlException;
	
	boolean isAvailable(long id, String name) throws BmsException, BmsSqlException;
	
	IGroupDao getGroupDao();

	void setGroupDao(IGroupDao groupDao);
	
}
