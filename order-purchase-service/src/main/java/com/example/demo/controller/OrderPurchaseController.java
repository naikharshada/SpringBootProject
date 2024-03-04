package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Order;
import com.example.demo.service.OrderFulfillmentService;

@RestController
@RequestMapping("/orders")
public class OrderPurchaseController {
	
	  @Autowired
	    private OrderFulfillmentService service;
	  
	  @PostMapping
	    public ResponseEntity<Order> processOrder(@RequestBody Order order) throws InterruptedException {
	        service.processOrder(order); // synchronous
	        // asynchronous
	        service.notifyUser(order);
	        service.assignVendor(order);
	        service.packaging(order);
	        service.assignDeliveryPartner(order);
	        service.assignTrailerAndDispatch(order);
	        return ResponseEntity.ok(order);
	    }

}
