package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AadharCard {
	
	@Id
	@GeneratedValue
	private int aadhar_id;
	
	@Column(name = "aadhar_num")
	private long aadhar_num;
	
	@OneToOne(mappedBy = "aadhar", cascade = CascadeType.ALL)
	@JsonBackReference
	private Person persons;

}
