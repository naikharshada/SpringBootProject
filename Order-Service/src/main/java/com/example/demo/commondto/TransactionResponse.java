package com.example.demo.commondto;

import com.example.demo.entity.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponse {
	
	private Order order;
	private double amount;
	private String transactionId;
	private String message;
}
