package com.acormier.groupup.repository;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acormier.groupup.dto.TeamResponse;
import com.acormier.groupup.model.Game;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	Set<Team> findAllByGame(Game game);
	Set<Team> findByTeamLeader(User user);
}
