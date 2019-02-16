package com.bms.service.dao.user;

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
import com.bms.service.data.user.UserRoleData;

@Repository("userRoleDao")
public class UserRoleDao extends BaseDao implements IUserRoleDao {

	@Autowired
	@Qualifier("userRoleQuery")
	private Properties userRoleQuery;
	
	@Override
	public long create(UserRoleData userRoleData) throws BmsSqlException {
		try {
			String sql = userRoleQuery.getProperty("userRole.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, userRoleData.getUserId());
					ps.setLong(2, userRoleData.getRoleId());
					ps.setLong(3, userRoleData.getCreatedBy());
					ps.setTimestamp(4, new java.sql.Timestamp(userRoleData.getCreatedOn().getTime()));
					ps.setLong(5, userRoleData.getUpdatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(userRoleData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(UserRoleData userRoleData) throws BmsSqlException {
		try {
			String sql = userRoleQuery.getProperty("userRole.update");
			return this.getTemplete().update(sql, 
					userRoleData.getUserId(),
					userRoleData.getRoleId(),
					userRoleData.getUpdatedBy(),
					new java.sql.Timestamp(userRoleData.getUpdatedOn().getTime()),
					userRoleData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long userRoleId) throws BmsSqlException {
		try {
			String sql = userRoleQuery.getProperty("userRole.delete");
			return this.getTemplete().update(sql.toString(), userRoleId) == 1;
		} catch (DataIntegrityViolationException e) {
			throw new BmsSqlException(SqlConstants.ERROR_DELETE_FOREIGN_KEY_CONSTRAINT_FAIL, e);
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public UserRoleData getUserRoleByUserId(long userId) throws BmsSqlException {
		try {
			String sql = userRoleQuery.getProperty("userRole.getUserRoleByUserId");
			Object[] params = new Object[] {userId};
			List<UserRoleData> list = this.getTemplete().query(sql, params, new RowMapper<UserRoleData>() {
				@Override
				public UserRoleData mapRow(ResultSet rs, int index) throws SQLException {
					UserRoleData userRoleData = new UserRoleData();
					userRoleData.setId(rs.getLong(1));
					userRoleData.setUserId(rs.getLong(2));
					userRoleData.setRoleId(rs.getLong(3));
					userRoleData.setRoleName(rs.getString(4));
					userRoleData.setPriority(rs.getInt(5));
					return userRoleData;
				}
			});
			
			if (list.isEmpty()) {
			  return null;
			} else if (list.size() == 1) {
			  return list.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
