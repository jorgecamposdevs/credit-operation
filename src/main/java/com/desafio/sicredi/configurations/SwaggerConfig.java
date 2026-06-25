package com.desafio.sicredi.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Credit Operation")
                        .version("1.0.0")
                        .description("Esta aplicação implementa a primeira etapa do processo de contratação, disponibilizando uma API REST desenvolvida em Java com Spring Boot.")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento")
                                .email("eusoujorgecampos@gmail.com")
                                .url("https://www.credit-operation"))
                        .license(new License()
                                .name("Licença Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}