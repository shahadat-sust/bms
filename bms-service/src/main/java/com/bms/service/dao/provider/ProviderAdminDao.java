package com.bms.service.dao.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.provider.ProviderAdminData;

@Service("providerAdminDao")
public class ProviderAdminDao extends BaseDao implements IProviderAdminDao {

	@Autowired
	@Qualifier("providerAdminQuery")
	private Properties providerAdminQuery;
	
	@Autowired
	@Qualifier("userDefaultProviderQuery")
	private Properties userDefaultProviderQuery;
	
	@Override
	public long createProviderAdmin(ProviderAdminData providerAdminData) throws BmsSqlException {
		try {
			String sql = providerAdminQuery.getProperty("providerAdmin.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, providerAdminData.getProviderId());
					ps.setLong(2, providerAdminData.getUserId());
					ps.setLong(3, providerAdminData.getCreatedBy());
					ps.setTimestamp(4, new Timestamp(providerAdminData.getCreatedOn().getTime()));
					ps.setLong(5, providerAdminData.getUpdatedBy());
					ps.setTimestamp(6, new Timestamp(providerAdminData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean deleteProviderAdmin(long userId, long providerId) throws BmsSqlException {
		try {
			String sql = providerAdminQuery.getProperty("providerAdmin.delete"); 
			return this.getTemplete().update(sql, 
					providerId,
					userId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean isProviderAssignedForAdmin(long userId, long providerId) throws BmsSqlException {
		try {
			String sql = providerAdminQuery.getProperty("providerAdmin.isProviderAssignedForAdmin");
			Object[] params = new Object[] {providerId, providerId, providerId, userId};
			List<Long> countList = this.getTemplete().query(sql, params, new RowMapper<Long> () {
				@Override
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong(1);
				}	
			});
			
			if (countList.isEmpty()) {
				return false;
			} else if (providerId > 0) {
				if (countList.size() == 1) {
					return countList.get(0) > 0;
				} else {
					throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");
				}
			} else {
				return countList.get(0) > 0;
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ProviderAdminData> getAllProviderAdminDatasByUserId(long userId) throws BmsSqlException {
		try {
			String sql = providerAdminQuery.getProperty("providerAdmin.getAllProviderAdminDatasByUserId");
			Object[] params = new Object[] {userId};
			List<ProviderAdminData> providerList = this.getTemplete().query(sql, params, new RowMapper<ProviderAdminData> () {
				@Override
				public ProviderAdminData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ProviderAdminData providerAdminData = new ProviderAdminData();
					providerAdminData.setId(rs.getLong(1));
					providerAdminData.setProviderId(rs.getLong(2));
					providerAdminData.setUserId(rs.getLong(3));
					providerAdminData.setTitle(rs.getString(4));
					providerAdminData.setStarRating(rs.getInt(5));
					providerAdminData.setUsername(rs.getString(6));
					providerAdminData.setFirstName(rs.getString(7));
					providerAdminData.setLastName(rs.getString(8));
					providerAdminData.setAddress(rs.getString(9));
					providerAdminData.setCityName(rs.getString(10));
					providerAdminData.setStateName(rs.getString(11));
					providerAdminData.setCountryName(rs.getString(12));
					return providerAdminData;
				}	
			});
			
			return providerList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public long createDefaultProvider(ProviderAdminData providerAdminData) throws BmsSqlException {
		try {
			String sql = userDefaultProviderQuery.getProperty("userDefaultProvider.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, providerAdminData.getProviderId());
					ps.setLong(2, providerAdminData.getUserId());
					ps.setLong(3, providerAdminData.getCreatedBy());
					ps.setTimestamp(4, new Timestamp(providerAdminData.getCreatedOn().getTime()));
					ps.setLong(5, providerAdminData.getUpdatedBy());
					ps.setTimestamp(6, new Timestamp(providerAdminData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean updateDefaultProvider(ProviderAdminData providerAdminData) throws BmsSqlException {
		try {
			String sql = userDefaultProviderQuery.getProperty("userDefaultProvider.update");
			return this.getTemplete().update(sql,
					providerAdminData.getProviderId(),
					providerAdminData.getUpdatedBy(),
					new Timestamp(providerAdminData.getUpdatedOn().getTime()),
					providerAdminData.getUserId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	public boolean deleteDefaultProvider(long userId) throws BmsSqlException  {
		try {
			String sql = userDefaultProviderQuery.getProperty("userDefaultProvider.delete"); 
			return this.getTemplete().update(sql, 
					userId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	public boolean isDefaultProviderAssignedForAdmin(long userId) throws BmsSqlException {
		try {
			String sql = userDefaultProviderQuery.getProperty("userDefaultProvider.isDefaultProviderAssignedForAdmin");
			Object[] params = new Object[] {userId};
			List<Long> countList = this.getTemplete().query(sql, params, new RowMapper<Long> () {
				@Override
				public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rs.getLong(1);
				}	
			});
			
			if (countList.isEmpty()) {
				return false;
			} else if (countList.size() == 1) {
				return countList.get(0) > 0;
			} else {
				throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ProviderAdminData getDefaultProviderByUserId(long userId) throws BmsSqlException {
		try {
			String sql = userDefaultProviderQuery.getProperty("userDefaultProvider.getDefaultProviderByUserId");
			Object[] params = new Object[] {userId};
			List<ProviderAdminData> providerAdminList = this.getTemplete().query(sql, params, new RowMapper<ProviderAdminData> () {
				@Override
				public ProviderAdminData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ProviderAdminData providerAdminData = new ProviderAdminData();
					providerAdminData.setId(rs.getLong(1));
					providerAdminData.setProviderId(rs.getLong(2));
					providerAdminData.setUserId(rs.getLong(3));
					providerAdminData.setTitle(rs.getString(4));
					providerAdminData.setStarRating(rs.getInt(5));
					providerAdminData.setUsername(rs.getString(6));
					providerAdminData.setFirstName(rs.getString(7));
					providerAdminData.setLastName(rs.getString(8));
					providerAdminData.setAddress(rs.getString(9));
					providerAdminData.setCityName(rs.getString(10));
					providerAdminData.setStateName(rs.getString(11));
					providerAdminData.setCountryName(rs.getString(12));
					return providerAdminData;
				}	
			});
			
			if (providerAdminList.isEmpty()) {
				return null;
			} else if (providerAdminList.size() == 1) {
				return providerAdminList.get(0);
			} else {
				throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
