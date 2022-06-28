package com.acormier.groupup.servicesTests;

import java.time.Instant;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.acormier.groupup.dto.TeamResponse;
import com.acormier.groupup.mapper.TeamMapper;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.repository.GameRepository;
import com.acormier.groupup.repository.TeamRepository;
import com.acormier.groupup.repository.UserRepository;
import com.acormier.groupup.service.AuthService;
import com.acormier.groupup.service.TeamService;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTests {
	
	@Mock
	private TeamRepository teamRepo;
	
	@Mock
	private GameRepository gameRepo;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private TeamMapper teamMap;
	
	@Mock 
	private AuthService authService;
	
	@Captor
	private ArgumentCaptor<Team> teamArgumentCaptor;
	
	private TeamService teamService;
	
	@Test
	@BeforeEach
	public void setup() {
		teamService = new TeamService(teamRepo, gameRepo, teamMap, userRepo, authService);
	}
	
	@Test
	@DisplayName("Should retrieve teams by id")
	public void shouldFindTeamById() {
		Team team = Team.builder()
				.teamId(123L)
				.teamName("testTeam")
				.url("test/test.com")
				.teamSize(0)
				.teamLeader(null)
				.game(null)
				.createdDate(Instant.now())
				.teamMembers(null)
				.build();
		TeamResponse expectedTeamResponse = new TeamResponse(123L, "testTeam", "test/test.com", null, null, 0, null, null, null, null, null, null);
		
		Mockito.when(teamRepo.findById(123L)).thenReturn(Optional.of(team));
		Mockito.when(teamMap.mapToDto(Mockito.any(Team.class))).thenReturn(expectedTeamResponse);
		
		TeamResponse actualTeamResponse = teamService.getTeam(123L);
		
		Assertions.assertThat(actualTeamResponse.getTeamId()).isEqualTo(expectedTeamResponse.getTeamId());
		
			
	}

}
