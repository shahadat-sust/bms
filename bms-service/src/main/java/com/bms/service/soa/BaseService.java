package com.bms.service.soa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;

public abstract class BaseService implements IBaseService {

	private PlatformTransactionManager txManager;

	@Override
	public PlatformTransactionManager getTxManager() {
		return txManager;
	}

	@Autowired
	@Qualifier("txManager")
	@Override
	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}

}
