package com.example.demo.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	//save
	@Async
	public CompletableFuture<List<CustomerDTO>> createCustomer(List<CustomerDTO> customerDto) {
		List<Customer> cust = convertToEntityList(customerDto);
		List<Customer> savedCust = customerRepository.saveAll(cust);
		log.info("created " + Thread.currentThread().getName());
		return CompletableFuture.completedFuture(convertToDTOList(savedCust));
		
	}
	
	//getAll
	@Async
	public CompletableFuture<List<CustomerDTO>> getAllCustomer() {
		log.info("Listed " + Thread.currentThread().getName());
		return CompletableFuture.completedFuture(customerRepository.findAll().stream().map(this::convertToDTO)
				.collect(Collectors.toList()));
	}
	
	//getAllById
	@Async
	public CompletableFuture<CustomerDTO> getCustomerById(Long id) {
		log.info("Listed oneById " + Thread.currentThread().getName());
		return CompletableFuture.completedFuture(customerRepository.findById(id).map(this::convertToDTO)
				.orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found")));
	}
	
	//update
	@Async
	public CompletableFuture<CustomerDTO> updateCustomer(CustomerDTO customerDto) {
		
		Customer ExistingCustomer = customerRepository.findById(customerDto.getId()).orElseThrow(() ->
		         new EntityNotFoundException("Customer with id " + customerDto.getId() + " not found"));
		         
		 ExistingCustomer.setId(customerDto.getId());
		 ExistingCustomer.setName(customerDto.getName());
		 ExistingCustomer.setAddress(customerDto.getAddress());
		 ExistingCustomer.setEmail(customerDto.getEmail());
		 ExistingCustomer.setPhone(customerDto.getPhone());
		 
		 ExistingCustomer = customerRepository.save(ExistingCustomer);
		 
		log.info("update " + Thread.currentThread().getName());
		 return CompletableFuture.completedFuture(convertToDTO(ExistingCustomer));
	}
	
	//Delete
	@Async
	public CompletableFuture<String> deleteCustomer(Long id) {
		 
		log.info("delete " + Thread.currentThread().getName());
		if(customerRepository.existsById(id)) {
			customerRepository.deleteById(id);
		} else {
			throw new EntityNotFoundException("Customer with id " + id + " not found"); 
		}
		return CompletableFuture.completedFuture("Customer deleted sucessfully");
	}
	
	
	//conversions
	private List<CustomerDTO> convertToDTOList(List<Customer> customers) {
		return customers.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	private List<Customer> convertToEntityList(List<CustomerDTO> customerDTO) {
		return customerDTO.stream().map(this::convertToEntity).collect(Collectors.toList());
	}
	
	private CustomerDTO convertToDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setPhone(customer.getPhone());
		return customerDTO;
	}
	
	private Customer convertToEntity(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setId(customerDTO.getId());
		customer.setName(customerDTO.getName());
		customer.setAddress(customerDTO.getAddress());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());
		return customer;
	}
}