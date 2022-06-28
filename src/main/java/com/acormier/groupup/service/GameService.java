package com.acormier.groupup.service;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acormier.groupup.dto.GamesDto;
import com.acormier.groupup.exception.GroupUpException;
import com.acormier.groupup.mapper.GameMapper;
import com.acormier.groupup.model.Game;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.GameRepository;
import com.acormier.groupup.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {
	
    private final GameRepository gameRepo;
    private final GameMapper gameMap;
    private final UserRepository userRepo;
    
    // save game to the db
    @Transactional
    public GamesDto save(GamesDto gameDto) {
    	Game game = gameRepo.save(gameMap.mapDtoToGame(gameDto));
    	gameDto.setGameId(game.getGameId());
    	return gameDto;
    }
    
    //save the game to the users account
    @Transactional
    public GamesDto saveToUser(GamesDto gameDto, String username) {
    	User user = userRepo.findByUsername(username).orElseThrow();
    	Game game = gameRepo.findByGameTitle(gameDto.getGameTitle());
    	user.getCollectedGames().add(game);
    	return gameDto;
    }
    
    //list all of the games in the db
    @Transactional(readOnly=true)
    public List<GamesDto> getAll(){
    	return gameRepo.findAll()
    			.stream()
    			.map(gameMap::mapGameToDto)
    			.collect(toList());
    }
    
    //get all games for the user
    @Transactional(readOnly=true)
    public List<GamesDto> getAllGamesForUser(String username){
	 User user = userRepo.findByUsername(username).orElseThrow();
	 	List<Game> games = new ArrayList<>();
	 	for(Game g : user.getCollectedGames())
	 		games.add(g);
    	return games.stream().map(gameMap::mapGameToDto).collect(toList());
    			
    }
    
    
    //get game by the game id
    public GamesDto getGame(Long gameId) {
    	Game game = gameRepo.findById(gameId)
    			.orElseThrow(()-> new GroupUpException("No game found with id " + gameId));
    	return gameMap.mapGameToDto(game);
    }
    
    
    //get the game by the game title
    public GamesDto getGameByTitle(String gameTitle) {
    	Game game = gameRepo.findByGameTitle(gameTitle);
    	return gameMap.mapGameToDto(game);
    }
    
    
	
}