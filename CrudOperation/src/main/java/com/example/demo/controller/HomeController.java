package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;
import com.example.demo.exception.NoStudentFoundException;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
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
	
	
	@GetMapping("/")
	public String index() {
		return "Welcome to spring boot crud application!";
	}
	
	///*****REST API****
	//Handler for creating new record in DB
	@PostMapping()
	public String saveData(@Valid @RequestBody Student student) {
		if(studentService.findStudentById(student.getRollNo()) == null) {
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
		if(studentService.findStudentById(student.getRollNo()) == null) {
			throw new NoStudentFoundException("No student found");
		} else {
		studentService.updateStudent(student);
		return "updated successfully";
		}
}
	
	
	//******JPAQL Query*******
	//handler to get all records using JPAQL query
	@GetMapping("/getAllUsingJPAQL")
	public List<Student> getAllStudentJPAQL() {
		return studentService.getAllStudentUsingJPAQL();
	}
	
	//handler to get records by address using JPAQL query
	@GetMapping("/getStudentByAddrUsingJPAQL/{Address}")
	public List<Student> getStudentByAddressUsingJPAQL(@PathVariable String Address) {
		if(studentService.getStudentByAddressUsingJPAQL(Address).isEmpty())
			throw new NoStudentFoundException("No student found");
		return studentService.getStudentByAddressUsingJPAQL(Address);
	}
	
	//handler to get records by name or address using JPAQL query
	@GetMapping("/getStudentByNameOrAddrUsingJPAQL/{Address}/{name}")
	public List<Student> getStudentByNameOrAddressUsingJPAQL(@PathVariable String Address, @PathVariable String name) {
		if(studentService.getStudentByNameOrAddressUsingJPAQL(Address, name).isEmpty())
			throw new NoStudentFoundException("No student found");
		return studentService.getStudentByNameOrAddressUsingJPAQL(Address, name);
	}
	
	//handler to update name by rollno using JPAQL query
	@PutMapping("/updateNameByRollnoJPAQL/{rollNo}/{name}")
	public String updateNameByRollJPAQL(@PathVariable int rollNo, @PathVariable String name) {
		if(studentService.findStudentById(rollNo) == null) {
			throw new NoStudentFoundException("No student found");
		} else {
		return studentService.updateNameByRollnoJPAQL(rollNo, name) + " student(s) updated";	
		}
	}
	
	//*****Native Query*****
	//handler to get all records using native query
	@GetMapping("/getAllUsingNative")
	public List<Student> getAllUsingNative() {
		return studentService.getAllStudentUsingNativeQuery();
	}
	
	//handler to get records by address using native query
		@GetMapping("/getStudentByAddrUsingNative/{Address}")
		public List<Student> getStudentByAddressUsingNative(@PathVariable String Address) {
			if(studentService.getStudentByAddressUsingNative(Address).isEmpty())
				throw new NoStudentFoundException("No student found");
			return studentService.getStudentByAddressUsingNative(Address);
		}
		
		//handler to insert records using native query
		@PostMapping("/insertStudentNative")
		public String insertStudentNative(@Valid @RequestBody Student student) {
			if(studentService.findStudentById(student.getRollNo()) == null) {
			return studentService.insertStudentNative(student.getRollNo(), student.getName(), student.getAddress()) + " student(s) inserted";
		} else {
			return "Student already exists.";
		}
		}
		
	//handler to update records using native query
		@PutMapping("/updateStudentNative")
		public String updateStudentNative(@Valid @RequestBody Student student) {
			if(studentService.findStudentById(student.getRollNo()) == null) {
				throw new NoStudentFoundException("No student found");
		} else {
			return studentService.updateStudentNative(student.getName(), student.getAddress(), student.getRollNo()) + " student(s) updated";
		}
	}
		
	//handler to update name by rollno using native query
	@PutMapping("/updateNameByRollnoNative/{rollNo}/{name}")
	public String updateNameByRollNative(@PathVariable int rollNo, @Valid @PathVariable String name) {
		if(studentService.findStudentById(rollNo) == null) {
			throw new NoStudentFoundException("No student found");
		} else {
		return studentService.updateNameByRollnoNative(name, rollNo) + " student(s) updated";
		}
	}
	
	//handler to delete records using native
	@DeleteMapping("/deleteStudentNative/{rollNo}")
	public String deleteStudentNative(@PathVariable int rollNo) {
		if(studentService.findStudentById(rollNo) == null) {
			throw new NoStudentFoundException("No student found");
		} else {
		return studentService.deleteStudentNative(rollNo) + " student(s) deleted";
		}
	}
}