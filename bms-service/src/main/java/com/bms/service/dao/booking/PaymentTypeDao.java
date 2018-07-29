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
import com.bms.service.data.booking.PaymentTypeData;

@Repository("paymentTypeDao")
public class PaymentTypeDao extends BaseDao implements IPaymentTypeDao {

	@Override
	public boolean create(PaymentTypeData paymentTypeData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO PaymentType ")
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
					ps.setLong(1, paymentTypeData.getId());
					ps.setString(2, paymentTypeData.getName());
					ps.setString(3, paymentTypeData.getRemarks());
					ps.setLong(4, paymentTypeData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(paymentTypeData.getCreatedOn().getTime()));
					ps.setLong(6, paymentTypeData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(paymentTypeData.getUpdatedOn().getTime()));
					return ps;
				}
			}) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(PaymentTypeData paymentTypeData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("UPDATE PaymentType SET ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
					paymentTypeData.getName(), 
					paymentTypeData.getRemarks(),
					paymentTypeData.getUpdatedBy(),
					new java.sql.Timestamp(paymentTypeData.getUpdatedOn().getTime()),
					paymentTypeData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long paymentTypeId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM PaymentType WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), paymentTypeId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public PaymentTypeData getPaymentTypeById(long paymentTypeId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM PaymentType ")
			.append("WHERE ")
			.append("Id = ?");
			
			Object[] params = new Object[] {paymentTypeId};
			List<PaymentTypeData> paymentTypeList = this.getTemplete().query(sql.toString(), params, new RowMapper<PaymentTypeData>() {
				@Override
				public PaymentTypeData mapRow(ResultSet rs, int index) throws SQLException {
					PaymentTypeData paymentTypeData = new PaymentTypeData();
					paymentTypeData.setId(rs.getLong(1));
					paymentTypeData.setName(rs.getString(2));
					paymentTypeData.setRemarks(rs.getString(3));
					return paymentTypeData;
				}
			});
			
			if (paymentTypeList.isEmpty()) {
			  return null;
			} else if (paymentTypeList.size() == 1) {
			  return paymentTypeList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<PaymentTypeData> getAllPaymentTypes() throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM PaymentType ")
			.append("ORDER BY Id DESC ");
	
			List<PaymentTypeData> paymentTypeList = this.getTemplete().query(sql.toString(), new RowMapper<PaymentTypeData>() {
				@Override
				public PaymentTypeData mapRow(ResultSet rs, int index) throws SQLException {
					PaymentTypeData paymentTypeData = new PaymentTypeData();
					paymentTypeData.setId(rs.getLong(1));
					paymentTypeData.setName(rs.getString(2));
					paymentTypeData.setRemarks(rs.getString(3));
					return paymentTypeData;
				}
			});
			
			return paymentTypeList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
