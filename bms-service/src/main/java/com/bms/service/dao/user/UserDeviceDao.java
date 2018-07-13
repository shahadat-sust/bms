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
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.user.UserDeviceData;

@Repository("userDeviceDao")
public class UserDeviceDao extends BaseDao implements IUserDeviceDao {

	@Override
	public long create(UserDeviceData userDeviceData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO UserDevice ")
		.append("( ")
			.append("UserId, ")
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
				PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { "Id" });
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
		.append("UPDATE UserDevice SET ")
			.append("Name = ?, ")
			.append("Token = ?, ")
			.append("Platform = ?, ")
			.append("ImeiNumber = ?, ")
			.append("FirstUsedTime = ?, ")
			.append("LastUsedTime = ?, ")
			.append("UpdatedBy = ?, ")
			.append("UpdatedOn = ? ")
		.append("WHERE ")
		.append("Id = ?")
		.append("UserId = ?");
		return this.getTemplete().update(sql.toString(), 
				userDeviceData.getName(),
				userDeviceData.getToken(),
				userDeviceData.getPlatform(),
				userDeviceData.getImeiNumber(),
				userDeviceData.getFirstUsedTime(),
				userDeviceData.getLastUsedTime(),
				userDeviceData.getUpdatedBy(),
				userDeviceData.getUpdatedOn(),
				userDeviceData.getId(),
				userDeviceData.getUserId()) == 1;
	}

	@Override
	public boolean delete(long userDeviceId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM UserDevice WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), userDeviceId) == 1;
	}

	@Override
	public UserDeviceData getUserDeviceById(long userDeviceId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("UserId, ")
			.append("Name, ")
			.append("Token, ")
			.append("Platform, ")
			.append("ImeiNumber, ")
			.append("FirstUsedTime, ")
			.append("LastUsedTime ")
		.append("FROM UserDevice ")
		.append("WHERE ")
		.append("Id = ?");
		
		Object[] params = new Object[] {userDeviceId};
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
	public UserDeviceData getUserDevice(long userId, String token, int platform) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("UserId, ")
			.append("Name, ")
			.append("Token, ")
			.append("Platform, ")
			.append("ImeiNumber, ")
			.append("FirstUsedTime, ")
			.append("LastUsedTime ")
		.append("FROM UserDevice ")
		.append("WHERE ")
		.append("UserId = ? AND ")
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
	public List<UserDeviceData> getAllUserDevices() throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("UserId, ")
			.append("Name, ")
			.append("Token, ")
			.append("Platform, ")
			.append("ImeiNumber, ")
			.append("FirstUsedTime, ")
			.append("LastUsedTime ")
		.append("FROM UserDevice");
		
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
