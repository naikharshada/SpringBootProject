package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/StudentCourse")
public class StudentCourseController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private CourseService courseService;
	
	//to save all records with course
	@PostMapping("/save")
	public Student saveStudentWithCourse(@Valid @RequestBody Student student) {
		return studentService.saveStudentWithCourse(student);
	}
	
	//to get all records with course
	@GetMapping("/getAll")
	public List<Student> findAll() {
		return studentService.findAllStudent();
	}
	
	//to fetch single record with course
	@GetMapping("/{id}")
	public Student findStudent(@PathVariable Long id) {
		return studentService.findStudentById(id);
	}
	
	//fetch student containing by name with course
	@GetMapping("/findByName/{name}")
	public List<Student> findByName(@PathVariable String name) {
		return studentService.findStuContainingName(name);
	}
	
	//fetch student whoes fees less than given prize
	@GetMapping("/searchLessThanFee/{fee}")
	public List<Course> findCourseLessThanFee(@PathVariable double fee) {
		return courseService.findByFeeLessThan(fee);
	}
}

