package com.example.demo.resource;

import org.springframework.web.multipart.MultipartFile;

public class EmailMessage {
	
	private String to;
	private String subject;
	private String message;
	private MultipartFile attachment;
	
	public EmailMessage() {
	}
	
	public EmailMessage(String to, String subject, String message, MultipartFile attachment) {
		super();
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.attachment = attachment;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MultipartFile getAttachment() {
		return attachment;
	}

	public void setAttachment(MultipartFile attachment) {
		this.attachment = attachment;
	}
}
