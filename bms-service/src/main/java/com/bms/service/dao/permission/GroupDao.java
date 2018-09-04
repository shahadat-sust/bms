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
import com.bms.service.data.permission.GroupData;

@Repository("groupDao")
public class GroupDao extends BaseDao implements IGroupDao {

	@Autowired
	@Qualifier("groupQuery")
	private Properties groupQuery;
	
	@Override
	public boolean create(GroupData groupData) throws BmsSqlException {
		try {
			String sql = groupQuery.getProperty("group.create");
			return this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
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
			String sql = groupQuery.getProperty("group.update");
			return this.getTemplete().update(sql, 
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
			String sql = groupQuery.getProperty("group.delete");
			return this.getTemplete().update(sql, groupID) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public GroupData getGroupById(long groupId) throws BmsSqlException {
		try {
			String sql = groupQuery.getProperty("group.getGroupById");
			Object[] params = new Object[] {groupId};
			List<GroupData> groupList = this.getTemplete().query(sql, params, new RowMapper<GroupData>() {
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
			String sql = groupQuery.getProperty("group.getAllGroups");
			List<GroupData> groupList = this.getTemplete().query(sql, new RowMapper<GroupData>() {
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
	
	@Override
	public boolean isAvailable(long id, String name) throws BmsSqlException {
		try {
			String sql = groupQuery.getProperty("group.isAvailable");
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
