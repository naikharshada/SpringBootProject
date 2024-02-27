package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee saveOrUpdate(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee findByEmpid(Long empid) {
		return employeeRepository.getByEmpid(empid);
	}
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
}