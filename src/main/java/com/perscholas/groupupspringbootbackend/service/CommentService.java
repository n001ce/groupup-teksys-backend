package com.perscholas.groupupspringbootbackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.perscholas.groupupspringbootbackend.dto.CommentsDto;
import com.perscholas.groupupspringbootbackend.exception.GameNotFoundException;
import com.perscholas.groupupspringbootbackend.exception.PostNotFoundException;
import com.perscholas.groupupspringbootbackend.exception.TeamNotFoundException;
import com.perscholas.groupupspringbootbackend.mapper.CommentMapper;
import com.perscholas.groupupspringbootbackend.model.Comment;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.NotificationEmail;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;
import com.perscholas.groupupspringbootbackend.repository.CommentRepository;
import com.perscholas.groupupspringbootbackend.repository.GameRepository;
import com.perscholas.groupupspringbootbackend.repository.TeamRepository;
import com.perscholas.groupupspringbootbackend.repository.UserRepository;
import static java.util.stream.Collectors.toList;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
	private static final String POST_URL="";
	private final TeamRepository teamRepo;
	private final UserRepository userRepo;
	private final AuthService authServ;
	private final CommentMapper commentMap;
	private final CommentRepository commentRepo;
	private final MailContentBuilder mailContBuild;
	private final MailService mailServ;
	private final GameRepository gameRepo;
	
	
	public void save(CommentsDto commentsDto) {
		if(commentsDto.getGameId() == null) {
	        Team team = teamRepo.findById(commentsDto.getTeamId())
	                .orElseThrow(() -> new PostNotFoundException(commentsDto.getTeamId().toString()));
	        Comment comment = commentMap.mapTeamToComment(commentsDto, team, authServ.getCurrentUser());
	        commentRepo.save(comment);
	        String message = mailContBuild.build(team.getTeamLeader().getUsername() + " posted a comment on your post." + POST_URL);
	        sendCommentNotification(message, team.getTeamLeader());
		}else {
			Optional<Game> game = gameRepo.findById(commentsDto.getGameId());
			Comment comment = commentMap.mapGameToComment(commentsDto, game, authServ.getCurrentUser());
			commentRepo.save(comment);
		}

    }
	
	private void sendCommentNotification(String message, User user) {
		mailServ.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getUsername(), message));
	}
	
	public List<CommentsDto> getAllCommentsForTeam(Long teamId){
		Team team = teamRepo.findById(teamId).orElseThrow(()-> new TeamNotFoundException(teamId.toString()));
		return commentRepo.findByTeam(team)
				.stream()
				.map(commentMap::mapToDto).collect(toList());
	}
	
	public List<CommentsDto> getAllCommentsForGame(Long gameId){
		Optional<Game> game = gameRepo.findById(gameId);
		return commentRepo.findByGame(game)
				.stream()
				.map(commentMap::mapToDto).collect(toList());
	}
	
	public List<CommentsDto> getAllCommentsForUser(String userName){
		User user = userRepo.findByUsername(userName)
				.orElseThrow(()-> new UsernameNotFoundException(userName));
		return commentRepo.findAllByUser(user)
				.stream()
				.map(commentMap::mapToDto)
				.collect(toList());
	}
	
}
