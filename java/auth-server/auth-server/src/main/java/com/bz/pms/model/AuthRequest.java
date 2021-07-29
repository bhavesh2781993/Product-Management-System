package com.bz.pms.model;

import lombok.Data;

@Data
public class AuthRequest {
	
	private String username;
	
	private String password;
	
	private String refreshToken;
	
}
