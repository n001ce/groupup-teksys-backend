package com.perscholas.groupupspringbootbackend.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.perscholas.groupupspringbootbackend.dto.TeamRequest;
import com.perscholas.groupupspringbootbackend.dto.TeamResponse;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	List<Team> findAllByGame(Game game);
	List<Team> findByTeamLeader(Long userId);
	void save(TeamResponse mapTeamToDto);
	Team findByTeamName(String teamName);
//	@Modifying
//	@Query("insert into Team t.teamMembers:teamMembers")
//	void updateTeam(@Param("teamMembers"));
}
