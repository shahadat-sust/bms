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
import com.bms.service.data.PhoneNumberData;

@Repository("phoneNumberDao")
public class PhoneNumberDao extends BaseDao implements IPhoneNumberDao {

	@Override
	public long create(long userId, long providerId, PhoneNumberData phoneNumberData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO PhoneNumber ")
		.append("( ")
			.append("Type, ")
			.append("Number, ")
			.append("Code, ")
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
			.append("?, ")
			.append("?, ")
			.append("? ")
		.append(")");
		KeyHolder holder = new GeneratedKeyHolder();
		this.getTemplete().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { "Id" });
				ps.setInt(1, phoneNumberData.getType());
				ps.setString(2, phoneNumberData.getNumber());
				ps.setString(3, phoneNumberData.getCode());
				ps.setBoolean(4, phoneNumberData.isVerified());
				ps.setBoolean(5, phoneNumberData.isPrimary());
				ps.setInt(6, phoneNumberData.getStatus());
				ps.setLong(7, phoneNumberData.getCreatedBy());
				ps.setDate(8, new java.sql.Date(phoneNumberData.getCreatedOn().getTime()));
				ps.setLong(9, phoneNumberData.getUpdatedBy());
				ps.setDate(10, new java.sql.Date(phoneNumberData.getUpdatedOn().getTime()));
				return ps;
			}
		}, holder);
		long phoneNumberId = holder.getKey().longValue();
		
		if(userId > 0) {
			StringBuilder sql1 = new StringBuilder()
			.append("INSERT INTO UserPhoneNumber ")
			.append("( ")
				.append("UserId, ")
				.append("PhoneNumberId, ")
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
					ps.setLong(2, phoneNumberId);
					ps.setLong(3, phoneNumberData.getCreatedBy());
					ps.setDate(4, new java.sql.Date(phoneNumberData.getCreatedOn().getTime()));
					ps.setLong(5, phoneNumberData.getUpdatedBy());
					ps.setDate(6, new java.sql.Date(phoneNumberData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder1);
		} else if(providerId > 0) {
			StringBuilder sql1 = new StringBuilder()
			.append("INSERT INTO ProviderPhoneNumber ")
			.append("( ")
				.append("ProviderId, ")
				.append("PhoneNumberId, ")
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
					ps.setLong(2, phoneNumberId);
					ps.setLong(3, phoneNumberData.getCreatedBy());
					ps.setDate(4, new java.sql.Date(phoneNumberData.getCreatedOn().getTime()));
					ps.setLong(5, phoneNumberData.getUpdatedBy());
					ps.setDate(6, new java.sql.Date(phoneNumberData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder1);
		}
		
		return phoneNumberId;
	}

	@Override
	public boolean update(PhoneNumberData phoneNumberData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE PhoneNumber SET ")
			.append("Type = ?, ")
			.append("Number = ?, ")
			.append("Code = ?, ")
			.append("IsVerified = ?, ")
			.append("IsPrimary = ?, ")
			.append("Status = ?, ")
			.append("UpdatedBy = ?, ")
			.append("UpdatedOn = ? ")
		.append("WHERE ")
		.append("Id = ?");
		return this.getTemplete().update(sql.toString(),  
				phoneNumberData.getType(),
				phoneNumberData.getNumber(),
				phoneNumberData.getCode(),
				phoneNumberData.isVerified(),
				phoneNumberData.isPrimary(),
				phoneNumberData.getStatus(),
				phoneNumberData.getUpdatedBy(),
				phoneNumberData.getUpdatedOn(),
				phoneNumberData.getId()) == 1;
	}

	@Override
	public boolean delete(long phoneNumberID) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM PhoneNumber WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), phoneNumberID) == 1;
	}

	@Override
	public PhoneNumberData getPhoneNumberById(long phoneNumberId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Type, ")
			.append("Number, ")
			.append("Code, ")
			.append("IsVerified, ")
			.append("IsPrimary, ")
			.append("Status ")
		.append("FROM PhoneNumber ")
		.append("WHERE ")
		.append("Id = ?");
		
		Object[] params = new Object[] {phoneNumberId};
		List<PhoneNumberData> phoneNumberList = this.getTemplete().query(sql.toString(), params, new RowMapper<PhoneNumberData>() {
			@Override
			public PhoneNumberData mapRow(ResultSet rs, int index) throws SQLException {
				PhoneNumberData phoneNumberData = new PhoneNumberData();
				phoneNumberData.setId(rs.getLong(1));
				phoneNumberData.setType(rs.getInt(2));
				phoneNumberData.setNumber(rs.getString(3));
				phoneNumberData.setCode(rs.getString(4));
				phoneNumberData.setVerified(rs.getBoolean(5));
				phoneNumberData.setPrimary(rs.getBoolean(6));
				phoneNumberData.setStatus(rs.getInt(7));
				return phoneNumberData;
			}
		});
		
		if (phoneNumberList.isEmpty()) {
		  return null;
		} else if (phoneNumberList.size() == 1) {
		  return phoneNumberList.get(0);
		} else {
		  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
		}
	}

	@Override
	public List<PhoneNumberData> getAllPhoneNumbers() throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("Type, ")
			.append("Number, ")
			.append("Code, ")
			.append("IsVerified, ")
			.append("IsPrimary, ")
			.append("Status ")
		.append("FROM PhoneNumber");
		
		List<PhoneNumberData> phoneNumberList = this.getTemplete().query(sql.toString(), new RowMapper<PhoneNumberData>() {
			@Override
			public PhoneNumberData mapRow(ResultSet rs, int index) throws SQLException {
				PhoneNumberData phoneNumberData = new PhoneNumberData();
				phoneNumberData.setId(rs.getLong(1));
				phoneNumberData.setType(rs.getInt(2));
				phoneNumberData.setNumber(rs.getString(3));
				phoneNumberData.setCode(rs.getString(4));
				phoneNumberData.setVerified(rs.getBoolean(5));
				phoneNumberData.setPrimary(rs.getBoolean(6));
				phoneNumberData.setStatus(rs.getInt(7));
				return phoneNumberData;
			}
		});
		return phoneNumberList;
	}

}
