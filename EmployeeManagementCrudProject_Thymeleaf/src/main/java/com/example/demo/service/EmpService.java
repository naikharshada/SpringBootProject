package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;

@Service
public interface EmpService {

	public Employee saveEmp(Employee employee);
	
	public List<Employee> getAllEmp();
	
	public Employee getEmpById(int id);
	
	public boolean deleteEmp(int id);
}
