package com.perscholas.groupupspringbootbackend.mapper;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import com.perscholas.groupupspringbootbackend.dto.TeamRequest;
import com.perscholas.groupupspringbootbackend.dto.TeamResponse;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;
import com.perscholas.groupupspringbootbackend.repository.CommentRepository;


@Mapper(componentModel="spring")
public abstract class TeamMapper {
	
	@Autowired
	private CommentRepository commentRepo;
	
	
	
	@Mapping(target="createdDate", expression="java(java.time.Instant.now())")
	@Mapping(target="console", source="teamRequest.console")
	@Mapping(target="game", source="game")
	@Mapping(target="teamLeader", source="teamLeader")
	@Mapping(target="teamMembers", source="teamMembers")
	public abstract Team map(TeamRequest teamRequest, Game game, User teamLeader, List<User> teamMembers);
	
	@Mapping(target="teamId", source="teamId")
	@Mapping(target="gameTitle", source="game.gameTitle")
	@Mapping(target="teamLeader", source="teamLeader.username")
	@Mapping(target="teamCount", expression="java(team.getTeamMembers().size())")
	@Mapping(target="commentCount", expression="java(commentCount(team))")
	@Mapping(target="teamMembers", expression = "java(team.getTeamMembers())")
	public abstract TeamResponse mapToDto(Team team);
	
	public abstract void updateTeamFromDto(TeamRequest request, @MappingTarget Team team);
	
	Integer commentCount(Team team) {
		return commentRepo.findByTeam(team).size();
	}
	
	

}
