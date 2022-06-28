package com.acormier.groupup.controller;


import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acormier.groupup.dto.GamesDto;
import com.acormier.groupup.service.GameService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/games")
@AllArgsConstructor
@Slf4j
public class GameController {
	

	private final GameService gameService;

	//post mapping to save game to the database from the rawg api pull
	@PostMapping
	public ResponseEntity<GamesDto> createGame(@RequestBody GamesDto gameDto){
		log.info("game creation requested");
		return ResponseEntity.status(CREATED).body(gameService.save(gameDto));
	}
	
	//get mapping to show list of all games in the db
	@GetMapping
	public ResponseEntity<List<GamesDto>> getAllGames(){
		log.info("get all games requested");
		return ResponseEntity.status(OK).body(gameService.getAll());
	}
	
	//get mapping to display game information
	@GetMapping("/{gameId}")
	public ResponseEntity<GamesDto> getGame(@PathVariable Long gameId){
		log.info("game with id: {} was requested", gameId);
		return ResponseEntity
				.status(OK)
				.body(gameService.getGame(gameId));
	}
	
	
	//follow the game for the user
	@PutMapping("/{username}")
	public ResponseEntity<GamesDto> followGame(@RequestBody GamesDto gameDto, @PathVariable String username){
		log.info("{} was requested to be followed", gameDto.getGameTitle());
		gameService.saveToUser(gameDto, username);
		return new ResponseEntity<>(CREATED);
	}
	
	//pull all of user collected games from the user account
	@GetMapping("/by-user/{username}")
	public ResponseEntity<List<GamesDto>> getAllGamesForUser(@PathVariable String username){
		log.info("all games requested for: {}", username);
		return ResponseEntity.status(OK).body(gameService.getAllGamesForUser(username));
	}
	
	
    
}
