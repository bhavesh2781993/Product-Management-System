package com.bz.pms.exception;

public class RecoveryFailureException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public RecoveryFailureException(String message) {
		super(message);
	}
	
}
