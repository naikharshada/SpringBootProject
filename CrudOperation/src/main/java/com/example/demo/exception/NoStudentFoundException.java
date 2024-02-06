package com.example.demo.exception;

public class NoStudentFoundException extends RuntimeException {
	
	public NoStudentFoundException(String msg) {
		super(msg);
	}
	
	public NoStudentFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
