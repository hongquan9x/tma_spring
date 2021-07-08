package com.tma.SpringSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tma.SpringSecurity.daos.UserRepository;
import com.tma.SpringSecurity.models.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User getByUsername(String username) {
		return repository.getByUsername(username);
	}
}
