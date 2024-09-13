package com.streamit.streaming_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.streamit.streaming_service.enums.UserRole;
import com.streamit.streaming_service.model.AdminModel;
import com.streamit.streaming_service.model.PersonModel;
import com.streamit.streaming_service.repositories.AdminRepository;
import com.streamit.streaming_service.repositories.PersonRepository;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;


@SpringBootApplication
@OpenAPIDefinition(
	    info = @Info(
	        title = "StreamIt - API para serviço de streaming",
	        description = "Documentação da API REST para o serviço de streaming",
	        version = "v1",
	                contact = @Contact(
	                        name = "Equipe StreamIt",
	                        email = "contato@streamit.com"
	                    ),
	        license = @License(
	            name = "Apache 2.0",
	            url = "https://www.apache.org/licenses/LICENSE-2.0"
	        )
	    ),
	    servers = {
	        @Server(
	            url = "http://localhost:8080",
	            description = "Servidor local"
	        ),
	        @Server(
	            url = "https://api.streamit.com.br",
	            description = "Servidor de produção"
	        )
	    },
	    externalDocs = @ExternalDocumentation(
	        description = "Documentação completa da API REST da StreamIt",
	        url = "http://localhost:8080/swagger-ui.html"
	    )
	)
public class StreamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingServiceApplication.class, args);
	}

    @Bean
    CommandLineRunner initAdmin(PersonRepository personRepository, AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!personRepository.existsByEmail("admin@streamit.com")) {
                PersonModel adminPerson = new PersonModel();
                adminPerson.setNome("adm");
                adminPerson.setEmail("admin@streamit.com");
                adminPerson.setSenha(passwordEncoder.encode("admin123")); 
                adminPerson.setRole(UserRole.ADMIN);
                
                AdminModel admin = new AdminModel();
                admin.setPerson(adminPerson);
                
                adminRepository.save(admin);
                
                System.out.println("Administrador criado com sucesso.");
            }
        };
    }
}
