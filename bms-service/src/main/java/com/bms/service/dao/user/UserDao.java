package com.bms.service.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.common.Constants;
import com.bms.common.util.StringUtils;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.EmailAddressData;
import com.bms.service.data.PhoneNumberData;
import com.bms.service.data.PostalAddressData;
import com.bms.service.data.provider.ProviderData;
import com.bms.service.data.user.UserData;
import com.bms.service.data.user.UserProfileData;

@Repository("userDao")
public class UserDao extends BaseDao implements IUserDao {

	@Autowired
	@Qualifier("userQuery")
	private Properties userQuery;

	@Override
	public long create(UserData userData) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setString(1, userData.getUsername());
					ps.setString(2, userData.getPassword());
					ps.setBoolean(3, userData.isActive());
					ps.setInt(4, userData.getStatus());
					ps.setLong(5, userData.getCreatedBy() > 0 ? userData.getCreatedBy() : null);
					ps.setTimestamp(6, new java.sql.Timestamp(userData.getCreatedOn().getTime()));
					ps.setLong(7, userData.getUpdatedBy() > 0 ? userData.getUpdatedBy() : null);
					ps.setTimestamp(8, new java.sql.Timestamp(userData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(UserData userData) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.update");
			return this.getTemplete().update(sql, 
					userData.getUsername(), 
					userData.getPassword(),
					userData.isActive() ? 1 : 0,
					userData.getStatus(),
					userData.getUpdatedBy(),
					new java.sql.Timestamp(userData.getUpdatedOn().getTime()),
					userData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(UserData userData) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.delete");
			return this.getTemplete().update(sql, 
					Constants.STATUS_NOT_EXIST,
					userData.getUpdatedBy(),
					new java.sql.Timestamp(userData.getUpdatedOn().getTime()),
					userData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public UserData getUserDataById(long userId) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.getUserDataById");
			Object[] params = new Object[] {userId};
			
			List<UserData> userList = this.getTemplete().query(sql, params, new RowMapper<UserData>() {
				@Override
				public UserData mapRow(ResultSet rs, int index) throws SQLException {
					UserData userData = new UserData();
					userData.setId(rs.getLong(1));
					userData.setUsername(rs.getString(2));
					userData.setPassword(rs.getString(3));
					userData.setActive(rs.getBoolean(4));
					userData.setStatus(rs.getInt(5));
					return userData;
				}
			});
			
			if (userList.isEmpty()) {
			  return null;
			} else if (userList.size() == 1) {
			  return userList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<UserData> getAllUserDatas() throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.getAllUserDatas");
			List<UserData> userList = this.getTemplete().query(sql, new RowMapper<UserData>() {
				@Override
				public UserData mapRow(ResultSet rs, int index) throws SQLException {
					UserData userData = new UserData();
					userData.setId(rs.getLong(1));
					userData.setUsername(rs.getString(2));
					userData.setPassword(rs.getString(3));
					userData.setActive(rs.getBoolean(4));
					userData.setStatus(rs.getInt(5));
					userData.setUserProfileData(new UserProfileData());
					long userProfileId = rs.getLong(6);
					if(userProfileId > 0) {
						userData.getUserProfileData().setId(userProfileId);
						userData.getUserProfileData().setUserId(userData.getId());
						userData.getUserProfileData().setFirstName(rs.getString(7));
						userData.getUserProfileData().setLastName(rs.getString(8));
						userData.getUserProfileData().setBirthDay(rs.getTimestamp(9));
						userData.getUserProfileData().setGender(rs.getInt(10));
						userData.getUserProfileData().setSecurityNumber(rs.getString(11));
						userData.getUserProfileData().setPassportNumber(rs.getString(12));
						userData.getUserProfileData().setDrivingLicenceNumber(rs.getString(13));
						userData.getUserProfileData().setCaption(rs.getString(14));
					}
					return userData;
				}
			});
			return userList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public boolean isUsernameAvailable(long userId, String username) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.isUsernameAvailable");
			Object[] params = new Object[] {userId, userId, username, userId, username};
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

	@Override
	public List<UserData> getSearchUser(String name, String username, String email, String code, String number) throws BmsSqlException {
		try {
			String sql = userQuery.getProperty("user.getSearchUser");
			Object[] params = new Object[] {
					"%" + (StringUtils.isNullEmptyOrWhiteSpace(name) ? "" : name.trim()) + "%", 
					"%" + (StringUtils.isNullEmptyOrWhiteSpace(name) ? "" : name.trim()) + "%", 
					"%" + (StringUtils.isNullEmptyOrWhiteSpace(username) ? "" : username.trim()) + "%", 
					"%" + (StringUtils.isNullEmptyOrWhiteSpace(email) ? "" : email.trim()) + "%", 
					"%" + (StringUtils.isNullEmptyOrWhiteSpace(code) ? "" : code.trim()) + "%", 
					"%" + (StringUtils.isNullEmptyOrWhiteSpace(number) ? "" : number.trim()) + "%"};
			List<UserData> userList = this.getTemplete().query(sql, params, new RowMapper<UserData>() {
				@Override
				public UserData mapRow(ResultSet rs, int index) throws SQLException {
					UserData userData = new UserData();
					userData.setId(rs.getLong(1));
					userData.setUsername(rs.getString(2));
					
					UserProfileData userProfileData =  new UserProfileData();
					userProfileData.setFirstName(rs.getString(3));
					userProfileData.setLastName(rs.getString(4));
					userData.setUserProfileData(userProfileData);
					
					EmailAddressData emailAddressData = new EmailAddressData();
					emailAddressData.setEmail(rs.getString(5));
					userData.setEmailAddressDatas(new ArrayList<>());
					userData.getEmailAddressDatas().add(emailAddressData);
					
					PhoneNumberData phoneNumberData = new PhoneNumberData();
					phoneNumberData.setCode(rs.getString(6));
					phoneNumberData.setNumber(rs.getString(7));
					userData.setPhoneNumberDatas(new ArrayList<>());
					userData.getPhoneNumberDatas().add(phoneNumberData);
					return userData;
				}
			});
			return userList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
}
