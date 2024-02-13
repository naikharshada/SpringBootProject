package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Person;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonServiceImp implements PersonService {
	
	@Autowired 
	private PersonRepository personRepository;

	@Override
	public Person save(Person person) {
			return personRepository.save(person);
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person findPersonById(Integer person_id) {
		if(personRepository.findById(person_id).isEmpty())
			throw new PersonNotFoundException("Requested Person does not exist");
		return personRepository.findById(person_id).get();
	}

	@Override
	public Integer deletePersonNativeQ(int person_id) {
		return personRepository.deletePersonNativeQ(person_id);
	}

	@Override
	public Integer updatePersonNativeQ(String name, String location, long phone, int person_id) {
		return personRepository.updatePersonNativeQ(name, location, phone, person_id);
	}

}