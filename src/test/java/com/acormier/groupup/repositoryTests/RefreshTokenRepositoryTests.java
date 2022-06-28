package com.acormier.groupup.repositoryTests;

import java.time.Instant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.acormier.groupup.model.RefreshToken;
import com.acormier.groupup.repository.RefreshTokenRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RefreshTokenRepositoryTests {
	
	@Autowired
	private RefreshTokenRepository tokenRepo;
	
	//test for findByToken
	@Test
	public void findByTokenTest() {
		RefreshToken token = RefreshToken.builder()
				.createdDate(Instant.now())
				.token("test token")
				.build();
		tokenRepo.save(token);
		tokenRepo.findByToken(token.getToken());
		
		Assertions.assertThat(token.getId()).isGreaterThan(0);
	}
	
	//test for deleteByToken
	@Test
	public void deleteTokenTest() {
		RefreshToken token = RefreshToken.builder()
				.createdDate(Instant.now())
				.token("test token")
				.build();
		tokenRepo.save(token);
		tokenRepo.deleteByToken(token.getToken());
		RefreshToken after = tokenRepo.findById(token.getId()).orElse(null);
		
		Assertions.assertThat(after).isNull();
	}
	

}
