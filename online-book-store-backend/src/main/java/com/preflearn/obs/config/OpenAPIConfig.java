package com.preflearn.obs.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

@OpenAPIDefinition(
        info = @Info(
                title = "Online Book Store",
                description = "Online Book Store using Spring Boot",
                version = "0.0.1"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8088/api/v1",
                        description = "Local ENV"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "Bearer Auth"
                )
        }
)
@SecurityScheme(
        name = "Bearer Auth",
        description = "JWT Auth using bearer token",
        scheme = "Bearer",
        type = HTTP,
        in = HEADER,
        bearerFormat = "JWT"
)
public class OpenAPIConfig {
}
