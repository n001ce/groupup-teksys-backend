package com.acormier.groupup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acormier.groupup.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
	Game findByGameTitle(String gameTitle);
}
