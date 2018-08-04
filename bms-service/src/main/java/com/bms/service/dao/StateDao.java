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
import com.bms.service.data.StateData;

@Repository("stateDao")
public class StateDao extends BaseDao implements IStateDao {

	@Autowired
	@Qualifier("stateQuery")
	private Properties stateQuery;
	
	@Override
	public long create(StateData stateData) throws BmsSqlException {
		try {
			String sql = stateQuery.getProperty("state.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
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
			String sql = stateQuery.getProperty("state.update");
			return this.getTemplete().update(sql, 
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
			String sql = stateQuery.getProperty("state.delete");
			return this.getTemplete().update(sql, stateId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public StateData getStateById(long stateId) throws BmsSqlException {
		try {
			String sql = stateQuery.getProperty("state.getStateById");
			Object[] params = new Object[] {stateId};
			List<StateData> stateList = this.getTemplete().query(sql, params, new RowMapper<StateData>() {
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
			String sql = stateQuery.getProperty("state.getStatesByCountryId");
			Object[] params = new Object[] {countryId};
			List<StateData> stateList = this.getTemplete().query(sql, params, new RowMapper<StateData>() {
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
			String sql = stateQuery.getProperty("state.getAllStates");
			List<StateData> stateList = this.getTemplete().query(sql, new RowMapper<StateData>() {
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
