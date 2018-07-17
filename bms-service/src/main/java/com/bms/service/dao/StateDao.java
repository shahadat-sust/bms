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
				ps.setDate(5, new java.sql.Date(stateData.getCreatedOn().getTime()));
				ps.setLong(6, stateData.getUpdatedBy());
				ps.setDate(7, new java.sql.Date(stateData.getUpdatedOn().getTime()));
				return ps;
			}
		}, holder);
		return holder.getKey().longValue();
	}

	@Override
	public boolean update(StateData stateData) throws BmsSqlException {
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
				new java.sql.Date(stateData.getUpdatedOn().getTime()),
				stateData.getId()) == 1;
	}

	@Override
	public boolean delete(long stateId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("DELETE FROM State WHERE Id = ?");

		return this.getTemplete().update(sql.toString(), stateId) == 1;
	}

	@Override
	public StateData getStateById(long stateId) throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("CountryId, ")
			.append("Name, ")
			.append("Remarks ")
		.append("FROM State ")
		.append("WHERE ")
		.append("Id = ?");
		
		Object[] params = new Object[] {stateId};
		List<StateData> stateList = this.getTemplete().query(sql.toString(), params, new RowMapper<StateData>() {
			@Override
			public StateData mapRow(ResultSet rs, int index) throws SQLException {
				StateData stateData = new StateData();
				stateData.setId(rs.getLong(1));
				stateData.setCountryId(rs.getLong(2));
				stateData.setName(rs.getString(3));
				stateData.setRemarks(rs.getString(4));
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
	}

	@Override
	public List<StateData> getAllStates() throws BmsSqlException {
		StringBuilder sql = new StringBuilder()
		.append("SELECT ")
			.append("Id, ")
			.append("CountryId, ")
			.append("Name, ")
			.append("Remarks ")
		.append("FROM State ");

		List<StateData> stateList = this.getTemplete().query(sql.toString(), new RowMapper<StateData>() {
			@Override
			public StateData mapRow(ResultSet rs, int index) throws SQLException {
				StateData stateData = new StateData();
				stateData.setId(rs.getLong(1));
				stateData.setCountryId(rs.getLong(2));
				stateData.setName(rs.getString(3));
				stateData.setRemarks(rs.getString(4));
				return stateData;
			}
		});
		
		return stateList;
	}

}
