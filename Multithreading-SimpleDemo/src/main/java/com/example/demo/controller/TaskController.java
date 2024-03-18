package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	 @Autowired
	    private TaskService taskService;

	    @GetMapping("/sync")
	    public String triggerSyncTask() {
	        taskService.synchronousTask();
	        return "Synchronous task triggered.";
	    }

	    @GetMapping("/async")
	    public String triggerAsyncTask() {
	        taskService.asynchronousTask();
	        return "Asynchronous task triggered.";
	    }

}
