package com.acormier.groupup.servicesTests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.acormier.groupup.service.RawgService;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RawgServiceTest {

	
	@ParameterizedTest
	@ValueSource(strings = {"Overwatch", "Apex Legends", "Mario Kart"})
	void findGameByGameTitleTest(String query) {
		RawgService rawg = new RawgService();
		try {
			String apiCallTest = rawg.getGame(query);
			assertNotNull(apiCallTest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
