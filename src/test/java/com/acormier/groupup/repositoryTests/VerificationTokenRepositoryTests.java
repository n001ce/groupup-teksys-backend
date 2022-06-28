package com.acormier.groupup.repositoryTests;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.acormier.groupup.model.VerificationToken;
import com.acormier.groupup.repository.VerificationTokenRepository;

/*
 * Optional<VerificationToken> findByToken(String token);
 */


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VerificationTokenRepositoryTests {
	
	@Autowired
	private VerificationTokenRepository tokenRepo;
	
	@Test
	public void findByTokenTest() {
		VerificationToken token = VerificationToken.builder()
				.token("test token")
				.build();
		tokenRepo.save(token);
		tokenRepo.findByToken(token.getToken());
		
		Assertions.assertThat(token).isNotNull();
	}

}
