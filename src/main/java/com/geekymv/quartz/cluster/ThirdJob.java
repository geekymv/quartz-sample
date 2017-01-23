package com.geekymv.quartz.cluster;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ThirdJob implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		System.out.println("third job");
	}

}
