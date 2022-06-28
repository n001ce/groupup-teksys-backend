package com.acormier.groupup.repositoryTests;

import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.acormier.groupup.model.Game;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.GameRepository;
import com.acormier.groupup.repository.TeamRepository;
import com.acormier.groupup.repository.UserRepository;

/*
 * Set<Team> findAllByGame(Game game);
	Set<Team> findByTeamLeader(User user);
	Team findByTeamName(String teamName);
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamRepositoryTests {
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void findAllByGameTest() {
		Game game = gameRepo.findById((long) 3).orElseThrow();
		Set<Team> teams = teamRepo.findAllByGame(game);
		
		Assertions.assertThat(teams.size()).isGreaterThan(0);
	}
	
	@Test
	public void findByTeamLeader() {
		User user = userRepo.findById((long) 3).orElseThrow();
		Set<Team> teams = teamRepo.findByTeamLeader(user);
		
		Assertions.assertThat(teams.size()).isGreaterThan(0);
	}
	
	

}
