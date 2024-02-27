package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	
	public Address saveOrUpdate(Address address) {
		return addressRepository.save(address);
	}
	
	public Address findByAddressid(Long addressid) {
		return addressRepository.getByAddressid(addressid);
	}
	
	public List<Address> findAll() {
		return addressRepository.findAll();
	}
}
