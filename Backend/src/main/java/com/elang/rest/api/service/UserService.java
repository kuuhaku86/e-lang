package com.elang.rest.api.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elang.rest.api.model.Users;
import com.elang.rest.api.repository.AdminRepository;
import com.elang.rest.api.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class UserService {

	@Autowired
	UsersRepository userRepository;
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	FileService fileRepositoy;
	
	ObjectMapper objectMapper = new ObjectMapper();
		
	public ResponseEntity<?> register(String stringUser, MultipartFile image) {
		Users newUser = stringToUser(stringUser);
		if( userRepository.findByEmail(newUser.getEmail()) != null )
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return saveUser(newUser, image);
	}
	
	public ResponseEntity<?> login(String email, String password) {		
		Object user = userRepository.findByEmailAndPassword(email, password);
		if( user != null )
			return new ResponseEntity<>(
	        		user,
	        		HttpStatus.OK);
		
		user = adminRepository.findByEmailAndPassword(email, password);
		if( user != null )
			return new ResponseEntity<>(
	        		user,
	        		HttpStatus.OK);
		
		return new ResponseEntity<>(
        		null,
        		HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> update(String stringUser, MultipartFile image) {
		Users user = stringToUser(stringUser);
		
		if( userRepository.findById(user.getId()).isEmpty())
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		
		return saveUser(user, image);
	}
	
	public ResponseEntity<?> getUnverified(){
		return new ResponseEntity<>(
				userRepository.findByVerified("unverified"),
				HttpStatus.OK);
	}
	
	public ResponseEntity<?> verifying(Long id){
		if (userRepository.findById(id).isEmpty())
			return new ResponseEntity<>(
					HttpStatus.NOT_FOUND);
		
		Users user = userRepository.getOne(id);
		user.setVerified("verified");
		userRepository.save(user);
		
		return new ResponseEntity<>(
				HttpStatus.OK);
	}
	
	
	
	/*--------------- Helper Function ------------------------------------------- */
	
	protected Boolean checkUser(Long id) {
		return userRepository.existsById(id);
	}
	
	private Users stringToUser(String stringUser) {
		try {
			return objectMapper.readValue(stringUser, Users.class);
		}catch(IOException e) {
			System.out.print(e);
			return null;
		}
	}

	private ResponseEntity<?> saveUser(Users user, MultipartFile image) {
		user.setVerified("unverified");
		
		Users savedUser = userRepository.save(user);
		
		String fileName = "User_" + savedUser.getId() + 
				image.getOriginalFilename().substring( image.getOriginalFilename().lastIndexOf('.') );
        user.setImageUrl( fileRepositoy.storeFile(image, fileName) );
        
        return new ResponseEntity<>(
        		userRepository.save(savedUser),
        		HttpStatus.OK);
	}

}
