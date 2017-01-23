package com.geekymv.quartz.cluster;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.geekymv.quartz.model.QuartzJob;
import com.geekymv.quartz.service.JobService;

public class ClusterTest {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-quartz-cluster.xml");
		
		JobService jobService = context.getBean("jobService", JobService.class);
		
		QuartzJob job = jobService.getJob("thirdJob", "DEFAULT");
		System.out.println(job);
		
//		jobService.getAllJobs();
	}

}
