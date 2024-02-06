package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SpringBootDemoAsyncService {
	
	@Async("multithreadingdemobean")
	public void processTask(String payload) {
		log.info("Starting Thread - processing payload {}", payload);
		log.info("length of payload string is {}", payload.length());
		try {
			Thread.sleep(500);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Existing thread - processing payload {} completed", payload);
		
	}
	
}
