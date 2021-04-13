package com.bz.pms.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CustomErrorResponse {

	private HttpStatus status;

	private int statusCode;
	
	private String errorMessage;
	
}
