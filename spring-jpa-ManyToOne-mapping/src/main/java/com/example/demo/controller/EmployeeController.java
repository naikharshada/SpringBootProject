package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.entity.service.EmployeeService;

@RestController
@RequestMapping("/Employee")
@CrossOrigin
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.saveOrUpdate(employee);
	}
	
	@GetMapping("")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/{empid}")
	public Employee getById(@PathVariable Long empid) {
		return employeeService.findByEmpid(empid);
	}
}
