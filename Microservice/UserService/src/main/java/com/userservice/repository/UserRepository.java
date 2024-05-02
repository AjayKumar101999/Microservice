package com.userservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice.entity.User;


public interface UserRepository extends JpaRepository<User, String>{
	
	public List<User> findAll();
}
