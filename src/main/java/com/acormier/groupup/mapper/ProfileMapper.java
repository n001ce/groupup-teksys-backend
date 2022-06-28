package com.acormier.groupup.mapper;
import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.acormier.groupup.dto.CommentsDto;
import com.acormier.groupup.dto.TeamResponse;
import com.acormier.groupup.dto.UserProfileDto;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.CommentRepository;
import com.acormier.groupup.repository.TeamRepository;

//Mapper takes the inputs passed from the frontend and creates the DTO from the backend
//target is the target of the dto variable
//source is the source of the Objects passed in the methods
//expression are custome expressions that call the portion of the entity of the backend that is requested by the target

@Mapper(componentModel="spring")
public abstract class ProfileMapper {
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private TeamMapper teamMap;
	
	@Autowired
	private CommentMapper commentMap;
	
	@Mapping(target="userId", source="user.userId")
	@Mapping(target="username", source="user.username")
	@Mapping(target="joinedDate", source="user.created")
	@Mapping(target="teams", expression="java(leadTeams(user))")
	@Mapping(target="joinedTeams", expression="java(joinedTeams(user))")
	@Mapping(target="games", expression="java(user.getCollectedGames())")
	@Mapping(target="comments", expression="java(userComments(user))")
	public abstract UserProfileDto map(User user);
	
	
	Set<TeamResponse> joinedTeams(User user){
		Set<TeamResponse> joinedTeams = new HashSet<>();
		List<TeamResponse> allTeams = teamRepo.findAll()
				.stream()
				.map(teamMap::mapToDto)
				.collect(toList());
		for(TeamResponse t: allTeams) {
			if(t.getTeamMembers().contains(user)) joinedTeams.add(t);
		}
		return joinedTeams;
	}
	
	Set<TeamResponse> leadTeams(User user){
		Set<TeamResponse> teams = new HashSet<>();
		List<TeamResponse> allTeams = teamRepo.findAll()
				.stream()
				.map(teamMap::mapToDto)
				.collect(toList());
		for(TeamResponse t: allTeams) {
			if(t.getTeamLeader() == user.getUsername()) teams.add(t);
		}
		return teams;
	}
	
	Set<CommentsDto> userComments(User user){
		Set<CommentsDto> userComments = new HashSet<>();
		List<CommentsDto> userC = commentRepo.findAllByUser(user)
				.stream()
				.map(commentMap::mapToDto)
				.collect(toList());
		for(CommentsDto c : userC) {
			if(c.getUserName() == user.getUsername()) userComments.add(c);
		}
		return userComments;
	}

	
}