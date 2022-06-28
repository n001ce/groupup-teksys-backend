package com.acormier.groupup.mapper;

import com.acormier.groupup.dto.TeamRequest;
import com.acormier.groupup.dto.TeamResponse;
import com.acormier.groupup.model.Game;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-27T20:16:00-0400",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class TeamMapperImpl extends TeamMapper {

    @Override
    public Team map(TeamRequest teamRequest, Game game, User teamLeader, Set<User> teamMembers) {
        if ( teamRequest == null && game == null && teamLeader == null && teamMembers == null ) {
            return null;
        }

        Team.TeamBuilder team = Team.builder();

        if ( teamRequest != null ) {
            team.console( teamRequest.getConsole() );
            team.teamSize( teamRequest.getTeamSize() );
            team.teamId( teamRequest.getTeamId() );
            team.teamName( teamRequest.getTeamName() );
            team.url( teamRequest.getUrl() );
        }
        team.game( game );
        team.teamLeader( teamLeader );
        Set<User> set = teamMembers;
        if ( set != null ) {
            team.teamMembers( new LinkedHashSet<User>( set ) );
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
        teamResponse.setBackground_image( teamGameBackground_image( team ) );
        if ( team.getCreatedDate() != null ) {
            teamResponse.setCreatedDate( team.getCreatedDate().toString() );
        }
        teamResponse.setTeamName( team.getTeamName() );
        teamResponse.setUrl( team.getUrl() );
        teamResponse.setConsole( team.getConsole() );
        teamResponse.setTeamSize( team.getTeamSize() );

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
        team.setTeamSize( request.getTeamSize() );
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

    private String teamGameBackground_image(Team team) {
        if ( team == null ) {
            return null;
        }
        Game game = team.getGame();
        if ( game == null ) {
            return null;
        }
        String background_image = game.getBackground_image();
        if ( background_image == null ) {
            return null;
        }
        return background_image;
    }
}
