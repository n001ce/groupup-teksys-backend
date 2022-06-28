package com.acormier.groupup.service;

import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acormier.groupup.dto.CommentsDto;
import com.acormier.groupup.exception.PostNotFoundException;
import com.acormier.groupup.exception.TeamNotFoundException;
import com.acormier.groupup.mapper.CommentMapper;
import com.acormier.groupup.model.Comment;
import com.acormier.groupup.model.NotificationEmail;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.CommentRepository;
import com.acormier.groupup.repository.TeamRepository;
import com.acormier.groupup.repository.UserRepository;

import static java.util.stream.Collectors.toList;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
	private static final String POST_URL="";
	private final TeamRepository teamRepo;
	private final UserRepository userRepo;
	private final CommentMapper commentMap;
	private final CommentRepository commentRepo;
	private final MailContentBuilder mailContBuild;
	private final MailService mailServ;
	
	// save comment to team
	public void saveToTeam(CommentsDto commentsDto) {
	        Team team = teamRepo.findById(commentsDto.getTeamId())
	                .orElseThrow(() -> new PostNotFoundException(commentsDto.getTeamId().toString()));
	        User user = userRepo.findByUsername(commentsDto.getUserName()).orElseThrow();
	        Comment comment = commentMap.mapTeamToComment(commentsDto, team, user);
	        commentRepo.save(comment);
	        String message = mailContBuild.build(team.getTeamLeader().getUsername() + " posted a comment on your post." + POST_URL);
	        sendCommentNotification(message, team.getTeamLeader());
    }
	
	//send comment notification to team leader
	private void sendCommentNotification(String message, User user) {
		mailServ.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
	}
	
	//get all of the comments for the team by the team Id
	public List<CommentsDto> getAllCommentsForTeam(Long teamId){
		Team team = teamRepo.findById(teamId).orElseThrow(()-> new TeamNotFoundException("Team not found with id " + teamId));
		return commentRepo.findByTeam(team)
				.stream()
				.map(commentMap::mapToDto).collect(toList());
	}

	//get all the comments from the controller inputed username
	public List<CommentsDto> getAllCommentsForUser(String userName){
		User user = userRepo.findByUsername(userName)
				.orElseThrow(()-> new UsernameNotFoundException(userName + " Not found"));
		return commentRepo.findAllByUser(user)
				.stream()
				.map(commentMap::mapToDto)
				.collect(toList());
	}
	
	//delete the comment
	public void deleteComment(Long commentId) {
		commentRepo.deleteById(commentId);
	}

	//edit and update the comment
	public void editComment(CommentsDto commentDto) {
		Comment comment = commentRepo.findById(commentDto.getId()).orElseThrow(()-> new PostNotFoundException("Comment not found with id: " + commentDto.getId()));
		comment.setId(commentDto.getId());
		comment.setText(commentDto.getText());
		commentMap.updateComment(commentDto, comment);
		commentRepo.save(comment);
	}


	
	
}
