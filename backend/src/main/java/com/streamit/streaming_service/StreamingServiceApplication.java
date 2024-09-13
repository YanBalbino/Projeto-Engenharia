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


@SpringBootApplication
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
