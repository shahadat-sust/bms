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
import com.bms.service.data.provider.PointOfInterestData;

@Repository("pointOfInterestDao")
public class PointOfInterestDao extends BaseDao implements IPointOfInterestDao {

	@Autowired
	@Qualifier("pointOfInterestQuery")
	private Properties pointOfInterestQuery;
	
	@Override
	public long create(PointOfInterestData pointOfInterestData) throws BmsSqlException {
		try {
			String sql = pointOfInterestQuery.getProperty("pointOfInterest.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
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
			String sql = pointOfInterestQuery.getProperty("pointOfInterest.update");
			return this.getTemplete().update(sql, 
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
			String sql = pointOfInterestQuery.getProperty("pointOfInterest.delete");
			return this.getTemplete().update(sql, pointOfInterestId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public PointOfInterestData getPointOfInterestById(long pointOfInterestId) throws BmsSqlException {
		try {
			String sql = pointOfInterestQuery.getProperty("pointOfInterest.getPointOfInterestById");
			Object[] params = new Object[] {pointOfInterestId};
			List<PointOfInterestData> pointOfInterestList = this.getTemplete().query(sql, params, new RowMapper<PointOfInterestData>() {
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
			String sql = pointOfInterestQuery.getProperty("pointOfInterest.getPointOfInterestsByProviderTypeId");
			Object[] params = new Object[] {providerTypeId};
			List<PointOfInterestData> pointOfInterestList = this.getTemplete().query(sql, params, new RowMapper<PointOfInterestData>() {
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
			String sql = pointOfInterestQuery.getProperty("pointOfInterest.getAllPointOfInterests");
			List<PointOfInterestData> pointOfInterestList = this.getTemplete().query(sql, new RowMapper<PointOfInterestData>() {
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
	public boolean isAvailable(long id, String name, long providerTypeId) throws BmsSqlException {
		try {
			String sql = pointOfInterestQuery.getProperty("pointOfInterest.isAvailable");
			Object[] params = new Object[] {id, id, name, providerTypeId, id, name, providerTypeId};
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
