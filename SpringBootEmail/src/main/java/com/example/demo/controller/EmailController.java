package com.example.demo.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.resource.EmailMessage;
import com.example.demo.service.EmailSenderService;

import jakarta.mail.MessagingException;

@RestController
public class EmailController {
	
	private final EmailSenderService emailSenderService;
	
	public EmailController(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}

	@PostMapping("/send-email")
	public ResponseEntity sendEmail(@ModelAttribute EmailMessage emailMessage) throws MessagingException, IOException {
		this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage(), emailMessage.getAttachment());
		return ResponseEntity.ok("Email Sent Successfully");
	}
}
