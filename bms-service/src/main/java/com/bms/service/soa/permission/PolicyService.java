package com.bms.service.soa.permission;

import java.util.Date;
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
	public long create(PolicyData policyData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			policyData.setCreatedBy(loginUserId);
			policyData.setCreatedOn(currDate);
			policyData.setUpdatedBy(loginUserId);
			policyData.setUpdatedOn(currDate);
			return policyDao.create(policyData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean update(PolicyData policyData, long loginUserId) throws BmsException, BmsSqlException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			policyData.setUpdatedBy(loginUserId);
			policyData.setUpdatedOn(currDate);
			return policyDao.update(policyData);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean delete(long policyId) throws BmsException, BmsSqlException {
		try {
			return policyDao.delete(policyId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public PolicyData getPolicyById(long policyId) throws BmsException, BmsSqlException {
		try {
			return policyDao.getPolicyById(policyId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public List<PolicyData> getAllPolicies() throws BmsException, BmsSqlException {
		try {
			return policyDao.getAllPolicies();
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public boolean isAvailable(long id, String code) throws BmsException, BmsSqlException {
		try {
			return policyDao.isAvailable(id, code);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
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
