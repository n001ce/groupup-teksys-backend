package com.acormier.groupup.servicesTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.UserRepository;
import com.acormier.groupup.service.UserServiceImp;

@SpringBootTest
public class UserServiceImpTest {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Test
	@DisplayName("Should pass if loadbyUsername loads the same user as user created")
	public void loadUserByUsernameTest() {
		User user = userRepository.findById((long)7).orElse(null);
		UserServiceImp userService = new UserServiceImp(userRepository);
		UserDetails verify = userService.loadUserByUsername(user.getUsername());
		Assertions.assertEquals(verify.getUsername(), user.getUsername());
		
	}
}
