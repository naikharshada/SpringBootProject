package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Course;
@Service
public interface CourseService {
	
	public List<Course> findByFeeLessThan(double fee);
}
