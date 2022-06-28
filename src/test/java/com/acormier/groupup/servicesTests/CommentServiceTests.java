package com.acormier.groupup.servicesTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.acormier.groupup.mapper.CommentMapper;
import com.acormier.groupup.mapper.ProfileMapper;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.repository.CommentRepository;
import com.acormier.groupup.repository.TeamRepository;
import com.acormier.groupup.repository.UserRepository;
import com.acormier.groupup.service.CommentService;
import com.acormier.groupup.service.MailContentBuilder;
import com.acormier.groupup.service.MailService;

@SpringBootTest
public class CommentServiceTests {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TeamRepository teamRepo;

	
	@Autowired
	private CommentMapper commentMap;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private MailContentBuilder mailContBuild;
	
	@Autowired
	private MailService mailServ;
	
	@Test
	@DisplayName("Should pass if team with id 1 is in db and fail if not")
	public void getAllCommentsByTeamTest() {
		Team team = teamRepo.findById((long)1).orElseThrow();
		CommentService commentService = new CommentService(teamRepo, userRepo, commentMap, commentRepo, mailContBuild, mailServ);
		Assertions.assertThat(commentService.getAllCommentsForTeam(team.getTeamId())).isNotNull();
	}
	
}
