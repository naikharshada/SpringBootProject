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

import com.example.demo.entity.Address;
import com.example.demo.entity.Employee;
import com.example.demo.entity.service.AddressService;
import com.example.demo.entity.service.EmployeeService;

@RestController
@RequestMapping("/Address")
@CrossOrigin
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@PostMapping("")
	public Address addEmployee(@RequestBody Address address) {
		return addressService.saveOrUpdate(address);
	}
	
	@GetMapping("")
	public List<Address> findAll() {
		return addressService.findAll();
	}
	
	@GetMapping("/{addressid}")
	public Address getByid(@PathVariable Long addressid) {
		return addressService.findByAddressid(addressid);
	}
}
