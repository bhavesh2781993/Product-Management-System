package com.bz.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.bz.pms.enums.AuthErrorMessage;
import com.bz.pms.enums.TokenType;
import com.bz.pms.exception.AuthenticationException;
import com.bz.pms.model.AuthRequest;
import com.bz.pms.model.AuthResponse;
import com.bz.pms.model.CreateUserRequest;
import com.bz.pms.model.CustomUserDetails;
import com.bz.pms.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public AuthResponse authenticateUser(AuthRequest request) {
		String username = request.getUsername();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, request.getPassword()));
		} catch (DisabledException e) {
			log.error("[{}] >> DisabledException in authenticateUser >> {}", username, e);
			throw new AuthenticationException(AuthErrorMessage.USER_DISABLED);
		} catch (LockedException e) {
			log.error("[{}] >> LockedException in authenticateUser >> {}", username, e);
			throw new AuthenticationException(AuthErrorMessage.USER_LOCKED);
		} catch (BadCredentialsException e) {
			log.error("[{}] >> BadCredentialsException in authenticateUser >> {}", username, e);
			throw new AuthenticationException(AuthErrorMessage.USER_INVALID_CREDENTIALS);
		} catch (Exception e) {
			log.error("[{}] >> Exception in authenticateUser >> {}", username, e);
			throw new AuthenticationException(AuthErrorMessage.COMMON_ERROR);
		}

		return generateAuthResponse(request.getUsername());
	}
	
	@Override
	public AuthResponse refreshToken(AuthRequest request) {
		try {
			final String refreshToken = request.getRefreshToken();
			final String email = jwtUtil.extractUsername(refreshToken, TokenType.REFRESH);
			final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(email);
		
			if(jwtUtil.validateToken(refreshToken, TokenType.REFRESH, userDetails)) {
				return generateAuthResponse(request.getUsername());			
			} else {
				throw new AuthenticationException(AuthErrorMessage.TOKEN_EXPIRED);
			}
		}catch (ExpiredJwtException e) {
			throw new AuthenticationException(AuthErrorMessage.TOKEN_EXPIRED);
		}catch (Exception e) {
			throw new AuthenticationException(AuthErrorMessage.TOKEN_INVALID);
		}
	}



	@Override
	public void createUser(CreateUserRequest request) {
		userDetailsService.createUser(request);
	}

	private AuthResponse generateAuthResponse(String username) {
		final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(username);
		final String authToken = jwtUtil.generateToken(userDetails);
		final String refreshToken = jwtUtil.generateRefreshToken(userDetails);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setUsername(userDetails.getUsername());
		authResponse.setToken(authToken);
		authResponse.setRefreshToken(refreshToken);
		
		String role = null;
		for(GrantedAuthority auth: userDetails.getAuthorities()) {
			role = auth.getAuthority();
		}
		authResponse.setRole(role);
		authResponse.setExpiryTime(""+jwtUtil.extractExpiration(authToken, TokenType.AUTH).getTime());
		
		log.info("Auth Token Expiry Time: {}", jwtUtil.extractExpiration(authToken, TokenType.AUTH).getTime());
		log.info("Refresh Token Expiry Time: {}", jwtUtil.extractExpiration(refreshToken, TokenType.REFRESH).getTime());
		return authResponse;
	}

}
