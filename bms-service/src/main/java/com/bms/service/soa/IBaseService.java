package com.bms.service.soa;

import org.springframework.transaction.PlatformTransactionManager;

public interface IBaseService {

	PlatformTransactionManager getTxManager();

	void setTxManager(PlatformTransactionManager txManager);
	
}
