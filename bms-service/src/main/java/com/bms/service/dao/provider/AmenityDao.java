package com.bms.service.dao.provider;

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
import com.bms.service.dao.BaseDao;
import com.bms.service.data.provider.AmenityData;

@Repository("amenityDao")
public class AmenityDao extends BaseDao implements IAmenityDao {

	@Override
	public long create(AmenityData amenityData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO Amenity ")
			.append("( ")
				.append("ProviderTypeId, ")
				.append("Name, ")
				.append("Remarks, ")
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
				.append("? ")
			.append(")");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { "Id" });
					ps.setLong(1, amenityData.getProviderTypeId());
					ps.setString(2, amenityData.getName());
					ps.setString(3, amenityData.getRemarks());
					ps.setLong(4, amenityData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(amenityData.getCreatedOn().getTime()));
					ps.setLong(6, amenityData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(amenityData.getUpdatedOn().getTime()));
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
			StringBuilder sql = new StringBuilder()
			.append("UPDATE Amenity SET ")
				.append("ProviderTypeId = ?, ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
					amenityData.getProviderTypeId(), 
					amenityData.getName(), 
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
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM Amenity WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), amenityId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public AmenityData getAmenityById(long amenityId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Amenity.Id, ")
				.append("Amenity.ProviderTypeId, ")
				.append("Amenity.Name, ")
				.append("Amenity.Remarks, ")
				.append("ProviderType.Name AS ProviderTypeName ")
			.append("FROM Amenity ")
			.append("LEFT OUTER JOIN ProviderType ON ")
				.append("ProviderType.Id = Amenity.ProviderTypeId ")
			.append("WHERE ")
			.append("Amenity.Id = ?");
			
			Object[] params = new Object[] {amenityId};
			List<AmenityData> amenityList = this.getTemplete().query(sql.toString(), params, new RowMapper<AmenityData>() {
				@Override
				public AmenityData mapRow(ResultSet rs, int index) throws SQLException {
					AmenityData amenityData = new AmenityData();
					amenityData.setId(rs.getLong(1));
					amenityData.setProviderTypeId(rs.getLong(2));
					amenityData.setName(rs.getString(3));
					amenityData.setRemarks(rs.getString(4));
					amenityData.setProviderTypeName(rs.getString(5));
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
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
					.append("Amenity.Id, ")
					.append("Amenity.ProviderTypeId, ")
					.append("Amenity.Name, ")
					.append("Amenity.Remarks, ")
					.append("ProviderType.Name AS ProviderTypeName ")
			.append("FROM Amenity ")
			.append("LEFT OUTER JOIN ProviderType ON ")
				.append("ProviderType.Id = Amenity.ProviderTypeId ")
			.append("WHERE Amenity.ProviderTypeId = ? ")
			.append("ORDER BY Amenity.Name ASC ");
	
			Object[] params = new Object[] {providerTypeId};
			List<AmenityData> amenityList = this.getTemplete().query(sql.toString(), params, new RowMapper<AmenityData>() {
				@Override
				public AmenityData mapRow(ResultSet rs, int index) throws SQLException {
					AmenityData amenityData = new AmenityData();
					amenityData.setId(rs.getLong(1));
					amenityData.setProviderTypeId(rs.getLong(2));
					amenityData.setName(rs.getString(3));
					amenityData.setRemarks(rs.getString(4));
					amenityData.setProviderTypeName(rs.getString(5));
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
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
					.append("Amenity.Id, ")
					.append("Amenity.ProviderTypeId, ")
					.append("Amenity.Name, ")
					.append("Amenity.Remarks, ")
					.append("ProviderType.Name AS ProviderTypeName ")
			.append("FROM Amenity ")
			.append("LEFT OUTER JOIN ProviderType ON ")
				.append("ProviderType.Id = Amenity.ProviderTypeId ")
			.append("ORDER BY Amenity.Id DESC ");
	
			List<AmenityData> amenityList = this.getTemplete().query(sql.toString(), new RowMapper<AmenityData>() {
				@Override
				public AmenityData mapRow(ResultSet rs, int index) throws SQLException {
					AmenityData amenityData = new AmenityData();
					amenityData.setId(rs.getLong(1));
					amenityData.setProviderTypeId(rs.getLong(2));
					amenityData.setName(rs.getString(3));
					amenityData.setRemarks(rs.getString(4));
					amenityData.setProviderTypeName(rs.getString(5));
					return amenityData;
				}
			});
			
			return amenityList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
