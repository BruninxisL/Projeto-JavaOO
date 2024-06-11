package com.example.AppPastelaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.AppPastelaria.model.CustomUserDetails;
import com.example.AppPastelaria.model.User;
import com.example.AppPastelaria.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override 
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		return new CustomUserDetails(user);
	}
}
