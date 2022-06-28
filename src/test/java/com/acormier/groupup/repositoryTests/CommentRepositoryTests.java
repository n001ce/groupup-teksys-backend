package com.acormier.groupup.repositoryTests;

import java.time.Instant;
import java.util.List;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.acormier.groupup.model.Comment;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;
import com.acormier.groupup.repository.CommentRepository;
import com.acormier.groupup.repository.TeamRepository;
import com.acormier.groupup.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTests {
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private TeamRepository teamRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	//Junit test for saveComment
	@Test
	public void saveCommentTest() {
		Team team = teamRepo.findById((long) 1).orElseThrow();
		User user = userRepo.findById((long) 7).orElseThrow();
		Comment comment = Comment.builder()
				.text("fasdfasd")
				.createdDate(Instant.now())
				.user(user)
				.team(team)
				.build();
		commentRepo.save(comment);
		
		Assertions.assertThat(comment.getId()).isGreaterThan(0);
	}
	
	//Junit test for findByTeam
	@Test
	public void getListOfCommentsByTeamTest() {
		Team team = teamRepo.findById((long) 1).orElseThrow();
		Set<Comment> comments = commentRepo.findByTeam(team);
		
		Assertions.assertThat(comments.size()).isGreaterThan(0);
	}
	
	//Junit test for findAllByUser
	@Test
	public void getListOfCommentsByUserTest() {
		User user = userRepo.findById((long) 7).orElseThrow();
		Set<Comment> comments = commentRepo.findAllByUser(user);
		
		Assertions.assertThat(comments.size()).isGreaterThan(0);
	}
	
	//Junit test for deleteComment
	@Test
	public void deleteCommentTest() {
		Comment comment = Comment.builder()
				.text("fasdfasd")
				.createdDate(Instant.now())
				.build();
		commentRepo.save(comment);
		commentRepo.delete(comment);
		Comment after = commentRepo.findById(comment.getId()).orElse(null);
		Assertions.assertThat(after).isNull();
	}
	
	

}
