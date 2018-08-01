package com.bms.service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class QueryConfiguration {

	@Value("classpath:userQuery.xml")
    private Resource userQueryResource;
	
	@Bean(name = "userQuery")
	public PropertiesFactoryBean userQuery() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(userQueryResource);
		return propertiesFactoryBean;
	}
	
}
