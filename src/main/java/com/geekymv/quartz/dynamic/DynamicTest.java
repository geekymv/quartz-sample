package com.geekymv.quartz.dynamic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.geekymv.quartz.model.QuartzJob;
import com.geekymv.quartz.service.JobService;

public class DynamicTest {
	
	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-quartz-dynamic.xml");
	
		JobService jobService = context.getBean("jobService", JobService.class);
		
		QuartzJob job = new QuartzJob("test_jobname", "test_jobgroup", "0/3 * * * * ?");
		job.setDesc("定时任务描述333");
		jobService.addJob(job, "com.geekymv.quartz.dynamic.MyJob", "test");

//		try {
//			Thread.sleep(10 * 1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		jobService.pauseJob("test_jobname4", "test_jobgroup4");
//		System.out.println("paused job " + format.format(new Date()));
//		try {
//			Thread.sleep(10 * 1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		jobService.resumeJob("test_jobname4", "test_jobgroup4");
	}

}
