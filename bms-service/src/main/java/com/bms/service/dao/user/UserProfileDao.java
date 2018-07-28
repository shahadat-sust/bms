package com.bms.service.dao.user;

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
import com.bms.service.dao.BaseDao;
import com.bms.service.data.user.UserProfileData;

@Repository("userProfileDao")
public class UserProfileDao extends BaseDao implements IUserProfileDao {

	@Override
	public long create(UserProfileData userProfileData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO UserProfile ")
			.append("( ")
				.append("UserId, ")
				.append("FirstName, ")
				.append("LastName, ")
				.append("BirthDay, ")
				.append("Gender, ")
				.append("SecurityNumber, ")
				.append("PassportNumber, ")
				.append("DrivingLicenceNumber, ")
				.append("Caption, ")
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
			StringBuilder sql = new StringBuilder()
			.append("UPDATE UserProfile SET ")
				.append("FirstName = ?, ")
				.append("LastName = ?, ")
				.append("BirthDay = ?, ")
				.append("Gender = ?, ")
				.append("SecurityNumber = ?, ")
				.append("PassportNumber = ?, ")
				.append("DrivingLicenceNumber = ?, ")
				.append("Caption = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?")
			.append("UserId = ?");
			return this.getTemplete().update(sql.toString(), 
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
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM UserProfile WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), userProfileId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public UserProfileData getUserProfileByUserId(long userId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("UserId, ")
				.append("FirstName, ")
				.append("LastName, ")
				.append("BirthDay, ")
				.append("Gender, ")
				.append("SecurityNumber, ")
				.append("PassportNumber, ")
				.append("DrivingLicenceNumber, ")
				.append("Caption ")
			.append("FROM UserProfile ")
			.append("WHERE ")
			.append("UserId = ?");
			
			Object[] params = new Object[] {userId};
			List<UserProfileData> userProfileList = this.getTemplete().query(sql.toString(), params, new RowMapper<UserProfileData>() {
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
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("UserId, ")
				.append("FirstName, ")
				.append("LastName, ")
				.append("BirthDay, ")
				.append("Gender, ")
				.append("SecurityNumber, ")
				.append("PassportNumber, ")
				.append("DrivingLicenceNumber, ")
				.append("Caption ")
			.append("FROM UserProfile ");
	
			List<UserProfileData> userProfileList = this.getTemplete().query(sql.toString(), new RowMapper<UserProfileData>() {
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
