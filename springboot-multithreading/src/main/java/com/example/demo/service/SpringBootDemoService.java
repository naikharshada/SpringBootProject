package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SpringBootDemoService {
	
	@Autowired
	SpringBootDemoAsyncService springBootDemoAsyncService;
	
	public void processTask() {
		log.info("Start preparing the task list");
		List<String> taskList = new ArrayList<>();
		taskList.add("apple");
		taskList.add("orange");
		taskList.add("grapes");
		taskList.add("pineapple");
		taskList.add("papaya");
		taskList.add("lemon");
		taskList.add("coconut");
		taskList.add("banana");
		taskList.add("sapota");
		taskList.add("muskmelon");
		taskList.add("guava");
		
		log.info("Start preparing the task list");
		for(String payload : taskList) {
			springBootDemoAsyncService.processTask(payload);
		}
		log.info("completed processing the task list");
	}

}