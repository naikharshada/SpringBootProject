package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;

@Service
public interface StudentService {
	
	public Student findStudentById(Integer rollNo);
	
	public String addStudent(Student student);
	
	public String updateStudent(Student student);
	
	public String deleteStudent(Integer rollNo);
	
	public List<Student> getAllStudent();
	
	public Student getStudent(Integer rollNo);
}