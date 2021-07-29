package com.bz.pms.exception;

import com.bz.pms.enums.AuthErrorMessage;

public class AuthenticationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7173156254635356158L;

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String message) {
		super(message);
	}
	
	public AuthenticationException(AuthErrorMessage message) {
		super(message.getErrorMsg());
	}
}
