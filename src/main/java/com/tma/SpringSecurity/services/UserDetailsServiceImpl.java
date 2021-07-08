package com.tma.SpringSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tma.SpringSecurity.daos.UserRepository;
import com.tma.SpringSecurity.models.User;
import com.tma.SpringSecurity.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.getByUsername(username);
		
		if (user == null) {
			System.out.println("Not found username" + username);
			throw new UsernameNotFoundException(username);
		}
		
		return new MyUserDetails(user);
	}

}
