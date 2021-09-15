package com.oleg.surveyapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Surveys API", description = "API for making surveys"))
public class ServeyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeyApiApplication.class, args);
	}

}
