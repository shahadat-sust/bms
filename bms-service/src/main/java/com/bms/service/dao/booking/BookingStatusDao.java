package com.bms.service.dao.booking;

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
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.booking.BookingStatusData;

@Repository("bookingStatusDao")
public class BookingStatusDao extends BaseDao implements IBookingStatusDao {

	@Autowired
	@Qualifier("bookingStatusQuery")
	private Properties bookingStatusQuery;
	
	@Override
	public boolean create(BookingStatusData bookingStatusData) throws BmsSqlException {
		try {
			String sql = bookingStatusQuery.getProperty("bookingStatus.create");
			return this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setLong(1, bookingStatusData.getId());
					ps.setString(2, bookingStatusData.getName());
					ps.setString(3, bookingStatusData.getRemarks());
					ps.setLong(4, bookingStatusData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(bookingStatusData.getCreatedOn().getTime()));
					ps.setLong(6, bookingStatusData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(bookingStatusData.getUpdatedOn().getTime()));
					return ps;
				}
			}) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(BookingStatusData bookingStatusData) throws BmsSqlException {
		try {
			String sql = bookingStatusQuery.getProperty("bookingStatus.update");
			return this.getTemplete().update(sql, 
					bookingStatusData.getName(), 
					bookingStatusData.getRemarks(),
					bookingStatusData.getUpdatedBy(),
					new java.sql.Timestamp(bookingStatusData.getUpdatedOn().getTime()),
					bookingStatusData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long bookingStatusId) throws BmsSqlException {
		try {
			String sql = bookingStatusQuery.getProperty("bookingStatus.delete");
			return this.getTemplete().update(sql, bookingStatusId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public BookingStatusData getBookingStatusById(long bookingStatusId) throws BmsSqlException {
		try {
			String sql = bookingStatusQuery.getProperty("bookingStatus.getBookingStatusById");
			Object[] params = new Object[] {bookingStatusId};
			List<BookingStatusData> bookingStatusList = this.getTemplete().query(sql, params, new RowMapper<BookingStatusData>() {
				@Override
				public BookingStatusData mapRow(ResultSet rs, int index) throws SQLException {
					BookingStatusData bookingStatusData = new BookingStatusData();
					bookingStatusData.setId(rs.getLong(1));
					bookingStatusData.setName(rs.getString(2));
					bookingStatusData.setRemarks(rs.getString(3));
					return bookingStatusData;
				}
			});
			
			if (bookingStatusList.isEmpty()) {
			  return null;
			} else if (bookingStatusList.size() == 1) {
			  return bookingStatusList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<BookingStatusData> getAllBookingStatuses() throws BmsSqlException {
		try {
			String sql = bookingStatusQuery.getProperty("bookingStatus.getAllBookingStatuses");
			List<BookingStatusData> bookingStatusList = this.getTemplete().query(sql, new RowMapper<BookingStatusData>() {
				@Override
				public BookingStatusData mapRow(ResultSet rs, int index) throws SQLException {
					BookingStatusData bookingStatusData = new BookingStatusData();
					bookingStatusData.setId(rs.getLong(1));
					bookingStatusData.setName(rs.getString(2));
					bookingStatusData.setRemarks(rs.getString(3));
					return bookingStatusData;
				}
			});
			
			return bookingStatusList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
