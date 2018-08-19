package com.bms.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
import com.bms.service.data.PhoneNumberData;
import com.bms.service.data.PostalAddressData;

@Repository("postalAddressDao")
public class PostalAddressDao extends BaseDao implements IPostalAddressDao {

	@Autowired
	@Qualifier("postalAddressQuery")
	private Properties postalAddressQuery;
	
	@Override
	public long create(long userId, long providerId, PostalAddressData postalAddressData) throws BmsSqlException {
		try {
			String sql = postalAddressQuery.getProperty("postalAddress.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setString(1, postalAddressData.getLine1());
					ps.setString(2, postalAddressData.getLine2());
					if(postalAddressData.getCityId() > 0) {
						ps.setLong(3, postalAddressData.getCityId());
					} else {
						ps.setNull(3, Types.BIGINT);
					}
					if(postalAddressData.getStateId() > 0) {
						ps.setLong(4, postalAddressData.getStateId());
					} else {
						ps.setNull(4, Types.BIGINT);
					}
					ps.setLong(5, postalAddressData.getCountryId());
					ps.setString(6, postalAddressData.getPostCode());
					ps.setLong(7, postalAddressData.getCreatedBy());
					ps.setTimestamp(8, new java.sql.Timestamp(postalAddressData.getCreatedOn().getTime()));
					ps.setLong(9, postalAddressData.getUpdatedBy());
					ps.setTimestamp(10, new java.sql.Timestamp(postalAddressData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			long postalAddressId = holder.getKey().longValue();
			
			if(userId > 0) {
				String sql1 = postalAddressQuery.getProperty("userPostalAddress.create");
				KeyHolder holder1 = new GeneratedKeyHolder();
				this.getTemplete().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement ps = conn.prepareStatement(sql1, new String[] { "Id" });
						ps.setLong(1, userId);
						ps.setLong(2, postalAddressId);
						ps.setLong(3, postalAddressData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(postalAddressData.getCreatedOn().getTime()));
						ps.setLong(5, postalAddressData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(postalAddressData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			} else if(providerId > 0) {
				String sql1 = postalAddressQuery.getProperty("providerPostalAddress.create");
				KeyHolder holder1 = new GeneratedKeyHolder();
				this.getTemplete().update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
						PreparedStatement ps = conn.prepareStatement(sql1.toString(), new String[] { "Id" });
						ps.setLong(1, providerId);
						ps.setLong(2, postalAddressId);
						ps.setLong(3, postalAddressData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(postalAddressData.getCreatedOn().getTime()));
						ps.setLong(5, postalAddressData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(postalAddressData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			}
			
			return postalAddressId;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(PostalAddressData postalAddressData) throws BmsSqlException {
		try {
			String sql = postalAddressQuery.getProperty("postalAddress.update");
			return this.getTemplete().update(sql, 
					postalAddressData.getLine1(), 
					postalAddressData.getLine2(),
					postalAddressData.getCityId() > 0 ? postalAddressData.getCityId() : null,
					postalAddressData.getStateId() > 0 ? postalAddressData.getStateId() : null,
					postalAddressData.getCountryId(),
					postalAddressData.getPostCode(),
					postalAddressData.getUpdatedBy(),
					new java.sql.Timestamp(postalAddressData.getUpdatedOn().getTime()),
					postalAddressData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long postalAddressId) throws BmsSqlException {
		try {
			String sql = postalAddressQuery.getProperty("postalAddress.delete");
			return this.getTemplete().update(sql, postalAddressId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public PostalAddressData getPostalAddressById(long postalAddressId) throws BmsSqlException {
		try {
			String sql = postalAddressQuery.getProperty("postalAddress.getPostalAddressById");
			Object[] params = new Object[] {postalAddressId};
			List<PostalAddressData> postalAddressList = this.getTemplete().query(sql, params, new RowMapper<PostalAddressData>() {
				@Override
				public PostalAddressData mapRow(ResultSet rs, int index) throws SQLException {
					PostalAddressData postalAddressData = new PostalAddressData();
					postalAddressData.setId(rs.getLong(1));
					postalAddressData.setLine1(rs.getString(2));
					postalAddressData.setLine2(rs.getString(3));
					postalAddressData.setCityId(rs.getLong(4));
					postalAddressData.setStateId(rs.getLong(5));
					postalAddressData.setCountryId(rs.getLong(6));
					postalAddressData.setPostCode(rs.getString(7));
					return postalAddressData;
				}
			});
			
			if (postalAddressList.isEmpty()) {
			  return null;
			} else if (postalAddressList.size() == 1) {
			  return postalAddressList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<PostalAddressData> getAllPostalAddressesByUserId(long userId) throws BmsSqlException {
		try {
			String sql = postalAddressQuery.getProperty("postalAddress.getAllPostalAddressesByUserId");
			Object[] params = new Object[] {userId};
			List<PostalAddressData> postalAddressList = this.getTemplete().query(sql, params, new RowMapper<PostalAddressData>() {
				@Override
				public PostalAddressData mapRow(ResultSet rs, int index) throws SQLException {
					PostalAddressData postalAddressData = new PostalAddressData();
					postalAddressData.setId(rs.getLong(1));
					postalAddressData.setLine1(rs.getString(2));
					postalAddressData.setLine2(rs.getString(3));
					postalAddressData.setCityId(rs.getLong(4));
					postalAddressData.setStateId(rs.getLong(5));
					postalAddressData.setCountryId(rs.getLong(6));
					postalAddressData.setPostCode(rs.getString(7));
					return postalAddressData;
				}
			});
			return postalAddressList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public List<PostalAddressData> getAllPostalAddressesByProviderId(long providerId) throws BmsSqlException {
		try {
			String sql = postalAddressQuery.getProperty("postalAddress.getAllPostalAddressesByProviderId");
			Object[] params = new Object[] {providerId};
			List<PostalAddressData> postalAddressList = this.getTemplete().query(sql, params, new RowMapper<PostalAddressData>() {
				@Override
				public PostalAddressData mapRow(ResultSet rs, int index) throws SQLException {
					PostalAddressData postalAddressData = new PostalAddressData();
					postalAddressData.setId(rs.getLong(1));
					postalAddressData.setLine1(rs.getString(2));
					postalAddressData.setLine2(rs.getString(3));
					postalAddressData.setCityId(rs.getLong(4));
					postalAddressData.setStateId(rs.getLong(5));
					postalAddressData.setCountryId(rs.getLong(6));
					postalAddressData.setPostCode(rs.getString(7));
					return postalAddressData;
				}
			});
			return postalAddressList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
