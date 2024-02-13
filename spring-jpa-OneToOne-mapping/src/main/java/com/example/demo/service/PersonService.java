package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Person;

@Service
public interface PersonService {
	
	public Person save(Person person);
	
	public List<Person> findAll();
	
	public Person findPersonById(Integer person_id);
	
	public Integer deletePersonNativeQ(int person_id);
	
	public Integer updatePersonNativeQ(String name, String location, long phone, int person_id);
}