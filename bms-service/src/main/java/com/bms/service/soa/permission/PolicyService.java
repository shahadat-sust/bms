package com.bms.service.soa.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.permission.IPolicyDao;
import com.bms.service.data.permission.PolicyData;
import com.bms.service.soa.BaseService;

@Service("policyService")
public class PolicyService extends BaseService implements IPolicyService {

	private IPolicyDao policyDao;
	
	@Override
	public long create(PolicyData policyData) throws BmsException, BmsSqlException {
		return policyDao.create(policyData);
	}

	@Override
	public boolean update(PolicyData policyData) throws BmsException, BmsSqlException {
		return policyDao.update(policyData);
	}

	@Override
	public boolean delete(long policyId) throws BmsException, BmsSqlException {
		return policyDao.delete(policyId);
	}

	@Override
	public PolicyData getPolicyById(long policyId) throws BmsException, BmsSqlException {
		return policyDao.getPolicyById(policyId);
	}

	@Override
	public List<PolicyData> getAllPolicies() throws BmsException, BmsSqlException {
		return policyDao.getAllPolicies();
	}

	@Override
	public IPolicyDao getPolicyDao() {
		return policyDao;
	}

	@Autowired
	@Qualifier("policyDao")
	@Override
	public void setPolicyDao(IPolicyDao policyDao) {
		this.policyDao = policyDao;
	}

}
