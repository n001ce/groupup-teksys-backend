package com.acormier.groupup.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acormier.groupup.dto.AuthenticationResponse;
import com.acormier.groupup.dto.LoginRequest;
import com.acormier.groupup.dto.RefreshTokenRequest;
import com.acormier.groupup.dto.RegisterRequest;
import com.acormier.groupup.service.AuthService;
import com.acormier.groupup.service.RefreshTokenService;

import static org.springframework.http.HttpStatus.OK;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
	
	private final AuthService authService;
	private final RefreshTokenService refreshTokenService;
	
	//Post mapping for user registration
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		log.info("new user requested to join {}", registerRequest.getUsername());
		authService.signup(registerRequest);
		return new ResponseEntity<>("User Registration Successful", OK);
	}
	
	//get mapping for user token to verify accounts
	@GetMapping("/accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token){
		log.info("account verified");
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successfully", OK);
	}

	//post mapping for login requests
	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		log.info("{} logged in", loginRequest.getUsername());
		return authService.login(loginRequest);
	}
	
	//create refresh tokens for users when they login to gain access to the website
	@PostMapping("/refresh/token")
	public AuthenticationResponse refreshToken(@Valid @RequestBody RefreshTokenRequest rtr) {
		return authService.refreshToken(rtr);
	}
	
	
	//delete user refresh token to sign them out of the website
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
		log.info("user logged out");
		refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
		return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully");
	}
	
	

}
