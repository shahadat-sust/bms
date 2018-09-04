package com.bms.service.dao.permission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.permission.RoleData;

@Repository("roleDao")
public class RoleDao extends BaseDao implements IRoleDao {

	@Autowired
	@Qualifier("roleQuery")
	private Properties roleQuery;
	
	@Override
	public boolean create(RoleData roleData) throws BmsSqlException {
		try {
			String sql = roleQuery.getProperty("role.create");
			return this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setLong(1, roleData.getId());
					ps.setString(2, roleData.getName());
					ps.setInt(3, roleData.getPriority());
					ps.setString(4, roleData.getRemarks());
					ps.setLong(5, roleData.getCreatedBy());
					ps.setDate(6, new java.sql.Date(roleData.getCreatedOn().getTime()));
					ps.setLong(7, roleData.getUpdatedBy());
					ps.setDate(8, new java.sql.Date(roleData.getUpdatedOn().getTime()));
					return ps;
				}
			}) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(RoleData roleData) throws BmsSqlException {
		try {
			String sql = roleQuery.getProperty("role.update");
			return this.getTemplete().update(sql, 
					roleData.getName(), 
					roleData.getPriority(), 
					roleData.getRemarks(),
					roleData.getUpdatedBy(),
					new java.sql.Date(roleData.getUpdatedOn().getTime()),
					roleData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long roleId) throws BmsSqlException {
		try {
			String sql = roleQuery.getProperty("role.delete");
			return this.getTemplete().update(sql, roleId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public RoleData getRoleById(long roleId) throws BmsSqlException {
		try {
			String sql = roleQuery.getProperty("role.getRoleById");
			Object[] params = new Object[] {roleId};
			List<RoleData> roleList = this.getTemplete().query(sql, params, new RowMapper<RoleData>() {
				@Override
				public RoleData mapRow(ResultSet rs, int index) throws SQLException {
					RoleData roleData = new RoleData();
					roleData.setId(rs.getLong(1));
					roleData.setName(rs.getString(2));
					roleData.setPriority(rs.getInt(3));
					roleData.setRemarks(rs.getString(4));
					return roleData;
				}
			});
			
			if (roleList.isEmpty()) {
			  return null;
			} else if (roleList.size() == 1) {
			  return roleList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<RoleData> getAllRoles() throws BmsSqlException {
		try {
			String sql = roleQuery.getProperty("role.getAllRoles");
			List<RoleData> roleList = this.getTemplete().query(sql, new RowMapper<RoleData>() {
				@Override
				public RoleData mapRow(ResultSet rs, int index) throws SQLException {
					RoleData roleData = new RoleData();
					roleData.setId(rs.getLong(1));
					roleData.setName(rs.getString(2));
					roleData.setPriority(rs.getInt(3));
					roleData.setRemarks(rs.getString(4));
					return roleData;
				}
			});
			return roleList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public boolean isAvailable(long id, String name) throws BmsSqlException {
		try {
			String sql = roleQuery.getProperty("role.isAvailable");
			Object[] params = new Object[] {id, id, name, id, name};
			List<Long> userIDs = getTemplete().query(sql, params, new RowMapper<Long> () {
				@Override
				public Long mapRow(ResultSet rs, int index) throws SQLException {
					return rs.getLong(1);
				}
			});
			
			if (userIDs.isEmpty()) {
			  return true;
			} else if (userIDs.size() == 1) {
			  return false;
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
