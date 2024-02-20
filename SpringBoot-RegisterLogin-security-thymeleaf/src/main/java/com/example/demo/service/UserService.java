package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.entity.User;

@Service
public interface UserService extends UserDetailsService {
	
	User save(UserRegistrationDto userRegistrationDto);
}