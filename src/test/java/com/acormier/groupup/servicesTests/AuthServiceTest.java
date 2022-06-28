package com.acormier.groupup.servicesTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import com.acormier.groupup.dto.RegisterRequest;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.UserRepository;
import com.acormier.groupup.repository.VerificationTokenRepository;
import com.acormier.groupup.security.JwtProvider;
import com.acormier.groupup.service.AuthService;
import com.acormier.groupup.service.MailService;
import com.acormier.groupup.service.RefreshTokenService;

@SpringBootTest
@Rollback
public class AuthServiceTest {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	
	@Test
	@DisplayName("Should pass if user signup is successfull and fail if not")
	public void getUserTest() {
		AuthService authService = new AuthService(passwordEncoder, userRepository, verificationTokenRepository, mailService, authenticationManager, jwtProvider, refreshTokenService);
		User user = User.builder()
				.email("test@test.com")
				.username("test")
				.password("test123")
				.build();
		RegisterRequest register = new RegisterRequest(user.getEmail(), user.getUsername(), user.getPassword());
		authService.signup(register);
		User registered = userRepository.findByUsername(user.getUsername()).orElse(null);
		
		Assertions.assertEquals(registered.getUsername(), user.getUsername());
		
	}
}
