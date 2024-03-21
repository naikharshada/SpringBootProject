package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	private Long id;
	
	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	@Size(min = 3, message = "Address should have at least 3 characters")
	private String address;
	
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email is incorrect!")
	private String email;
	
	@Size(min = 10, max = 10, message = "Phone no is incorrect!")
	private String phone;	
}