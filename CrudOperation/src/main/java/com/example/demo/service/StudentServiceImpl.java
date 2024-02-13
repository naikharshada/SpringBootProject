package com.example.demo.service;

import java.util.List;
import java.util.Map;

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
			return studentRepository.findById(rollNo).get();
	}

	@Override
	public String addStudent(Student student) {
		studentRepository.save(student);
		return "success";
	}

	@Override
	public String updateStudent(Student student) {
		studentRepository.save(student);
		return "success";
	}

	@Override
	public String deleteStudent(Integer rollNo) {
		studentRepository.deleteById(rollNo);;
		return "success";
	}

	@Override
	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	@Override
	public Student getStudent(Integer rollNo) {
		return studentRepository.findById(rollNo).get();
	}

	//JPAQL
	@Override
	public List<Student> getAllStudentUsingJPAQL() {
		return studentRepository.getAllStudentUsingJPAQL();
	}

	@Override
	public List<Student> getStudentByAddressUsingJPAQL(String Address) {
		return studentRepository.getStudentByAddressUsingJPAQL(Address);
	}

	@Override
	public List<Student> getStudentByNameOrAddressUsingJPAQL(String Address, String name) {
		return studentRepository.getStudentByNameOrAddressUsingJPAQL(Address, name);
	}
	
	@Override
	public Integer updateNameByRollnoJPAQL(int rollNo, String name) {
		return studentRepository.updateNameByRollnoJPAQL(rollNo, name);
	}

	//Native
	@Override
	public List<Student> getAllStudentUsingNativeQuery() {
		return studentRepository.getAllStudentUsingNativeQuery();
	}

	@Override
	public List<Student> getStudentByAddressUsingNative(String Address) {
		return studentRepository.getStudentByAddressUsingNative(Address);
	}

	@Override
	public Integer updateNameByRollnoNative(String name, int rollNo) {
		return studentRepository.updateNameByRollnoNative(name, rollNo);
	}

	@Override
	public Integer insertStudentNative(int rollNo, String name, String Address) {
		return studentRepository.insertStudentNative(rollNo, name, Address);
	}

	@Override
	public Integer updateStudentNative(String name, String Address, int rollNo) {
		return studentRepository.updateStudentNative(name, Address, rollNo);
	}

	@Override
	public Integer deleteStudentNative(int rollNo) {
		return studentRepository.deleteStudentNative(rollNo);
	}
}