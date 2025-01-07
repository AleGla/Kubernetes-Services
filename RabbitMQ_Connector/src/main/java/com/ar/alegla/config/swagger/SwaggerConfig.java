package com.ar.alegla.config.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
     OpenAPI apiInfo() {
        return new OpenAPI().info(new Info().title("Swagger").version("1.0.0"));
    }

    @Bean
     GroupedOpenApi httpApi() {
        return GroupedOpenApi.builder()
                .group("http")
                .pathsToMatch("/**")
                .build();
    }
	
		
}
