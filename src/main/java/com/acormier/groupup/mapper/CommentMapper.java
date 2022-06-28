package com.acormier.groupup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.acormier.groupup.dto.CommentsDto;
import com.acormier.groupup.model.Comment;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;

//Mapper takes the inputs passed from the frontend and creates the DTO from the backend
//target is the target of the dto variable
//source is the source of the Objects passed in the methods
//expression are custome expressions that call the portion of the entity of the backend that is requested by the target

@Mapper(componentModel = "spring")
public interface CommentMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "text", source="commentsDto.text")
	@Mapping(target="createdDate", expression="java(java.time.Instant.now())")
	@Mapping(target="team", source="team")
	@Mapping(target="user", source="user")
	Comment mapTeamToComment(CommentsDto commentsDto, Team team, User user);
	
	@Mapping(target="teamId", expression="java(comment.getTeam().getTeamId())")
	@Mapping(target="userName", expression="java(comment.getUser().getUsername())")
	@Mapping(target="teamName", expression="java(comment.getTeam().getTeamName())")
	CommentsDto mapToDto(Comment comment);
	
	void updateComment(CommentsDto commentDto, @MappingTarget Comment comment);
}
