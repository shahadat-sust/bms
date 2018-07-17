package com.bms.service.soa.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.permission.IRoleDao;
import com.bms.service.data.permission.RoleData;
import com.bms.service.soa.BaseService;

public class RoleService extends BaseService implements IRoleService {

	private IRoleDao roleDao;
	
	@Override
	public long create(RoleData roleData) throws BmsException, BmsSqlException {
		return roleDao.create(roleData);
	}

	@Override
	public boolean update(RoleData roleData) throws BmsException, BmsSqlException {
		return roleDao.update(roleData);
	}

	@Override
	public boolean delete(long roleId) throws BmsException, BmsSqlException {
		return roleDao.delete(roleId);
	}

	@Override
	public RoleData getRoleByID(long roleId) throws BmsException, BmsSqlException {
		return roleDao.getRoleByID(roleId);
	}

	@Override
	public List<RoleData> getAllRoles() throws BmsException, BmsSqlException {
		return roleDao.getAllRoles();
	}

	@Override
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	@Autowired
	@Qualifier("roleDao")
	@Override
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

}
