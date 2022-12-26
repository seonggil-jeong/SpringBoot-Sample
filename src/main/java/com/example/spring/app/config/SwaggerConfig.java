package com.example.spring.app.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.spring.app.constants.AccountConstants.AUTHORIZATION_TOKEN_KEY;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi customTestOpenAPi() {

        return GroupedOpenApi
                .builder()
                .group("SpringBoot-Sample")
                .pathsToMatch("/**")
                .addOpenApiCustomiser(buildSecurityOpenApi()).build();
    }

    public OpenApiCustomiser buildSecurityOpenApi() {
        return OpenApi -> OpenApi.addSecurityItem(new SecurityRequirement().addList("jwt token"))
                .getComponents().addSecuritySchemes("jwt token", new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .bearerFormat("JWT")
                        .scheme("bearer"));
    }


    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SpringBoot-Sample API")
                        .description("sample").version("v1.0.2"));
    }
}
