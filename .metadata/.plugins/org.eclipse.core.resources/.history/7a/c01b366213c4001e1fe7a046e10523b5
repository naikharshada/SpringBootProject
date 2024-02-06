package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Student;
import com.example.demo.exception.NoStudentFoundException;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student findStudentById(Integer rollNo) {
		
	if(studentRepository.findById(rollNo).isEmpty()) 
		throw new NoStudentFoundException("No Student Found");
			return studentRepository.findById(rollNo).get() ;			
	}
}
