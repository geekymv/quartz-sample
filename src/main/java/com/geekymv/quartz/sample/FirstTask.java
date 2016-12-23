package com.geekymv.quartz.sample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstTask {

	public void doTask() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("do task at " + format.format(new Date()));
	}
}
