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
import com.bms.service.data.CityData;
import com.bms.service.data.StateData;

@Repository("cityDao")
public class CityDao extends BaseDao implements ICityDao {

	@Autowired
	@Qualifier("cityQuery")
	private Properties cityQuery;
	
	@Override
	public long create(CityData cityData) throws BmsSqlException {
		try {
			String sql = cityQuery.getProperty("city.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, cityData.getStateId());
					ps.setString(2, cityData.getName());
					ps.setString(3, cityData.getRemarks());
					ps.setLong(4, cityData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(cityData.getCreatedOn().getTime()));
					ps.setLong(6, cityData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(cityData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(CityData cityData) throws BmsSqlException {
		try {
			String sql = cityQuery.getProperty("city.update");
			return this.getTemplete().update(sql, 
					cityData.getStateId(), 
					cityData.getName(), 
					cityData.getRemarks(),
					cityData.getUpdatedBy(),
					new java.sql.Timestamp(cityData.getUpdatedOn().getTime()),
					cityData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long cityId) throws BmsSqlException {
		try {
			String sql = cityQuery.getProperty("city.delete");
			return this.getTemplete().update(sql, cityId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public CityData getCityById(long cityId) throws BmsSqlException {
		try {
			String sql = cityQuery.getProperty("city.getCityById");
			Object[] params = new Object[] {cityId};
			List<CityData> cityList = this.getTemplete().query(sql, params, new RowMapper<CityData>() {
				@Override
				public CityData mapRow(ResultSet rs, int index) throws SQLException {
					CityData cityData = new CityData();
					cityData.setId(rs.getLong(1));
					cityData.setStateId(rs.getLong(2));
					cityData.setName(rs.getString(3));
					cityData.setRemarks(rs.getString(4));
					cityData.setStateName(rs.getString(5));
					cityData.setCountryId(rs.getLong(6));
					cityData.setCountryName(rs.getString(7));
					return cityData;
				}
			});
			
			if (cityList.isEmpty()) {
			  return null;
			} else if (cityList.size() == 1) {
			  return cityList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	public List<CityData> getCitiesByCountryId(long countryId) throws BmsSqlException {
		try {
			String sql = cityQuery.getProperty("city.getCitiesByCountryId");
			Object[] params = new Object[] {countryId};
			List<CityData> cityList = this.getTemplete().query(sql, params, new RowMapper<CityData>() {
				@Override
				public CityData mapRow(ResultSet rs, int index) throws SQLException {
					CityData cityData = new CityData();
					cityData.setId(rs.getLong(1));
					cityData.setStateId(rs.getLong(2));
					cityData.setName(rs.getString(3));
					cityData.setRemarks(rs.getString(4));
					cityData.setStateName(rs.getString(5));
					cityData.setCountryId(rs.getLong(6));
					cityData.setCountryName(rs.getString(7));
					return cityData;
				}
			});
			
			return cityList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<CityData> getAllCities() throws BmsSqlException {
		try {
			String sql = cityQuery.getProperty("city.getAllCities");
			List<CityData> cityList = this.getTemplete().query(sql, new RowMapper<CityData>() {
				@Override
				public CityData mapRow(ResultSet rs, int index) throws SQLException {
					CityData cityData = new CityData();
					cityData.setId(rs.getLong(1));
					cityData.setStateId(rs.getLong(2));
					cityData.setName(rs.getString(3));
					cityData.setRemarks(rs.getString(4));
					cityData.setStateName(rs.getString(5));
					cityData.setCountryId(rs.getLong(6));
					cityData.setCountryName(rs.getString(7));
					return cityData;
				}
			});
			
			return cityList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
