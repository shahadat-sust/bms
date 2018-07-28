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
import com.bms.service.data.provider.ProviderTypeData;

@Repository("providerTypeDao")
public class ProviderTypeDao extends BaseDao implements IProviderTypeDao {

	@Override
	public long create(ProviderTypeData providerTypeData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO ProviderType ")
			.append("( ")
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
				.append("? ")
			.append(")");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { "Id" });
					ps.setString(1, providerTypeData.getName());
					ps.setString(2, providerTypeData.getRemarks());
					ps.setLong(3, providerTypeData.getCreatedBy());
					ps.setTimestamp(4, new java.sql.Timestamp(providerTypeData.getCreatedOn().getTime()));
					ps.setLong(5, providerTypeData.getUpdatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(providerTypeData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(ProviderTypeData providerTypeData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("UPDATE ProviderType SET ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
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
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM ProviderType WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), providerTypeId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ProviderTypeData getProviderTypeById(long providerTypeId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM ProviderType ")
			.append("WHERE ")
			.append("Id = ?");
			
			Object[] params = new Object[] {providerTypeId};
			List<ProviderTypeData> providerTypeList = this.getTemplete().query(sql.toString(), params, new RowMapper<ProviderTypeData>() {
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
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM ProviderType ")
			.append("ORDER BY Id DESC ");
	
			List<ProviderTypeData> providerTypeList = this.getTemplete().query(sql.toString(), new RowMapper<ProviderTypeData>() {
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

}
