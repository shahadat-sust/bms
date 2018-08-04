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
import com.bms.service.data.provider.AmenityData;

@Repository("amenityDao")
public class AmenityDao extends BaseDao implements IAmenityDao {

	@Autowired
	@Qualifier("amenityQuery")
	private Properties amenityQuery;
	
	@Override
	public long create(AmenityData amenityData) throws BmsSqlException {
		try {
			String sql = amenityQuery.getProperty("amenity.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, amenityData.getProviderTypeId());
					ps.setString(2, amenityData.getName());
					ps.setInt(3, amenityData.getType());
					ps.setString(4, amenityData.getRemarks());
					ps.setLong(5, amenityData.getCreatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(amenityData.getCreatedOn().getTime()));
					ps.setLong(7, amenityData.getUpdatedBy());
					ps.setTimestamp(8, new java.sql.Timestamp(amenityData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(AmenityData amenityData) throws BmsSqlException {
		try {
			String sql = amenityQuery.getProperty("amenity.update");
			return this.getTemplete().update(sql, 
					amenityData.getProviderTypeId(), 
					amenityData.getName(), 
					amenityData.getType(), 
					amenityData.getRemarks(),
					amenityData.getUpdatedBy(),
					new java.sql.Timestamp(amenityData.getUpdatedOn().getTime()),
					amenityData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long amenityId) throws BmsSqlException {
		try {
			String sql = amenityQuery.getProperty("amenity.delete");
			return this.getTemplete().update(sql, amenityId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public AmenityData getAmenityById(long amenityId) throws BmsSqlException {
		try {
			String sql = amenityQuery.getProperty("amenity.getAmenityById");
			Object[] params = new Object[] {amenityId};
			List<AmenityData> amenityList = this.getTemplete().query(sql, params, new RowMapper<AmenityData>() {
				@Override
				public AmenityData mapRow(ResultSet rs, int index) throws SQLException {
					AmenityData amenityData = new AmenityData();
					amenityData.setId(rs.getLong(1));
					amenityData.setProviderTypeId(rs.getLong(2));
					amenityData.setName(rs.getString(3));
					amenityData.setType(rs.getInt(4));
					amenityData.setRemarks(rs.getString(5));
					amenityData.setProviderTypeName(rs.getString(6));
					return amenityData;
				}
			});
			
			if (amenityList.isEmpty()) {
			  return null;
			} else if (amenityList.size() == 1) {
			  return amenityList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	public List<AmenityData> getAmenitiesByProviderTypeId(long providerTypeId) throws BmsSqlException {
		try {
			String sql = amenityQuery.getProperty("amenity.getAmenitiesByProviderTypeId");
			Object[] params = new Object[] {providerTypeId};
			List<AmenityData> amenityList = this.getTemplete().query(sql, params, new RowMapper<AmenityData>() {
				@Override
				public AmenityData mapRow(ResultSet rs, int index) throws SQLException {
					AmenityData amenityData = new AmenityData();
					amenityData.setId(rs.getLong(1));
					amenityData.setProviderTypeId(rs.getLong(2));
					amenityData.setName(rs.getString(3));
					amenityData.setType(rs.getInt(4));
					amenityData.setRemarks(rs.getString(5));
					amenityData.setProviderTypeName(rs.getString(6));
					return amenityData;
				}
			});
			
			return amenityList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<AmenityData> getAllAmenities() throws BmsSqlException {
		try {
			String sql = amenityQuery.getProperty("amenity.getAllAmenities");
			List<AmenityData> amenityList = this.getTemplete().query(sql, new RowMapper<AmenityData>() {
				@Override
				public AmenityData mapRow(ResultSet rs, int index) throws SQLException {
					AmenityData amenityData = new AmenityData();
					amenityData.setId(rs.getLong(1));
					amenityData.setProviderTypeId(rs.getLong(2));
					amenityData.setName(rs.getString(3));
					amenityData.setType(rs.getInt(4));
					amenityData.setRemarks(rs.getString(5));
					amenityData.setProviderTypeName(rs.getString(6));
					return amenityData;
				}
			});
			
			return amenityList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
