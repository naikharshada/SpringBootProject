package com.example.demo.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.EmailUtil;
import com.example.demo.util.OtpUtil;

import jakarta.mail.MessagingException;

@Service
public class UserService {
	
	@Autowired
	private OtpUtil otpUtil;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private UserRepository userRepository;

	public String register(RegisterDto registerDto) {
		//generate otp
		String otp = otpUtil.generateOtp();
		
		//send Otp
		try {
			emailUtil.sendOtpEmail(registerDto.getEmail(), otp);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send otp please try again");
		}
		
		//data to store into db
		User user = new User();
	    user.setName(registerDto.getName());
	    user.setEmail(registerDto.getEmail());
	    user.setPassword(registerDto.getPassword());
	    user.setOtp(otp);
	    user.setOtpGeneratedTime(LocalDateTime.now());
	    userRepository.save(user);
		return "User Registered Successfully";
	}

	
	public String verifyAccount(String email, String otp) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		
		if(user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(), LocalDateTime.now()).getSeconds() < (1 * 60)) {
			user.setActive(true);
			userRepository.save(user);
			return "OTP verified you can login";
		}
		return "Please regenerate otp and try again";
	}


	public String regenerateOtp(String email) {
		//get mail
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		//generate otp
		String otp = otpUtil.generateOtp();
		//send otp
		try {
			emailUtil.sendOtpEmail(email, otp);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send otp please try again");
		}
		//data to store into db
		user.setOtp(otp);
		user.setOtpGeneratedTime(LocalDateTime.now());
		userRepository.save(user);
		return "Email sent! please verify account within 1 minute";
	}


	public String login(LoginDto loginDto) {
		//get mail
		User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new RuntimeException("User not found with this email: " + loginDto.getEmail()));
		//match password and verify account
		if(!loginDto.getPassword().equals(user.getPassword())) {
			 return "Password is incorrect";
		} else if (!user.isActive()) {
			 return "your account is not verified";
		}
		return "Login successfully";
	}


	public String forgotPassword(String email) {
		//get mail
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		//send set password email
		try {
			emailUtil.sendSetPasswordEmail(email);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send set password email please try again");
		}
		return "please check your email to set new password to your account";
	}


	public String setPassword(String email, String newPassword) {
		//get mail
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		user.setPassword(newPassword);
		userRepository.save(user);
		return "New password set successfully. login with new password";
	}
}
