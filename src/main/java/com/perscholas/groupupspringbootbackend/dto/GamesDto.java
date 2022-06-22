package com.perscholas.groupupspringbootbackend.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private Integer numberOfComments;
}