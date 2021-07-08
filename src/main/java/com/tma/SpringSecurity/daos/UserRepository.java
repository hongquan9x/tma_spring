package com.tma.SpringSecurity.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tma.SpringSecurity.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select user from User user where user.username = ?1")
	public User getByUsername(String username);
	
}
