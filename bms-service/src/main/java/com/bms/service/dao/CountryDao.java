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
import com.bms.service.data.CountryData;
import com.bms.service.data.permission.GroupData;

@Repository("countryDao")
public class CountryDao extends BaseDao implements ICountryDao {

	@Override
	public long create(CountryData countryData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO Country ")
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
					ps.setString(1, countryData.getName());
					ps.setString(2, countryData.getRemarks());
					ps.setLong(3, countryData.getCreatedBy());
					ps.setTimestamp(4, new java.sql.Timestamp(countryData.getCreatedOn().getTime()));
					ps.setLong(5, countryData.getUpdatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(countryData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(CountryData countryData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("UPDATE Country SET ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
					countryData.getName(), 
					countryData.getRemarks(),
					countryData.getUpdatedBy(),
					new java.sql.Timestamp(countryData.getUpdatedOn().getTime()),
					countryData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long countryId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM Country WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), countryId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public CountryData getCountryById(long countryId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM Country ")
			.append("WHERE ")
			.append("Id = ?");
			
			Object[] params = new Object[] {countryId};
			List<CountryData> countryList = this.getTemplete().query(sql.toString(), params, new RowMapper<CountryData>() {
				@Override
				public CountryData mapRow(ResultSet rs, int index) throws SQLException {
					CountryData countryData = new CountryData();
					countryData.setId(rs.getLong(1));
					countryData.setName(rs.getString(2));
					countryData.setRemarks(rs.getString(3));
					return countryData;
				}
			});
			
			if (countryList.isEmpty()) {
			  return null;
			} else if (countryList.size() == 1) {
			  return countryList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<CountryData> getAllCountries() throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM Country ")
			.append("ORDER BY Id DESC ");
	
			List<CountryData> countryList = this.getTemplete().query(sql.toString(), new RowMapper<CountryData>() {
				@Override
				public CountryData mapRow(ResultSet rs, int index) throws SQLException {
					CountryData countryData = new CountryData();
					countryData.setId(rs.getLong(1));
					countryData.setName(rs.getString(2));
					countryData.setRemarks(rs.getString(3));
					return countryData;
				}
			});
			
			return countryList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
