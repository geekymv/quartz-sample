package com.geekymv.quartz.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

	@RequestMapping("/sample")
	public String sample(HttpServletRequest request, HttpServletResponse response) {
		return "index";
	}
	
}
