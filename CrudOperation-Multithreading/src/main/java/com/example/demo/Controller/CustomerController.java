package com.example.demo.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.CustomerService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save")
	public CompletableFuture<List<CustomerDTO>> saveCutomer(@Valid @RequestBody List<CustomerDTO> cutomerDto) {
		return customerService.createCustomer(cutomerDto);
	}
	
	@GetMapping("/getAll")
	public CompletableFuture<List<CustomerDTO>> getAllCutomer() {
		return customerService.getAllCustomer();
	}
	
	@GetMapping("/{id}")
	public CompletableFuture<CustomerDTO> getCutomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}
	
	@PutMapping("/update")
	public CompletableFuture<CustomerDTO> updateCutomer(@Valid @RequestBody CustomerDTO cutomerDto) {
		return customerService.updateCustomer(cutomerDto);
	}
	
	@DeleteMapping("/{id}")
	public CompletableFuture<String> deleteCutomerById(@PathVariable Long id) {
		return customerService.deleteCustomer(id);
	}
	
	//Exceptions
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleEntityNotFoundException(EntityNotFoundException e) {
		return e.getMessage();
	}
	
	//Validations
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<String> handleValidationExceptions(MethodArgumentNotValidException e) {
		return e.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
	}
}