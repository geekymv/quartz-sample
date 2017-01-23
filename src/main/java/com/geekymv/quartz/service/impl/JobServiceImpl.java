package com.geekymv.quartz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;

import com.geekymv.quartz.cluster.DetailQuartzJobBean;
import com.geekymv.quartz.model.QuartzJob;
import com.geekymv.quartz.service.JobService;

public class JobServiceImpl implements JobService {
	
	private static final String DEFAULT_JOB_GROUP_NAME = "DEFAULT_JOB_GROUP_NAME";
	
	private static final String DEFAULT_TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP_NAME";
	

	private Scheduler scheduler;

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	@Override
	public QuartzJob getJob(String jobName, String jobGroup) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		if(jobKey == null) {
			return null;
		}
		QuartzJob job = new QuartzJob();
		job.setJobName(jobName);
		job.setJobGroup(jobGroup);
		try {
			// 一个job对应多个trigger
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				TriggerKey triggerKey = trigger.getKey();
				String triggerName = triggerKey.getName();
				System.out.println(triggerName);
				
				String cronExpression = "";
				if (trigger instanceof CronTrigger) {    
		            CronTrigger cronTrigger = (CronTrigger) trigger;    
		            cronExpression = cronTrigger.getCronExpression();    
				}  
				job.setCronExpression(cronExpression);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return job;
	}

	@Override
	public List<QuartzJob> getAllJobs() {
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();    
		try {
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					TriggerKey triggerKey = trigger.getKey();
					String name =  triggerKey.getName();
					String group = triggerKey.getGroup();
					System.out.println("name = " + name );
					System.out.println("group = " + group);
					
					String cronExpression = "";
					if (trigger instanceof CronTrigger) {    
			            CronTrigger cronTrigger = (CronTrigger) trigger;    
			            cronExpression = cronTrigger.getCronExpression();    
					}  
					System.out.println("cronExpression = " + cronExpression);
					
					Date startTime = trigger.getStartTime();
					// 上一次触发时间
					Date previousFireTime = trigger.getPreviousFireTime();
					// 下一次触发时间
					Date nextFireTime = trigger.getNextFireTime();
					// 最后一次触发时间
					Date finalFireTime = trigger.getFinalFireTime();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String startTimeStr = "";
					if(startTime != null) {
						startTimeStr = dateFormat.format(startTime);
					}
					String previousFireTimeStr = "";
					if(previousFireTime != null) {
						previousFireTimeStr = dateFormat.format(previousFireTime);
					}
					String nextFireTimeStr = "";
					if(nextFireTime != null) {
						nextFireTimeStr = dateFormat.format(nextFireTime);
					}
					String finalFireTimeStr = "";
					if(finalFireTime != null) {
						finalFireTimeStr = dateFormat.format(finalFireTime);
					}
					System.out.println("startTime = " + startTimeStr);
					System.out.println("previousFireTime = " + previousFireTimeStr);
					System.out.println("nextFireTime = " + nextFireTimeStr);
					System.out.println("finalFireTime = " + finalFireTimeStr);
					
					System.out.println("----------------------------------------------------------");
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}    
		
		return null;
	}
	
	@Override
	public void addJob(QuartzJob job, String targetObject, String targetMethod) {
		String jobName = job.getJobName();
		String jobGroup = job.getJobGroup();
		String desc = job.getDesc();
		String cronExpression = job.getCronExpression();
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		try {
			CronTrigger trigger =  (CronTrigger)scheduler.getTrigger(triggerKey);
			CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
			if(trigger == null) { // trigger 不存在
				JobDetail jobDetail = JobBuilder.newJob(DetailQuartzJobBean.class) //
												.withIdentity(jobName, jobGroup) //
												.withDescription(desc) //
												.build();
				jobDetail.getJobDataMap().put("targetObject", targetObject);
				jobDetail.getJobDataMap().put("targetMethod", targetMethod);
			
				trigger = TriggerBuilder.newTrigger()
//										.withIdentity(jobName, jobGroup)
										.withIdentity(triggerKey)
										.withSchedule(cronScheduleBuilder).build();
				scheduler.scheduleJob(jobDetail, trigger);
			}else {
				trigger = trigger.getTriggerBuilder() //
								 .withSchedule(cronScheduleBuilder) //
								 .withDescription("trigger 描述信息") //
								 .build();
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void pauseJob(String jobName, String jobGroup) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler.pauseJob(jobKey);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void resumeJob(String jobName, String jobGroup) {
		JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		try {
			scheduler.resumeJob(jobKey);
		} catch (SchedulerException e) {
			throw new RuntimeException("恢复job出错！jobName = " + jobName + ", jobGroup = " + jobGroup);
		}
	}

}
