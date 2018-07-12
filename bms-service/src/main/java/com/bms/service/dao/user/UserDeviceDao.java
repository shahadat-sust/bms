package com.bms.service.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.user.UserDeviceData;

public class UserDeviceDao extends BaseDao implements IUserDeviceDao {

	@Override
	public long create(UserDeviceData userDeviceData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO device ")
		.append("( ")
			.append("UserID, ")
			.append("Name, ")
			.append("Token, ")
			.append("Platform, ")
			.append("ImeiNumber, ")
			.append("FirstUsedTime, ")
			.append("LastUsedTime, ")
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
			.append("?, ")
			.append("?, ")
			.append("?, ")
			.append("? ")
		.append(")");
		KeyHolder holder = new GeneratedKeyHolder();
		this.getTemplete().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { "ID" });
				ps.setLong(1, userDeviceData.getUserId());
				ps.setString(2, userDeviceData.getName());
				ps.setString(3, userDeviceData.getToken());
				ps.setInt(4, userDeviceData.getPlatform());
				ps.setString(5, userDeviceData.getImeiNumber());
				ps.setDate(6, userDeviceData.getFirstUsedTime() != null ? new java.sql.Date(userDeviceData.getFirstUsedTime().getTime()) : null);
				ps.setDate(7, userDeviceData.getLastUsedTime() != null ? new java.sql.Date(userDeviceData.getLastUsedTime().getTime()) : null);
				ps.setLong(8, userDeviceData.getCreatedBy());
				ps.setDate(9, new java.sql.Date(userDeviceData.getCreatedOn().getTime()));
				ps.setLong(10, userDeviceData.getUpdatedBy());
				ps.setDate(11, new java.sql.Date(userDeviceData.getUpdatedOn().getTime()));
				return ps;
			}
		}, holder);
		return holder.getKey().longValue();
	}

	@Override
	public boolean update(UserDeviceData userDeviceData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE device SET ")
			.append("UserID = ?, ")
			.append("Name = ?, ")
			.append("Token = ?, ")
			.append("Platform = ?, ")
			.append("ImeiNumber = ?, ")
			.append("FirstUsedTime = ?, ")
			.append("LastUsedTime = ?, ")
			.append("UpdatedBy = ?, ")
			.append("UpdatedOn = ? ")
		.append("WHERE ")
		.append("ID = ?");
		return this.getTemplete().update(sql.toString(), 
				userDeviceData.getUserId(), 
				userDeviceData.getName(),
				userDeviceData.getToken(),
				userDeviceData.getPlatform(),
				userDeviceData.getImeiNumber(),
				userDeviceData.getFirstUsedTime(),
				userDeviceData.getLastUsedTime(),
				userDeviceData.getUpdatedBy(),
				userDeviceData.getUpdatedOn(),
				userDeviceData.getId()) == 1;
	}

	@Override
	public boolean delete(long deviceID) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM device WHERE ID = ?");

		return this.getTemplete().update(sql.toString(), deviceID) == 1;
	}

	@Override
	public UserDeviceData getDeviceByID(long deviceId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("ID, ")
			.append("UserID, ")
			.append("Name, ")
			.append("Token, ")
			.append("Platform, ")
			.append("ImeiNumber, ")
			.append("FirstUsedTime, ")
			.append("LastUsedTime ")
		.append("FROM device ")
		.append("WHERE ")
		.append("ID = ?");
		
		Object[] params = new Object[] {deviceId};
		List<UserDeviceData> deviceList = this.getTemplete().query(sql.toString(), params, new RowMapper<UserDeviceData>() {
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
	}
	
	@Override
	public UserDeviceData getDevice(long userId, String token, int platform) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("ID, ")
			.append("UserID, ")
			.append("Name, ")
			.append("Token, ")
			.append("Platform, ")
			.append("ImeiNumber, ")
			.append("FirstUsedTime, ")
			.append("LastUsedTime ")
		.append("FROM device ")
		.append("WHERE ")
		.append("UserID = ? AND ")
		.append("Token = ? AND ")
		.append("Platform = ? ");
		
		Object[] params = new Object[] {userId, token, platform};
		List<UserDeviceData> deviceList = this.getTemplete().query(sql.toString(), params, new RowMapper<UserDeviceData>() {
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
	}

	@Override
	public List<UserDeviceData> getAllDevices() throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("ID, ")
			.append("UserID, ")
			.append("Name, ")
			.append("Token, ")
			.append("Platform, ")
			.append("ImeiNumber, ")
			.append("FirstUsedTime, ")
			.append("LastUsedTime ")
		.append("FROM device");
		
		List<UserDeviceData> deviceList = this.getTemplete().query(sql.toString(), new RowMapper<UserDeviceData>() {
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
	}

}
