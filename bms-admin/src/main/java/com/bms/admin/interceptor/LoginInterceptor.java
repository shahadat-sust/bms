package com.bms.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bms.admin.AppConstants;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		Object userSession = request.getSession().getAttribute(AppConstants.KEY_USER);
		if(userSession == null) {
			logger.info("Pre-Handle LoginInterceptor(Admin) :: handler = " + handler.getClass().getSimpleName() + " , Session = 'NOT FOUND'");
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		} else {
			logger.info("Pre-Handle LoginInterceptor(Admin) :: handler = " + handler.getClass().getSimpleName());
			return super.preHandle(request, response, handler);
		}
	}
	
	@Override
	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("Post-Handle LoginInterceptor(Admin) :: handler = " + handler.getClass().getSimpleName());
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, 
			Exception ex)
			throws Exception {
		logger.debug("After-Completion LoginInterceptor(Admin) :: handler = " + handler.getClass().getSimpleName());
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		logger.debug("After-ConcurrentHandlingStarted LoginInterceptor(Admin) :: handler = " + handler.getClass().getSimpleName());
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	
}
