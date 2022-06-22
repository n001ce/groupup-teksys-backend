package com.perscholas.groupupspringbootbackend.mapper;

import com.perscholas.groupupspringbootbackend.dto.TeamRequest;
import com.perscholas.groupupspringbootbackend.dto.TeamResponse;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T09:17:54-0400",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class TeamMapperImpl extends TeamMapper {

    @Override
    public Team map(TeamRequest teamRequest, Game game, User teamLeader, List<User> teamMembers) {
        if ( teamRequest == null && game == null && teamLeader == null && teamMembers == null ) {
            return null;
        }

        Team.TeamBuilder team = Team.builder();

        if ( teamRequest != null ) {
            team.console( teamRequest.getConsole() );
            team.teamId( teamRequest.getTeamId() );
            team.teamName( teamRequest.getTeamName() );
            if ( teamRequest.getTeamSize() != null ) {
                team.teamSize( String.valueOf( teamRequest.getTeamSize() ) );
            }
            team.url( teamRequest.getUrl() );
        }
        team.game( game );
        team.teamLeader( teamLeader );
        List<User> list = teamMembers;
        if ( list != null ) {
            team.teamMembers( new ArrayList<User>( list ) );
        }
        team.createdDate( java.time.Instant.now() );

        return team.build();
    }

    @Override
    public TeamResponse mapToDto(Team team) {
        if ( team == null ) {
            return null;
        }

        TeamResponse teamResponse = new TeamResponse();

        teamResponse.setTeamId( team.getTeamId() );
        teamResponse.setGameTitle( teamGameGameTitle( team ) );
        teamResponse.setTeamLeader( teamTeamLeaderUsername( team ) );
        teamResponse.setTeamName( team.getTeamName() );
        teamResponse.setUrl( team.getUrl() );
        teamResponse.setConsole( team.getConsole() );

        teamResponse.setTeamCount( team.getTeamMembers().size() );
        teamResponse.setCommentCount( commentCount(team) );
        teamResponse.setTeamMembers( team.getTeamMembers() );

        return teamResponse;
    }

    @Override
    public void updateTeamFromDto(TeamRequest request, Team team) {
        if ( request == null ) {
            return;
        }

        team.setTeamId( request.getTeamId() );
        team.setTeamName( request.getTeamName() );
        if ( request.getTeamSize() != null ) {
            team.setTeamSize( String.valueOf( request.getTeamSize() ) );
        }
        else {
            team.setTeamSize( null );
        }
        team.setConsole( request.getConsole() );
        team.setUrl( request.getUrl() );
    }

    private String teamGameGameTitle(Team team) {
        if ( team == null ) {
            return null;
        }
        Game game = team.getGame();
        if ( game == null ) {
            return null;
        }
        String gameTitle = game.getGameTitle();
        if ( gameTitle == null ) {
            return null;
        }
        return gameTitle;
    }

    private String teamTeamLeaderUsername(Team team) {
        if ( team == null ) {
            return null;
        }
        User teamLeader = team.getTeamLeader();
        if ( teamLeader == null ) {
            return null;
        }
        String username = teamLeader.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
