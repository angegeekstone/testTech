package com.geekstone.testtech.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OpenApi specification - Ange Stephane Kobo (GeekStone)")
                        .description("Description de mon API")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Ange Stephane Kobo")
                                .url("https://mon-site.com")
                                .email("angestephanekobo@gmail.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentation complète")
                        .url("https://mon-site.com/docs"));
    }
}
