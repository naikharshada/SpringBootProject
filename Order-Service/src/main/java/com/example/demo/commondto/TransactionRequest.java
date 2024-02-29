package com.example.demo.commondto;

import com.example.demo.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequest {
	
	private Order order;
	private Payment payment;

}
