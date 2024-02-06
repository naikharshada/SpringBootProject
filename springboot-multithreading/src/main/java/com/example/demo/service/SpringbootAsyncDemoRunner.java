package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringbootAsyncDemoRunner implements CommandLineRunner {
	
	@Autowired
	SpringBootDemoService springBootDemoService;

	@Override
	public void run(String... args) throws Exception {
		springBootDemoService.processTask();
		
	}
	
	
}
