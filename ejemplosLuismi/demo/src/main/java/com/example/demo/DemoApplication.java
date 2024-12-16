package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
		@Info(description = "Una api de ejemplo para los alumnos de 2ºDam",
				version = "1.0",
				contact = @Contact(name = "Luismi", email = "emaildeprueba1234@gmail.com"),
				license = @License(name = "Kalise para todos"),
				title = "Api sobre productos")
		)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
