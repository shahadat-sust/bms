package com.bms.admin.listener;

import com.bms.service.data.user.UserData;

public interface LoginUserAware {

	void setLoginUserData(UserData userData);
	
}
