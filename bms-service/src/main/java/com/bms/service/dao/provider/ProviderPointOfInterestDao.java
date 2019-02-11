package com.bms.service.dao.provider;

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
import com.bms.service.data.provider.ProviderPointOfInterestData;

@Repository("providerPointOfInterestDao")
public class ProviderPointOfInterestDao extends BaseDao implements IProviderPointOfInterestDao {

	@Autowired
	@Qualifier("providerPointOfInterestQuery")
	private Properties providerPointOfInterestQuery;
	
	@Override
	public long create(ProviderPointOfInterestData providerPointOfInterestData) throws BmsSqlException {
		try {
			String sql = providerPointOfInterestQuery.getProperty("providerPointOfInterest.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, providerPointOfInterestData.getProviderId());
					ps.setLong(2, providerPointOfInterestData.getPointOfInterestId());
					ps.setLong(3, providerPointOfInterestData.getCreatedBy());
					ps.setTimestamp(4, new java.sql.Timestamp(providerPointOfInterestData.getCreatedOn().getTime()));
					ps.setLong(5, providerPointOfInterestData.getUpdatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(providerPointOfInterestData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long providerPointOfInterestId) throws BmsSqlException {
		try {
			String sql = providerPointOfInterestQuery.getProperty("providerPointOfInterest.delete");
			return this.getTemplete().update(sql, providerPointOfInterestId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ProviderPointOfInterestData getProviderPointOfInterestById(long providerPointOfInterestId) throws BmsSqlException {
		try {
			String sql = providerPointOfInterestQuery.getProperty("providerPointOfInterest.getProviderPointOfInterestById");
			Object[] params = new Object[] {providerPointOfInterestId};
			List<ProviderPointOfInterestData> list = this.getTemplete().query(sql, params, new RowMapper<ProviderPointOfInterestData>() {
				@Override
				public ProviderPointOfInterestData mapRow(ResultSet rs, int index) throws SQLException {
					ProviderPointOfInterestData data = new ProviderPointOfInterestData();
					data.setId(rs.getLong(1));
					data.setProviderId(rs.getLong(2));
					data.setPointOfInterestId(rs.getLong(3));
					data.setPointOfInterestName(rs.getString(4));
					return data;
				}
			});
			
			if (list.isEmpty()) {
			  return null;
			} else if (list.size() == 1) {
			  return list.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ProviderPointOfInterestData> getAllProviderPointOfInterestsByProviderId(long providerId) throws BmsSqlException {
		try {
			String sql = providerPointOfInterestQuery.getProperty("providerPointOfInterest.getAllProviderPointOfInterestsByProviderId");
			Object[] params = new Object[] {providerId};
			List<ProviderPointOfInterestData> list = this.getTemplete().query(sql, params, new RowMapper<ProviderPointOfInterestData>() {
				@Override
				public ProviderPointOfInterestData mapRow(ResultSet rs, int index) throws SQLException {
					ProviderPointOfInterestData data = new ProviderPointOfInterestData();
					data.setId(rs.getLong(1));
					data.setProviderId(rs.getLong(2));
					data.setPointOfInterestId(rs.getLong(3));
					data.setPointOfInterestName(rs.getString(4));
					return data;
				}
			});
			
			return list;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean isAvailable(long pointOfInterestId, long providerId) throws BmsSqlException {
		try {
			String sql = providerPointOfInterestQuery.getProperty("providerPointOfInterest.isAvailable");
			Object[] params = new Object[] {pointOfInterestId, providerId};
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
