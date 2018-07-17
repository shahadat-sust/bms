package com.bms.service.soa.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.permission.IGroupDao;
import com.bms.service.data.permission.GroupData;
import com.bms.service.soa.BaseService;

@Service("groupService")
public class GroupService extends BaseService implements IGroupService {

	private IGroupDao groupDao;
	
	@Override
	public long create(GroupData groupData) throws BmsException, BmsSqlException {
		return groupDao.create(groupData);
	}

	@Override
	public boolean update(GroupData groupData) throws BmsException, BmsSqlException {
		return groupDao.update(groupData);
	}

	@Override
	public boolean delete(long groupId) throws BmsException, BmsSqlException {
		return groupDao.delete(groupId);
	}

	@Override
	public GroupData getGroupById(long groupId) throws BmsException, BmsSqlException {
		return groupDao.getGroupById(groupId);
	}

	@Override
	public List<GroupData> getAllGroups() throws BmsException, BmsSqlException {
		return groupDao.getAllGroups();
	}

	@Override
	public IGroupDao getGroupDao() {
		return groupDao;
	}

	@Autowired
	@Qualifier("groupDao")
	@Override
	public void setGroupDao(IGroupDao groupDao) {
		this.groupDao = groupDao;
	}

}
