package com.elang.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elang.rest.api.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	// buat user baru
	@PostMapping(value = "/register", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> register(@RequestPart("user") String user, @RequestPart("image") MultipartFile image) {
		return userService.register(user, image);
	}
	
	// login
	@PostMapping("/login")
	public ResponseEntity<?> login(String email, String password) {
		return userService.login(email, password);
	}
	
	// update data user
	@PostMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> update(@RequestPart("user") String user, @RequestPart("image") MultipartFile image) {
		return userService.update(user, image);
	}
	
	// lihat semua user yang belum verified
	@GetMapping("/unverified")
	public ResponseEntity<?> getUnverified() {
		return userService.getUnverified();
	}
		
	// verify user
	@PostMapping("/verifying")
	public ResponseEntity<?> verifying(Long userId){
		return userService.verifying(userId);
	}
	
}
