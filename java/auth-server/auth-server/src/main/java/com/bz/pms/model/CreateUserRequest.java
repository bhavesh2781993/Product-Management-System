package com.bz.pms.model;

import com.bz.pms.enums.UserRole;
import com.bz.pms.enums.UserStatus;

import lombok.Data;

@Data
public class CreateUserRequest {
	
	private String username;
	private String password;
	
	private UserRole role;
	private UserStatus status;
	
	private String firstName;
	private String lastName;
	private String email;	
	private String company;
	
}
