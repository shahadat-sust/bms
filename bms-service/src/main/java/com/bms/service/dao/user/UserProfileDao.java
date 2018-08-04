package com.bms.service.dao.user;

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
import com.bms.service.dao.BaseDao;
import com.bms.service.data.user.UserProfileData;

@Repository("userProfileDao")
public class UserProfileDao extends BaseDao implements IUserProfileDao {

	@Autowired
	@Qualifier("userProfileQuery")
	private Properties userProfileQuery;
	
	@Override
	public long create(UserProfileData userProfileData) throws BmsSqlException {
		try {
			String sql = userProfileQuery.getProperty("userProfile.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, userProfileData.getUserId());
					ps.setString(2, userProfileData.getFirstName());
					ps.setString(3, userProfileData.getLastName());
					ps.setDate(4, userProfileData.getBirthDay() != null ? new java.sql.Date(userProfileData.getBirthDay().getTime()) : null);
					ps.setInt(5, userProfileData.getGender());
					ps.setString(6, userProfileData.getSecurityNumber());
					ps.setString(7, userProfileData.getPassportNumber());
					ps.setString(8, userProfileData.getDrivingLicenceNumber());
					ps.setString(9, userProfileData.getCaption());
					ps.setLong(10, userProfileData.getCreatedBy());
					ps.setTimestamp(11, new java.sql.Timestamp(userProfileData.getCreatedOn().getTime()));
					ps.setLong(12, userProfileData.getUpdatedBy());
					ps.setTimestamp(13, new java.sql.Timestamp(userProfileData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(UserProfileData userProfileData) throws BmsSqlException {
		try {
			String sql = userProfileQuery.getProperty("userProfile.update");
			return this.getTemplete().update(sql, 
					userProfileData.getFirstName(),
					userProfileData.getLastName(),
					userProfileData.getBirthDay() != null ? new java.sql.Date(userProfileData.getBirthDay().getTime()) : null,
					userProfileData.getGender(),
					userProfileData.getSecurityNumber(),
					userProfileData.getPassportNumber(),
					userProfileData.getDrivingLicenceNumber(),
					userProfileData.getCaption(),
					userProfileData.getUpdatedBy(),
					new java.sql.Timestamp(userProfileData.getUpdatedOn().getTime()),
					userProfileData.getId(),
					userProfileData.getUserId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long userProfileId) throws BmsSqlException {
		try {
			String sql = userProfileQuery.getProperty("userProfile.delete");
			return this.getTemplete().update(sql, userProfileId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public UserProfileData getUserProfileByUserId(long userId) throws BmsSqlException {
		try {
			String sql = userProfileQuery.getProperty("userProfile.getUserProfileByUserId");
			Object[] params = new Object[] {userId};
			List<UserProfileData> userProfileList = this.getTemplete().query(sql, params, new RowMapper<UserProfileData>() {
				@Override
				public UserProfileData mapRow(ResultSet rs, int index) throws SQLException {
					UserProfileData userDeviceData = new UserProfileData();
					userDeviceData.setId(rs.getLong(1));
					userDeviceData.setUserId(rs.getLong(2));
					userDeviceData.setFirstName(rs.getString(3));
					userDeviceData.setLastName(rs.getString(4));
					userDeviceData.setBirthDay(rs.getTimestamp(5));
					userDeviceData.setGender(rs.getInt(6));
					userDeviceData.setSecurityNumber(rs.getString(7));
					userDeviceData.setPassportNumber(rs.getString(8));
					userDeviceData.setDrivingLicenceNumber(rs.getString(9));
					userDeviceData.setCaption(rs.getString(10));
					return userDeviceData;
				}
			});
			
			if (userProfileList.isEmpty()) {
			  return null;
			} else if (userProfileList.size() == 1) {
			  return userProfileList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<UserProfileData> getAllUserProfileDatas() throws BmsSqlException {
		try {
			String sql = userProfileQuery.getProperty("userProfile.getAllUserProfileDatas");
			List<UserProfileData> userProfileList = this.getTemplete().query(sql, new RowMapper<UserProfileData>() {
				@Override
				public UserProfileData mapRow(ResultSet rs, int index) throws SQLException {
					UserProfileData userDeviceData = new UserProfileData();
					userDeviceData.setId(rs.getLong(1));
					userDeviceData.setUserId(rs.getLong(2));
					userDeviceData.setFirstName(rs.getString(3));
					userDeviceData.setLastName(rs.getString(4));
					userDeviceData.setBirthDay(rs.getTimestamp(5));
					userDeviceData.setGender(rs.getInt(6));
					userDeviceData.setSecurityNumber(rs.getString(7));
					userDeviceData.setPassportNumber(rs.getString(8));
					userDeviceData.setDrivingLicenceNumber(rs.getString(9));
					userDeviceData.setCaption(rs.getString(10));
					return userDeviceData;
				}
			});
			return userProfileList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	

}
