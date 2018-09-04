package com.bms.service.dao;

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
import com.bms.service.data.CountryData;
import com.bms.service.data.permission.GroupData;

@Repository("countryDao")
public class CountryDao extends BaseDao implements ICountryDao {

	@Autowired
	@Qualifier("countryQuery")
	private Properties countryQuery;
	
	@Override
	public long create(CountryData countryData) throws BmsSqlException {
		try {
			String sql = countryQuery.getProperty("country.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
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
			String sql = countryQuery.getProperty("country.update");
			return this.getTemplete().update(sql, 
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
			String sql = countryQuery.getProperty("country.delete");
			return this.getTemplete().update(sql, countryId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public CountryData getCountryById(long countryId) throws BmsSqlException {
		try {
			String sql = countryQuery.getProperty("country.getCountryById");
			Object[] params = new Object[] {countryId};
			List<CountryData> countryList = this.getTemplete().query(sql, params, new RowMapper<CountryData>() {
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
			String sql = countryQuery.getProperty("country.getAllCountries");
			List<CountryData> countryList = this.getTemplete().query(sql, new RowMapper<CountryData>() {
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
	
	@Override
	public boolean isAvailable(long id, String name) throws BmsSqlException {
		try {
			String sql = countryQuery.getProperty("country.isAvailable");
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
