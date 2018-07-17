package com.bms.service.soa.permission;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.permission.IRoleDao;
import com.bms.service.data.permission.RoleData;

public interface IRoleService {

	long create(RoleData roleData) throws BmsException, BmsSqlException;
	
	boolean update(RoleData roleData) throws BmsException, BmsSqlException;
	
	boolean delete(long roleId) throws BmsException, BmsSqlException;
	
	RoleData getRoleByID(long roleId) throws BmsException, BmsSqlException;
	
	List<RoleData> getAllRoles() throws BmsException, BmsSqlException;
	
	public IRoleDao getRoleDao();

	public void setRoleDao(IRoleDao roleDao);
}
