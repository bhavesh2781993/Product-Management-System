package com.bz.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableRetry
public class CommonConfiguration {

	@Bean
	public ObjectMapper getObjcetMapper() {
		return new ObjectMapper();		
	}
	
}
