package com.bms.service.soa.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bms.common.BmsException;
import com.bms.common.util.CryptoChiper;
import com.bms.service.BmsSqlException;
import com.bms.service.dao.auth.IAuthenticationDao;
import com.bms.service.soa.BaseService;

@Service("authenticationService")
public class AuthenticationService extends BaseService implements IAuthenticationService {

	private IAuthenticationDao authenticationDao;

	@Override
	public long getAuthorizedAdmin(String username, String password) throws BmsException, BmsSqlException {
		try {
			return authenticationDao.getAuthorizedAdmin(username, new CryptoChiper().encrypt(password));
		} catch (BmsSqlException e) {
			throw e;
		} catch (Exception e) {
			throw new BmsException(e);
		}
	}

	@Override
	public IAuthenticationDao getAuthenticationDao() {
		return authenticationDao;
	}

	@Autowired
	@Qualifier("authenticationDao")
	@Override
	public void setAuthenticationDao(IAuthenticationDao authenticationDao) {
		this.authenticationDao = authenticationDao;
	}
	
	
	
}
