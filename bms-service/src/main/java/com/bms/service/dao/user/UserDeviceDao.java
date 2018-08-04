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
import com.bms.service.data.user.UserDeviceData;

@Repository("userDeviceDao")
public class UserDeviceDao extends BaseDao implements IUserDeviceDao {

	@Autowired
	@Qualifier("userDeviceQuery")
	private Properties userDeviceQuery;
	
	@Override
	public long create(UserDeviceData userDeviceData) throws BmsSqlException {
		try {
			String sql = userDeviceQuery.getProperty("userCard.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, userDeviceData.getUserId());
					ps.setString(2, userDeviceData.getName());
					ps.setString(3, userDeviceData.getToken());
					ps.setInt(4, userDeviceData.getPlatform());
					ps.setString(5, userDeviceData.getImeiNumber());
					ps.setTimestamp(6, userDeviceData.getFirstUsedTime() != null ? new java.sql.Timestamp(userDeviceData.getFirstUsedTime().getTime()) : null);
					ps.setTimestamp(7, userDeviceData.getLastUsedTime() != null ? new java.sql.Timestamp(userDeviceData.getLastUsedTime().getTime()) : null);
					ps.setLong(8, userDeviceData.getCreatedBy());
					ps.setTimestamp(9, new java.sql.Timestamp(userDeviceData.getCreatedOn().getTime()));
					ps.setLong(10, userDeviceData.getUpdatedBy());
					ps.setTimestamp(11, new java.sql.Timestamp(userDeviceData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(UserDeviceData userDeviceData) throws BmsSqlException {
		try {
			String sql = userDeviceQuery.getProperty("userCard.update");
			return this.getTemplete().update(sql, 
					userDeviceData.getName(),
					userDeviceData.getToken(),
					userDeviceData.getPlatform(),
					userDeviceData.getImeiNumber(),
					new java.sql.Timestamp(userDeviceData.getFirstUsedTime().getTime()),
					new java.sql.Timestamp(userDeviceData.getLastUsedTime().getTime()),
					userDeviceData.getUpdatedBy(),
					new java.sql.Timestamp(userDeviceData.getUpdatedOn().getTime()),
					userDeviceData.getId(),
					userDeviceData.getUserId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long userDeviceId) throws BmsSqlException {
		try {
			String sql = userDeviceQuery.getProperty("userCard.delete");
			return this.getTemplete().update(sql, userDeviceId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public UserDeviceData getUserDeviceById(long userDeviceId) throws BmsSqlException {
		try {
			String sql = userDeviceQuery.getProperty("userCard.getUserDeviceById");
			Object[] params = new Object[] {userDeviceId};
			List<UserDeviceData> deviceList = this.getTemplete().query(sql, params, new RowMapper<UserDeviceData>() {
				@Override
				public UserDeviceData mapRow(ResultSet rs, int index) throws SQLException {
					UserDeviceData userDeviceData = new UserDeviceData();
					userDeviceData.setId(rs.getLong(1));
					userDeviceData.setUserId(rs.getLong(2));
					userDeviceData.setName(rs.getString(3));
					userDeviceData.setToken(rs.getString(4));
					userDeviceData.setPlatform(rs.getInt(5));
					userDeviceData.setImeiNumber(rs.getString(6));
					userDeviceData.setFirstUsedTime(rs.getTimestamp(7));
					userDeviceData.setLastUsedTime(rs.getTimestamp(8));
					return userDeviceData;
				}
			});
			
			if (deviceList.isEmpty()) {
			  return null;
			} else if (deviceList.size() == 1) {
			  return deviceList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public UserDeviceData getUserDevice(long userId, String token, int platform) throws BmsSqlException {
		try {
			String sql = userDeviceQuery.getProperty("userCard.getUserDevice");
			Object[] params = new Object[] {userId, token, platform};
			List<UserDeviceData> deviceList = this.getTemplete().query(sql, params, new RowMapper<UserDeviceData>() {
				@Override
				public UserDeviceData mapRow(ResultSet rs, int index) throws SQLException {
					UserDeviceData userDeviceData = new UserDeviceData();
					userDeviceData.setId(rs.getLong(1));
					userDeviceData.setUserId(rs.getLong(2));
					userDeviceData.setName(rs.getString(3));
					userDeviceData.setToken(rs.getString(4));
					userDeviceData.setPlatform(rs.getInt(5));
					userDeviceData.setImeiNumber(rs.getString(6));
					userDeviceData.setFirstUsedTime(rs.getTimestamp(7));
					userDeviceData.setLastUsedTime(rs.getTimestamp(8));
					return userDeviceData;
				}
			});
			
			if (deviceList.isEmpty()) {
			  return null;
			} else if (deviceList.size() == 1) {
			  return deviceList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<UserDeviceData> getAllUserDevices() throws BmsSqlException {
		try {
			String sql = userDeviceQuery.getProperty("userCard.getAllUserDevices");
			List<UserDeviceData> deviceList = this.getTemplete().query(sql, new RowMapper<UserDeviceData>() {
				@Override
				public UserDeviceData mapRow(ResultSet rs, int index) throws SQLException {
					UserDeviceData userDeviceData = new UserDeviceData();
					userDeviceData.setId(rs.getLong(1));
					userDeviceData.setUserId(rs.getLong(2));
					userDeviceData.setName(rs.getString(3));
					userDeviceData.setToken(rs.getString(4));
					userDeviceData.setPlatform(rs.getInt(5));
					userDeviceData.setImeiNumber(rs.getString(6));
					userDeviceData.setFirstUsedTime(rs.getTimestamp(7));
					userDeviceData.setLastUsedTime(rs.getTimestamp(8));
					return userDeviceData;
				}
			});
			return deviceList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
