package com.example.demo.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService {
	
	 public boolean checkProductAvailability(int productId) {
	        return true;
	    }
}
