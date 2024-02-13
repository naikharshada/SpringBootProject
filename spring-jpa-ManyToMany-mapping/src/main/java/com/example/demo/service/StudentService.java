package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;

@Service
public interface StudentService {
	
		public Student saveStudentWithCourse(Student student);
		
		public List<Student> findAllStudent();
		
		public Student findStudentById(Long id);

		public List<Student> findStuContainingName(String name);
}