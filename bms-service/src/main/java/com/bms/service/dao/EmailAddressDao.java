package com.bms.service.dao;

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
import com.bms.service.data.EmailAddressData;

@Repository("emailAddressDao")
public class EmailAddressDao extends BaseDao implements IEmailAddressDao {

	@Override
	public long create(long userId, long providerId, EmailAddressData emailAddressData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO EmailAddress ")
		.append("( ")
			.append("Email, ")
			.append("IsVerified, ")
			.append("IsPrimary, ")
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
				ps.setString(1, emailAddressData.getEmail());
				ps.setBoolean(2, emailAddressData.isVerified());
				ps.setBoolean(3, emailAddressData.isPrimary());
				ps.setInt(4, emailAddressData.getStatus());
				ps.setLong(5, emailAddressData.getCreatedBy());
				ps.setDate(6, new java.sql.Date(emailAddressData.getCreatedOn().getTime()));
				ps.setLong(7, emailAddressData.getUpdatedBy());
				ps.setDate(8, new java.sql.Date(emailAddressData.getUpdatedOn().getTime()));
				return ps;
			}
		}, holder);
		long emailAddressId = holder.getKey().longValue();
		
		if(userId > 0) {
			StringBuilder sql1 = new StringBuilder()
			.append("INSERT INTO UserEmailAddress ")
			.append("( ")
				.append("UserId, ")
				.append("EmailAddressId, ")
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
				.append("? ")
			.append(")");
			KeyHolder holder1 = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql1.toString(), new String[] { "Id" });
					ps.setLong(1, userId);
					ps.setLong(2, emailAddressId);
					ps.setLong(3, emailAddressData.getCreatedBy());
					ps.setDate(4, new java.sql.Date(emailAddressData.getCreatedOn().getTime()));
					ps.setLong(5, emailAddressData.getUpdatedBy());
					ps.setDate(6, new java.sql.Date(emailAddressData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder1);
		} else if(providerId > 0) {
			StringBuilder sql1 = new StringBuilder()
			.append("INSERT INTO ProviderEmailAddress ")
			.append("( ")
				.append("ProviderId, ")
				.append("EmailAddressId, ")
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
				.append("? ")
			.append(")");
			KeyHolder holder1 = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql1.toString(), new String[] { "Id" });
					ps.setLong(1, providerId);
					ps.setLong(2, emailAddressId);
					ps.setLong(3, emailAddressData.getCreatedBy());
					ps.setDate(4, new java.sql.Date(emailAddressData.getCreatedOn().getTime()));
					ps.setLong(5, emailAddressData.getUpdatedBy());
					ps.setDate(6, new java.sql.Date(emailAddressData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder1);
		}
		
		return emailAddressId;
	}

	@Override
	public boolean update(EmailAddressData emailAddressData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE EmailAddress SET ")
			.append("Email = ?, ")
			.append("IsVerified = ?, ")
			.append("IsPrimary = ?, ")
			.append("Status = ?, ")
			.append("UpdatedBy = ?, ")
			.append("UpdatedOn = ? ")
		.append("WHERE ")
		.append("Id = ?");
		return this.getTemplete().update(sql.toString(), 
				emailAddressData.getEmail(),
				emailAddressData.isVerified(),
				emailAddressData.isPrimary(),
				emailAddressData.getStatus(),
				emailAddressData.getUpdatedBy(),
				emailAddressData.getUpdatedOn(),
				emailAddressData.getId()) == 1;
	}

	@Override
	public boolean delete(long emailAddressId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM EmailAddress WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), emailAddressId) == 1;
	}

	@Override
	public EmailAddressData getEmailAddressById(long emailAddressId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Email, ")
			.append("IsVerified, ")
			.append("IsPrimary, ")
			.append("Status ")
		.append("FROM EmailAddress ")
		.append("WHERE ")
		.append("Id = ?");
		
		Object[] params = new Object[] {emailAddressId};
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
	}

	@Override
	public EmailAddressData getEmailAddressByEmail(String email) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Email, ")
				.append("IsVerified, ")
				.append("IsPrimary, ")
				.append("Status ")
			.append("FROM EmailAddress ")
			.append("WHERE ")
			.append("Email = ?");
				
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
	}
	
	@Override
	public List<EmailAddressData> getAllEmailAddresses() throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Email, ")
			.append("IsVerified, ")
			.append("IsPrimary, ")
			.append("Status ")
		.append("FROM EmailAddress");
		
		List<EmailAddressData> emailAddressList = this.getTemplete().query(sql.toString(), new RowMapper<EmailAddressData>() {
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
	}

}
