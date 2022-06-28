package com.acormier.groupup.repositoryTests;

import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.acormier.groupup.model.Game;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.UserRepository;

/*
 * Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Set<Game> collectedGames(User user);
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void findByUsernameTest() {
		User user = User.builder()
				.username("Test user")
				.created(Instant.now())
				.email("test@test.com")
				.password("test123").build();
		userRepo.save(user);
		User found = userRepo.findByUsername(user.getUsername()).orElse(null);
		Assertions.assertThat(found).isNotNull();
	}
	
	@Test
	public void findByEmailTest() {
		User user = User.builder()
				.username("Test user")
				.created(Instant.now())
				.email("test@test.com")
				.password("test123").build();
		userRepo.save(user);
		User found = userRepo.findByEmail(user.getEmail()).orElse(null);
		Assertions.assertThat(found).isNotNull();
	}
	
}
