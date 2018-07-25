package com.bms.service.dao.permission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.permission.RoleData;

@Repository("roleDao")
public class RoleDao extends BaseDao implements IRoleDao {

	@Override
	public boolean create(RoleData roleData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO Role ")
		.append("( ")
			.append("Id, ")
			.append("Name, ")
			.append("Priority, ")
			.append("Remarks, ")
			.append("CreatedBy, ")
			.append("CreatedOn, ")
			.append("UpdatedBy, ")
			.append("UpdatedOn ")
		.append(") ")
		.append("VALUES ")
		.append("( ")
			.append("?, ")
			.append("?, ")
			.append("?, ")
			.append("?, ")
			.append("?, ")
			.append("?, ")
			.append("?, ")
			.append("? ")
		.append(")");
		
		return this.getTemplete().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql.toString());
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
	}

	@Override
	public boolean update(RoleData roleData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE Role SET ")
			.append("Name = ?, ")
			.append("Priority = ?, ")
			.append("Remarks = ?, ")
			.append("UpdatedBy = ?, ")
			.append("UpdatedOn = ? ")
		.append("WHERE ")
		.append("Id = ?");
		return this.getTemplete().update(sql.toString(), 
				roleData.getName(), 
				roleData.getPriority(), 
				roleData.getRemarks(),
				roleData.getUpdatedBy(),
				new java.sql.Date(roleData.getUpdatedOn().getTime()),
				roleData.getId()) == 1;
	}

	@Override
	public boolean delete(long roleId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM Role WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), roleId) == 1;
	}

	@Override
	public RoleData getRoleById(long roleId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Name, ")
			.append("Priority, ")
			.append("Remarks ")
		.append("FROM Role ")
		.append("WHERE ")
		.append("Id = ?");
		
		Object[] params = new Object[] {roleId};
		List<RoleData> roleList = this.getTemplete().query(sql.toString(), params, new RowMapper<RoleData>() {
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
	}

	@Override
	public List<RoleData> getAllRoles() throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Name, ")
			.append("Priority, ")
			.append("Remarks ")
		.append("FROM Role ")
		.append("ORDER BY Id DESC");

		List<RoleData> roleList = this.getTemplete().query(sql.toString(), new RowMapper<RoleData>() {
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
	}

}
