package com.test.loganalysisservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "Log Analysis API", version = "2.5.8", description = "Log Analysis", contact = @Contact(name = "Surabhi Gupta")))
@SpringBootApplication
public class LogAnalysisServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LogAnalysisServiceApplication.class, args);
	}
}
