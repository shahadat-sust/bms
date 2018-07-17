package com.bms.service.dao.permission;

import java.util.List;

import com.bms.service.BmsSqlException;
import com.bms.service.data.permission.PolicyData;

public interface IPolicyDao {
	
	long create(PolicyData policyData) throws BmsSqlException;
	
	boolean update(PolicyData policyData) throws BmsSqlException;
	
	boolean delete(long policyId) throws BmsSqlException;
	
	PolicyData getPolicyById(long policyId) throws BmsSqlException;
	
	List<PolicyData> getAllPolicies() throws BmsSqlException;
	
}
