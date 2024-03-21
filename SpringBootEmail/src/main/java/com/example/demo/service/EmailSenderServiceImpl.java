package com.example.demo.service;

import java.io.File;
import java.io.IOException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
	
	private final JavaMailSender mailSender;
	
	public EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

	@Override
	public void sendEmail(String to, String subject, String message, MultipartFile attachment) throws MessagingException, IOException {
		
		//to send attachment
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
	    mimeMessageHelper.setTo(to);
	    mimeMessageHelper.setSubject(subject);
	    mimeMessageHelper.setText(message, true);
	    mimeMessageHelper.addAttachment(attachment.getOriginalFilename(), convertMultipartToFile(attachment, attachment.getOriginalFilename()));
	    
	    this.mailSender.send(mimeMessage);
	}
	
	private static File convertMultipartToFile(MultipartFile multipartFile, String fileName) throws IOException {
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipartFile.transferTo(convFile);
		return convFile;
	}
}
