package com.acormier.groupup.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acormier.groupup.dto.TeamMemberDto;
import com.acormier.groupup.dto.TeamRequest;
import com.acormier.groupup.dto.TeamResponse;
import com.acormier.groupup.service.TeamService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/team")
@AllArgsConstructor
public class TeamController {
private final TeamService team;
	
	//create new team from the user 
	@PostMapping("/{username}")
	public ResponseEntity<Void> createTeam(@RequestBody TeamRequest teamReq, @PathVariable String username){
		team.save(teamReq, username);
		return new ResponseEntity<>(CREATED);
	}
	
	//add teammate to team
	@PutMapping("/{teamId}/{username}")
	public ResponseEntity<TeamRequest> addToTeam(@RequestBody TeamRequest teamRequest, @PathVariable String username){
		team.addTeamMate(teamRequest, username);
		return new ResponseEntity<>(CREATED);
	}
	
	//get a list of all of the teams from the db
	@GetMapping
	public ResponseEntity<List<TeamResponse>> getAllTeams(){
		return status(OK).body(team.getAllTeams());
	}
	
	//get team by the teamId
	@GetMapping("/{id}")
	public ResponseEntity<TeamResponse>getTeam(@PathVariable Long id){
		return status(HttpStatus.OK).body(team.getTeam(id));
	}
	
	//get list of teams from the gameId
	@GetMapping("/game/{gameId}")
	public ResponseEntity<List<TeamResponse>> getTeamByGame(@PathVariable Long gameId){
		return status(OK).body(team.getTeamsByGame(gameId));
	}
	
	//get list of games for the user
	@GetMapping("/by-user/{username}")
	public ResponseEntity<List<TeamResponse>> getTeamsByUsername(TeamResponse teamResponse, @PathVariable String username){
		return status(OK).body(team.getTeamsByUsername(teamResponse, username));
	}
	
	//get all teammembers from the team
	@GetMapping("/members/{teamId}")
	public ResponseEntity<List<TeamMemberDto>> getTeamMembers(@PathVariable Long teamId){
		return status(OK).body(team.getTeamMembers(teamId));
	}
	
	

}

