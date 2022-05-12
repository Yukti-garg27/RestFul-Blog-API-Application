package com.springboot.demo.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.demo.entity.Role;
import com.springboot.demo.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
	com.springboot.demo.entity.User user=	userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
		.orElseThrow(
				()-> new UsernameNotFoundException("User not found with given username or email: "+usernameOrEmail));
				
		return new User(user.getEmail(),user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
		}

	//convert set of roles into granted authorities
	//SimpleGrantedAuthority implements GrantedAuthority interface
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
	return	roles.stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
}
