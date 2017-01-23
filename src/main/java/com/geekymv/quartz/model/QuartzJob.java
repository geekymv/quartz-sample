package com.geekymv.quartz.model;

import java.io.Serializable;

public class QuartzJob implements Serializable {
	private static final long serialVersionUID = -8272729530859097724L;

	/**
	 * 任务id
	 */
	private String jobId;
	
	/**
	 * 任务名称
	 */
	private String jobName;
	
	/**
	 * 任务分组
	 */
	private String jobGroup;
	
	/**
	 * 任务初始状态：0禁用，1启用，2删除
	 */
	private String jobStatus;
	
	/**
	 * 任务是否有状态（并发）
	 */
	private String isConcurrent;
	
	/**
	 * 任务运行时间表达式
	 */
	private String cronExpression;
	
	/**
	 * 任务描述
	 */
	private String desc;

	public QuartzJob() {
	}
	
	public QuartzJob(String jobName, String jobGroup, String cronExpression) {
		this.jobName = jobName;
		this.jobGroup = jobGroup;
		this.cronExpression = cronExpression;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "QuartzJob [jobId=" + jobId + ", jobName=" + jobName
				+ ", jobGroup=" + jobGroup + ", jobStatus=" + jobStatus
				+ ", isConcurrent=" + isConcurrent + ", cronExpression="
				+ cronExpression + ", desc=" + desc + "]";
	}

}
