package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/upload")
	public ResponseEntity<List<List<String>>> uploadExcel(@RequestParam("file") MultipartFile file)
			throws EncryptedDocumentException, IOException {
		
		studentService.save(file);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}