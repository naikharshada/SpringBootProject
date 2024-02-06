package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.exception.NoStudentFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/student")
public class HomeController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired StudentRepository studentRepository;
	
	@GetMapping("/")
	public String index() {
		return "Welcome to spring boot crud application!";
	}
	
	//Handler for creating new record in DB
	@PostMapping()
	public String saveData(@Valid @RequestBody Student student) {
		Student existingStudent = studentRepository.findById(student.getRollNo()).orElse(null);
		if(existingStudent == null) {
		studentService.addStudent(student);
		return "student added successfully";
		} else {
			return "student already exist";
		}
	}
	
	//handle to fetch a single record with exception handling
	@GetMapping("{rollNo}")
	public Student getStudentData(@PathVariable("rollNo") Integer rollNo) {
		if(studentService.findStudentById(rollNo) == null)
			throw new NoStudentFoundException("no student found");
		return studentService.getStudent(rollNo);
	}
	
	//Handler for fetch all data from db
	@GetMapping()
	public List<Student> getAll() {
		return studentService.getAllStudent();
	}
	
	//Handle for delete a particular record from db with exception handling
	@DeleteMapping("{rollNo}")
	public String deleteStudent(@PathVariable("rollNo") Integer rollNo) {
		if(studentService.findStudentById(rollNo) == null) {
			throw new NoStudentFoundException("No Student Found");
		} else {
			studentService.deleteStudent(rollNo);
			return "deleted successfully";
		}
	}
	
	//handle to update a record of db with exception handling
	@PutMapping()
	public String updateStudentData(@Valid @RequestBody Student student) {
		Student existingStudent = studentRepository.findById(student.getRollNo()).orElse(null);
		if(existingStudent == null) {
			throw new NoStudentFoundException("No student found");
		} else {
		studentService.updateStudent(student);
		return "updated successfully";
}
}
}