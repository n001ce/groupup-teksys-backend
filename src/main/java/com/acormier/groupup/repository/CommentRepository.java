package com.acormier.groupup.repository;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acormier.groupup.model.Comment;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	Set<Comment> findByTeam(Team team);
	Set<Comment> findAllByUser(User user);
	void deleteByTeam(Team team);
}
