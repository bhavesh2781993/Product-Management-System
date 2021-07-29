package com.bz.pms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {
	private String username;
	
	private String token;
	
	private String refreshToken;
	
	private String role;
	
	private String expiryTime;
	
}
