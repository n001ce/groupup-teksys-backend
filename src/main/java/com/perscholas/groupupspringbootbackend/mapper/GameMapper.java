package com.perscholas.groupupspringbootbackend.mapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.perscholas.groupupspringbootbackend.dto.GamesDto;
import com.perscholas.groupupspringbootbackend.model.Comment;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;
import com.perscholas.groupupspringbootbackend.repository.CommentRepository;


@Mapper(componentModel="spring")
public interface GameMapper{
	
	@Mapping(target = "numberOfTeams", expression = "java(mapTeams(game.getTeams()))")
	@Mapping(target = "numberOfComments", expression = "java(mapComments(game.getComments()))")
	GamesDto mapGameToDto(Game game);

	
	default Integer mapTeams(List<Team> numberOfTeams) {
		return numberOfTeams.size();
	}
	
	default Integer mapComments(List<Comment> numberOfComments) {
		return numberOfComments.size();
	}
	
	
	@InheritInverseConfiguration
	@Mapping(target="teams", ignore=true)
	@Mapping(target="comments", ignore=true)
	Game mapDtoToGame(GamesDto gamesDto);
}