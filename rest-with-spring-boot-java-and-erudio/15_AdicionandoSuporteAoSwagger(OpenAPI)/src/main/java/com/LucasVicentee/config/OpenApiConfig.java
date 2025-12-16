package com.LucasVicentee.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean // É um objeto instanciado que é montado e gerenciado pelo Spring, buscando informações XM annotations e Java
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
                        .version("v1")
                        .description("REST API's RESTful from 0 with Java, Spring Boot, Kubernetes and Docker")
                        .termsOfService("https://www.linkedin.com/in/lucas-henrique-vicente-dos-santos-0766612b2/")
                        .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.linkedin.com/in/lucas-henrique-vicente-dos-santos-0766612b2/")));
    }
}
