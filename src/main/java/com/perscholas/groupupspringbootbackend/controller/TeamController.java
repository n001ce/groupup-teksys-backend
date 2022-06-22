package com.perscholas.groupupspringbootbackend.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.perscholas.groupupspringbootbackend.dto.TeamMemberDto;
import com.perscholas.groupupspringbootbackend.dto.TeamRequest;
import com.perscholas.groupupspringbootbackend.dto.TeamResponse;
import com.perscholas.groupupspringbootbackend.model.User;
import com.perscholas.groupupspringbootbackend.service.TeamService;
import com.perscholas.groupupspringbootbackend.service.UserServiceImp;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/team")
@AllArgsConstructor
public class TeamController {
private final TeamService team;
private final UserServiceImp user;
	
	@PostMapping
	public ResponseEntity<Void> createTeam(@RequestBody TeamRequest teamReq){
		team.save(teamReq);
		return new ResponseEntity<>(CREATED);
	}
	
	@PutMapping("/{teamId}/{username}")
	public ResponseEntity<TeamRequest> addToTeam(@RequestBody TeamRequest teamRequest, @PathVariable String username){
		team.addTeamMate(teamRequest, username);
		return new ResponseEntity<>(CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TeamResponse>> getAllTeams(){
		return status(OK).body(team.getAllTeams());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TeamResponse>getTeam(@PathVariable Long id){
		return status(HttpStatus.OK).body(team.getTeam(id));
	}
	
	@GetMapping("/game/{id}")
	public ResponseEntity<List<TeamResponse>> getTeamByGame(Long gameId){
		return status(OK).body(team.getTeamsByGame(gameId));
	}
	
	@GetMapping("/by-user/{username}")
	public ResponseEntity<List<TeamResponse>> getTeamsByUsername(String username){
		return status(OK).body(team.getTeamsByUsername(username));
	}
	
	@GetMapping("/teammembers/{teamId}")
	public ResponseEntity<List<TeamMemberDto>> getTeamMembers(@PathVariable Long teamId){
		return status(OK).body(team.getTeamMembers(teamId));
	}
	

}

