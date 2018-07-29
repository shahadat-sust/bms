package com.bms.service.dao.booking;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.booking.PaymentMethodData;

public interface IPaymentMethodDao {
	
	boolean create(PaymentMethodData paymentMethodData) throws BmsSqlException;
	
	boolean update(PaymentMethodData paymentMethodData) throws BmsSqlException;
	
	boolean delete(long paymentMethodId) throws BmsSqlException;
	
	PaymentMethodData getPaymentMethodById(long paymentMethodId) throws BmsSqlException;
	
	List<PaymentMethodData> getAllPaymentMethods() throws BmsSqlException;
	
}
