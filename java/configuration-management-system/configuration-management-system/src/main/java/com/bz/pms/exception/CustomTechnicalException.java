package com.bz.pms.exception;

public class CustomTechnicalException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CustomTechnicalException(String message) {
		super(message);
	}
	
}
