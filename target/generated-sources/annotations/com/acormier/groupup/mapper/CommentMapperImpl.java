package com.acormier.groupup.mapper;

import com.acormier.groupup.dto.CommentsDto;
import com.acormier.groupup.model.Comment;
import com.acormier.groupup.model.Team;
import com.acormier.groupup.model.User;
import java.time.Instant;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-27T20:16:00-0400",
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
        comment.team( team );
        comment.user( user );
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
        if ( comment.getCreatedDate() != null ) {
            commentsDto.setCreatedDate( comment.getCreatedDate().toString() );
        }
        commentsDto.setText( comment.getText() );

        commentsDto.setTeamId( comment.getTeam().getTeamId() );
        commentsDto.setUserName( comment.getUser().getUsername() );
        commentsDto.setTeamName( comment.getTeam().getTeamName() );

        return commentsDto;
    }

    @Override
    public void updateComment(CommentsDto commentDto, Comment comment) {
        if ( commentDto == null ) {
            return;
        }

        comment.setId( commentDto.getId() );
        comment.setText( commentDto.getText() );
        if ( commentDto.getCreatedDate() != null ) {
            comment.setCreatedDate( Instant.parse( commentDto.getCreatedDate() ) );
        }
        else {
            comment.setCreatedDate( null );
        }
    }
}
