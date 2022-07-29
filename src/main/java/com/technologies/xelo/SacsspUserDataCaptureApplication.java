package com.technologies.xelo;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.technologies.xelo.security.model.User;
//import com.technologies.xelo.security.model.UserRepository;
import com.technologies.xelo.security.model.Role;
import com.technologies.xelo.security.model.User;
import com.technologies.xelo.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SacsspUserDataCaptureApplication {

	public static void main(String[] args) {
		SpringApplication.run(SacsspUserDataCaptureApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();	}

	@Bean
	public ApplicationRunner initializer(UserService userService ){
		return args -> {

			/*
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_NORMAL"));

			userService.saveUser(new User(null, "sonwabo@xelo-technologies.co.za", "P@ssword1", new ArrayList<>()));
			userService.saveUser(new User(null, "s.singatha@gmail.com", "P@ssword1", new ArrayList<>()));
			userService.saveUser(new User(null, "millym@sacssp.co.za", "P@ssword1", new ArrayList<>()));
			userService.saveUser(new User(null, "leepos@sacssp.co.za", "P@ssword1", new ArrayList<>()));
			userService.saveUser(new User(null, "pamelam@sacssp.co.za", "P@ssword1", new ArrayList<>()));

			userService.addRoleToUser("sonwabo@xelo-technologies.co.za", "ROLE_ADMIN");
			userService.addRoleToUser("s.singatha@gmail.com", "ROLE_ADMIN");
			userService.addRoleToUser("millym@sacssp.co.za", "ROLE_ADMIN");
			userService.addRoleToUser("leepos@sacssp.co.za", "ROLE_ADMIN");
			userService.addRoleToUser("pamelam@sacssp.co.za", "ROLE_ADMIN"); */
		};
	}

}
