package com.acormier.groupup.mapper;

import java.util.List;
import java.util.Set;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.acormier.groupup.dto.GamesDto;
import com.acormier.groupup.model.Game;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;

//Mapper takes the inputs passed from the frontend and creates the DTO from the backend
//target is the target of the dto variable
//source is the source of the Objects passed in the methods
//expression are custome expressions that call the portion of the entity of the backend that is requested by the target

@Mapper(componentModel="spring")
public interface GameMapper{
	
	@Mapping(target = "numberOfTeams", expression = "java(mapTeams(game.getTeams()))")
	@Mapping(target="numberOfUsers", expression = "java(timesCollected(game.getUsersCollected()))")
	GamesDto mapGameToDto(Game game);
	

	
	default Integer mapTeams(List<Team> numberOfTeams) {
		return numberOfTeams.size();
	}
	
	default Integer timesCollected(Set<User>timesCollected) {
		return timesCollected.size();
	}
	
	
	@InheritInverseConfiguration
	@Mapping(target="teams", ignore=true)
	@Mapping(target="usersCollected", ignore=true)
	Game mapDtoToGame(GamesDto gamesDto);
}