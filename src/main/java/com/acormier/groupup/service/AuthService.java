package com.acormier.groupup.service;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acormier.groupup.dto.AuthenticationResponse;
import com.acormier.groupup.dto.LoginRequest;
import com.acormier.groupup.dto.RefreshTokenRequest;
import com.acormier.groupup.dto.RegisterRequest;
import com.acormier.groupup.exception.GroupUpException;
import com.acormier.groupup.exception.UserExistsException;
import com.acormier.groupup.model.NotificationEmail;
import com.acormier.groupup.model.User;
import com.acormier.groupup.model.VerificationToken;
import com.acormier.groupup.repository.UserRepository;
import com.acormier.groupup.repository.VerificationTokenRepository;
import com.acormier.groupup.security.JwtProvider;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AuthService {
	//it is not recommended to use Autowired when using field injection. Spring recommends using field injection whenever possible
	private final PasswordEncoder passwordEncoder;	
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final RefreshTokenService refreshTokenService;
	
	
	//sign up user
	public void signup(RegisterRequest registerRequest) throws UserExistsException{
		//create new user from register request
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());//Java 8 class to get current time
		user.setEnabled(false);
		//save user
		userRepository.save(user);
		//create new JWT token
		String token = generateVerificationToken(user);
		//send token to users email address
		mailService.sendMail(new NotificationEmail("Please Activate your Account", 
				user.getEmail(), "Thank you for signing up to Group-Up," +
				"please click on the below url to activate your account: " +
				"http://localhost:8080/api/auth/accountVerification/" + token));
		log.info("{} succesfully signed up", user.getUsername());
		
	}
	
	//get the current user signed in by their jwt token
	@Transactional(readOnly = true)
	public User getCurrentUser() {
		Jwt principal = (Jwt) SecurityContextHolder.
				getContext().getAuthentication().getPrincipal();
		return userRepository.findByUsername(principal.getSubject())
				.orElseThrow(()-> new UsernameNotFoundException("User name not found " + principal.getSubject()));
	}
	
	//find user in db and authorize them when the user clicks the email sent after registration
	public void fetchUserAndEnable(VerificationToken verificationToken) {
		String username = verificationToken.getUser().getUsername();
		User user = userRepository.findByUsername(username).orElseThrow(()->
			new GroupUpException("User not found with name " + username));
		user.setEnabled(true);
		userRepository.save(user);
	}
	
	//generate the verification token for when the user signs up
	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();//generate unique and random 128 bit value for verification token.
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);//save token in DB incase user does not click the link after signingup
		return token;
	}
	
	//verify the users account with the verification token
	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		fetchUserAndEnable(verificationToken.orElseThrow(()-> new GroupUpException("Invalid Token")));
	}
	
	//login method
	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
				loginRequest.getPassword()
				));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		String token = jwtProvider.generateToken(authenticate);
		return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenService.generateRefreshToken().getToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(loginRequest.getUsername())
				.build();
	}
	
	//create refresh token and set the expiration date
	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
		refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
		String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
		return AuthenticationResponse.builder()
				.authenticationToken(token)
				.refreshToken(refreshTokenRequest.getRefreshToken())
				.expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
				.username(refreshTokenRequest.getUsername())
				.build();
	}
	
	//check to see if the user is logged in
	public boolean isLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
	
	

}
