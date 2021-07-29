package com.bz.pms.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bz.pms.document.User;
import com.bz.pms.enums.UserRole;
import com.bz.pms.enums.UserStatus;
import com.bz.pms.model.CreateUserRequest;
import com.bz.pms.model.CustomUserDetails;
import com.bz.pms.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findById(username);		
		
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			return new CustomUserDetails(user);
		}
		
		throw new UsernameNotFoundException("User " + username + " not found");
	}
	
	public CustomUserDetails createUser(CreateUserRequest requestedUser) {
		User user = new User();
		user.setUsername(requestedUser.getUsername());
		user.setFirstName(requestedUser.getFirstName());
		user.setLastName(requestedUser.getLastName());
		user.setEmail(requestedUser.getEmail());
		user.setCompany(requestedUser.getCompany());
		user.setPassword(passwordEncoder.encode(requestedUser.getPassword()));
		user.setStatus(UserStatus.PENDING);
		user.setRoles(Arrays.asList(UserRole.USER));
		user.setEnabled(false);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		
		user = userRepository.save(user);
		return new CustomUserDetails(user);
	}
		
}
