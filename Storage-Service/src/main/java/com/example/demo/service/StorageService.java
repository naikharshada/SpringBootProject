package com.example.demo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ImageData;
import com.example.demo.repository.StorageRepository;
import com.example.demo.util.ImageUtils;

@Service
public class StorageService {
	
	@Autowired 
	public StorageRepository storageRepository;
	
	public String uploadImage(MultipartFile file) throws IOException {
		ImageData imageData = storageRepository.save(ImageData.builder()
				.name(file.getOriginalFilename())
				.type(file.getContentType())
				.imageData(ImageUtils.compressImage(file.getBytes())).build());
		
		if(imageData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}
	
	public byte[] downloadImage(String name) {
		Optional<ImageData> dbImageData = storageRepository.findByName(name);
		byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
		return images;
	}
}
