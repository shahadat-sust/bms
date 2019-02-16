package com.bms.service.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.SqlConstants;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.user.UserSocialAccountData;

@Repository("userSocialAccountDao")
public class UserSocialAccountDao extends BaseDao implements IUserSocialAccountDao {

	@Autowired
	@Qualifier("userSocialAccountQuery")
	private Properties userSocialAccountQuery;
	
	@Override
	public long create(UserSocialAccountData userSocialAccountData) throws BmsSqlException {
		try {
			String sql = userSocialAccountQuery.getProperty("userSocialAccount.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, userSocialAccountData.getUserId());
					ps.setInt(2, userSocialAccountData.getType());
					ps.setString(3, userSocialAccountData.getAccountId());
					ps.setBoolean(4, userSocialAccountData.isVerified());
					ps.setInt(5, userSocialAccountData.getStatus());
					ps.setLong(6, userSocialAccountData.getCreatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(userSocialAccountData.getCreatedOn().getTime()));
					ps.setLong(8, userSocialAccountData.getUpdatedBy());
					ps.setTimestamp(9, new java.sql.Timestamp(userSocialAccountData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(UserSocialAccountData userSocialAccountData) throws BmsSqlException {
		try {
			String sql = userSocialAccountQuery.getProperty("userSocialAccount.update");
			return this.getTemplete().update(sql, 
					userSocialAccountData.getUserId(), 
					userSocialAccountData.getType(),
					userSocialAccountData.getAccountId(),
					userSocialAccountData.isVerified(),
					userSocialAccountData.getStatus(), 
					userSocialAccountData.getUpdatedBy(),
					new java.sql.Timestamp(userSocialAccountData.getUpdatedOn().getTime()),
					userSocialAccountData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long userSocialAccountId) throws BmsSqlException {
		try {
			String sql = userSocialAccountQuery.getProperty("userSocialAccount.delete");
			return this.getTemplete().update(sql, userSocialAccountId) == 1;
		} catch (DataIntegrityViolationException e) {
			throw new BmsSqlException(SqlConstants.ERROR_DELETE_FOREIGN_KEY_CONSTRAINT_FAIL, e);
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public UserSocialAccountData getUserSocialAccountById(long userSocialAccountId) throws BmsSqlException {
		try {
			String sql = userSocialAccountQuery.getProperty("userSocialAccount.getUserSocialAccountById");
			Object[] params = new Object[] {userSocialAccountId};
			List<UserSocialAccountData> socialAccountList = this.getTemplete().query(sql, params, new RowMapper<UserSocialAccountData>() {
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
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	public UserSocialAccountData getUserSocialAccountByTypeAccountId(int type, String accountId) throws BmsSqlException {
		try {
			String sql = userSocialAccountQuery.getProperty("userSocialAccount.getUserSocialAccountByTypeAccountId");
			Object[] params = new Object[] {type, accountId};
			List<UserSocialAccountData> userSocialAccountList = this.getTemplete().query(sql, params, new RowMapper<UserSocialAccountData>() {
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
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public List<UserSocialAccountData> getAllUserSocialAccountsByUserId(long userId) throws BmsSqlException {
		try {
			String sql = userSocialAccountQuery.getProperty("userSocialAccount.getAllUserSocialAccountsByUserId");
			Object[] params = new Object[] {userId};
			List<UserSocialAccountData> userSocialAccountList = this.getTemplete().query(sql, params, new RowMapper<UserSocialAccountData>() {
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
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
