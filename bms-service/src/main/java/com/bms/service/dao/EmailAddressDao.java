package com.bms.service.dao;

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
import com.bms.service.data.EmailAddressData;

@Repository("emailAddressDao")
public class EmailAddressDao extends BaseDao implements IEmailAddressDao {

	@Autowired
	@Qualifier("emailAddressQuery")
	private Properties emailAddressQuery;
	
	@Override
	public long create(long userId, long providerId, EmailAddressData emailAddressData) throws BmsSqlException {
		try {
			String sql = emailAddressQuery.getProperty("emailAddress.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setString(1, emailAddressData.getEmail());
					ps.setBoolean(2, emailAddressData.isVerified());
					ps.setBoolean(3, emailAddressData.isPrimary());
					ps.setInt(4, emailAddressData.getStatus());
					ps.setLong(5, emailAddressData.getCreatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(emailAddressData.getCreatedOn().getTime()));
					ps.setLong(7, emailAddressData.getUpdatedBy());
					ps.setTimestamp(8, new java.sql.Timestamp(emailAddressData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			long emailAddressId = holder.getKey().longValue();
			
			if(userId > 0) {
				String sql1 = emailAddressQuery.getProperty("userEmailAddress.create");
				KeyHolder holder1 = new GeneratedKeyHolder();
				this.getTemplete().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
						ps.setLong(1, userId);
						ps.setLong(2, emailAddressId);
						ps.setLong(3, emailAddressData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(emailAddressData.getCreatedOn().getTime()));
						ps.setLong(5, emailAddressData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(emailAddressData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			} else if(providerId > 0) {
				String sql1 = emailAddressQuery.getProperty("providerEmailAddress.create");
				KeyHolder holder1 = new GeneratedKeyHolder();
				this.getTemplete().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
						ps.setLong(1, providerId);
						ps.setLong(2, emailAddressId);
						ps.setLong(3, emailAddressData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(emailAddressData.getCreatedOn().getTime()));
						ps.setLong(5, emailAddressData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(emailAddressData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			}
			
			return emailAddressId;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(EmailAddressData emailAddressData) throws BmsSqlException {
		try {
			String sql = emailAddressQuery.getProperty("emailAddress.update");
			return this.getTemplete().update(sql, 
					emailAddressData.getEmail(),
					emailAddressData.isVerified(),
					emailAddressData.isPrimary(),
					emailAddressData.getStatus(),
					emailAddressData.getUpdatedBy(),
					new java.sql.Timestamp(emailAddressData.getUpdatedOn().getTime()),
					emailAddressData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long emailAddressId) throws BmsSqlException {
		try {
			String sql = emailAddressQuery.getProperty("emailAddress.delete");
			return this.getTemplete().update(sql, emailAddressId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public EmailAddressData getEmailAddressById(long emailAddressId) throws BmsSqlException {
		try {
			String sql = emailAddressQuery.getProperty("emailAddress.getEmailAddressById");
			Object[] params = new Object[] {emailAddressId};
			List<EmailAddressData> emailAddressList = this.getTemplete().query(sql, params, new RowMapper<EmailAddressData>() {
				@Override
				public EmailAddressData mapRow(ResultSet rs, int index) throws SQLException {
					EmailAddressData emailAddressData = new EmailAddressData();
					emailAddressData.setId(rs.getLong(1));
					emailAddressData.setEmail(rs.getString(2));
					emailAddressData.setVerified(rs.getBoolean(3));
					emailAddressData.setPrimary(rs.getBoolean(4));
					emailAddressData.setStatus(rs.getInt(5));
					return emailAddressData;
				}
			});
			
			if (emailAddressList.isEmpty()) {
			  return null;
			} else if (emailAddressList.size() == 1) {
			  return emailAddressList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public EmailAddressData getEmailAddressByEmail(String email) throws BmsSqlException {
		try {
			String sql = emailAddressQuery.getProperty("emailAddress.getEmailAddressByEmail");
			Object[] params = new Object[] {email};
			List<EmailAddressData> emailAddressList = this.getTemplete().query(sql.toString(), params, new RowMapper<EmailAddressData>() {
				@Override
				public EmailAddressData mapRow(ResultSet rs, int index) throws SQLException {
					EmailAddressData emailAddressData = new EmailAddressData();
					emailAddressData.setId(rs.getLong(1));
					emailAddressData.setEmail(rs.getString(2));
					emailAddressData.setVerified(rs.getBoolean(3));
					emailAddressData.setPrimary(rs.getBoolean(4));
					emailAddressData.setStatus(rs.getInt(5));
					return emailAddressData;
				}
			});
			
			if (emailAddressList.isEmpty()) {
			  return null;
			} else if (emailAddressList.size() == 1) {
			  return emailAddressList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public List<EmailAddressData> getAllEmailAddressesByUserId(long userId) throws BmsSqlException {
		try {
			String sql = emailAddressQuery.getProperty("emailAddress.getAllEmailAddressesByUserId");
			Object[] params = new Object[] {userId};
			List<EmailAddressData> emailAddressList = this.getTemplete().query(sql, params, new RowMapper<EmailAddressData>() {
				@Override
				public EmailAddressData mapRow(ResultSet rs, int index) throws SQLException {
					EmailAddressData emailAddressData = new EmailAddressData();
					emailAddressData.setId(rs.getLong(1));
					emailAddressData.setEmail(rs.getString(2));
					emailAddressData.setVerified(rs.getBoolean(3));
					emailAddressData.setPrimary(rs.getBoolean(4));
					emailAddressData.setStatus(rs.getInt(5));
					return emailAddressData;
				}
			});
			return emailAddressList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public List<EmailAddressData> getAllEmailAddressesByProviderId(long providerId) throws BmsSqlException {
		try {
			String sql = emailAddressQuery.getProperty("emailAddress.getAllEmailAddressesByProviderId");
			Object[] params = new Object[] {providerId};
			List<EmailAddressData> emailAddressList = this.getTemplete().query(sql, params, new RowMapper<EmailAddressData>() {
				@Override
				public EmailAddressData mapRow(ResultSet rs, int index) throws SQLException {
					EmailAddressData emailAddressData = new EmailAddressData();
					emailAddressData.setId(rs.getLong(1));
					emailAddressData.setEmail(rs.getString(2));
					emailAddressData.setVerified(rs.getBoolean(3));
					emailAddressData.setPrimary(rs.getBoolean(4));
					emailAddressData.setStatus(rs.getInt(5));
					return emailAddressData;
				}
			});
			return emailAddressList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
