package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id 
	private Long id;
	
	@Column(name = "name")
	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	@Column(name = "address")
	@Size(min = 3, message = "Address should have at least 3 characters")
	private String address;
	
	@Column(name = "email")
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email is incorrect!")
	private String email;
	
	@Column(name = "phone")
	@Size(min = 10, max = 10, message = "Phone no is incorrect!")
	private String phone;
}