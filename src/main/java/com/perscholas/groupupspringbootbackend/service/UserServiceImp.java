package com.perscholas.groupupspringbootbackend.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;

import com.perscholas.groupupspringbootbackend.dto.TeamResponse;
import com.perscholas.groupupspringbootbackend.exception.TeamNotFoundException;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;
import com.perscholas.groupupspringbootbackend.repository.TeamRepository;
import com.perscholas.groupupspringbootbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
//UserService implements UserDetailsService interface provided by spring
public class UserServiceImp implements UserDetailsService {
	private final UserRepository userRepository;
	private final TeamRepository teamRepo;
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
