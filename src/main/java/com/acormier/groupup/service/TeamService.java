package com.acormier.groupup.service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acormier.groupup.dto.TeamMemberDto;
import com.acormier.groupup.dto.TeamRequest;
import com.acormier.groupup.dto.TeamResponse;
import com.acormier.groupup.exception.GroupUpException;
import com.acormier.groupup.exception.TeamNotFoundException;
import com.acormier.groupup.mapper.TeamMapper;
import com.acormier.groupup.model.Game;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.GameRepository;
import com.acormier.groupup.repository.TeamRepository;
import com.acormier.groupup.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class TeamService {
	private final TeamRepository teamRepo;
	private final GameRepository gameRepo;
	private final TeamMapper teamMap;
	private final UserRepository userRepo;
	private final AuthService authService;
	
	//create a new team and save it to the db
	public void save(TeamRequest teamRequest, String username) {
		Game game = gameRepo.findByGameTitle(teamRequest.getGameTitle());
		User user = userRepo.findByUsername(username).orElseThrow();
		Set<User> teamMembers = new HashSet<>();
		teamRepo.save(teamMap.map(teamRequest, game, user, teamMembers));
		log.info("team successfully added");
	}
	
	//add team mate to the selected team
	public void addTeamMate(TeamRequest teamRequest, String username) {
		Team team = teamRepo.findById(teamRequest.getTeamId()).orElseThrow(()-> new TeamNotFoundException("Team not found with id: " + teamRequest.getTeamId()));
		User user = userRepo.findByUsername(username).orElseThrow(null);
		team.getTeamMembers().add(user);
		teamMap.updateTeamFromDto(teamRequest, team);
		teamRepo.save(team);
	}
	
	//get team by the team id
	@Transactional(readOnly=true)
	public TeamResponse getTeam(Long id) {
		Team team = teamRepo.findById(id)
				.orElseThrow(()->new GroupUpException(id.toString()));
		return teamMap.mapToDto(team);
	}
	
	//get list of all teams
	@Transactional(readOnly=true)
	public List<TeamResponse> getAllTeams(){
		return teamRepo.findAll()
				.stream()
				.map(teamMap::mapToDto)
				.collect(toList());
	}
	
	//get a list of all the teams for a specific game
	@Transactional(readOnly=true)
	public List<TeamResponse> getTeamsByGame(Long gameId){
		Game game = gameRepo.findById(gameId)
				.orElseThrow(()-> new GroupUpException("Game not found with id: " + gameId));
		Set<Team> teams = teamRepo.findAllByGame(game);
		return teams.stream().map(teamMap::mapToDto).collect(toList());
	}
	
	//get teams for the selected user
	@Transactional(readOnly=true)
	public List<TeamResponse> getTeamsByUsername(TeamResponse teamResponse, String username){
		User user = userRepo.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException(username));
		return teamRepo.findByTeamLeader(user)
				.stream()
				.map(teamMap::mapToDto)
				.collect(toList());
	}
	
	//get all team members for the game
	@Transactional(readOnly=true)
	public List<TeamMemberDto> getTeamMembers(Long teamId){
		Team team = teamRepo.findById(teamId).orElseThrow(null);
		TeamMemberDto teamMember = new TeamMemberDto();
		List<TeamMemberDto> teamMembers = new ArrayList<>();
		team.getTeamMembers().forEach(member->{
			teamMember.setUsername(member.getUsername());
			teamMember.setUrl("/view-profile/" + member.getUsername());
		});
		return teamMembers;
	}
	
	

	
	
}
