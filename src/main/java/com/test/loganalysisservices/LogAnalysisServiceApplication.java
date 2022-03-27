package com.test.loganalysisservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@OpenAPIDefinition(info = @Info(title = "Log Analysis API", version = "2.5.8", description = "Log Analysis", contact = @Contact(name = "Surabhi Gupta")))
@SpringBootApplication
public class LogAnalysisServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAnalysisServiceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.OAS_30).select().apis(RequestHandlerSelectors.any())
				.build()/*
						 * .apiInfo( new ApiInfoBuilder().title("Log Analysis API").version("2.5.8").
						 * description("Log Analysis").contact( new
						 * springfox.documentation.service.Contact("Surabhi Gupta", "",
						 * "sur.gupta93@gmail.com")) .build())
						 */;
	}

}
