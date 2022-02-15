package com.spring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.spring.entity.Product;
import com.spring.entity.Role;
import com.spring.entity.UserRoleCompositeKey;
import com.spring.entity.UserRoles;
import com.spring.entity.Users;
import com.spring.repositories.ProductRepository;
import com.spring.repositories.RoleRepository;
import com.spring.repositories.UserRepository;
import com.spring.repositories.UserRoleRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class InstallData {
//	@Autowired
//	private UserRepository userRepo;
//
//	@Autowired
//	RoleRepository roleRepo;
//	
//	@Autowired
//	UserRoleRepository userRole;
//	
//	@Autowired
//	ProductRepository proRepo;

	@Bean
	@Transactional
	public CommandLineRunner initData() {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
//				Users u = new Users();
//				u.setUserName("user22");
//				u.setPassWord("asdasdasd");
//				userRepo.save(u);
				
//				System.out.println(proRepo.findById((long)5).get().getType().getName());

			}
		};
	}

}
