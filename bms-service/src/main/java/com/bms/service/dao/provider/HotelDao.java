package com.bms.service.dao.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.provider.HotelData;

@Service("hotelDao")
public class HotelDao extends BaseDao implements IHotelDao {

	@Autowired
	@Qualifier("hotelQuery")
	private Properties hotelQuery;
	
	@Override
	public long create(HotelData hotelData) throws BmsSqlException {
		try {
			String sql = hotelQuery.getProperty("hotel.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setLong(1, hotelData.getProviderId());
					ps.setInt(2, hotelData.getStarRating());
					ps.setInt(3, hotelData.getNumberOfFloor());
					ps.setDouble(4, hotelData.getLatitude());
					ps.setDouble(5, hotelData.getLongitude());
					ps.setTime(6, Time.valueOf(hotelData.getCheckInTime()));
					ps.setTime(7, Time.valueOf(hotelData.getCheckOutTime()));
					ps.setString(8, hotelData.getWebsite());
					ps.setLong(9, hotelData.getCreatedBy());
					ps.setTimestamp(10, new Timestamp(hotelData.getCreatedOn().getTime()));
					ps.setLong(11, hotelData.getUpdatedBy());
					ps.setTimestamp(12, new Timestamp(hotelData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(HotelData hotelData) throws BmsSqlException {
		try {
			String sql = hotelQuery.getProperty("hotel.update");
			return this.getTemplete().update(sql,
					hotelData.getProviderId(),
					hotelData.getStarRating(),
					hotelData.getNumberOfFloor(),
					hotelData.getLatitude(),
					hotelData.getLongitude(),
					Time.valueOf(hotelData.getCheckInTime()),
					Time.valueOf(hotelData.getCheckOutTime()),
					hotelData.getWebsite(),
					hotelData.getUpdatedBy(),
					new Timestamp(hotelData.getUpdatedOn().getTime()),
					hotelData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public HotelData getHotelDataByProviderId(long hotelId) throws BmsSqlException {
		try {
			String sql = hotelQuery.getProperty("hotel.getHotelDataByProviderId");
			Object[] params = new Object[] {hotelId};
			List<HotelData> hotelList = this.getTemplete().query(sql, params, new RowMapper<HotelData> () {
				@Override
				public HotelData mapRow(ResultSet rs, int rowNum) throws SQLException {
					HotelData hotelData = new HotelData();
					hotelData.setId(rs.getLong(1));
					hotelData.setProviderId(rs.getLong(2));
					hotelData.setStarRating(rs.getInt(3));
					hotelData.setNumberOfFloor(rs.getInt(4));
					hotelData.setLatitude(rs.getDouble(5));
					hotelData.setLongitude(rs.getDouble(6));
					hotelData.setCheckInTime(rs.getTime(7).toLocalTime());
					hotelData.setCheckOutTime(rs.getTime(8).toLocalTime());
					hotelData.setWebsite(rs.getString(9));
					return hotelData;
				}	
			});
			
			if (hotelList.isEmpty()) {
				return null;
			} else if (hotelList.size() == 1) {
				return hotelList.get(0);
			} else {
				throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<HotelData> getAllHotelDatas() throws BmsSqlException {
		try {
			String sql = hotelQuery.getProperty("hotel.getAllHotelDatas");
			List<HotelData> hotelList = this.getTemplete().query(sql, new RowMapper<HotelData> () {
				@Override
				public HotelData mapRow(ResultSet rs, int rowNum) throws SQLException {
					HotelData hotelData = new HotelData();
					hotelData.setId(rs.getLong(1));
					hotelData.setProviderId(rs.getLong(2));
					hotelData.setStarRating(rs.getInt(3));
					hotelData.setNumberOfFloor(rs.getInt(4));
					hotelData.setLatitude(rs.getDouble(5));
					hotelData.setLongitude(rs.getDouble(6));
					hotelData.setCheckInTime(rs.getTime(7).toLocalTime());
					hotelData.setCheckOutTime(rs.getTime(8).toLocalTime());
					hotelData.setWebsite(rs.getString(9));
					return hotelData;
				}	
			});
			
			return hotelList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
