package com.bz.pms.enums;

public enum AuthErrorMessage {

	TOKEN_EXPIRED("Token expired"),
	TOKEN_INVALID("Token invalid"),
	TOKEN_INVALID_TYPE("Token invalid type"),
	USER_DISABLED("User disabled"),
	USER_LOCKED("User locked"),
	USER_EXPIRED("User expired"),
	USER_INVALID_CREDENTIALS("User invalid credentials"),
	COMMON_ERROR("Something went wrong, please contact Admin");
	
	private String errorMsg;
	
	private AuthErrorMessage(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
}
