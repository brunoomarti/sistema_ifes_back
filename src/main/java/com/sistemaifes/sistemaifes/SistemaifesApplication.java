package com.sistemaifes.sistemaifes;

import com.sistemaifes.sistemaifes.controller.AuthenticationController;
import com.sistemaifes.sistemaifes.dto.request.UserRequestDTO;
import com.sistemaifes.sistemaifes.repository.UserRepository;
import com.sistemaifes.sistemaifes.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SistemaifesApplication {
	@Autowired
	private AuthenticationController authenticationController;
	public static void main(String[] args) {
		SpringApplication.run(SistemaifesApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase (UserRepository userRepository){
		return args -> {
			if (userRepository.findByLogin("admin") == null){
				UserRequestDTO u = new UserRequestDTO("Admin", "admin", "123456!", UserRole.ADMIN);
				authenticationController.register(u);
			}
		};
	}
}
