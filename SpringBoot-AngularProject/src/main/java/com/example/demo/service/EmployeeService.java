package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	public EmployeeRepository employeeRepository;
	
	public Employee addEmployee(Employee employee) {
	 return employeeRepository.save(employee);	
	}
	
	public List<Employee> findAll() {		
		return employeeRepository.findAll();
	}

	public String updateEmployee(Employee employee) {
		if(employeeRepository.existsById(employee.getId())) {
			employeeRepository.save(employee);
			return "Employee Updated.";
		} else {
			return "Employee does not exist";
		}
	}
	
	public String deleteEmployee(long id) {
		if(employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			return "Employee deleted.";
		} else {
			return "Employee does not exist";	
		}
	}
}
