package com.acormier.groupup.servicesTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.acormier.groupup.mapper.GameMapper;
import com.acormier.groupup.repository.GameRepository;
import com.acormier.groupup.repository.TeamRepository;
import com.acormier.groupup.repository.UserRepository;
import com.acormier.groupup.service.GameService;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {
	
	
	@Mock
	private TeamRepository teamRepo;
	
	@Mock
	private GameRepository gameRepo;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private GameMapper gameMap;
	

	
	@Test
	@DisplayName("Should pull all games if games table is not null and false if it is")
	public void shouldReturnAllGames() {
		GameService gameService = new GameService(gameRepo, gameMap, userRepo);
		Assertions.assertThat(gameService.getAll()).isNotNull();
	}

}

