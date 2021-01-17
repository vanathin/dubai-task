package com.ekar.demo.app.api.document.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Ekar - Producer Consumer - Demo Service - OpenAPI 3.0 documentation",
                description = "These APIs are used to update number of threads for producer / consumer and set the counter.",
                contact = @Contact(
                        name = "Vanathi N",
                        url = "https://github.com/vanathin",
                        email = "vanudevi@gmail.com"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "")),
        servers = @Server(url = "http://localhost:8080")
)
public class OpenAPIConfig {
}
