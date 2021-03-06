package com.bms.service.dao.provider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.bms.common.Constants;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.provider.HotelData;
import com.bms.service.data.provider.ProviderData;

@Service("providerDao")
public class ProviderDao extends BaseDao implements IProviderDao {

	@Autowired
	@Qualifier("providerQuery")
	private Properties providerQuery;
	
	@Override
	public long create(ProviderData providerData) throws BmsSqlException {
		try {
			String sql = providerQuery.getProperty("provider.create");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "Id" });
					ps.setString(1, providerData.getTitle());
					ps.setLong(2, providerData.getProviderTypeId());
					ps.setString(3, providerData.getSpecification());
					ps.setBoolean(4, providerData.isActive());
					ps.setInt(5, providerData.getStatus());
					ps.setLong(6, providerData.getCreatedBy());
					ps.setTimestamp(7, new Timestamp(providerData.getCreatedOn().getTime()));
					ps.setLong(8, providerData.getUpdatedBy());
					ps.setTimestamp(9, new Timestamp(providerData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(ProviderData providerData) throws BmsSqlException {
		try {
			String sql = providerQuery.getProperty("provider.update");
			return this.getTemplete().update(sql,
					providerData.getTitle(),
					providerData.getProviderTypeId(),
					providerData.getSpecification(),
					providerData.isActive() ? 1 : 0,
					providerData.getStatus(),
					providerData.getUpdatedBy(),
					new Timestamp(providerData.getUpdatedOn().getTime()),
					providerData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(ProviderData providerData) throws BmsSqlException {
		try {
			String sql = providerQuery.getProperty("provider.delete"); 
			return this.getTemplete().update(sql, 
					Constants.STATUS_NOT_EXIST,
					providerData.getUpdatedBy(),
					new Timestamp(providerData.getUpdatedOn().getTime())) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public ProviderData getProviderDataById(long providerId) throws BmsSqlException {
		try {
			String sql = providerQuery.getProperty("provider.getProviderDataById");
			Object[] params = new Object[] {providerId};
			List<ProviderData> providerList = this.getTemplete().query(sql, params, new RowMapper<ProviderData> () {
				@Override
				public ProviderData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ProviderData providerData = new ProviderData();
					providerData.setId(rs.getLong(1));
					providerData.setTitle(rs.getString(2));
					providerData.setProviderTypeId(rs.getLong(3));
					providerData.setSpecification(rs.getString(4));
					providerData.setActive(rs.getBoolean(5));
					providerData.setStatus(rs.getInt(6));
					return providerData;
				}	
			});
			
			if (providerList.isEmpty()) {
				return null;
			} else if (providerList.size() == 1) {
				return providerList.get(0);
			} else {
				throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}
	
	@Override
	public List<ProviderData> getProviderDatasByProviderTypeId(long providerTypeId) throws BmsSqlException {
		try {
			String sql = providerQuery.getProperty("provider.getProviderDatasByProviderTypeId");
			Object[] params = new Object[] {providerTypeId};
			List<ProviderData> providerList = this.getTemplete().query(sql, params, new RowMapper<ProviderData> () {
				@Override
				public ProviderData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ProviderData providerData = new ProviderData();
					providerData.setId(rs.getLong(1));
					providerData.setTitle(rs.getString(2));
					providerData.setProviderTypeId(rs.getLong(3));
					providerData.setSpecification(rs.getString(4));
					providerData.setActive(rs.getBoolean(5));
					providerData.setStatus(rs.getInt(6));
					providerData.setHotelData(new HotelData());
					long hotelId = rs.getLong(7);
					if (hotelId > 0) {
						providerData.getHotelData().setId(hotelId);
						providerData.getHotelData().setProviderId(providerData.getId());
						providerData.getHotelData().setStarRating(rs.getInt(8));
						providerData.getHotelData().setNumberOfFloor(rs.getInt(9));
						providerData.getHotelData().setLatitude(rs.getDouble(10));
						providerData.getHotelData().setLongitude(rs.getDouble(11));
						providerData.getHotelData().setCheckInTime(rs.getTime(12).toLocalTime());
						providerData.getHotelData().setCheckOutTime(rs.getTime(13).toLocalTime());
						providerData.getHotelData().setWebsite(rs.getString(14));
					}
					return providerData;
				}	
			});
			
			return providerList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<ProviderData> getAllProviderDatas() throws BmsSqlException {
		try {
			String sql = providerQuery.getProperty("provider.getAllProviderDatas");
			List<ProviderData> providerList = this.getTemplete().query(sql, new RowMapper<ProviderData> () {
				@Override
				public ProviderData mapRow(ResultSet rs, int rowNum) throws SQLException {
					ProviderData providerData = new ProviderData();
					providerData.setId(rs.getLong(1));
					providerData.setTitle(rs.getString(2));
					providerData.setProviderTypeId(rs.getLong(3));
					providerData.setSpecification(rs.getString(4));
					providerData.setActive(rs.getBoolean(5));
					providerData.setStatus(rs.getInt(6));
					providerData.setHotelData(new HotelData());
					long hotelId = rs.getLong(7);
					if (hotelId > 0) {
						providerData.getHotelData().setId(hotelId);
						providerData.getHotelData().setProviderId(providerData.getId());
						providerData.getHotelData().setStarRating(rs.getInt(8));
						providerData.getHotelData().setNumberOfFloor(rs.getInt(9));
						providerData.getHotelData().setLatitude(rs.getDouble(10));
						providerData.getHotelData().setLongitude(rs.getDouble(11));
						providerData.getHotelData().setCheckInTime(rs.getTime(12).toLocalTime());
						providerData.getHotelData().setCheckOutTime(rs.getTime(13).toLocalTime());
						providerData.getHotelData().setWebsite(rs.getString(14));
					}
					return providerData;
				}	
			});
			
			return providerList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
