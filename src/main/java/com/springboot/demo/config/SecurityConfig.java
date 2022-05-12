package com.springboot.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
//To enable method level security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//To customise default Security cofiguration, extend WebSecurityConfigurerAdapter
	
	/*Basic authentication where a pop-up appears rather than a login page
	 * to access the page provide credentials
	 * 
	 * Provided credentials with username and password are encoded in base64 
	 * And are added to Header's section Key:Authorization
	
	*/
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		     .csrf().disable()
		     .authorizeRequests()
		     .antMatchers(HttpMethod.GET, "/api/**").permitAll()
		     .anyRequest()
		     .authenticated().and()
		     .httpBasic();
	}
	
	
	/*
	 * In Memory Authorization - Role based authentication
	 */
	/*
	 * Provided password in string format. Encode using BcryptPasswordEncoder 
	 */
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
	UserDetails vance=User.builder().username("Vance").password(passwordEncoder().encode("abc123"))
	  .roles("USER").build();
	UserDetails admin=User.builder().username("admin").password(passwordEncoder().encode("admin"))
			  .roles("ADMIN").build();
	
	return new InMemoryUserDetailsManager(vance,admin);
	
	}

}
