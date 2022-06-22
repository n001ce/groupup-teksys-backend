package com.perscholas.groupupspringbootbackend.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;



@Service
public class RawgService {
	OkHttpClient client = new OkHttpClient();
	
	@Value("${api.key}")
	private String apiKey;
	
	public String getGame(String gameId) throws IOException{
		Request request = new Request.Builder()
				.url("https://api.rawg.io/api/games?page_size=10&search=" + gameId + "&key=" + apiKey)
				.get()
				.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}
	
	public String getGameById(int gameId) throws IOException{
		Request request = new Request.Builder()
				.url("https://api.rawg.io/api/games/" + gameId + "?key=" + apiKey)
				.get()
				.build();
		Response response = client.newCall(request).execute();
		return response.body().string();
	}


}
