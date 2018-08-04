package com.bms.service.dao.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;

@Repository("authenticationDao")
public class AuthenticationDao extends BaseDao implements IAuthenticationDao {

	@Autowired
	@Qualifier("authenticationQuery")
	private Properties authenticationQuery;
	
	@Override
	public long getAuthorizedAdmin(String username, String password) throws BmsSqlException {
		try {
			String sql = authenticationQuery.getProperty("authentication.getAuthorizedAdmin");
			Object[] params = new Object[] {username, password};
			List<Long> userIDs = getTemplete().query(sql, params, new RowMapper<Long> () {
				@Override
				public Long mapRow(ResultSet rs, int index) throws SQLException {
					return rs.getLong(1);
				}
			});
			
			if (userIDs.isEmpty()) {
			  return 0;
			} else if (userIDs.size() == 1) {
			  return userIDs.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
