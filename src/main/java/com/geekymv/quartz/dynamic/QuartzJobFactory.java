package com.geekymv.quartz.dynamic;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.geekymv.quartz.model.QuartzJob;

public class QuartzJobFactory implements Job {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		QuartzJob job = (QuartzJob)context.getMergedJobDataMap().get("scheduleJob");
		System.out.println(job.getJobName());
	}

}
