package com.perscholas.groupupspringbootbackend.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.perscholas.groupupspringbootbackend.dto.CommentsDto;
import com.perscholas.groupupspringbootbackend.model.Comment;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "text", source="commentsDto.text")
	@Mapping(target="createdDate", expression="java(java.time.Instant.now())")
	@Mapping(target="team", source="team")
	@Mapping(target="user", source="user")
	Comment mapTeamToComment(CommentsDto commentsDto, Team team, User user);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "text", source="commentsDto.text")
	@Mapping(target="createdDate", expression="java(java.time.Instant.now())")
	@Mapping(target="game", source="game")
	@Mapping(target="user", source="user")
	Comment mapGameToComment(CommentsDto commentsDto, Optional<Game> game, User user);
	
	@Mapping(target="teamId", expression="java(comment.getTeam().getTeamId())")
	@Mapping(target="gameId", expression="java(comment.getGame().getGameId())")
	@Mapping(target="userName", expression="java(comment.getUser().getUsername())")
	CommentsDto mapToDto(Comment comment);

	Game map(Optional<Game> value);
}
