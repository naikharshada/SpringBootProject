package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService {
	
	public void synchronousTask() {
        log.info("Starting synchronous task...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Synchronous task completed.");
    }
	
	@Async
    public void asynchronousTask() {
		log.info("Starting asynchronous task...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Asynchronous task completed.");
    }
}
