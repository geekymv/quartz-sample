package com.geekymv.quartz.dynamic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJob {
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public void test() {
		System.out.println("do task at " + format.format(new Date()));
	}

}
