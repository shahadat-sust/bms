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
import com.bms.service.data.booking.BookingTypeData;

@Repository("bookingTypeDao")
public class BookingTypeDao extends BaseDao implements IBookingTypeDao {

	@Override
	public boolean create(BookingTypeData bookingTypeData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO BookingType ")
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
					ps.setLong(1, bookingTypeData.getId());
					ps.setString(2, bookingTypeData.getName());
					ps.setString(3, bookingTypeData.getRemarks());
					ps.setLong(4, bookingTypeData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(bookingTypeData.getCreatedOn().getTime()));
					ps.setLong(6, bookingTypeData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(bookingTypeData.getUpdatedOn().getTime()));
					return ps;
				}
			}) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(BookingTypeData bookingTypeData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("UPDATE BookingType SET ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
					bookingTypeData.getName(), 
					bookingTypeData.getRemarks(),
					bookingTypeData.getUpdatedBy(),
					new java.sql.Timestamp(bookingTypeData.getUpdatedOn().getTime()),
					bookingTypeData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long bookingTypeId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM BookingType WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), bookingTypeId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public BookingTypeData getBookingTypeById(long bookingTypeId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM BookingType ")
			.append("WHERE ")
			.append("Id = ?");
			
			Object[] params = new Object[] {bookingTypeId};
			List<BookingTypeData> bookingTypeList = this.getTemplete().query(sql.toString(), params, new RowMapper<BookingTypeData>() {
				@Override
				public BookingTypeData mapRow(ResultSet rs, int index) throws SQLException {
					BookingTypeData bookingTypeData = new BookingTypeData();
					bookingTypeData.setId(rs.getLong(1));
					bookingTypeData.setName(rs.getString(2));
					bookingTypeData.setRemarks(rs.getString(3));
					return bookingTypeData;
				}
			});
			
			if (bookingTypeList.isEmpty()) {
			  return null;
			} else if (bookingTypeList.size() == 1) {
			  return bookingTypeList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<BookingTypeData> getAllBookingTypes() throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM BookingType ")
			.append("ORDER BY Id DESC ");
	
			List<BookingTypeData> bookingTypeList = this.getTemplete().query(sql.toString(), new RowMapper<BookingTypeData>() {
				@Override
				public BookingTypeData mapRow(ResultSet rs, int index) throws SQLException {
					BookingTypeData bookingTypeData = new BookingTypeData();
					bookingTypeData.setId(rs.getLong(1));
					bookingTypeData.setName(rs.getString(2));
					bookingTypeData.setRemarks(rs.getString(3));
					return bookingTypeData;
				}
			});
			
			return bookingTypeList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
