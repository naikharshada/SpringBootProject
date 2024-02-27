package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BalanceExceptionHandler {
	
	@ExceptionHandler(value = {InsufficientAmountException.class})
	public ResponseEntity<Object> handleInsufficientAmountException(InsufficientAmountException insufficientAmountExecption) {
		ApiError apiError = new ApiError(
				insufficientAmountExecption.getMessage()
				
		);
		return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
	}
}
