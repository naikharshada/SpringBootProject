package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Person;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.service.AadharCardService;
import com.example.demo.service.PersonService;

@RestController
public class PersonAadharController {
	
	@Autowired
	private PersonService personService;
	
	//save
	@PostMapping("/save")
	public Person savePerson(@RequestBody Person person) {
	return personService.save(person);
	}
	
	//getAll
	@GetMapping("/getAll")
	public List<Person> findAll() {
		return personService.findAll();
	}
	
	//getOne by id
	@GetMapping("/findPersonById/{person_id}")
	public Person findById(@PathVariable int person_id) {
		if(personService.findPersonById(person_id) == null) 
			throw new PersonNotFoundException("Requested Person does not exist");
		return personService.findPersonById(person_id);
	}
	
	//Delete by id by native query
	@DeleteMapping("/deleteById/{person_id}")
	public String deleteById(@PathVariable int person_id) {
		if(personService.findPersonById(person_id) == null) {
			throw new PersonNotFoundException("Requested Person does not exist");
		}
		return personService.deletePersonNativeQ(person_id) + " person(s) deleted";
	}
	
	//upate person with native query
	@PutMapping("/updatePerson")
	public String updatePerson(@RequestBody Person person) {
		if(personService.findPersonById(person.getPerson_id()) == null)
			throw new PersonNotFoundException("Requested Person does not exist");
		return personService.updatePersonNativeQ(person.getName(), person.getLocation(), person.getPhone(), person.getPerson_id()) + " person(s) updated";
	}	
}
