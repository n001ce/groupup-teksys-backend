package com.acormier.groupup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO created from the frontend

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {
	private Long teamId;
	private String teamName;
	private String gameTitle;
	private String url;
	private String console;
	private Integer teamSize;
}