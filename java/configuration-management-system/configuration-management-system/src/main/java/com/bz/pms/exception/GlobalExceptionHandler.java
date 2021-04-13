package com.bz.pms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bz.pms.model.CustomErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = CustomTechnicalException.class)
	public ResponseEntity<Object> handleBusinessException(CustomTechnicalException exception) {
		CustomErrorResponse errorResponse = new CustomErrorResponse();
		errorResponse.setErrorMessage(exception.getMessage());
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
}
