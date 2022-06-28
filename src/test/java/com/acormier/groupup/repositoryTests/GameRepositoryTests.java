package com.acormier.groupup.repositoryTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.acormier.groupup.model.Game;
import com.acormier.groupup.repository.GameRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GameRepositoryTests {
	
	@Autowired
	private GameRepository gameRepo;
	
	//test for findByGameTitle
	@Test
	public void getListOfCommentsByTeamTest() {
		Game game = Game.builder()
				.gameTitle("Test game")
				.description("Test game")
				.background_image("background image test")
				.build();
		gameRepo.save(game);
		gameRepo.findByGameTitle(game.getGameTitle());
		
		Assertions.assertThat(game.getGameId()).isGreaterThan(0);
	}

}
