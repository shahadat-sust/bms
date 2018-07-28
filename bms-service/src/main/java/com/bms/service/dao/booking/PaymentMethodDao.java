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
import com.bms.service.data.booking.PaymentMethodData;

@Repository("paymentMethodDao")
public class PaymentMethodDao extends BaseDao implements IPaymentMethodDao {

	@Override
	public long create(PaymentMethodData paymentMethodData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("INSERT INTO PaymentMethod ")
			.append("( ")
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
				.append("? ")
			.append(")");
			KeyHolder holder = new GeneratedKeyHolder();
			this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql.toString(), new String[] { "Id" });
					ps.setString(1, paymentMethodData.getName());
					ps.setString(2, paymentMethodData.getRemarks());
					ps.setLong(3, paymentMethodData.getCreatedBy());
					ps.setTimestamp(4, new java.sql.Timestamp(paymentMethodData.getCreatedOn().getTime()));
					ps.setLong(5, paymentMethodData.getUpdatedBy());
					ps.setTimestamp(6, new java.sql.Timestamp(paymentMethodData.getUpdatedOn().getTime()));
					return ps;
				}
			}, holder);
			return holder.getKey().longValue();
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(PaymentMethodData paymentMethodData) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("UPDATE PaymentMethod SET ")
				.append("Name = ?, ")
				.append("Remarks = ?, ")
				.append("UpdatedBy = ?, ")
				.append("UpdatedOn = ? ")
			.append("WHERE ")
			.append("Id = ?");
			return this.getTemplete().update(sql.toString(), 
					paymentMethodData.getName(), 
					paymentMethodData.getRemarks(),
					paymentMethodData.getUpdatedBy(),
					new java.sql.Timestamp(paymentMethodData.getUpdatedOn().getTime()),
					paymentMethodData.getId()) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean delete(long paymentMethodId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("DELETE FROM PaymentMethod WHERE Id = ?");
	
			return this.getTemplete().update(sql.toString(), paymentMethodId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public PaymentMethodData getPaymentMethodById(long paymentMethodId) throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM PaymentMethod ")
			.append("WHERE ")
			.append("Id = ?");
			
			Object[] params = new Object[] {paymentMethodId};
			List<PaymentMethodData> paymentMethodList = this.getTemplete().query(sql.toString(), params, new RowMapper<PaymentMethodData>() {
				@Override
				public PaymentMethodData mapRow(ResultSet rs, int index) throws SQLException {
					PaymentMethodData paymentMethodData = new PaymentMethodData();
					paymentMethodData.setId(rs.getLong(1));
					paymentMethodData.setName(rs.getString(2));
					paymentMethodData.setRemarks(rs.getString(3));
					return paymentMethodData;
				}
			});
			
			if (paymentMethodList.isEmpty()) {
			  return null;
			} else if (paymentMethodList.size() == 1) {
			  return paymentMethodList.get(0);
			} else {
			  throw new BmsSqlException("Incorrect result size: expected 1, actual greater than 0!");   
			}
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public List<PaymentMethodData> getAllPaymentMethods() throws BmsSqlException {
		try {
			StringBuilder sql = new StringBuilder()
			.append("SELECT ")
				.append("Id, ")
				.append("Name, ")
				.append("Remarks ")
			.append("FROM PaymentMethod ")
			.append("ORDER BY Id DESC ");
	
			List<PaymentMethodData> paymentMethodList = this.getTemplete().query(sql.toString(), new RowMapper<PaymentMethodData>() {
				@Override
				public PaymentMethodData mapRow(ResultSet rs, int index) throws SQLException {
					PaymentMethodData paymentMethodData = new PaymentMethodData();
					paymentMethodData.setId(rs.getLong(1));
					paymentMethodData.setName(rs.getString(2));
					paymentMethodData.setRemarks(rs.getString(3));
					return paymentMethodData;
				}
			});
			
			return paymentMethodList;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

}
