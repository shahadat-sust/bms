package com.bms.service.dao.booking;

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
import com.bms.service.data.booking.BookingStatusData;

@Repository("bookingStatusDao")
public class BookingStatusDao extends BaseDao implements IBookingStatusDao {

	@Override
	public boolean create(BookingStatusData bookingStatusData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO BookingStatus ")
			.append("( ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks, ")
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
				.append("? ")
			.append(")");
			return this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql.toString());
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
			StringBuilder sql = new StringBuilder()
			.append("UPDATE BookingStatus SET ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
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
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM BookingStatus WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), bookingStatusId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public BookingStatusData getBookingStatusById(long bookingStatusId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM BookingStatus ")
			.append("WHERE ")
			.append("Id = ?");
			
			Object[] params = new Object[] {bookingStatusId};
			List<BookingStatusData> bookingStatusList = this.getTemplete().query(sql.toString(), params, new RowMapper<BookingStatusData>() {
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
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM BookingStatus ")
			.append("ORDER BY Id DESC ");
	
			List<BookingStatusData> bookingStatusList = this.getTemplete().query(sql.toString(), new RowMapper<BookingStatusData>() {
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
