package com.acormier.groupup.servicesTests;

import java.time.Instant;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.acormier.groupup.model.RefreshToken;
import com.acormier.groupup.repository.RefreshTokenRepository;
import com.acormier.groupup.service.RefreshTokenService;


@ExtendWith(MockitoExtension.class)
public class RefreshTokenServiceTests {
	
	@Mock
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private RefreshToken refreshToken;
	
	@Test
	@DisplayName("Should generate a new refresh token")
	public void generateRefreshTokenTest() {
		RefreshTokenService refreshTokenService = new RefreshTokenService(refreshTokenRepository);
		Assertions.assertThat(refreshTokenService.generateRefreshToken()).isEqualTo(refreshToken);
	}
}
