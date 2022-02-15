package com.spring.config;

import com.spring.model.UserDto;

public interface UserService {
	void resisterNewUser(UserDto userDto) throws Exception;
	boolean emailExsit(String email);
}
