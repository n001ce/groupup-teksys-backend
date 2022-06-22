package com.perscholas.groupupspringbootbackend.service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perscholas.groupupspringbootbackend.dto.TeamMemberDto;
import com.perscholas.groupupspringbootbackend.dto.TeamRequest;
import com.perscholas.groupupspringbootbackend.dto.TeamResponse;
import com.perscholas.groupupspringbootbackend.exception.GroupUpException;
import com.perscholas.groupupspringbootbackend.exception.TeamNotFoundException;
import com.perscholas.groupupspringbootbackend.mapper.TeamMapper;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;
import com.perscholas.groupupspringbootbackend.repository.GameRepository;
import com.perscholas.groupupspringbootbackend.repository.TeamRepository;
import com.perscholas.groupupspringbootbackend.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
	
	
	public void save(TeamRequest teamRequest) {
		Game game = gameRepo.findByGameTitle(teamRequest.getGameTitle());
		List<User> teamMembers = new ArrayList<>();
		teamRepo.save(teamMap.map(teamRequest, game, authService.getCurrentUser(), teamMembers));
		log.info("team successfully added");
	}

	public void addTeamMate(TeamRequest teamRequest, String username) {
		Team team = teamRepo.findById(teamRequest.getTeamId()).orElseThrow(()-> new TeamNotFoundException("Team not found with id: " + teamRequest.getTeamId()));
		User user = userRepo.findByUsername(username).orElseThrow(null);
		team.getTeamMembers().add(user);
		teamMap.updateTeamFromDto(teamRequest, team);
		teamRepo.save(team);
	}
	
	@Transactional(readOnly=true)
	public TeamResponse getTeam(Long id) {
		Team team = teamRepo.findById(id)
				.orElseThrow(()->new GroupUpException(id.toString()));
		return teamMap.mapToDto(team);
	}
	
	@Transactional(readOnly=true)
	public List<TeamResponse> getAllTeams(){
		return teamRepo.findAll()
				.stream()
				.map(teamMap::mapToDto)
				.collect(toList());
	}
	
	@Transactional(readOnly=true)
	public List<TeamResponse> getTeamsByGame(Long gameId){
		Game game = gameRepo.findById(gameId)
				.orElseThrow(()-> new GroupUpException("Game not found with id: " + gameId));
		List<Team> teams = teamRepo.findAllByGame(game);
		return teams.stream().map(teamMap::mapToDto).collect(toList());
	}
	
	@Transactional(readOnly=true)
	public List<TeamResponse> getTeamsByUsername(String username){
		User user = userRepo.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException(username));
		return teamRepo.findByTeamLeader(user.getUserId())
				.stream()
				.map(teamMap::mapToDto)
				.collect(toList());
	}
	
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
