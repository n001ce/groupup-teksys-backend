package com.acormier.groupup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO data transfered from the front end then updated in the backend with data.

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamesDto {
	private Long gameId;
	private String gameTitle;
	private String description;
	private String background_image;
	private String console;
	private Long rawgId;
	private Integer numberOfTeams;
	private Integer numberOfUsers;
}