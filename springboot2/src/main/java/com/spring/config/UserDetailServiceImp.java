package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.entity.Users;
import com.spring.repositories.UserRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepo.findByuserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("dont have this user!");
		}
		return new CustomUserDetail(user);
	}

}
