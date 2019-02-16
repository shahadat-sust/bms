package com.bms.service.dao.permission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.SqlConstants;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.permission.PolicyData;

@Repository("policyDao")
public class PolicyDao extends BaseDao implements IPolicyDao {

	@Autowired
	@Qualifier("policyQuery")
	private Properties policyQuery;
	
	@Override
	public long create(PolicyData policyData) throws BmsSqlException {
		try {
			String sql = policyQuery.getProperty("policy.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setString(1, policyData.getName());
					ps.setString(2, policyData.getCode());
					ps.setInt(3, policyData.getType());
					ps.setLong(4, policyData.getParentId() > 0 ? policyData.getParentId() : null);
					ps.setString(5, policyData.getRemarks());
					ps.setLong(6, policyData.getCreatedBy());
					ps.setDate(7, new java.sql.Date(policyData.getCreatedOn().getTime()));
					ps.setLong(8, policyData.getUpdatedBy());
					ps.setDate(9, new java.sql.Date(policyData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(PolicyData policyData) throws BmsSqlException {
		try {
			String sql = policyQuery.getProperty("policy.update");
			return this.getTemplete().update(sql, 
					policyData.getName(), 
					policyData.getCode(), 
					policyData.getType(),
					policyData.getParentId() > 0 ? policyData.getParentId() : null,
					policyData.getRemarks(),
					policyData.getUpdatedBy(),
					new java.sql.Date(policyData.getUpdatedOn().getTime()),
					policyData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long policyId) throws BmsSqlException {
		try {
			String sql = policyQuery.getProperty("policy.delete");
			return this.getTemplete().update(sql, policyId) == 1;
		} catch (DataIntegrityViolationException e) {
			throw new BmsSqlException(SqlConstants.ERROR_DELETE_FOREIGN_KEY_CONSTRAINT_FAIL, e);
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public PolicyData getPolicyById(long policyId) throws BmsSqlException {
		try {
			String sql = policyQuery.getProperty("policy.getPolicyById");
			Object[] params = new Object[] {policyId};
			List<PolicyData> policyList = this.getTemplete().query(sql, params, new RowMapper<PolicyData>() {
				@Override
				public PolicyData mapRow(ResultSet rs, int index) throws SQLException {
					PolicyData policyData = new PolicyData();
					policyData.setId(rs.getLong(1));
					policyData.setName(rs.getString(2));
					policyData.setCode(rs.getString(3));
					policyData.setType(rs.getInt(4));
					policyData.setParentId(rs.getLong(5));
					policyData.setRemarks(rs.getString(6));
					return policyData;
				}
			});
			
			if (policyList.isEmpty()) {
			  return null;
			} else if (policyList.size() == 1) {
			  return policyList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<PolicyData> getAllPolicies() throws BmsSqlException {
		try {
			String sql = policyQuery.getProperty("policy.getAllPolicies");
			List<PolicyData> policyList = this.getTemplete().query(sql, new RowMapper<PolicyData>() {
				@Override
				public PolicyData mapRow(ResultSet rs, int index) throws SQLException {
					PolicyData policyData = new PolicyData();
					policyData.setId(rs.getLong(1));
					policyData.setName(rs.getString(2));
					policyData.setCode(rs.getString(3));
					policyData.setType(rs.getInt(4));
					policyData.setParentId(rs.getLong(5));
					policyData.setRemarks(rs.getString(6));
					return policyData;
				}
			});
			return policyList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public boolean isAvailable(long id, String code) throws BmsSqlException {
		try {
			String sql = policyQuery.getProperty("policy.isAvailable");
			Object[] params = new Object[] {id, id, code, id, code};
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
