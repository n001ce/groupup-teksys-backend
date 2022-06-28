package com.acormier.groupup.mapper;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.acormier.groupup.dto.TeamRequest;
import com.acormier.groupup.dto.TeamResponse;
import com.acormier.groupup.model.Game;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.CommentRepository;

//Mapper takes the inputs passed from the frontend and creates the DTO from the backend
//target is the target of the dto variable
//source is the source of the Objects passed in the methods
//expression are custome expressions that call the portion of the entity of the backend that is requested by the target

@Mapper(componentModel="spring")
public abstract class TeamMapper {
	
	@Autowired
	private CommentRepository commentRepo;
	
	
	
	@Mapping(target="createdDate", expression="java(java.time.Instant.now())")
	@Mapping(target="console", source="teamRequest.console")
	@Mapping(target="teamSize", source="teamRequest.teamSize")
	@Mapping(target="game", source="game")
	@Mapping(target="teamLeader", source="teamLeader")
	@Mapping(target="teamMembers", source="teamMembers")
	public abstract Team map(TeamRequest teamRequest, Game game, User teamLeader, Set<User> teamMembers);
	
	@Mapping(target="teamId", source="teamId")
	@Mapping(target="gameTitle", source="game.gameTitle")
	@Mapping(target="teamLeader", source="teamLeader.username")
	@Mapping(target="background_image", source="game.background_image")
	@Mapping(target="teamCount", expression="java(team.getTeamMembers().size())")
	@Mapping(target="commentCount", expression="java(commentCount(team))")
	@Mapping(target="teamMembers", expression = "java(team.getTeamMembers())")
	@Mapping(target="createdDate", source = "team.createdDate")
	public abstract TeamResponse mapToDto(Team team);
	
	public abstract void updateTeamFromDto(TeamRequest request, @MappingTarget Team team);
	
	Integer commentCount(Team team) {
		return commentRepo.findByTeam(team).size();
	}
	
	

}
