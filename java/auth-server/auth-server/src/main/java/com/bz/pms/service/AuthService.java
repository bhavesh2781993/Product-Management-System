package com.bz.pms.service;

import com.bz.pms.model.AuthRequest;
import com.bz.pms.model.AuthResponse;
import com.bz.pms.model.CreateUserRequest;

public interface AuthService {

	AuthResponse authenticateUser(AuthRequest request);
	
	AuthResponse refreshToken(AuthRequest request);
	
	void createUser(CreateUserRequest request);
}
