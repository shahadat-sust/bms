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
import com.bms.service.data.provider.AmenityChargeData;

@Repository("amenityChargeDao")
public class AmenityChargeDao extends BaseDao implements IAmenityChargeDao {

	@Autowired
	@Qualifier("amenityChargeQuery")
	private Properties amenityChargeQuery;
	
	@Override
	public long create(AmenityChargeData amenityChargeData) throws BmsSqlException {
		try {
			String sql = amenityChargeQuery.getProperty("amenityCharge.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, amenityChargeData.getProviderId());
					ps.setLong(2, amenityChargeData.getAmenityId());
					ps.setDouble(3, amenityChargeData.getCharge());
					ps.setLong(4, amenityChargeData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(amenityChargeData.getCreatedOn().getTime()));
					ps.setLong(6, amenityChargeData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(amenityChargeData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(AmenityChargeData amenityChargeData) throws BmsSqlException {
		try {
			String sql = amenityChargeQuery.getProperty("amenityCharge.update");
			return this.getTemplete().update(sql, 
					amenityChargeData.getCharge(),
					amenityChargeData.getUpdatedBy(),
					new java.sql.Timestamp(amenityChargeData.getUpdatedOn().getTime()),
					amenityChargeData.getAmenityId(),
					amenityChargeData.getProviderId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long amenityChargeId) throws BmsSqlException {
		try {
			String sql = amenityChargeQuery.getProperty("amenityCharge.delete");
			return this.getTemplete().update(sql, amenityChargeId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public AmenityChargeData getAmenityChargeById(long amenityChargeId) throws BmsSqlException {
		try {
			String sql = amenityChargeQuery.getProperty("amenityCharge.getAmenityChargeById");
			Object[] params = new Object[] {amenityChargeId};
			List<AmenityChargeData> list = this.getTemplete().query(sql, params, new RowMapper<AmenityChargeData>() {
				@Override
				public AmenityChargeData mapRow(ResultSet rs, int index) throws SQLException {
					AmenityChargeData data = new AmenityChargeData();
					data.setId(rs.getLong(1));
					data.setProviderId(rs.getLong(2));
					data.setAmenityId(rs.getLong(3));
					data.setCharge(rs.getDouble(4));
					data.setAmenityName(rs.getString(5));
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
	public List<AmenityChargeData> getAllAmenityChargesByProviderId(long providerId) throws BmsSqlException {
		try {
			String sql = amenityChargeQuery.getProperty("amenityCharge.getAllAmenityChargesByProviderId");
			Object[] params = new Object[] {providerId};
			List<AmenityChargeData> list = this.getTemplete().query(sql, params, new RowMapper<AmenityChargeData>() {
				@Override
				public AmenityChargeData mapRow(ResultSet rs, int index) throws SQLException {
					AmenityChargeData data = new AmenityChargeData();
					data.setId(rs.getLong(1));
					data.setProviderId(rs.getLong(2));
					data.setAmenityId(rs.getLong(3));
					data.setCharge(rs.getDouble(4));
					data.setAmenityName(rs.getString(5));
					return data;
				}
			});
			
			return list;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, String amenityId, long providerId) throws BmsSqlException {
		try {
			String sql = amenityChargeQuery.getProperty("amenityCharge.isAvailable");
			Object[] params = new Object[] {id, id, amenityId, providerId, id, amenityId, providerId};
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
