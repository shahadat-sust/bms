package com.bms.service.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.user.UserData;
import com.bms.service.data.user.UserProfileData;

@Repository("userDao")
public class UserDao extends BaseDao implements IUserDao {

	@Autowired
	@Qualifier("userQuery")
	private Properties userQuery;

	@Override
	public long create(UserData userData) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setString(1, userData.getUsername());
					ps.setString(2, userData.getPassword());
					ps.setBoolean(3, userData.isActive());
					ps.setInt(4, userData.getStatus());
					ps.setLong(5, userData.getCreatedBy() > 0 ? userData.getCreatedBy() : null);
					ps.setTimestamp(6, new java.sql.Timestamp(userData.getCreatedOn().getTime()));
					ps.setLong(7, userData.getUpdatedBy() > 0 ? userData.getUpdatedBy() : null);
					ps.setTimestamp(8, new java.sql.Timestamp(userData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(UserData userData) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.update");
			return this.getTemplete().update(sql, 
					userData.getUsername(), 
					userData.getPassword(),
					userData.isActive() ? 1 : 0,
					userData.getStatus(),
					userData.getUpdatedBy(),
					new java.sql.Timestamp(userData.getUpdatedOn().getTime()),
					userData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long userId) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.delete");
			return this.getTemplete().update(sql, userId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public UserData getUserDataById(long userId) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.getUserDataById");
			Object[] params = new Object[] {userId};
			
			List<UserData> userList = this.getTemplete().query(sql, params, new RowMapper<UserData>() {
				@Override
				public UserData mapRow(ResultSet rs, int index) throws SQLException {
					UserData userData = new UserData();
					userData.setId(rs.getLong(1));
					userData.setUsername(rs.getString(2));
					userData.setPassword(rs.getString(3));
					userData.setActive(rs.getBoolean(4));
					userData.setStatus(rs.getInt(5));
					return userData;
				}
			});
			
			if (userList.isEmpty()) {
			  return null;
			} else if (userList.size() == 1) {
			  return userList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<UserData> getAllUserDatas() throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.getAllUserDatas");
			List<UserData> userList = this.getTemplete().query(sql, new RowMapper<UserData>() {
				@Override
				public UserData mapRow(ResultSet rs, int index) throws SQLException {
					UserData userData = new UserData();
					userData.setId(rs.getLong(1));
					userData.setUsername(rs.getString(2));
					userData.setPassword(rs.getString(3));
					userData.setActive(rs.getBoolean(4));
					userData.setStatus(rs.getInt(5));
					userData.setUserProfileData(new UserProfileData());
					long userProfileId = rs.getLong(6);
					if(userProfileId > 0) {
						userData.getUserProfileData().setId(userProfileId);
						userData.getUserProfileData().setFirstName(rs.getString(7));
						userData.getUserProfileData().setLastName(rs.getString(8));
					}
					return userData;
				}
			});
			return userList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public boolean isUsernameAvailable(long userId, String username) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.isUsernameAvailable");
			Object[] params = new Object[] {userId, userId, username, userId, username};
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
