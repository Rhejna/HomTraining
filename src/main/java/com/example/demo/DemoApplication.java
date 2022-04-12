package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_CDE"));
			userService.saveRole(new Role(null, "ROLE_FORMATEUR"));
			userService.saveRole(new Role(null, "ROLE_COMPTABLE"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.createUser(new User(null, "firstName","lastName","fullName",
					"email@gm.cm","1234",123456789,"userCode", new ArrayList<>()));
			userService.createUser(new User(null, "Jim","lastName","fullName",
					"jim@gm.cm","1234",123456789,"userCodeJim", new ArrayList<>()));
			userService.createUser(new User(null, "Paul","lastName","fullName",
					"paul@gm.cm","1234",123456789,"userCodePaul", new ArrayList<>()));

			userService.addRoleToUser("email@gm.cm", "ROLE_USER");
			userService.addRoleToUser("jim@gm.cm", "ROLE_CDE");
			userService.addRoleToUser("jim@gm.cm", "ROLE_FORMATEUR");
			userService.addRoleToUser("paul@gm.cm", "ROLE_COMPTABLE");
			userService.addRoleToUser("paul@gm.cm", "ROLE_ADMIN");

		};
	}

}
