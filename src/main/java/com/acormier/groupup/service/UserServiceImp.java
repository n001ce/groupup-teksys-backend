package com.acormier.groupup.service;

import java.util.Collection;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.UserRepository;

import static java.util.Collections.singletonList;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
//UserService implements UserDetailsService interface provided by spring
public class UserServiceImp implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		//UserRepository acquires user based on username
		Optional<User> userOptional = userRepository.findByUsername(username);
		//if user doesn't exist throw UsernameNotFoundException (provided by spring)
		User user = userOptional
				.orElseThrow(()->
						new UsernameNotFoundException("No user " + 
								"Found with username: " + username));
		//if user does exist create new user utilizing UserDetailsService interface
		//map user details to user class
		//provide authority type of USER
		return new org.springframework.security
        .core.userdetails.User(user.getUsername(), user.getPassword(),
        user.isEnabled(), true, true,
        true, getAuthorities("USER"));
	}

	 private Collection<? extends GrantedAuthority> getAuthorities(String role) {
	        return singletonList(new SimpleGrantedAuthority(role));
	    }
	 

	 
}
