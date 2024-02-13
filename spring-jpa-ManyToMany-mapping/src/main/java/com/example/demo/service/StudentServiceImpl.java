package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student saveStudentWithCourse(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> findStuContainingName(String name) {
		return studentRepository.findByNameContaining(name);
	}

	@Override
	public List<Student> findAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	public Student findStudentById(Long id) {
		return studentRepository.findById(id).get();
	}

}