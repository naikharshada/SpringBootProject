package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)// to ignore json property or error
@JsonInclude(JsonInclude.Include.NON_DEFAULT)// do not want to see null fields
public class OrderResponse {
	
	//it will include  constructor of all three combination
	private int id;
	private String name;
	private String email;
	private String gender;
	private int pid;
	private String productName;
	private int qty;
	private int price;
	
	//***create constructor combination as per your queries***
	
	//constructor to get name and productName Info w.r.t query
	public OrderResponse(String name, String productName) {
		super();
		this.name = name;
		this.productName = productName;
	}
	
	//constructor to get name and price Info w.r.t query
	public OrderResponse(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	//constructor to get name, pid and productName info w.r.t query
	public OrderResponse(String name, int pid, String productName) {
		super();
		this.name = name;
		this.pid = pid;
		this.productName = productName;
	}
	
	//constructor to get id, name, pid and productName info w.r.t query
		public OrderResponse(int id, String name, int pid, String productName) {
			super();
			this.id = id;
			this.name = name;
			this.pid = pid;
			this.productName = productName;
		}
	
	
}
