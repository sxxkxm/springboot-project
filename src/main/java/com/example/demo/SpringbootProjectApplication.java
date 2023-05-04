package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.constant.RoleNames;
import com.example.demo.data.entity.Role;
import com.example.demo.data.repository.RoleRepository;


@SpringBootApplication
public class SpringbootProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(RoleRepository roleRepository) throws Exception {
		return (String[] args) -> {
			Role roleUser = new Role().builder().name(RoleNames.USER).build();
			Role roleManager = new Role().builder().name(RoleNames.MANAGER).build();
			Role roleAdmin = new Role().builder().name(RoleNames.ADMIN).build();
			
			roleRepository.save(roleUser);
			roleRepository.save(roleManager);
			roleRepository.save(roleAdmin);
		};
	}

}
