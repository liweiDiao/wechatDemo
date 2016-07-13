package com.dlw.common.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SuperSpringContext implements ApplicationContextAware {

	protected static ApplicationContext context;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	public static ApplicationContext getContext() {
		return context;
	}
	
}