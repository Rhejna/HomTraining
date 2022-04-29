package com.example.demo;

import com.example.demo.config.SwaggerConfiguration;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;


@SpringBootApplication
@EnableAsync
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/*@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}*/

	/*@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_CDE"));
			userService.saveRole(new Role(null, "ROLE_FORMATEUR"));
			userService.saveRole(new Role(null, "ROLE_COMPTABLE"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.createUser(new User(null, "irstName","lastName","fullName",
					"mail@gm.cm","1234",123456789,"userCode", new ArrayList<>()));
			userService.createUser(new User(null, "Tim","lastName","fullName",
					"tim@gm.cm","1234",123456789,"userCodeJim", new ArrayList<>()));
			userService.createUser(new User(null, "Raul","lastName","fullName",
					"raul@gm.cm","1234",123456789,"userCodePaul", new ArrayList<>()));

			userService.addRoleToUser("mail@gm.cm", "ROLE_USER");
			userService.addRoleToUser("tim@gm.cm", "ROLE_CDE");
			userService.addRoleToUser("tim@gm.cm", "ROLE_FORMATEUR");
			userService.addRoleToUser("raul@gm.cm", "ROLE_COMPTABLE");
			userService.addRoleToUser("raul@gm.cm", "ROLE_ADMIN");

		};
	}*/

}
