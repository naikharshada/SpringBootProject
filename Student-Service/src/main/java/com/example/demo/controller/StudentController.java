package com.example.demo.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/student")
public class StudentController {
	Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/save")
	public Student saveStudent(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}
	
	@GetMapping("/{id}")
	@CircuitBreaker(name="STUDENT-SERVICE", fallbackMethod="fallbackMethod")
	//@Retry(name="STUDENT-SERVICE")
	//@TimeLimiter(name="STUDENT-SERVICE")
	//@ResponseStatus(HttpStatus.ACCEPTED)
	public CompletableFuture<String> getStudentWithDepartment(@PathVariable Long id) {
		return CompletableFuture.supplyAsync(()->studentService.getStudentWithDepartment(id));
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CompletableFuture<String> fallbackMethod(@PathVariable Long id, RuntimeException ex) {
		return CompletableFuture.supplyAsync(()->"Service is down. Please try after some time.");
	}
}