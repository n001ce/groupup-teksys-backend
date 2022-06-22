package com.perscholas.groupupspringbootbackend.mapper;

import com.perscholas.groupupspringbootbackend.dto.GamesDto;
import com.perscholas.groupupspringbootbackend.model.Game;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T09:17:54-0400",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Override
    public GamesDto mapGameToDto(Game game) {
        if ( game == null ) {
            return null;
        }

        GamesDto.GamesDtoBuilder gamesDto = GamesDto.builder();

        gamesDto.gameId( game.getGameId() );
        gamesDto.gameTitle( game.getGameTitle() );
        gamesDto.description( game.getDescription() );
        gamesDto.background_image( game.getBackground_image() );
        gamesDto.rawgId( game.getRawgId() );

        gamesDto.numberOfTeams( mapTeams(game.getTeams()) );
        gamesDto.numberOfComments( mapComments(game.getComments()) );

        return gamesDto.build();
    }

    @Override
    public Game mapDtoToGame(GamesDto gamesDto) {
        if ( gamesDto == null ) {
            return null;
        }

        Game.GameBuilder game = Game.builder();

        game.gameId( gamesDto.getGameId() );
        game.rawgId( gamesDto.getRawgId() );
        game.gameTitle( gamesDto.getGameTitle() );
        game.description( gamesDto.getDescription() );
        game.background_image( gamesDto.getBackground_image() );

        return game.build();
    }
}
