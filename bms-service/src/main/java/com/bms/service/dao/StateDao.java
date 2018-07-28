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
import com.bms.service.data.StateData;

@Repository("stateDao")
public class StateDao extends BaseDao implements IStateDao {

	@Override
	public long create(StateData stateData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO State ")
			.append("( ")
				.append("CountryId, ")
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
					ps.setLong(1, stateData.getCountryId());
					ps.setString(2, stateData.getName());
					ps.setString(3, stateData.getRemarks());
					ps.setLong(4, stateData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(stateData.getCreatedOn().getTime()));
					ps.setLong(6, stateData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(stateData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(StateData stateData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("UPDATE State SET ")
				.append("CountryId = ?, ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
					stateData.getCountryId(), 
					stateData.getName(), 
					stateData.getRemarks(),
					stateData.getUpdatedBy(),
					new java.sql.Timestamp(stateData.getUpdatedOn().getTime()),
					stateData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long stateId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM State WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), stateId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public StateData getStateById(long stateId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("State.Id, ")
				.append("State.CountryId, ")
				.append("State.Name, ")
				.append("State.Remarks, ")
				.append("Country.Name AS CountryName ")
			.append("FROM State ")
			.append("LEFT OUTER JOIN Country ON ")
				.append("Country.Id = State.CountryId ")
			.append("WHERE ")
			.append("State.Id = ?");
			
			Object[] params = new Object[] {stateId};
			List<StateData> stateList = this.getTemplete().query(sql.toString(), params, new RowMapper<StateData>() {
				@Override
				public StateData mapRow(ResultSet rs, int index) throws SQLException {
					StateData stateData = new StateData();
					stateData.setId(rs.getLong(1));
					stateData.setCountryId(rs.getLong(2));
					stateData.setName(rs.getString(3));
					stateData.setRemarks(rs.getString(4));
					stateData.setCountryName(rs.getString(5));
					return stateData;
				}
			});
			
			if (stateList.isEmpty()) {
			  return null;
			} else if (stateList.size() == 1) {
			  return stateList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	public List<StateData> getStatesByCountryId(long countryId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
					.append("State.Id, ")
					.append("State.CountryId, ")
					.append("State.Name, ")
					.append("State.Remarks, ")
					.append("Country.Name AS CountryName ")
			.append("FROM State ")
			.append("LEFT OUTER JOIN Country ON ")
				.append("Country.Id = State.CountryId ")
			.append("WHERE State.CountryId = ? ")
			.append("ORDER BY State.Name ASC ");
	
			Object[] params = new Object[] {countryId};
			List<StateData> stateList = this.getTemplete().query(sql.toString(), params, new RowMapper<StateData>() {
				@Override
				public StateData mapRow(ResultSet rs, int index) throws SQLException {
					StateData stateData = new StateData();
					stateData.setId(rs.getLong(1));
					stateData.setCountryId(rs.getLong(2));
					stateData.setName(rs.getString(3));
					stateData.setRemarks(rs.getString(4));
					stateData.setCountryName(rs.getString(5));
					return stateData;
				}
			});
			
			return stateList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<StateData> getAllStates() throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
					.append("State.Id, ")
					.append("State.CountryId, ")
					.append("State.Name, ")
					.append("State.Remarks, ")
					.append("Country.Name AS CountryName ")
			.append("FROM State ")
			.append("LEFT OUTER JOIN Country ON ")
				.append("Country.Id = State.CountryId ")
			.append("ORDER BY State.Id DESC ");
	
			List<StateData> stateList = this.getTemplete().query(sql.toString(), new RowMapper<StateData>() {
				@Override
				public StateData mapRow(ResultSet rs, int index) throws SQLException {
					StateData stateData = new StateData();
					stateData.setId(rs.getLong(1));
					stateData.setCountryId(rs.getLong(2));
					stateData.setName(rs.getString(3));
					stateData.setRemarks(rs.getString(4));
					stateData.setCountryName(rs.getString(5));
					return stateData;
				}
			});
			
			return stateList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
