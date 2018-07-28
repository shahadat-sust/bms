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
import com.bms.service.data.PostalAddressData;

@Repository("postalAddressDao")
public class PostalAddressDao extends BaseDao implements IPostalAddressDao {

	@Override
	public long create(long userId, long providerId, PostalAddressData postalAddressData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO PostalAddress ")
			.append("( ")
				.append("Line1, ")
				.append("Line2, ")
				.append("CityId, ")
				.append("StateId, ")
				.append("CountryId, ")
				.append("PostCode, ")
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
					ps.setString(1, postalAddressData.getLine1());
					ps.setString(2, postalAddressData.getLine2());
					ps.setLong(3, postalAddressData.getCityId());
					ps.setLong(4, postalAddressData.getStateId());
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
				StringBuilder sql1 = new StringBuilder()
				.append("INSERT INTO UserPostalAddress ")
				.append("( ")
					.append("UserId, ")
					.append("PostalAddressId, ")
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
						ps.setLong(2, postalAddressId);
						ps.setLong(3, postalAddressData.getCreatedBy());
						ps.setTimestamp(4, new java.sql.Timestamp(postalAddressData.getCreatedOn().getTime()));
						ps.setLong(5, postalAddressData.getUpdatedBy());
						ps.setTimestamp(6, new java.sql.Timestamp(postalAddressData.getUpdatedOn().getTime()));
						return ps;
					}
				}, holder1);
			} else if(providerId > 0) {
				StringBuilder sql1 = new StringBuilder()
				.append("INSERT INTO ProviderPostalAddress ")
				.append("( ")
					.append("ProviderId, ")
					.append("PostalAddressId, ")
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
			StringBuilder sql = new StringBuilder()
			.append("UPDATE PostalAddress SET ")
				.append("Line1 = ?, ")
				.append("Line2 = ?, ")
				.append("CityId = ?, ")
				.append("StateId = ?, ")
				.append("CountryId = ?, ")
				.append("PostCode = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
					postalAddressData.getLine1(), 
					postalAddressData.getLine2(),
					postalAddressData.getCityId(),
					postalAddressData.getStateId(),
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
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM PostalAddress WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), postalAddressId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public PostalAddressData getPostalAddressById(long postalAddressId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Line1, ")
				.append("Line2, ")
				.append("CityId, ")
				.append("StateId, ")
				.append("CountryId, ")
				.append("PostCode ")
			.append("FROM PostalAddress ")
			.append("WHERE ")
			.append("Id = ?");
			
			Object[] params = new Object[] {postalAddressId};
			List<PostalAddressData> postalAddressList = this.getTemplete().query(sql.toString(), params, new RowMapper<PostalAddressData>() {
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
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("PostalAddress.Id, ")
				.append("Line1, ")
				.append("Line2, ")
				.append("CityId, ")
				.append("StateId, ")
				.append("CountryId, ")
				.append("PostCode ")
			.append("FROM PostalAddress ")
			.append("LEFT OUTER JOIN UserPostalAddress ON ")
				.append("UserPostalAddress.PostalAddressId = PostalAddress.Id ")
			.append("WHERE ")
				.append("UserPostalAddress.UserId = ?");
			
			Object[] params = new Object[] {userId};
			List<PostalAddressData> postalAddressList = this.getTemplete().query(sql.toString(), params, new RowMapper<PostalAddressData>() {
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
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("PostalAddress.Id, ")
				.append("Line1, ")
				.append("Line2, ")
				.append("CityId, ")
				.append("StateId, ")
				.append("CountryId, ")
				.append("PostCode ")
			.append("FROM PostalAddress ")
			.append("LEFT OUTER JOIN ProviderPostalAddress ON ")
				.append("ProviderPostalAddress.PostalAddressId = PostalAddress.Id ")
			.append("WHERE ")
				.append("ProviderPostalAddress.ProviderId = ?");
			
			Object[] params = new Object[] {providerId};
			List<PostalAddressData> postalAddressList = this.getTemplete().query(sql.toString(), params, new RowMapper<PostalAddressData>() {
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
