package com.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByuserName(String username);
	
}
