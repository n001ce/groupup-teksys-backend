package com.perscholas.groupupspringbootbackend.controller;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.perscholas.groupupspringbootbackend.dto.GamesDto;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.User;
import com.perscholas.groupupspringbootbackend.service.GameService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/games")
@AllArgsConstructor
@Slf4j
public class GameController {
	

	private final GameService gameService;

	@PostMapping
	public ResponseEntity<GamesDto> createGame(@RequestBody GamesDto gameDto){
		return ResponseEntity.status(CREATED).body(gameService.save(gameDto));
	}
	
	@GetMapping
	public ResponseEntity<List<GamesDto>> getAllGames(){
		return ResponseEntity.status(OK).body(gameService.getAll());
	}
	
	@GetMapping("/{gameId}")
	public ResponseEntity<GamesDto> getGame(@PathVariable Long gameId){
		return ResponseEntity
				.status(OK)
				.body(gameService.getGame(gameId));
	}
	
	@PostMapping("/followGame")
	public ResponseEntity<User> followGame(@RequestBody GamesDto gameDto){
		return ResponseEntity.status(CREATED).body(gameService.addToUser(gameDto));
	}
	
    
}
