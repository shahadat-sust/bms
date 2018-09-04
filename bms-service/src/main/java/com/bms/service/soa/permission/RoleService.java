package com.bms.service.soa.permission;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.permission.IRoleDao;
import com.bms.service.data.permission.RoleData;
import com.bms.service.soa.BaseService;

@Service("roleService")
public class RoleService extends BaseService implements IRoleService {

	private IRoleDao roleDao;
	
	@Override
	public boolean create(RoleData roleData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			roleData.setCreatedBy(loginUserId);
			roleData.setCreatedOn(currDate);
			roleData.setUpdatedBy(loginUserId);
			roleData.setUpdatedOn(currDate);
			return roleDao.create(roleData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(RoleData roleData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			roleData.setUpdatedBy(loginUserId);
			roleData.setUpdatedOn(currDate);
			return roleDao.update(roleData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long roleId) throws BmsException, BmsSqlException {
		return roleDao.delete(roleId);
	}

	@Override
	public RoleData getRoleById(long roleId) throws BmsException, BmsSqlException {
		return roleDao.getRoleById(roleId);
	}

	@Override
	public List<RoleData> getAllRoles() throws BmsException, BmsSqlException {
		return roleDao.getAllRoles();
	}

	@Override
	public boolean isAvailable(long id, String name) throws BmsSqlException {
		return roleDao.isAvailable(id, name);
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
