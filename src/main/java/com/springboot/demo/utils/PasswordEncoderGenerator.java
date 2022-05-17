package com.springboot.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {

	public static void main(String[] args) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		System.out.println(encoder.encode("administrator"));
	}
	
}
//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhdG9yVXNlckBnbWFpbC5jb20iLCJpYXQiOjE2NTI4MTM3NDAsImV4cCI6MTY1MzQxODU0MH0.48pm3xGMBN-CvQg04Mv8IMKMl29NyynWH0N57ekYVrAJePB3MvC1gUSFKraaIuQgZG9BvarVNIPey9e7WOmcAQ
