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
	
	public List<Student> getAllStudentUsingJPAQL();
	
	public List<Student> getStudentByAddressUsingJPAQL(String Address);
	
	public List<Student> getStudentByNameOrAddressUsingJPAQL(String Address, String name);
	
	public Integer updateNameByRollnoJPAQL(int rollNo, String name);
	
	public List<Student> getAllStudentUsingNativeQuery();
	
	public List<Student> getStudentByAddressUsingNative(String Address);
	
	public Integer updateStudentNative(String name, String Address, int rollNo);
	
	public Integer updateNameByRollnoNative(String name, int rollNo);
	
	public Integer insertStudentNative(int rollNo, String name, String Address);
	
	public Integer deleteStudentNative(int rollNo);
}