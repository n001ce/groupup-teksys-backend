package com.perscholas.groupupspringbootbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
	Game findByGameTitle(String gameTitle);
	Game findByUser(User user);
	
}
