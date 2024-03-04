package com.example.demo.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.demo.dto.Order;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderFulfillmentService {
	
	 @Autowired
	 private InventoryService inventoryService;

	 @Autowired
	 private PaymentService paymentService;
	
	
	/*  1. Inventory service check order availability
    	2. Process payment for order */
	 public Order processOrder(Order order) throws InterruptedException {
	        order.setTrackingId(UUID.randomUUID().toString());
	        if (inventoryService.checkProductAvailability(order.getProductId())) {
	            //handle exception here
	            paymentService.processPayment(order);
	        } else {
	            throw new RuntimeException("Technical issue please retry");
	        }
	        return order;
	    }
	 
	 // 3. Notify to the user
	 @Async("asyncTaskExecutor")
	 public void notifyUser(Order order) throws InterruptedException {
	        Thread.sleep(4000L);
	        log.info("Notified to the user " + Thread.currentThread().getName());
	    }
	 // 4. Assign to vendor
	 @Async("asyncTaskExecutor")
	  public void assignVendor(Order order) throws InterruptedException {
	        Thread.sleep(5000L);
	        log.info("Assign order to vendor " + Thread.currentThread().getName());
	    }
	 
	  // 5. packaging
	 @Async("asyncTaskExecutor")
	  public void packaging(Order order) throws InterruptedException {
	        Thread.sleep(2000L);
	        log.info("Order packaging completed " + Thread.currentThread().getName());
	    }
	 
	  //6. assign delivery partner
	 @Async("asyncTaskExecutor")
	  public void assignDeliveryPartner(Order order) throws InterruptedException {
	        Thread.sleep(10000L);
	        log.info("Delivery partner assigned " + Thread.currentThread().getName());
	    }
	  
	  //7. assign trailer and dispatch product
	 @Async("asyncTaskExecutor")
	  public void assignTrailerAndDispatch(Order order) throws InterruptedException {
	        Thread.sleep(3000L);
	        log.info("Trailer assigned and Order dispatched " + Thread.currentThread().getName());
	    }


}