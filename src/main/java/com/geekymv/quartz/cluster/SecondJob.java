package com.geekymv.quartz.cluster;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SecondJob {

	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void doTask() {
		System.out.println("do task at " + format.format(new Date()));
	}
	
	public void doLog() {
		System.out.println("do log at " + format.format(new Date()));
	}
	
}
