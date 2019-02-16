package com.bms.service.dao;

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
import com.bms.service.data.PhoneNumberData;

@Repository("phoneNumberDao")
public class PhoneNumberDao extends BaseDao implements IPhoneNumberDao {

	@Autowired
	@Qualifier("phoneNumberQuery")
	private Properties phoneNumberQuery;
	
	@Override
	public long create(long userId, long providerId, PhoneNumberData phoneNumberData) throws BmsSqlException {
		try {
			String sql = phoneNumberQuery.getProperty("phoneNumber.create");
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
					ps.setTimestamp(8, new java.sql.Timestamp(phoneNumberData.getCreatedOn().getTime()));
					ps.setLong(9, phoneNumberData.getUpdatedBy());
					ps.setTimestamp(10, new java.sql.Timestamp(phoneNumberData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			long phoneNumberId = holder.getKey().longValue();
			
			if(userId > 0) {
				String sql1 = phoneNumberQuery.getProperty("userPhoneNumber.create");
				KeyHolder holder1 = new GeneratedKeyHolder();
				this.getTemplete().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
						ps.setLong(1, userId);
						ps.setLong(2, phoneNumberId);
						ps.setLong(3, phoneNumberData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(phoneNumberData.getCreatedOn().getTime()));
						ps.setLong(5, phoneNumberData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(phoneNumberData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			} else if(providerId > 0) {
				String sql1 = phoneNumberQuery.getProperty("providerPhoneNumber.create");
				KeyHolder holder1 = new GeneratedKeyHolder();
				this.getTemplete().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
						ps.setLong(1, providerId);
						ps.setLong(2, phoneNumberId);
						ps.setLong(3, phoneNumberData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(phoneNumberData.getCreatedOn().getTime()));
						ps.setLong(5, phoneNumberData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(phoneNumberData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			}
			
			return phoneNumberId;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(PhoneNumberData phoneNumberData) throws BmsSqlException {
		try {
			String sql = phoneNumberQuery.getProperty("phoneNumber.update");
			return this.getTemplete().update(sql,  
					phoneNumberData.getType(),
					phoneNumberData.getNumber(),
					phoneNumberData.getCode(),
					phoneNumberData.isVerified(),
					phoneNumberData.isPrimary(),
					phoneNumberData.getStatus(),
					phoneNumberData.getUpdatedBy(),
					new java.sql.Timestamp(phoneNumberData.getUpdatedOn().getTime()),
					phoneNumberData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long phoneNumberId) throws BmsSqlException {
		try {
			String sql = phoneNumberQuery.getProperty("phoneNumber.delete");
			return this.getTemplete().update(sql, phoneNumberId) == 1;
		} catch (DataIntegrityViolationException e) {
			throw new BmsSqlException(SqlConstants.ERROR_DELETE_FOREIGN_KEY_CONSTRAINT_FAIL, e);
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public PhoneNumberData getPhoneNumberById(long phoneNumberId) throws BmsSqlException {
		try {
			String sql = phoneNumberQuery.getProperty("phoneNumber.getPhoneNumberById");
			Object[] params = new Object[] {phoneNumberId};
			List<PhoneNumberData> phoneNumberList = this.getTemplete().query(sql, params, new RowMapper<PhoneNumberData>() {
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
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<PhoneNumberData> getAllPhoneNumbersByUserId(long userId) throws BmsSqlException {
		try {
			String sql = phoneNumberQuery.getProperty("phoneNumber.getAllPhoneNumbersByUserId");
			Object[] params = new Object[] {userId};
			List<PhoneNumberData> phoneNumberList = this.getTemplete().query(sql, params, new RowMapper<PhoneNumberData>() {
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
					phoneNumberData.setUserId(rs.getLong(8));
					return phoneNumberData;
				}
			});
			return phoneNumberList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public List<PhoneNumberData> getAllPhoneNumbersByProviderId(long providerId) throws BmsSqlException {
		try {
			String sql = phoneNumberQuery.getProperty("phoneNumber.getAllPhoneNumbersByProviderId");
			Object[] params = new Object[] {providerId};
			List<PhoneNumberData> phoneNumberList = this.getTemplete().query(sql, params, new RowMapper<PhoneNumberData>() {
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
					phoneNumberData.setProviderId(rs.getLong(8));
					return phoneNumberData;
				}
			});
			return phoneNumberList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean isPhoneNumberAvailableForUser(long userId, String code, String number) throws BmsSqlException {
		try {
			String sql = phoneNumberQuery.getProperty("userPhoneNumber.isPhoneNumberAvailableForUser");
			Object[] params = new Object[] {userId, userId, code, number, userId, code, number};
			List<Long> userIDs = getTemplete().query(sql, params, new RowMapper<Long> () {
				@Override
				public Long mapRow(ResultSet rs, int index) throws SQLException {
					return rs.getLong(1);
				}
			});
			
			if (userIDs.isEmpty()) {
			  return true;
			} else if (userIDs.size() == 1) {
			  return false;
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
