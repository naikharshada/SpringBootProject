package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
		boolean f = userService.existsEmailCheck(user.getEmail());
		if(f) {
			session.setAttribute("msg", "Email already exists");
		} else {
			User saveUser = userService.saveUser(user);
			if(saveUser != null) {
				session.setAttribute("msg", "Register successfully");
			} else {
				session.setAttribute("msg", "Server Error!");
			}
		}
		return "redirect:/register";
	}
	
	@GetMapping("/signIn")
	public String login() {
		return "login";
	}
}
