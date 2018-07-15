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
import com.bms.service.data.user.UserSocialAccountData;

@Repository("userSocialAccountDao")
public class UserSocialAccountDao extends BaseDao implements IUserSocialAccountDao {

	@Override
	public long create(UserSocialAccountData userSocialAccountData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO UserSocialAccount ")
		.append("( ")
			.append("UserId, ")
			.append("Type, ")
			.append("AccountId, ")
			.append("IsVerified, ")
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
			.append("?, ")
			.append("? ")
		.append(")");
		KeyHolder holder = new GeneratedKeyHolder();
		this.getTemplete().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { "Id" });
				ps.setLong(1, userSocialAccountData.getUserId());
				ps.setInt(2, userSocialAccountData.getType());
				ps.setString(3, userSocialAccountData.getAccountId());
				ps.setBoolean(4, userSocialAccountData.isVerified());
				ps.setInt(5, userSocialAccountData.getStatus());
				ps.setLong(6, userSocialAccountData.getCreatedBy());
				ps.setDate(7, new java.sql.Date(userSocialAccountData.getCreatedOn().getTime()));
				ps.setLong(8, userSocialAccountData.getUpdatedBy());
				ps.setDate(9, new java.sql.Date(userSocialAccountData.getUpdatedOn().getTime()));
				return ps;
			}
		}, holder);
		return holder.getKey().longValue();
	}

	@Override
	public boolean update(UserSocialAccountData userSocialAccountData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE UserSocialAccount SET ")
			.append("UserId = ?, ")
			.append("Type = ?, ")
			.append("AccountId = ?, ")
			.append("IsVerified = ?, ")
			.append("Status = ?, ")
			.append("UpdatedBy = ?, ")
			.append("UpdatedOn = ? ")
		.append("WHERE ")
		.append("Id = ?");
		return this.getTemplete().update(sql.toString(), 
				userSocialAccountData.getUserId(), 
				userSocialAccountData.getType(),
				userSocialAccountData.getAccountId(),
				userSocialAccountData.isVerified(),
				userSocialAccountData.getStatus(), 
				userSocialAccountData.getUpdatedBy(),
				userSocialAccountData.getUpdatedOn(),
				userSocialAccountData.getId()) == 1;
	}

	@Override
	public boolean delete(long userSocialAccountId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM socialaccount WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), userSocialAccountId) == 1;
	}

	@Override
	public UserSocialAccountData getUserSocialAccountByID(long userSocialAccountId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("UserId, ")
			.append("Type, ")
			.append("AccountId, ")
			.append("IsVerified, ")
			.append("Status ")
		.append("FROM UserSocialAccount ")
		.append("WHERE ")
		.append("Id = ?");
		
		Object[] params = new Object[] {userSocialAccountId};
		List<UserSocialAccountData> socialAccountList = this.getTemplete().query(sql.toString(), params, new RowMapper<UserSocialAccountData>() {
			@Override
			public UserSocialAccountData mapRow(ResultSet rs, int index) throws SQLException {
				UserSocialAccountData userSocialAccountData = new UserSocialAccountData();
				userSocialAccountData.setId(rs.getLong(1));
				userSocialAccountData.setUserId(rs.getLong(2));
				userSocialAccountData.setType(rs.getInt(3));
				userSocialAccountData.setAccountId(rs.getString(4));
				userSocialAccountData.setVerified(rs.getBoolean(5));
				userSocialAccountData.setStatus(rs.getInt(6));
				return userSocialAccountData;
			}
		});
		
		if (socialAccountList.isEmpty()) {
		  return null;
		} else if (socialAccountList.size() == 1) {
		  return socialAccountList.get(0);
		} else {
		  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
		}
	}

	public UserSocialAccountData getUserSocialAccountByTypeAccountId(int type, String accountId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("UserId, ")
			.append("Type, ")
			.append("AccountId, ")
			.append("IsVerified, ")
			.append("Status ")
		.append("FROM UserSocialAccount ")
		.append("WHERE ")
		.append("Type = ? AND ")
		.append("AccountId = ?");
		
		Object[] params = new Object[] {type, accountId};
		List<UserSocialAccountData> userSocialAccountList = this.getTemplete().query(sql.toString(), params, new RowMapper<UserSocialAccountData>() {
			@Override
			public UserSocialAccountData mapRow(ResultSet rs, int index) throws SQLException {
				UserSocialAccountData userSocialAccountData = new UserSocialAccountData();
				userSocialAccountData.setId(rs.getLong(1));
				userSocialAccountData.setUserId(rs.getLong(2));
				userSocialAccountData.setType(rs.getInt(3));
				userSocialAccountData.setAccountId(rs.getString(4));
				userSocialAccountData.setVerified(rs.getBoolean(5));
				userSocialAccountData.setStatus(rs.getInt(6));
				return userSocialAccountData;
			}
		});
		
		if (userSocialAccountList.isEmpty()) {
		  return null;
		} else if (userSocialAccountList.size() == 1) {
		  return userSocialAccountList.get(0);
		} else {
		  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
		}
	}
	
	@Override
	public List<UserSocialAccountData> getAllUserSocialAccountsByUserId(long userId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("UserId, ")
			.append("Type, ")
			.append("AccountId, ")
			.append("IsVerified, ")
			.append("Status ")
		.append("FROM UserSocialAccount ")
		.append("WHERE ")
		.append("UserId = ?");
		
		Object[] params = new Object[] {userId};
		List<UserSocialAccountData> userSocialAccountList = this.getTemplete().query(sql.toString(), params, new RowMapper<UserSocialAccountData>() {
			@Override
			public UserSocialAccountData mapRow(ResultSet rs, int index) throws SQLException {
				UserSocialAccountData userSocialAccountData = new UserSocialAccountData();
				userSocialAccountData.setId(rs.getLong(1));
				userSocialAccountData.setUserId(rs.getLong(2));
				userSocialAccountData.setType(rs.getInt(3));
				userSocialAccountData.setAccountId(rs.getString(4));
				userSocialAccountData.setVerified(rs.getBoolean(5));
				userSocialAccountData.setStatus(rs.getInt(6));
				return userSocialAccountData;
			}
		});
		return userSocialAccountList;
	}

}
