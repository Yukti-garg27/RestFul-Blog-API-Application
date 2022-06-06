package com.springboot.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.entity.Role;
import com.springboot.demo.entity.User;
import com.springboot.demo.payload.JWTAuthResponse;
import com.springboot.demo.payload.LoginDto;
import com.springboot.demo.payload.SignUpDto;
import com.springboot.demo.repository.UserRepository;
import com.springboot.demo.security.JwtTokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Auth controller exposes siginin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
//	@Autowired
//	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	 @ApiOperation(value = "REST API to Register or Signup user to Blog app")
	 @PostMapping("/signin")
	    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // get token form tokenProvider
	        String token = tokenProvider.generateToken(authentication);

	        return ResponseEntity.ok(new JWTAuthResponse(token));
	    }

	  @ApiOperation(value = "REST API to Signin or Login user to Blog app")
	  @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

	        // add check for username exists in a DB
	        if(userRepository.existsByUsername(signUpDto.getUsername())){
	            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
	        }

	        // add check for email exists in DB
	        if(userRepository.existsByEmail(signUpDto.getEmail())){
	            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
	        }

	        // create user object
	        User user = new User();
	        user.setName(signUpDto.getName());
	        user.setUsername(signUpDto.getUsername());
	        user.setEmail(signUpDto.getEmail());
	        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

		/*
		 * Static Role Role roles =roleRepository.findByName("ROLE_ADMIN").get(); Create
		 * an immutable object with an specific role for newly signup user
		 * user.setRoles(Collections.singleton(roles));
		 */
		/*
		 * Dynamic roles added
		 */
		Set<Role> newRoles = new HashSet<>();
		List<String> temp = signUpDto.getRoles();

		temp.stream().forEach(role -> {
			Role tempRole = new Role();
			tempRole.setName(role);
			newRoles.add(tempRole);
		});

		user.setRoles(newRoles);
		userRepository.save(user);
		return new ResponseEntity<>("New user registered successfully!", HttpStatus.CREATED);

	}

}
