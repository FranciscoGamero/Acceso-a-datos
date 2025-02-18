package com.salesianostriana.ProyectoConecta;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(description = "API para la gestión de una aplicacion de prácticas académicas",
		title = "Proyecto Conecta API",
		version = "1.0"))
public class ProyectoConectaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoConectaApplication.class, args);
	}

}
