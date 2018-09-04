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
import com.bms.service.data.provider.ProviderTypeData;

@Repository("providerTypeDao")
public class ProviderTypeDao extends BaseDao implements IProviderTypeDao {

	@Autowired
	@Qualifier("providerTypeQuery")
	private Properties providerTypeQuery;
	
	@Override
	public boolean create(ProviderTypeData providerTypeData) throws BmsSqlException {
		try {
			String sql = providerTypeQuery.getProperty("providerType.create");
			return this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setLong(1, providerTypeData.getId());
					ps.setString(2, providerTypeData.getName());
					ps.setString(3, providerTypeData.getRemarks());
					ps.setLong(4, providerTypeData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(providerTypeData.getCreatedOn().getTime()));
					ps.setLong(6, providerTypeData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(providerTypeData.getUpdatedOn().getTime()));
					return ps;
				}
			}) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(ProviderTypeData providerTypeData) throws BmsSqlException {
		try {
			String sql = providerTypeQuery.getProperty("providerType.update");
			return this.getTemplete().update(sql, 
					providerTypeData.getName(), 
					providerTypeData.getRemarks(),
					providerTypeData.getUpdatedBy(),
					new java.sql.Timestamp(providerTypeData.getUpdatedOn().getTime()),
					providerTypeData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long providerTypeId) throws BmsSqlException {
		try {
			String sql = providerTypeQuery.getProperty("providerType.delete");
			return this.getTemplete().update(sql, providerTypeId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ProviderTypeData getProviderTypeById(long providerTypeId) throws BmsSqlException {
		try {
			String sql = providerTypeQuery.getProperty("providerType.getProviderTypeById");
			Object[] params = new Object[] {providerTypeId};
			List<ProviderTypeData> providerTypeList = this.getTemplete().query(sql, params, new RowMapper<ProviderTypeData>() {
				@Override
				public ProviderTypeData mapRow(ResultSet rs, int index) throws SQLException {
					ProviderTypeData providerTypeData = new ProviderTypeData();
					providerTypeData.setId(rs.getLong(1));
					providerTypeData.setName(rs.getString(2));
					providerTypeData.setRemarks(rs.getString(3));
					return providerTypeData;
				}
			});
			
			if (providerTypeList.isEmpty()) {
			  return null;
			} else if (providerTypeList.size() == 1) {
			  return providerTypeList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ProviderTypeData> getAllProviderTypes() throws BmsSqlException {
		try {
			String sql = providerTypeQuery.getProperty("providerType.getAllProviderTypes");
			List<ProviderTypeData> providerTypeList = this.getTemplete().query(sql, new RowMapper<ProviderTypeData>() {
				@Override
				public ProviderTypeData mapRow(ResultSet rs, int index) throws SQLException {
					ProviderTypeData providerTypeData = new ProviderTypeData();
					providerTypeData.setId(rs.getLong(1));
					providerTypeData.setName(rs.getString(2));
					providerTypeData.setRemarks(rs.getString(3));
					return providerTypeData;
				}
			});
			
			return providerTypeList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public boolean isAvailable(long id, String name) throws BmsSqlException {
		try {
			String sql = providerTypeQuery.getProperty("providerType.isAvailable");
			Object[] params = new Object[] {id, id, name, id, name};
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
