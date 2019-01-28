package com.bms.service.dao.user;

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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.user.UserGroupData;

@Repository("userGroupDao")
public class UserGroupDao extends BaseDao implements IUserGroupDao {

	@Autowired
	@Qualifier("userGroupQuery")
	private Properties userGroupQuery;
	
	@Override
	public long create(UserGroupData userGroupData) throws BmsSqlException {
		try {
			String sql = userGroupQuery.getProperty("userGroup.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, userGroupData.getUserId());
					ps.setLong(2, userGroupData.getGroupId());
					ps.setLong(3, userGroupData.getCreatedBy());
					ps.setTimestamp(4, new java.sql.Timestamp(userGroupData.getCreatedOn().getTime()));
					ps.setLong(5, userGroupData.getUpdatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(userGroupData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(UserGroupData userGroupData) throws BmsSqlException {
		try {
			String sql = userGroupQuery.getProperty("userGroup.update");
			return this.getTemplete().update(sql, 
					userGroupData.getUserId(),
					userGroupData.getGroupId(),
					userGroupData.getUpdatedBy(),
					new java.sql.Timestamp(userGroupData.getUpdatedOn().getTime()),
					userGroupData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long userGroupId) throws BmsSqlException {
		try {
			String sql = userGroupQuery.getProperty("userGroup.delete");
			return this.getTemplete().update(sql.toString(), userGroupId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public UserGroupData getUserGroupByUserId(long userId) throws BmsSqlException {
		try {
			String sql = userGroupQuery.getProperty("userGroup.getUserGroupByUserId");
			Object[] params = new Object[] {userId};
			List<UserGroupData> list = this.getTemplete().query(sql, params, new RowMapper<UserGroupData>() {
				@Override
				public UserGroupData mapRow(ResultSet rs, int index) throws SQLException {
					UserGroupData userGroupData = new UserGroupData();
					userGroupData.setId(rs.getLong(1));
					userGroupData.setUserId(rs.getLong(2));
					userGroupData.setGroupId(rs.getLong(3));
					userGroupData.setGroupName(rs.getString(4));
					return userGroupData;
				}
			});
			
			if (list.isEmpty()) {
			  return null;
			} else if (list.size() == 1) {
			  return list.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
