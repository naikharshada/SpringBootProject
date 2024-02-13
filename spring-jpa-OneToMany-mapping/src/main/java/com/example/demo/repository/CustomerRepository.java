package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query("select new com.example.demo.dto.OrderResponse(c.id, c.name, p.pid, p.productName) from Customer c join c.products p")
	public List<OrderResponse> getJoin();

}