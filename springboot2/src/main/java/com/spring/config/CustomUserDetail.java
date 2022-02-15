package com.spring.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.entity.Role;
import com.spring.entity.UserRoles;
import com.spring.entity.Users;

public class CustomUserDetail implements UserDetails {

	Users user;

	public CustomUserDetail(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<UserRoles> role = user.getRoles();
		List<SimpleGrantedAuthority> author = new ArrayList<SimpleGrantedAuthority>();
		for (UserRoles roles : role) {
			author.add(new SimpleGrantedAuthority(roles.getRole().getName()));
		}
		return author;
	}
	
	public String test2() {
		return "test";
	}

	@Override
	public String getPassword() {
		return user.getPassWord();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
