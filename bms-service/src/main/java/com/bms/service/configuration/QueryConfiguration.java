package com.bms.service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class QueryConfiguration {

	@Bean(name = "authenticationQuery")
	public PropertiesFactoryBean authenticationQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(authenticationQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "userQuery")
	public PropertiesFactoryBean userQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(userQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "userCardQuery")
	public PropertiesFactoryBean userCardQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(userCardQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "userDeviceQuery")
	public PropertiesFactoryBean userDeviceQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(userDeviceQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "userProfileQuery")
	public PropertiesFactoryBean userProfileQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(userProfileQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "userSocialAccountQuery")
	public PropertiesFactoryBean userSocialAccountQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(userSocialAccountQueryResource);
		return factoryBean;
	}

	@Bean(name = "cityQuery")
	public PropertiesFactoryBean cityQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(cityQueryResource);
		return factoryBean;
	}

	@Bean(name = "stateQuery")
	public PropertiesFactoryBean stateQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(stateQueryResource);
		return factoryBean;
	}

	@Bean(name = "countryQuery")
	public PropertiesFactoryBean countryQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(countryQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "emailAddressQuery")
	public PropertiesFactoryBean emailAddressQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(emailAddressQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "phoneNumberQuery")
	public PropertiesFactoryBean phoneNumberQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(phoneNumberQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "imageQuery")
	public PropertiesFactoryBean imageQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(imageQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "postalAddressQuery")
	public PropertiesFactoryBean postalAddressQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(postalAddressQueryResource);
		return factoryBean;
	}

	@Bean(name = "bookingStatusQuery")
	public PropertiesFactoryBean bookingStatusQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(bookingStatusQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "bookingTypeQuery")
	public PropertiesFactoryBean bookingTypeQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(bookingTypeQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "paymentMethodQuery")
	public PropertiesFactoryBean paymentMethodQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(paymentMethodQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "paymentTypeQuery")
	public PropertiesFactoryBean paymentTypeQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(paymentTypeQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "groupQuery")
	public PropertiesFactoryBean groupQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(groupQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "policyQuery")
	public PropertiesFactoryBean policyQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(policyQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "roleQuery")
	public PropertiesFactoryBean roleQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(roleQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "amenityQuery")
	public PropertiesFactoryBean amenityQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(amenityQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "pointOfInterestQuery")
	public PropertiesFactoryBean pointOfInterestQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(pointOfInterestQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "providerTypeQuery")
	public PropertiesFactoryBean providerTypeQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(providerTypeQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "providerQuery")
	public PropertiesFactoryBean providerQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(providerQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "hotelQuery")
	public PropertiesFactoryBean hotelQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(hotelQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "providerAdminQuery")
	public PropertiesFactoryBean providerAdminQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(providerAdminQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "userDefaultProviderQuery")
	public PropertiesFactoryBean userDefaultProviderQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(userDefaultProviderQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "userRoleQuery")
	public PropertiesFactoryBean userRoleQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(userRoleQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "userGroupQuery")
	public PropertiesFactoryBean userGroupQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(userGroupQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "itemTypeQuery")
	public PropertiesFactoryBean itemTypeQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(itemTypeQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "itemCategoryQuery")
	public PropertiesFactoryBean itemCategoryQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(itemCategoryQueryResource);
		return factoryBean;
	}
	
	@Bean(name = "itemQuery")
	public PropertiesFactoryBean itemQuery() {
		PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
		factoryBean.setLocation(itemQueryResource);
		return factoryBean;
	}
	
	@Value("classpath:authenticationQuery.xml")
    private Resource authenticationQueryResource;
	
	@Value("classpath:userQuery.xml")
    private Resource userQueryResource;
	
	@Value("classpath:userCardQuery.xml")
    private Resource userCardQueryResource;
	
	@Value("classpath:userDeviceQuery.xml")
    private Resource userDeviceQueryResource;
	
	@Value("classpath:userProfileQuery.xml")
    private Resource userProfileQueryResource;
	
	@Value("classpath:userSocialAccountQuery.xml")
    private Resource userSocialAccountQueryResource;
	
	@Value("classpath:cityQuery.xml")
    private Resource cityQueryResource;
	
	@Value("classpath:stateQuery.xml")
    private Resource stateQueryResource;
	
	@Value("classpath:countryQuery.xml")
    private Resource countryQueryResource;

	@Value("classpath:emailAddressQuery.xml")
    private Resource emailAddressQueryResource;

	@Value("classpath:phoneNumberQuery.xml")
    private Resource phoneNumberQueryResource;

	@Value("classpath:imageQuery.xml")
    private Resource imageQueryResource;

	@Value("classpath:postalAddressQuery.xml")
    private Resource postalAddressQueryResource;

	@Value("classpath:bookingStatusQuery.xml")
    private Resource bookingStatusQueryResource;
	
	@Value("classpath:bookingTypeQuery.xml")
    private Resource bookingTypeQueryResource;
	
	@Value("classpath:paymentMethodQuery.xml")
    private Resource paymentMethodQueryResource;
	
	@Value("classpath:paymentTypeQuery.xml")
    private Resource paymentTypeQueryResource;
	
	@Value("classpath:groupQuery.xml")
    private Resource groupQueryResource;
	
	@Value("classpath:policyQuery.xml")
    private Resource policyQueryResource;
	
	@Value("classpath:roleQuery.xml")
    private Resource roleQueryResource;
	
	@Value("classpath:amenityQuery.xml")
    private Resource amenityQueryResource;
	
	@Value("classpath:pointOfInterestQuery.xml")
    private Resource pointOfInterestQueryResource;
	
	@Value("classpath:providerTypeQuery.xml")
    private Resource providerTypeQueryResource;
	
	@Value("classpath:providerQuery.xml")
	private Resource providerQueryResource;
	
	@Value("classpath:hotelQuery.xml")
	private Resource hotelQueryResource;
	
	@Value("classpath:providerAdminQuery.xml")
	private Resource providerAdminQueryResource;
	
	@Value("classpath:userDefaultProviderQuery.xml")
	private Resource userDefaultProviderQueryResource;
	
	@Value("classpath:userRoleQuery.xml")
	private Resource userRoleQueryResource;
	
	@Value("classpath:userGroupQuery.xml")
	private Resource userGroupQueryResource;
	
	@Value("classpath:itemTypeQuery.xml")
	private Resource itemTypeQueryResource;
	
	@Value("classpath:itemCategoryQuery.xml")
	private Resource itemCategoryQueryResource;
	
	@Value("classpath:itemQuery.xml")
	private Resource itemQueryResource;
	
}
