package com.geekymv.quartz.service;

import java.util.List;

import com.geekymv.quartz.model.QuartzJob;

public interface JobService {
	
	/**
	 * 获取单个job
	 *
	 * @author: miying
	 * @createTime: 2016年12月29日 下午5:02:43
	 * @history:
	 * @param jobName 任务名称
	 * @param jobGroup 任务所在组
	 * @return QuartzJob
	 */
	public QuartzJob getJob(String jobName,String jobGroup);
	
	/**
	 * 获取所有job
	 *
	 * @author: miying
	 * @createTime: 2016年12月29日 下午6:38:32
	 * @history:
	 * @return List<QuartzJob>
	 */
	public List<QuartzJob> getAllJobs();
	
	/**
	 * 添加job
	 * 一个job可以对应多个trigger， 但一个trigger只能对应一个job
	 * 这里设计成一个job对应一个trigger的模式，两者的name和group相同， 方便管理，
	 * 任务创建时如果不存在新建一个，如果已经存在则更新任务。
	 * 
	 * @author: miying
	 * @createTime: 2017年1月22日 上午10:49:16
	 * @history:
	 * @param job void
	 * @param targetObject 待执行的job class
	 * @param targetMethod 待执行的job method
	 */
	public void addJob(QuartzJob job, String targetObject, String targetMethod);
	
	/**
	 * 暂停任务
	 *
	 * @author: miying
	 * @createTime: 2017年1月22日 上午10:29:07
	 * @history:
	 * @param jobName 任务名称
	 * @param jobGroup 任务所在组
	 */
	public void pauseJob(String jobName, String jobGroup);
	
	/**
	 * 恢复任务
	 *
	 * @author: miying
	 * @createTime: 2017年1月22日 上午10:47:56
	 * @history:
	 * @param jobName 任务名称
	 * @param jobGroup 任务所在组
	 */
	public void resumeJob(String jobName, String jobGroup);

}
