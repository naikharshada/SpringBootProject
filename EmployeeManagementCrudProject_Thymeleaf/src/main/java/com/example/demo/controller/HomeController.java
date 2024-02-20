package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private EmpService empService;
	
	@GetMapping("/")
	public String index(Model m) {
		List<Employee> list = empService.getAllEmp();
		m.addAttribute("empList", list);
		return "index";
	}
	
	@GetMapping("/saveEmp")
	public String loadSaveEmp() {
		return "emp_save";
	}
	
	@GetMapping("/editEmp/{id}")
	public String loadEditEmp(@PathVariable int id, Model m) {
		//System.out.println(id);
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp", emp);
		return "edit_emp";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee employee, HttpSession session) {
		Employee newEmp = empService.saveEmp(employee);
		if (newEmp != null) {
			session.setAttribute("msg", "Registered Successfully");
		} else {
			session.setAttribute("msg", "Server Error!");
		}
		return "redirect:/saveEmp";
	}
	
	@PostMapping("/updateEmp")
	public String updateEmp(@ModelAttribute Employee employee, HttpSession session) {
		Employee updateEmp = empService.saveEmp(employee);
		if (updateEmp != null) {
			session.setAttribute("msg", "Updated Successfully");
		} else {
			session.setAttribute("msg", "Server Error!");
		}
		return "redirect:/";
	}
	
	@GetMapping("/deleteEmp/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {
		boolean f = empService.deleteEmp(id);
		if (f) {
			session.setAttribute("msg", "Deleted sucessfully");
		} else {
			session.setAttribute("msg", "Server Error!");
		}
		return "redirect:/";
	}
}