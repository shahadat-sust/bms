package com.bms.service.soa.permission;

import java.util.List;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.permission.IPolicyDao;
import com.bms.service.data.permission.PolicyData;

public interface IPolicyService {

	long create(PolicyData policyData) throws BmsException, BmsSqlException;
	
	boolean update(PolicyData policyData) throws BmsException, BmsSqlException;
	
	boolean delete(long policyId) throws BmsException, BmsSqlException;
	
	PolicyData getPolicyById(long policyId) throws BmsException, BmsSqlException;
	
	List<PolicyData> getAllPolicies() throws BmsException, BmsSqlException;

	public IPolicyDao getPolicyDao();

	public void setPolicyDao(IPolicyDao policyDao);
}
