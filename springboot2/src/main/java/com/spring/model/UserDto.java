package com.spring.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
public class UserDto {
	@Pattern(regexp = "[a-zA-z0-9]+",message = "username cant be null")
	private String userName;
	@Pattern(regexp = "[a-zA-z0-9]+",message = "password cant be null")
	private String passWord;

	public UserDto create(String userName, String passWrod) {
		UserDto u = new UserDto();
		u.setPassWord(passWrod);
		u.setUserName(userName);
		return u;
	}
}
