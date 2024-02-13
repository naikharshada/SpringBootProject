package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "modules")
	private int modules;
	
	@Column(name = "fee")
	private int fee;

	@ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Student> students;
}
