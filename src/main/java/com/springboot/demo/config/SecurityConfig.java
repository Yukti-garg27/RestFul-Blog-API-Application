package com.springboot.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//To customise default Security cofiguration, extend WebSecurityConfigurerAdapter
	
	//Basic authentication where a pop-up appears rather than a login page, to access the page provide credentials
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		     .csrf().disable()
		     .authorizeRequests()
		     .anyRequest()
		     .authenticated().and()
		     .httpBasic();
	}
	

}
