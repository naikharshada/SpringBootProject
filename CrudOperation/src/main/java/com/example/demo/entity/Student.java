package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	private int rollNo;
	
	@Column(name = "name", nullable = false)
	
	//user name should not be null or empty
	//user name should have at least 3 characters
	@NotEmpty
	@Size(min = 3, message = "user name should have at least 3 characters")
	private String name;
	
	@Column(name = "Address")
	//address should not be null or empty
	//address should have at least 3 characters
	@NotEmpty
	@Size(min = 3, message = "address should have at least 3 characters")
	private String Address;
	
	//generate constructor for superclass
	public Student() {
		
	}

	//generate using fields
	public Student(int rollNo, String name, String address) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.Address = address;
	}

	//generate getter and setters
	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	//generate to string
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", Address=" + Address + "]";
	}

	
	
}
