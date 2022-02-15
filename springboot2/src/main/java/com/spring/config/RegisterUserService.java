package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.entity.UserRoles;
import com.spring.entity.Users;
import com.spring.model.UserDto;
import com.spring.repositories.RoleRepository;
import com.spring.repositories.UserRepository;
import com.spring.repositories.UserRoleRepository;

@Service
public class RegisterUserService implements UserService {

	@Autowired
	UserRepository repo;

	@Autowired
	UserRoleRepository userRoleRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	BCryptPasswordEncoder passwrodEncode;

	@Override
	public void resisterNewUser(UserDto userDto) throws Exception {
		if (emailExsit(userDto.getUserName())) {
			throw new UsernameNotFoundException("there is an account with username already exsit!");
		}
		Users user = new Users();
		user.setUserName(userDto.getUserName());
		user.setPassWord(passwrodEncode.encode(userDto.getPassWord()));
		UserRoles userRole = new UserRoles();
		userRole.setRole(roleRepo.findById((long) 2).get());
		userRole.setUser(repo.save(user));
		userRoleRepo.save(userRole);
	}

	@Override
	public boolean emailExsit(String email) {
		return repo.findByuserName(email) != null;
	}

}
