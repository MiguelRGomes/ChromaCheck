package br.com.java.tcc.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("miguelrgomes07@gmail.com");
        contact.setName("Miguel Rodrigues Gomes");
        contact.setUrl("https://www.bezkoder.com");

        Info info = new Info()
                .title("ChormaCheck API")
                .version("1.0")
                .contact(contact)
                .description("API para criação de orçamentos");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}