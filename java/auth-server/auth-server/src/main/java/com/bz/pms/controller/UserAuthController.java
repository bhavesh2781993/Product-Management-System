package com.bz.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bz.pms.model.AuthRequest;
import com.bz.pms.model.AuthResponse;
import com.bz.pms.model.CreateUserRequest;
import com.bz.pms.service.AuthService;

@RestController
@RequestMapping("/user")
public class UserAuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping(value = {"/authenticate", "/login"})
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest request) {
		AuthResponse response = authService.authenticateUser(request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = {"/create", "/signup"})
	public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request) {
		authService.createUser(request);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/refreshToken")
	public ResponseEntity<AuthResponse> refreshToken(@RequestBody AuthRequest request) {
		AuthResponse response = authService.refreshToken(request);
		
		return new ResponseEntity<>(response, HttpStatus.OK);		
	}
}
