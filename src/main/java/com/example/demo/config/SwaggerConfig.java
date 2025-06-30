package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class SwaggerConfig {

	@Bean
	@Primary
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	        .info(new Info().title("Ecommerce API").version("1.0"))
	        .components(new Components().addSecuritySchemes("bearerAuth",
	            new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
	        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	}

}

