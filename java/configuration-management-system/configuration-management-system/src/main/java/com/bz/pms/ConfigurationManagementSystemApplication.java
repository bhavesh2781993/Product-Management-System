package com.bz.pms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigurationManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationManagementSystemApplication.class, args);
	}

}
