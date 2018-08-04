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
import com.bms.service.data.user.UserCardData;

@Repository("userCardDao")
public class UserCardDao extends BaseDao implements IUserCardDao {

	@Autowired
	@Qualifier("userCardQuery")
	private Properties userCardQuery;
	
	@Override
	public long create(UserCardData userCardData) throws BmsSqlException {
		try {
			String sql = userCardQuery.getProperty("userCard.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, userCardData.getUserId());
					ps.setString(2, userCardData.getCardNumber());
					ps.setString(3, userCardData.getHolderName());
					ps.setString(4, userCardData.getCvvNumber());
					ps.setDate(5, new java.sql.Date(userCardData.getExpireDate().getTime()));
					ps.setInt(6, userCardData.getStatus());
					ps.setBoolean(7, userCardData.isDefault());
					ps.setLong(8, userCardData.getCreatedBy());
					ps.setTimestamp(9, new java.sql.Timestamp(userCardData.getCreatedOn().getTime()));
					ps.setLong(10, userCardData.getUpdatedBy());
					ps.setTimestamp(11, new java.sql.Timestamp(userCardData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(UserCardData userCardData) throws BmsSqlException {
		try {
			String sql = userCardQuery.getProperty("userCard.update");
			return this.getTemplete().update(sql, 
					userCardData.getCardNumber(),
					userCardData.getHolderName(),
					userCardData.getCvvNumber(),
					new java.sql.Date(userCardData.getExpireDate().getTime()),
					userCardData.getStatus(),
					userCardData.isDefault(),
					userCardData.getUpdatedBy(),
					new java.sql.Timestamp(userCardData.getUpdatedOn().getTime()),
					userCardData.getId(),
					userCardData.getUserId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long userCardId) throws BmsSqlException {
		try {
			String sql = userCardQuery.getProperty("userCard.delete");
			return this.getTemplete().update(sql.toString(), userCardId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public UserCardData getUserCardById(long userCardId) throws BmsSqlException {
		try {
			String sql = userCardQuery.getProperty("userCard.getUserCardById");
			Object[] params = new Object[] {userCardId};
			List<UserCardData> userCardList = this.getTemplete().query(sql, params, new RowMapper<UserCardData>() {
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
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public List<UserCardData> getAllUserCardsByUserId(long userId) throws BmsSqlException {
		try {
			String sql = userCardQuery.getProperty("userCard.getAllUserCardsByUserId");
			Object[] params = new Object[] {userId};
			List<UserCardData> userCardList = this.getTemplete().query(sql, params, new RowMapper<UserCardData>() {
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
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
