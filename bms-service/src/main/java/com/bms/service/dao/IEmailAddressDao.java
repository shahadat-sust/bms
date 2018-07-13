package com.bms.service.dao;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.EmailAddressData;

public interface IEmailAddressDao {
	
	long create(long userId, long providerId, EmailAddressData emailAddressData) throws BmsSqlException;
	
	boolean update(EmailAddressData emailAddressData) throws BmsSqlException;
	
	boolean delete(long emailAddressId) throws BmsSqlException;
	
	EmailAddressData getEmailAddressById(long emailAddressId) throws BmsSqlException;
	
	EmailAddressData getEmailAddressByEmail(String email) throws BmsSqlException;
	
	List<EmailAddressData> getAllEmailAddresses() throws BmsSqlException;
	
}
