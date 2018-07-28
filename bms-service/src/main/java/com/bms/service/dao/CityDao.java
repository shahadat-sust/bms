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
import com.bms.service.data.CityData;
import com.bms.service.data.StateData;

@Repository("cityDao")
public class CityDao extends BaseDao implements ICityDao {

	@Override
	public long create(CityData cityData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("INSERT INTO City ")
		.append("( ")
			.append("StateId, ")
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
				ps.setLong(1, cityData.getStateId());
				ps.setString(2, cityData.getName());
				ps.setString(3, cityData.getRemarks());
				ps.setLong(4, cityData.getCreatedBy());
				ps.setDate(5, new java.sql.Date(cityData.getCreatedOn().getTime()));
				ps.setLong(6, cityData.getUpdatedBy());
				ps.setDate(7, new java.sql.Date(cityData.getUpdatedOn().getTime()));
				return ps;
			}
		}, holder);
		return holder.getKey().longValue();
	}

	@Override
	public boolean update(CityData cityData) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("UPDATE City SET ")
			.append("StateId = ?, ")
			.append("Name = ?, ")
			.append("Remarks = ?, ")
			.append("UpdatedBy = ?, ")
			.append("UpdatedOn = ? ")
		.append("WHERE ")
		.append("Id = ?");
		return this.getTemplete().update(sql.toString(), 
				cityData.getStateId(), 
				cityData.getName(), 
				cityData.getRemarks(),
				cityData.getUpdatedBy(),
				new java.sql.Date(cityData.getUpdatedOn().getTime()),
				cityData.getId()) == 1;
	}

	@Override
	public boolean delete(long cityId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM City WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), cityId) == 1;
	}

	@Override
	public CityData getCityById(long cityId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
				.append("City.Id, ")
				.append("City.StateId, ")
				.append("City.Name, ")
				.append("City.Remarks, ")
				.append("State.Name AS StateName, ")
				.append("Country.Id AS CountryId, ")
				.append("Country.Name AS CountryName ")
		.append("FROM City ")
		.append("LEFT OUTER JOIN State ON ")
			.append("State.Id = City.StateId ")
		.append("LEFT OUTER JOIN Country ON ")
			.append("Country.Id = State.CountryId ")
		.append("WHERE City.Id = ? ");
		
		Object[] params = new Object[] {cityId};
		List<CityData> cityList = this.getTemplete().query(sql.toString(), params, new RowMapper<CityData>() {
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
	}
	
	public List<CityData> getCitiesByCountryId(long countryId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
				.append("City.Id, ")
				.append("City.StateId, ")
				.append("City.Name, ")
				.append("City.Remarks, ")
				.append("State.Name AS StateName, ")
				.append("Country.Id AS CountryId, ")
				.append("Country.Name AS CountryName ")
		.append("FROM City ")
		.append("LEFT OUTER JOIN State ON ")
			.append("State.Id = City.StateId ")
		.append("LEFT OUTER JOIN Country ON ")
			.append("Country.Id = State.CountryId ")
		.append("WHERE Country.Id = ? ")
		.append("ORDER BY City.Name ASC ");

		Object[] params = new Object[] {countryId};
		List<CityData> cityList = this.getTemplete().query(sql.toString(), params, new RowMapper<CityData>() {
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
	}

	@Override
	public List<CityData> getAllCities() throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
				.append("City.Id, ")
				.append("City.StateId, ")
				.append("City.Name, ")
				.append("City.Remarks, ")
				.append("State.Name AS StateName, ")
				.append("Country.Id AS CountryId, ")
				.append("Country.Name AS CountryName ")
		.append("FROM City ")
		.append("LEFT OUTER JOIN State ON ")
			.append("State.Id = City.StateId ")
		.append("LEFT OUTER JOIN Country ON ")
			.append("Country.Id = State.CountryId ")
		.append("ORDER BY City.Id DESC ");

		List<CityData> cityList = this.getTemplete().query(sql.toString(), new RowMapper<CityData>() {
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
	}

}
