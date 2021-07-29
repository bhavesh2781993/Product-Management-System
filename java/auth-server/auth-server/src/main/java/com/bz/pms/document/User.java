package com.bz.pms.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bz.pms.enums.UserRole;
import com.bz.pms.enums.UserStatus;

import lombok.Data;

@Data
@Document(collection = "user_details")
public class User {
	
	@Id
	private String username;
	
	private String password;
	
	private List<UserRole> roles;
		
	private boolean enabled;
	
	private boolean accountNonLocked;
	
	private boolean accountNonExpired;
	
	private boolean credentialNonExpired;	
	
	// Extra Attributes
	private UserStatus status;
	private String firstName;
	private String lastName;
	private String email;	
	private String company;
}
