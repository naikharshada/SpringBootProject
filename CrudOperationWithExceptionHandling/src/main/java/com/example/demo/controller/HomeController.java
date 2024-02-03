package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.exception.NoStudentFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

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
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HomeController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String index() {
		return "Welcome to spring boot crud application!";
	}
	
	//Handler for creating new record in DB
	@PostMapping("/saveStudent")
	public String saveData(@RequestBody Student student) {
		Student stu = studentRepository.findById(student.getRollNo()).orElse(null);
		if(stu == null) {
			studentRepository.save(student);
			return "Student added successfully";
		} else {
			return "student already exist";
		}
		
	}
	
	//handle to fetch a single record with exception handling
	@GetMapping("/getStudent/{rollNo}")
	public Student getStudentData(@PathVariable int rollNo) {
		if(studentService.findStudentById(rollNo) == null) {
			throw new NoStudentFoundException("No Student Found");
		} else {
		Optional<Student> student = studentRepository.findById(rollNo);
		Student student1 = student.get();
		return student1;
	}
	}
	
	//Handler for fetch all data from db
	@GetMapping("/getAllStudent")
	public List<Student> getAll() {
		List<Student> studentList = studentRepository.findAll();
		return studentList;
	}
	
	//Handle for delete a particular record from db with exception handling
	@DeleteMapping("/deleteStudent/{rollNo}")
	public String deleteStudent(@PathVariable int rollNo) {
		if(studentService.findStudentById(rollNo) == null) {
			throw new NoStudentFoundException("No Student Found");
		} else {
			Student student= studentRepository.findById(rollNo).get();
			studentRepository.delete(student);
			return ("Deleted successfully");
		}
	}
	
	//handle to update a record of db with exception handling
	@PutMapping("/updateData")
	public String updateStudentData(@RequestBody Student student) {
		Student stduent1 = studentService.findStudentById(student.getRollNo());
		if(stduent1 == null) {
			throw new NoStudentFoundException("No Student Found");
		} else {
		Student stu = studentRepository.findById(student.getRollNo()).orElse(null);
			stu.setName(student.getName());
			stu.setAddress(student.getAddress());
		studentRepository.save(stu);
		return ("updated sucessfully");	
	}
	}
}
