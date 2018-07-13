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
import com.bms.service.data.user.UserData;

@Repository("userDao")
public class UserDao extends BaseDao implements IUserDao {

	@Override
	public long create(UserData userData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO User ")
		.append("( ")
			.append("Username, ")
			.append("Password, ")
			.append("IsActive, ")
			.append("Status, ")
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
		KeyHolder holder = new GeneratedKeyHolder();
		this.getTemplete().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { "Id" });
				ps.setString(1, userData.getUsername());
				ps.setString(2, userData.getPassword());
				ps.setBoolean(3, userData.isActive());
				ps.setInt(4, userData.getStatus());
				ps.setLong(5, userData.getCreatedBy() > 0 ? userData.getCreatedBy() : null);
				ps.setDate(6, new java.sql.Date(userData.getCreatedOn().getTime()));
				ps.setLong(7, userData.getUpdatedBy() > 0 ? userData.getUpdatedBy() : null);
				ps.setDate(8, new java.sql.Date(userData.getUpdatedOn().getTime()));
				return ps;
			}
		}, holder);
		return holder.getKey().longValue();
	}

	@Override
	public boolean update(UserData userData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE User SET ")
			.append("Username = ?, ")
			.append("Password = ?, ")
			.append("IsActive = ?, ")
			.append("Status = ?, ")
			.append("UpdatedBy = ?, ")
			.append("UpdatedOn = ? ")
		.append("WHERE ")
		.append("Id = ?");
		return this.getTemplete().update(sql.toString(), 
				userData.getUsername(), 
				userData.getPassword(),
				userData.isActive() ? 1 : 0,
				userData.getStatus(),
				userData.getUpdatedBy(),
				new java.sql.Date(userData.getUpdatedOn().getTime()),
				userData.getId()) == 1;
	}

	@Override
	public boolean delete(long userId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM User WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), userId) == 1;
	}

	@Override
	public UserData getUserByID(long userId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Username, ")
			.append("Password, ")
			.append("IsActive, ")
			.append("Status ")
		.append("FROM User ")
		.append("WHERE ")
		.append("Id = ?");
		
		Object[] params = new Object[] {userId};
		List<UserData> userList = this.getTemplete().query(sql.toString(), params, new RowMapper<UserData>() {
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
	}

	@Override
	public List<UserData> getAllUsers() throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Username, ")
			.append("Password, ")
			.append("IsActive, ")
			.append("Status ")
		.append("FROM User");
		
		List<UserData> userList = this.getTemplete().query(sql.toString(), new RowMapper<UserData>() {
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
		return userList;
	}
	
}
