package com.acormier.groupup.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;



@Service
public class RawgService {
	OkHttpClient client = new OkHttpClient();
	
	//rawg api key
	@Value("${api.key}")
	private String apiKey;
	
	//get game by game game title from rawg api
	public String getGame(String gameTitle) throws IOException{
		Request request = new Request.Builder()
				.url("https://api.rawg.io/api/games?page_size=10&search=" + gameTitle + "&key=" + apiKey)
				.get()
				.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
	
	//get game by game id from rawg api to save to the backend
	public String getGameById(int gameId) throws IOException{
		Request request = new Request.Builder()
				.url("https://api.rawg.io/api/games/" + gameId + "?key=" + apiKey)
				.get()
				.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}


}
