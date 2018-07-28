package com.bms.service.dao.permission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.permission.GroupData;

@Repository("groupDao")
public class GroupDao extends BaseDao implements IGroupDao {

	@Override
	public boolean create(GroupData groupData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO `Group` ")
			.append("( ")
				.append("Id, ")
				.append("Name, ")
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
				.append("? ")
			.append(")");
			return this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql.toString());
					ps.setLong(1, groupData.getId());
					ps.setString(2, groupData.getName());
					ps.setString(3, groupData.getRemarks());
					ps.setLong(4, groupData.getCreatedBy());
					ps.setDate(5, new java.sql.Date(groupData.getCreatedOn().getTime()));
					ps.setLong(6, groupData.getUpdatedBy());
					ps.setDate(7, new java.sql.Date(groupData.getUpdatedOn().getTime()));
					return ps;
				}
			}) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(GroupData groupData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("UPDATE `Group` SET ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ? ");
			return this.getTemplete().update(sql.toString(), 
					groupData.getName(), 
					groupData.getRemarks(),
					groupData.getUpdatedBy(),
					new java.sql.Date(groupData.getUpdatedOn().getTime()),
					groupData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long groupID) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM `Group` WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), groupID) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public GroupData getGroupById(long groupId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM `Group` ")
			.append("WHERE ")
			.append("Id = ?");
			
			Object[] params = new Object[] {groupId};
			List<GroupData> groupList = this.getTemplete().query(sql.toString(), params, new RowMapper<GroupData>() {
				@Override
				public GroupData mapRow(ResultSet rs, int index) throws SQLException {
					GroupData groupData = new GroupData();
					groupData.setId(rs.getLong(1));
					groupData.setName(rs.getString(2));
					groupData.setRemarks(rs.getString(3));
					return groupData;
				}
			});
			
			if (groupList.isEmpty()) {
			  return null;
			} else if (groupList.size() == 1) {
			  return groupList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<GroupData> getAllGroups() throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM `Group`")
			.append("ORDER BY Id DESC");
			
			List<GroupData> groupList = this.getTemplete().query(sql.toString(), new RowMapper<GroupData>() {
				@Override
				public GroupData mapRow(ResultSet rs, int index) throws SQLException {
					GroupData groupData = new GroupData();
					groupData.setId(rs.getLong(1));
					groupData.setName(rs.getString(2));
					groupData.setRemarks(rs.getString(3));
					return groupData;
				}
			});
			return groupList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
