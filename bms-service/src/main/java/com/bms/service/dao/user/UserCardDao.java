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
import com.bms.service.data.user.UserCardData;

@Repository("userCardDao")
public class UserCardDao extends BaseDao implements IUserCardDao {

	@Override
	public long create(UserCardData userCardData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO UserCard ")
		.append("( ")
			.append("UserId, ")
			.append("CardNumber, ")
			.append("HolderName, ")
			.append("CvvNumber, ")
			.append("ExpireDate, ")
			.append("Status, ")
			.append("IsDefault, ")
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
				ps.setLong(1, userCardData.getUserId());
				ps.setString(2, userCardData.getCardNumber());
				ps.setString(3, userCardData.getHolderName());
				ps.setString(4, userCardData.getCvvNumber());
				ps.setDate(5, new java.sql.Date(userCardData.getExpireDate().getTime()));
				ps.setInt(6, userCardData.getStatus());
				ps.setBoolean(7, userCardData.isDefault());
				ps.setLong(8, userCardData.getCreatedBy());
				ps.setDate(9, new java.sql.Date(userCardData.getCreatedOn().getTime()));
				ps.setLong(10, userCardData.getUpdatedBy());
				ps.setDate(11, new java.sql.Date(userCardData.getUpdatedOn().getTime()));
				return ps;
			}
		}, holder);
		return holder.getKey().longValue();
	}

	@Override
	public boolean update(UserCardData userCardData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE UserCard SET ")
			.append("CardNumber = ?, ")
			.append("HolderName = ?, ")
			.append("CvvNumber = ?, ")
			.append("ExpireDate = ?, ")
			.append("Status = ?, ")
			.append("IsDefault = ?, ")
			.append("UpdatedBy = ?, ")
			.append("UpdatedOn = ? ")
		.append("WHERE ")
		.append("Id = ?")
		.append("UserId = ?");
		return this.getTemplete().update(sql.toString(), 
				userCardData.getCardNumber(),
				userCardData.getHolderName(),
				userCardData.getCvvNumber(),
				new java.sql.Date(userCardData.getExpireDate().getTime()),
				userCardData.getStatus(),
				userCardData.isDefault(),
				userCardData.getUpdatedBy(),
				userCardData.getUpdatedOn(),
				userCardData.getId(),
				userCardData.getUserId()) == 1;
	}

	@Override
	public boolean delete(long userCardId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM UserCard WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), userCardId) == 1;
	}

	@Override
	public UserCardData getUserCardById(long userCardId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("UserId, ")
			.append("CardNumber, ")
			.append("HolderName, ")
			.append("CvvNumber, ")
			.append("ExpireDate, ")
			.append("Status, ")
			.append("IsDefault ")
		.append("FROM UserCard ")
		.append("WHERE ")
		.append("Id = ?");
		
		Object[] params = new Object[] {userCardId};
		List<UserCardData> userCardList = this.getTemplete().query(sql.toString(), params, new RowMapper<UserCardData>() {
			@Override
			public UserCardData mapRow(ResultSet rs, int index) throws SQLException {
				UserCardData userDeviceData = new UserCardData();
				userDeviceData.setId(rs.getLong(1));
				userDeviceData.setUserId(rs.getLong(2));
				userDeviceData.setCardNumber(rs.getString(3));
				userDeviceData.setHolderName(rs.getString(4));
				userDeviceData.setCvvNumber(rs.getString(5));
				userDeviceData.setExpireDate(rs.getTimestamp(6));
				userDeviceData.setStatus(rs.getInt(7));
				userDeviceData.setDefault(rs.getBoolean(8));
				return userDeviceData;
			}
		});
		
		if (userCardList.isEmpty()) {
		  return null;
		} else if (userCardList.size() == 1) {
		  return userCardList.get(0);
		} else {
		  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
		}
	}
	
	@Override
	public List<UserCardData> getAllUserCardsByUserId(long userId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("UserId, ")
			.append("CardNumber, ")
			.append("HolderName, ")
			.append("CvvNumber, ")
			.append("ExpireDate, ")
			.append("Status, ")
			.append("IsDefault ")
		.append("FROM UserCard")
		.append("WHERE ")
		.append("UserId = ?");
		
		Object[] params = new Object[] {userId};
		List<UserCardData> userCardList = this.getTemplete().query(sql.toString(), params, new RowMapper<UserCardData>() {
			@Override
			public UserCardData mapRow(ResultSet rs, int index) throws SQLException {
				UserCardData userDeviceData = new UserCardData();
				userDeviceData.setId(rs.getLong(1));
				userDeviceData.setUserId(rs.getLong(2));
				userDeviceData.setCardNumber(rs.getString(3));
				userDeviceData.setHolderName(rs.getString(4));
				userDeviceData.setCvvNumber(rs.getString(5));
				userDeviceData.setExpireDate(rs.getTimestamp(6));
				userDeviceData.setStatus(rs.getInt(7));
				userDeviceData.setDefault(rs.getBoolean(8));
				return userDeviceData;
			}
		});
		return userCardList;
	}

}
