package com.acormier.groupup.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acormier.groupup.exception.GroupUpException;
import com.acormier.groupup.model.RefreshToken;
import com.acormier.groupup.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {
	private final RefreshTokenRepository refreshTokenRepository;
	
	//generate a new refresh token for the user
	public RefreshToken generateRefreshToken() {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setCreatedDate(Instant.now());
		return refreshTokenRepository.save(refreshToken);
	}
	
	//make sure the refresh token is in the db
	void validateRefreshToken(String token) {
		refreshTokenRepository.findByToken(token)
			.orElseThrow(()-> new GroupUpException("Invalid Refresh Token"));
	}
	
	//delete the refresh token to log the user out
	public void deleteRefreshToken(String token) {
		refreshTokenRepository.deleteByToken(token);
	}
}
