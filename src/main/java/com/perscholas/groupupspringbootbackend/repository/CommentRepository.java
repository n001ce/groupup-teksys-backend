package com.perscholas.groupupspringbootbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.perscholas.groupupspringbootbackend.model.Comment;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByTeam(Team team);
	List<Comment> findAllByUser(User user);
	List<Comment> findByGame(Optional<Game> game);
}
