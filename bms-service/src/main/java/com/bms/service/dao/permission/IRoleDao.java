package com.bms.service.dao.permission;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.permission.RoleData;

public interface IRoleDao {
	
	long create(RoleData roleData) throws BmsSqlException;
	
	boolean update(RoleData roleData) throws BmsSqlException;
	
	boolean delete(long roleId) throws BmsSqlException;
	
	RoleData getRoleByID(long roleId) throws BmsSqlException;
	
	List<RoleData> getAllRoles() throws BmsSqlException;
	
}
