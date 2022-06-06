package com.springboot.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.demo.entity.Role;
import com.springboot.demo.repository.RoleRepository;

@SpringBootApplication
public class SpringBootBlogApplication implements CommandLineRunner{
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogApplication.class, args);
		
		
		
	}
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		Role adminRole=new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);
		
		Role userRole=new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
	}
	

}
