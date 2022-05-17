package com.springboot.demo.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	// Docket Instance- To configure API Documentation Info

	private ApiInfo apiInfo() {
		return new ApiInfo("Spring Boot Bog REST APIs", 
				"Spring Boot Bog REST API Documentation",
				"1",
				"Terms of Service",
				new Contact("Yukti", "www.demo.com", "yukti@gmail.com"), 
				"License of API",
				"API Licence Url", 
				Collections.emptyList());
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()) 
				.select()
				.apis(RequestHandlerSelectors.any())  //Configure from where Swagger should pick REST APIs
				.paths(PathSelectors.any())  //Path documentation for the paths
				.build();// Build docket object
	}

}
