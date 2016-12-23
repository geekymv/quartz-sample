package com.geekymv.quartz.cluster;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DetailQuartzJobBean extends QuartzJobBean {

	private ApplicationContext applicationContext;

	private String targetObject;

	private String targetMethod;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try { 
            Object otargetObject = applicationContext.getBean(targetObject); 
            Method m = otargetObject.getClass().getMethod(targetMethod, new Class[] {}); 
            m.invoke(otargetObject, new Object[] {}); 
        } catch (Exception e) { 
        	e.printStackTrace();
        } 
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/** 
     * 从SchedulerFactoryBean注入的applicationContext. 
     */   
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public String getTargetObject() {
		return targetObject;
	}
	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public String getTargetMethod() {
		return targetMethod;
	}
	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

	
}
