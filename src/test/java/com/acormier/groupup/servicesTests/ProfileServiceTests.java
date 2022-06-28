package com.acormier.groupup.servicesTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.acormier.groupup.mapper.GameMapper;
import com.acormier.groupup.mapper.ProfileMapper;
import com.acormier.groupup.repository.GameRepository;
import com.acormier.groupup.repository.TeamRepository;
import com.acormier.groupup.repository.UserRepository;
import com.acormier.groupup.service.GameService;
import com.acormier.groupup.service.ProfileService;

@ExtendWith(MockitoExtension.class)
public class ProfileServiceTests {
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private ProfileMapper profileMap;

	
	@Test
	@DisplayName("Should pass if users are signed up and fail if no users")
	public void shouldReturnAllProfiles() {
		ProfileService profileService = new ProfileService(userRepo, profileMap);
		Assertions.assertThat(profileService.getAll()).isNotNull();
	}

}

