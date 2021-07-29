package com.bz.pms.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bz.pms.enums.AuthErrorMessage;
import com.bz.pms.enums.TokenType;
import com.bz.pms.exception.AuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	private static final String AUTH_SECRET_KEY = "PMSZindabad@123";
	private static final String REFRESH_SECRET_KEY = "PMSRefreshZindabad!@#";
	
	private static final Long AUTH_TOKEN_EXPIRY_TIME = 1000L * 60 * 60 * 1; 
	private static final Long REFRESH_TOKEN_EXPIRY_TIME = 1000L * 60 * 60 * 5; 
	
    public String extractUsername(String token, TokenType type) {
        return extractClaim(token, type, Claims::getSubject);
    }

    public Date extractExpiration(String token, TokenType type) {
        return extractClaim(token, type, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, TokenType type, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token, type);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token, TokenType type) {
    	if(type == TokenType.AUTH) {
    		return Jwts.parser().setSigningKey(AUTH_SECRET_KEY).parseClaimsJws(token).getBody();    		    		
    	}else if (type == TokenType.REFRESH) {
    		return Jwts.parser().setSigningKey(REFRESH_SECRET_KEY).parseClaimsJws(token).getBody();    		    		
    	}else {
    		throw new AuthenticationException(AuthErrorMessage.TOKEN_INVALID_TYPE);
    	}
    }

    public Boolean isTokenExpired(String token, TokenType type) {
        return extractExpiration(token, type).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), AUTH_TOKEN_EXPIRY_TIME, AUTH_SECRET_KEY);
    }
    
    public String generateRefreshToken(UserDetails userDetails) {
    	Map<String, Object> claims = new HashMap<>();
    	return createToken(claims, userDetails.getUsername(), REFRESH_TOKEN_EXPIRY_TIME, REFRESH_SECRET_KEY);
    }

    private String createToken(Map<String, Object> claims, String subject, Long expiryTime, String secretKey) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiryTime))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public boolean validateToken(String token, TokenType type, UserDetails userDetails) {
        final String username = extractUsername(token, type);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, type));
    }
}
