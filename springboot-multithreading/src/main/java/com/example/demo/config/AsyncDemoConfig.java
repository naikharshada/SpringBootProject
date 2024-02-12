package com.example.demo.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncDemoConfig {

	//create a bean executor  
	@Bean(name="multithreadingdemobean")
	public Executor getThreadPoolExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5); //only 5 thread will initialize
		executor.setMaxPoolSize(10); //maximum limit
		executor.setThreadNamePrefix("DemoThread-");
		executor.initialize();
		return executor;
	}
}
