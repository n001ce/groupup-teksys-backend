package com.perscholas.groupupspringbootbackend.service;

import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perscholas.groupupspringbootbackend.dto.GamesDto;
import com.perscholas.groupupspringbootbackend.exception.GameNotFoundException;
import com.perscholas.groupupspringbootbackend.exception.GroupUpException;
import com.perscholas.groupupspringbootbackend.exception.TeamNotFoundException;
import com.perscholas.groupupspringbootbackend.mapper.GameMapper;
import com.perscholas.groupupspringbootbackend.model.Comment;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;
import com.perscholas.groupupspringbootbackend.repository.GameRepository;
import com.perscholas.groupupspringbootbackend.repository.TeamRepository;
import com.perscholas.groupupspringbootbackend.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {
	
    private final GameRepository gameRepo;
    private final GameMapper gameMap;
    private final AuthService authService;
    private final UserRepository userRepo;
    
    @Transactional
    public GamesDto save(GamesDto gameDto) {
    	Game game = gameRepo.save(gameMap.mapDtoToGame(gameDto));
    	gameDto.setGameId(game.getGameId());
    	return gameDto;
    }
    
    
    @Transactional(readOnly=true)
    public List<GamesDto> getAll(){
    	return gameRepo.findAll()
    			.stream()
    			.map(gameMap::mapGameToDto)
    			.collect(toList());
    }
    
    public GamesDto getGame(Long gameId) {
    	Game game = gameRepo.findById(gameId)
    			.orElseThrow(()-> new GroupUpException("No game found with id " + gameId));
    	return gameMap.mapGameToDto(game);
    }
    
    @Transactional
    public User addToUser(GamesDto gameDto) {
    	Game game = gameRepo.findByGameTitle(gameDto.getGameTitle());
    	User user = authService.getCurrentUser();
    	user.getCollectedGames().add(game);
    	return userRepo.save(user);
    }
    
    public GamesDto getGameByTitle(String gameTitle) {
    	Game game = gameRepo.findByGameTitle(gameTitle);
    	return gameMap.mapGameToDto(game);
    }
	
}