package com.acormier.groupup.mapper;

import com.acormier.groupup.dto.UserProfileDto;
import com.acormier.groupup.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-27T20:16:00-0400",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class ProfileMapperImpl extends ProfileMapper {

    @Override
    public UserProfileDto map(User user) {
        if ( user == null ) {
            return null;
        }

        UserProfileDto.UserProfileDtoBuilder userProfileDto = UserProfileDto.builder();

        userProfileDto.userId( user.getUserId() );
        userProfileDto.username( user.getUsername() );
        if ( user.getCreated() != null ) {
            userProfileDto.joinedDate( user.getCreated().toString() );
        }

        userProfileDto.teams( leadTeams(user) );
        userProfileDto.joinedTeams( joinedTeams(user) );
        userProfileDto.games( user.getCollectedGames() );
        userProfileDto.comments( userComments(user) );

        return userProfileDto.build();
    }
}
