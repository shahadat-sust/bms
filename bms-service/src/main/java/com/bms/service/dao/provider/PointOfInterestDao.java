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
import com.bms.service.data.provider.PointOfInterestData;

@Repository("pointOfInterestDao")
public class PointOfInterestDao extends BaseDao implements IPointOfInterestDao {

	@Override
	public long create(PointOfInterestData pointOfInterestData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO PointOfInterest ")
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
					ps.setLong(1, pointOfInterestData.getProviderTypeId());
					ps.setString(2, pointOfInterestData.getName());
					ps.setString(3, pointOfInterestData.getRemarks());
					ps.setLong(4, pointOfInterestData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(pointOfInterestData.getCreatedOn().getTime()));
					ps.setLong(6, pointOfInterestData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(pointOfInterestData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(PointOfInterestData pointOfInterestData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("UPDATE PointOfInterest SET ")
				.append("ProviderTypeId = ?, ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
					pointOfInterestData.getProviderTypeId(), 
					pointOfInterestData.getName(), 
					pointOfInterestData.getRemarks(),
					pointOfInterestData.getUpdatedBy(),
					new java.sql.Timestamp(pointOfInterestData.getUpdatedOn().getTime()),
					pointOfInterestData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long pointOfInterestId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM PointOfInterest WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), pointOfInterestId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public PointOfInterestData getPointOfInterestById(long pointOfInterestId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("PointOfInterest.Id, ")
				.append("PointOfInterest.ProviderTypeId, ")
				.append("PointOfInterest.Name, ")
				.append("PointOfInterest.Remarks, ")
				.append("ProviderType.Name AS ProviderTypeName ")
			.append("FROM PointOfInterest ")
			.append("LEFT OUTER JOIN ProviderType ON ")
				.append("ProviderType.Id = PointOfInterest.ProviderTypeId ")
			.append("WHERE ")
			.append("PointOfInterest.Id = ?");
			
			Object[] params = new Object[] {pointOfInterestId};
			List<PointOfInterestData> pointOfInterestList = this.getTemplete().query(sql.toString(), params, new RowMapper<PointOfInterestData>() {
				@Override
				public PointOfInterestData mapRow(ResultSet rs, int index) throws SQLException {
					PointOfInterestData pointOfInterestData = new PointOfInterestData();
					pointOfInterestData.setId(rs.getLong(1));
					pointOfInterestData.setProviderTypeId(rs.getLong(2));
					pointOfInterestData.setName(rs.getString(3));
					pointOfInterestData.setRemarks(rs.getString(4));
					pointOfInterestData.setProviderTypeName(rs.getString(5));
					return pointOfInterestData;
				}
			});
			
			if (pointOfInterestList.isEmpty()) {
			  return null;
			} else if (pointOfInterestList.size() == 1) {
			  return pointOfInterestList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	public List<PointOfInterestData> getPointOfInterestsByProviderTypeId(long providerTypeId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
					.append("PointOfInterest.Id, ")
					.append("PointOfInterest.ProviderTypeId, ")
					.append("PointOfInterest.Name, ")
					.append("PointOfInterest.Remarks, ")
					.append("ProviderType.Name AS ProviderTypeName ")
			.append("FROM PointOfInterest ")
			.append("LEFT OUTER JOIN ProviderType ON ")
				.append("ProviderType.Id = PointOfInterest.ProviderTypeId ")
			.append("WHERE PointOfInterest.ProviderTypeId = ? ")
			.append("ORDER BY PointOfInterest.Name ASC ");
	
			Object[] params = new Object[] {providerTypeId};
			List<PointOfInterestData> pointOfInterestList = this.getTemplete().query(sql.toString(), params, new RowMapper<PointOfInterestData>() {
				@Override
				public PointOfInterestData mapRow(ResultSet rs, int index) throws SQLException {
					PointOfInterestData pointOfInterestData = new PointOfInterestData();
					pointOfInterestData.setId(rs.getLong(1));
					pointOfInterestData.setProviderTypeId(rs.getLong(2));
					pointOfInterestData.setName(rs.getString(3));
					pointOfInterestData.setRemarks(rs.getString(4));
					pointOfInterestData.setProviderTypeName(rs.getString(5));
					return pointOfInterestData;
				}
			});
			
			return pointOfInterestList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<PointOfInterestData> getAllPointOfInterests() throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
					.append("PointOfInterest.Id, ")
					.append("PointOfInterest.ProviderTypeId, ")
					.append("PointOfInterest.Name, ")
					.append("PointOfInterest.Remarks, ")
					.append("ProviderType.Name AS ProviderTypeName ")
			.append("FROM PointOfInterest ")
			.append("LEFT OUTER JOIN ProviderType ON ")
				.append("ProviderType.Id = PointOfInterest.ProviderTypeId ")
			.append("ORDER BY PointOfInterest.Id DESC ");
	
			List<PointOfInterestData> pointOfInterestList = this.getTemplete().query(sql.toString(), new RowMapper<PointOfInterestData>() {
				@Override
				public PointOfInterestData mapRow(ResultSet rs, int index) throws SQLException {
					PointOfInterestData pointOfInterestData = new PointOfInterestData();
					pointOfInterestData.setId(rs.getLong(1));
					pointOfInterestData.setProviderTypeId(rs.getLong(2));
					pointOfInterestData.setName(rs.getString(3));
					pointOfInterestData.setRemarks(rs.getString(4));
					pointOfInterestData.setProviderTypeName(rs.getString(5));
					return pointOfInterestData;
				}
			});
			
			return pointOfInterestList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
