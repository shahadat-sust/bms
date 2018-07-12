package com.bms.service.dao.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;

public class AuthenticationDao extends BaseDao implements IAuthenticationDao {

	@Override
	public long getAuthorizedAdmin(String username, String password) throws BmsSqlException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ")
			.append("User.ID ")
		.append("FROM User ")
		.append("WHERE ")
			.append("user.Username = ? ")
			.append("AND user.Email = ? ");

		List<Long> userIDs = getTemplete().query(sql.toString(), new Object[] {password, username}, new RowMapper<Long> () {
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
	}

}
