package com.bms.service.soa.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bms.common.BmsException;
import com.bms.common.SetupConstants;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.provider.IProviderAdminDao;
import com.bms.service.dao.provider.IProviderDao;
import com.bms.service.data.provider.ProviderAdminData;
import com.bms.service.data.provider.ProviderData;
import com.bms.service.soa.BaseService;

@Service("providerAdminService")
public class ProviderAdminService extends BaseService implements IProviderAdminService {

	private IProviderAdminDao providerAdminDao;
	private IProviderDao providerDao;
	
	@Override
	public boolean setProviderForAdmin(long userId, long providerId, boolean isAssign, long loginUserId) throws BmsSqlException, BmsException {
		boolean status = false;
		TransactionStatus txStatus= null;
		try {
			txStatus = getTxManager().getTransaction(new DefaultTransactionDefinition());
			if (providerAdminDao.isProviderAssignedForAdmin(userId, providerId)) {
				if (isAssign) {
					status = true;
				} else {
					status = providerAdminDao.deleteProviderAdmin(userId, providerId);
					boolean isProviderAssignedForAdmin = providerAdminDao.isProviderAssignedForAdmin(userId, 0);
					
					if (status && !isProviderAssignedForAdmin) {
						providerAdminDao.deleteDefaultProvider(userId);
					}
				}
			} else {
				if (isAssign) {
					boolean isProviderAssignedForAdmin = providerAdminDao.isProviderAssignedForAdmin(userId, 0);
					Date currDate = new Date(System.currentTimeMillis());
					ProviderAdminData providerAdminData = new ProviderAdminData();
					providerAdminData.setProviderId(providerId);
					providerAdminData.setUserId(userId);
					providerAdminData.setCreatedBy(loginUserId);
					providerAdminData.setCreatedOn(currDate);
					providerAdminData.setUpdatedBy(loginUserId);
					providerAdminData.setUpdatedOn(currDate);
					status = providerAdminDao.createProviderAdmin(providerAdminData) > 0;
					
					if (status && !isProviderAssignedForAdmin) {
						providerAdminDao.createDefaultProvider(providerAdminData);
					}
				} else {
					status = true;
				}
			}
			getTxManager().commit(txStatus);
		} catch (BmsSqlException e) {
			if (txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			throw e;
		} catch (Exception e) {
			if (txStatus != null) {
				getTxManager().rollback(txStatus);
			}
			throw new BmsException(e);
		}
		return status;
	}

	@Override
	public boolean setDefaultProviderForAdmin(long userId, long providerId, long loginUserId) throws BmsSqlException, BmsException {
		try {
			Date currDate = new Date(System.currentTimeMillis());
			ProviderAdminData providerAdminData = new ProviderAdminData();
			providerAdminData.setProviderId(providerId);
			providerAdminData.setUserId(userId);
			providerAdminData.setCreatedBy(loginUserId);
			providerAdminData.setCreatedOn(currDate);
			providerAdminData.setUpdatedBy(loginUserId);
			providerAdminData.setUpdatedOn(currDate);
			
			if (providerId == 0) {
				return providerAdminDao.deleteDefaultProvider(userId);
			} else if (providerAdminDao.isDefaultProviderAssignedForAdmin(userId)) {
				return providerAdminDao.updateDefaultProvider(providerAdminData);
			} else {
				return providerAdminDao.createDefaultProvider(providerAdminData) > 0;
			}
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public ProviderAdminData getDefaultProviderByUserId(long userId) throws BmsSqlException, BmsException {
		try {
			return providerAdminDao.getDefaultProviderByUserId(userId);
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public List<ProviderAdminData> getAssignedProviders(long userId, long groupId, long roleId) throws BmsSqlException, BmsException {
		try {
			List<ProviderAdminData> assignedProviders = new ArrayList<>();
			Map<Long, ProviderAdminData> providerIdVsProviderAdminMap = new HashMap<>();

			List<ProviderAdminData> providerAdminDatas = providerAdminDao.getAllProviderAdminDatasByUserId(userId);
			for (ProviderAdminData providerAdminData : providerAdminDatas) {
				providerIdVsProviderAdminMap.put(providerAdminData.getProviderId(), providerAdminData);
			}
			
			List<ProviderData> providerDatas = providerDao.getAllProviderDatas();
			for (ProviderData providerData : providerDatas) {
				if (!(providerIdVsProviderAdminMap.containsKey(providerData.getId())
						|| (groupId == SetupConstants.GROUP_ADMIN 
								&& roleId == SetupConstants.ROLE_SUPER_USER))) {
					continue;
				}
				
				ProviderAdminData providerAdminData = providerIdVsProviderAdminMap.get(providerData.getId());
				if (providerAdminData == null) {
					providerAdminData = new ProviderAdminData();
					providerAdminData.setProviderId(providerData.getId());
					providerAdminData.setUserId(userId);
					providerAdminData.setTitle(providerData.getTitle());
					providerAdminData.setStarRating(providerData.getHotelData().getStarRating());
					providerAdminData.setCityName(providerData.getPostalAddressDatas().get(0).getCityName());
					providerAdminData.setStateName(providerData.getPostalAddressDatas().get(0).getStateName());
					providerAdminData.setCountryName(providerData.getPostalAddressDatas().get(0).getCountryName());
				}
				
				assignedProviders.add(providerAdminData);
			}
			
			return assignedProviders;
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}
	
	@Override
	public List<ProviderAdminData> getAssignableProviders(long assigneeUserId, long assignerUserId, long assignerUserGroupId, long assignerUserRoleId) throws BmsSqlException, BmsException {
		try {
			List<ProviderAdminData> assignableProviders = new ArrayList<>();
			Map<Long, ProviderAdminData> providerIdVsProviderAdminForAssigneeMap = new HashMap<>();
			Map<Long, ProviderAdminData> providerIdVsProviderAdminForAssignerMap = new HashMap<>();
			
			List<ProviderAdminData> assigneeProviderAdminDatas = providerAdminDao.getAllProviderAdminDatasByUserId(assigneeUserId);
			for (ProviderAdminData providerAdminData : assigneeProviderAdminDatas) {
				providerIdVsProviderAdminForAssigneeMap.put(providerAdminData.getProviderId(), providerAdminData);
			}
			
			List<ProviderAdminData> assignerProviderAdminDatas = providerAdminDao.getAllProviderAdminDatasByUserId(assignerUserId);
			for (ProviderAdminData providerAdminData : assignerProviderAdminDatas) {
				providerIdVsProviderAdminForAssignerMap.put(providerAdminData.getProviderId(), providerAdminData);
			}
			
			List<ProviderData> providerDatas = providerDao.getAllProviderDatas();
			for (ProviderData providerData : providerDatas) {
				if (!(providerIdVsProviderAdminForAssignerMap.containsKey(providerData.getId())
						|| (assignerUserGroupId == SetupConstants.GROUP_ADMIN 
								&& assignerUserRoleId == SetupConstants.ROLE_SUPER_USER))) {
					continue;
				}
				
				ProviderAdminData providerAdminData = providerIdVsProviderAdminForAssigneeMap.get(providerData.getId());
				if (providerAdminData == null) {
					providerAdminData = new ProviderAdminData();
					providerAdminData.setProviderId(providerData.getId());
					providerAdminData.setUserId(assigneeUserId);
					providerAdminData.setTitle(providerData.getTitle());
					providerAdminData.setStarRating(providerData.getHotelData().getStarRating());
					providerAdminData.setCityName(providerData.getPostalAddressDatas().get(0).getCityName());
					providerAdminData.setStateName(providerData.getPostalAddressDatas().get(0).getStateName());
					providerAdminData.setCountryName(providerData.getPostalAddressDatas().get(0).getCountryName());
				}
				
				assignableProviders.add(providerAdminData);
			}
			
			return assignableProviders;
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IProviderAdminDao getProviderAdminDao() {
		return providerAdminDao;
	}

	@Autowired
	@Qualifier("providerAdminDao")
	@Override
	public void setProviderAdminDao(IProviderAdminDao providerAdminDao) {
		this.providerAdminDao = providerAdminDao;
	}
	
	@Override
	public IProviderDao getProviderDao() {
		return providerDao;
	}

	@Autowired
	@Qualifier("providerDao")
	@Override
	public void setProviderDao(IProviderDao providerDao) {
		this.providerDao = providerDao;
	}

}
