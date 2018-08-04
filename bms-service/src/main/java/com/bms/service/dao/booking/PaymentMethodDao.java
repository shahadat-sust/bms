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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.bms.service.BmsSqlException;
import com.bms.service.dao.BaseDao;
import com.bms.service.data.booking.PaymentMethodData;

@Repository("paymentMethodDao")
public class PaymentMethodDao extends BaseDao implements IPaymentMethodDao {

	@Autowired
	@Qualifier("paymentMethodQuery")
	private Properties paymentMethodQuery;
	
	@Override
	public boolean create(PaymentMethodData paymentMethodData) throws BmsSqlException {
		try {
			String sql = paymentMethodQuery.getProperty("paymentMethod.create");
			return this.getTemplete().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setLong(1, paymentMethodData.getId());
					ps.setString(2, paymentMethodData.getName());
					ps.setString(3, paymentMethodData.getRemarks());
					ps.setLong(4, paymentMethodData.getCreatedBy());
					ps.setTimestamp(5, new java.sql.Timestamp(paymentMethodData.getCreatedOn().getTime()));
					ps.setLong(6, paymentMethodData.getUpdatedBy());
					ps.setTimestamp(7, new java.sql.Timestamp(paymentMethodData.getUpdatedOn().getTime()));
					return ps;
				}
			}) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public boolean update(PaymentMethodData paymentMethodData) throws BmsSqlException {
		try {
			String sql = paymentMethodQuery.getProperty("paymentMethod.update");
			return this.getTemplete().update(sql, 
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
			String sql = paymentMethodQuery.getProperty("paymentMethod.delete");
			return this.getTemplete().update(sql, paymentMethodId) == 1;
		} catch (Exception e) {
			throw new BmsSqlException(e);
		}
	}

	@Override
	public PaymentMethodData getPaymentMethodById(long paymentMethodId) throws BmsSqlException {
		try {
			String sql = paymentMethodQuery.getProperty("paymentMethod.getPaymentMethodById");
			Object[] params = new Object[] {paymentMethodId};
			List<PaymentMethodData> paymentMethodList = this.getTemplete().query(sql, params, new RowMapper<PaymentMethodData>() {
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
			String sql = paymentMethodQuery.getProperty("paymentMethod.getAllPaymentMethods");
			List<PaymentMethodData> paymentMethodList = this.getTemplete().query(sql, new RowMapper<PaymentMethodData>() {
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
