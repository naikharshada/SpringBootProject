package com.example.demo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;

public interface EmailSenderService {	
	void sendEmail(String to, String subject, String message, MultipartFile attachment) throws MessagingException, IOException;
}
