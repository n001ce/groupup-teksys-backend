package com.perscholas.groupupspringbootbackend.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;
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

import com.perscholas.groupupspringbootbackend.dto.GamesDto;
import com.perscholas.groupupspringbootbackend.service.RawgService;
import com.squareup.okhttp.Response;

@RestController
@RequestMapping("/api/search")
public class RawgController {
	
	@Autowired
	RawgService rawgService;

	@GetMapping("/by-title/{gameTitle}")
	public String getGameByTitle(@PathVariable String gameTitle) throws IOException{
		return rawgService.getGame(gameTitle);
	}
	
	@GetMapping("/by-id/{gameId}")
	public String getGameById(@PathVariable int gameId) throws IOException{
		return rawgService.getGameById(gameId);
	}
	
}
