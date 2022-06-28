package com.acormier.groupup.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acormier.groupup.service.RawgService;

@RestController
@RequestMapping("/api/search")
public class RawgController {
	
	@Autowired
	RawgService rawgService;
	
	//search the Rawg API for game by the inserted gameTitle
	@GetMapping("/by-title/{gameTitle}")
	public String getGameByTitle(@PathVariable String gameTitle) throws IOException{
		return rawgService.getGame(gameTitle);
	}
	
	//get the Game by the game Id when the user saves the game to the db.
	@GetMapping("/by-id/{gameId}")
	public String getGameById(@PathVariable int gameId) throws IOException{
		return rawgService.getGameById(gameId);
	}
	
}
