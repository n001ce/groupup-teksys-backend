package com.perscholas.groupupspringbootbackend.mapper;

import com.perscholas.groupupspringbootbackend.dto.CommentsDto;
import com.perscholas.groupupspringbootbackend.model.Comment;
import com.perscholas.groupupspringbootbackend.model.Game;
import com.perscholas.groupupspringbootbackend.model.Team;
import com.perscholas.groupupspringbootbackend.model.User;
import java.util.Optional;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T09:17:53-0400",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment mapTeamToComment(CommentsDto commentsDto, Team team, User user) {
        if ( commentsDto == null && team == null && user == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        if ( commentsDto != null ) {
            comment.text( commentsDto.getText() );
        }
        if ( team != null ) {
            comment.team( team );
            comment.game( team.getGame() );
        }
        comment.user( user );
        comment.createdDate( java.time.Instant.now() );

        return comment.build();
    }

    @Override
    public Comment mapGameToComment(CommentsDto commentsDto, Optional<Game> game, User user) {
        if ( commentsDto == null && game == null && user == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        if ( commentsDto != null ) {
            comment.text( commentsDto.getText() );
        }
        if ( user != null ) {
            comment.user( user );
            comment.team( user.getTeam() );
        }
        comment.game( map( game ) );
        comment.createdDate( java.time.Instant.now() );

        return comment.build();
    }

    @Override
    public CommentsDto mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setId( comment.getId() );
        commentsDto.setCreatedDate( comment.getCreatedDate() );
        commentsDto.setText( comment.getText() );

        commentsDto.setTeamId( comment.getTeam().getTeamId() );
        commentsDto.setGameId( comment.getGame().getGameId() );
        commentsDto.setUserName( comment.getUser().getUsername() );

        return commentsDto;
    }

    @Override
    public Game map(Optional<Game> value) {
        if ( value == null ) {
            return null;
        }

        Game.GameBuilder game = Game.builder();

        return game.build();
    }
}
